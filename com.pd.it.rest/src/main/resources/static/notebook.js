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
require([ 'jquery', 'easyui', 'common', 'echarts', 'ai$echart' ],
		function(jquery, easyui, common, echarts, ai$echart) {
			var impl = {
				init$price$product : function() {
					var data = common.ajax("rest/ra_notebook$price$product");
					var salaryData = common.attrArray(data,
							"price,monthSales,sellNum,company,title");

					option = {
						xAxis : {
							scale : true,
							name : "平均售价"
						},
						yAxis : {
							scale : true,
							name : "月销售量"
						},
						tooltip : {
							trigger : 'item',
							axisPointer : {
								type : "shadow"
							},
							formatter : function(params) {
								debugger;
								var curVO = params.data;
								return "产品：" + curVO[4] + "<br/>公司：" + curVO[3]
										+ "<br/>商家数：" + curVO[2] + "<br/>平均价格："
										+ curVO[0] + "<br/>销量：" + curVO[1];
							}
						},
						series : [ {
							type : 'scatter',
							data : salaryData,
						} ]
					};
					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartPrice$product'));

					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				},
				init$price$company : function() {
					debugger;
					var data = common.ajax("rest/ra_notebook$price$company");
					debugger;
					var salaryData = common.attrArray(data,
							"price,monthSales,sellNum,company");

					option = {
						xAxis : {
							scale : true,
							name : "平均售价"
						},
						yAxis : {
							scale : true,
							name : "月销售量"
						},
						tooltip : {
							trigger : 'item',
							axisPointer : {
								type : "shadow"
							},
							formatter : function(params) {
								debugger;
								var curVO = params.data;
								return "公司：" + curVO[3] + "<br/>商家数："
										+ curVO[2] + "<br/>平均价格：" + curVO[0]
										+ "<br/>销量：" + curVO[1];
							}
						},
						series : [{
							type : 'scatter',
							data : salaryData,
						} ]
					};
					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartPrice$company'));

					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				},
				init$income$company : function() {
					debugger;
					var data = common.ajax("rest/ra_notebook$income$company");
					var companyArr = common.attrArray(data, "company");
					var incomeArr = common.attrArray(data, "income");
					debugger;
					option = {
						title : {
							text : '笔记本电脑公司收入统计'
						},
						tooltip : {
							trigger : 'axis',
							axisPointer : {
								type : 'shadow'
							}
						},
						legend : {
							data : [ '收入' ]
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
							data : companyArr
						},
						series : [ {
							name : '收入',
							type : 'bar',
							data : incomeArr
						}]
					};

					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartIncome$company'));

					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				},
				init$income$shopAvg : function() {
					debugger;
					var data = common.ajax("rest/ra_notebook$income$shopAvg");
					var companyArr = common.attrArray(data, "company");
					var incomeArr = common.attrArray(data, "income");
					debugger;
					option = {
						title : {
							text : '淘宝网店笔记本电脑平均收入统计'
						},
						tooltip : {
							trigger : 'axis',
							axisPointer : {
								type : 'shadow'
							}
						},
						legend : {
							data : [ '收入' ]
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
							data : companyArr
						},
						series : [ {
							name : '收入',
							type : 'bar',
							data : incomeArr
						}]
					};

					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartIncome$shopAvg'));

					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				},
				init : function() {
					this.init$price$product();
					this.init$price$company();
					this.init$income$company();
					this.init$income$shopAvg();
				}
			}

			impl.init();
		});