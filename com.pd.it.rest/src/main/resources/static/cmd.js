require.config({
	urlArgs : "r=" + (new Date()).getTime(),
	paths : {
		jquery : "jquery.min",
		easyui : "jquery.easyui.min"
	},
	shim : {
		"easyui" : {
			deps : [ "jquery" ]
		}
	}
});
function keyup_submit(e) {
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
require([ 'jquery', 'easyui', 'common', 'tree', 'db' ], function(jquery,
		easyui, common, tree, db) {
	main = {
		init : function() {
			main.init$module();
		},
		init$module : function() {
			console.log("init$menu");
			$('#cbModule').combobox({
				url : 'rest/ra_cmd$module',
				valueField : 'id',
				textField : 'text'
			});
		}
	}
	main.init();
});