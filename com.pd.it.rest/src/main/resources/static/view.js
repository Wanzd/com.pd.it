require.config({
	urlArgs : "r=" + (new Date()).getTime(),
	paths : {
		jquery : "jquery.min",
		easyui : "jquery.easyui.min",
		echarts : "echarts.min"
	},
	shim : {
		"easyui" : {
			deps : [ "jquery" ]
		}
	}
});

require([ 'jquery', 'easyui', 'common', 'tree', 'db', 'echarts' ], function(
		jquery, easyui, common, tree, db, ec) {
	debugger;
	console.log(location.href);
	var curParams = common.parseUrl(location.href);
	console.log(curParams.viewId);
	var viewJson = commmon.ajax("");

	$("#main").html(viewJson)
});