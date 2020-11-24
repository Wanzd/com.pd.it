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
				init$all : function() {
					var data = common.ajax("rest/ra_house$all");
					var salaryData = common.attrArray(data,
							"area,cost,floor,floors,location,detail");

					option = {
						xAxis : {
							scale : true,
							name : "面积"
						},
						yAxis : {
							scale : true,
							name : "总价"
						},
						tooltip : {
							trigger : 'item',
							axisPointer : {
								type : "shadow"
							},
							formatter : function(params) {
								debugger;
								var curVO = params.data;
								return "位置：" + curVO[4] + "<br/>面积：" + curVO[0]
										+ "<br/>总价：" + curVO[1];
							},
							position : function(point, params, dom, rect, size) {
								return [ point[0], point[1] + 30 ];
							}
						},
						series : [ {
							type : 'scatter',
							data : salaryData,
						} ]
					};
					echarts.init(document.getElementById('chartAll'))
							.setOption(option);

				},
				init$target : function() {
					var data = common.ajax("rest/ra_house$target");
					var salaryData = common.attrArray(data,
							"area,cost,floor,floors,location,detail,url");

					option = {
						xAxis : {
							scale : true,
							name : "面积"
						},
						yAxis : {
							scale : true,
							name : "总价"
						},
						tooltip : {
							trigger : 'item',
							axisPointer : {
								type : "shadow"
							},
							formatter : function(params) {
								debugger;
								var curVO = params.data;
								return "位置：" + curVO[4] + "<br/>面积：" + curVO[0]
										+ "<br/>总价：" + curVO[1];
							},
							position : function(point, params, dom, rect, size) {
								return [ point[0], point[1] + 30 ];
							}
						},
						series : [ {
							type : 'scatter',
							data : salaryData,
						} ]
					};
					var myChart=echarts.init(document.getElementById('chartTarget'))
							;
					myChart.on('click', function(params) {
						window.open(params['data'][6]);
					});
					myChart.setOption(option);

				},
				init$location : function() {
					var data = common.ajax("rest/ra_house$location");
					var salaryData = common.attrArray(data,
							"area,cost,floor,floors,location,detail");

					var textArr = common.attrArray(data, "text");
					var cArr = common.attrArray(data, "c");
					option = {
						title : {
							text : '高频词关联房间数量'
						},
						tooltip : {
							trigger : 'axis',
							axisPointer : {
								type : 'shadow'
							}
						},
						legend : {
							data : [ '房数' ]
						},
						grid : {
							left : '3%',
							right : '4%',
							bottom : '3%',
							containLabel : true
						},
						xAxis : {
							type : 'value',
							boundaryGap : [ 0, 0.01 ]
						},
						yAxis : {
							type : 'category',
							data : textArr
						},
						series : [ {
							name : '房数',
							type : 'bar',
							data : cArr
						} ]
					};
					echarts.init(document.getElementById('chartLocation'))
							.setOption(option);

				},
				init$map : function() {
					var data = common.ajax("rest/ra_house$target");
					var salaryData = common.attrArray(data,
							"area,cost,floor,floors,location,detail");

					option = {
						xAxis : {
							scale : true,
							name : "面积"
						},
						yAxis : {
							scale : true,
							name : "总价"
						},
						tooltip : {
							trigger : 'item',
							axisPointer : {
								type : "shadow"
							},
							formatter : function(params) {
								debugger;
								var curVO = params.data;
								return "位置：" + curVO[4] + "<br/>面积：" + curVO[0]
										+ "<br/>总价：" + curVO[1];
							},
							position : function(point, params, dom, rect, size) {
								return [ point[0], point[1] + 30 ];
							}
						},
						series : [ {
							type : 'scatter',
							data : salaryData,
						} ]
					};
					echarts.init(document.getElementById('chartMap'))
							.setOption(option);

				},
				init : function() {
					this.init$target();
					this.init$all();
					this.init$map();
					this.init$location();
				}
			};
			impl.init();
		});