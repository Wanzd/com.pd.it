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
require(
		[ 'jquery', 'easyui', 'common', 'tree', 'db' ],
		function(jquery, easyui, common, tree, db) {
			function initDatagrid() {
				$('#dg')
						.datagrid(
								{
									loadMsg : '数据加载中请稍后……',
									striped : true,
									url : '../db/ra?mid=user',
									method : "get",
									striped : true,
									border : true,
									selectOnCheck : false,
									checkOnSelect : false,
									remoteSort : true,
									singleSelect : true, // 只允许选中一行
									idField : 'EmployeeID',
									pagination : true,
									rownumbers : false,
									fitColumns : true,
									pageSize : 100, // 每页显示的记录条数，默认为100
									pageList : [ 20, 50, 100, 200, 500 ], // 设置可选的每页记录条数的列表
									columns : [ [ {
										field : 'ck',
										checkbox : true
									}, {
										field : 'Num',
										title : "编号",
										width : 30,
										sortable : false
									}, {
										field : 'Name',
										title : "姓名",
										width : 80,
										sortable : true,
										editor : {
											type : 'validatebox',
											options : {
												required : true,
												missingMessage : '请输入姓名'
											}
										}
									}, {
										field : 'Sex',
										title : "性别",
										width : 50,
										sortable : true,
										formatter : formatSex,
										// 编辑器
										editor : {
											type : 'combobox', // 指明控件类型
											// options里面的内容可选
											options : {
												textField : 'sex',
												valueField : 'value',
												data : [ {
													sex : '男',
													value : 0
												}, {
													sex : '女',
													value : 1
												} ],
												required : true, // 是否必填
												missingMessage : '请选择性别'
											}
										}
									} ] ],
									// 工具栏
									toolbar : [
											{
												text : "新增",
												iconCls : "icon-add",
												handler : function() {
													if (isEditing == true) {
														$.messager
																.alert(
																		'操作提示',
																		'您有正在编辑的数据尚未保存，请先保存或取消编辑！',
																		'warning');
													} else {
														// 在第一行编辑新增数据
														$('#dg')
																.datagrid(
																		'insertRow',
																		{
																			index : 0,
																			row : {
																				Name : '',
																				Sex : '',
																				EntryDate : '',
																				LeaveDate : '',
																				OMPDate : ''
																			}
																		});
														$("#dg").datagrid(
																"beginEdit", 0);
														isEditing = true;
														editRow = 0;

														isEdit = false;
													}
												}
											},
											{
												text : "编辑",
												iconCls : "icon-edit",
												handler : function() {
													var row = $('#dg')
															.datagrid(
																	'getSelected');
													var rowIndex = $('#dg')
															.datagrid(
																	'getRowIndex',
																	row);
													if (isEditing == true
															&& rowIndex != editRow) {
														$.messager
																.alert(
																		'操作提示',
																		'您有正在编辑的数据尚未保存，请先保存或取消编辑！',
																		'warning');
													} else if (rowIndex > -1) {
														$('#dg').datagrid(
																"beginEdit",
																rowIndex);
														isEditing = true;
														editRow = rowIndex;
													} else {
														$.messager.alert(
																'操作提示',
																'请先选择要编辑的行！',
																'info');
													}

													isEdit = true;
												}
											},
											{
												text : "保存",
												iconCls : "icon-save",
												handler : function() {
													$('#dg').datagrid(
															"endEdit", editRow);
												}
											},
											{
												text : "取消",
												iconCls : "icon-cancel",
												handler : function() {
													$('#dg').datagrid(
															"cancelEdit",
															editRow);
													isEditing = false;
													$('#dg').datagrid("reload");
												}
											} ],
									onBeforeLoad : function(params) {
										// 添加参数
										params.name = $("#queryName").val();
										params.year = $("#queryYear")
												.numberbox("getValue");
										params.month = $("#queryMonth")
												.numberbox("getValue");
									},
									// 完成编辑时发送请求保存数据到后台
									onAfterEdit : function(rowIndex, rowData,
											changes) {
										$(this).datagrid("beginEdit", rowIndex); // 这句如果注释掉下一行的ed就获取不到值
										var ed = $(this).datagrid('getEditor',
												{
													index : rowIndex,
													field : 'Sex'
												});
										var sex = ed.target
												.combobox('getValue');
										editRow = rowIndex;
										isEditing = false;
										Employee.save(rowData, sex);
									}
								});
			}
		})