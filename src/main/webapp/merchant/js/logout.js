

$(function () {
	$(".header-right-logout").click(function(){
		$.ajax({
	        url:"./login/logout.do",
	        type:"post",
	        success:function(data){
                alert(data.msg);
                window.location.href = "./merchant/login.html";
	        },
	        error:function(e){
	            alert("错误！！");
	        	console.log(e)
	        }
	    });        
	});
	
});

