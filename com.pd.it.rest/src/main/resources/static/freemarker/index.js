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
require(
		[ 'jquery', 'easyui', 'common' ],
		function(jquery, easyui, common) {
			$impl = {
				init$filter : function() {
					common.init({
						type : "grid",
						id : "economicDateMonth"
					});
				},
				generate : function() {
					var result = common.html("../freemarkerRest/generate", {
						source : $("#source").val(),
						template : $("#template").val()
					});
					$("#result").val(result);

					var resultHighLight = common
							.html(
									"../freemarkerRest/generate",
									{
										source : $("#source").val(),
										template : $("#template")
												.val()
												.replaceAll('\\${',
														'<strong style="background-color:yellow;">${')
												.replaceAll('}',
														'}</strong>')
									});
					debugger;
					$("#highLight").html(
							resultHighLight.replaceAll('\n', '<p/>'));
					$("#dFreemarksTabs").tabs('select', 'HighLight');
				}
			};
			$api = $impl;
			$impl.init$filter();

		});