require.config({
	urlArgs : "r=" + (new Date()).getTime(),
	paths : {
		jquery : "../../jquery.min",
		easyui : "../../jquery.easyui.min",
		common : "../../common"
	},
	shim : {
		"easyui" : {
			deps : [ "jquery" ]
		}
	}
});
var $api = null;
require([ 'jquery', 'easyui', 'common' ], function(jquery, easyui, common) {
	$impl = {
		numbers : null,
		continueFlag : true,
		init$filter : function() {
			$impl.numbers = [ Math.floor(Math.random() * 13 + 1),
					Math.floor(Math.random() * 13 + 1),
					Math.floor(Math.random() * 13 + 1),
					Math.floor(Math.random() * 13 + 1) ];
			$("#in1").val($impl.numbers[0]);
			$("#in2").val($impl.numbers[1]);
			$("#in3").val($impl.numbers[2]);
			$("#in4").val($impl.numbers[3]);
			$("#answer").val('');
		},
		submit : function() {
			var result = $impl.valid();
			if (result == "success") {
				if (confirm("恭喜你答对了，你可真厉害，是否进行下一盘！")) {
					$impl.init$filter();
				}
			} else {
				alert(result)
			}
		},
		crack : function() {
			var result = common.html("/gameRest/crack", {
				"game" : "point24",
				"param" : $("#in1").val()+","+$("#in2").val()+","+$("#in3").val()+","+$("#in4").val()
			})
			alert(result);
		},
		valid : function() {
			/* 姝ｅ垯鏍￠獙鍏ュ弬鍐呭 */
			debugger;
			var answerStr = $("#answer").val();
			if (!/^[\d+\-\*\/\(\)]+$/.test(answerStr)) {
				return "只能输入数字和+-*/()";
			}

			/* 鏍￠獙鏁板瓧鏄惁涓庡叆鍙備竴鑷� */
			var answerArray = answerStr.replaceAll('\\(', "").replaceAll("\\)",
					"").replaceAll("\\-", "+").replaceAll("\\*", "+")
					.replaceAll("\\/", "+").split("+");
			if (answerArray.length == $impl.numbers.length) {
				for ( var eachAnswer in answerArray) {
					if (!$impl.numbers
							.includes(Number(answerArray[eachAnswer]))) {
						return "入参数字非法:" + answerArray[eachAnswer];
					}
				}
			} else {
				return "入参数量不正确";
			}
			/* 鏍￠獙璁＄畻缁撴灉鏄惁涓�24 */
			var rs = eval($("#answer").val());
			if (rs == 24) {
				return "success";
			}
			return "结果不为24";
		}
	};
	$api = $impl;
	$impl.init$filter();
});