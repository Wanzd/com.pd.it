require.config({
	urlArgs : "r=" + (new Date()).getTime(),
	paths : {
		jquery : "jquery.min",
		easyui : "jquery.easyui.min"
	},
	shim : {
		"easyui" : {
			deps : [ "jquery" ]
		}
	}
});
var _common=null;
require(
		[ 'jquery', 'easyui', 'ai', 'common', 'tree', 'db' ],
		function(jquery, easyui, ai, common, tree, db) {
			_common=common;
			var editIndex = undefined;
			var curParams = common.parseUrl(location.href);
			var $pageCfg = {
				id : "lookupType"
			};
			var $pageAtom = {
				init : function() {
					$('#dg').datagrid($pageCfg.datagrid.cfg)
				},
				action : {
					search : function() {
						$('#tt').datagrid('load', {
							itemid : $('#itemid').val(),
							productid : $('#productid').val()
						});
					},
					update : function(index) {
						$('#dg').datagrid('updateRow', {
							index : index,
							row : {}
						});
					}
				}
			};
			var colSchema = common.ajax("rest/str_gridSchema/?gid="
					+ curParams.m);
			var columns = [ {
				width : 80,
				field : 'ck',
				checkbox : true
			} ].concat(colSchema);
			var $pageCfg = {
				datagrid : {
					cfg : { // 定位到Table标签，Table标签的ID是grid
						border:1,
						title : "DataSet",
						width : '100%',
						height : '100%',
						singleSelect : false,
						url : 'rest/ra_' + curParams.m, // 指向后台的Action来获取当前菜单的信息的Json格式的数据
						iconCls : 'icon-edit',
						nowrap : true,
						autoRowHeight : true,
						striped : true,
						collapsible : true,
						pagination : true,
						pageSize : 20,
						pageList : [ 50, 100, 200 ],
						rownumbers : false,
						fit:true,
						// sortName: 'ID', //根据某个字段给easyUI排序
						sortOrder : 'asc',
						remoteSort : false,
						idField : 'id',
						// queryParams : queryData, // 异步查询的参数
						columns : [ columns ],

						onBeforeEdit : function(index, row) {
							row.editing = true;
							$pageAtom.action.update(index);
						},
						onAfterEdit : function(index, row) {
							row.editing = false;
							$pageAtom.action.update(index);
						},
						onCancelEdit : function(index, row) {
							row.editing = false;
							$pageAtom.action.update(index);
						},
						toolbar : [
								{
									id : 'btnAdd',
									text : '添加',
									iconCls : 'icon-add',
									handler : function() {
										$('#dg').datagrid('appendRow', {});
										editIndex = $('#dg')
												.datagrid('getRows').length - 1;
										$('#dg').datagrid('selectRow',
												editIndex).datagrid(
												'beginEdit', editIndex);
									}
								},
								'-',
								{
									id : 'btnSave',
									text : '修改',
									iconCls : 'icon-save',
									handler : function() {
										debugger;
										$('#dg').datagrid('endEdit', editIndex);
										var rows = $("#dg").datagrid(
												"getChanges");

										var url = "rest/us_" + curParams.m;
										var data = {
											list : JSON.stringify(rows)
										};
										var rs = common.ajax(url, data);
										if (rs) {
											$("#dg").datagrid("reload");
											$('#dg').datagrid('uncheckAll');
										} else {
											alert("保存失败");
										}

									}
								},
								'-',
								{
									id : 'btnDelete',
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										$.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
										    if (r){
										    	debugger;
												var rows = $("#dg").datagrid(
														"getSelections");
												var url = "rest/ds_" + curParams.m;
												var data = {
													list : JSON.stringify(rows)
												};
												var rs = common.ajax(url, data);
												if (rs) {
													$("#dg").datagrid("reload");
													$('#dg').datagrid('uncheckAll');
												} else {
													alert("删除失败");
												}
										    }
										});
										
									}
								},
								'-',
								{
									id : 'btnView',
									text : '查看',
									iconCls : 'icon-table',
									handler : function() {
										var selectRow = $('#dg').datagrid(
												'getSelected');
										var detailObj = common.ajax("rest/"
												+ curParams.m + ".detail?id="
												+ selectRow.id);
										console.debug(detailObj);
									}
								}, '-', {
									id : 'btnReload',
									text : '刷新',
									iconCls : 'icon-reload',
									handler : function() {
										// 实现刷新栏目中的数据
										$("#dg").datagrid("reload");
										$('#dg').datagrid('uncheckAll');
									}
								} ],
						onClickRow : function(rowIndex, rowData) {
							$('#dg').datagrid('uncheckAll');
							$('#dg').datagrid('checkRow', rowIndex);
							$('#dg').datagrid('endEdit', editIndex);
							editIndex = rowIndex;
							$("#dg").datagrid("beginEdit", rowIndex); // 这句如果注释掉下一行的ed就获取不到值
							// $build("db.u$open");
						}
					}
				}
			};
			var $pageImpl = {
				reader : {
					page : {
						id : function() {
							return "lookup$type";
						}
					}
				}
			};
			$pageAtom.init();
		});