/*
 * regCar.js
 * 차량 > 등록차량
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

/* 강제 */
var GLOBAL_LOGIN_USER_ROLE = 'RA';
var GLOBAL_LINK_RTIDX = '0N31TsXt';

var MODAL_NAME = 'regCarDetail';
var MODAL_TITLE = '등록차량 상세';
var MODAL_WIDTH = 1200;
var MODAL_HEIGHT = 1500;
var CURRENT_PAGE = 0;
var insuranceData = [];			// 보험 select box 배열
var planData = [];				// 기본요금 select box 배열
var carmodeldetaildata = [];	// 차량상세모델 select box 배열
var CRUD_METHOD = '';			// 저장 구분자
var calinit = 0;

function initializingPageData(){
	loadApi(drawTable, null, null);
	initSelectBox();
	bindEvent();

	/*
	 * IE 브라우저인경우 data-mask="____-__-__ __:__" 사용
	 * 이외 부라우저인 경우 type="datetime-local" 사용
	 */
	var browse = navigator.userAgent.toLowerCase();
	if( (navigator.appName === 'Netscape' && browse.indexOf('trident') !== -1) || (browse.indexOf("msie") !== -1)) {
		$("input[name=calRentStartDt2]").remove();
		$("input[name=calRentEndDt2]").remove();
	}else{
		$("input[name=calRentStartDt]").remove();
		$("input[name=calRentEndDt]").remove();
	}

}

function loadApi (fnc, page, displayPageNum, division) {

	calinit = 0;

	let searchSelectBox = $("#searchSelectBox option:selected").val();
	let strSearchKeyWord = $("#searchKeyWord").val();
	let showContents = $("#showContents option:selected").val();
	showContents = isEmpty(showContents) ? 10 : showContents;

	CURRENT_PAGE = parseInt(page);
	displayPageNum = parseInt(displayPageNum);

	CURRENT_PAGE = isNaN(page) ? 1 : (typeof page === 'number') ? page : 1;
	displayPageNum = isNaN(displayPageNum) ? showContents: (typeof displayPageNum === 'number') ? displayPageNum : showContents;

	let gbnReserveStatus = $("#gbnReserveStatus option:selected").val();

	if(!isEmpty(showContents)) {
		displayPageNum = Number(showContents);
	}

	let _rtIdx = '';

	if(GLOBAL_LOGIN_USER_ROLE !== 'RA'){
		_rtIdx = GLOBAL_LOGIN_RT_IDX;
	}

	// 회원사 링크 버튼 클릭 후 변수 사용
	if(!isEmpty(GLOBAL_LINK_RTIDX)){
		_rtIdx = GLOBAL_LINK_RTIDX;
		GLOBAL_LINK_RTIDX = "";
	}

	let req = {
		'page' 		     	: CURRENT_PAGE
		,'displayPageNum' 	: displayPageNum
		,'rtIdx' 			: _rtIdx
		,'userRole' 		: GLOBAL_LOGIN_USER_ROLE
		,'searchType' 	 	: searchSelectBox
		,'searchKeyWord'    : strSearchKeyWord
		,'reserveStatus'	: gbnReserveStatus
	};


	let target = 'regCarInfo';
	let method = 'select';

	fn_callApi(method, target, req, function (response) {
		let res = response;

		//200이라면 페이징을 구한다.
		// if(res.code == 200) {
		fnc(res.data, page, displayPageNum, division);
		// }else { //200이 아닐때 empty처리 error처리 등을 기록한다.
		// 	errorAlert('조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
		// }
	});//end fn_callApi

}

var drawTable = function drawTable(res, page, displayPageNum){

	page = parseInt(page);
	displayPageNum = parseInt(displayPageNum);

	page = isNaN(page) ? 1 : (typeof page === 'number') ? page : 1;
	displayPageNum = isNaN(displayPageNum) ? 10 : (typeof displayPageNum === 'number') ? displayPageNum : 10;

	let data = res.result;


	let rows = [];
	let columns;

	columns = [

		{ "name": "rowNumber", "id" : "rowNum" ,"title": "No" , "visible": false },
		{ "name": "crIdx", "title": "등록차량번호" ,
			"formatter" : function(value, options, rowData){
				return '<a href="javascript:initDetailInfoSub(' + "'" +value + "'" +');"  >'+value+'</a>';
			}
		},
		{ "name": "companyName", "title": "회원사/지점" ,
			"formatter" : function(value, options, rowData){
				let displayText = '';
				let rowIndex = String((rowData.rowNumber)-1);
				rowIndex = rowIndex.charAt(rowIndex.length-1);
				var targetRowData = options.rows[rowIndex];

				branchName = targetRowData.branchName;
				companyName = targetRowData.companyName;

				if(!isEmpty(branchName)) {
					displayText = companyName + '<br>'+ branchName;
				} else {
					displayText = companyName;
				}

				return displayText;
			}
		},
		{ "name": "year", "title": "연식", "breakpoints": "xs"},
		{ "name": "modelName", "title": "모델", "breakpoints": "xs"},
		{ "name": "modelDetailName", "title": "모델상세", "breakpoints": "xs"},
		{ "name": "carNumber", "title": "차량번호", "breakpoints": "xs"},
		{ "name": "fuelName", "title": "연료", "breakpoints": "xs"},
		{ "name": "mileage", "title": "누적km", "breakpoints": "xs",
			"formatter" : function(value, options, rowData){

				let displayText = '';
				displayText = value + ' km';
				return displayText;
			}
		},
		{ "name": "dailyStandardPay", "title": "일 기본요금", "breakpoints": "xs sm",
			"formatter" : function(value, options, rowData){
				let displayText = '';
				displayText += objectConvertToPriceFormat(value);
				return displayText + ' 원';
			}
		},
		{ "name": "monthlyStandardPay", "title": "월 기본요금", "breakpoints": "xs sm md" ,
			"formatter" : function(value, options, rowData){
				let displayText = '';
				let dailyStandardPay = value;

				displayText = objectConvertToPriceFormat(value);

				return displayText + ' 원';
			}
		},
		{ "name": "", "title": "상태", "breakpoints": "xs sm md",
			"formatter" : function(value, options, rowData){
				let displayText = '';
				let rowIndex = String((rowData.rowNumber)-1);
				rowIndex = rowIndex.charAt(rowIndex.length-1);
				let targetRowData = options.rows[rowIndex];

				let reserveStatus = targetRowData.reserveStatus;

				if(reserveStatus == 'UnableReserve'){
					displayText = '예약불가';
				}else if(reserveStatus == 'booking'){
					displayText = '예약중';
				}else if(reserveStatus == 'rantal'){
					displayText = '대여중';
				}else{
					displayText = '대기';
				}

				return displayText;
			}//end formatter
		},
		{ "name": "", "title": "일대여", "breakpoints": "xs sm md", "style":{"width":250,"maxWidth":250},
			"formatter" : function(value, options, rowData){
				let displayText = '';
				let rowIndex = String((rowData.rowNumber)-1);
				rowIndex = rowIndex.charAt(rowIndex.length-1);
				let targetRowData = options.rows[rowIndex];

				if(targetRowData.dailyYn == 'Y'){
					displayText = '<button class="btn btn-primary" type="button" id="d' +targetRowData.crIdx +'" onclick="javascript:ddRent(' + "'" + targetRowData.crIdx + "'" +');">일대여</button>&nbsp;' ;
				}else{
					displayText = '<button class="btn btn-secondary" type="button" id="d' +targetRowData.crIdx +'" onclick="javascript:ddRent(' + "'" + targetRowData.crIdx + "'" +');">일대여</button>&nbsp;' ;
				}
				if(targetRowData.monthlyYn == 'Y'){
					displayText += '<button class="btn btn-primary" type="button" id="m' +targetRowData.crIdx +'" onclick="javascript:mmRent(' + "'" + targetRowData.crIdx + "'" +');">월대여</button>&nbsp;' ;
				}else{
					displayText += '<button class="btn btn-secondary" type="button" id="m' +targetRowData.crIdx +'" onclick="javascript:mmRent(' + "'" + targetRowData.crIdx + "'" +');">월대여</button>&nbsp;' ;
				}
//				displayText += '<button class="btn btn-info" type="button" id="s' +targetRowData.crIdx +'" onclick="javascript:carsuspend(' + "'" + targetRowData.crIdx + "'" + ');">휴차일</button>' ;

				if(targetRowData.suspendCnt > 0){
					displayText += '<button class="btn btn-danger" type="button" id="s' +targetRowData.crIdx +'" onclick="javascript:carsuspend(' + "'" + targetRowData.crIdx + "'" + ');">휴차일</button>' ;
				}else{
					displayText += '<button class="btn btn-info" type="button" id="s' +targetRowData.crIdx +'" onclick="javascript:carsuspend(' + "'" + targetRowData.crIdx + "'" + ');">휴차일</button>' ;
				}

				return displayText;
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

	//page는 전역변수 사용
	let prev = res.paging.prev;
	let next = res.paging.next;

	makePaging(totalCnt, perPageNum, showDisplayPageNum, page, prev, next, $("#page"));

	if(!isEmpty(totalCnt)) {
		$('#totalRowCount').text('총 [' + totalCnt + '] 건이 검색되었습니다.');
	} else {
		$("#totalRowCount").text('총 [0] 건이 검색되었습니다.');
	}
}

function initSelectBox(){

	let searchOption = '';
	let countOption = '';
	let reserveStatusOption = '';
	let selInsuranceFeeOption = '';

	searchOption += '<option value="">선택</option>';
	searchOption += '<option value="companyName">회원사</option>';
	searchOption += '<option value="branchName">지점</option>';
	searchOption += '<option value="modelName">모델</option>';
	searchOption += '<option value="carNumber">차량번호</option>';

	countOption += '<option value="10">10개씩 보기</option>';
	countOption += '<option value="20">20개씩 보기</option>';
	countOption += '<option value="30">30개씩 보기</option>';
	countOption += '<option value="40">60개씩 보기</option>';

	reserveStatusOption += '<option value="">선택</option>';
	reserveStatusOption += '<option value="rantal">대여중</option>';
	reserveStatusOption += '<option value="booking">예약중</option>';
	reserveStatusOption += '<option value="waiting">대기</option>';
	reserveStatusOption += '<option value="UnableReserve">예약불가</option>';

	$('#showContents').append(countOption);
	$('#searchSelectBox').append(searchOption);
	$('#gbnReserveStatus').append(reserveStatusOption);

	$("#searchSelectBox option:eq(0)").prop("selected", true);
	$("#showContents option:eq(0)").prop("selected", true);


	selInsuranceFeeOption += '<option value="">선택하세요</option>';
	$('#selInsuranceFee').append(selInsuranceFeeOption);
}

function bindEvent(){

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

/*
 * 모아보기 선택시
 */
function changeList() {
	loadApi(drawTable, null, null);
}


$("#searchKeyWord").keypress(function(e) {
	let searchKeyWord = $("#searchKeyWord").val();
	let searchSelectBox = $("#searchSelectBox option:selected").val();


	if (e.keyCode === 13){
		if(isEmpty(searchSelectBox)) {
			loadApi(drawTable, null, null);
		} else {
			if(isEmpty(searchKeyWord)) {
				errorAlert('검색어', '검색어를 입력해 주세요.');
			}  else {
				loadApi(drawTable, null, null);
			}
		}
	}

});

$('#btnSearch').click(function(e){
	let searchKeyWord = $("#searchKeyWord").val();
	let searchSelectBox = $("#searchSelectBox option:selected").val();

	if(isEmpty(searchSelectBox)) {
		loadApi(drawTable, null, null);
	} else {
		if(isEmpty(searchKeyWord)) {
			errorAlert('검색어', '검색어를 입력해 주세요.');
		}  else {
			loadApi(drawTable, null, null);
		}
	}
});


$('#btnExcelDown').click(function(){

	let todayDate = moment().format("YYYY-MM-DD HH:MM");
	let fileTitle = "등록차량"  + todayDate;
	let sheetName = "등록차량"  + todayDate;

	fn_ExcelReport("listTable",fileTitle, sheetName);
});


/* =========================== detail function start ======================================*/

function initDetailInfoSub(seq){
	CRUD_METHOD = 'update';
	initDetailInfo(seq);
}

// Modal Detail Start
function initDetailInfo(seq){

//	swal("상세화면은 순차적으로 오픈할 예정입니다.", { icon: "warning", });
//	if(true){
//		return;
//	}

	// input box 초기화
	initInput();

	let _crIdx = seq;

	_crIdx = seq;


	let req = {
		crIdx : _crIdx
	};

	let target = 'regCarDetail';
	let method = 'select';

	fn_callApi(method,target, req, function(response) {
		let data = response;

		// 200이라면 페이징을 구한다.
		// if (res.code === 200) {
		// 	let data = res.data.result[0];

		let rtIdx 					= data.rtIdx              ; //제휴사idx
		let crIdx 					= data.crIdx			  ; //차량idx
		let companyName 			= data.companyName        ; //회사명
		let branchName 				= data.branchName         ; //지점명
		let year 					= data.year               ; //연식
		let mdIdx 					= data.mdIdx              ; //모델idx
		let modelName				= data.modelName		  ; //모델명
		let modelDetailName 		= data.modelDetailName    ; //모델상세명
		let fuelCode 				= data.fuelCode           ; //연료구분code
		let carRegDt 				= data.carRegDt           ; //차량등록일
		let colorName 				= data.colorName          ; //색상
		let carNumber 				= data.carNumber          ; //차량번호
		let carChassisNumber 		= data.carChassisNumber   ; //차대번호
		let mileage 				= data.mileage            ; //주행거리
		let reserveAbleYn 			= data.reserveAbleYn      ; //차량예약가능여부code
		let delYn 					= data.delYn              ; //차량삭제여부
		let ageLimit 				= data.ageLimit           ; //대여연령제한(장기,단기)

		/*
                    // 차량 추가 정보
                    let transmissionCode 		= data.transmissionCode   ; //변속기구분code
                    let driveTypeCode 			= data.driveTypeCode      ; //구동방식구분code
                    let cartypeCode 			= data.cartypeCode        ; //차종code
                    let driveLicenseCode 		= data.driveLicenseCode   ; //면허구분code
                    let manufacturerCode 		= data.manufacturerCode   ; //제조사code
                    let displacement 			= data.displacement       ; //배기량
                    let maximumPassenger 		= data.maximumPassenger   ; //승차인원
                    let imgIdx 					= data.imgIdx             ; //이미지idx
        //			let carDriveLimit 			= data.carDriveLimit      ; //주행거리제한
        //			let garageAddr 				= data.garageAddr         ; //차고지주소
        //			let carEtc 					= data.carEtc             ; //비고
                    // 보험정보
                    let ciIdx 					= data.ciIdx              ; //보험정보idx
                    let personalCover 			= data.personalCover      ; //대인보상금액
                    let propertyDamageCover 	= data.propertyDamageCover; //대물보상금액
                    let onselfDamageCover 		= data.onselfDamageCover  ; //자손보상금액
                    let insuranceCompanyment 		= data.insuranceCompanyment ; //고객부담금(보험료)
                    let carDamageCover 			= data.carDamageCover     ; //자차보상금(면책금)
                    let insuranceCompanyment2 	= data.insuranceCompanyment2; //고객부담금2(보험료)
                    let carDamageCover2 		= data.carDamageCover2    ; //자차보상금2(면책금)
                    let insuranceCompanyment3 	= data.insuranceCompanyment3; //고객부담금3(보험료)
                    let carDamageCover3 		= data.carDamageCover3    ; //자차보상금3(면책금)
                    let insuranceCompanyment4 	= data.insuranceCompanyment4; //고객부담금4(보험료)
                    let carDamageCover4 		= data.carDamageCover4    ; //자차보상금4(면책금)
                    let carDamage1Yn 			= data.carDamage1Yn       ; //자차1사용유무
                    let carDamage2Yn 			= data.carDamage2Yn       ; //자차2사용유무
                    let carDamage3Yn 			= data.carDamage3Yn       ; //자차3사용유무
                    let carDamage4Yn 			= data.carDamage4Yn       ; //자차4사용유무
                    // 기본요금
                    let pyIdx 					= data.pyIdx              ; //요금idx
                    let dailyStandardPay 		= data.dailyStandardPay   ; //일기본요금
                    let dailyMaxRate 			= data.dailyMaxRate       ; //일대여최대할인율
                    let monthlyStandardPay 		= data.monthlyStandardPay ; //월기본요금
                    let monthlyMaxRate 			= data.monthlyMaxRate     ; //월대여최대할인율
                    let month3Deposit 			= data.month3Deposit      ; //3개월보증금
                    let month6Deposit 			= data.month6Deposit      ; //6개월보증금
                    let month9Deposit 			= data.month9Deposit      ; //9개월보증금
                    let month12Deposit 			= data.month12Deposit     ; //12개월보증금
                    let deliveryStandardPay 	= data.deliveryStandardPay; //배달기본요금
                    let deliveryAddPay 			= data.deliveryAddPay     ; //배달10KM단위추가요금
                    let deliveryMaxRate 		= data.deliveryMaxRate    ; //배달최대할인율
        */
		let dailyYn 				= data.dailyYn            ; //일대여사용유무
		let monthlyYn 				= data.monthlyYn          ; //월대여사용유무
		let closedvehicleYn 		= data.closedvehicleYn    ; //휴차일사용유무
		let perIdx 					= data.perIdx	          ; //기간요금idx

		if(!isEmpty(branchName)){
			companyName = companyName + "(" + nullCheck(branchName) + ")";
		}

		let companyNameItem = "<option selected value='" + rtIdx + "'>"+companyName+"</option>";
		$('#companyName').empty();
		$('#companyName').append(companyNameItem);
		$('#companyName').attr('disabled', true);

		$("#crIdx").val(crIdx);
		$("#carNumber").val(carNumber);
		$("#year").val(year);
		$("#colorName").val(colorName);
		$("#mileage").val(mileage);
		$("#carChassisNumber").val(carChassisNumber);
		$("#carRegDt").val(carRegDt);
		$("#ageLimit").val(ageLimit);

		$("#carNumber").attr('readonly', true);
		$("#carChassisNumber").attr('readonly', true);

//			$("#sel_fuel").val(fuelCode).prop("selected", true);
//			$("#transmissionCode").val(transmissionCode);
//			$("#driveTypeCode").val(driveTypeCode);
//			$("#cartypeCode").val(cartypeCode);
//			$("#driveLicenseCode").val(driveLicenseCode);
//			$("#manufacturerCode").val(manufacturerCode);
//			$("#displacement").val(displacement);
//			$("#maximumPassenger").val(maximumPassenger);
//			$("#carDriveLimit").val(carDriveLimit);


		/*
         * 회원사 select box
         * 연료  select box
         * 차종  select box
         * 차종상세  select box
         * 보험료템플릿 select box
         * 기본요금 select box
         */
		initDetailSelectBox(data);

		if(CRUD_METHOD !== 'insert'){
			openIziModal(MODAL_NAME);
		}

		CRUD_METHOD = 'update';

		// } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
		// 	errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
		//
		// }

	});

}

function initDetailSelectBox(_data){
	let target = '';
	let method = '';
	let req = {};
	let res;
	let data;
	let strOption = '';

	// 회원사  select box
	if (isEmpty(_data)){
		target = 'selectCompanyList';
		method = 'select';
		req = {};

		// 회사리스트
		fn_callApi(method, target, req, function(response) {
			data = response;
			// 200이라면 페이징을 구한다.
			// if (res.code === 200) {
			// 	data = res.data.result.result;
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
			$('#companyName').attr('disabled', false);
			// } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
			// 	errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
			// }
		});// end fn_callApi
	}

	if(_data !== '*'){
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
				$("#sel_fuel").val('0').prop("selected", true);

				if(!isEmpty(_data)){
					let fuelCode = _data.fuelCode           ; //연료구분code
					$("#sel_fuel").val(fuelCode).prop("selected", true);
				}
			}
		});

		// 차종  select box
		target = 'selectCarModelForSelectBox';
		method = 'select';

		fn_callApi(method, target, req, function (response) {
			data = response;

			// //200이라면 페이징을 구한다.
			// if(res.code == 200) {
			// 	data = res.data.result;

			strOption = "";
			strOption += "<option value = '0'>선택하세요</option>";

			for ( var i in data) {

				if (data[i].modelName) {
					strOption += "<option value = '" + data[i].modelName + "'>" + nullCheck(data[i].modelName) + "</option>";
				}
			}
			$('#sel_modelName').empty();
			$('#sel_modelName').append(strOption);
			$("#sel_modelName").val('0').prop("selected", true);

			if(!isEmpty(_data)){
				let modelName = _data.modelName; //모델명
				$("#sel_modelName").val(modelName).prop("selected", true);
				selectCarModelDetail(modelName, _data);
			}

			// }

		});//end fn_callApi

	}

	// 보험료템플릿 가져오기
	let rtIdx = $("#companyName option:selected").val();

	// 신규 차량 등록시 보험, 기본요금 select box 초기화
	if(isEmpty(rtIdx)){
		_data = null;
		rtIdx = '*';
	}

	target = 'insuranceTemplateinfoDetail';
	method = 'select';

	req = {
		rtIdx : rtIdx
	};

	insuranceData = [];

	fn_callApi(method, target, req, function(response) {
		data = response;

		// 200이라면 페이징을 구한다.
		// if (res.code === 200) {

		// data = res.data.result;
		insuranceData = data;

		strOption = "";
		strOption += "<option value = '0'>선택하세요</option>";

		for ( var i in data) {

			if (data[i].ciIdx) {
				strOption += "<option value = '" + data[i].ciIdx + "'>" + nullCheck(data[i].ciEtc) + "</option>";
			}
		}
		$('#sel_ciIdx').empty();
		$('#sel_ciIdx').append(strOption);

		if(!isEmpty(_data)){
			let ciIdx = _data.ciIdx; //템플릿
			if(isEmpty(ciIdx) || ciIdx == '0'){
				cancelData('saveInsurance');
			}else{
				setData('saveInsurance', _data);
			}
		}


		// } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
		// 	errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
		//
		// }
	});// end fn_callApi

	target = 'basicPlanDetail';
	method = 'select';

	req = {
		rtIdx : rtIdx
	};

	planData = [];

	fn_callApi(method, target, req, function(response) {
		let data = response;

		// 200이라면 페이징을 구한다.
		// if (res.code == 200) {
		// 	data = res.data.result;
		planData = data;

		strOption = "";
		strOption += "<option value = '0'>선택하세요</option>";

		for ( var i in data) {

			if (data[i].pyIdx) {
				strOption += "<option value = '" + data[i].pyIdx + "'>" + nullCheck(data[i].pyEtc) + "</option>";
			}
		}
		$('#sel_pyIdx').empty();
		$('#sel_pyIdx').append(strOption);

		if(!isEmpty(_data)){
			let pyIdx = _data.pyIdx; //템플릿
			if(isEmpty(pyIdx) || pyIdx == '0'){
				cancelData('savePaymentinfo');
			}else{
				setData('savePaymentinfo', _data);
			}
		}

		// } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
		// 	errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');

		// }
	});// end fn_callApi

}
/*
 * 차종상세 정보 가져오기
 */
function selectCarModelDetail(modelName, _data){

	// 차종상세  select box
	let target = 'selectCarModelDetailForSelectBox';
	let method = 'select';
	//연료
	let req = {
		modelName:modelName
	};

	fn_callApi(method, target, req, function (response) {
		let data = response;

		//200이라면 페이징을 구한다.
		// if(res.code == 200) {
		// 	let data = res.data.result;
		carmodeldetaildata = data;

		let strOption = "";
		strOption += "<option value = '0'>선택하세요</option>";

		for ( var i in data) {
			if (data[i].mdIdx) {
				strOption += "<option value = '" + data[i].mdIdx + "'>" + nullCheck(data[i].modelDetailName) + "</option>";
			}
		}
		$('#sel_modelDetailName').empty();
		$('#sel_modelDetailName').append(strOption);

		if(!isEmpty(_data)){
			let mdIdx = _data.mdIdx           ; //연료구분code

			if(isEmpty(mdIdx) || mdIdx == '0'){
				cancelData('saveCarinfo');
			}else{
				setData('saveCarinfo', _data);
				$("#sel_modelDetailName").val(mdIdx).prop("selected", true);
			}
		}

		// }

	});//end fn_callApi
}

/*
 * 차종 변경에 따른 차종 상세 호출
 */
function modelNamechange() {
	let modelName = $("#sel_modelName option:selected").val(); //모델명
	selectCarModelDetail(modelName, null);
}

/*
 * 차종 상세 변경에 따른 차종 정보 호출
 */
function modelDetailNamechange() {

	let mdIdxIndex = $("#sel_modelDetailName option:selected").index() - 1;	//모델상세 선택 index
	let modelName = $("#sel_modelName option:selected").val(); 		//모델명
	let mdIdx = $("#sel_modelDetailName option:selected").val(); 	//모델상세순번
	if(isEmpty(modelName) || modelName == '0'){
		errorAlert('차종을 선택하여 주세요');
		return;
	}else if(isEmpty(mdIdx)){
		errorAlert('차종 상세를 선택하여 주세요');
		return;
	}else if (mdIdx == '0'){
		$("#transmissionCode").val('');
		$("#driveTypeCode").val('');
		$("#cartypeCode").val('');
		$("#driveLicenseCode").val('');
		$("#manufacturerCode").val('');
		$("#displacement").val('');
		$("#maximumPassenger").val('');
//		$("#sel_fuel option:selected").val('0');
		return;
	}

	let data = carmodeldetaildata[mdIdxIndex];
	setData('saveCarinfo', data);
}
//보험료 select box 변경시
function ciIdxchange() {
	let ciIdxIndex = $("#sel_ciIdx option:selected").index() - 1;
	if(ciIdxIndex < 0){
		cancelData('saveInsurance');
		return;
	}else{
		setData('saveInsurance', insuranceData[ciIdxIndex]);
	}
}
// 기본요금 select box 변경시
function pyIdxchange(){
	let pyIdxIndex = $("#sel_pyIdx option:selected").index() - 1;
	if(pyIdxIndex < 0){
		cancelData('savePaymentinfo');
		return;
	}else{
		setData('savePaymentinfo', planData[pyIdxIndex]);
	}
}
// 회원사 select box 변경시
function rtIdxchange(){
	// select box 가져오기
	initDetailSelectBox('*');

}

function cancelData(cancel_type) {
	switch (cancel_type) {
		case 'saveCarinfo':		// 차량기본정보
			$("#transmissionCode").val('');
			$("#driveTypeCode").val('');
			$("#cartypeCode").val('');
			$("#driveLicenseCode").val('');
			$("#manufacturerCode").val('');
			$("#displacement").val('');
			$("#maximumPassenger").val('');
			break;

		case 'saveInsurance':	// 보험정보
			$('#personalCover').val('');		//대인보상금액
			$('#onselfDamageCover').val('');	//자손보상금액
			$('#propertyDamageCover').val('');	//대물보상금액

			$('#carDamageCover').val('');		//자차보상금액(면책금)
			$('#insuranceCompanyment').val('');	//고객부담금(보험료)
			$('#carDamageCover2').val('');		//자차보상금액2(면책금)
			$('#insuranceCompanyment2').val('');	//고객부담금2(보험료)
			$('#carDamageCover3').val('');		//자차보상금액3(면책금)
			$('#insuranceCompanyment3').val('');	//고객부담금3(보험료)
			$('#carDamageCover4').val('');		//자차보상금액4(면책금)
			$('#insuranceCompanyment4').val('');	//고객부담금4(보험료)
			$("input:radio[name='carDamage1Yn']:radio[value='Y']").prop('checked', true);
			$("input:radio[name='carDamage2Yn']:radio[value='Y']").prop('checked', true);
			$("input:radio[name='carDamage3Yn']:radio[value='Y']").prop('checked', true);
			$("input:radio[name='carDamage4Yn']:radio[value='Y']").prop('checked', true);
			$("#sel_ciIdx").val('0').prop("selected", true);

			break;
		case 'savePaymentinfo':	// 기본요금
			$('#dailyStandardPay').val('');
			$('#dailyMaxRate').val('');
			$('#monthlyStandardPay').val('');
			$('#monthlyMaxRate').val('');
			$('#month3Deposit').val('');
			$('#month6Deposit').val('');
			$('#month9Deposit').val('');
			$('#month12Deposit').val('');

			$('#deliveryStandardPay').val('');
			$('#deliveryAddPay').val('');
			$('#deliveryMaxRate').val('');
			$("#sel_pyIdx").val('0').prop("selected", true);

			break;
	}

}
/*
 * 데이터 셋팅
 */
function setData(set_type, _data){

	switch (set_type) {
		case 'saveCarinfo':	// 보험정보
			let transmissionCode 		= _data.transmissionCode   ; //변속기구분code
			let driveTypeCode 			= _data.driveTypeCode      ; //구동방식구분code
			let cartypeCode 			= _data.cartypeCode        ; //차종code
			let driveLicenseCode 		= _data.driveLicenseCode   ; //면허구분code
			let manufacturerCode 		= _data.manufacturerCode   ; //제조사code
			let displacement 			= _data.displacement       ; //배기량
			let maximumPassenger 		= _data.maximumPassenger   ; //승차인원
			//		let imgIdx 					= _data.imgIdx             ; //이미지idx
			//		let carDriveLimit 			= _data.carDriveLimit      ; //주행거리제한
			//		let ageLimit 				= _data.ageLimit           ; //대여연령제한(장기,단기)
			//		let garageAddr 				= _data.garageAddr         ; //차고지주소
			//		let carEtc 					= _data.carEtc             ; //비고
			let fuelCode				= _data.fuelCode			 ; //연료코드

			$("#transmissionCode").val(transmissionCode);
			$("#driveTypeCode").val(driveTypeCode);
			$("#cartypeCode").val(cartypeCode);
			$("#driveLicenseCode").val(driveLicenseCode);
			$("#manufacturerCode").val(manufacturerCode);
			$("#displacement").val(displacement);
			$("#maximumPassenger").val(maximumPassenger);
			$("#sel_fuel").val(fuelCode).prop("selected", true);
			break;
		case 'saveInsurance':	// 보험정보
			let ciIdx                		= nullCheck(_data.ciIdx);
			let onselfDamageCover           = nullCheck(_data.onselfDamageCover) == '' ? '' : objectConvertToPriceFormat(nullCheck(_data.onselfDamageCover));;
			let personalCover               = nullCheck(_data.personalCover) == '' ? '' : objectConvertToPriceFormat(nullCheck(_data.personalCover));;
			let propertyDamageCover     	= nullCheck(_data.propertyDamageCover) == '' ? '' : objectConvertToPriceFormat(nullCheck(_data.propertyDamageCover));;
			let carDamageCover              = nullCheck(_data.carDamageCover) == '' ? '' : objectConvertToPriceFormat(nullCheck(_data.carDamageCover));
			let insuranceCompanyment          = nullCheck(_data.insuranceCompanyment) == '' ? '' : objectConvertToPriceFormat(nullCheck(_data.insuranceCompanyment));
			let carDamageCover2             = nullCheck(_data.carDamageCover2) == '' ? '' : objectConvertToPriceFormat(nullCheck(_data.carDamageCover2));
			let insuranceCompanyment2         = nullCheck(_data.insuranceCompanyment2) == '' ? '' : objectConvertToPriceFormat(nullCheck(_data.insuranceCompanyment2));
			let carDamageCover3             = nullCheck(_data.carDamageCover3) == '' ? '' : objectConvertToPriceFormat(nullCheck(_data.carDamageCover3));
			let insuranceCompanyment3         = nullCheck(_data.insuranceCompanyment3) == '' ? '' : objectConvertToPriceFormat(nullCheck(_data.insuranceCompanyment3));
			let carDamageCover4             = nullCheck(_data.carDamageCover4) == '' ? '' : objectConvertToPriceFormat(nullCheck(_data.carDamageCover4));
			let insuranceCompanyment4         = nullCheck(_data.insuranceCompanyment4) == '' ? '' : objectConvertToPriceFormat(nullCheck(_data.insuranceCompanyment4));
			let carDamage1Yn                = nullCheck(_data.carDamage1Yn) == '' ? 'N' : nullCheck(_data.carDamage1Yn);
			let carDamage2Yn                = nullCheck(_data.carDamage2Yn) == '' ? 'N' : nullCheck(_data.carDamage2Yn);
			let carDamage3Yn                = nullCheck(_data.carDamage3Yn) == '' ? 'N' : nullCheck(_data.carDamage3Yn);
			let carDamage4Yn                = nullCheck(_data.carDamage4Yn) == '' ? 'N' : nullCheck(_data.carDamage4Yn);

			$('#personalCover').val(personalCover);				//대인보상금액
			$('#onselfDamageCover').val(onselfDamageCover);		//자손보상금액
			$('#propertyDamageCover').val(propertyDamageCover);	//대물보상금액

			$('#carDamageCover').val(carDamageCover);			//자차보상금액(면책금)
			$('#insuranceCompanyment').val(insuranceCompanyment);	//고객부담금(보험료)
			$('#carDamageCover2').val(carDamageCover2);			//자차보상금액2(면책금)
			$('#insuranceCompanyment2').val(insuranceCompanyment2);	//고객부담금2(보험료)
			$('#carDamageCover3').val(carDamageCover3);			//자차보상금액3(면책금)
			$('#insuranceCompanyment3').val(insuranceCompanyment3);	//고객부담금3(보험료)
			$('#carDamageCover4').val(carDamageCover4);			//자차보상금액4(면책금)
			$('#insuranceCompanyment4').val(insuranceCompanyment4);	//고객부담금4(보험료)

			// 자차1사용유무
			if(carDamage1Yn == 'Y') {
				$("input:radio[name='carDamage1Yn']:radio[value='Y']").prop('checked', true); // 선택하기
			} else {
				$("input:radio[name='carDamage1Yn']:radio[value='N']").prop('checked', true); // 선택하기
			}
			// 자차2사용유무
			if(carDamage2Yn == 'Y') {
				$("input:radio[name='carDamage2Yn']:radio[value='Y']").prop('checked', true); // 선택하기
			} else {
				$("input:radio[name='carDamage2Yn']:radio[value='N']").prop('checked', true); // 선택하기
			}
			// 자차3사용유무
			if(carDamage3Yn == 'Y') {
				$("input:radio[name='carDamage3Yn']:radio[value='Y']").prop('checked', true); // 선택하기
			} else {
				$("input:radio[name='carDamage3Yn']:radio[value='N']").prop('checked', true); // 선택하기
			}
			// 자차4사용유무
			if(carDamage4Yn == 'Y') {
				$("input:radio[name='carDamage4Yn']:radio[value='Y']").prop('checked', true); // 선택하기
			} else {
				$("input:radio[name='carDamage4Yn']:radio[value='N']").prop('checked', true); // 선택하기
			}

			$("#sel_ciIdx").val(ciIdx).prop("selected", true);
			break;
		case 'savePaymentinfo':	// 기본요금
			let pyIdx                = nullCheck(_data.pyIdx);
			let dailyStandardPay     = objectConvertToPriceFormat(nullCheck(_data.dailyStandardPay));
			let monthlyStandardPay   = objectConvertToPriceFormat(nullCheck(_data.monthlyStandardPay));
			let dailyMaxRate         = objectConvertToPriceFormat(nullCheck(_data.dailyMaxRate));
			let monthlyMaxRate       = objectConvertToPriceFormat(nullCheck(_data.monthlyMaxRate));
			let month3Deposit        = objectConvertToPriceFormat(nullCheck(_data.month3Deposit));
			let month6Deposit        = objectConvertToPriceFormat(nullCheck(_data.month6Deposit));
			let month9Deposit        = objectConvertToPriceFormat(nullCheck(_data.month9Deposit));
			let month12Deposit       = objectConvertToPriceFormat(nullCheck(_data.month12Deposit));
			let deliveryStandardPay  = objectConvertToPriceFormat(nullCheck(_data.deliveryStandardPay));
			let deliveryAddPay       = objectConvertToPriceFormat(nullCheck(_data.deliveryAddPay));
			let deliveryMaxRate      = objectConvertToPriceFormat(nullCheck(_data.deliveryMaxRate));

			$('#dailyStandardPay').val(dailyStandardPay);
			$('#dailyMaxRate').val(dailyMaxRate);
			$('#monthlyStandardPay').val(monthlyStandardPay);
			$('#monthlyMaxRate').val(monthlyMaxRate);
			$('#month3Deposit').val(month3Deposit);
			$('#month6Deposit').val(month6Deposit);
			$('#month9Deposit').val(month9Deposit);
			$('#month12Deposit').val(month12Deposit);

			$('#deliveryStandardPay').val(deliveryStandardPay);
			$('#deliveryAddPay').val(deliveryAddPay);
			$('#deliveryMaxRate').val(deliveryMaxRate);

			$("#sel_pyIdx").val(pyIdx).prop("selected", true);
			break;
	}

}
// validation
function detailValidation(save_type){
	let title, text, icon, cancel_text;
	let req = {};
	let target = '';
	let method = '';

	let _rtIdx 	= $("#companyName option:selected").val();
	let crIdx 	= $("#crIdx").val();

	if(save_type !== 'saveCarinfo' && CRUD_METHOD === 'insert'){
		errorAlert('차량정보', '차량정보를 먼저 저장해 주세요.');
		return;
	}
	if (!isEmpty(save_type)) { // is not empty
		switch (save_type) {
			case 'saveCarinfo':		// 차량기본정보
				let companyName 		= _rtIdx;
				let carNumber 			= $("#carNumber").val();
				let carChassisNumber 	= $("#carChassisNumber").val();
				let year 				= $("#year").val();
				let carRegDt 			= $("#carRegDt").val();
				let modelName 			= $("#sel_modelName").val();
				let modelDetailName 	= $("#sel_modelDetailName option:selected").text();
				let mdIdx 				= $("#sel_modelDetailName").val();
				let fuelCode			= $("#sel_fuel").val();
				let colorName 			= $("#colorName").val();
				let mileage 			= $("#mileage").val();
				let ageLimit 			= $("#ageLimit").val();

				let transmissionCode 	= $("#transmissionCode").val();
				let driveTypeCode 		= $("#driveTypeCode").val();
				let cartypeCode 		= $("#cartypeCode").val();
				let driveLicenseCode 	= $("#driveLicenseCode").val();
				let manufacturerCode 	= $("#manufacturerCode").val();
				let displacement 		= $("#displacement").val();
				let maximumPassenger 	= $("#maximumPassenger").val();

				// let regDt = 'zz';


				// let pp = companyName+'\n'+carNumber+'\n'+year+'\n'+carRegDt+'\n'+modelName+'\n'+modelDetailName+'\n'+mdIdx+'\n'+fuelCode+'\n'+colorName+'\n'+mileage+'\n'+ageLimit+'\n';
				// pp += crIdx+'\n'+transmissionCode+'\n'+driveTypeCode+'\n'+cartypeCode+'\n'+driveLicenseCode+'\n'+manufacturerCode+'\n'+displacement+'\n'+maximumPassenger;
				// alert(pp);
				if (isEmpty(companyName)) { // is not empty
					errorAlert('차량정보', '회원사명은 필수 입력값 입니다.\n\r회원사명 선택하여 주세요.');
					$('#companyName').focus();
					return;
				}
				if (isEmpty(carNumber)) { // is not empty
					errorAlert('차량정보', '차량번호는 필수 입력값 입니다.');
					return;
				}
//			if (isEmpty(carChassisNumber)) { // is not empty
//				errorAlert('차량정보', '차량대번호는 필수 입력값 입니다.');
//				return;
//			}
				if (isEmpty(year)) { // is not empty
					errorAlert('차량정보', '차량연식은 필수 입력값 입니다.');
					return;
				}
				if (isEmpty(carRegDt)) { // is not empty
					errorAlert('차량정보', '차량등록일은 필수 입력값 입니다.');
					return;
				}
				if (getPureText(carRegDt).length !== 6) { // is not empty
					errorAlert('차량정보', '차량등록일은 YYYY-MM 형식으로 입력하여 주세요.');
					return;
				}

				if (isEmpty(modelName)) { // is not empty
					errorAlert('차량정보', '차종은 필수 입력값 입니다.\n\차종을 선택하여 주세요.');
					return;
				}
				if (isEmpty(mdIdx) || mdIdx === '0') { // is not empty
					errorAlert('차량정보', '차종상세는 필수 입력값 입니다.\n\차종상세를 선택하여 주세요.');
					return;
				}
				if (isEmpty(fuelCode)) { // is not empty
					errorAlert('차량정보', '연료는 필수 입력값 입니다.\n\연료를 선택하여 주세요.');
					return;
				}
				if (isEmpty(colorName)) { // is not empty
					errorAlert('차량정보', '색상은 필수 입력값 입니다.');
					return;
				}
				if (isEmpty(mileage)) { // is not empty
					mileage = '0';
				}
				if (isEmpty(ageLimit)) { // is not empty
					ageLimit = '21';
				}

				req  = {};
				req = {
					'rtIdx' : _rtIdx,
					'crIdx' : crIdx,
					'carNumber' : carNumber,
					'carChassisNumber' : carChassisNumber,
					'year' : year,
					'carRegDt' : carRegDt,
					'modelName' : modelName,
					'modelDetailName' : modelDetailName,
					'mdIdx' : mdIdx,
					'fuelCode' : fuelCode,
					'colorName' : colorName,
					'mileage' : mileage,
					'ageLimit' : ageLimit,
					'transmissionCode' : transmissionCode,
					'driveTypeCode' : driveTypeCode,
					'cartypeCode' : cartypeCode,
					'driveLicenseCode' : driveLicenseCode,
					'manufacturerCode' : manufacturerCode,
					'displacement' : displacement,
					'maximumPassenger' : maximumPassenger,
					// 'regId' : GLOBAL_LOGIN_USER_IDX,
					// 'modId' : GLOBAL_LOGIN_USER_IDX
				};

				title = '차량정보 저장';
				text = '저장하시겠습니까?';
				icon = 'info';
				cancel_text = '취소하셨습니다.';

				call_before_save(title, text, icon, cancel_text, save_type, req);
				break;
			case 'saveInsurance':	// 보험정보
				let ciIdx 				= $("#sel_ciIdx option:selected").val();
				let personalCover 		= getPureText($('#personalCover').val());		//대인보상금액
				let onselfDamageCover 	= getPureText($('#onselfDamageCover').val());	//자손보상금액
				let propertyDamageCover = getPureText($('#propertyDamageCover').val());	//대물보상금액

				let carDamageCover 		= getPureText($('#carDamageCover').val());		//자차보상금액(면책금)
				let insuranceCompanyment 	= getPureText($('#insuranceCompanyment').val());	//고객부담금(보험료)
				let carDamageCover2 	= getPureText($('#carDamageCover2').val());		//자차보상금액2(면책금)
				let insuranceCompanyment2 = getPureText($('#insuranceCompanyment2').val());	//고객부담금2(보험료)
				let carDamageCover3 	= getPureText($('#carDamageCover3').val());		//자차보상금액3(면책금)
				let insuranceCompanyment3	= getPureText($('#insuranceCompanyment3').val());	//고객부담금3(보험료)
				let carDamageCover4 	= getPureText($('#carDamageCover4').val());		//자차보상금액4(면책금)
				let insuranceCompanyment4 = getPureText($('#insuranceCompanyment4').val());	//고객부담금4(보험료)

				let carDamage1Yn 		=  insuranceCompanyment === '' ? 'N' : $(':input:radio[name=carDamage1Yn]:checked').val();
				let carDamage2Yn 		=  insuranceCompanyment2 === '' ? 'N' : $(':input:radio[name=carDamage2Yn]:checked').val();
				let carDamage3Yn 		=  insuranceCompanyment3 === '' ? 'N' : $(':input:radio[name=carDamage3Yn]:checked').val();
				let carDamage4Yn 		=  insuranceCompanyment4 == '' ? 'N' : $(':input:radio[name=carDamage4Yn]:checked').val();

				if(isEmpty(personalCover)){
					errorAlert('책임보험', '대인은 필수 입력값 입니다.');
					return;
				}else if(isEmpty(propertyDamageCover)){
					errorAlert('책임보험', '대물은 필수 입력값 입니다.');
					return;
				}else if(isEmpty(onselfDamageCover)){
					errorAlert('책임보험', '자손은 필수 입력값 입니다.');
					return;
				}else if(isEmpty(insuranceCompanyment)){
					errorAlert('자차보험1', '자차 보험 요금/일은 필수 입력값 입니다.');
					return;
				}else if(isEmpty(carDamageCover)){
					errorAlert('자차보험1', '자차보험 면책금은 필수 입력값 입니다.');
					return;
				}else if(!isEmpty(insuranceCompanyment) && !$.isNumeric(insuranceCompanyment)){
					errorAlert('자차보험1', '자차 보험 요금/일 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(carDamageCover) && !$.isNumeric(carDamageCover)){
					errorAlert('자차보험1', '자차보험 면책금 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(insuranceCompanyment2) && !$.isNumeric(insuranceCompanyment2)){
					errorAlert('자차보험2', '자차 보험 요금/일 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(carDamageCover2) && !$.isNumeric(carDamageCover2)){
					errorAlert('자차보험2', '자차보험 면책금 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(insuranceCompanyment3) && !$.isNumeric(insuranceCompanyment3)){
					errorAlert('자차보험3', '자차 보험 요금/일 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(carDamageCover3) && !$.isNumeric(carDamageCover3)){
					errorAlert('자차보험3', '자차보험 면책금 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(insuranceCompanyment4) && !$.isNumeric(insuranceCompanyment4)){
					errorAlert('자차보험4', '자차 보험 요금/일 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(carDamageCover4) && !$.isNumeric(carDamageCover4)){
					errorAlert('자차보험4', '자차보험 면책금 숫자만 입력 가능합니다.');
					return;
				}

				if(isEmpty(ciIdx) || ciIdx === '0'){
					ciIdx = '';
				}else{
					// 템플릿 참조 비교하여 값이 변경 되었을시 ciIdx 참조 하지 않은것으로 판단
					let ciIdxIndex 				= $("#sel_ciIdx option:selected").index()-1;
					let ciYn = 'Y';
					if (nullCheck(insuranceData[ciIdxIndex].onselfDamageCover) != onselfDamageCover){
						ciYn = 'N';
					}else if (nullCheck(insuranceData[ciIdxIndex].personalCover) != personalCover){
						ciYn = 'N';
					}else if (nullCheck(insuranceData[ciIdxIndex].propertyDamageCover) != propertyDamageCover){
						ciYn = 'N';
					}else if (nullCheck(insuranceData[ciIdxIndex].carDamageCover) != carDamageCover){
						ciYn = 'N';
					}else if (nullCheck(insuranceData[ciIdxIndex].insuranceCompanyment) != insuranceCompanyment){
						ciYn = 'N';
					}else if (nullCheck(insuranceData[ciIdxIndex].carDamageCover2) != carDamageCover2){
						ciYn = 'N';
					}else if (nullCheck(insuranceData[ciIdxIndex].insuranceCompanyment2) != insuranceCompanyment2){
						ciYn = 'N';
					}else if (nullCheck(insuranceData[ciIdxIndex].carDamageCover3) != carDamageCover3){
						ciYn = 'N';
					}else if (nullCheck(insuranceData[ciIdxIndex].insuranceCompanyment3) != insuranceCompanyment3){
						ciYn = 'N';
					}else if (nullCheck(insuranceData[ciIdxIndex].carDamageCover4) != carDamageCover4){
						ciYn = 'N';
					}else if (nullCheck(insuranceData[ciIdxIndex].insuranceCompanyment4) != insuranceCompanyment4){
						ciYn = 'N';
					}

					if (ciYn === 'N'){
						ciIdx = '';
					}
					//insuranceData[ciIdxIndex]
				}

				req = {
					'rtIdx' : _rtIdx
					,	'ciIdx' : ciIdx
					,	'crIdx' : crIdx
					,	'personalCover' : personalCover
					,	'onselfDamageCover' : onselfDamageCover
					,	'propertyDamageCover' : propertyDamageCover
					,	'carDamageCover' : carDamageCover
					,	'insuranceCompanyment' : insuranceCompanyment
					,	'carDamageCover2' : carDamageCover2
					,	'insuranceCompanyment2' : insuranceCompanyment2
					,	'carDamageCover3' : carDamageCover3
					,	'insuranceCompanyment3' : insuranceCompanyment3
					,	'carDamageCover4' : carDamageCover4
					,	'insuranceCompanyment4' : insuranceCompanyment4
					,	'carDamage1Yn' : carDamage1Yn
					,	'carDamage2Yn' : carDamage2Yn
					,	'carDamage3Yn' : carDamage3Yn
					,	'carDamage4Yn' : carDamage4Yn
					// ,	'modId' : GLOBAL_LOGIN_USER_IDX
					// ,	'regId' : GLOBAL_LOGIN_USER_IDX
				}

				title = '보험정보 저장';
				text = '저장하시겠습니까?'
				icon = 'info';
				cancel_text = '취소하셨습니다.';

				call_before_save(title, text, icon, cancel_text, save_type, req);

				break;
			case 'savePaymentinfo':	// 기본요금정보
				let pyIdx 				= $("#sel_pyIdx option:selected").val();
				let dailyStandardPay 	= getPureText($('#dailyStandardPay').val());
				let dailyMaxRate 		= getPureText($('#dailyMaxRate').val());
				let monthlyStandardPay 	= getPureText($('#monthlyStandardPay').val());
				let monthlyMaxRate 		= getPureText($('#monthlyMaxRate').val());
				let month3Deposit 		= getPureText($('#month3Deposit').val());
				let month6Deposit 		= getPureText($('#month6Deposit').val());
				let month9Deposit 		= getPureText($('#month9Deposit').val());
				let month12Deposit 		= getPureText($('#month12Deposit').val());
				let deliveryStandardPay = getPureText($('#deliveryStandardPay').val());
				let deliveryAddPay 		= getPureText($('#deliveryAddPay').val());
				let deliveryMaxRate 	= getPureText($('#deliveryMaxRate').val());

				if(isEmpty(dailyStandardPay)){
					errorAlert('요금정보', '일 기본요금은 필수 입력값 입니다.');
					return;
				}else if(!isEmpty(dailyStandardPay) && !$.isNumeric(dailyStandardPay)){
					errorAlert('요금정보', '일 기본요금은 숫자만 입력 가능합니다.');
					return;
				}else if(isEmpty(dailyMaxRate)){
					errorAlert('요금정보', '일대여 최대 할인율은 필수 입력값 입니다.');
				}else if(!isEmpty(dailyMaxRate) && !$.isNumeric(dailyMaxRate)){
					errorAlert('요금정보', '일대여 최대 할인율은 숫자만 입력 가능합니다.');
					return;
				}else if(isEmpty(monthlyStandardPay)){
					errorAlert('요금정보', '월 기본요금은 필수 입력값 입니다.');
					return;
				}else if(!isEmpty(monthlyStandardPay) && !$.isNumeric(monthlyStandardPay)){
					errorAlert('요금정보', '월 기본요금은 숫자만 입력 가능합니다.');
					return;
				}else if(isEmpty(monthlyMaxRate)){
					errorAlert('요금정보', '월 최대 할인율은 필수 입력값 입니다.');
					return;
				}else if(!isEmpty(monthlyMaxRate) && !$.isNumeric(monthlyMaxRate)){
					errorAlert('요금정보', '월 최대 할인율은 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(month3Deposit) && !$.isNumeric(month3Deposit)){
					errorAlert('요금정보', '3개월 보증금은 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(month6Deposit) && !$.isNumeric(month6Deposit)){
					errorAlert('요금정보', '6개월 보증금은 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(month3Deposit) && !$.isNumeric(month9Deposit)){
					errorAlert('요금정보', '9개월 보증금은 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(month3Deposit) && !$.isNumeric(month12Deposit)){
					errorAlert('요금정보', '12개월 보증금은 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(month3Deposit) && !$.isNumeric(deliveryStandardPay)){
					errorAlert('요금정보', '배달기본요금은 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(month3Deposit) && !$.isNumeric(deliveryAddPay)){
					errorAlert('요금정보', '배달10KM단위추가요금은 숫자만 입력 가능합니다.');
					return;
				}else if(!isEmpty(month3Deposit) && !$.isNumeric(deliveryMaxRate)){
					errorAlert('요금정보', '배달최대할인율은 숫자만 입력 가능합니다.');
					return;
				}
				if(isEmpty(pyIdx) || pyIdx == '0'){
					pyIdx = '';
				}else{
					let pyIdxIndex 				= $("#sel_pyIdx option:selected").index()-1;
					let pyYn = 'Y';
					if(dailyStandardPay != nullCheck(planData[pyIdxIndex].dailyStandardPay)){
						pyYn = 'N';
					}else if(monthlyStandardPay != nullCheck(planData[pyIdxIndex].monthlyStandardPay)){
						pyYn = 'N';
					}else if(dailyMaxRate != nullCheck(planData[pyIdxIndex].dailyMaxRate)){
						pyYn = 'N';
					}else if(monthlyMaxRate != nullCheck(planData[pyIdxIndex].monthlyMaxRate)){
						pyYn = 'N';
					}else if(month3Deposit != nullCheck(planData[pyIdxIndex].month3Deposit)){
						pyYn = 'N';
					}else if(month6Deposit != nullCheck(planData[pyIdxIndex].month6Deposit)){
						pyYn = 'N';
					}else if(month9Deposit != nullCheck(planData[pyIdxIndex].month9Deposit)){
						pyYn = 'N';
					}else if(month12Deposit != nullCheck(planData[pyIdxIndex].month12Deposit)){
						pyYn = 'N';
					}else if(deliveryStandardPay != nullCheck(planData[pyIdxIndex].deliveryStandardPay)){
						pyYn = 'N';
					}else if(deliveryAddPay != nullCheck(planData[pyIdxIndex].deliveryAddPay)){
						pyYn = 'N';
					}else if(deliveryMaxRate != nullCheck(planData[pyIdxIndex].deliveryMaxRate)){
						pyYn = 'N';
					}

					if(pyYn == 'N'){
						pyIdx = '';
					}
				}
//
				req = {
					'rtIdx' : _rtIdx
					,	'pyIdx' : pyIdx
					,	'crIdx' : crIdx
					,	'dailyStandardPay' : dailyStandardPay
					,	'dailyMaxRate' : dailyMaxRate
					,	'monthlyStandardPay' : monthlyStandardPay
					,	'monthlyMaxRate' : monthlyMaxRate
					,	'month3Deposit' : month3Deposit
					,	'month6Deposit' : month6Deposit
					,	'month9Deposit' : month9Deposit
					,	'month12Deposit' : month12Deposit
					,	'deliveryStandardPay' : deliveryStandardPay
					,	'deliveryAddPay' : deliveryAddPay
					,	'deliveryMaxRate' : deliveryMaxRate
					,	'modId' : GLOBAL_LOGIN_USER_IDX
					,	'regId' : GLOBAL_LOGIN_USER_IDX
				}

				title = '기본요금제 저장';
				text = '저장하시겠습니까?'
				icon = 'info';
				cancel_text = '취소하셨습니다.';

				call_before_save(title, text, icon, cancel_text, save_type, req);

				break;
		}
	}
}

// submit
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

	switch (save_type) {
		case 'saveCarinfo':		// 차량정보
			if (CRUD_METHOD === 'insert') {
				target = 'insertDcCarInfo';
				method = 'insert';
			} else if (CRUD_METHOD === 'update') {
				target = 'updateDcCarInfo';
				method = 'update';
			}
			break;
		case 'saveInsurance':	// 보험정보
			target = 'updateDcCarInfo';
			method = 'update';
			break;
		case 'savePaymentinfo':	// 기본요금
			target = 'updateDcCarInfo';
			method = 'update';
			break;

	}// end switch

	fn_callApi(method, target, req, function(response) {
		let data = response;

		// 200이라면 페이징을 구한다.
		// if (res.code == 200) {

		if (data.res === 1) {
			swal("저장 성공", {icon : "success"});

			switch (save_type) {
				case 'saveCarinfo':		// 차량정보
					if (CRUD_METHOD === 'insert'){
//						CRUD_METHOD = 'update';
						let crIdx = data.crIdx;
						$("#crIdx").val(crIdx);
						initDetailInfo(crIdx);
//						$("#"+MODAL_NAME).iziModal('close');
//						loadApi(drawTable, null, null);
					}
					break;
				case 'saveInsurance':	// 보험정보
//					loadApi(drawTable, null, null);
					break;
				case 'savePaymentinfo':	// 기본요금
//					loadApi(drawTable, null, null);
					break;
			}// end switch
		}
		// } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
		// 	errorAlert('저장 실패', '관리자에게 문의하세요.');
		// }
	});// end fn_callApi

}

//모달 오픈
function initDetailData(data){

	if(data === 'insert'){
		CRUD_METHOD = data;
	}else{
		CRUD_METHOD = '';
	}

	// 모델 상세 초기화
	let strOption = "";
	strOption += "<option value = '0'>선택하세요</option>";
	$('#sel_modelDetailName').empty();
	$('#sel_modelDetailName').append(strOption);

	// input 초기화
	initInput();

	// input readonly 해제
	$("#carNumber").attr('readonly', false);
	$("#carChassisNumber").attr('readonly', false);

	$("#companyName").val('').prop("selected", true);
	// select box 가져오기
	initDetailSelectBox(null);

	// 모달 오픈
	openIziModal(MODAL_NAME);

}

$("#" + MODAL_NAME).iziModal({
	radius: 5,
	padding: 20,
	closeButton: true,
	overlayClose: false,
	width: MODAL_WIDTH,
	height:MODAL_HEIGHT,
	title: MODAL_TITLE,
	headerColor: '#002e5b',
	backdrop: 'static',
	keyboard: false
});

/*
 * input box 초기화
 */
function initInput(){

	$("#" + MODAL_NAME).find('input:text').each(function(){
		$(this).val('');
		// input 태그의 자동완성 기능 해제
		$(this).attr('autocomplete','off');
	});

	$("#" + MODAL_NAME).find('select').each(function(){
		$(this).find('option').eq(0).prop('selected',true);
	});

}

function rentcal(){
	if(calinit === 0){
		errorAlert('요금계산', '요금 계산기 초기화를 먼저 실행하여 주세요.');
		return;
	}
	let crIdx = $("#crIdx").val();
	let calRentStartDt = $("#calRentStartDt").val().replace('____-__-__ __:__','').replace('T',' ');
	let calRentEndDt = $("#calRentEndDt").val().replace('____-__-__ __:__','').replace('T',' ');
	let selInsuranceFee = $("#selInsuranceFee").val();

	if(isEmpty(calRentStartDt)){
		errorAlert('요금계산', '대여일시를 입력해 주세요.');
		return;
	}
	if(isEmpty(calRentEndDt)){
		errorAlert('요금계산', '반납일시를 입력해 주세요.');
		return;
	}
	if(isEmpty(selInsuranceFee) || selInsuranceFee === '0'){
		errorAlert('요금계산', '자차보험을 선택해 주세요.');
		return;
	}

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

		// TODO response 200
		// if (res.code == 200) {
		// 	let data = res.data.result[0];

		let rentFee			= data.rentFee; 		//대여금
		let disRentFee		= data.disRentFee; 		//할인 후 대여금 -->
		let insuranceFee	= data.insuranceFee; 	//보험료 -->
//			let insuranceFee2	= data.insuranceFee2; 	//보험료2 -->
//			let insuranceFee3	= data.insuranceFee3; 	//보험료3 -->
//			let insuranceFee4	= data.insuranceFee4; 	//보험료4 -->
		let mmRentAmt		= data.mmRentAmt; 		//장기 월 대여요금 -->
		let mmLastRentAmt	= data.mmLastRentAmt; 	//장기 마지막월 대여요금 -->
		let commissionPer	= data.commissionPer; 	//회원사 수수료 -->
		let calcPeriodDt	= data.calcPeriodDt; 	//대여일수

		if(rentFee == '0' && insuranceFee == '0'){
			errorAlert('요금계산', '보험료정보 또는 기본요금 정보를 확인하세요.');
			return;
		}
		$("#calRentPeriod").val(calcPeriodDt);

		let calRentFee = '0';
		let calInsuranceFee = '0';
		let calRentTotAmount = '0';
		let calCarssumRate = '0';
		let calPaymentAmount = '0';

		if(mmRentAmt == 0){
			calRentFee = disRentFee;
			calInsuranceFee = insuranceFee;
//				if(selInsuranceFee == '1'){
//					calInsuranceFee = insuranceFee;
//				}else if(selInsuranceFee == '2'){
//					calInsuranceFee = insuranceFee2;
//				}else if(selInsuranceFee == '3'){
//					calInsuranceFee = insuranceFee3;
//				}else if(selInsuranceFee == '4'){
//					calInsuranceFee = insuranceFee4;
//				}
			calRentTotAmountEtc = '';
		}else{
			calRentFee = mmRentAmt;
			calInsuranceFee = '0';
			calRentTotAmountEtc = '월장기 총금액 : ' + objectConvertToPriceFormat(rentFee) + '원<br>';
			calRentTotAmountEtc += '월별 대여요금 : ' + objectConvertToPriceFormat(mmRentAmt) + '원 * 개월수';
			if(mmLastRentAmt >= 0){
				calRentTotAmountEtc += '<br>남은 일수의 대여요금 : ' + objectConvertToPriceFormat(mmLastRentAmt) + '원';
			}
		}

		calRentTotAmount = Number(calRentFee) + Number(calInsuranceFee);
		calCarssumRate = calRentTotAmount*(Number(commissionPer)/100);
		calPaymentAmount = calRentTotAmount - calCarssumRate;

		$("#calRentFee").val(objectConvertToPriceFormat(calRentFee));
		$("#calInsuranceFee").val(objectConvertToPriceFormat(calInsuranceFee));

		$("#calRentTotAmount").val(objectConvertToPriceFormat(calRentTotAmount));
		$("#calCarssumRate").val(objectConvertToPriceFormat(calCarssumRate));
		$("#calPaymentAmount").val(objectConvertToPriceFormat(calPaymentAmount));

		$("#calRentTotAmountEtc").empty();
		$("#calRentTotAmountEtc").append(calRentTotAmountEtc);


		// }
	});// end fn_callApi

}

function initcal(){

	if(CRUD_METHOD === 'insert'){
		errorAlert('차량정보', '차량정보를 먼저 저장해 주세요.');
		return;
	}
	calinit = calinit + 1;

	$("#calRentStartDt").val('');
	$("#calRentEndDt").val('');
	$("#calRentPeriod").val('');
	$("#selInsuranceFee").val('');
	$("#calRentFee").val('');
	$("#calInsuranceFee").val('');
	$("#calRentTotAmount").val('');
	$("#calCarssumRate").val('');
	$("#calPaymentAmount").val('');

	// 보험료 select box 생성
	let carDamageCover 		= getPureText($('#carDamageCover').val());		//자차보상금액(면책금)
	let insuranceCompanyment 	= getPureText($('#insuranceCompanyment').val());	//고객부담금(보험료)
	let carDamageCover2 	= getPureText($('#carDamageCover2').val());		//자차보상금액2(면책금)
	let insuranceCompanyment2 = getPureText($('#insuranceCompanyment2').val());	//고객부담금2(보험료)
	let carDamageCover3 	= getPureText($('#carDamageCover3').val());		//자차보상금액3(면책금)
	let insuranceCompanyment3	= getPureText($('#insuranceCompanyment3').val());	//고객부담금3(보험료)
	let carDamageCover4 	= getPureText($('#carDamageCover4').val());		//자차보상금액4(면책금)
	let insuranceCompanyment4 = getPureText($('#insuranceCompanyment4').val());	//고객부담금4(보험료)

	let strOption = "";
	strOption += "<option value = '0'>선택하세요</option>";

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

	$('#selInsuranceFee').empty();
	$('#selInsuranceFee').append(strOption);

	if(calinit === 1){
		swal("요금 계산기 준비가 완료 되었습니다.", { icon: "warning", });
	}
}

function ddRent(crIdx) {
	let dailyYn = 'Y';

	if($("#d"+crIdx).hasClass("btn btn-primary") == true){
		dailyYn = 'N';
	}else{
		dailyYn = 'Y';
	}

	let req = {
		'crIdx' : crIdx
		,	'dailyYn' : dailyYn
		,	'modId' : GLOBAL_LOGIN_USER_IDX
		,	'regId' : GLOBAL_LOGIN_USER_IDX
	}
	let target = 'updateDcCarInfo';
	let method = 'update';

	fn_callApi(method, target, req, function(response) {
		let res = response;

		// 200이라면 페이징을 구한다.
		// if (res.code === 200) {

		if (res.data.result == 1) {
			if($("#d"+crIdx).hasClass("btn btn-primary") == true){
				$("#d"+crIdx).attr('class','btn btn-secondary');
			}else{
				$("#d"+crIdx).attr('class','btn btn-primary');
			}
			swal("저장 성공", {icon : "success"});
		}
		// } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
		// 	errorAlert('저장 실패', '관리자에게 문의하세요.');
		// }
	});// end fn_callApi

}

function mmRent(crIdx) {
	let monthlyYn = 'Y';

	if($("#m"+crIdx).hasClass("btn btn-primary") == true){
		monthlyYn = 'N';
	}else{
		monthlyYn = 'Y';
	}

	let req = {
		'crIdx' : crIdx
		,	'monthlyYn' : monthlyYn
		,	'modId' : GLOBAL_LOGIN_USER_IDX
		,	'regId' : GLOBAL_LOGIN_USER_IDX
	}

	let target = 'updateDcCarInfo';
	let method = 'update';

	fn_callApi(method, target, req, function(response) {
		let res = response;

		// 200이라면 페이징을 구한다.
		// if (res.code == 200) {

		if (res.data.result == 1) {
			if($("#m"+crIdx).hasClass("btn btn-primary") == true){
				$("#m"+crIdx).attr('class','btn btn-secondary');
			}else{
				$("#m"+crIdx).attr('class','btn btn-primary');
			}
			swal("저장 성공", {icon : "success"});
		}
		// } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
		// 	errorAlert('저장 실패', '관리자에게 문의하세요.');
		// }
	});// end fn_callApi
}
function carsuspend(crIdx) {
	let suspendStartDt = $("#suspendStartDt").val();
	let suspendEndDt = $("#suspendEndDt").val();

	if(isEmpty(suspendStartDt)){
		errorAlert('휴차일 등록', '휴차일 시작일을 입력해 주세요.');
		return;
	}else if(isEmpty(suspendEndDt)){
		errorAlert('휴차일 등록', '휴차일 종료일을 입력해 주세요.');
		return;
	}else if(suspendStartDt > suspendEndDt){
		errorAlert('휴차일 등록', '휴차일 시작일이 종료일보다 클수 없습니다.');
		return;
	}

	let req = {
		'crIdx' : crIdx
		,	'suspendStartDt' : suspendStartDt
		,	'suspendEndDt' : suspendEndDt
		,	'modId' : GLOBAL_LOGIN_USER_IDX
		,	'regId' : GLOBAL_LOGIN_USER_IDX
	}

	let target = 'insertDcCarInfoSuspend';
	let method = 'insert';

	fn_callApi(method, target, req, function(response) {
		let res = response;

		// 200이라면 페이징을 구한다.
		// if (res.code == 200) {

		if (res.data.result == 1) {
			if($("#s"+crIdx).hasClass("btn btn-info") == true){
				$("#s"+crIdx).attr('class','btn btn-danger');
			}else{
				$("#s"+crIdx).attr('class','btn btn-info');
			}
			swal("저장 성공", {icon : "success"});
		}
		// } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
		// 	errorAlert('저장 실패', '관리자에게 문의하세요.');
		// }
	});// end fn_callApi
}

// 날짜 마스킹 처리
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

		field.selectionStart = oldStart;
		field.selectionEnd = oldEnd;
	}

	field.addEventListener('click', changed)
	field.addEventListener('keyup', changed)
}
//$("#calRentStartDt").keyup(function(e) {
//	let num = $("#calRentStartDt").val();
//	num = getOnlyNumber(num);
//
//	$("#calRentStartDt").val(num);
//});
//$("#calRentEndDt").keyup(function(e) {
//	let num = $("#calRentEndDt").val();
//	num = getOnlyNumber(num);
//
//	$("#calRentEndDt").val(num);
//});


/* =========================== detail function end ======================================*/