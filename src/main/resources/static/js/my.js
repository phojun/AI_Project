$(document).ready(function() {

	const id = $.cookie("id");
	if (id) {
		$("#loginSpan").html(id + " login ok <button id='logoutBtn'>logout</button>");
	}

	$("#loginBtn").click(function() {
		const id = $("#id").val();
		const pw = $("#pw").val();

		alert(id + ":" + pw);

		$.post('../login', { id, pw }, function(data) {
			data = JSON.parse(data);
			if (data.id) {
				$.cookie("id", data.id);
				$("#loginSpan").html(data.id + " login ok <button id='logoutBtn'>logout</button>");
			} else {
				alert(data.msg);
			}

		});
	});

	$(document).on("click", "#logoutBtn", function() {
		$.post('../logout', {}, function(data) {
			data = JSON.parse(data);
			if (data.msg == "ok") {
				$.removeCookie("id");
				location.reload();
			} else {
				alert(data.msg);
			}
		});
	});
	$(document).on('click', "#colorInsertBtn", function() {
		const pColor = $("#colorText").val();
		if (pColor) {
			$.post('../insertColorBox', { id, pColor }, function(data) {
				if (data.msg) {
					alert(data.msg);
				}
			});
		} else {
			alert("컬러입력하세요");
		}
	});
	$("#fileUploadBtn").click(function() {
		let formData = new FormData();
		formData.append('image', $("#file")[0].files[0]);

		$.ajax({
			type: 'post',
			url: '../personDetect',
			cache: false,
			data: formData,
			processData: false,
			contentType: false,
			success: function(data) {
				data = JSON.parse(data);
				if (data.result) {
					alert(data.result);
				} else {
					alert("data.result없음");
				}
			}

		});
	});
	$("#faceDetectBtn").click(function() {
		let formData = new FormData();
		formData.append('image', $("#file")[0].files[0]);

		$.ajax({
			type: 'post',
			url: '../objectDetect',
			cache: false,
			data: formData,
			processData: false,
			contentType: false,
			success: function(data) {
				data = JSON.parse(data);
				$("#drawCanvas").attr('width', data.width + 'px');
				$("#drawCanvas").attr('height', data.height + 'px');
				$("#drawCanvas").attr('style', "border: 1px solid #993300");
				const canvas = document.getElementById("drawCanvas");
				const context = canvas.getContext("2d");
				const image = new Image();

				image.src = '../media/upload.png';

				image.onload = function() {
					context.drawImage(image, 0, 0);
					context.strokeStyle = 'yellow';
					context.lineWidth = 3;


					const x = (data.faces[0].roi.x)
					const y = (data.faces[0].roi.y)

					const width = (data.faces[0].roi.width)
					const height = (data.faces[0].roi.height)


					console.log(x, y, width, height);

					context.strokeRect(x, y, width, height);
				}
			}

		});
	});
	$("#celebrityDetection").click(function() {
		$("#resultDiv").text("");
		let formData = new FormData();
		formData.append('image', $("#file")[0].files[0]);

		$.ajax({
			type: 'post',
			url: '../celebrityDetect',
			cache: false,
			data: formData,
			processData: false,
			contentType: false,
			success: function(data) {
				data = JSON.parse(data);
				//console.log(data.info.size.width)
				if (data.info.faceCount == 1) {
					$("#drawCanvas").attr('width', data.info.size.width + 'px');
					$("#drawCanvas").attr('height', data.info.size.height + 'px');
					$("#drawCanvas").attr('style', "border: 1px solid #993300");
					const canvas = document.getElementById("drawCanvas");
					const context = canvas.getContext("2d");
					const image = new Image();

					image.src = '../media/test.png';

					image.onload = function() {
						context.drawImage(image, 0, 0);
					}

					var jsonArray = new Array();   
					if (data.info.faceCount > 0) {
						let faces = data.info.faceCount;
						for (let i = 0; i < faces; i++) {
							var jsonObj = new Object();
							jsonObj.celebrity = data.faces[i].celebrity.value;
							jsonObj.confidence = (data.faces[i].celebrity.confidence) * 100 + "%";

							jsonObj = JSON.stringify(jsonObj);
							//String 형태로 파싱한 객체를 다시 json으로 변환
							jsonArray.push(JSON.parse(jsonObj));
						}
						console.log(jsonArray);
						for (let i = 0; i < jsonArray.length; i++) {
							var result = jsonArray[i].celebrity + "을(를) " + jsonArray[i].confidence + "정도 닮았습니다.<br>";
							//console.log(result);
							$("#resultDiv").append(result);
						}

						var celebrity = data.faces[0].celebrity.value;
						//console.log(celebrity);
						$.post('../celeImg', { celebrity }, function() {
							console.log(data);
							$("#celeImg").attr("src","../media/newCele.png");
						});

					} else {
						$("#resultDiv").text("닮은꼴 연예인이 없네요 ㅠㅠ");
					}

				} else if (data.info.faceCount > 1) {
					alert("얼굴이 2개이상 검출되었습니다 한개의 얼굴만 들어간 사진을 넣어주세요~");
				} else {
					alert("얼굴이 검출되지 않았습니다 사진을 다시한번 확인해 주세요");
				}


			},
			error: function(e) {
				console.log("ERROR : ", e);
				alert("사진을 첨부해주세요");
			}

		});
	});


});