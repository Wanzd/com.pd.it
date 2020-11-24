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

require(
		[ 'jquery', 'easyui', 'common', 'echarts' ],
		function(jquery, easyui, common, echarts) {
			var impl = {
				init$year : function() {
					// var data1 = common.ajax("rest/ra_house$all");
					// var salaryData = common.attrArray(data,
					// "area,cost,floor,floors,location,detail");

					var data = [ {
						name : '2018',
						itemStyle : {
							color : '#da0d68'
						},
						children : [ {
							name : '纪念日',
							value : 5,
							itemStyle : {
								color : '#975e6d'
							},
							children : [ {
								name : '端午节',
								value : 1,
								itemStyle : {
									color : '#000000'
								}
							}, {
								name : '春节',
								value : 1,
								itemStyle : {
									color : '#f99e1c'
								}
							}, {
								name : '劳动节',
								value : 1,
								itemStyle : {
									color : '#f99e1c'
								}
							}, {
								name : '万正东生日',
								value : 1,
								itemStyle : {
									color : '#f99e1c'
								}
							}, {
								name : '胡翠生日',
								value : 1,
								itemStyle : {
									color : '#f99e1c'
								}
							} ]
						}, {
							name : '待处理',
							itemStyle : {
								color : '#e0719c'
							},
							children : [ {
								name : 'Chamomile',
								value : 1,
								itemStyle : {
									color : '#f99e1c'
								}
							}, {
								name : 'Rose',
								value : 1,
								itemStyle : {
									color : '#ef5a78'
								}
							}, {
								name : 'Jasmine',
								value : 1,
								itemStyle : {
									color : '#f7f1bd'
								}
							} ]
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
					echarts.init(document.getElementById('chartYearSun'))
							.setOption(option);
				},
				init : function() {
					debugger;
					this.init$year();
				}
			};
			impl.init();
		});