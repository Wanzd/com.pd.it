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
require([ 'jquery', 'easyui', 'common', 'echarts' ], function(jquery, easyui,
		common, echarts) {
	var curCfg = {
		div : {
			id : "chartTodoYear"
		}
	}
	var impl = {
		init$todo$year : function() {
			var data = common.ajax("rest/json_health$weight");
			var data = [ {
				name : '1月',
				value : 31,
				itemStyle : {
					color : 'red'
				},
				children : [ {
					name : '1日',
					value : 1,
					itemStyle : {
						color : 'green'
					}
				}, {
					name : '',
					value : 10,
					itemStyle : {
						color : 'gray'
					}
				}, {
					name : '12日',
					value : 1,
					itemStyle : {
						color : '#e0719c'
					},
					children : [ {
						name : 'test1',
						value : 0.1,
						itemStyle : {
							color : 'green'
						}
					}, {
						name : 'test2',
						value : 0.2,
						itemStyle : {
							color : 'blue'
						}
					}, {
						name : 'test3',
						value : 0.3,
						itemStyle : {
							color : 'red'
						}
					} ]
				} ]
			}, {
				name : '2月',
				value : 28,
				itemStyle : {
					color : 'gray'
				}
			}, {
				name : '3月',
				value : 31,
				itemStyle : {
					color : 'gray'
				}
			}, {
				name : '4月',
				value : 30,
				itemStyle : {
					color : 'gray'
				}
			}, {
				name : '5月',
				value : 31,
				itemStyle : {
					color : 'gray'
				}
			}, {
				name : '6月',
				value : 30,
				itemStyle : {
					color : 'gray'
				}
			}, {
				name : '7月',
				value : 31,
				itemStyle : {
					color : 'gray'
				}
			}, {
				name : '8月',
				value : 31,
				itemStyle : {
					color : 'gray'
				}
			} , {
				name : '9月',
				value : 30,
				itemStyle : {
					color : 'gray'
				}
			} , {
				name : '10月',
				value : 31,
				itemStyle : {
					color : 'gray'
				}
			} , {
				name : '11月',
				value : 30,
				itemStyle : {
					color : 'gray'
				}
			} , {
				name : '12月',
				value : 31,
				itemStyle : {
					color : 'gray'
				}
			} ];

			option = {
				title : {
					text : '待办太阳图',
					subtext : '',
					textStyle : {
						fontSize : 14,
						align : 'center'
					},
					subtextStyle : {
						align : 'center'
					},
					sublink : ''
				},
				series : {
					type : 'sunburst',
					highlightPolicy : 'ancestor',
					data : data,
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
			echarts.init(document.getElementById(curCfg.div.id)).setOption(
					option);

		},
		init$health$weight : function() {
			var data = common.ajax("rest/ra_health$weight");
			option = {
				xAxis : {
					data : common.attrArray(data, "logDate")
				},
				yAxis : {
					type : 'value',
					min : 80
				},
				series : [ {
					data : common.attrArray(data, "value"),
					type : 'line'
				} ]
			};

			// 初始化echarts实例
			var myChart = echarts.init(document
					.getElementById('chartHealthWeight'));

			// 使用制定的配置项和数据显示图表
			myChart.setOption(option);
		},
		init$health : function() {
			this.init$health$weight();
		},
		init$summary : function() {
			$("#divSummaryHealth").html("健康指数：肥胖");
			$("#divSummaryRelation").html("关系指数：计算机国考");
			$("#divSummaryIO").html("收入指数：XXXXXX");
		},
		init : function() {
			this.init$todo$year();
			this.init$health();
			this.init$summary();
		}
	}

	impl.init();
});