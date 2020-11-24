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
			$('#dHealth').accordion({
				onSelect : function(title, index) {
					var selectF = {
						0 : $api.init$health$heat,
						1 : $api.init$health$person,
						2 : $api.init$health$weight
					}
					selectF[index]();
				}
			});
			var impl = {
				init$health$heat : function() {
					var data = common.ajax("rest/ra_health$heat");
					var groupArr = common.groupArray(data, "heatLevel");
					var treeMapVO = ai$echart.x$TreeMapVO(groupArr);

					option = {
						tooltip : {
							formatter : function(info) {
								var vo = info.data;
								var fmtStr = vo.detail;

								return fmtStr;
							}
						},
						series : [ {
							type : 'treemap',
							data : treeMapVO
						} ]
					};
					;
					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartHealthHeat'));

					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				},
				init$health$person : function() {
					var data = common.ajax("rest/ra_health$heat");
					var groupArr = common.groupArray(data, "name");
					var treeMapVO = ai$echart.x$sunBurstVO(groupArr);
					option = {
						tooltip : {
							formatter : function(info) {
								var vo = info.data;
								var fmtStr = vo.detail;

								return fmtStr;
							}
						},
						series : {
							type : 'sunburst',
							highlightPolicy : 'ancestor',
							data : treeMapVO,
							radius : [ 0, '95%' ],
							sort : null,
							levels : [ {}, {
								r0 : '15%',
								r : '35%',
								itemStyle : {
									borderWidth : 2
								},
								label : {
									rotate : 'tangential'
								}
							}, {
								r0 : '35%',
								r : '70%',
								label : {
									align : 'right'
								}
							}, {
								r0 : '70%',
								r : '72%',
								label : {
									position : 'outside',
									padding : 3,
									silent : false
								},
								itemStyle : {
									borderWidth : 3
								}
							} ]
						}

					};
					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartHealthPerson'));

					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				},
				
				init : function() {
					this.init$health$heat();
					// this.init$health$person();
				}
			};

			impl.init();
			debugger;
			$api = impl;
		});