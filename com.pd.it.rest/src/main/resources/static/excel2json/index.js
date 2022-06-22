require.config({
			urlArgs : "r=" + (new Date()).getTime(),
			paths : {
				jquery : "../jquery.min",
				easyui : "../jquery.easyui.min",
				xlsx : "../xlsx.full.min",
				common : "../common"
			},
			shim : {
				"easyui" : {
					deps : ["jquery"]
				}
			}
		});
require(['jquery', 'easyui', 'common', 'xlsx'],
		function(jquery, easyui, common) {
			$(document).ready(function() {
				$("#fileUploader").change(function(evt) {
					var selectedFile = evt.target.files[0];
					var reader = new FileReader();
					reader.onload = function(event) {
						var data = event.target.result;
						var workbook = XLSX.read(data, {
									type : 'binary'
								});
						workbook.SheetNames.forEach(function(sheetName) {
							var XL_row_object = XLSX.utils
									.sheet_to_row_object_array(workbook.Sheets[sheetName]);
							console.log(XL_row_object);
							if (XL_row_object.length > 0) {
								if ($("#dExcelTabs").tabs('exists', sheetName)) {
									$("#dExcelTabs").tabs('select', sheetName);
								} else {
									var content = '<div title="' + sheetName
											+ '" style="height: 100%;">'
											+ JSON.stringify(XL_row_object)
											+ '</div>';
									$("#dExcelTabs").tabs('add', {
												title : sheetName,
												content : content,
												closable : true
											});
								}
							}
						})
					};
					reader.onerror = function(event) {
						console.error("File could not be read! Code "
								+ event.target.error.code);
					};
					// 读取上传文件为二进制
					reader.readAsBinaryString(selectedFile);
				});
			});
		});