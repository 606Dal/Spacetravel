document.addEventListener("DOMContentLoaded", () => {
	const form = document.querySelector("form");
	if (!form) return;

	const currentEl = document.getElementById("currentPassword");
	const newEl = document.getElementById("newPassword");
	const confirmEl = document.getElementById("confirmPassword");
	const msgEl = document.getElementById("pwConfirm");

	const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[\d])(?=.*[!@#$%^&*?_])[a-zA-Z\d!@#$%^&*?_]{8,20}$/;

	function setMsg(text, color) {
		if (!msgEl) return;
		msgEl.textContent = text || "";
		msgEl.style.color = color || "";
	}

	function pwCheck() {
		const pw = newEl?.value ?? "";
		const cpw = confirmEl?.value ?? "";

		if (pw.length < 8 || pw.length > 20) {
			setMsg("비밀번호는 8~20자, 영문자, 숫자, 특수문자를 모두 포함하여 입력해주세요", "");
			return;
		}

		if (!pw && !cpw) {
			setMsg("", "");
			return;
		}

		if (pw === cpw) setMsg("비밀번호 일치", "green");
		else setMsg("비밀번호 불일치", "red");
	}

	// 실시간 체크
	newEl?.addEventListener("input", pwCheck);
	confirmEl?.addEventListener("input", pwCheck);

	function valid() {
		const currentPw = currentEl?.value ?? "";
		const newPassword = newEl?.value ?? "";
		const confirmPw = confirmEl?.value ?? "";

		if (!passwordRegex.test(currentPw)) {
			alert("비밀번호는 8~20자, 영문자, 숫자, 특수문자를 모두 포함하여 입력해주세요.");
			currentEl?.focus();
			return false;
		}

		if (!passwordRegex.test(newPassword)) {
			alert("비밀번호는 8~20자, 영문자, 숫자, 특수문자를 모두 포함하여 입력해주세요.");
			newEl?.focus(); // 여기 원래 currentPassword로 포커스 가던 버그도 같이 수정
			return false;
		}

		if (newPassword !== confirmPw) {
			alert("비밀번호가 일치하지 않습니다.");
			confirmEl?.focus();
			return false;
		}

		return true;
	}

	// submit 검증
	form.addEventListener("submit", (e) => {
		if (!valid()) e.preventDefault();
	});
});
