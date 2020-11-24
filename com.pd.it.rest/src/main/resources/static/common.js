define(['common'], function() {
	String.prototype.replaceAll = function(s1, s2) {
		return this.replace(new RegExp(s1, "gm"), s2);
	};
	Date.prototype.format = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, // 月份
			"d+" : this.getDate(), // 日
			"h+" : this.getHours(), // 小时
			"m+" : this.getMinutes(), // 分
			"s+" : this.getSeconds(), // 秒
			"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
			"S" : this.getMilliseconds()
			// 毫秒
		};
		if (/(y+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4
							- RegExp.$1.length));
		}
		for (var k in o) {
			if (new RegExp("(" + k + ")").test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1)
								? (o[k])
								: (("00" + o[k]).substr(("" + o[k]).length)));
			}
		}
		return fmt;
	};
	String.prototype.cap = function() {
		return this.substr(0, 1).toUpperCase() + this.substr(1)
	};
	$.fn.serializeJson = function() {
		var serializeObj = {};
		var array = this.serializeArray();
		var str = this.serialize();
		$(array).each(function() {
			if (serializeObj[this.name]) {
				if ($.isArray(serializeObj[this.name])) {
					serializeObj[this.name].push(this.value);
				} else {
					serializeObj[this.name] = [serializeObj[this.name],
							this.value];
				}
			} else {
				serializeObj[this.name] = this.value;
			}
		});
		return serializeObj;
	};

	var commonImpl = {
		html : function(url, data) {
			// console.log("html:" + url);
			// console.log(data);
			var rs = $.ajax({
						url : url,
						contentType : 'application/json',
						data : JSON.stringify(data),
						dataType : "json",
						async : false,
						type : "POST"
					});
			if (rs.responseText.length == 0) {
				return null;
			}
			return rs.responseText;
		},
		ajax : function(url, data) {
			// console.log("ajax:" + url);
			// console.log(data);
			var rs = $.ajax({
						url : url,
						contentType : 'application/json',
						data : JSON.stringify(data),
						dataType : "json",
						async : false,
						type : "POST"
					});
			if (rs.responseText.length == 0) {
				return null;
			}
			var rsJson = eval("(" + rs.responseText + ")");
			// console.log(rsJson);
			return rsJson;
		},
		parseUrl : function(url) {
			var result = {};
			var query = url.split("?")[1];
			var queryArr = query.split("&");
			queryArr.forEach(function(item) {
						var obj = {};
						var key = item.split("=")[0];
						var value = item.split("=")[1];
						result[key] = value;
					});
			return result;
		},
		addTab : function(vo) {
			if ($('#' + vo.divId).tabs('exists', vo.title)) {
				$('#' + vo.divId).tabs('select', vo.title);
			} else {
				var content = '<iframe scrolling="auto" frameborder="0"  src="'
						+ vo.url
						+ '" style="width:100%;height:100%;"></iframe>';
				$('#tt').tabs('add', {
							title : vo.title,
							content : content,
							closable : true
						});
			}
		},
		attrArray : function(data, attrName) {
			var attrs = attrName.split(",");
			if (attrs.length == 1) {
				var result = [];
				for (var i = 0, total = data.length; i < total; i++) {
					result.push(data[i][attrs[0]]);
				}
			} else {
				var result = [];
				for (var i = 0, total = data.length; i < total; i++) {
					var vo = [];
					for (var j = 0, total2 = attrs.length; j < total2; j++) {

						vo.push(data[i][attrs[j]]);
					}
					result.push(vo);
				}
			}
			return result;
		},
		groupArray : function(data, groupBy) {
			var splitGroup = new Object();
			if (data.length > 0) {
				for (var i = 0, total = data.length; i < total; i++) {
					var curRow = data[i];
					var curGroup = splitGroup[curRow[groupBy]];
					if (curGroup == null) {
						curGroup = [];
						splitGroup[curRow[groupBy]] = curGroup;
					}
					curGroup.push(curRow);
				}
			}
			return splitGroup;
		},
		split$line : function(str, lineLength) {
			var tmpArr = [];
			var length = str.length;
			var index = 0;

			while (index < length) {
				tmpArr.push(str.slice(index, (index += lineLength)));
			}
			return tmpArr.join("<br/>");
		},
		init$date$month : function(vo) {
			var curId = "#" + vo.id;
			$(function() {
				$(curId).datebox({
					onShowPanel : function() {// 显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
						span.trigger('click'); // 触发click事件弹出月份层
						if (!tds)
							setTimeout(function() {// 延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
										tds = p
												.find('div.calendar-menu-month-inner td');
										tds.click(function(e) {
											e.stopPropagation(); // 禁止冒泡执行easyui给月份绑定的事件
											var year = /\d{4}/
													.exec(span.html())[0]// 得到年份
											, month = parseInt($(this)
															.attr('abbr'), 10)
													+ 1; // 月份
											$(curId).datebox('hidePanel')
													// 隐藏日期对象
													.datebox('setValue',
															year + '-' + month); // 设置日期的值
										});
									}, 0);
					},
					parser : function(s) {// 配置parser，返回选择的日期
						if (!s)
							return new Date();
						var arr = s.split('-');
						return new Date(parseInt(arr[0], 10), parseInt(arr[1],
										10)
										- 1, 1);
					},
					formatter : function(d) {
						var month = d.getMonth();
						if (month < 10) {
							month = "0" + month;
						}
						if (d.getMonth() == 0) {
							return d.getFullYear() - 1 + '-' + 12;
						} else {
							return d.getFullYear() + '-' + month;
						}
					}// 配置formatter，只返回年月
				});
				var p = $(curId).datebox('panel'), // 日期选择对象
				tds = false, // 日期选择对象中月份
				span = p.find('span.calendar-text'); // 显示月份层的触发控件

			});
		},
		init : function(vo) {
			switch (vo.type) {
				case "grid" :
					$("#" + vo.gridId).datagrid(vo.cfg);
					break;
				case "date$month" :
					this.init$date$month(vo);
			}

		},
		log : function(msg) {
			console.debug(msg);
			$("#logDiv").html($("#logDiv").html() + "<br/>"
					+ "<font color='blue'>"
					+ new Date().format("yyyy-MM-dd hh:mm:ss") + "</font>"
					+ msg);
			var scrollDom = document.getElementById('logDiv');
			scrollDom.scrollTop = scrollDom.scrollHeight;
		}
	};
	return commonImpl;
});
