var treeGridCols = [[{
			title : '市',
			field : 'city',
			width : 180,
			styler : function(value, vo, index) {
				return 'background-color:lightyellow;color:blue;'
			}
		}, {
			field : 'weatherDate',
			title : '日期',
			width : 120
		}, {
			field : 'high',
			title : '最高气温',
			width : 120,
			align : 'right'
		}, {
			field : 'low',
			title : '最低气温',
			width : 120,
			align : 'right'
		}]];