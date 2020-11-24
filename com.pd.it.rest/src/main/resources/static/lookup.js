require.config({
	urlArgs : "r=" + (new Date()).getTime(),
	paths : {
		jquery : "jquery.min",
		easyui : "jquery.easyui.min",
		echarts : "echarts.min",
		wordcloud : "echarts-wordcloud.min"
	},
	shim : {
		"easyui" : {
			deps : [ "jquery" ]
		}
	}
});
var $api = null;
require([ 'jquery', 'easyui', 'common', 'echarts', 'wordcloud', 'ai$echart' ],
		function(jquery, easyui, common, echarts, wordcloud, ai$echart) {
			$impl = {
				init$filter : function() {
					common.init({
						type : "date$month",
						id : "economicDateMonth"
					});
				}
			};
			$api = $impl;
			$impl.init$filter();

		});