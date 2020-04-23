$(function () {

    var oldPasswordEle = $("input[name=old_password]"),
    	 rePasswordEle = $("input[name=re_password]"), 
    	 newPasswordEle = $("input[name=new_password]"),
    	 sendEmailEle = $("input[name=emailCode]");

    //旧密码校验
    oldPasswordEle.blur(function () {
        if (oldPasswordEle.val() == "" || oldPasswordEle.val() == null) {
            $("#old_password_tip").html("旧密码不能为空！").css("display", "block");
        } else {
            $("#old_password_tip").css("display", "none");
        }
    });

    //6到18位字母+数字
    var reg = /^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)\S{6,18}$/;
    //新密码校验
    newPasswordEle.blur(function () {
        var newPassword = newPasswordEle.val();
        if (newPassword == "" || newPassword == null) {
            $("#new_password_tip").html("请输入新密码！").css("display", "block");
        } else if (!reg.test(newPassword)) {
            $("#new_password_tip").html("密码格式不正确, 请重新输入, 必须是6到18位字母加数字！").css("display", "block");
        } else {
            $("#new_password_tip").css("display", "none");
        }
    });

    //重复密码校验
    rePasswordEle.blur(function () {
        if (newPasswordEle.val() != rePasswordEle.val()) {
            $("#re_password_tip").html("两次输入密码不一致,请重输！").css("display", "block");
        } else {
            $("#re_password_tip").css("display", "none");
        }
    });
    
    sendEmailEle.blur(function () {
    	if (sendEmailEle.val() == '' || sendEmailEle == null) {
    		 $("#sendEmain_tip").html("请输入邮箱验证码！").css("display", "block");
    	} else {
    		$("#sendEmain_tip").css("display", "none");
    	}
    });

    //表单提交前校验
    $(".submit-btn").click(function () {
    	
    	  if(sendEmailEle.val() == '' || sendEmailEle == null){
              $("#sendEmain_tip").html("请输入邮箱验证码！").css("display", "block");
              return;
          }
    	  $("#sendEmain_tip").css("display", "none");
    	  
    	  
    	  
    	  
        $.ajax({
            url: "./user/updatePassword.do",
            type: "POST",
            async: false,
            data: $("#form").serialize(), //表单数据序列化, 可以对form表单进行序列化，从而将form表单中的所有参数传递到服务端。
            success: function (data) {
                if (data.code == 0) {
                    alert("更新成功！");
                    window.location.href = "./user.html";
                } else {
                    alert(data.msg);
                }
            },
            error: function (e) {
                alert("请求出错！");
            }
        });
    });

    $('#send_captcha').click(function () {
        var tipEmail = $('#send_captcha').attr('data-tip_email');
        $.ajax({
        	type: "POST",
        	url: "./login/sedEmail.do",
        	data: {
        		"email" : $('#send_captcha').attr('data')	
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
});