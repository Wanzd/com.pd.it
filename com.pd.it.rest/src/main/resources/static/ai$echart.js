define(['ai$echart'], function() {
			var impl = {
				x$TreeMapVO : function(groupVO) {
					var rsVO = [];
					for (var eachAttrName in groupVO) {
						var eachAttr = groupVO[eachAttrName];
						var curVO = new Object();;
						curVO.name = eachAttrName;
						var valueSum = 0;
						curVO.children = [];
						for (var i = 0, total = eachAttr.length; i < total; i++) {
							var tmpVO = eachAttr[i];
							valueSum += tmpVO.value;
							curVO.children.push(tmpVO);
						}
						curVO.value = valueSum;
						rsVO.push(curVO);
					}
					return rsVO;
				},
				x$sunBurstVO : function(groupVO) {
					var rsVO = [];
					for (var eachAttrName in groupVO) {
						var eachAttr = groupVO[eachAttrName];
						var curVO = new Object();;
						curVO.name = eachAttrName;
						var valueSum = 0;
						curVO.children = [];
						for (var i = 0, total = eachAttr.length; i < total; i++) {
							var tmpVO = eachAttr[i];
							valueSum += tmpVO.value;
							tmpVO.name = tmpVO.detail;
							var colorMap = {
								1 : "yellow",
								2 : "orange",
								3 : "red"
							};
							tmpVO.itemStyle = {
								color : colorMap[tmpVO.value]
							};
							curVO.children.push(tmpVO);
						}
						curVO.value = valueSum;
						rsVO.push(curVO);
					}
					return rsVO;
				},
				x$option$scatterSimple : function(data) {
					var symbolSize = 2.5;
					var xCol = data["xCol"];
					var yCol = data["yCol"];
					var tooltip = null;
					eval(data["tooltip"]);
					var list = [];
					$.each(data.list, function(idx, it) {
								list.push([it[xCol], it[yCol], it]);
							})
					var option = {
						xAxis : {},
						yAxis : {},
						tooltip : {
							confine : true,
							formatter : function(list, vo) {
								title = list.value[2]['location'] + "<p/>"
										+ list.value[2]['company'] + "<p/>"
										+ list.value[2]['jobName'] + "<p/>"
										+ list.value[2]['salary'] + "<p/>";
								return title;
							}
						},
						onclick : function(e) {
							window.open(e.data[2]['url']);
						},
						series : [{
									symbolSize : 10,
									data : list,
									type : 'scatter'
								}]
					};
					return option;
				},
				x$option$scatter3D : function(data) {
					var symbolSize = 2.5;
					var option = {
						grid3D : {},
						xAxis3D : {
							type : 'category'
						},
						yAxis3D : {},
						zAxis3D : {},
						dataset : {
							dimensions : ['qty', 'city', {
										name : 'creationDate',
										type : 'ordinal'
									}],
							source : data
						},
						series : [{
									type : 'scatter3D',
									symbolSize : symbolSize,
									encode : {
										x : 'city',
										y : 'creationDate',
										z : 'qty',
										tooltip : [0, 1, 2]
									}
								}]
					};
					return option;
				},
				x$option$line : function(data) {
					var list = data.list;
					xDatas = [];
					yDatas = [];
					$.each(list, function(idx, it) {
								xDatas.push(list[idx][data.xCol]);
								yDatas.push(list[idx][data.yCol]);
							})
					var option = {
						title : {
							text : data.title
						},
						xAxis : {
							type : 'category',
							data : xDatas
						},
						yAxis : {
							type : 'value'
						},
						series : [{
									data : yDatas,
									type : 'line'
								}]
					};
					return option;
				},
				x$option$treeMap : function(data) {
					var list = data.list;
					var datas = [];
					$.each(list, function(idx, it) {
								datas.push({
											name : it.name,
											value : it.qty
										});
							})
					var option = {
						title : {
							text : data.title
						},
						tooltip : {
							confine : true
						},
						series : [{
									data : datas,
									type : 'treemap'
								}]
					};
					return option;
				},
				x$option$verticalTree : function(data) {
					var list = data.list;
					var datas = {
						name : "root",
						children : [data.list[1]]
					};
					var option = {
						title : {
							text : datas.name
						},
						tooltip : {
							trigger : 'axis',
							confine : true
						},
						series : [{
									type : 'tree',
									data : [datas],
									top : '1%',
									left : '15%',
									bottom : '1%',
									right : '7%',
									orient : 'RL',
									label : {
										position : 'left',
										verticalAlign : 'middle',
										align : 'right'
									},

									leaves : {
										label : {
											position : 'left',
											verticalAlign : 'middle',
											align : 'right'
										}
									},

									expandAndCollapse : true,

									animationDuration : 550,
									animationDurationUpdate : 750

								}]
					};
					return option;
				},
				x$option$bMap : function(data) {
					var cityPointMap = {
						'海门' : [121.15, 31.89],
						'鄂尔多斯' : [109.781327, 39.608266],
						'招远' : [120.38, 37.35],
						'舟山' : [122.207216, 29.985295],
						'齐齐哈尔' : [123.97, 47.33],
						'盐城' : [120.13, 33.38],
						'赤峰' : [118.87, 42.28],
						'青岛' : [120.33, 36.07],
						'乳山' : [121.52, 36.89],
						'金昌' : [102.188043, 38.520089],
						'泉州' : [118.58, 24.93],
						'莱西' : [120.53, 36.86],
						'日照' : [119.46, 35.42],
						'胶南' : [119.97, 35.88],
						'南通' : [121.05, 32.08],
						'拉萨' : [91.11, 29.97],
						'云浮' : [112.02, 22.93],
						'梅州' : [116.1, 24.55],
						'文登' : [122.05, 37.2],
						'上海' : [121.48, 31.22],
						'攀枝花' : [101.718637, 26.582347],
						'威海' : [122.1, 37.5],
						'承德' : [117.93, 40.97],
						'厦门' : [118.1, 24.46],
						'汕尾' : [115.375279, 22.786211],
						'潮州' : [116.63, 23.68],
						'丹东' : [124.37, 40.13],
						'太仓' : [121.1, 31.45],
						'曲靖' : [103.79, 25.51],
						'烟台' : [121.39, 37.52],
						'福州' : [119.3, 26.08],
						'瓦房店' : [121.979603, 39.627114],
						'即墨' : [120.45, 36.38],
						'抚顺' : [123.97, 41.97],
						'玉溪' : [102.52, 24.35],
						'张家口' : [114.87, 40.82],
						'阳泉' : [113.57, 37.85],
						'莱州' : [119.942327, 37.177017],
						'湖州' : [120.1, 30.86],
						'汕头' : [116.69, 23.39],
						'昆山' : [120.95, 31.39],
						'宁波' : [121.56, 29.86],
						'湛江' : [110.359377, 21.270708],
						'揭阳' : [116.35, 23.55],
						'荣成' : [122.41, 37.16],
						'连云港' : [119.16, 34.59],
						'葫芦岛' : [120.836932, 40.711052],
						'常熟' : [120.74, 31.64],
						'东莞' : [113.75, 23.04],
						'河源' : [114.68, 23.73],
						'淮安' : [119.15, 33.5],
						'泰州' : [119.9, 32.49],
						'南宁' : [108.33, 22.84],
						'营口' : [122.18, 40.65],
						'惠州' : [114.4, 23.09],
						'江阴' : [120.26, 31.91],
						'蓬莱' : [120.75, 37.8],
						'韶关' : [113.62, 24.84],
						'嘉峪关' : [98.289152, 39.77313],
						'广州' : [113.23, 23.16],
						'延安' : [109.47, 36.6],
						'太原' : [112.53, 37.87],
						'清远' : [113.01, 23.7],
						'中山' : [113.38, 22.52],
						'昆明' : [102.73, 25.04],
						'寿光' : [118.73, 36.86],
						'盘锦' : [122.070714, 41.119997],
						'长治' : [113.08, 36.18],
						'深圳' : [114.07, 22.62],
						'珠海' : [113.52, 22.3],
						'宿迁' : [118.3, 33.96],
						'咸阳' : [108.72, 34.36],
						'铜川' : [109.11, 35.09],
						'平度' : [119.97, 36.77],
						'佛山' : [113.11, 23.05],
						'海口' : [110.35, 20.02],
						'江门' : [113.06, 22.61],
						'章丘' : [117.53, 36.72],
						'肇庆' : [112.44, 23.05],
						'大连' : [121.62, 38.92],
						'临汾' : [111.5, 36.08],
						'吴江' : [120.63, 31.16],
						'石嘴山' : [106.39, 39.04],
						'沈阳' : [123.38, 41.8],
						'苏州' : [120.62, 31.32],
						'茂名' : [110.88, 21.68],
						'嘉兴' : [120.76, 30.77],
						'长春' : [125.35, 43.88],
						'胶州' : [120.03336, 36.264622],
						'银川' : [106.27, 38.47],
						'张家港' : [120.555821, 31.875428],
						'三门峡' : [111.19, 34.76],
						'锦州' : [121.15, 41.13],
						'南昌' : [115.89, 28.68],
						'柳州' : [109.4, 24.33],
						'三亚' : [109.511909, 18.252847],
						'自贡' : [104.778442, 29.33903],
						'吉林' : [126.57, 43.87],
						'阳江' : [111.95, 21.85],
						'泸州' : [105.39, 28.91],
						'西宁' : [101.74, 36.56],
						'宜宾' : [104.56, 29.77],
						'呼和浩特' : [111.65, 40.82],
						'成都' : [104.06, 30.67],
						'大同' : [113.3, 40.12],
						'镇江' : [119.44, 32.2],
						'桂林' : [110.28, 25.29],
						'张家界' : [110.479191, 29.117096],
						'宜兴' : [119.82, 31.36],
						'北海' : [109.12, 21.49],
						'西安' : [108.95, 34.27],
						'金坛' : [119.56, 31.74],
						'东营' : [118.49, 37.46],
						'牡丹江' : [129.58, 44.6],
						'遵义' : [106.9, 27.7],
						'绍兴' : [120.58, 30.01],
						'扬州' : [119.42, 32.39],
						'常州' : [119.95, 31.79],
						'潍坊' : [119.1, 36.62],
						'重庆' : [106.54, 29.59],
						'台州' : [121.420757, 28.656386],
						'南京' : [118.78, 32.04],
						'滨州' : [118.03, 37.36],
						'贵阳' : [106.71, 26.57],
						'无锡' : [120.29, 31.59],
						'本溪' : [123.73, 41.3],
						'克拉玛依' : [84.77, 45.59],
						'渭南' : [109.5, 34.52],
						'马鞍山' : [118.48, 31.56],
						'宝鸡' : [107.15, 34.38],
						'焦作' : [113.21, 35.24],
						'句容' : [119.16, 31.95],
						'北京' : [116.46, 39.92],
						'徐州' : [117.2, 34.26],
						'衡水' : [115.72, 37.72],
						'包头' : [110, 40.58],
						'绵阳' : [104.73, 31.48],
						'乌鲁木齐' : [87.68, 43.77],
						'枣庄' : [117.57, 34.86],
						'杭州' : [120.19, 30.26],
						'淄博' : [118.05, 36.78],
						'鞍山' : [122.85, 41.12],
						'溧阳' : [119.48, 31.43],
						'库尔勒' : [86.06, 41.68],
						'安阳' : [114.35, 36.1],
						'开封' : [114.35, 34.79],
						'济南' : [117, 36.65],
						'德阳' : [104.37, 31.13],
						'温州' : [120.65, 28.01],
						'九江' : [115.97, 29.71],
						'邯郸' : [114.47, 36.6],
						'临安' : [119.72, 30.23],
						'兰州' : [103.73, 36.03],
						'沧州' : [116.83, 38.33],
						'临沂' : [118.35, 35.05],
						'南充' : [106.110698, 30.837793],
						'天津' : [117.2, 39.13],
						'富阳' : [119.95, 30.07],
						'泰安' : [117.13, 36.18],
						'诸暨' : [120.23, 29.71],
						'郑州' : [113.65, 34.76],
						'哈尔滨' : [126.63, 45.75],
						'聊城' : [115.97, 36.45],
						'芜湖' : [118.38, 31.33],
						'唐山' : [118.02, 39.63],
						'平顶山' : [113.29, 33.75],
						'邢台' : [114.48, 37.05],
						'德州' : [116.29, 37.45],
						'济宁' : [116.59, 35.38],
						'荆州' : [112.239741, 30.335165],
						'宜昌' : [111.3, 30.7],
						'义乌' : [120.06, 29.32],
						'丽水' : [119.92, 28.45],
						'洛阳' : [112.44, 34.7],
						'秦皇岛' : [119.57, 39.95],
						'株洲' : [113.16, 27.83],
						'石家庄' : [114.48, 38.03],
						'莱芜' : [117.67, 36.19],
						'常德' : [111.69, 29.05],
						'保定' : [115.48, 38.85],
						'湘潭' : [112.91, 27.87],
						'金华' : [119.64, 29.12],
						'岳阳' : [113.09, 29.37],
						'长沙' : [113, 28.21],
						'衢州' : [118.88, 28.97],
						'廊坊' : [116.7, 39.53],
						'菏泽' : [115.480656, 35.23375],
						'合肥' : [117.27, 31.86],
						'武汉' : [114.31, 30.52],
						'大庆' : [125.03, 46.58]
					};

					var datas = [];
					debugger;
					$.each(data.list, function(idx, it) {
								var point = cityPointMap[it[data.xCol]];
								if (point) {
									datas.push({
												name : it[data.xCol],
												value : cityPointMap[it[data.xCol]]
														.concat(it[data.yCol])
											})
								}
							})
					debugger;
					var option = {
						title : {
							text : data.title
						},
						tooltip : {
							trigger : 'item',
							confine : true
						},
						bmap : {
							center : [104.114129, 37.550339],
							zoom : 5,
							roam : true,
							mapStyle : {
								styleJson : [{
											'featureType' : 'water',
											'elementType' : 'all',
											'stylers' : {
												'color' : '#d1d1d1'
											}
										}, {
											'featureType' : 'land',
											'elementType' : 'all',
											'stylers' : {
												'color' : '#f3f3f3'
											}
										}, {
											'featureType' : 'railway',
											'elementType' : 'all',
											'stylers' : {
												'visibility' : 'off'
											}
										}, {
											'featureType' : 'highway',
											'elementType' : 'all',
											'stylers' : {
												'color' : '#fdfdfd'
											}
										}, {
											'featureType' : 'highway',
											'elementType' : 'labels',
											'stylers' : {
												'visibility' : 'off'
											}
										}, {
											'featureType' : 'arterial',
											'elementType' : 'geometry',
											'stylers' : {
												'color' : '#fefefe'
											}
										}, {
											'featureType' : 'arterial',
											'elementType' : 'geometry.fill',
											'stylers' : {
												'color' : '#fefefe'
											}
										}, {
											'featureType' : 'poi',
											'elementType' : 'all',
											'stylers' : {
												'visibility' : 'off'
											}
										}, {
											'featureType' : 'green',
											'elementType' : 'all',
											'stylers' : {
												'visibility' : 'off'
											}
										}, {
											'featureType' : 'subway',
											'elementType' : 'all',
											'stylers' : {
												'visibility' : 'off'
											}
										}, {
											'featureType' : 'manmade',
											'elementType' : 'all',
											'stylers' : {
												'color' : '#d1d1d1'
											}
										}, {
											'featureType' : 'local',
											'elementType' : 'all',
											'stylers' : {
												'color' : '#d1d1d1'
											}
										}, {
											'featureType' : 'arterial',
											'elementType' : 'labels',
											'stylers' : {
												'visibility' : 'off'
											}
										}, {
											'featureType' : 'boundary',
											'elementType' : 'all',
											'stylers' : {
												'color' : '#fefefe'
											}
										}, {
											'featureType' : 'building',
											'elementType' : 'all',
											'stylers' : {
												'color' : '#d1d1d1'
											}
										}, {
											'featureType' : 'label',
											'elementType' : 'labels.text.fill',
											'stylers' : {
												'color' : '#999999'
											}
										}]
							}
						},
						series : [{
									name : 'pm2.5',
									type : 'scatter',
									coordinateSystem : 'bmap',
									data : datas,
									symbolSize : function(val) {
										return 1;
									},
									label : {
										formatter : '{b}',
										position : 'right',
										show : false
									},
									itemStyle : {
										color : 'purple'
									},
									emphasis : {
										label : {
											show : true
										}
									}
								}]
					};
					return option;
				},
				x$option$bMap2 : function(p1) {
					var data = [[-83, 76.5, 1.1]];
					data = data.filter(function(dataItem) {
								return dataItem[2] > 0;
							}).map(function(dataItem) {
						return [dataItem[0], dataItem[1],
								Math.sqrt(dataItem[2])];
					});
					var option = {
						backgroundColor : '#cdcfd5',
						geo3D : {
							map : 'world',
							shading : 'lambert',
							light : {
								main : {
									intensity : 5,
									shadow : true,
									shadowQuality : 'high',
									alpha : 30
								},
								ambient : {
									intensity : 0
								},
								ambientCubemap : {
									texture : 'data-gl/asset/canyon.hdr',
									exposure : 1,
									diffuseIntensity : 0.5
								}
							},
							viewControl : {
								distance : 50,
								panMouseButton : 'left',
								rotateMouseButton : 'right'
							},
							groundPlane : {
								show : true,
								color : '#999'
							},
							postEffect : {
								enable : true,
								bloom : {
									enable : false
								},
								SSAO : {
									radius : 1,
									intensity : 1,
									enable : true
								},
								depthOfField : {
									enable : false,
									focalRange : 10,
									blurRadius : 10,
									fstop : 1
								}
							},
							temporalSuperSampling : {
								enable : true
							},
							itemStyle : {},

							regionHeight : 2
						},
						visualMap : {
							max : 40,
							calculable : true,
							realtime : false,
							inRange : {
								color : ['#313695', '#4575b4', '#74add1',
										'#abd9e9', '#e0f3f8', '#ffffbf',
										'#fee090', '#fdae61', '#f46d43',
										'#d73027', '#a50026']
							},
							outOfRange : {
								colorAlpha : 0
							}
						},
						series : [{
									type : 'bar3D',
									coordinateSystem : 'geo3D',
									shading : 'lambert',
									data : data,
									barSize : 0.1,
									minHeight : 0.2,
									silent : true,
									itemStyle : {
										color : 'orange'
										// opacity: 0.8
									}
								}]
					}
					return option;
				},
				x$option$stackLine : function(data) {
					var list = data.list;
					var tmpMap = new Object();
					var xCol = data.xCol;
					var yCol = data.yCol;
					var zCol = data.zCol;
					$.each(list, function(i, it) {
								var lv2Map = tmpMap[it[zCol]];
								if (lv2Map == null) {
									lv2Map = new Object();
									lv2Map[zCol] = it[zCol];
									tmpMap[it[zCol]] = lv2Map;
								}
								var lv2List = lv2Map.list;
								if (lv2List == null) {
									lv2List = [];
									lv2Map['list'] = lv2List;
								}
								var maxX = lv2Map['maxX'];
								var curX = it[xCol];
								if (!maxX || curX > maxX) {
									lv2Map['maxX'] = curX;
									lv2Map['lastX'] = it[yCol];
								}
								lv2List.push(it);
							});
					var list2 = [];
					for (var key in tmpMap) {
						list2.push(tmpMap[key]);
					}
					list2 = list2.sort(function(v1, v2) {
								return v2.lastX - v1.lastX;
							});
					var zDatas = [];
					var xDatas = [];
					var yDatas = [];
					$.each(list2, function(i, it) {
								if (i == 0) {
									$.each(it.list, function(i2, it2) {
												xDatas.push(it2[data.xCol]);
											})
								}
								zDatas.push(it[data.zCol]);

								tmpData = [];
								$.each(it.list, function(i2, it2) {
											tmpData.push(it2[data.yCol]);
										})
								yData = {
									name : it[data.zCol],
									type : "line",
									data : tmpData
								};
								yDatas.push(yData);
							});
					var option = {
						title : {
							text : data.title
						},
						tooltip : {
							trigger : 'axis',
							confine : true
						},
						legend : {
							data : zDatas
						},
						grid : {
							left : '3%',
							right : '4%',
							bottom : '3%',
							containLabel : true
						},
						toolbox : {
							feature : {
								saveAsImage : {}
							}
						},
						xAxis : {
							type : 'category',
							boundaryGap : false,
							data : xDatas
						},
						yAxis : {
							type : 'value'
						},
						series : yDatas
					};
					return option;
				},
				x$option$timeLine : function(data) {
					var list = data.list;
					var itemStyle = {
						opacity : 0.8,
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowOffsetY : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					};
					var category = [];
					$.each(list[0], function(i, it) {
								category.push(it.name);
							});
					var series = [];
					$.each(list, function(i, it) {
								var serie = [];
								$.each(it.list, function(iL2, itL2) {
											var serieL2 = [];
											serieL2.push(itL2[data.xCol]);
											serieL2.push(itL2[data.yCol]);
											serieL2.push(1);
											serieL2.push(itL2[data.zCol]);
											serieL2.push(itL2['date']);
											serie.push(serieL2);
										});
								series.push(serie);
							});
					var sizeFunction = function(x) {
						var y = Math.sqrt(x / 5e8) + 0.1;
						return 5;
					};
					// Schema:
					var schema = [{
								name : data.xCol,
								index : 0,
								text : data.zTitle,
								unit : ''
							}, {
								name : data.yCol,
								index : 1,
								text : data.yTitle,
								unit : ''
							}, {
								name : data.zCol,
								index : 2,
								text : data.zTitle,
								unit : ''
							}];

					option = {
						baseOption : {
							timeline : {
								axisType : 'category',
								orient : 'vertical',
								autoPlay : true,
								inverse : true,
								playInterval : 1000,
								left : null,
								right : 0,
								top : 20,
								bottom : 20,
								width : 55,
								height : null,
								label : {
									color : '#999'
								},
								symbol : 'none',
								lineStyle : {
									color : '#555'
								},
								checkpointStyle : {
									color : '#bbb',
									borderColor : '#777',
									borderWidth : 2
								},
								controlStyle : {
									showNextBtn : false,
									showPrevBtn : false,
									color : '#666',
									borderColor : '#666'
								},
								emphasis : {
									label : {
										color : '#fff'
									},
									controlStyle : {
										color : '#aaa',
										borderColor : '#aaa'
									}
								},
								data : []
							},
							backgroundColor : '#404a59',
							title : [{
										text : data.title,
										textAlign : 'center',
										left : '63%',
										top : '55%',
										textStyle : {
											fontSize : 100,
											color : 'rgba(255, 255, 255, 0.7)'
										}
									}, {
										text : data.title,
										left : 'center',
										top : 10,
										textStyle : {
											color : '#aaa',
											fontWeight : 'normal',
											fontSize : 20
										}
									}],
							tooltip : {
								padding : 5,
								backgroundColor : '#222',
								borderColor : '#777',
								borderWidth : 1,
								formatter : function(obj) {
									var value = obj.value;
									return schema[0].text + '：'
											+ value[data.xCol] + schema[1].unit
											+ '<br>' + schema[1].text + '：'
											+ value[data.yCol] + schema[1].unit
											+ '<br>' + schema[2].text + '：'
											+ value[data.zCol] + schema[2].unit;
								}
							},
							grid : {
								top : 100,
								containLabel : true,
								left : 30,
								right : '110'
							},
							xAxis : {
								type : 'log',
								name : data.xTitle,
								nameGap : 25,
								nameLocation : 'middle',
								nameTextStyle : {
									fontSize : 18
								},
								splitLine : {
									show : false
								},
								axisLine : {
									lineStyle : {
										color : '#ccc'
									}
								},
								axisLabel : {
									formatter : '{value}'
								}
							},
							yAxis : {
								type : 'value',
								name : data.yTitle,
								nameTextStyle : {
									color : '#ccc',
									fontSize : 18
								},
								axisLine : {
									lineStyle : {
										color : '#ccc'
									}
								},
								splitLine : {
									show : false
								},
								axisLabel : {
									formatter : '{value}'
								}
							},
							visualMap : [{
								show : false,
								dimension : 3,
								categories : category,
								calculable : true,
								precision : 0.1,
								textGap : 30,
								textStyle : {
									color : '#ccc'
								},
								inRange : {
									color : (function() {
										var colors = ['#bcd3bb', '#e88f70',
												'#edc1a5', '#9dc5c8',
												'#e1e8c8', '#7b7c68',
												'#e5b5b5', '#f0b489',
												'#928ea8', '#bda29a'];
										return colors.concat(colors);
									})()
								}
							}],
							series : [{
										type : 'scatter',
										itemStyle : itemStyle,
										data : series,
										symbolSize : function(val) {
											return sizeFunction(val[data.xCol]);
										}
									}],
							animationDurationUpdate : 1000,
							animationEasingUpdate : 'quinticInOut'
						},
						options : []
					};

					for (var n = 0; n < data.list.length; n++) {
						option.baseOption.timeline.data.push(data.list[n]);
						option.options.push({
									title : {
										show : true,
										'text' : data.list[n].date
									},
									series : {
										name : data.list[n].date,
										type : 'scatter',
										itemStyle : itemStyle,
										data : data.list[n],
										symbolSize : function(val) {
											return sizeFunction(val[data.xCol]);
										}
									}
								});
					}
					return option;
				}
			};
			impl.x$option = function(data) {
				var option = null;
				fMap = {
					"line" : impl.x$option$line,
					"stackLine" : impl.x$option$stackLine,
					"scatterSimple" : impl.x$option$scatterSimple,
					"scatter3D" : impl.x$option$scatter3D,
					"treeMap" : impl.x$option$treeMap,
					"bMap" : impl.x$option$bMap2,
					"timeLine" : impl.x$option$timeLine,
					"verticalTree" : impl.x$option$verticalTree
				}
				f = fMap[data.chartType]
				return f != null ? f(data) : null;
			}
			return impl;
		});
