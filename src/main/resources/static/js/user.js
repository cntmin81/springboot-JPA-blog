let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-login").on("click", () => {
			this.login();
		});
	},

	save: function() {
		//alert("save");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		console.log(data);
		$.ajax({
			type: "POST",
			url: "/api/user",
			data: JSON.stringify(data), // http body data
			contentType: "application/json; charset=utf-8", // body data type
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열. 타입이 json이라면 javascript오브젝트로 자동으로 변환해준다.
		})
			.done(function(resp) {
				alert("회원가입완료 ");
				location.href = "/";
			})
			.fail(function(error) {
				alert(JSON.stringify(error));
			});
	},
	login: function() {
		//alert("save");
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};
		console.log(data);
		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data), // http body data
			contentType: "application/json; charset=utf-8", // body data type
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열. 타입이 json이라면 javascript오브젝트로 자동으로 변환해준다.
		})
			.done(function() {
				location.href = "/";
			})
			.fail(function(error) {
				alert(JSON.stringify(error));
			});
	}
};

index.init();