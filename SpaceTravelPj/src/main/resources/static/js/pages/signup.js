document.addEventListener("DOMContentLoaded", () => {
	const form = document.getElementById("signupForm");
	if (!form) return;

	const usernameEl = document.getElementById("username");
	const pwEl = document.getElementById("password");
	const cPwEl = document.getElementById("confirmPassword");
	const pwConfirmEl = document.getElementById("pwConfirm");
	const captchaImgEl = document.getElementById("captchaImg");

	const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[\d])(?=.*[!@#$%^&*?_])[a-zA-Z\d!@#$%^&*?_]{8,20}$/;
	const idRegex = /^[a-zA-Z가-힣0-9]{3,20}$/;

	// ===== 에러 UI 헬퍼 =====
	function showError(id, message) {
		const el = document.getElementById(id);
		if (!el) return;
		if (!message) {
			el.style.display = "none";
			el.textContent = "";
			return;
		}
		el.style.display = "inline-block";
		el.textContent = message;
	}

	function clearErrors() {
		showError("usernameError", null);
		showError("captchaError", null);
	}

	// ===== captcha =====
	async function refreshCaptcha() {
		const url = (window.APP && APP.captchaUrl) ? APP.captchaUrl : "/captcha";

		try {
			const res = await fetch(url);
			const data = await res.json();
			if (captchaImgEl && data?.captcha) captchaImgEl.src = data.captcha;
		} catch (err) {
			console.error("Error loading captcha:", err);
		}
	}
	
	const refreshBtn = document.getElementById("captchaRefreshBtn");
	refreshBtn?.addEventListener("click", refreshCaptcha);

	// ===== 비밀번호 실시간 체크 =====
	function pwCheck() {
		if (!pwConfirmEl) return;

		const pw = pwEl?.value ?? "";
		const cPw = cPwEl?.value ?? "";

		if (pw.length < 8 || pw.length > 20) {
			pwConfirmEl.textContent = "비밀번호는 8~20, 영문자, 숫자, 특수문자를 모두 포함하여 입력해주세요";
			pwConfirmEl.style.color = "";
			return;
		}

		if (pw && cPw && pw === cPw) {
			pwConfirmEl.textContent = "비밀번호 일치";
			pwConfirmEl.style.color = "green";
		} else if (pw || cPw) {
			pwConfirmEl.textContent = "비밀번호 불일치";
			pwConfirmEl.style.color = "red";
		} else {
			pwConfirmEl.textContent = "";
			pwConfirmEl.style.color = "";
		}
	}

	pwEl?.addEventListener("input", pwCheck);
	cPwEl?.addEventListener("input", pwCheck);

	// ===== 유효성 검사 =====
	function valid() {
		const name = usernameEl?.value?.trim() ?? "";
		const pw = pwEl?.value ?? "";
		const cPw = cPwEl?.value ?? "";

		if (!idRegex.test(name)) {
			alert("아이디는 3~20자, 한글, 영문자, 숫자만 가능합니다.");
			usernameEl?.focus();
			return false;
		}

		if (!passwordRegex.test(pw)) {
			alert("비밀번호는 8~20자, 영문자, 숫자, 특수문자를 모두 포함하여 입력해주세요.");
			pwEl?.focus();
			return false;
		}

		if (pw !== cPw) {
			alert("비밀번호가 일치하지 않습니다.");
			pwEl?.focus();
			return false;
		}

		return true;
	}

	// ===== submit AJAX 처리 =====
	form.addEventListener("submit", async (e) => {
		e.preventDefault();
		clearErrors();

		if (!valid()) return;

		const formData = new FormData(form);

		try {
			const res = await fetch(form.action, {
				method: "POST",
				body: formData,
				headers: { "X-Requested-With": "XMLHttpRequest" },
			});

			const data = await res.json();

			if (data.ok) {
				alert("회원 가입에 성공하였습니다.");
				const loginUrl = (window.APP && APP.loginUrl) ? APP.loginUrl : "/user/loginPage";
				window.location.href = loginUrl;
				return;
			}

			if (data.errors) {
				showError("usernameError", data.errors.username);
				showError("captchaError", data.errors.captcha);
			} else if (data.message) {
				showError("usernameError", data.message);
			}

			await refreshCaptcha();
		} catch (err) {
			console.error(err);
			alert("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
		}
	});

	// 초기 captcha
	refreshCaptcha();
});
