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
var $cfg = {
	year : (new Date().getYear() + 1900),
	day : {
		warnLevel : {},
		map : {}
	}
};
var $api = null;
require(
		[ 'jquery', 'easyui', 'common', 'echarts', 'wordcloud', 'ai$echart' ],
		function(jquery, easyui, common, echarts, wordcloud, ai$echart) {
			$impl = {
				init$todo : function() {
					var todoRs = common.ajax("common/ra/todo", {
						year : 2018
					});
					$.each(todoRs,
							function(index, value, array) {
								var startDate = value.startDate;
								var endDate = value.endDate;

								if ($cfg.day.map[startDate] == null) {
									$cfg.day.map[startDate] = [];
								}
								$cfg.day.map[startDate].push(value);

								if ($cfg.day.warnLevel[startDate] == null) {
									$cfg.day.warnLevel[startDate] = 0;
								}
								$cfg.day.warnLevel[startDate] = Math.max(
										$cfg.day.warnLevel[startDate],
										value.baseLevel);
							});
				},
				init$month : function() {
					for (var i = 1; i <= 12; i++) {
						var curMonthHtml = this.getMonthHtml($cfg.year, i);
						$("#month" + i).html(curMonthHtml);
					}
				},
				getMonthHtml : function(year, month) {
					// 获取第一天是星期几
					var weekDay = new Date(year, month - 1, 1).getDay();

					// 攻取当前月有几天
					var monthDayCount = new Date(year, month, 0).getDate();

					// 生成html的table格式字符串
					var tableHtml = "";
					tableHtml += "<table border=0 style='width:100%;height:200;font-size:12px;'	>";
					tableHtml += "<tr><td colspan=7>" + month + "月</td></tr>";
					tableHtml += "<tr  bgcolor='lightgreen'><td>一</td><td>二</td><td>三</td><td>四</td><td>五</td><td>六</td><td>日</td></tr>"
					tableHtml += "<tr>";

					var rowCount = 0;
					for (var i = 0; i < (weekDay + 6) % 7; i++) {
						tableHtml += "<td bgcolor='white'></td>";
					}
					for (var i = 1; i <= monthDayCount; i++) {
						var dateStr = year + "_" + month + "_" + i;
						var titleStr = this.getTitle$day(year, month, i);
						var bgColorStr = this.getColor$day(year, month, i);
						var backgroundStr = this.getBackground$day(year, month,
								i);
						tableHtml += "<td id='d" + dateStr + "'" + titleStr
								+ bgColorStr + backgroundStr + ">" + i
								+ "</td>";
						if ((i + weekDay) % 7 == 1 || i == monthDayCount) {
							tableHtml += "</tr>";
							rowCount++;
						}
					}
					for (var i = rowCount; i <= 5; i++) {
						tableHtml += "<tr><td bgcolor='white' colspan=7>-</td></tr>";
					}
					tableHtml += "</table>";
					return tableHtml;
				},
				getColor$day : function(y, m, d) {
					var diff = (new Date() - new Date(y, m - 1, d)) / 86400000;
					if (diff >= 0 && diff < 1) {
						return " bgcolor='blue'";
					}
					if (diff >= 0) {
						return " bgcolor='lightgray'";
					}

					if ($cfg.day.warnLevel[y + "-" + m + "-" + d] != null) {
						var warnLevel = $cfg.day.warnLevel[y + "-" + m + "-"
								+ d];

						var warnColor = "white";
						switch (warnLevel) {
						case 1:
							warnColor = "yellow";
							break;
						case 2:
							warnColor = "orange";
							break;
						case 3:
							warnColor = "red";
							break;
						default:
							warnColor = "white"
							break;
						}
						return " bgcolor='" + warnColor + "'";
					}
					return " bgcolor='white'";
				},
				getTitle$day : function(y, m, d) {
					if ($cfg.day.map[y + "-" + m + "-" + d] != null) {
						var todoDayList = $cfg.day.map[y + "-" + m + "-" + d];
						var titleRs = "";
						$.each(todoDayList, function(index, value, array) {
							titleRs += value.detail;
						})
						return " title='" + titleRs + "'";
					}
					return "";
				},
				getBackground$day : function(y, m, d) {
					var diff = (new Date() - new Date(y, m - 1, d)) / 86400000;
					if (diff >= 0 && diff < 1) {
						return "";
					}
					return "";
				},
				initSummaryYear$sun : function() {
					debugger;
					var data = common.ajax("common/ra/todo", {
						year : $cfg.year
					});
					var groupArr = common.groupArray(data, "name");
					var treeMapVO = ai$echart.x$sunBurstVO(groupArr);
					var data = [ {
						name : '2018年',
						value : 365,
						itemStyle : {
							color : 'en'
						},
						children : [ {
							name : '1月',
							value : 31,
							itemStyle : {
								color : 'gray'
							}
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
						}, {
							name : '9月',
							value : 30,
							itemStyle : {
								color : 'gray'
							}
						}, {
							name : '10月',
							value : 31,
							itemStyle : {
								color : 'gray'
							}
						}, {
							name : '11月',
							value : 30,
							itemStyle : {
								color : 'red'
							},
							children : [ {
								name : '24日',
								value : 1,
								itemStyle : {
									color : 'red'
								},
								children : [ {
									name : '胡翠生日',
									value : 1,
									itemStyle : {
										color : 'red'
									}
								} ]
							}, {
								name : '25日',
								value : 1,
								itemStyle : {
									color : 'gray'
								}
							} ]
						}, {
							name : '12月',
							value : 31,
							itemStyle : {
								color : 'yellow'
							}
						} ]
					} ];
					option = {
						title : {
							text : 'WORLD COFFEE RESEARCH SENSORY LEXICON',
							subtext : 'Source: https://worldcoffeeresearch.org/work/sensory-lexicon/',
							textStyle : {
								fontSize : 14,
								align : 'center'
							},
							subtextStyle : {
								align : 'center'
							},
							sublink : 'https://worldcoffeeresearch.org/work/sensory-lexicon/'
						},
						series : {
							type : 'sunburst',
							highlightPolicy : 'ancestor',
							data : data,
							radius : [ 0, '95%' ],
							sort : null,
							levels : [ {}, {
								r0 : '1%',
								r : '15%',
								itemStyle : {
									borderWidth : 2
								},
								label : {
									rotate : 'tangential'
								}
							}, {
								r0 : '15%',
								r : '30%',
								label : {
									align : 'right'
								}
							}, {
								r0 : '30%',
								r : '70%',
								label : {
									align : 'right'
								}
							}, {
								r0 : '70%',
								r : '90%',
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
							.getElementById('chartSummaryYear$sun'));

					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				}
			};

			$api = $impl;
			$impl.init$todo();
			$impl.init$month();
			$impl.initSummaryYear$sun();
		});