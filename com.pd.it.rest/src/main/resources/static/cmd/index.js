require.config({
	urlArgs : "r=" + (new Date()).getTime(),
	paths : {
		jquery : "../jquery.min",
		easyui : "../jquery.easyui.min"
	},
	shim : {
		"easyui" : {
			deps : [ "jquery" ]
		}
	}
});

var $impl = null;
require([ 'jquery', 'easyui' ], function(jquery, easyui) {
	$impl = {
		init : function() {
			console.info("$impl.init");
			$impl.init$module();
		},
		init$module : function() {
			$("#cmdInput").keyup(function() {
				$impl.keyup_submit(event);
			})
		},
		keyup_submit : function(e) {
			var evt = window.event || e;
			if (evt.keyCode != 13) {
				return;
			}
			$("#cmdInput").attr("disabled", "disabled");
			var input = $("#cmdInput").val();
			if (input == "clear") {
				$("#output").html("");
				$("#cmdInput").val("");
				$("#cmdInput").removeAttr("disabled");
				$("#cmdInput").focus();
				return;
			}
			$("#output").html(input);
			$("#cmdInput").val("");
			$("#cmdInput").removeAttr("disabled");
			$("#cmdInput").focus();
		}
	}
	$impl.init();
});