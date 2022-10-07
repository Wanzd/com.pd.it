require.config({
			urlArgs : "r=" + (new Date()).getTime(),
			paths : {
				jquery : "../jquery.min",
				easyui : "../jquery.easyui.min",
				common : "../common",
				echarts : "../echarts.min",
				echartsgl : "../echarts-gl.min"
			},
			shim : {
				"easyui" : {
					deps : ["jquery"]
				}
			}
		});
var $api = null;
require(['jquery', 'easyui', 'common', 'echarts', 'echartsgl', 'wordcloud',
				'ai$echart'], function(jquery, easyui, common, echarts,
				echartsgl, wordcloud, ai$echart) {
			$api = this;
			this.search = function() {
				var myChart = echarts.init(document.getElementById('myChart'));
				data2 = $("#fSari").serializeJson();
				url = '../ai/IViewDao/queryList';
				result = common.ajax(url, data2);
				var symbolSize = 2.5;
				option = {
					grid3D : {},
					xAxis3D : {
						type : 'category'
					},
					yAxis3D : {},
					zAxis3D : {},
					dataset : {
						dimensions : ['qty', 'city', {
									name : 'creationDate',
									type : 'ordinal'
								}],
						source : result
					},
					series : [{
								type : 'scatter3D',
								symbolSize : symbolSize,
								encode : {
									x : 'city',
									y : 'creationDate',
									z : 'qty',
									tooltip : [0, 1, 2]
								}
							}]
				};
				myChart.setOption(option);
			}
			$("#viewName").combobox({
						url : '../ai/IViewDao/queryCombo',
						valueField : 'id',
						textField : 'text'
					});
			$("#qtyType").combobox({
						url : '../ai/Sari$IQtyTypeDao/queryCombo',
						valueField : 'id',
						textField : 'text'
					});
			$("#province").combobox({
						url : '../ai/Sari$IProvinceDao/queryCombo',
						valueField : 'id',
						textField : 'text',
						onSelect : function(rec) {
							$("#city").combobox("clear");
							var url = '../ai/IAppSariCityDao/queryCombo';
							var data = {
								"province" : rec.id
							};
							var results = common.ajax(url, data);
							$('#city').combobox('loadData', results);
						}
					});
			$("#city").combobox({
						valueField : 'id',
						textField : 'text',
						multiple : true
					});
			$("#search").click($api.search);
		});