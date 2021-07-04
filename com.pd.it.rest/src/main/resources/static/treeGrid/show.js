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
				urlVO = common.parseUrl(location.href);
				var id = urlVO.id;
				var jsonData = common.ajax('../CHART:' + id);
				eval(common.html(id + '.js'));
				$('#taskTree').treegrid({
							data : jsonData.list,
							idField : 'id',
							treeField : 'text',
							columns : treeGridCols,
							onClickRow : function() {
								console.log("onClickRow");
							}
						});
				$.each(jsonData, function(i, val) {
							$('#taskTree').treegrid('collapseAll', val.id);
						});
			}
			this.refresh();
		});