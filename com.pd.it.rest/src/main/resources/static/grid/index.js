// 通过加载指定js来动态实现相关配置(数据源，查询过滤条件，展示列名，渲染方法)功能.
// js名称为URL中参数id

require.config({
			urlArgs : "r=" + (new Date()).getTime(),
			paths : {
				jquery : "../jquery.min",
				easyui : "../jquery.easyui.min",
				easyuiCn : "../easyui-lang-zh_CN",
				ai : "../ai",
				common : "../common"
			},
			shim : {
				"easyui" : {
					deps : ["jquery"]
				}
			}
		});
var _common = null;
require(['jquery', 'easyui', 'ai', 'common'], function(jquery, easyui, ai,
				common) {
			_common = common;
			var editIndex = undefined;
			var urlVO = common.parseUrl(location.href);
			var id = urlVO.id;
			eval(common.html(id + '.js'));
		});