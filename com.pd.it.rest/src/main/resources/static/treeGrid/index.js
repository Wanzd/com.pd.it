require.config({
			urlArgs : "r=" + (new Date()).getTime(),
			paths : {
				jquery : "../jquery.min",
				easyui : "../jquery.easyui.min",
				common : "../common",
				echarts : "../echarts.min",
				echartsgl : "../echarts-gl.min",
				ai$echart : "../ai$echart"
			},
			shim : {
				"easyui" : {
					deps : ["jquery"]
				}
			}
		});
var $api = null;
require(['jquery', 'easyui', 'common', 'echarts', 'echartsgl', 'ai$echart'],
		function(jquery, easyui, common, echarts, echartsgl, ai$echart) {
			$api = this;
			this.refresh = function() {
				console.log('test');
				var jsonData = common.ajax('../taskTreeRest/DATA:testTask1');
				jsonData2 = [{
							"id" : "a",
							"text" : "a",
							"children" : [{
										"id" : "a1",
										"text" : "a1",
										"size" : "120 MB",
										"date" : "03/20/2010",
										"children" : [{
													"id" : "a11",
													"text" : "a11"
												}]
									}, {
										"id" : 3,
										"name" : "eclipse",
										"size" : "",
										"date" : "01/20/2010",
										"children" : [{
													"id" : 31,
													"name" : "eclipse.exe",
													"size" : "56 KB",
													"date" : "05/19/2009"
												}]
									}]
						}];
				$('#taskTree').treegrid({
							data : jsonData,
							idField : 'id',
							treeField : 'text',
							columns : [[{
										title : 'text',
										field : 'text',
										width : 180
									}, {
										field : 'status',
										title : 'Status',
										width : 60,
										align : 'right'
									}, {
										field : 'startTime',
										title : 'Start Time',
										width : 80
									}, {
										field : 'endTime',
										title : 'End Time',
										width : 80
									}, {
										field : 'useTime',
										title : 'Use Time',
										width : 80
									}, {
										field : 'detail',
										title : 'Detail',
										width : 80
									}]]
						});
			}
			this.refresh();
			setInterval("this.refresh();", 10000);
		});