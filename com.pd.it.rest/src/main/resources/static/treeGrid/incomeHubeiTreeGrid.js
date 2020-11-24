var treeGridCols = [[{
			title : '名称',
			field : 'name',
			width : 180,
			styler : function(value, vo, index) {
				return 'background-color:lightyellow;color:blue;'
			}
		}, {
			field : 'personCount',
			title : '人口',
			width : 120,
			align : 'right'
		}, {
			field : 'gdp',
			title : 'GDP',
			width : 120,
			align : 'right'
		}, {
			field : 'avgGdp',
			title : '人均GDP',
			width : 120,
			align : 'right'
		}, {
			field : 'monthIncome',
			title : '人均月GDP',
			width : 120,
			align : 'right',
			formatter : function(value, vo) {
				return Number(vo.avgGdp/12).toFixed(1);
			}
		}]];