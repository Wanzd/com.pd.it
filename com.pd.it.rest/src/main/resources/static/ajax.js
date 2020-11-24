define(['ajax'], function() {
			return {
				post : {
					str : function(vo) {
						vo.type = "POST";
						vo.dataType = "text";
						vo.async = false;
						// return $aiAtom.post(vo);
					},
					json : function(vo) {
						vo.type = "POST";
						vo.dataType = "json";
						vo.async = false;
						// return $aiAtom.post(vo);
					}
				},
				get : {
					str : function(vo) {
						vo.type = "get";
						vo.dataType = "text";
						vo.aysnc = false;
						// return $aiAtom.ajax(vo);
					},
					json : function(vo) {
						vo.type = "get";
						vo.dataType = "json";
						vo.aysnc = false;
						// return $aiAtom.ajax(vo);
					}
				}
			}
		});
