$(function() {
    var merIdEle = $("input[name=merchantid]"), 
    emailEle = $("input[name=email]");

    merIdEle.blur(function () {
        if (merIdEle.val() == '' || merIdEle.val() == null) {
            error_tips('请输入商户号');
            //$("#error_tips").html("请输入商户号").show();
        } else {
            //$("#error_tips").hide();
        }
    });
    emailEle.blur(function () {
        if (emailEle.val() == '' || emailEle.val() == null) {
            error_tips('请输入邮箱');
            //$("#error_tips").html("请输入邮箱").show();
        } else {
            //$("#error_tips").hide();
        }
    });

    $("#captcha_email").click(function(){
        $("#email-captcha-div").show();
        $("#phone-captcha-div").hide();
    });

    $("#captcha_phone").click(function(){
        $("#email-captcha-div").hide();
        $("#phone-captcha-div").show();
    });

    //表单提交前校验
    $(".login-btn").click(function () {
        if($("input[name=merchantid]").val() == '' || $("input[name=merchantid]").val() == null)
        {
            error_tips('请输入商户号');
            return;
        }
        if($("input[name=email]").val() == '' || $("input[name=email]").val() == null)
        {
            error_tips('请输入邮箱');
            return;
        }
        if($("#password").val() == '' || $("#password").val() == null)
        {
            error_tips('请输入密码');
            return;
        }
//        if ($("input[name=captcha_type]:checked").val() == 1){
//            if($("#phoneMessage").val() == '' || $("#phoneMessage").val() == null)
//            {
//                error_tips('请输入手机验证码');
//                return;
//            }
//        } else {
//            if($("#emailMessage").val() == '' || $("#emailMessage").val() == null)
//            {
//                error_tips('请输入邮箱验证码');
//                return;
//            }
//        }

        $.ajax({
            url: "../login/toLogin.do",
            type: "POST",
            async: false,
            data: $("#form_login").serialize(), //表单数据序列化, 可以对form表单进行序列化，从而将form表单中的所有参数传递到服务端。
            success: function (data) {
                //json字符串转为json对象
                if (data.code == 0) {
                    window.location.href = "../merchant.html";
                } else {
                    alert(data.msg);
                }
            },
            error: function (e) {
                alert("请求出错！");
            }
        });
    });

    $('#send_email_captcha').click(function () {
        var email = emailEle.val();
        if (email == '' || email == null) {
            //$("#error_tips").html("请输入邮箱").show();
            error_tips('请输入邮箱');
            return;
        }
        $.ajax({
            type: "POST",
            url: "../login/sedEmail.do",
            data: {
            	"email" : email	
            },
            dataTpye:"json",
            success: function (data) {
                 alert(data.msg);
            },
            error: function (e) {
                alert("请求出错！");
            }
        });
    });

    $('#send_phone_captcha').click(function () {
        var email = emailEle.val();
        if (email == '' || email == null) {
            error_tips('请输入邮箱');
            return;
        }
        $.ajax({
            type: "POST",
            url: "../login/sedPhone.do",
            data: "email=" + email,
            success: function (data) {
                 alert(data.msg);
            },
            error: function (e) {
                alert("请求出错！");
            }
        });
    });
});


function error_tips()
{
    var msg = arguments[0] ? arguments[0] : '';
    var eleId = arguments[1] ? arguments[1] : '';

    $("#error_tips").html(msg).show();
    /*
    if(!eleId)
    {
        alert(msg);
    }*/
}