$(document).ready(function(){
	
	const id=$.cookie("id");
	if(id){
		$("#loginSpan").html(id+" login ok <button id='logoutBtn'>logout</button>");
	}
	
	$("#loginBtn").click(function(){
		const id=$("#id").val();
		const pw=$("#pw").val();
		
		alert(id+":"+pw);
		
		$.post('../login',{id,pw},function(data){
			data=JSON.parse(data);
			if(data.id){
				$.cookie("id",data.id);
				$("#loginSpan").html(data.id+" login ok <button id='logoutBtn'>logout</button>");
			}else{
				alert(data.msg);
			}
			
		});
	});
	
	$(document).on("click", "#logoutBtn", function(){
		$.post('../logout',{},function(data){
			data=JSON.parse(data);
			if(data.msg=="ok"){
				$.removeCookie("id");
				location.reload();
			}else{
				alert(data.msg);
			}
		});
	});
	$(document).on('click',"#colorInsertBtn",function(){
			const pColor=$("#colorText").val();
			if(pColor){
				$.post('../insertColorBox',{id,pColor},function(data){
					if(data.msg){
						alert(data.msg);
					}
				});
			}else{
				alert("컬러입력하세요");
			}
		});
	
	
});