require.config({
			urlArgs : "r=" + (new Date()).getTime(),
			paths : {
				jquery : "../jquery.min",
				easyui : "../jquery.easyui.min",
				common : "../common"
			},
			shim : {
				"easyui" : {
					deps : ["jquery"]
				}
			}
		});
var $api = null;
require(['jquery', 'easyui', 'common'], function(jquery, easyui, common) {
			function x(pid, item) {
				var newData = [];
				var idx = 0;
				for (var attr in item) {
					curTarget = item[attr];
					curId = pid + '_' + idx;
					switch (typeof curTarget) {
						case 'object' :
							cn = attr;
							break;
						case 'string' :
							cn = attr + ':"' + curTarget + '"';
							break;
						case 'number' :
							cn = attr + ':' + curTarget;
							break;
					}
					curData = {
						parentId : pid,
						id : curId,
						text : cn,
						checked : 'false',
						children : []
					}
					newData.push(curData);
					if (typeof curTarget == 'object') {
						curData.children = x(curId, curTarget);
					}
					idx++;
				}

				return newData;
			}
			$('#objectTree').tree({
						id : "id",
						parentField : "pid",
						url : '/testRest/test1',
						loadFilter : function(data) {
							debugger;
							return x(null, data[0]);
						}
					});
		});