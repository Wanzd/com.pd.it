require.config({
	urlArgs : "r=" + (new Date()).getTime(),
	paths : {
		jquery : "../jquery.min",
		easyui : "../jquery.easyui.min",
		common : "../common"
	},
	shim : {
		"easyui" : {
			deps : [ "jquery" ]
		}
	}
});
var $api = null;
require([ 'jquery', 'easyui', 'common' ], function(jquery, easyui, common) {
	$impl = {
		init$filter : function() {
			common.init({
				type : "grid",
				id : "economicDateMonth"
			});
		},
		generate : function() {
			var result=common.html("../freemarkerRest/generate", {
				source :  $("#source").val(),
				template :  $("#template").val()
			})		
			$("#result").val(result);
			$("#dFreemarksTabs").tabs('select','Result');
		}
	};
	$api = $impl;
	$impl.init$filter();

});