let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.updateById();
		});
		//$("#btn-login").on("click", () => {
		//	this.login();
		//});
	},

	save: function() {
		//alert("save");
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		console.log(data);
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), // http body data
			contentType: "application/json; charset=utf-8", // body data type
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열. 타입이 json이라면 javascript오브젝트로 자동으로 변환해준다.
		})
			.done(function(resp) {
				alert("글쓰기완료 ");
				location.href = "/";
			})
			.fail(function(error) {
				alert(JSON.stringify(error));
			});
	},
	deleteById: function() {
		let id = $("#id").text();
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id
		})
			.done(function(resp) {
				alert("글삭제완료");
				location.href = "/";
			})
			.fail(function(error) {
				alert(JSON.stringify(error));
			});
	},
	updateById: function() {
		let id = $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		console.log(id);
		console.log(data);
		$.ajax({
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data), // http body data
			contentType: "application/json; charset=utf-8", // body data type
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열. 타입이 json이라면 javascript오브젝트로 자동으로 변환해준다.
		})
			.done(function(resp) {
				alert("글쓰기완료 ");
				location.href = "/";
			})
			.fail(function(error) {
				alert(JSON.stringify(error));
			});
	}
	/*,
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
	*/
};

index.init();