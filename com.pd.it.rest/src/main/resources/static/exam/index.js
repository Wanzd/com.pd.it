require.config({
			urlArgs : "r=" + (new Date()).getTime(),
			paths : {
				jquery : "../jquery.min",
				easyui : "../jquery.easyui.min",
				common : "../common"
			},
			shim : {
				"easyui" : {
					deps : ["jquery"]
				}
			}
		});
var $api = null;
require(['jquery', 'easyui', 'common'], function(jquery, easyui, common) {
			$impl = {
				init$filter : function() {
					common.init({
								type : "grid",
								id : "economicDateMonth"
							});
				}
			};
			$api = $impl;
			$impl.init$filter();

		});