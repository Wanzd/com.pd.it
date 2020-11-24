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
require(
		[ 'jquery', 'easyui', 'common', 'echarts', 'wordcloud', 'ai$echart' ],
		function(jquery, easyui, common, echarts, wordcloud, ai$echart) {
			var impl = {
				init$company : function() {
					var data = common.ajax("rest/ra_51Job$company");
					var companyArr = common.attrArray(data, "company");
					var salaryAvgArr = common.attrArray(data, "salaryAvg");
					var salaryMaxArr = common.attrArray(data, "salaryMax");
					option = {
						title : {
							text : '公司工资统计'
						},
						tooltip : {
							trigger : 'axis',
							axisPointer : {
								type : 'shadow'
							}
						},
						legend : {
							data : [ '最大工资', '平均工资' ]
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
							name : '最大工资',
							type : 'bar',
							data : salaryMaxArr
						}, {
							name : '平均工资',
							type : 'bar',
							data : salaryAvgArr
						} ]
					};

					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartCompany'));

					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				},
				init$salary : function() {
					var data = common.ajax("rest/ra_51Job$target");
					var salaryData = common.attrArray(data,
							"salaryStart,salaryEnd,company,job,location,url");

					option = {
						xAxis : {
							scale : true,
							name : "最低工资"
						},
						yAxis : {
							scale : true,
							name : "最高工资"
						},
						tooltip : {
							trigger : 'item',
							axisPointer : {
								type : "shadow"
							},
							formatter : function(params) {
								var curVO = params.data;
								var htmlStr = "位置：" + curVO[4] + "<br/>公司："
										+ curVO[2] + "<br/>岗位：" + curVO[3];
								return htmlStr;
							},
							position : function(point, params, dom, rect, size) {
								return [ point[0], point[1] + 30 ];
							}
						},
						series : [ {
							type : 'effectScatter',
							symbolSize : 20,
							data : [ [ 172.7, 105.2 ], [ 153.4, 42 ] ]
						}, {
							type : 'scatter',
							data : salaryData,
						} ]
					};
					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartSalary'));
					myChart.on('click', function(params) {
						window.open(params['data'][5]);
					});
					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				},
				init$skill : function() {
					var data = common.ajax("rest/ra_51Job$skillHeat$max");
					var salaryData = common.attrArray(data,
							"salaryStart,salaryEnd,text");

					option = {
						xAxis : {
							scale : true,
							name : "最低工资"
						},
						yAxis : {
							scale : true,
							name : "最高工资"
						},
						tooltip : {
							trigger : 'item',
							axisPointer : {
								type : "shadow"
							},
							formatter : function(params) {
								debugger;
								var curVO = params.data;
								return curVO[2];
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
					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartSkill'));

					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				},
				init$skill$heat : function() {
					var data = common.ajax("rest/ra_51Job$skillHeat");
					var groupArr = common.attrArray(data, "category");
					var treeMapVO = ai$echart.x$TreeMapVO(groupArr);
					option = {
						tooltip : {
							formatter : function(info) {
								var vo = info.data;
								return "Name：" + vo.name + "<br/>Value："
										+ vo.value;
							}
						},
						series : [ {
							type : 'treemap',
							data : treeMapVO
						} ]
					};

					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartSkillHeat'));

					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				},
				init$skill$heat$cloud : function() {
					var jsonData = common.ajax("rest/ra_51Job$skillHeat");
					var keywords = common.attrArray(jsonData, "text,c");
					var data = [];
					for (var i = 0, total = keywords.length; i < total; i++) {
						var keyword = keywords[i];
						data.push({
							name : keyword[0],
							value : Math.sqrt(keyword[1])
						})
					}
					var maskImage = new Image();
					maskImage.src = 'images/word-cloud.png';
					var option = {
						title : {
							text : 'echarts3云图展示'
						},
						tooltip : {},
						series : [ {
							type : 'wordCloud', // 类型为字符云
							shape : 'smooth', // 平滑
							gridSize : 2, // 网格尺寸
							size : [ '80%', '80%' ],
							// sizeRange : [ 50, 100 ],
							rotationRange : [ 46, 80 ], // 旋转范围
							textStyle : {
								normal : {
									fontFamily : 'sans-serif',
									color : function() {
										return 'rgb('
												+ [
														Math
																.round(Math
																		.random() * 160),
														Math
																.round(Math
																		.random() * 160),
														Math
																.round(Math
																		.random() * 160) ]
														.join(',') + ')';
									}
								},
								emphasis : {
									shadowBlur : 5, // 阴影距离
									shadowColor : '#333' // 阴影颜色
								}
							},
							data : data
						} ]
					};
					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartSkillHeatCloud'));

					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				},
				init$skill$sort : function() {
					var data = common.ajax("rest/ra_51Job$skillHeat");
					var textArr = common.attrArray(data, "text");
					var salaryAvgArr = common.attrArray(data, "salaryAvg");
					var salaryEndArr = common.attrArray(data, "salaryEnd");
					var salaryStartArr = common.attrArray(data, "salaryStart");
					option = {
						title : {
							text : '公司工资统计'
						},
						tooltip : {
							trigger : 'axis',
							axisPointer : {
								type : 'shadow'
							}
						},
						legend : {
							data : [ '最大工资', '平均工资', '最低工资' ]
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
							name : '最大工资',
							type : 'bar',
							data : salaryEndArr
						}, {
							name : '平均工资',
							type : 'bar',
							data : salaryAvgArr
						}, {
							name : '最低工资',
							type : 'bar',
							data : salaryStartArr
						} ]
					};

					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartmain'));

					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				},
				init : function() {
					this.init$company();
					this.init$salary();
					this.init$skill();
					// this.init$skill$heat();
					this.init$skill$heat$cloud();
					this.init$skill$sort();
				}
			}

			impl.init();
		});