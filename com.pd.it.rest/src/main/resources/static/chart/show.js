require.config({
			urlArgs : "r=" + (new Date()).getTime(),
			paths : {
				jquery : "../jquery.min",
				easyui : "../jquery.easyui.min",
				common : "../common",
				echarts : "../echarts.min",
				echartsgl : "../echarts-gl.min",
				ai$echart : "../ai$echart"
			},
			shim : {
				"easyui" : {
					deps : ["jquery"]
				}
			}
		});
var $api = null;
require(['jquery', 'easyui', 'common', 'echarts', 'echartsgl', 'ai$echart'],
		function(jquery, easyui, common, echarts, echartsgl, ai$echart) {
			var myChart = echarts.init(document.getElementById('myChart'));
			var chartId = common.parseUrl(location.href).id;
			var chartUrl = '../CHART:' + chartId;
			var chartJson = common.ajax(chartUrl);
			echarts.util.each(chartJson.children, function(datum, index) {
						datum.collapsed = true;
					});
			option = ai$echart.x$option(chartJson);
			myChart.setOption(option);
			myChart.on('click', option.onclick);
		});