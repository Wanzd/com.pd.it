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
	var data = common.ajax("rest/ra_io$year");
	var yeasArr = common.attrArray(data, "year");
	debugger;

	option = {
		title : {
			text : '手机占有率',
			subtext : '虚构数据'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{b}: {c}"
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
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : false,
		series : [ {
			name : '矩形图',
			type : 'treemap',
			itemStyle : {
				normal : {
					label : {
						show : true,
						formatter : "{b}"
					},
					borderWidth : 1
				},
				emphasis : {
					label : {
						show : true
					}
				}
			},
			data : [ {
				name : '三星',
				value : 6
			}, {
				name : '小米',
				value : 4
			}, {
				name : '苹果',
				value : 4
			}, {
				name : '华为',
				value : 2
			}, {
				name : '联想',
				value : 2
			}, {
				name : '魅族',
				value : 1
			}, {
				name : '中兴',
				value : 1
			} ]
		} ]
	};
	// 初始化echarts实例
	var myChart = echarts.init(document.getElementById('chartmain'));

	// 使用制定的配置项和数据显示图表
	myChart.setOption(option);
});