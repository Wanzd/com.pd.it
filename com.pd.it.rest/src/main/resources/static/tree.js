define([ "tree" ], function() {
	var rs = {
		root : function(treeId) {
			console("tree.root:" + treeId);
			var treeObj = $("#" + treeId);
			return treeObj.tree("getRoot");
		},
		find : function(vo) {
			var treeObj = $("#" + vo.treeId);
			return treeObj.tree("find", vo.itemId);
		},
		removeSub : function(vo) {
			var treeObj = $("#" + vo.treeId);
			var selectIt = this.selected(vo.treeId);
			var subIts = selectIt.children;
			$.each(subIts, function(eachIdx, eachIt) {
				var eachIt = treeObj.tree("find", eachIt.id);
				treeObj.tree("remove", eachIt.target);
			});
		},
		selected : function(treeId) {
			var treeObj = $("#" + treeId);
			return treeObj.tree("getSelected");
		},
		sub : function(treeId, parent) {
			var treeObj = $("#" + treeId);
			return treeObj.tree("getNode", parent);
		},
		append : function(vo) {
			var treeObj = $("#" + vo.treeId);
			treeObj.tree("append", {
				parent : vo.parent,
				data : vo.data,
				onClick : vo.onClickF
			});
		},
		refresh : function(vo) {
			var selectId = this.selected(vo.treeId).id;
			var treeObj = $("#" + vo.treeId);
			var allItem = treeObj.tree("getChildren");
			this.removeSub({
				treeId : vo.treeId,
				itemId : selectId
			});
			treeObj.tree("append", {
				parent : vo.parent,
				data : vo.data,
				onClick : vo.onClickF
			});
		}
	};
	return rs;
})
