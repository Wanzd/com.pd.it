define([ "db" ], function(common) {
	return {
		c$open : function() {
			$("#dlg").dialog("open").dialog("setTitle", "新增");
			$("#fm").form("clear");
			url = "db/c?mid=" + $r("page.id");
		},
		save : function(module) {
			debugger;
			var rows = $("#dg").datagrid("getChanges");

			var rs = common.ajax("rest/us_" + module, {
				data : rows
			});
			console.debug(rs);
		},
		r : function() {

		},
		u$open : function() {
			var row = $("#dg").datagrid("getSelected");
			if (row) {
				$("#dlg").dialog("open").dialog("setTitle", "编辑");
				$("#fm").form("load", row);
				url = "db/u?mid=" + $r("page.id");
			}
		},
		d : function() {
			var row = $("#dg").datagrid("getSelected");
			if (row) {
				$.messager.confirm("Confirm", "确定要删除吗?", function(r) {
					if (r) {
						var rs = $r("ajax.post.json", {
							url : "db/d?mid=" + $r("page.id"),
							data : {
								id : row.id
							}
						});
						if (rs.status == "SUCCESS") {
							$("#dg").datagrid("reload"); // reload
							// the
							// user data
						} else {
							$.messager.show({ // show error message
								title : "Error",
								msg : rs.errorMsg
							});
						}
					}
				});
			}
		},
		addEvent : function() {
			$('#dg').datagrid({
				onClickCell : function(index, field, value) {
					$build("db.u$open");
				}
			});
		}

	};
});
