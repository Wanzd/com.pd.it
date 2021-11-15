require.config({
			urlArgs : "r=" + (new Date()).getTime(),
			paths : {
				jquery : "../../jquery.min",
				easyui : "../../jquery.easyui.min",
				common : "../../common"
			},
			shim : {
				"easyui" : {
					deps : ["jquery"]
				}
			}
		});
var $api = null;
require(['jquery', 'easyui', 'common'], function(jquery, easyui, common) {
	$impl = {
		result : Math.floor(Math.random() * 100),
		start : 0,
		end : 99,
		continueFlag : true,
		init$filter : function() {
			// 初始化原始框架
			for (var trIdx = 0, trTotal = 10; trIdx < trTotal; trIdx++) {
				var tmpTr = $("<tr></tr>");
				for (var tdIdx = 0, tdTotal = 10; tdIdx < tdTotal; tdIdx++) {
					var tdId = trIdx * 10 + tdIdx;
					var tmpTd = $("<td id='" + tdId + "'>" + tdId + "</td>");
					tmpTr.append(tmpTd);
				}
				$('#tNumberBoom').append(tmpTr);
			}

			// 所有方格标识为黄色
			for (var tdIdx = 0, tdTotal = 100; tdIdx < tdTotal; tdIdx++) {
				$('#' + tdIdx).css('backgroundColor', 'yellow');

			}

			$('#tNumberBoom').on('click', 'td', function(tar) {
				if (!$impl.continueFlag) {
					return
				}
				var curId = tar.currentTarget.id;
				if (curId < $impl.start && curId > $impl.end) {
					return
				}
				if (curId == $impl.result) {
					$('#' + curId).css('backgroundColor', 'red');
					$impl.continueFlag = false;
					alert("Boom !")
					return
				}
				var startIdx = curId > $impl.result ? curId : $impl.start;
				var endIdx = curId < $impl.result ? curId : $impl.end;
				for (var tdIdx = startIdx, tdTotal = endIdx; tdIdx <= tdTotal; tdIdx++) {
					$('#' + tdIdx).css('backgroundColor', 'lightgreen');
				}
			});
		}
	};
	$api = $impl;
	$impl.init$filter();
});