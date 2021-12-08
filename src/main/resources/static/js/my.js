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
	$("#basketBtn").click(function(){
		const id = $.cookie("id");
		if(id){
			$.post("../basketList",{id},function(){
				window.open("basketList");
			});
		}else{
			alert("로그인 해야 이용 가능합니다.");
		}
	});
		
	$("#fileUploadBtn").click(function(){
	let formData = new FormData();
	formData.append('image', $("#file")[0].files[0]);
		
		$.ajax({
			type : 'post',
			url : '../personDetect',
			cache : false,
			data : formData,
			processData : false,
			contentType : false,
			success : function(data) {
				data=JSON.parse(data);
				if(data.result){
					alert(data.result);
				}else{
					alert("data.result없음");
				}
			}
			
		});
	});
		$("#faceDetectBtn").click(function(){
		let formData = new FormData();
		formData.append('image', $("#file")[0].files[0]);
		
		
		$.ajax({
			type : 'post',
			url : '../objectDetect',
			cache : false,
			data : formData,
			processData : false,
			contentType : false,
			success : function(data) {
				data=JSON.parse(data);
				$("#drawCanvas").attr('width',data.width+'px');
				$("#drawCanvas").attr('height',data.height+'px');
				$("#drawCanvas").attr('style',"border: 1px solid #993300");
				const canvas=document.getElementById("drawCanvas");
				const context=canvas.getContext("2d");
				const image=new Image();
				
				image.src='../media/upload.png';
				
				image.onload=function(){
					context.drawImage(image,0,0);
					context.strokeStyle = 'yellow';
					context.lineWidth = 3;
					
					
						const x=(data.faces[0].roi.x)	
						const y=(data.faces[0].roi.y)
										
						const width=(data.faces[0].roi.width)	
						const height=(data.faces[0].roi.height)
						
					
						console.log(x,y,width,height);
					
						context.strokeRect(x,y,width,height);
					}
				}	
				
			});
		});
		$("#selectAllTypeBtn").click(function(){
			alert();
			$.post('selectAllType',{},function(data){
				
			});
		});
		$("#getPcolorBtn").click(function(){
		let formData = new FormData();
		formData.append('image', $("#file")[0].files[0]);
		
		$.ajax({
			type : 'post',
			url : '../getPcolor',
			cache : false,
			data : formData,
			processData : false,
			contentType : false,
			success : function(data) {
				data=JSON.parse(data);
				if(data.pColor){
					alert(data.pColor);
				}else if(data.msg){
					alert(data.msg);
				}else{
					alert("data없음")
				}
			}
			
		});
	});
		
	
	
});