	jQuery(function($){ 
		
		loadApi(drawTable, null, null);
		
		$("#page").on('click', 'a', function(){
			
			if($(this).attr('class') != 'active'){
				var clickPage = $(this).text();
				var displayPageNum = $("#showContents").val();
								
				loadApi(drawTable, clickPage, displayPageNum);
			}
			
		});
	});
	
	function initializingPageData(){
		
		initSelectBox();
		bindEvent();
		
	}
	
	function initSelectBox(){
		
		var searchOption = '';
		var countOption = '';
		
		$('#showContents').empty();
		$('#searchSelectBox').empty();;
		
		searchOption += '<option>전체</option>';
		searchOption += '<option>이름별</option>';
		searchOption += '<option>날짜별</option>';
		
		countOption += '<option>전체</option>';
		countOption += '<option value="10">10개씩 보기</option>';
		countOption += '<option value="20">20개씩 보기</option>';
		countOption += '<option value="30">30개씩 보기</option>';
		countOption += '<option value="60">60개씩 보기</option>';
		
		$('#showContents').append(countOption);
		$('#searchSelectBox').append(searchOption);
	}
	
	function bindEvent(){  
		
		// add event  
		$('#custTable tbody').bind('click','tr',function() {	
			var seq = $(this).find('td').eq(0).text();
			locationDetail(seq);
		});
		
	}
	
	function locationDetail(url,seq){
		
	}
	
	var loadApi = function loadApi (fnc, page, displayPageNum, division) {
		
		page = parseInt(page);
		displayPageNum = parseInt(displayPageNum);
		
		page = isNaN(page) ? 1 : (typeof page === 'number') ? page : 1;
		displayPageNum = isNaN(displayPageNum) ? 10 : (typeof displayPageNum === 'number') ? displayPageNum : 10;

	    var req = {
	    		page: page,
	    		displayPageNum: displayPageNum
	    };
		 
		// var division = 'userInfoList';

		 //여기에서 모든것을 처리한다. ajax로 빼내서 사용하는건 금지.
		 //아래 함수를 단일 페이지에서 함수로 만들어서 쓰는것을 추천한다.
		 //페이징이동시 , 보여줄 페이징 갯수 선택시 사용해야하기 때문이다.
	     FindInfoList(division, req, function (response) {
	    	 var res = response;
	    	 
	    	 //200이라면 페이징을 구한다.
	    	 if(res.code == 200) {
	    		 
	    		 
	    		 fnc(res.data, page, displayPageNum, division);
	    	 }else { //200이 아닐때 empty처리 error처리 등을 기록한다.
	    		 alert('error!');
	    	 }
	     });//end FindUserInfoList

	}
	
	var drawTable = function drawTable(res, page, displayPageNum){
		
		
		page = parseInt(page);
		displayPageNum = parseInt(displayPageNum);
		
		page = isNaN(page) ? 1 : (typeof page === 'number') ? page : 1;
		displayPageNum = isNaN(displayPageNum) ? 10 : (typeof displayPageNum === 'number') ? displayPageNum : 10;
		
		var data = res.result;
		
		$('#target_page_header').text('통합회원 관리');     
		
		var rows = [];
		var columns;
		
		
	
		columns = [
			{ "name": "urIdx", "title": "회원번호", "breakpoints": "xs" },
			{ "name": "userId", "title": "아이디", "breakpoints": "xs"},
			{ "name": "userName", "title": "이름", "breakpoints": "xs"},
			{ "name": "userBirthday", "title": "생년월일", "breakpoints": "xs" },
			{ "name": "userContact1", "title": "연락처", "breakpoints": "xs sm" },
			{ "name": "userRole", "title": "등급", "breakpoints": "xs sm md" },
			{ "name": "rentCount", "title": "대여횟수", "breakpoints": "xs sm md" },
			{ "name": "regDt", "title": "가입일시", "breakpoints": "xs sm md" }
		];
		
		var dataRowKey = [];
		
		for(var i=0; i<columns.length; i++){
			dataRowKey.push(columns[i].name);
		}
						
		for(var i=0; i<data.length; i++){		
			
			var aJson = new Object();
			
			for(var j=0; j<dataRowKey.length;j++){
				
				aJson[dataRowKey[j]] = data[i][dataRowKey[j]];
				
			}
			
			rows.push(aJson);
		} 
		
		$('.table').empty();
		
		$('.table').footable({

			 'calculateWidthOverride': function() {  
				    return { width: $(window).width() };
				  },
	        'on': {
	            'postinit.ft.table': function(e, ft) {

	            }
	        },
			"columns": columns,
			"rows": rows
		});
		
		var totalCnt = res.paging.totalCount;
	    var perPageNum = res.paging.cri.perPageNum;
	    var displayPageNum = res.paging.cri.displayPageNum;

	    //page는 전역변수 사용
	    var prev = res.paging.prev;
	    var next = res.paging.next;
		
		makePageing(totalCnt, perPageNum, displayPageNum, page, prev, next, $("#page"));
		
		initializingPageData();
		
	}
	
var columns	= [
	{ "name": "urIdx", "title": "회원번호", "breakpoints": "xs" },
	{ "name": "userId", "title": "아이디", "breakpoints": "xs"},
	{ "name": "userName", "title": "이름", "breakpoints": "xs"},
	{ "name": "userBirthday", "title": "생년월일", "breakpoints": "xs" },
	{ "name": "userContact1", "title": "연락처", "breakpoints": "xs sm" },
	{ "name": "userRole", "title": "등급", "breakpoints": "xs sm md" },
	{ "name": "rentCount", "title": "대여횟수", "breakpoints": "xs sm md" },
	{ "name": "regDt", "title": "가입일시", "breakpoints": "xs sm md" }
];