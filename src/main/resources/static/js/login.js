$( document ).ready(function() {

    $(document).keydown(function(key) {
        if (key.keyCode == 13) {
            $('#btnLogin').trigger('click');
        }


        if( key.keyCode == 70 ){
            let url = '/api/v1.0/docha.login';
            var userId = 'woosung.lee@carssum.com';
            var userPassword = '1234';

            var req = {
                userId : userId
                ,	userPassword : userPassword
            }

            $.ajax({
                url: url,
                type: 'POST',
                data: JSON.stringify(req),
                contentType: 'application/json',
                cache: false,
                acync : false,
                timeout: 10000
            }).done(function (data, textStatus, jqXHR) {

                if( data.code == 400 ){
                    alert(data.data.errMsg);
                }
                if( data.code == 200 ){
                    location.href = 'main.do';
                }

            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + '서버와의 통신에 오류가 있습니다.');
            }).always(function () {

            });

        }
    });


    $('#btnLogin').click(function(){

        let userId = $('#userId').val();
        let userPassword = $('#userPassword').val();

        if( nullCheck(userId) == '' ){
            swal("아이디를 입력해 주세요.", { icon: "warning", });
            return;
        }

        if( nullCheck(userPassword) == '' ){
            swal("비밀번호를 입력해 주세요.", { icon: "warning", });
            return;
        }


        let url = '/api/v1.0/docha.login';

        userId = $('#userId').val();
        userPassword = $('#userPassword').val();
        // userPassword = CryptoJS.SHA256($('#userPassword').val().trim());

        var req = {
            userId : userId,
            userPassword : userPassword
        };

        $.ajax({
            url: url,
            type: 'POST',
            data: JSON.stringify(req),
            contentType: 'application/json',
            cache: false,
            async : false,
            timeout: 10000
        }).done(function (data, textStatus, jqXHR) {
            //
            if( data.code == 400 ){
                errorAlert('로그인 실패', data.errMsg);
            }
            if( data.code == 200 ){
                let res = data.userInfoRequest;

                sessionStorage.setItem('urIdx', res.urIdx);
                sessionStorage.setItem('userRole', res.userRole);
                sessionStorage.setItem('userName', res.userName);
                sessionStorage.setItem('rtIdx', res.rtIdx);

                location.href = '/';
            }

        }).fail(function (jqXHR, textStatus, errorThrown) {
            errorAlert('로그인에러', jqXHR.status + '서버와의 통신에 오류가 있습니다.');
        }).always(function () {

        });

    });

});
