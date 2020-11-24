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
	var impl = {
		init$year : function() {
			var data = common.ajax("rest/ra_io$month");
			var yeasArr = common.attrArray(data, "year");
			debugger;

			option = {
				tooltip : {
					trigger : 'axis',
					axisPointer : { // 坐标轴指示器，坐标轴触发有效
						type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				legend : {
					data : [ '累计', '利润', '支出', '收入' ]
				},
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : true
						},
						dataView : {
							show : true,
							readOnly : false
						},
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'value'
				} ],
				yAxis : [ {
					type : 'category',
					axisTick : {
						show : false
					},
					data : common.attrArray(data, "month")
				} ],
				series : [ {
					name : '累计',
					type : 'bar',
					itemStyle : {
						normal : {
							label : {
								show : true,
								position : 'inside'
							}
						}
					},
					data : common.attrArray(data, "gaps")
				}, {
					name : '利润',
					type : 'bar',
					itemStyle : {
						normal : {
							label : {
								show : true,
								position : 'inside'
							}
						}
					},
					data : common.attrArray(data, "gap")
				}, {
					name : '收入',
					type : 'bar',
					barWidth : 5,
					itemStyle : {
						normal : {
							label : {
								show : true
							}
						}
					},
					data : common.attrArray(data, "supply")
				}, {
					name : '支出',
					type : 'bar',
					itemStyle : {
						normal : {
							label : {
								show : true
							}
						}
					},
					data : common.attrArray(data, "demand")
				} ]
			};
			// 初始化echarts实例
			var myChart = echarts.init(document.getElementById('chartYear'));

			// 使用制定的配置项和数据显示图表
			myChart.setOption(option);
		},
		init$month : function() {
			var data = common.ajax("rest/ra_io$month");
			var yeasArr = common.attrArray(data, "year");
			debugger;

			option = {
				tooltip : {
					trigger : 'axis',
					axisPointer : { // 坐标轴指示器，坐标轴触发有效
						type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				legend : {
					data : [ '累计', '利润', '支出', '收入' ]
				},
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : true
						},
						dataView : {
							show : true,
							readOnly : false
						},
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'value'
				} ],
				yAxis : [ {
					type : 'category',
					axisTick : {
						show : false
					},
					data : common.attrArray(data, "month")
				} ],
				series : [ {
					name : '累计',
					type : 'bar',
					itemStyle : {
						normal : {
							label : {
								show : true,
								position : 'inside'
							}
						}
					},
					data : common.attrArray(data, "gaps")
				}, {
					name : '利润',
					type : 'bar',
					itemStyle : {
						normal : {
							label : {
								show : true,
								position : 'inside'
							}
						}
					},
					data : common.attrArray(data, "gap")
				}, {
					name : '收入',
					type : 'bar',
					barWidth : 5,
					itemStyle : {
						normal : {
							label : {
								show : true
							}
						}
					},
					data : common.attrArray(data, "supply")
				}, {
					name : '支出',
					type : 'bar',
					itemStyle : {
						normal : {
							label : {
								show : true
							}
						}
					},
					data : common.attrArray(data, "demand")
				} ]
			};
			// 初始化echarts实例
			var myChart = echarts.init(document.getElementById('chartMonth'));

			// 使用制定的配置项和数据显示图表
			myChart.setOption(option);
		},
		init$year2:function(){
			var data = common.ajax("rest/ra_io$year");
			var yeasArr = common.attrArray(data, "year");
			debugger;

			option = {
				tooltip : {
					trigger : 'axis',
					axisPointer : { // 坐标轴指示器，坐标轴触发有效
						type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				legend : {
					data : [ '直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎', '百度', '谷歌', '必应',
							'其他' ]
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				xAxis : [ {
					type : 'category',
					data : [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
				} ],
				yAxis : [ {
					type : 'value'
				} ],
				series : [ {
					name : '直接访问',
					type : 'bar',
					data : [ 320, 332, 301, 334, 390, 330, 320 ]
				}, {
					name : '邮件营销',
					type : 'bar',
					stack : '广告',
					data : [ 120, 132, 101, 134, 90, 230, 210 ]
				}, {
					name : '联盟广告',
					type : 'bar',
					stack : '广告',
					data : [ 220, 182, 191, 234, 290, 330, 310 ]
				}, {
					name : '视频广告',
					type : 'bar',
					stack : '广告',
					data : [ 150, 232, 201, 154, 190, 330, 410 ]
				}, {
					name : '搜索引擎',
					type : 'bar',
					data : [ 862, 1018, 964, 1026, 1679, 1600, 1570 ],
					markLine : {
						lineStyle : {
							normal : {
								type : 'dashed'
							}
						},
						data : [ [ {
							type : 'min'
						}, {
							type : 'max'
						} ] ]
					}
				}, {
					name : '百度',
					type : 'bar',
					barWidth : 5,
					stack : '搜索引擎',
					data : [ 620, 732, 701, 734, 1090, 1130, 1120 ]
				}, {
					name : '谷歌',
					type : 'bar',
					stack : '搜索引擎',
					data : [ 120, 132, 101, 134, 290, 230, 220 ]
				}, {
					name : '必应',
					type : 'bar',
					stack : '搜索引擎',
					data : [ 60, 72, 71, 74, 190, 130, 110 ]
				}, {
					name : '其他',
					type : 'bar',
					stack : '搜索引擎',
					data : [ 62, 82, 91, 84, 109, 110, 120 ]
				} ]
			};

			// 初始化echarts实例
			var myChart = echarts.init(document.getElementById('chartYear2'));

			// 使用制定的配置项和数据显示图表
			myChart.setOption(option);
		},
		init : function() {
			this.init$year();
			this.init$year2();
			this.init$month();
		}
	}

	impl.init();
});