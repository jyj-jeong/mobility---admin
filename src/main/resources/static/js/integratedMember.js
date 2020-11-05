/*
 * integratedMember.js
 * 회원 > 통합회원
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


//모달설정
var MODEL_NAME = 'integratedMemberDetail';
var MODEL_TITLE = '회원통합 상세';
var MODEL_WIDTH = 1500;
//var UR_IDX = '';
var CRUD = '';
var CURRENT_PAGE = 0;

var GLOBAL_LOGIN_USER_ROLE = 'RA';
var GLOBAL_LOGIN_USER_IDX = 'ur';

function initializingPageData() {
    loadApi(null, null);
    initSelectBox();
    bindEvent();
}

function loadApi(page, displayPageNum, division) {

    let _rtIdx = '';

    if (GLOBAL_LOGIN_USER_ROLE != 'RA') {
        _rtIdx = GLOBAL_LOGIN_RT_IDX;
    }

    let req = {
    };

    let target = 'userInfoList';
    let method = 'select';


    fn_callApi(method, target, req, function(response) {
        let res = response;

        // 200이라면 페이징을 구한다.
        if (res.code === 200) {

        } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
            errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
            return;
        }
    });



    // end FindUserInfoList

}

function initSelectBox() {

    let searchOption = '';
    let countOption = '';

    searchOption += '<option value="" >선택</option>';
    searchOption += '<option value="urIdx" >회원번호</option>';
    searchOption += '<option value="userId" >아이디</option>';
    searchOption += '<option value="userName" >이름</option>';
    searchOption += '<option value="userBirthday" >생년월일</option>';
    searchOption += '<option value="userContact1" >연락처</option>';


    countOption += '<option value="10">10개씩 보기</option>';
    countOption += '<option value="20">20개씩 보기</option>';
    countOption += '<option value="30">30개씩 보기</option>';
    countOption += '<option value="60">60개씩 보기</option>';

    $('#showContents').append(countOption);
    $('#searchSelectBox').append(searchOption);

    $("#searchSelectBox option:eq(0)").prop("selected", true);
    $("#showContents option:eq(0)").prop("selected", true);

}

function initModalSelectBox(data) {

    let target = 'commonCodeInfo';
    let method = 'select';
    let req = {};

    let ulIdx 			= '';
    let userGender 		= '';
    let userRole 		= '';
    let licenseCode 	= '';
    let userStatusCode 	= '';

    if(CRUD =='modify') {
        if(data != null) {
            ulIdx 			= data.ulIdx;
            userGender 		= data.userGender;	   //성별
            userRole 		= data.userRole;	   //유져권한
            licenseCode 	= data.licenseCode;	   //면허코드
            userStatusCode 	= data.userStatusCode; //회원상태
        }
    }


    //START 성별셋팅============================================================
    req = {
        rtCode : "CCD",
        pCode : "GD"
    };

    fn_callApi(method, target, req, function(response) {
        let data = response;

        // 200이라면 페이징을 구한다.
        // if (res.code == 200) {

        // let data = res.data.result;

        let strOption = "";

        strOption += "<option value = '0'>선택</option>";

        for ( var i in data) {
            if (data[i].codeValue) {
                strOption += "<option value = '"+ data[i].code + "'>"+ data[i].codeValue + "</option>";
            }
        }

        $('#sel_userGender').empty();
        $('#sel_userGender').append(strOption);

        if(CRUD =='modify') {
            $("#sel_userGender").val(userGender).prop("sel_userGender", true);
        }

        // } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
        // 	errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
        // 	return;
        //
        // }
    });// end fn_callApi

    //END 성별셋팅	============================================================

    //START 권한셋팅============================================================
    req = {
        rtCode : "RL",
        pCode : "UR"
    };

    fn_callApi(method, target, req, function(response) {
        let data = response;

        // 200이라면 페이징을 구한다.
        // if (res.code == 200) {

        // let data = res.data.result;

        let strOption = "";

        strOption += "<option value = '0'>선택</option>";

        for ( var i in data) {
            if (data[i].codeValue) {
                strOption += "<option value = '" + data[i].code + "'>" + data[i].codeValue + "</option>";
            }
        }
        $('#sel_userRole').empty();
        $('#sel_userRole').append(strOption);

        if(CRUD =='modify') {
            $("#sel_userRole").val(userRole).prop("selected", true);
        }


        // } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
        // 	errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
        // 	return;
        //
        // }
    });// end fn_callApi

    //END 권한셋팅============================================================

    //START 면허정보============================================================
    req = {
        rtCode : "CR",
        pCode : "DL"
    };

    fn_callApi( method, target, req, function(response) {
        let data = response;

        // 200이라면 페이징을 구한다.
        // if (res.code == 200) {

        // let data = res.data.result;

        let strOption = "";

        strOption += "<option value = '0'>선택</option>";

        for ( var i in data) {
            if (data[i].codeValue) {
                strOption += "<option value = '" + data[i].code + "'>" + data[i].codeValue + "</option>";
            }
        }

        $('#sel_LicenseCode').empty();
        $('#sel_LicenseCode').append(strOption);
        if (ulIdx != null) {
            $("#sel_LicenseCode").val(licenseCode).prop("selected", true);
        }
//
//				// 면허종류 셋팅
//				if(CRUD =='modify') {
//					$("#sel_LicenseCode").val(licenseCode).prop("selected", true);
//					
//					licenseNumber = data.licenseNumber; // 면허번호
//					licenseExpiration = data.licenseExpiration; // 적성검사만료일
//					licenseIssueDt = data.licenseIssueDt; // 발급일
//					
//					$("#licenseNumber").val(licenseNumber);
//					$("#licenseExpiration").val(licenseExpiration);
//					$("#licenseIssueDt").val(licenseIssueDt);
//
//				} else {
//					$("#licenseNumber").val('');
//					$("#licenseExpiration").val('');
//					$("#licenseIssueDt").val('');
//				}
//
//
//			} else {
//
//				if(CRUD =='modify') {
//					$("#sel_LicenseCode").val(0).prop("selected", true);
//				}
//			}


        // } else {
        // 	errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
        // 	return;
        // }
    });// end fn_callApi
    //END 면허정보============================================================

    //START 회원상태============================================================
    req = {
        rtCode : "RL",
        pCode : "US"
    };

    fn_callApi(method, target, req, function(response) {
        let data = response;

        // 200이라면 페이징을 구한다.
        // if (res.code == 200) {

        // let data = res.data.result;

        let strOption = "";

        strOption += "<option value = '0'>선택</option>";

        for ( let i in data) {
            if (data[i].codeValue) {
                strOption += "<option value = '"+ data[i].code + "'>"+ data[i].codeValue + "</option>";
            }
        }
        $('#sel_userStatusCode').empty();
        $('#sel_userStatusCode').append(strOption);

        if(CRUD =='modify') {
            $("#sel_userStatusCode").val(userStatusCode).prop("selected", true);
        }

        // } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
        // 	errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
        // 	return;
        // }
    });// end fn_callApi
    //END 회원상태============================================================

}//end modal selectbox

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


/*
 * 모아보기 선택시
 */
function changeList() {
    loadApi(drawTable, null, null);
//	bindEvent();
}

$("#searchKeyWord").keypress(function(e) {
    if (!isEmpty(searchSelectBox)) {
        if (e.keyCode == 13) {
            fn_search();
        }
    }
});


$('#btnSearch').click(function(e) {
    fn_search();
});

function fn_search(){

    let searchKeyWord = $("#searchKeyWord").val();
    let searchSelectBox = $("#searchSelectBox option:selected").val();

    if(!isEmpty(searchSelectBox)){
        if (isEmpty(searchKeyWord)) {
            errorAlert('검색어', '검색어를 입력하세요.');
            return;
        } else {
            loadApi(drawTable, null, null);
        }
    }else{
        loadApi(drawTable, null, null);
    }

}

/* =========================== detail function start====================================== */

// init
function initDetailInfo(seq) {

//	swal("상세화면은 순차적으로 오픈할 예정입니다.", { icon: "warning", });
//	if(true){
//		return;
//	}

    let _urIdx = '';

//	if(!isEmpty(sessionStorage.getItem('seq'))) {
//		sessionStorage.removeItem('seq');
//	} else {
//		sessionStorage.setItem('seq', seq);
//		
//		seq = sessionStorage.getItem('seq');
//	}

    _urIdx = seq;

    let req = {
        urIdx : _urIdx
    };

    let target = 'userInfoListDetail';
    let method = 'select';

    // Detail정보 조회
    fn_callApi(method,target, req, function(response) {
        let res = response;

        // 200이라면 페이징을 구한다.
        if (res.code == 200) {

            var data = res.result[0];

            // 기본정보 셋팅
            let urIdx 			= data.urIdx;
            let userId 			= data.userId;
            let userName 		= data.userName;
            let joinChannel 	= data.joinChannel;
            let userBirthday 	= YMDFormatter(data.userBirthday);
            let userContact1 	= phoneFomatter(data.userContact1);
            let regDt 			= dateFormatter(data.regDt , "-");


            $("#urIdx").val(urIdx);
            $("#userId").val(userId);
            $("#userName").val(userName);


            if(joinChannel =='web') {
                $("#joinChannel").val(joinChannel);
            }

            $("#userContact1").val(userContact1);
            $("#userRegDt").val(regDt);

            initDatePicker('userBirthday' , userBirthday);
            // $("#userBirthday").val(userBirthday);

            initModalSelectBox(data);

        } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
            errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
        }
    });// end fn_callApi

//	UR_IDX = urIdx; 

    req = {
        urIdx : _urIdx
    };

    target = 'userLicenseInfo';
    method = 'select';

    // Detail정보 조회
    fn_callApi(method,target, req, function(response) {
        let res = response;

        // 200이라면 페이징을 구한다.
        if (res.code == 200) {

            var data = res.result[0];

            let licenseCode  		= '';// 면허코드
            let licenseNumber  		= '';// 운전면허 번호
            let licenseExpiration  	= '';// 적성검사만료일
            let licenseIssueDt  	= '';// 면허 발급일
            let userLicenseOwnYn 	= '';
            let licenseLocation     = '';// 면허지역
            let useYn				= '';// 사용여부

            if(CRUD =='modify') {
                if (data != null) {
                    licenseCode = data.licenseCode;
                    licenseNumber = data.licenseNumber;
                    licenseExpiration = data.licenseExpiration;
                    licenseIssueDt = data.licenseIssueDt;
                    userLicenseOwnYn = data.userLicenseOwnYn;
                    licenseLocation = data.licenseLocation;
                    useYn = data.useYn;

                }
                // }

//			$('#licenseCode').val(licenseCode);
                $('#licenseNumber').val(licenseNumber);
                $('#licenseExpiration').val(licenseExpiration);
                $('#licenseIssueDt').val(licenseIssueDt);

                //면허정보 조회
			initDatePicker('licenseExpiration' , licenseExpiration);
			initDatePicker('licenseIssueDt' , licenseIssueDt);

                //START 면허종류============================================================
                let target = 'commonCodeInfo';
                let method = 'select';
                let req = {};
                req = {
                    rtCode: "CR",
                    pCode: "DL"
                };

                fn_callApi(method, target, req, function (response) {
                    let data = response;

                    // 200이라면 페이징을 구한다.
                    // if (res.code == 200) {

                    // let data = res.data.result;

                    let strOption = "";

                    strOption += "<option value = '0'>선택</option>";

                    for (let i in data) {
                        if (data[i].codeValue) {
                            strOption += "<option value = '" + data[i].code + "'>" + data[i].codeValue + "</option>";
                        }
                    }
                    $('#sel_LicenseCode').empty();
                    $('#sel_LicenseCode').append(strOption);

                    $("#sel_LicenseCode").val(licenseCode).prop("selected", true);


                    // } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
                    // 	errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
                    // }
                });// end fn_callApi
                //END 면허종류============================================================


            } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
                errorAlert('API ERROR', '조회중 에러가 발생했습니다. \r\n 관리자에게 문의하세요.');
            }
        }
    });// end fn_callApi
    //START 면허종류============================================================

    // openIziModal(MODEL_NAME);

}//end initDetailInfo


// validation
function detailValidation(save_type){

    let req = {};
    let updateFlag = false;

    let title, text, icon, cancel_text;

    if(!isEmpty(save_type)) { // is not empty

//		let seq = sessionStorage.getItem('seq');
        let urIdx = $("#urIdx").val();


        switch (save_type) {

            case 'saveUser':// 회원 정보
                let userId 				= $("#userId").val();								//아이디
                let userName 			= $("#userName").val();								//이름
                let userContact1        = $("#userContact1").val();							//연락처
                let joinChannel 		= $("#joinChannel").val();							//계정타입
                let userBirthday 		= formatDate(getUserBirthday());      	            //생년월일
                let userRole 			= $("#sel_userRole option:selected").val();			//권한
                let sel_userStatusCode 	= $("#sel_userStatusCode option:selected").val();	//회원상태코드
                let userGender 			= $("#sel_userGender option:selected").val();		//성별

                if(isEmpty(userId)) { //is not empty
                    errorAlert('아이디', '아이디는 필수 입력값 입니다.');
                    $('#userId').focus();
                    return;
                }

                if(!isValidEmail(userId)) { //is not Valid
                    errorAlert('아이디', '아이디는 이메일 형식으로 입력해주세요.');
                    $('#userId').focus();
                    return;
                }

                if(isEmpty(userName)) { //is not empty
                    errorAlert('이름', '이름는 필수 입력값 입니다.');
                    $('#userName').focus();
                    return;
                }

                if(isEmpty(userBirthday)) { //is not empty
                    errorAlert('생년월일', '생년월일은 필수 입력값 입니다.');
                    return;
                }

                if(isEmpty(userContact1)) {
                    errorAlert('연락처', '연락처는 필수 입력값 입니다.');
                    return;
                }else {
                    userContact1 = removeHypen(userContact1);
                }

                if(isEmpty(userRole) || userRole == 0) { //is not empty
                    errorAlert('권한 미설정', '사용자 권한을 설정하세요.');
                    return;
                }

                save_type = 'saveUser';

                if(CRUD == 'modify') {
                    if(isEmpty(urIdx)) {
                        errorAlert('API ERROR', 'seq가 Null일 수 없습니다.');
                        return;
                    }
                    req = {
                        urIdx			     :  urIdx
                        ,  userId 				 :  userId
                        ,  userName 			 :  userName
                        ,	userContact1		 :  userContact1
                        ,  joinChannel 		 :  joinChannel
                        ,  userBirthday 		 :  userBirthday
                        ,  userRole 			 :  userRole
                        ,  userStatusCode 	 	 :  sel_userStatusCode
                        ,  userGender 			 :  userGender
                        ,	modId : GLOBAL_LOGIN_USER_IDX
                        ,	regId : GLOBAL_LOGIN_USER_IDX
                    };
                }else if (CRUD == 'insert') {
                    req = {
                        userId 				 :  userId
                        ,  userName 			 :  userName
                        ,	userContact1		 :  userContact1
                        ,  joinChannel 		 :  joinChannel
                        ,  userBirthday 		 :  userBirthday
                        ,  userRole 			 :  userRole
                        ,  userStatusCode 	 	 :  sel_userStatusCode
                        ,  userGender 			 :  userGender
                        // ,	modId : GLOBAL_LOGIN_USER_IDX
                        // ,	regId : GLOBAL_LOGIN_USER_IDX
                    };
                }


                title = '회원정보 저장';
                text = '저장하시겠습니까?'
                icon = 'info';
                cancel_text = '취소하셨습니다.';

                call_before_save(title, text, icon, cancel_text, save_type, req);
                break;
            case 'saveUserLicenseInfo': //면허정보

                let licenseCode  		= $("#sel_LicenseCode option:selected").val();	// 면허코드
                let licenseNumber  		= $("#licenseNumber").val();  					// 운전면허 번호
                let licenseExpiration  	= formatDate(getLicenseExpiration());  		    // 적성검사만료일
                let licenseIssueDt  	= formatDate(getLicenseIssueDt());				// 면허 발급일
                let userLicenseOwnYn 	= 'N';											//
                let licenseLocation     = '';											// 면허지역

                if(isEmpty(urIdx)) {
                    errorAlert('회원정보', '회원정보를 먼저 저장하여 주세요.');
                    return;
                }

                if(isEmpty(licenseCode) || licenseCode == 0) {
                    errorAlert('면허코드', '면허코드를 선택');
                    return;
                }

                if(isEmpty(licenseNumber)) {
                    errorAlert('운전면허 번호', '운전면허 번호는 필수 입력값 입니다.');
                    return;
                }

                //if(isEmpty(licenseExpiration)) {
                //  errorAlert('적성검사만료일', '적성검사만료일은 필수 입력값 입니다.');
                //  return;
                //}

                if(isEmpty(licenseIssueDt)) {
                    errorAlert('면허 발급일', '면허 발급일은 필수 입력값 입니다.');
                    return;
                }

                let target = 'userLicenseInfo';
                let method = 'select';

                //면허정보갸 있는지 먼저 체크한다.
                req = {
                    urIdx : urIdx
                };

                fn_callApi( method, target, req, function(response) {
                    let res = response;
                    if(isEmpty(urIdx)) {
                        errorAlert('API ERROR', 'seq가 Null일 수 없습니다.');
                        return;
                    }
                    if(res.code == 200) {

                        save_type = 'UPDATEuserLicenseInfo';
                        req = {
                            urIdx				: urIdx
                            ,  licenseCode  		: licenseCode
                            ,  licenseNumber  		: licenseNumber
                            ,  licenseExpiration 	: licenseExpiration
                            ,  licenseIssueDt  	: licenseIssueDt
                            ,  userLicenseOwnYn  	: userLicenseOwnYn
                            ,  licenseLocation   	: licenseLocation
                            ,	useYn				: 'Y'
                            ,	modId : GLOBAL_LOGIN_USER_IDX
                            ,	regId : GLOBAL_LOGIN_USER_IDX
                        }
                    }else if (res.code == 400) {
                        save_type = 'INSERTuserLicenseInfo';
                        req = {
                            urIdx				: urIdx
                            ,  licenseCode  		: licenseCode
                            ,  licenseNumber  		: licenseNumber
                            ,  licenseExpiration 	: licenseExpiration
                            ,  licenseIssueDt  	: licenseIssueDt
                            ,  userLicenseOwnYn  	: userLicenseOwnYn
                            ,  licenseLocation   	: licenseLocation
                            ,	useYn				: 'Y'
                            ,	modId : GLOBAL_LOGIN_USER_IDX
                            ,	regId : GLOBAL_LOGIN_USER_IDX
                        }
                    }

                    title = '면허정보 저장';
                    text = '저장하시겠습니까?'
                    icon = 'info';
                    cancel_text = '취소하셨습니다.';

                    call_before_save(title, text, icon, cancel_text, save_type, req);
                });// end fn_callApi
                break;

        }//end switch
    }//end save_type check
}//end function

// submit
function detailSubmit(save_type, req){
    let target = '';
    let method = '';

    if(isEmpty(save_type)) {
        errorAlert('API ERROR', 'Save Type이 존재하지 않습니다. 관리자에게 문의하세요');
        return;
    }

    if(isEmpty(req)) {
        errorAlert('API ERROR', '전송가능한 파라메터가 존재하지 않습니다. 관리자에게 문의하세요');
        return;
    }


    switch (save_type) {
        case 'saveUser'://회원정보
            if( CRUD == 'modify') {
                target = 'updateUserInfo';
                method = 'update';
            }else if( CRUD == 'insert') {
                target = 'insertUserInfo';
                method = 'insert';
            }
            break;
        case 'UPDATEuserLicenseInfo'://면허정보  UPDATE
            target = 'updateUserLicenseInfo';
            method = 'update';
            break;
        case 'INSERTuserLicenseInfo'://면허정보  INSERT
            target = 'insertUserLicenseInfo';
            method = 'insert';
            break;
    }//end switch

    if(isEmpty(target)) { //is not empty
        errorAlert('API ERROR ' + save_type, 'api target이 존재하지 않습니다. 관리자에게 문의하세요');
        return;
    }

    if(isEmpty(method)) { //is not empty
        errorAlert('API ERROR '  + save_type, 'api method가 존재하지 않습니다. 관리자에게 문의하세요');
        return;
    }

    fn_callApi(method, target, req, function(response) {
        let res = response;

        // 200이라면 페이징을 구한다.
        if (res.code == 200) {

            if(res!=null){
                swal("저장 성공", { icon: "success"});
                switch (save_type) {
                    case 'saveUser'://회원정보
                        if( CRUD === 'insert') {
                            let urIdx = res.urIdx;
                            $("#urIdx").val(urIdx);
                        }
                        break;
                    case 'INSERTuserLicenseInfo'://운전면허
                        if( CRUD === 'insert') {
                            // $("#"+MODAL_NAME).iziModal('close');
                        }
                        break;
                }//end switch


                //left_location("/static/viewContents/member/integratedMember.html" , "" , "");
            }
        } else { // 200이 아닐때 empty처리 error처리 등을 기록한다.
            errorAlert('저장 실패', '관리자에게 문의하세요.');
            return;
        }
    });// end fn_callApi
}

$('#licenseExpiration input').click(function(event) {
    // $('#datetimepicker1 ').data("DateTimePicker").show();
});



$("#" + MODEL_NAME).iziModal({
    radius: 5,
    padding: 20,
    closeButton: true,
    overlayClose: false,
    width: MODEL_WIDTH,
    height:1500,
    title: MODEL_TITLE,
    headerColor: '#002e5b',
    backdrop: 'static',
    keyboard: false
});

function openCreateMember(){
    CRUD = 'insert';
//	UR_IDX = '';

    $('#urIdx').val('');
    $('#userId').val('');
    $('#userName').val('');
    $('#joinChannel').val('');
    $('#userBirthday').val('');
    $('#userContact1').val('');
    $('#regDt').val('');

    $('#licenseCode').val('');
    $('#licenseNumber').val('');
    $('#licenseExpiration').val('');
    $('#licenseIssueDt').val('');
    $('#userLicenseOwnYn').val('');
    $('#licenseLocation').val('');

    initModalSelectBox(null);

}

// 회원 탈퇴
function deleteUserInfo() {
    let alertModal = $("#alertModal");
    let alertModalContent = $("#alertModal #modalContent");
    let alertModalBtn = $("#alertModal .modal-footer button");

    alertModalContent.text("회원 탈퇴를 진행하시겠습니까?");
    alertModalBtn.click(function () {
        let req = {
            urIdx : $("#urIdx").val(),
            userId : $("#userId").val(),
            userName : $("#userName").val(),
            userBirthday : $("#userBirthday").val(),
            userContact1 : $("#userContact1").val()
        };
        let target = 'userInfoDelete';
        let method = 'delete';

        fn_callApi( method, target, req, function(response) {
            let res = response;
            if (res.code == 200) {
                location.reload();
                alert("회원 탈퇴가 완료되었습니다.");
                self.close();
            }
        });

    });

    alertModal.modal('show');

}

function userContactClick(){
    let num = $("#userContact1").val();
    num = getOnlyNumber(num);

    $("#userContact1").val(num);
}

//input box auto hypen
function userContactAutoHyphen(){
    let num = $("#userContact1").val();

    autoHyphenFromNumber('phone','userContact1', num);
}

function licenseNumAutoHyphen() {
    let num = $("#licenseNumber").val();

    autoHyphenFromNumber('license','licenseNumber', num);
}

/*
 * 입력값을 취소합니다.
 * */
function cancelData(cancel_type) {
    switch (cancel_type) {
        case 'saveUserInfo':// 회원 정보
            $("#userId").val('');
            $("#userName").val('');
            $("#userBirthday").val('');
            $("#userContact1").val('');
            $("#sel_userGender").val('');
            $("#sel_userRole").val('');
            break;

        case 'saveLicenseInfo':// 면허 정보
            $("#sel_LicenseCode").val('');
            $("#licenseNumber").val('');
            $("#licenseExpiration").val('');
            $("#licenseIssueDt").val('');
            break;
    }

}

// 카드 변경
function changeCardView(cardViewName) {
    let cards = $('.card-body');

    switch (cardViewName) {
        case 'userInfoCard':
            cards.addClass('d-none');
            $('#integratedMemberDetail').removeClass('d-none');
            break;
        case 'userReviewCard':
            swal("상세화면은 순차적으로 오픈할 예정입니다.", { icon: "warning", });
            cards.addClass('d-none');
            $('#userReviewCard').removeClass('d-none');
            break;
        case 'userReservationHisCard':
            cards.addClass('d-none');
            $('#userReservationHisCard').removeClass('d-none');
            break;
        case 'userCouponCard':
            swal("상세화면은 순차적으로 오픈할 예정입니다.", { icon: "warning", });
            cards.addClass('d-none');
            $('#userCouponCard').removeClass('d-none');
            break;
        case 'userPointHisCard':
            swal("상세화면은 순차적으로 오픈할 예정입니다.", { icon: "warning", });
            cards.addClass('d-none');
            $('#userPointHisCard').removeClass('d-none');
            break;
        case 'userLicenseCard':
            cards.addClass('d-none');
            $('#userLicenseCard').removeClass('d-none');
            break;
        case 'userGradeCard':
            swal("상세화면은 순차적으로 오픈할 예정입니다.", { icon: "warning", });
            cards.addClass('d-none');
            $('#userGradeCard').removeClass('d-none');
            break;
        default:
            break;
    }
}