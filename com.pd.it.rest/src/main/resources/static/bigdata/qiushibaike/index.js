require.config({
	urlArgs : "r=" + (new Date()).getTime(),
	paths : {
		jquery : "/jquery.min",
		easyui : "/jquery.easyui.min",
		echarts : "/echarts.min",
		wordcloud : "/echarts-wordcloud.min"
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
				init$all : function() {
					var data = common.ajax("rest/bigData/queryList", {
						"typeId" : "joke"
					});
					debugger;
					var salaryData = common.attrArray(data,
							"thumbs,comments,contents,id");
					option = {
						title : {
							text : '糗事百科数据'
						},
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
								var curVO = params.data;
								var content = curVO[2];
								content.replaceAll("<br/>", "");
								content = common.split$line(content, 40);
								return content;
							}
						},
						series : [ {
							type : 'scatter',
							data : salaryData,
						} ]
					};

					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartAll'));

					// 使用制定的配置项和数据显示图表
					myChart.on('click', function(params) {
						window.open("https://www.qiushibaike.com/article/"
								+ params['data'][3]);
					});
					myChart.setOption(option);
				},
				init$thumbsHeatCloud : function() {
					var jsonData = common.ajax("rest/ra_joke$thumbsHeat$cloud");
					debugger;
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
					debugger;
					// 初始化echarts实例
					var myChart = echarts.init(document
							.getElementById('chartThumbsHeat'));

					// 使用制定的配置项和数据显示图表
					myChart.setOption(option);
				},

				init : function() {
					this.init$all();
					this.init$thumbsHeatCloud();
				}
			}

			impl.init();
		});