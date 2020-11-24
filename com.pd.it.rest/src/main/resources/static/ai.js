define([ 'ai' ], function() {
	return {
		build : function(key) {
			var paths = key.split(".");
			console.debug(paths);
			
			var curMethod=eval(key);
			console.debug(curMethod);
		}
	}
});
