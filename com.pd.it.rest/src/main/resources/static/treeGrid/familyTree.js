var treeGridImpl = {
	text : {
		formater : function(value, vo, index) {
			var colorMap = {
				'男' : "blue",
				1 : "blue",
				'女' : "red",
				0 : "red"
			};
			var mateColorMap = {
				'blue' : "red",
				'red' : "blue",
				'black' : "black"
			};
			var color = colorMap[vo.sex];
			if (color == null) {
				color = "black";
			}
			var mateColor = mateColorMap[color];
			var valueStr = value;
			if (vo.mate != null) {
				var mateValueStr = vo.mate.name;
				return '<font color="' + color + '">' + vo.sortId + ':'
						+ valueStr + '</font>' + '(' + '<font color="'
						+ mateColor + '">' + mateValueStr + '</font>' + ')';
			}
			return '<font color="' + color + '">' + vo.sortId + ':' + valueStr
					+ '</font>';
		}
	},
	fulChildrenCnt : {
		formater : function(value, vo, index) {
			return value;
		}
	}
}
var treeGridCols = [[{
			title : '姓名',
			field : 'text',
			width : 300,
			rowspan : 1,
			formatter : treeGridImpl.text.formater
		}, {
			title : '年龄',
			field : 'age',
			width : 60,
			align : 'right',
			rowspan : 1
		}, {
			title : '生日',
			field : 'birthday',
			width : 80,
			align : 'left',
			rowspan : 1
		}, {
			title : '电话号码',
			field : 'tel',
			width : 90,
			align : 'left',
			rowspan : 1
		}, {
			title : '子女',
			field : 'childrenCnt',
			width : 40,
			align : 'right',
			rowspan : 1
		}, {
			title : '子孙(分布)',
			field : 'fulChildrenCnt',
			width : 100,
			align : 'right',
			rowspan : 1,
			formatter : treeGridImpl.fulChildrenCnt.formater
		}, {
			title : '叶节点',
			field : 'leafChildrenCnt',
			width : 40,
			align : 'right',
			rowspan : 1
		}, {
			title : '有效叶节点',
			field : 'validLeafChildrenCnt',
			width : 40,
			align : 'right',
			rowspan : 1
		}]];