require.config({
			urlArgs : "r=" + (new Date()).getTime(),
			paths : {
				jquery : "../../jquery.min",
				easyui : "../../jquery.easyui.min",
				common : "../../common"
			},
			shim : {
				"easyui" : {
					deps : ["jquery"]
				}
			}
		});
var $api = null;
require(['jquery', 'easyui', 'common'], function(jquery, easyui, common) {
	$impl = {
		result : Math.floor(Math.random() * 100),
		start : 0,
		end : 99,
		continueFlag : true,
		curPlayer:0,
		checkValue:null,
		init$filter : function() {
			// 初始化原始框架
			$impl.checkValue=[];
			for (var trIdx = 0, trTotal = 8; trIdx < trTotal; trIdx++) {
				var tmpTr = $("<tr height=30></tr>");
				for (var tdIdx = 0, tdTotal = 8; tdIdx < tdTotal; tdIdx++) {
					$impl.checkValue[trIdx*8+tdIdx]=-1;
					var tdId = trIdx * 8 + tdIdx;
					var tmpTd = $("<td id='td" + tdId + "'  width='19px' style='word-wrap:break-word;font-size:14pt' align='center' value='"+tdId+"'></td>");
					tmpTr.append(tmpTd);
				}
				$('#tClipCheck').append(tmpTr);
			}

			$('#tClipCheck').on('click', 'td', function(tar) {
				if (!$impl.continueFlag) {
					return
				}
				if($(tar.currentTarget).text()!=""){
					return
				}
				$impl.set($(tar.currentTarget).attr("value"));
			});
		},
		set:function(tdId){
			/*写当前棋子的真值*/ $impl.checkValue[tdId]=$impl.curPlayer;
			/*写当前格棋子*/ $("#td"+tdId).text($impl.curPlayer==0?"●":"৹");
			/*计算吃其它颜色棋子*/ $impl.eats(tdId);
			/*换棋手*/ $impl.curPlayer=$impl.curPlayer==0?1:0;
		},
		eats:function(tdId,pathX,pathY){
			var paths=[[-1,-1],[-1,0],[-1,1],[0,-1],[0,1],[1,-1],[1,0],[1,1]];
			for(var index=0;index<paths.length;index++) {
				//debugger;
				var path=paths[index];
				console.debug(paths[index]);
				$impl.eat(tdId,path[0],path[1]);
			}
		},
		eat:function(tdId,pathX,pathY){
			var eatIndex=0;
			var eatIds=[];
			var continueFlag=true;
			
			var curPlayer=$impl.curPlayer;
			var tmpX=parseInt(tdId/8);
			var tmpY=parseInt(tdId%8);
			while(continueFlag){
				/*下一个棋子为不存在，则退出*/
				
				var nextX=tmpX+pathX;
				var nextY=tmpY+pathY;
				if(nextX>=8||nextX<0||nextY>=8||nextY<0){
					continueFlag=false;
					break;
				}
				
				/*下一个棋子为空白，则退出循环*/
				var nextId=nextX*8+nextY;
				if($impl.checkValue[nextId]==-1){
					continueFlag=false;
					break;
				}
				
				/*下一个棋子为己色，则退出循环，并吃掉缓存敌方棋子*/
				if($impl.checkValue[nextId]==curPlayer){
					for(var i=0;i<eatIds.length;i++){
						$impl.checkValue[eatIds[i]]=curPlayer;
						$("#td"+eatIds[i]).text($impl.curPlayer==0?"●":"৹");
					}
					continueFlag=false;
					break;
					
				}
				
				/*下一个棋子为敌色，则加入缓存*/
				tmpX=nextX;
				tmpY=nextY;
				debugger;
				eatIds[eatIndex++]=nextId;
			}
			
		}
	};
	$api = $impl;
	$impl.init$filter();
});