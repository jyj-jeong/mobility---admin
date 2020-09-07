/*
 * reserveMnt.js

 * 예약 > 예약관리
 * 
 * 2020-02-04 lws 최초생성
 * 
 * 
 * 
 * update history
 * =============================================
 * |date       |comment             | author   |
 * =============================================
 * |2020-02-05 |ready 함수 제거             | pws      |
 * 
 * 
 * 
 * 
 * */

// 검색 SELECT BOX LIST

var CURRENT_PAGE = 0;
var carListData = [];		// 차량 select box 배열
var CRUD_METHOD = '';		// 저장 구분자
var CALCULABLE_MOTH = 'N';	// 요금계산 적용 여부
var RESERVE_STATUS = '';	// 상세 화면 진입시 대여 상태 
var BROWSEYN = "";			// 브라우저에 따른 대여일시, 반납일시 입력 input 타입 변경 및 변수 값 처리
function initializingPageData(){
	loadApi(drawTable, null, null );
	initSearchForm();  
	bindEvent();
	
	/*
	 * IE 브라우저인경우 data-mask="____-__-__ __:__" 사용
	 * 이외 부라우저인 경우 type="datetime-local" 사용
	 */
    var browse = navigator.userAgent.toLowerCase(); 
    if( (navigator.appName == 'Netscape' && browse.indexOf('trident') != -1) || (browse.indexOf("msie") != -1)) {
    	$("input[name=rentStartDay]").remove();
    	$("input[name=rentEndDay]").remove();
    	BROWSEYN = "N";
    }else{
    	$("input[name=rentStartDay2]").remove();
    	$("input[name=rentEndDay2]").remove();
    	BROWSEYN = "Y";
    }
}

// 검색 조건 SELECT BOX 초기화
function initSearchForm(){

	let target = 'commonCodeInfo';
	let method = 'select';
	let req = {
			rtCode : "RSV",
			pCode  : "RST"
	};
	
	// 상태코드
	fn_callApi(method, target, req, function(response) {
		let res = response;  
		// 200이라면 페이징을 구한다.
		if (res.code == 200) { 
			let data = res.data.result;  
			let ignoreCode = '';
			if(GLOBAL_LOGIN_USER_ROLE != 'RA'){
				ignoreCode = ['IG', 'FN', 'RE', 'AP'];
			}else{
				ignoreCode = ['IG', 'FN', 'RE'];
			}

			
			let strOption = '<option value="" >선택</option>';   
			for ( var i=0; i<data.length; i++ ) { 
				if(!ignoreCode.contains(data[i].code)){ 
					strOption += "<option value = '" + data[i].code + "'>" + data[i].codeValue + "</option>";
				}
			}
			// 검색 예약 상태
			$('#gbnStatus').empty();
			$('#gbnStatus').append(strOption);
			// 상세 페이지 예약 상태
			$('#sel_reserveStatusCode').empty();
			$('#sel_reserveStatusCode').append(strOption);
		} else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
			errorAlert('조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
		}
	});// end fn_callApi
	
	let gbnDayOption = '<option value="">선택</option>';
	gbnDayOption += '<option value="T">24시간 미만 대여</option>';
	gbnDayOption += '<option value="D">일단위 대여</option>';
	gbnDayOption += '<option value="M">월단위 대여</option>';
	gbnDayOption += '<option value="Y">연단위 대여</option>';
	
	let gbnLocationOption = '<option value="">선택</option>';
	gbnLocationOption += '<option value="IL">내륙</option>';
	//gbnLocationOption += '<option value="IS">제주를 제외한 섬</option>';
	gbnLocationOption += '<option value="JJ">제주</option>';
	
	let gbnReserveOption = '<option value="">선택</option>';
	gbnReserveOption += '<option value="AD">관리자 예약</option>';
	gbnReserveOption += '<option value="QT">견적 예약</option>';
	gbnReserveOption += '<option value="UR">사용자 예약</option>';
	
	
	$('#gbnDay').empty();
	$('#gbnDay').append(gbnDayOption);
	
	$('#gbnLocation').empty();
	$('#gbnLocation').append(gbnLocationOption); 
	
	$('#gbnReserve').empty();
	$('#gbnReserve').append(gbnReserveOption); 
	
	$('#sel_reserveTypeCode').empty();
	$('#sel_reserveTypeCode').append(gbnReserveOption); 
	
	let searchOption = '';
	searchOption += '<option value="" >선택</option>';
	searchOption += '<option value="RM" >예약번호</option>';
	searchOption += '<option value="CT" >연락처</option>';
	searchOption += '<option value="MD" >모델</option>';
	searchOption += '<option value="CR" >차량번호</option>';
	searchOption += '<option value="CM" >회원사</option>';
	searchOption += '<option value="DL" >대여방법</option>';
	
	let countOption = '';
	countOption += '<option selected value="10" >10개씩 보기</option>';
	countOption += '<option value="20" >20개씩 보기</option>';
	countOption += '<option value="30" >30개씩 보기</option>';
	countOption += '<option value="60" >60개씩 보기</option>';

	$('#showContents').append(countOption);
	$('#searchSelectBox').append(searchOption);
}

// 검색조건, 검색어 조회
function fn_search(){ 
	
	let searchKeyWord = $("#searchKeyWord").val();
	let searchSelectBox = $("#searchSelectBox option:selected").val();
	
	if(!isEmpty(searchSelectBox)){ 
		if (isEmpty(searchKeyWord)) {
			errorAlert('검색어', '검색어를 입력하세요.');
		} else {
			loadApi(drawTable, null, null);
		}
	}else{
		loadApi(drawTable, null, null);
	}
}

$("#searchKeyWord").keypress(function(e) {	
	let searchSelectBox = $("#searchSelectBox option:selected").val();
	if (!isEmpty(searchSelectBox)) {
		if (e.keyCode == 13) {
			fn_search();
		}
	} 
});

function loadApi(fnc, page, displayPageNum, division){
	
	let searchSelectBox = $("#searchSelectBox option:selected").val();
	let strSearchKeyWord = $("#searchKeyWord").val();	
	let showContents = $("#showContents option:selected").val();
	showContents = isEmpty(showContents) ? 10 : showContents;
	
	CURRENT_PAGE = parseInt(page);
	displayPageNum = parseInt(displayPageNum);

	CURRENT_PAGE = isNaN(page) ? 1 : (typeof page === 'number') ? page : 1;
	displayPageNum = isNaN(displayPageNum) ? showContents: (typeof displayPageNum === 'number') ? displayPageNum : showContents;

	let gbnStatus = $("#gbnStatus option:selected").val(); 
	let gbnDay = $("#gbnDay option:selected").val(); 
	let gbnLocation = $("#gbnLocation option:selected").val(); 
	let gbnReserve = $("#gbnReserve option:selected").val(); 

	let _rtIdx = '';
	
	if(GLOBAL_LOGIN_USER_ROLE != 'RA'){
		_rtIdx = GLOBAL_LOGIN_RT_IDX;
	}
	
	// 회원사 링크 버튼 클릭 후 변수 사용
	if(!isEmpty(GLOBAL_LINK_RTIDX)){
		_rtIdx = GLOBAL_LINK_RTIDX;
		GLOBAL_LINK_RTIDX = "";
	}

	var req = { 
    		'page': CURRENT_PAGE,
    		'displayPageNum': displayPageNum,  
    		'searchKeyWord' : strSearchKeyWord,    
			'rtIdx' : _rtIdx,
    		'gbnStatus' : gbnStatus,
    		'gbnDay' : gbnDay,
    		'gbnLocation' : gbnLocation,
    		'gbnReserve' : gbnReserve,
    		'gbnInput' : searchSelectBox
    };  

	var target = 'reserveInfoList';
	var method = 'select';
    
    fn_callApi(method, target, req, function (response) {
    	 var res = response;

    	 //200이라면 페이징을 구한다. 
    	 if(res.code == 200) {
    		 fnc(res.data, page, displayPageNum);
    	 }else { //200이 아닐때 empty처리 error처리 등을 기록한다.
    		 errorAlert('조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
    	 }
     });//end 

}

/*
 * 예약관리 리스트
 */
var drawTable = function drawTable(res, page, displayPageNum){
	page = parseInt(page);
	displayPageNum = parseInt(displayPageNum);
		
	page = isNaN(page) ? 1 : (typeof page === 'number') ? page : 1;
	displayPageNum = isNaN(displayPageNum) ? 10 : (typeof displayPageNum === 'number') ? displayPageNum : 10;
	
	var data = res.result;

	var columns;
	
	columns = [  

		{ "name": "rowNumber", "id" : "rowNum" ,"title": "No" , "visible": false },          
		{ "name": "rmIdx", "title": "예약번호" ,
			"formatter" : function(value, options, rowData){
				return '<a href="javascript:initDetailInfo(' + "'" +value + "'" +');"  >'+value+'</a>';               
			}
		},
		{ "name": "flag", "title": "구분", "breakpoints": "xs" },
		{ "name": "reserveDate", "title": "예약일시" , "breakpoints": "xs"},  
		{ "name": "reserveStatusName", "title": "상태", "breakpoints": "xs",
			"formatter" : function(value, options, rowData){   
				let displayText = nullCheck(value) == '' ? '<font color="red">미확인</font>' : value;     
				return displayText; 
			} 
		},
		{ "name": "userName", "title": "회원</br>연락처", "breakpoints": "xs",
			"formatter" : function(value, options, rowData){
				let displayText = '';
				let rowIndex = String((rowData.rowNumber)-1);
				rowIndex = rowIndex.charAt(rowIndex.length-1); 
				let targetRowData = options.rows[rowIndex];  

				let userName = nullCheck(value);
				let contact = phoneFomatter(nullCheck(targetRowData.userContact1)); 
				displayText = userName + '<br/>' + contact;
				return displayText;
			} 
		}, 
		{ "name": "modelName", "title": "모델<br/>차량번호", "breakpoints": "xs",
			"formatter" : function(value, options, rowData){
				let displayText = '';
				let rowIndex = String((rowData.rowNumber)-1);
				rowIndex = rowIndex.charAt(rowIndex.length-1); 
				let targetRowData = options.rows[rowIndex];  

				displayText = '<font color="blue">' + nullCheck(value) + '</font><br/>';  
				displayText += nullCheck(targetRowData.modelDetailName) + ' | <font color="red">' + nullCheck(targetRowData.fuelName) + '</font>';    
				displayText += '<br/>';
				displayText += nullCheck(targetRowData.carNumber);   
				return displayText;
			} 
		}, 
		{ "name": "rentStartDay", "title": "대여일시<br/>반납일시", "breakpoints": "xs sm",
			"formatter" : function(value, options, rowData){  
				let displayText = '';
				let rowIndex = String((rowData.rowNumber)-1);
				rowIndex = rowIndex.charAt(rowIndex.length-1); 
				let targetRowData = options.rows[rowIndex];  

				displayText += nullCheck(targetRowData.rentStartDay);   
				displayText += '<br/>';
				displayText += nullCheck(targetRowData.rentEndDay);
				return displayText;
			}
		},  
		{ "name": "periodDt", "title": "사용기간", "breakpoints": "xs sm md"},
		{ "name": "companyName", "title": "회원사<br/>지점", "breakpoints": "xs",
			"formatter" : function(value, options, rowData){  
				let displayText = '';
				let rowIndex = String((rowData.rowNumber)-1);
				rowIndex = rowIndex.charAt(rowIndex.length-1); 
				let targetRowData = options.rows[rowIndex];  

				displayText += nullCheck(targetRowData.companyName);   
				displayText += '<br/>';
				displayText += nullCheck(targetRowData.branchName);
				return displayText;
			}
		},
		{ "name": "deliveryTypeName", "title": "대여방법", "breakpoints": "xs"}, 
		{ "name": "paymentTotalAmount", "title": "총금액", "breakpoints": "xs sm md",
			"formatter" : function(value, options, rowData){
				let displayText = objectConvertToPriceFormat(nullCheck(value));
				return displayText + '원';
			}
		},  
		{ "name": "paymentAmount", "title": "결제금액", "breakpoints": "xs sm md" ,
			"formatter" : function(value, options, rowData){
				let displayText = objectConvertToPriceFormat(nullCheck(value));
				return displayText+ '원';  
			}
		}
	];
	
	$('#listTable').empty();
	$('#listTable').footable({
		 'calculateWidthOverride': function() {  
			    return { width: $(window).width() };
			  },
        'on': {
            'postinit.ft.table': function(e, ft) {

            }
        },
		"columns": columns,
		"rows": data  
	});  
	
	let totalCnt = res.paging.totalCount;
	let perPageNum = res.paging.cri.perPageNum;
	let showDisplayPageNum = res.paging.cri.displayPageNum;

	// page는 전역변수 사용
	let prev = res.paging.prev;
	let next = res.paging.next;

	makePaging(totalCnt, perPageNum, showDisplayPageNum, page, prev, next,$("#page"));

	if (!isEmpty(totalCnt)) {
		$('#totalRowCount').text('총 [' + totalCnt + '] 건이 검색되었습니다.');
	} else {
		$("#totalRowCount").text('총 [0] 건이 검색되었습니다.');
	}
  
}

/*
 * 결제정보 리스트
 */
function drawData(paymentList){
	var payment_list = paymentList;
	var columns;
	columns = [  

		{ "name": "paymentTypeName", "title": "결제수단" },
		{ "name": "paymentKindName", "title": "결제구분" },
		{ "name": "paymentDate",  "title": "결제일시" },          
		{ "name": "paymentAmount", "title": "결제금액" ,
			"formatter" : function(value, options, rowData){
				var displayText = objectConvertToPriceFormat(nullCheck(value));
				return displayText;                
			}
		},
		{ "name": "", "title": "정산금액", "breakpoints": "xs"},
		{ "name": "", "title": "정산예정일", "breakpoints": "xs"},
		{ "name": "", "title": "정산완료일시", "breakpoints": "xs"}
	];
	
	$('#paymentList').empty();
	$('#paymentList').footable({
		 'calculateWidthOverride': function() {  
			    return { width: $(window).width() };
			  },
	    'on': {
	        'postinit.ft.table': function(e, ft) {

	        }
	    },  
		"columns": columns,
		"rows": payment_list  
	});
}

/*
 * 모아보기 선택시
 */
function changeList() {
	loadApi(drawTable, null, null);
}

/*
 * 페이지 이동
 */
function bindEvent() {

	$("#page").on('click', 'a', function() {
		if ($(this).attr('class') != 'active') {
			let clickPage = $(this).text();
			
			let displayPageNum = $("#showContents").val();
			
			if(!isNaN(clickPage)) { //숫자면 현재 페이지므로 
				CURRENT_PAGE = parseInt(clickPage);
			} else { //
				if(clickPage == '«Previous Over') {					
					CURRENT_PAGE = parseInt(CURRENT_PAGE) - 1;
				} if(clickPage == '»Next Over') {
					CURRENT_PAGE = parseInt(CURRENT_PAGE) + 1;
				}
			}
			
			loadApi(drawTable, CURRENT_PAGE, displayPageNum);

		}
	});  
}
// LIST END ============================================================================================================================================================================

/*
 * 상세페이지 정보 셋팅
 */
function initDetailInfo(seq){
	
//	swal("상세화면은 순차적으로 오픈할 예정입니다.", { icon: "warning", });
//	if(true){
//		return;
//	}
	CRUD_METHOD = 'update';
	
	let _rmIdx = seq;

	let req = { 
			rmIdx : _rmIdx  
	};
	
	let target = 'reserveInfo';
	let method = 'select';

	// Detail정보 조회
	fn_callApi(method,target, req, function(res){  
		if( res.code == 200 ){
			let data = res.data.result[0];
			let paymentList = res.data.paymentList;
			let dataSet = new Object();
			dataSet.reserveInfo = data;
			dataSet.paymentList = paymentList;

			/*
			 * 회원사 select box
			 * 연료  select box
			 * 성별  select box
			 * 면허종류  select box
			 * 차량별차종 select box
			 */
			initDetailSelectBox(data);

			let urIdx = nullCheck(data.urIdx);
			let reserveUserName = nullCheck(data.reserveUserName);
			let reserveUserGender = nullCheck(data.reserveUserGender);
			let reserveUserEmail = nullCheck(data.reserveUserEmail);
			let reserveUserContact1 = nullCheck(data.reserveUserContact1);
			let reserveUserBirthDay = nullCheck(data.reserveUserBirthDay) == ''?'':dateFormatter(data.reserveUserBirthDay);

			// 예약자 정보
			$("#urIdx").val(urIdx);
			$("#reserveUserName").val(reserveUserName);
			$("#sel_reserveUserGender").val(reserveUserGender).prop("selected", true);
			$("#reserveUserEmail").val(reserveUserEmail);
			$("#reserveUserContact1").val(reserveUserContact1);
			$("#reserveUserBirthDay").val(reserveUserBirthDay);
			
			// 운전자 정보
//			let userFlag = nullCheck(data.;
			// 제1 운전자 정보
			let ulIdx1 = nullCheck(data.ulIdx1);
			let firstDriverName = nullCheck(data.firstDriverName);
			let firstDriverGender = nullCheck(data.firstDriverGender);
			let firstDriverContact = nullCheck(data.firstDriverContact);
			let firstDriverBirthDay = nullCheck(data.firstDriverBirthDay) == ''?'':dateFormatter(data.firstDriverBirthDay);
			let firstDriverLicenseCode = nullCheck(data.firstDriverLicenseCode);
			let firstDriverLicenseNumber = nullCheck(data.firstDriverLicenseNumber);
			let firstDriverExpirationDate = nullCheck(data.firstDriverExpirationDate) == ''?'':dateFormatter(data.firstDriverExpirationDate);
			let firstDriverLicenseIsDate = nullCheck(data.firstDriverLicenseIsDate) == ''?'':dateFormatter(data.firstDriverLicenseIsDate);
			
			$("#ulIdx1").val(ulIdx1);
			$("#firstDriverName").val(firstDriverName);
			$("#sel_firstDriverGender").val(firstDriverGender).prop("selected", true);
			$("#firstDriverContact").val(firstDriverContact);
			$("#firstDriverBirthDay").val(firstDriverBirthDay);
			$("#firstDriverLicenseCode").val(firstDriverLicenseCode);
			$("#firstDriverLicenseNumber").val(firstDriverLicenseNumber);
			$("#firstDriverExpirationDate").val(firstDriverExpirationDate);
			$("#firstDriverLicenseIsDate").val(firstDriverLicenseIsDate);
			
			// 제2 운전자 정보
			let ulIdx2 = nullCheck(data.ulIdx2);
			let secondDriverName = nullCheck(data.secondDriverName);
			let secondDriverGender = nullCheck(data.secondDriverGender);
			let secondDriverContact = nullCheck(data.secondDriverContact);
			let secondDriverBirthDay = nullCheck(data.secondDriverBirthDay) == ''?'':dateFormatter(data.secondDriverBirthDay);
			let secondDriverLicenseCode = nullCheck(data.secondDriverLicenseCode);
			let secondDriverLicenseNumber = nullCheck(data.secondDriverLicenseNumber);
			let secondDriverExpirationDate = nullCheck(data.secondDriverExpirationDate) == ''?'':dateFormatter(data.secondDriverExpirationDate);
			let secondDriverLicenseIsDate = nullCheck(data.secondDriverLicenseIsDate) == ''?'':dateFormatter(data.secondDriverLicenseIsDate);

			$("#ulIdx2").val(ulIdx2);
			$("#secondDriverName").val(secondDriverName);
			$("#sel_secondDriverGender").val(secondDriverGender).prop("selected", true);
			$("#secondDriverContact").val(secondDriverContact);
			$("#secondDriverBirthDay").val(secondDriverBirthDay);
			$("#secondDriverLicenseCode").val(secondDriverLicenseCode);
			$("#secondDriverLicenseNumber").val(secondDriverLicenseNumber);
			$("#secondDriverExpirationDate").val(secondDriverExpirationDate);
			$("#secondDriverLicenseIsDate").val(secondDriverLicenseIsDate);

			// 예약정보
			let rmIdx = nullCheck(data.rmIdx);
			let reserveYmdt = nullCheck(data.reserveYmdt);
			let reserveChannel = nullCheck(data.reserveChannel);
			let landCode = nullCheck(data.landCode);
			let reserveDate = nullCheck(data.reserveDate);
			let reserveStatusCode = nullCheck(data.reserveStatusCode);
			let reserveTypeCode = nullCheck(data.reserveTypeCode);
			let deliveryTypeCode = nullCheck(data.deliveryTypeCode);
			let rentStartDay = nullCheck(data.rentStartDay);
			let rentEndDay = nullCheck(data.rentEndDay);
			let periodDt = nullCheck(data.periodDt);
			let deliveryAddr = nullCheck(data.deliveryAddr);
			let returnAddr = nullCheck(data.returnAddr);
			
			let gbn = reserveYmdt+reserveChannel+landCode;
			
			RESERVE_STATUS = reserveStatusCode;
			
			if(BROWSEYN == "Y"){
				rentStartDay = rentStartDay.replace(' ', 'T');
				rentEndDay = rentEndDay.replace(' ', 'T');
			}
			
			$("#rmIdx").val(rmIdx);
			$("#gbn").val(gbn);
			$("#reserveDate").val(reserveDate);
			$("#sel_reserveTypeCode").val(reserveTypeCode).prop("selected", true);
			$("#sel_reserveStatusCode").val(reserveStatusCode).prop("selected", true);
			$("#sel_deliveryTypeCode").val(deliveryTypeCode).prop("selected", true);
			$("#rentStartDay").val(rentStartDay);
			$("#rentEndDay").val(rentEndDay);
			$("#periodDt").val(periodDt);
			$("#deliveryAddr").val(deliveryAddr);
			$("#returnAddr").val(returnAddr);

			if(reserveTypeCode == 'QT'){
				$("#rentcalAmt").hide();
			}else{
				$("#rentcalAmt").show();
			}
			// 회원사, 차량정보
			let rtIdx = nullCheck(data.rtIdx);
			let staffName = nullCheck(data.staffName);
			let staffContact1 = nullCheck(data.staffContact1);
			let crIdx = nullCheck(data.crIdx);
			let mdIdx = nullCheck(data.mdIdx);
			let onselfDamageCover = nullCheck(data.onselfDamageCover) == ''?'':objectConvertToPriceFormat(data.onselfDamageCover); 
			let personalCover = nullCheck(data.personalCover) == ''?'':objectConvertToPriceFormat(data.personalCover);  
			let propertyDamageCover = nullCheck(data.propertyDamageCover) == ''?'':objectConvertToPriceFormat(data.propertyDamageCover);
			let carDamageCover = nullCheck(data.carDamageCover) == ''?'':objectConvertToPriceFormat(data.carDamageCover);  
			let insuranceCompanyment = nullCheck(data.insuranceCompanyment) == ''?'':objectConvertToPriceFormat(data.insuranceCompanyment);
			let fuelCode = nullCheck(data.fuelCode);
			
			$("#rtIdx").val(rtIdx);
			$("#staffName").val(staffName);
			$("#staffContact1").val(staffContact1);
			$("#sel_modelName").val(crIdx).prop("selected", true);
			$("#mdIdx").val(mdIdx);
			$("#onselfDamageCover").val(onselfDamageCover);
			$("#personalCover").val(personalCover);
			$("#propertyDamageCover").val(propertyDamageCover);
			
			$("#carDamageCover").val(carDamageCover); // 예약시 면책금
			$("#insuranceCompanyment").val(insuranceCompanyment); // 예약시 보험료
			
			$("#sel_fuel").val(fuelCode).prop("selected", true);

			// 대여금액 정보
			let rentFee = nullCheck(data.rentFee) == ''?'':objectConvertToPriceFormat(data.rentFee);
			let insuranceFee = nullCheck(data.insuranceFee) == ''?'':objectConvertToPriceFormat(data.insuranceFee);
			let discountFee = nullCheck(data.discountFee) == ''?'':objectConvertToPriceFormat(data.discountFee);
			let deliveryFee = nullCheck(data.deliveryFee) == ''?'':objectConvertToPriceFormat(data.deliveryFee);
			let addFee = nullCheck(data.addFee) == ''?'':objectConvertToPriceFormat(data.addFee);
			let paymentTotalAmount = nullCheck(data.paymentTotalAmount) == ''?'':objectConvertToPriceFormat(data.paymentTotalAmount);
			let paymentAmount = nullCheck(data.paymentAmount) == ''?'':objectConvertToPriceFormat(data.paymentAmount);

			$("#rentFee").val(rentFee);
			$("#insuranceFee").val(insuranceFee);
			$("#discountFee").val(discountFee);
			$("#deliveryFee").val(deliveryFee);
			$("#addFee").val(addFee);
			$("#paymentTotalAmount").val(paymentTotalAmount);
			$("#paymentAmount").val(paymentAmount);
			
			$("#reserveUserName").val(reserveUserName);
			$("#sel_reserveUserGender").val(reserveUserGender).prop("selected", true);
			$("#reserveUserEmail").val(reserveUserEmail);
			$("#btnreserveUserEmail").hide();
			$("#reserveUserContact1").val(reserveUserContact1);
			$("#reserveUserBirthDay").val(reserveUserBirthDay);
			
			let refundFee = nullCheck(data.refundFee) == ''?'':objectConvertToPriceFormat(data.refundFee);
			let miSu = nullCheck(data.miSu) == ''?'':objectConvertToPriceFormat(data.miSu);
			$("#lbl_rentmisu").text(" 미납금액:"+miSu);
			$("#lbl_refundFee").text(" 환불금액:"+refundFee);

			settingInputStatus();

			drawData(paymentList);

			$("#reserveMasterInfo").iziModal('open');
//			initDetailData(dataSet);  
		}
		
	}); 	
}

/*
 * 상세페이지 콤보박스 정보 가져오기
 */
function initDetailSelectBox(_data){ 
	let target = '';
	let method = '';
	let req = {};
	let res;
	let data;
	let strOption = '';
	
	// 회원사  select box
	target = 'selectCompanyList';
	method = 'select';
	req = {};

	// 회사리스트
	fn_callApi(method, target, req, function(response) {
		res = response;   
		// 200이라면 페이징을 구한다.
		if (res.code == 200) {
			data = res.data.result.result;
			strOption = "<option value=''>선택</option>";     
			for ( var i=0; i<data.length; i++ ) { 
				let companyName = '';
				if(!isEmpty(data[i].branchName)){
					companyName = data[i].companyName + "(" + nullCheck(data[i].branchName) + ")";
				}else{
					companyName = data[i].companyName;
				}

				strOption += "<option value = '" + data[i].rtIdx + "'>" + companyName + "</option>";  
			} 				 
			$('#companyName').empty();
			$('#companyName').append(strOption);
			
			if(!isEmpty(_data)){
				let rtIdx = nullCheck(_data.rtIdx) == ''?'':_data.rtIdx;
				$("#companyName").val(rtIdx).prop("selected", true);
			}else{
				let _rtIdx = '';
				
				if(GLOBAL_LOGIN_USER_ROLE != 'RA'){
					_rtIdx = GLOBAL_LOGIN_RT_IDX;
				}

				if(!isEmpty(_rtIdx)){
					$('#companyName').val(_rtIdx).prop("selected", true);
					if(GLOBAL_LOGIN_USER_ROLE != 'RA'){
						$('#companyName').attr('disabled', true);
					}
				}else{
					$("#companyName").val('').prop("selected", true);
				}
			}
			
			let _crIdx = "";
			if(!isEmpty(_data)){
				_crIdx = nullCheck(_data.crIdx) == ''?'':_data.crIdx;
			}
			selectCompany($("#companyName option:selected").val(), _crIdx);

		} else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
			errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
		}
	});// end fn_callApi
	
	// 연료  select box
	req = {
	    		rtCode:'CR',
	      		pCode: 'FL'
	};
		 
	target = 'commonCodeInfo';
	method = 'select';

	fn_callApi(method, target, req, function (response) {
		res = response;
	    	 
			if (res.code == 200) {

				data = res.data.result;
				
				strOption = "";
				strOption += "<option value = '0'>선택하세요</option>";
				
				for ( let i in data) {
					if (data[i].codeValue) {
						strOption += "<option value = '"+ data[i].code + "'>"+ data[i].codeValue + "</option>";
					}
				}
				
				$('#sel_fuel').empty();
				$('#sel_fuel').append(strOption);
			
				if(!isEmpty(_data)){
					let fuelCode = nullCheck(_data.fuelCode) == ''?'0':_data.fuelCode; //연료구분code
					$("#sel_fuel").val(fuelCode).prop("selected", true);
				}else{
					$("#sel_fuel").val('0').prop("selected", true);
				}
			}
	});

	// 성별  select box
	req = {
	    		rtCode:'CCD',
	      		pCode: 'GD'
	};
		 
	target = 'commonCodeInfo';
	method = 'select';

	fn_callApi(method, target, req, function (response) {
		res = response;
	    	 
			if (res.code == 200) {

				data = res.data.result;
				
				strOption = "";
				strOption += "<option value = '0'>선택하세요</option>";
				
				for ( let i in data) {
					if (data[i].codeValue) {
						strOption += "<option value = '"+ data[i].code + "'>"+ data[i].codeValue + "</option>";
					}
				}

				$('#sel_reserveUserGender').empty();
				$('#sel_reserveUserGender').append(strOption);
				
				if(!isEmpty(_data)){
					let reserveUserGender = nullCheck(_data.reserveUserGender) == ''?'0':_data.reserveUserGender;
					$("#sel_reserveUserGender").val(reserveUserGender).prop("selected", true);
				}else{
					$("#sel_reserveUserGender").val('0').prop("selected", true);
				}

				$('#sel_firstDriverGender').empty();
				$('#sel_firstDriverGender').append(strOption);
				
				if(!isEmpty(_data)){
					let firstDriverGender = nullCheck(_data.firstDriverGender) == ''?'0':_data.firstDriverGender;
					$("#sel_firstDriverGender").val(firstDriverGender).prop("selected", true);
				}else{
					$("#sel_firstDriverGender").val('0').prop("selected", true);
				}

				$('#sel_secondDriverGender').empty();
				$('#sel_secondDriverGender').append(strOption);
				
				if(!isEmpty(_data)){
					let secondDriverGender = nullCheck(_data.secondDriverGender) == ''?'0':_data.secondDriverGender;
					$("#sel_secondDriverGender").val(secondDriverGender).prop("selected", true);
				}else{
					$("#sel_secondDriverGender").val('0').prop("selected", true);
				}
			}
	});
	
	// 면허종류  select box
	req = {
	    		rtCode:'CR',
	      		pCode: 'DL'
	};
		 
	target = 'commonCodeInfo';
	method = 'select';

	fn_callApi(method, target, req, function (response) {
		res = response;
	    	 
			if (res.code == 200) {

				data = res.data.result;
				
				strOption = "";
				strOption += "<option value='0'>선택하세요</option>";
				
				for ( let i in data) {
					if (data[i].codeValue) {
						strOption += "<option value = '"+ data[i].code + "'>"+ data[i].codeValue + "</option>";
					}
				}
				
				$('#sel_firstDriverLicenseCode').empty();
				$('#sel_firstDriverLicenseCode').append(strOption);
				if(!isEmpty(_data)){
					let firstDriverLicenseCode = nullCheck(_data.firstDriverLicenseCode) == ''?'0':_data.firstDriverLicenseCode;
					$("#sel_firstDriverLicenseCode").val(firstDriverLicenseCode).prop("selected", true);
				}else{
					$("#sel_firstDriverLicenseCode").val('0').prop("selected", true);
				}

				$('#sel_secondDriverLicenseCode').empty();
				$('#sel_secondDriverLicenseCode').append(strOption);
				if(!isEmpty(_data)){
					let secondDriverLicenseCode = nullCheck(_data.secondDriverLicenseCode) == ''?'0':_data.secondDriverLicenseCode;
					$("#sel_secondDriverLicenseCode").val(secondDriverLicenseCode).prop("selected", true);
				}else{
					$("#sel_secondDriverLicenseCode").val('0').prop("selected", true);
				}
			}
	});

	// 배달방법  select box
	req = {
	    		rtCode:'QT',
	      		pCode: 'QDC'
	};
		 
	target = 'commonCodeInfo';
	method = 'select';

	fn_callApi(method, target, req, function (response) {
		res = response;
	    	 
			if (res.code == 200) {

				data = res.data.result;
				
				strOption = "";
				strOption += "<option value='0'>선택하세요</option>";
				
				for ( let i in data) {
					if (data[i].codeValue) {
						strOption += "<option value = '"+ data[i].code + "'>"+ data[i].codeValue + "</option>";
					}
				}
				
				$('#sel_deliveryTypeCode').empty();
				$('#sel_deliveryTypeCode').append(strOption);
				if(!isEmpty(_data)){
					let deliveryTypeCode = nullCheck(_data.deliveryTypeCode) == ''?'0':_data.deliveryTypeCode;
					$("#sel_deliveryTypeCode").val(deliveryTypeCode).prop("selected", true);
				}else{
					$("#sel_deliveryTypeCode").val('0').prop("selected", true);
				}
			}
	});
	
}

/*
 * 회원사별 차량 리스트
 */
function selectCompany(rtIdx, crIdx){
	let rentStartDay = getPureText($("#rentStartDay").val().replace('____-__-__ __:__','').replace('T',' ').replace(' ',''));
	let rentEndDay = getPureText($("#rentEndDay").val().replace('____-__-__ __:__','').replace('T',' ').replace(' ',''));
	let rmIdx = getPureText($("#rmIdx").val());

//	if(isEmpty(rentStartDay)){
//		errorAlert('대여일시를 먼저 입력하여 주세요');
//		$("#companyName").val('').prop("selected", true);
//		return;
//	}else if(isEmpty(rentEndDay)){
//		errorAlert('반납일시를 먼저 입력하여 주세요');
//		$("#companyName").val('').prop("selected", true);
//		return;
//	}

	let target = 'selectCompanyInfoAndCarInfo';
	let method = 'select';
	let req = { 
			'rtIdx' : rtIdx 
			,'rmIdx' : rmIdx 
			,'rentStartDay' : rentStartDay 
			,'rentEndDay' : rentEndDay 
	};

	fn_callApi(method, target, req, function(response) {
		let res = response;
		// 200이라면 페이징을 구한다. 
		if (res.code == 200) {
			let companyInfo = res.data.result.companyInfo; 
			if( companyInfo != undefined && companyInfo != null){
				let staffContact = nullCheck(companyInfo.staffContact1);
				let staffName = nullCheck(companyInfo.staffName);
				let companyAddress = nullCheck(companyInfo.companyAddress);
				let companyAddressDetail = nullCheck(companyInfo.companyAddressDetail);
				let companyAddr = companyAddress + ' ' + companyAddressDetail;
				$('#staffContact1').val('');
				$('#staffName').val(''); 
				
				$('#staffContact1').val(staffContact);
				$('#staffName').val(staffName); 
				
				let deliveryTypeCode = $("#sel_deliveryTypeCode option:selected").val();
				// 배달 지점 방문인 경우 회원사 주소 셋팅
				if(deliveryTypeCode == 'OF'){
					$('#returnAddr').val(companyAddr);
					$('#deliveryAddr').val(companyAddr);
				}

			}
			
			let carList = res.data.result.carList;     
			carListData = carList;

			let strOption = "<option value=''>선택</option>";    
			for ( var i=0; i<carList.length; i++ ) {
				strOption += "<option value = '" + carList[i].crIdx + "'>" + carList[i].modelName + '('+carList[i].carNumber+')' + "</option>";  
			}

			$('#sel_modelName').empty();
			$('#sel_modelName').append(strOption);
			if(!isEmpty(crIdx)){
				$("#sel_modelName").val(crIdx).prop("selected", true);
				selectCarInfo(crIdx);
			}
		} else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
			errorAlert('조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
		} 
	});// end fn_callApi

}

/*
 * 차량정보 가져오기
 */
function selectCarInfo(crIdx){
	let crIdxIndex = $("#sel_modelName option:selected").index() - 1;

	let mdIdx = nullCheck(carListData[crIdxIndex].mdIdx);
	let fuelCode = nullCheck(carListData[crIdxIndex].fuelCode);
	let year = nullCheck(carListData[crIdxIndex].year);
	let colorName = nullCheck(carListData[crIdxIndex].colorName);
	let mileage = nullCheck(carListData[crIdxIndex].mileage) == ''?'0':objectConvertToPriceFormat(carListData[crIdxIndex].mileage);
	let onselfDamageCover = nullCheck(carListData[crIdxIndex].onselfDamageCover) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].onselfDamageCover);
	let personalCover = nullCheck(carListData[crIdxIndex].personalCover) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].personalCover);
	let propertyDamageCover = nullCheck(carListData[crIdxIndex].propertyDamageCover) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].propertyDamageCover);
	let carTypeCode = nullCheck(carListData[crIdxIndex].carTypeCode);
	let optionCodeValue  = nullCheck(carListData[crIdxIndex].optionCodeValue);

	$('#mdIdx').val(mdIdx);
	$("#sel_fuel").val(fuelCode).prop("selected", true);
	$('#year').val(year);
	$('#colorName').val(colorName);
	$('#mileage').val(mileage);
	$('#onselfDamageCover').val(onselfDamageCover);
	$('#personalCover').val(personalCover);
	$('#propertyDamageCover').val(propertyDamageCover);
	$('#carTypeCode').val(carTypeCode);
	$('#optionCodeValue').val(optionCodeValue);

	let carDamageCover = nullCheck(carListData[crIdxIndex].carDamageCover) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].carDamageCover);
	let insuranceCompanyment = nullCheck(carListData[crIdxIndex].insuranceCompanyment) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].insuranceCompanyment);
	let carDamageCover2 = nullCheck(carListData[crIdxIndex].carDamageCover2) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].carDamageCover2);
	let insuranceCompanyment2 = nullCheck(carListData[crIdxIndex].insuranceCompanyment2) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].insuranceCompanyment2);
	let carDamageCover3 = nullCheck(carListData[crIdxIndex].carDamageCover3) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].carDamageCover3);
	let insuranceCompanyment3 = nullCheck(carListData[crIdxIndex].insuranceCompanyment3) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].insuranceCompanyment3);
	let carDamageCover4 = nullCheck(carListData[crIdxIndex].carDamageCover4) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].carDamageCover4);
	let insuranceCompanyment4 = nullCheck(carListData[crIdxIndex].insuranceCompanyment4) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].insuranceCompanyment4);

	let strOption = "";
	strOption += "<option value = ''>선택하세요</option>";
 
	if(!isEmpty(carDamageCover) && !isEmpty(insuranceCompanyment)){
		strOption += "<option value = '"+insuranceCompanyment+"'>" + "면책금:" + carDamageCover + "/보험금:" + insuranceCompanyment + "</option>";
	}
	if(!isEmpty(carDamageCover2) && !isEmpty(insuranceCompanyment2)){
		strOption += "<option value = '"+insuranceCompanyment2+"'>" + "면책금:" + carDamageCover2 + "/보험금:" + insuranceCompanyment2 + "</option>";
	}
	if(!isEmpty(carDamageCover3) && !isEmpty(insuranceCompanyment3)){
		strOption += "<option value = '"+insuranceCompanyment3+"'>" + "면책금:" + carDamageCover3 + "/보험금:" + insuranceCompanyment3 + "</option>";
	}
	if(!isEmpty(carDamageCover4) && !isEmpty(insuranceCompanyment4)){
		strOption += "<option value = '"+insuranceCompanyment4+"'>" + "면책금:" + carDamageCover4 + "/보험금:" + insuranceCompanyment4 + "</option>";
	}

	$('#sel_ciIdx').empty();
	$('#sel_ciIdx').append(strOption);
	
	let revinsuranceCompanyment = $("#insuranceCompanyment").val();
	let revcarDamageCover = $("#carDamageCover").val();
	
	if((isEmpty(revinsuranceCompanyment) || revinsuranceCompanyment == '0') && !isEmpty(revcarDamageCover) && revcarDamageCover > '0'){
		if (carDamageCover == revcarDamageCover){
			revinsuranceCompanyment = insuranceCompanyment;
		}else if (carDamageCover2 == revcarDamageCover){
			revinsuranceCompanyment = insuranceCompanyment2;
		}else if (carDamageCover3 == revcarDamageCover){
			revinsuranceCompanyment = insuranceCompanyment3;
		}else if (carDamageCover4 == revcarDamageCover){
			revinsuranceCompanyment = insuranceCompanyment4;
		}
	}

	if(!isEmpty(revinsuranceCompanyment) && revinsuranceCompanyment != '0' && !isEmpty(crIdx) ){
		$("#sel_ciIdx").val(revinsuranceCompanyment).prop("selected", true);
	}else{
		$("#sel_ciIdx").val('').prop("selected", true);
	}
	
}

/*
 * 회원검색(회원id검색)
 */
function serachUserInfo(flag, urIdx) {
	let reserveUserEmail = '';

	if(flag == 'init'){
		reserveUserEmail = $("#reserveUserEmail").val();
	}else if(flag == 'first'){
		reserveUserEmail = $("#firstDriverId").val();
	}else if(flag == 'second'){
		reserveUserEmail = $("#secondDriverId").val();
	}

	if(isEmpty(reserveUserEmail)){
		errorAlert('이메일', '회원 가입시 등록한 이메일을 입력하여 주세요');
		return;
	}
	
	let req = {
		userId : reserveUserEmail
	};

	let target = 'userInfoListDetail';
	let method = 'select';

	// Detail정보 조회
	fn_callApi(method,target, req, function(response) {
		let res = response;

		// 200이라면 페이징을 구한다.
		if (res.code == 200) {
			var data = res.data.result;
			if(isEmpty(data)){
				errorAlert('회원정보', '회원정보가 존재하지 않습니다.');
				$("#urIdx").val('');
			}else{
				// 기본정보 셋팅
				let urIdx 			= data.urIdx;
				let userName 		= data.userName;
				let userContact1 	= phoneFomatter(data.userContact1);
				let userGender		= data.userGender;
				let userId			= data.userId;
				let userBirthDay	= data.userBirthDay;
				userBirthDay 		= dateFormatter(userBirthDay);

				if(flag == 'init'){
					$("#urIdx").val(urIdx);
					$("#reserveUserName").val(userName);
					$("#reserveUserContact1").val(userContact1);
					$("#reserveUserEmail").val(userId);
					$("#reserveUserBirthDay").val(userBirthDay);
					$("#sel_reserveUserGender").val(userGender).prop("selected", true);
				}else if(flag == 'first'){
					$("#firstDriverId").val(userId);
					$("#firstDriverName").val(userName);
					$("#sel_firstDriverGender").val(userGender).prop("selected", true);
					$('#firstDriverContact').val(userContact1);
					$('#firstDriverBirthDay').val(userBirthDay);  
					DriverSetting(flag, urIdx);
				}else if(flag == 'second'){
					$("#secondDriverId").val(userId);
					$("#secondDriverName").val(userName);
					$("#sel_secondDriverGender").val(userGender).prop("selected", true);
					$('#secondDriverContact').val(userContact1);
					$('#secondDriverBirthDay').val(userBirthDay);  
					DriverSetting(flag, urIdx);
				}

//					swal("회원정보 조회 완료", {icon : "success"});
			}
			
		} else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
			errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
		}
	});// end fn_callApi
}

/*
 * 에약자와 동일 체크박스 선택시 회원 정보 가져오기
 */
function firstDriverSetting(target){

	let flag = $("#"+target).is(':checked'); 

	if(flag){
		$('#firstDriverDiv').find('input').each(function(){
			$(this).val('');
		});
		let urIdx =  $('#urIdx').val();

		let name = $('#reserveUserName').val();
		let gender = $("#sel_reserveUserGender option:selected").val();
		let contact1 = $('#reserveUserContact1').val();
		let birthDay = $('#reserveUserBirthDay').val();
		
		$('#firstDriverName').val(name);  
		$("#sel_firstDriverGender").val(gender).prop("selected", true)
		$('#firstDriverContact').val(contact1);
		$('#firstDriverBirthDay').val(birthDay);  

		if(!isEmpty(urIdx)){
			DriverSetting('first', urIdx);
		}

		
	}else{
		$('#ulIdx1').val('');  
		$('#firstDriverName').val('');  
		$("#sel_firstDriverGender").val('0').prop("selected", true);
		$('#firstDriverContact').val('');
		$('#firstDriverBirthDay').val('');  

		$('#sel_firstDriverLicenseCode').val('0').prop("selected", true);
		$('#firstDriverLicenseNumber').val('');
		$('#firstDriverExpirationDate').val('');
		$('#firstDriverLicenseIsDate').val('');
	}
}
/*
 * 회원인 경우 운전면허 정보 가져오기
 */
function DriverSetting(flag, urIdx){

	let req = {
			urIdx : urIdx
	};

	let target = 'userLicenseInfo';
	let method = 'select';
	// Detail정보 조회
	fn_callApi(method,target, req, function(response) {
		let res = response;
	
		// 200이라면 페이징을 구한다.
		if (res.code == 200) {
	
			var data = res.data.result[0];
	
			let licenseCode  		= '';// 면허코드
			let licenseNumber  		= '';// 운전면허 번호
			let licenseExpiration  	= '';// 적성검사만료일
			let licenseIssueDt  	= '';// 면허 발급일
			let userLicenseOwnYn 	= '';
			let licenseLocation     = '';// 면허지역
			let useYn				= '';// 사용여부
			
			if(!isEmpty(data)) {
				ulIdx  				= data.ulIdx;			
				licenseCode  		= data.licenseCode;			
				licenseNumber  		= data.licenseNumber;  		
				licenseExpiration  	= data.licenseExpiration;
				licenseIssueDt  	= data.licenseIssueDt;  	
				userLicenseOwnYn 	= data.userLicenseOwnYn; 
				licenseLocation     = data.licenseLocation;		

				if(flag == 'first'){
					$('#ulIdx1').val(ulIdx);
					$('#sel_firstDriverLicenseCode').val(licenseCode);
					$('#firstDriverLicenseNumber').val(licenseNumber);
					$('#firstDriverExpirationDate').val(licenseExpiration);
					$('#firstDriverLicenseIsDate').val(licenseIssueDt);
				}else if(flag == 'second'){
					$('#ulIdx2').val(ulIdx);
					$('#sel_secondDriverLicenseCode').val(licenseCode);
					$('#secondDriverLicenseNumber').val(licenseNumber);
					$('#secondDriverExpirationDate').val(licenseExpiration);
					$('#secondDriverLicenseIsDate').val(licenseIssueDt);
				}
			}
			
		}
	});

}

/*
 * 요금 계산
 */
function rentcal(){

	let crIdx = $("#sel_modelName option:selected").val();
	let calRentStartDt = $("#rentStartDay").val().replace('____-__-__ __:__','').replace('T',' ');
	let calRentEndDt = $("#rentEndDay").val().replace('____-__-__ __:__','').replace('T',' ');
	let selInsuranceFee = $("#sel_ciIdx option:selected").val().replace(',','');

	if(isEmpty(calRentStartDt)){
		errorAlert('요금계산', '대여일시를 입력해 주세요.');
		return;
	}
	if(isEmpty(calRentEndDt)){
		errorAlert('요금계산', '반납일시를 입력해 주세요.');
		return;
	}
	if(calDate(getPureText(calRentStartDt), getPureText(calRentEndDt)) == 0){
		if(isEmpty(selInsuranceFee)){
			errorAlert('요금계산', '자차보험을 선택해 주세요.');
			return;
		}
	}
//	alert(crIdx+'<>'+calRentStartDt+'<>'+calRentEndDt+'<>'+selInsuranceFee);
	let req = {
			crIdx : crIdx,
			calRentStartDt : calRentStartDt,
			calRentEndDt : calRentEndDt,
			insuranceCompanyment : selInsuranceFee
	};

	let target = 'selectReserveAmt';
	let method = 'select';

	fn_callApi(method,target,req,function(response) {
		let res = response;

		// 200이라면 페이징을 구한다.
		if (res.code == 200) {
			let data = res.data.result[0];
			
			let rentFee				= data.rentFee; 			//대여금
			let disRentFee			= data.disRentFee; 			//할인 후 대여금 -->
			let insuranceFee		= data.insuranceFee; 		//보험료 -->
			let mmRentAmt			= data.mmRentAmt; 			//장기 월 대여요금 -->
			let mmLastRentAmt		= data.mmLastRentAmt; 		//장기 마지막월 대여요금 -->
			let commissionPer		= data.commissionPer; 		//회원사 수수료 -->
			let calcPeriodDt		= data.calcPeriodDt; 		//대여일수
			let carDeposit			= data.carDeposit; 			//월장기 보증금
			let deliveryStandardPay	= data.deliveryStandardPay; //배달기본요금
			let deliveryAddPay		= data.deliveryAddPay; 		//배달10KM단위추가요금
			let periodMm			= data.periodMm; 			//총 개월수
			
			if(rentFee == '0' && insuranceFee == '0'){
				errorAlert('요금계산', '보험료정보 또는 기본요금 정보를 확인하세요.');
				return;
			}
			
			let calRentFee = '0';
			let calInsuranceFee = '0';
			let calRentTotAmount = '0';
//			let calCarssumRate = '0';
			let calPaymentAmount = '0';
			let caldiscountFee = '0';
			let deliveryFee = '0';
			let calRentTotAmountEtc = '';
			
			let deliveryTypeCode = getPureText($('#sel_deliveryTypeCode option:selected').val());
			// 배달대여인 경우
			if(deliveryTypeCode == 'DL'){
				deliveryFee = deliveryStandardPay;
			}else{
				deliveryFee = '0';
			}
			
			calRentFee = rentFee;
			if(periodMm == 0){
				calInsuranceFee = insuranceFee;
				caldiscountFee = Number(rentFee) - Number(disRentFee);
				calRentTotAmountEtc = '';
			}else{
				calInsuranceFee = '0';
				caldiscountFee = '0';
				calRentTotAmountEtc = '월장기 총금액 : ' + objectConvertToPriceFormat(rentFee) + '원<br>';
				calRentTotAmountEtc += '월별 대여요금 : ' + objectConvertToPriceFormat(mmRentAmt) + '원 *' + objectConvertToPriceFormat(periodMm) +'개월수';
				if(mmLastRentAmt >= 0){
					calRentTotAmountEtc += '<br>남은 일수의 대여요금 : ' + objectConvertToPriceFormat(mmLastRentAmt) + '원';
				}
				if(deliveryFee != '0'){
					calRentTotAmountEtc += '<br>배달료 첫 결제에 포함 : ' + objectConvertToPriceFormat(deliveryFee) + '원';
				}
			}
			
			calRentTotAmount = Number(calRentFee) + Number(calInsuranceFee) - Number(caldiscountFee) + Number(deliveryFee);
//			calCarssumRate = calRentTotAmount*(Number(commissionPer)/100);
			if(periodMm == 0){
				// 대여금액 = (할인전)대여금 + 보험금 - (할인후)대여금 + 배달금액
				calPaymentAmount = calRentTotAmount;
			}else{
				// 대여금액 = 월장기기본요금 + 배달금액
				calPaymentAmount = Number(mmRentAmt) + Number(deliveryFee);
			}
			
			$("#rentFee").val(objectConvertToPriceFormat(calRentFee));
			$("#insuranceFee").val(objectConvertToPriceFormat(calInsuranceFee));
			$("#discountFee").val(objectConvertToPriceFormat(caldiscountFee));
			$("#revaddFee").val('0');
			$("#deliveryFee").val(objectConvertToPriceFormat(deliveryFee));
			$("#periodDt").val(calcPeriodDt);
			$("#carDeposit").val(objectConvertToPriceFormat(carDeposit));
			
			$("#paymentTotalAmount").val(objectConvertToPriceFormat(calRentTotAmount));
			$("#paymentAmount").val(objectConvertToPriceFormat(calPaymentAmount));
			
			$("#calRentTotAmountEtc").empty();
			$("#calRentTotAmountEtc").append(calRentTotAmountEtc);
			
//			$("#deliveryFee").val('0');
			CALCULABLE_MOTH = 'Y';
		}
	});// end fn_callApi

}

/*
 * 상세페이지 오픈
 */
function initDetailData(data){
	 
	if(data == 'insert'){
		CRUD_METHOD = data; 
	}
	   
	initDetailSelectBox(null);
	settingInputStatus();
	$("#reserveMasterInfo").iziModal('open');

}
/*
 * 신규 등록, 상세 페이지 로딩시 프리폼컨트롤 초기화
 */
function settingInputStatus(){
	
	if(CRUD_METHOD == 'insert'){
		$("#reserveMasterInfo").find('input').each(function(){
			$(this).val('');  
			// input 태그의 자동완성 기능 해제
			$(this).attr('autocomplete','off');  
		});

		$("#reserveMasterInfo").find('select').each(function(){
			$(this).find('option').eq(0).prop('selected',true);    
		});    
		
		$('#companyName').prop('disabled' , false); 
		$('#reserveUserEmail').prop('readonly' , false); 
		$('#reserveUserName').prop('readonly' , false); 
		$('#sel_reserveUserGender').prop('disabled' , false); 
		$('#reserveUserContact1').prop('readonly' , false);
		$('#reserveUserBirthDay').prop('readonly' , false);
		
		$('#btnreserveUserEmail').show();

		let strOption = "<option value=''>선택하세요</option>";    
		$('#sel_modelName').empty();
		$('#sel_modelName').append(strOption);
		$("#sel_modelName").val('').prop("selected", true);
		
		$('#sel_ciIdx').empty();
		$('#sel_ciIdx').append(strOption);
		$("#sel_ciIdx").val('').prop("selected", true);
		$('#sel_fuel').attr('disabled', true);
		
		$('#rentcalAmt').show();
		$("#sel_reserveTypeCode").val('AD').prop("selected", true);
		$("#sel_reserveTypeCode").attr('disabled', true);

		$("#sel_reserveStatusCode").val('RS').prop("selected", true);
		$("#sel_reserveStatusCode").attr('disabled', true);
		
		drawData(null);
		
	}else{
		$('#reserveUserName').attr('readonly', true);
		$('#sel_reserveUserGender').attr('disabled', true);
		$('#reserveUserEmail').attr('readonly', true);
		$('#reserveUserContact1').attr('readonly', true);
		$('#reserveUserBirthDay').attr('readonly', true);

		if(GLOBAL_LOGIN_USER_ROLE != 'RA'){
			$('#companyName').attr('disabled', true);
		}
		$('#sel_fuel').attr('disabled', true);
		$("#sel_reserveTypeCode").attr('disabled', true);
		
		$('#btnreserveUserEmail').hide();
		
		// 예약/대여 취소시 수정불가 처리
		if($("#sel_reserveStatusCode").val() == "RC"){
			$("#sel_reserveStatusCode").attr('disabled', true);
			$('#rentcalAmt').hide();
			$('#btnReserveSave').hide();
		}else{
			$("#sel_reserveStatusCode").attr('disabled', false);
			$('#rentcalAmt').show();
			$('#btnReserveSave').show();
		}
	}  
}
/*
 * 예약저장 값 체크
 */
function detailValidation(){
//	swal("예약 저장 작업중...", { icon: "warning", });
//	if(true){
//		return;
//	}
	
	let title, text, icon, cancel_text, save_type;
	let target = '';
	let method = '';

	let urIdx = getPureText($('#urIdx').val());
	let reserveUserEmail = $('#reserveUserEmail').val();
	let reserveUserName = getPureText($('#reserveUserName').val());
	let reserveUserGender = getPureText($('#sel_reserveUserGender option:selected').val());
	let reserveUserContact1 = getPureText($('#reserveUserContact1').val());
	let reserveUserBirthDay = getPureText($('#reserveUserBirthDay').val());

	if (isEmpty(reserveUserEmail)) { // is not empty
		errorAlert('회원', '이메일(아이디)는 필수 입력값 입니다.');
		return;
	}else if (isEmpty(reserveUserName)) { // is not empty
		errorAlert('회원', '이름은 필수 입력값 입니다.');
		return;
	}else if (isEmpty(reserveUserGender)) { // is not empty
		errorAlert('회원', '성별은 필수 선택값 입니다.');
		return;
	}else if (isEmpty(reserveUserContact1)) { // is not empty
		errorAlert('회원', '연락처는 필수 입력값 입니다.');
		return;
	}else if (isEmpty(reserveUserBirthDay)) { // is not empty
		errorAlert('회원', '생년월일은 필수 입력값 입니다.');
		return;
	}
//	alert(urIdx+'\n'+reserveUserEmail+'\n'+reserveUserName+'\n'+reserveUserGender+'\n'+reserveUserContact1+'\n'+reserveUserBirthDay);
	let reserveTypeCode = getPureText($('#sel_reserveTypeCode option:selected').val());
	let ulIdx1 = getPureText($('#urIdx').val());
	let firstDriverName = getPureText($('#firstDriverName').val());
	let firstDriverGender = getPureText($('#sel_firstDriverGender option:selected').val());
	let firstDriverContact = getPureText($('#firstDriverContact').val());
	let firstDriverBirthDay = getPureText($('#firstDriverBirthDay').val());
	let firstDriverLicenseCode = getPureText($('#sel_firstDriverLicenseCode option:selected').val());
	let firstDriverLicenseNumber = getPureText($('#firstDriverLicenseNumber').val());
	let firstDriverExpirationDate = $('#firstDriverExpirationDate').val();
	let firstDriverLicenseIsDate = $('#firstDriverLicenseIsDate').val();

	if (isEmpty(firstDriverName)) { // is not empty
		errorAlert('제1운전자', '이름은 필수 입력값 입니다.');
		return;
	}else if (isEmpty(firstDriverGender)) { // is not empty
		errorAlert('제1운전자', '성별은 필수 선택값 입니다.');
		return;
	}else if (isEmpty(firstDriverContact)) { // is not empty
		errorAlert('제1운전자', '연락처는 필수 입력값 입니다.');
		return;
	}else if (isEmpty(firstDriverBirthDay)) { // is not empty
		errorAlert('제1운전자', '생년월일은 필수 입력값 입니다.');
		return;
	}else if (isEmpty(firstDriverLicenseCode) && reserveTypeCode != 'QT') { // is not empty
		errorAlert('제1운전자', '면허종류는 필수 선택값 입니다.');
		return;
	}else if (isEmpty(firstDriverLicenseNumber) && reserveTypeCode != 'QT') { // is not empty
		errorAlert('제1운전자', '면허번호는 필수 입력값 입니다.');
		return;
	}else if (isEmpty(firstDriverExpirationDate) && reserveTypeCode != 'QT') { // is not empty
		errorAlert('제1운전자', '적성검사 만료일은 필수 입력값 입니다.');
		return;
	}else if (isEmpty(firstDriverLicenseIsDate) && reserveTypeCode != 'QT') { // is not empty
		errorAlert('제1운전자', '발급일은 필수 입력값 입니다.');
		return;
	}
//	alert(ulIdx1+'\n'+firstDriverName+'\n'+firstDriverGender+'\n'+firstDriverContact+'\n'+firstDriverBirthDay+'\n'+firstDriverLicenseCode+'\n'+firstDriverLicenseNumber+'\n'+firstDriverExpirationDate+'\n'+firstDriverLicenseIsDate);
	let ulIdx2 = getPureText($('#ulIdx2').val());
	let secondDriverName = getPureText($('#secondDriverName').val());
	let secondDriverGender = getPureText($('#sel_secondDriverGender option:selected').val());
	let secondDriverContact = getPureText($('#secondDriverContact').val());
	let secondDriverBirthDay = getPureText($('#secondDriverBirthDay').val());
	let secondDriverLicenseCode = getPureText($('#sel_secondDriverLicenseCode option:selected').val());
	let secondDriverLicenseNumber = getPureText($('#secondDriverLicenseNumber').val());
	let secondDriverExpirationDate = $('#secondDriverExpirationDate').val();
	let secondDriverLicenseIsDate = $('#secondDriverLicenseIsDate').val();
//	alert(ulIdx2+'\n'+secondDriverName+'\n'+secondDriverGender+'\n'+secondDriverContact+'\n'+secondDriverBirthDay+'\n'+secondDriverLicenseCode+'\n'+secondDriverLicenseNumber+'\n'+secondDriverExpirationDate+'\n'+secondDriverLicenseIsDate);
	
	let rmIdx = getPureText($('#rmIdx').val());
	let reserveDate = $('#reserveDate').val();
	let reserveStatusCode = getPureText($('#sel_reserveStatusCode option:selected').val());
	let deliveryTypeCode = getPureText($('#sel_deliveryTypeCode option:selected').val());
	let rentStartDay = getPureText($('#rentStartDay').val()).replace('T', ' ');
	let rentEndDay = getPureText($('#rentEndDay').val()).replace('T', ' ');
	let deliveryAddr = getPureText($('#deliveryAddr').val());
	let returnAddr = getPureText($('#returnAddr').val());

//	alert(rentStartDay+'<>'+rentEndDay);
//	return;
	if(deliveryTypeCode == 'OF' && isEmpty(returnAddr)){
		returnAddr = deliveryAddr;
	}

	if (isEmpty(reserveTypeCode) || reserveTypeCode == '0') { // is not empty
		errorAlert('예약', '예약구분은 필수 선택값 입니다.');
		return;
	}else if (isEmpty(reserveStatusCode) || reserveStatusCode == '0') { // is not empty
		errorAlert('예약', '상태는 필수 선택값 입니다.');
		return;
	}else if (isEmpty(deliveryTypeCode) || deliveryTypeCode == '0') { // is not empty
		errorAlert('예약', '대여방법은 필수 선택값 입니다.');
		return;
	}else if (isEmpty(rentStartDay)) { // is not empty
		errorAlert('예약', '대여일시는 필수 입력값 입니다.');
		return;
	}else if (isEmpty(rentEndDay)) { // is not empty
		errorAlert('예약', '반납일시 필수 입력값 입니다.');
		return;
	}else if(rentStartDay >= rentEndDay){
		errorAlert('예약', '대여일시가 반납일시 보다 크거나 같을 수 없습니다.');
		return;
	}else if (isEmpty(deliveryAddr)) { // is not empty
		errorAlert('예약', '대여위치는 필수 입력값 입니다.');
		return;
	}else if (isEmpty(returnAddr)) { // is not empty
		errorAlert('예약', '반납위치는 필수 입력값 입니다.');
		return;
	}

	let sdt = rentStartDay.split(' ');
	rentStartDay = sdt[0];
	let rentStartTime = sdt[1];

	let edt = rentEndDay.split(' ');
	rentEndDay = edt[0];
	let rentEndTime = edt[1];


	let periodDt = $("#periodDt").val();
    let exp = /개월/; 

    let longTermYn = '';
	if(exp.test(periodDt)){
		longTermYn = 'LT';
	}else{
		longTermYn = 'ST';
	}
//	alert(reserveTypeCode+'\n'+rmIdx+'\n'+reserveDate+'\n'+reserveStatusCode+'\n'+deliveryTypeCode+'\n'+rentStartDay+'\n'+rentEndDay+'\n'+deliveryAddr+'\n'+returnAddr);
	
	let rtIdx = getPureText($('#companyName option:selected').val());
	let mdIdx = getPureText($('#mdIdx').val());
	let crIdx = getPureText($('#sel_modelName option:selected').val()); // 차량순번
	let carDamageCover = getPureText($('#carDamageCover').val());
	let insuranceCompanyment = getPureText($('#insuranceCompanyment').val());
	let carTypeCode = getPureText($('#carTypeCode').val());
	
	if (isEmpty(rtIdx)) { // is not empty
		errorAlert('차량', '회사명(지점)은 필수 선택값 입니다.');
		return;
	}else if (isEmpty(crIdx)) { // is not empty
		errorAlert('차량', '모델(번호)는 필수 선택값 입니다.');
		return;
	}else if (isEmpty(insuranceCompanyment) && reserveTypeCode != 'QT') { // is not empty
		errorAlert('차량', '자차 고객부담금은 필수 선택값 입니다.');
		return;
	}
	let rtIdxSplit = $('#companyName option:selected').text().split('(');
	let companyName = rtIdxSplit[0];
//	alert(rtIdx+'\n'+mdIdx+'\n'+crIdx+'\n'+carDamageCover+'\n'+insuranceCompanyment);
	
	let rentFee = getPureText($('#rentFee').val());
	let insuranceFee = getPureText($('#insuranceFee').val());
	let discountFee = getPureText($('#discountFee').val());
	let deliveryFee = getPureText($('#deliveryFee').val());
	let addFee = getPureText($('#revaddFee').val());
	let paymentTotalAmount = getPureText($('#paymentTotalAmount').val());
	let paymentAmount = getPureText($('#paymentAmount').val());
	let carDeposit = getPureText($('#carDeposit').val());
	
//	alert(rentFee+'\n'+insuranceFee+'\n'+discountFee+'\n'+deliveryFee+'\n'+addFee+'\n'+paymentTotalAmount+'\n'+paymentAmount);
	if ((isEmpty(rentFee) || rentFee == '0' || CALCULABLE_MOTH == 'N') && reserveStatusCode == 'RS') { // is not empty
		errorAlert('결제', '요금계산을 먼저 실행하여 주세요.');
		return;
	}
	
	var req = {
			'rmIdx' : rmIdx
			,	'reserveStatusCode' : reserveStatusCode
			,	'reserveTypeCode' : reserveTypeCode
			,	'longTermYn' : longTermYn
			,	'urIdx' : urIdx
			,	'reserveUserName' : reserveUserName  
			,	'reserveUserEmail' : reserveUserEmail
			,	'reserveUserContact1' : reserveUserContact1
			,	'reserveUserBirthDay' : reserveUserBirthDay
			,	'reserveUserGender' : reserveUserGender
			,	'rentStartDay' : rentStartDay
			,	'rentStartTime' : rentStartTime
			,	'rentEndDay' : rentEndDay
			,	'rentEndTime' : rentEndTime
			,	'deliveryTypeCode' : deliveryTypeCode
			,	'deliveryAddr' : deliveryAddr
			,	'returnAddr' : returnAddr
			,	'crIdx' : crIdx
			,	'carTypeCode' : carTypeCode
			,	'rtIdx' : rtIdx
			,	'companyName' : companyName
			,	'carDeposit' : carDeposit
			,	'rentFee' : rentFee
			,	'insuranceFee' : insuranceFee
			,	'discountFee' : discountFee
			,	'deliveryFee' : deliveryFee
			,	'addFee' : addFee
			,	'paymentTotalAmount' : paymentTotalAmount
			,	'reserveChannel' : 'CA'
			,	'ulIdx1' : ulIdx1
			,	'firstDriverName' : firstDriverName
			,	'firstDriverGender' : firstDriverGender
			,	'firstDriverContact' : firstDriverContact
			,	'firstDriverBirthDay' : firstDriverBirthDay
			,	'firstDriverLicenseCode' : firstDriverLicenseCode
			,	'firstDriverLicenseNumber' : firstDriverLicenseNumber
			,	'firstDriverExpirationDate' : firstDriverExpirationDate
			,	'firstDriverLicenseIsDate' : firstDriverLicenseIsDate
			,	'ulIdx2' : ulIdx2
			,	'secondDriverName' : secondDriverName
			,	'secondDriverGender' : secondDriverGender
			,	'secondDriverContact' : secondDriverContact
			,	'secondDriverBirthDay' : secondDriverBirthDay
			,	'secondDriverLicenseCode' : secondDriverLicenseCode
			,	'secondDriverLicenseNumber' : secondDriverLicenseNumber
			,	'secondDriverExpirationDate' : secondDriverExpirationDate
			,	'secondDriverLicenseIsDate' : secondDriverLicenseIsDate
			,	'insuranceCompanyment' : insuranceCompanyment
			,	'carDamageCover' : carDamageCover
			,	'modId' : GLOBAL_LOGIN_USER_IDX
			,	'regId' : GLOBAL_LOGIN_USER_IDX
	}
//	var pp = JSON.stringify(req);
//	console.log(pp);
//	alert(pp);
	title = '예약정보 저장';
	text = '저장하시겠습니까?'
	icon = 'info';
	cancel_text = '취소하셨습니다.';
	save_type = CRUD_METHOD;

	call_before_save(title, text, icon, cancel_text, save_type, req);

}

//submit
function detailSubmit(save_type, req){
	let target = '';
	let method = '';

	if (isEmpty(save_type)) { // save_type
		errorAlert('API ERROR', 'Save Type이 존재하지 않습니다. 관리자에게 문의하세요');
		return;
	}

	if (isEmpty(req)) { // req array
		errorAlert('API ERROR', '전송가능한 파라메터가 존재하지 않습니다. 관리자에게 문의하세요');
		return;
	}
	
	if (CRUD_METHOD == 'insert') {
		target = 'insertReserveInfo';
		method = 'insert';
	} else if (CRUD_METHOD == 'update') {
		target = 'updateReserveInfo';
		method = 'update';
	}

	fn_callApi(method, target, req, function(response) {
		let res = response;
		
		// 200이라면 페이징을 구한다.
		if (res.code == 200) {

			RESERVE_STATUS = $("#sel_reserveStatusCode option:selected").val();
			if (res.data.result == 1) {
				swal("저장 성공", {icon : "success"});
				closeDetail();
				loadApi(drawTable, null, null);
			}else{
				let msg = res.data.reservemsg;
				errorAlert('저장 실패', msg);
			}
		} else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
			errorAlert('저장 실패', '관리자에게 문의하세요.');
		}
	});// end fn_callApi
	
}

/*
 * 보험선택시 기존 보험료와 
 */
function selectInsuranceInfo() {
	let crIdxIndex = $("#sel_modelName option:selected").index() - 1;
	let ciIdxinsuranceCompanyment = $("#sel_ciIdx option:selected").val();

	let carDamageCover = nullCheck(carListData[crIdxIndex].carDamageCover) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].carDamageCover);
	let insuranceCompanyment = nullCheck(carListData[crIdxIndex].insuranceCompanyment) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].insuranceCompanyment);
	let carDamageCover2 = nullCheck(carListData[crIdxIndex].carDamageCover2) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].carDamageCover2);
	let insuranceCompanyment2 = nullCheck(carListData[crIdxIndex].insuranceCompanyment2) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].insuranceCompanyment2);
	let carDamageCover3 = nullCheck(carListData[crIdxIndex].carDamageCover3) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].carDamageCover3);
	let insuranceCompanyment3 = nullCheck(carListData[crIdxIndex].insuranceCompanyment3) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].insuranceCompanyment3);
	let carDamageCover4 = nullCheck(carListData[crIdxIndex].carDamageCover4) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].carDamageCover4);
	let insuranceCompanyment4 = nullCheck(carListData[crIdxIndex].insuranceCompanyment4) == ''?'':objectConvertToPriceFormat(carListData[crIdxIndex].insuranceCompanyment4);
	
	if(ciIdxinsuranceCompanyment == insuranceCompanyment){
		$("#carDamageCover").val(carDamageCover);
		$("#insuranceCompanyment").val(insuranceCompanyment);
	}else if(ciIdxinsuranceCompanyment == insuranceCompanyment2){
		$("#carDamageCover").val(carDamageCover2);
		$("#insuranceCompanyment").val(insuranceCompanyment2);
	}else if(ciIdxinsuranceCompanyment == insuranceCompanyment3){
		$("#carDamageCover").val(carDamageCover3);
		$("#insuranceCompanyment").val(insuranceCompanyment3);
	}else if(ciIdxinsuranceCompanyment == insuranceCompanyment4){
		$("#carDamageCover").val(carDamageCover4);
		$("#insuranceCompanyment").val(insuranceCompanyment4);
	}else{
		$("#carDamageCover").val('');
		$("#insuranceCompanyment").val('');
	}
	CALCULABLE_MOTH = 'N';
}

function calDate(pStartDate, pEndDate) {
	var strSDT = new Date(pStartDate.substring(0,4),pStartDate.substring(4,6)-1,pStartDate.substring(6,8));
	var strEDT = new Date(pEndDate.substring(0,4),pEndDate.substring(4,6)-1,pEndDate.substring(6,8));
	var strTermCnt = 0;

	var days = strEDT.getDate() - strSDT.getDate();
	var months = strEDT.getMonth() - strSDT.getMonth();
	var years = strEDT.getFullYear() - strSDT.getFullYear();


	strTermCnt = years * 12 + months + (days >= 0 ? 0 : -1);

	return strTermCnt;
}
/* =========================== detail function start ======================================*/


$("#reserveMasterInfo").iziModal({
	radius: 5,
	padding: 20,
	closeButton: false,
	overlayClose: false,
	width: 1000,    
	height :'auto',     
	overflow : 'scroll', 
	title: '예약상세',
	headerColor: '#002e5b',
    backdrop: 'static',
    keyboard: false,
    subtitle : ' ',   
    group:'groupA',
    loop : true,
    navigateCaption: false,
    navigateArrows: false,
    onClosing: function(){
    	closeDetail();
    }
}); 

$("#reserveMasterInfo2").iziModal({  
	radius: 5,
	padding: 20,
	closeButton: true,
	overlayClose: false,
	width: 1000,    
	height :'auto',     
	overflow : 'scroll', 
	title: '예약금액수정',  
	subtitle : ' ',  
	headerColor: '#002e5b',
    backdrop: 'static',
    keyboard: false,
    group:'groupA',
    loop : true,
    navigateCaption: false,
    navigateArrows: false,
    onClosing: function(){
    	closeDetail();
    } 
}); 
 
//모달창 닫기  전 상태 변경시 확인 창 출력
function closeDetail(){
	CRUD_METHOD = '';
	let reserveStatusCode = $("#sel_reserveStatusCode option:selected").val();

	// 변경 상태 와 이전 상태가 틀리고 예약취소요청이 아니면 확인창
	if(reserveStatusCode != RESERVE_STATUS && reserveStatusCode != 'CR' && CRUD_METHOD != 'insert'){
		ConfirmAlert('예약상태', '예약상태가 변경되었습니다. 종료하시겠습니까?');
		return;
	}else{
		detailclose();
	}
}

//모달창 닫기  
function detailclose(){
	$("#sel_reserveStatusCode").val(RESERVE_STATUS).prop("selected", true);
	$("#reserveMasterInfo").iziModal('close');
	$("#reserveMasterInfo2").iziModal('close');  
}

function initRepatmentModal(_value){

	/*
	RC   |예약취소/대여취소
	CR   |취소요청
	*/
	let targetCode = [ 'CR' ];
	let str = nullCheck(_value);
	
	if( CRUD_METHOD == 'insert' ){
		
	}else{
		if( containsList(str , targetCode) ){
			$('.iziModal-navigate-next').trigger('click');
			
			$("#reserveMasterInfo2").find('input').each(function(){
				$(this).val('0');  
				// input 태그의 자동완성 기능 해제
				$(this).attr('autocomplete','off');  
			});

			initReserveCancelWait();
		}
	}

}

// 취소요청 모달창 start =========================================================================================================================================================================
/*
 * 취소요청시 팝업 초기화
 */
function initReserveCancelWait(){
	let _rmIdx = $("#rmIdx").val();
	
	let req = { 
			rmIdx : _rmIdx  
	};
	
	let target = 'reserveInfo';
	let method = 'select';

	// Detail정보 조회
	fn_callApi(method,target, req, function(res){  
		if( res.code == 200 ){
			let data = res.data.result[0];

			// 예약정보
			let rmIdx = nullCheck(data.rmIdx);

			// 대여금액 정보
			let rentFee = nullCheck(data.rentFee) == ''?'':objectConvertToPriceFormat(data.rentFee);
			let insuranceFee = nullCheck(data.insuranceFee) == ''?'':objectConvertToPriceFormat(data.insuranceFee);
			let discountFee = nullCheck(data.discountFee) == ''?'':objectConvertToPriceFormat(data.discountFee);
			let deliveryFee = nullCheck(data.deliveryFee) == ''?'':objectConvertToPriceFormat(data.deliveryFee);
			let addFee = nullCheck(data.addFee) == ''?'':objectConvertToPriceFormat(data.addFee);
			let paymentTotalAmount = nullCheck(data.paymentTotalAmount) == ''?'':objectConvertToPriceFormat(data.paymentTotalAmount);
			let paymentAmount = nullCheck(data.paymentAmount) == ''?'':objectConvertToPriceFormat(data.paymentAmount);

			$("#rp_rentFee").val(rentFee);
			$("#rp_insuranceFee").val(insuranceFee);
			$("#rp_discountFee").val(discountFee);
			$("#rp_deliveryFee").val(deliveryFee);
			$("#rp_addFee").val(addFee);
			$("#rp_paymentTotalAmount").val(paymentTotalAmount);
			$("#rp_paymentAmount").val(paymentAmount);
			$("#return_refundFee").val(paymentTotalAmount);
		}
		
	}); 	
	
}

/*
 * 취소요청 창 닫기
 */
function closeRepatmentDetail(){  
	$("#sel_reserveStatusCode option:selected").val(RESERVE_STATUS);  
	$("#reserveMasterInfo2").iziModal('close');  
	$("#reserveMasterInfo").iziModal('open');  
}

function returnFeeSetting(){ 

	var repatmentFee = 0; 
	$('.returnSum').each(function(){ 
		repatmentFee += Number(getPureText(nullCheck($(this).val())));  
	});
	
	$('.returnMinus').each(function(){
		repatmentFee -= Number(getPureText(nullCheck($(this).val())));  
	});
	
	var setCancelWaitFee = repatmentFee; 
	var setFee = Number(getPureText(nullCheck($('#rp_paymentTotalAmount').val()))) - setCancelWaitFee; 
	
	$('#return_paymentTotalAmount').val(objectConvertToPriceFormat(setCancelWaitFee));
	$('#return_paymentAmount').val(objectConvertToPriceFormat(setCancelWaitFee));
	$('#return_refundFee').val(objectConvertToPriceFormat(setFee));
	

}

function repatmentValidation(){
	swal({
		  title: "저장하시겠습니까?",
		  icon: "info",
		  buttons: true,
		  dangerMode: true,
		})
		.then(function(willSave) {
			if (willSave) { //ok
				repatmentSubmit();
			} else {
				return;
			}
		});
}

function repatmentSubmit(){

	let rmIdx = getPureText(nullCheck($('#rmIdx').val()));  
	let reserveStatusCode = 'CR';
	let paymentTotalAmount = getPureText(nullCheck($('#return_paymentTotalAmount').val())); 
	let deliveryFee = getPureText(nullCheck($('#return_deliveryFee').val()));
	let insuranceFee = getPureText(nullCheck($('#return_insuranceFee').val()));
	let rentFee = getPureText(nullCheck($('#return_rentFee').val()));
	let addFee = getPureText(nullCheck($('#return_addFee').val()));
	let discountFee = getPureText(nullCheck($('#return_discountFee').val()));
	let cancelFee = getPureText(nullCheck($('#return_cancelFee').val())); 
	let paymentAmount = getPureText(nullCheck($('#return_paymentAmount').val()));  
	let refundFee = getPureText(nullCheck($('#return_refundFee').val())); 

	if(isEmpty(rmIdx)){
		errorAlert('수정', '수정시 정보가 부족합니다.');
		return;
	}	
	
	let param = {
			'rmIdx' : rmIdx
			,'reserveStatusCode' : reserveStatusCode
			,'paymentTotalAmount' : paymentTotalAmount
			,'deliveryFee' : deliveryFee
			,'insuranceFee' : insuranceFee
			,'rentFee' : rentFee
			,'addFee' : addFee
			,'discountFee' : discountFee
			,'cancelFee' : cancelFee
			,'refundFee' : refundFee
	};
	
	let _target = 'updateReserveInfo';
	let _method = 'update'; 
	
	fn_callApi(_method, _target, param, function(response) {
		let res = response;
		
		// 200이라면 페이징을 구한다.
		if (res.code == 200) {

			if (res.data.result == 1) {
				swal("저장 성공", {icon : "success"});
				closeDetail();
				loadApi(drawTable, null, null);
			}
		} else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
			errorAlert('저장 실패', '관리자에게 문의하세요.');
		}
	});// end fn_callApi


}
//취소요청 모달창 end =========================================================================================================================================================================

//날짜 마스킹 처리
Array.prototype.forEach.call(document.body.querySelectorAll("*[data-mask]"), applyDataMask);
function applyDataMask(field) {
 var mask = field.dataset.mask.split('');

 // For now, this just strips everything that's not a number
 function stripMask(maskedData) {
     function isDigit(char) {
         return /\d/.test(char);
     }
     return maskedData.split('').filter(isDigit);
 }
 
 // Replace `_` characters with characters from `data`
 function applyMask(data) {
     return mask.map(function(char) {
         if (char != '_') return char;
         if (data.length == 0) return char;
         return data.shift();
     }).join('')
 }
 
 function reapplyMask(data) {
     return applyMask(stripMask(data));
 }
 
 function changed() {   
     var oldStart = field.selectionStart;
     var oldEnd = field.selectionEnd;
     
     field.value = reapplyMask(field.value);
     CALCULABLE_MOTH = 'N';
     field.selectionStart = oldStart;
     field.selectionEnd = oldEnd;
 }
 
 field.addEventListener('click', changed)
 field.addEventListener('keyup', changed)
};
