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
require([ 'jquery', 'easyui', 'common', 'tree', 'db' ], function(jquery,
		easyui, common, tree, db) {
	main = {
		init : function() {
			main.init$menu();
		},
		init$menu : function() {
			console.log("init$menu");
			$("#menu").tree({
				id : "id",
				text : "cn",
				pIdKey : "parentId",
				url : "rest/querys_menuTree",
				onClick : main.tabMenu
			});
		},
		tabMenu : function() {
			main.tabMenu$subNode();
			main.openModule();
		},
		tabMenu$subNode : function() {
			console.debug("main.tabMenu$subNode");
			var treeItem = tree.selected("menu");
			if (!treeItem) {
				return;
			}
			var subNode = $("#menu").tree("getNode", treeItem);
			if (subNode) {
				return;
			}
			$.ajax({
				type : "POST",
				url : "rest/querys_menuTree?id=" + treeItem.id,
				dataType : "JSON",
				success : function(data) {
					tree.refresh({
						treeId : "menu",
						parent : treeItem.target,
						data : data,
						onClickF : main.tabMenu
					});
				}
			});
		},
		tabMenu$refreshTab : function() {
			var treeItem = $("#menu").tree("getSelected");
			if (!treeItem) {
				return;
			}
			if (treeItem.url == null || treeItem.url == "") {
				return;
			}
			if (!$("#tt").tabs("exists", treeItem.cn)) {
				$('#tt').tabs('add', {
					title : treeItem.cn,
					href : treeItem.url,
					selected : true,
					closable : true
				});
			}
			$("#tt").tabs("select", treeItem.cn);
			setTimeout('$build("db.addEvent")', 500);
		},
		openModule : function() {
			console.debug("main.openModule");
			var treeItem = tree.selected("menu");
			if (!treeItem) {
				return;
			}
			if (treeItem.url == null || treeItem.url == "") {
				return;
			}
			$('#moduleFrame')[0].src = treeItem.url;
		}
	}
	main.init();
});