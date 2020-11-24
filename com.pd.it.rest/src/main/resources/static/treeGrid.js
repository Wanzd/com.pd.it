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
	var curParams = common.parseUrl(location.href);
	var colSchema = common.ajax("rest/gridSchema/ra?gid=" + curParams.m);
	$('#td').treegrid({
		url : 'dbTree/root?mid=' + curParams.m,
		idField : 'id',
		treeField : 'id',
		columns : [ colSchema ]
	});
});