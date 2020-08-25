/*
 * totalCnt : 전체 레코드수
 * perPageNum : 페이지당 보여줄 데이터 수
 * displayPageNum : 페이지 그룹 범위
 * page : 현재페이지
 * */

function makePaging(totalCnt, perPageNum, displayPageNum, currentPage, prev, next, elements){
	   totalCnt = parseInt(totalCnt);// 전체레코드수
	   perPageNum = parseInt(perPageNum);       // 페이지당 보여줄 데이타수
	   displayPageNum = parseInt(displayPageNum);     // 페이지 그룹 범위       1 2 3 5 6 7 8 9 10
	   currentPage = parseInt(currentPage);        // 현재페이지
	  
	   var  html = new Array();
	   
	   $(elements).empty();
	   if(totalCnt == 0){
				  return "";
	   }
	  
	   // 페이지 카운트
	   var pageCnt = totalCnt % perPageNum;         
	   if(pageCnt == 0){
				  pageCnt = parseInt(totalCnt / perPageNum);
	   }else{
				  pageCnt = parseInt(totalCnt / perPageNum) + 1;
	   }
	  
	   var pRCnt = parseInt(currentPage / displayPageNum);
	   if(currentPage % displayPageNum == 0){
				  pRCnt = parseInt(currentPage / displayPageNum) - 1;
	   }
		  
		   //이전 화살표
	   if(prev == true){
				  var s2;
				  if(currentPage % displayPageNum == 0){
							  s2 = currentPage - displayPageNum;
				  }else{
							  s2 = currentPage - page % displayPageNum;
				  }
				  
				  var li = makeLi();
				  
				  $(li).append('<a class="page-link" aria-label="Previous Over"><span aria-hidden="true">«</span><span class="sr-only">Previous Over</span></a>');
				  
				  $(elements).append(li);
				
	   }else{	/*
				  html.push('<a href="#">\n');
				  html.push('◀');
				  html.push('</a>');
				*/
	   }
		  
		   //paging Bar
	   for(var index=pRCnt * displayPageNum + 1;index<(pRCnt + 1)*displayPageNum + 1;index++){
		   
		  var li = makeLi();
	   
		  if(index == currentPage){
			  $(li).append('<li class="page-item active"><a class="page-link">' + index + '</a></li>');	
		  }else{
			  $(li).append('<li class="page-item"><a class="page-link">' + index + '</a></li>');
		  }
		  
		  $(elements).append(li);
		  
		  if(index == pageCnt){
			  break;
		  }
	   }
			
	   //다음 화살표
	   if(next == true){
		   var li = makeLi();
			  
			  $(li).append('<li class="page-item"><a class="page-link" aria-label="Next Over"><span aria-hidden="true">»</span><span class="sr-only">Next Over</span></a></li>');
			  
			  $(elements).append(li);
	   }else{
		   /*
				  html.push('<a href="#">');
				  html.push('▶');
				  html.push('</a>');
		    */
	   }
	   var strHtml = html.join("");
		   
	   //$("#pagingView").empty().html(strHtml);
	}//end Paging

	function makeLi(){
		var li =$("<li></li>");
		$(li).attr("class", 'page-item');
		
		return li;
	}


