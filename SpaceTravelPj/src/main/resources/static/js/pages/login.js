document.addEventListener("DOMContentLoaded", () => {
	const form = document.querySelector("form");
	if (!form) return;

	const usernameEl = document.querySelector("#username");
	const passwordEl = document.querySelector("#password");

	form.addEventListener("submit", (e) => {
		const username = usernameEl?.value.trim();
		const password = passwordEl?.value.trim();

		if (!username) {
			alert("아이디를 입력하세요.");
			usernameEl?.focus();
			e.preventDefault();
			return;
		}

		if (!password) {
			alert("비밀번호를 입력하세요.");
			passwordEl?.focus();
			e.preventDefault();
		}
	});
});