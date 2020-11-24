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
			$api = this;
			this.search = function() {
				var data = $("#form").serializeJson();
				if (data.chartId instanceof Array) {
					$.each(data.chartId, function(idx, it) {
								var myChart = echarts.init(document
										.getElementById('myChart' + idx));
								myChart.clear();
								var chartUrl = '../CHART:' + it;
								var chartJson = common.ajax(chartUrl);
								option = ai$echart.x$option(chartJson);
								myChart.setOption(option);
							})
				} else {
					var myChart = echarts.init(document
							.getElementById('myChart0'));
					myChart.clear();
					var chartUrl = '../CHART:' + data.chartId;
					var chartJson = common.ajax(chartUrl);
					option = ai$echart.x$option(chartJson);
					myChart.setOption(option);
				}
			}
			$("#moduleId").combobox({
				url : '../ai/ISystemChartComboModuleIdDao/queryCombo',
				valueField : 'id',
				textField : 'text',
				onSelect : function(rec) {
					$("#chartType").combobox("clear");
					var chartTypeData = common.ajax(
							'../ai/ISystemChartComboChartTypeDao/queryCombo', {
								"moduleId" : rec.id
							});
					$('#chartType').combobox('loadData', chartTypeData);

					$("#chartId").combobox("clear");
					var chartIdData = common.ajax(
							'../ai/ISystemChartDao/queryCombo', {
								"moduleId" : rec.id
							});
					$('#chartId').combobox('loadData', chartIdData);
				}
			});
			$("#chartType").combobox({
				url : '../ai/ISystemChartComboChartTypeDao/queryCombo',
				valueField : 'id',
				textField : 'text',
				onSelect : function(rec) {
					$("#chartId").combobox("clear");
					var chartIdData = common.ajax(
							'../ai/ISystemChartDao/queryCombo', {
								"moduleId" : $("#moduleId").val(),
								"chartType" : rec.id
							});
					$('#chartId').combobox('loadData', chartIdData);
				}
			});
			$("#chartId").combobox({
						url : '../ai/ISystemChartDao/queryCombo',
						valueField : 'id',
						textField : 'text',
						multiple : true
					});
			$("#search").click($api.search);

		});