require.config({
	urlArgs : "r=" + (new Date()).getTime(),
	paths : {
		jquery : "../jquery.min",
		easyui : "../jquery.easyui.min",
		common : "../common"
	},
	shim : {
		"easyui" : {
			deps : [ "jquery" ]
		}
	}
});
var $api = null;
require(
		[ 'jquery', 'easyui', 'common' ],
		function(jquery, easyui, common) {
			$impl = {
				init$filter : function() {
					var id=common.parseUrl(location.href).id;
					$("#source").val(common.get('../testData/freemarker/'+id+'.json'));
					$("#template").val(common.get('../testData/freemarker/'+id+'.tpl'));
				},
				generate : function() {
					var result = common.html("../freemarkerRest/generate", {
						source : $("#source").val(),
						template : $("#template").val()
					});
					$("#result").val(result);

					var resultHighLight = common
							.html(
									"../freemarkerRest/generate",
									{
										source : $("#source").val(),
										template : $("#template")
												.val()
												.replaceAll('\\${',
														'<strong style="background-color:yellow;">${')
												.replaceAll('}',
														'}</strong>')
									});
					debugger;
					$("#highLight").html(
							resultHighLight.replaceAll('\n', '<p/>'));
					$("#dFreemarksTabs").tabs('select', 'HighLight');
				},
				copy2board:function(){
					console.log("$impl copy2board");
					var targetDiv=$("#highLight")[0];
					if (document.body.createTextRange) {
			            var range = document.body.createTextRange();
			            range.moveToElementText(targetDiv);
			            range.select();
			        } else if (window.getSelection) {
			            var selection = window.getSelection();
			            var range = document.createRange();
			            range.selectNodeContents(targetDiv);
			            selection.removeAllRanges();
			            selection.addRange(range);
			        } 
					document.execCommand("copy");
					alert("Copy to board success !");
				}
			};
			$api = $impl;
			$impl.init$filter();

		});