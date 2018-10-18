let loginView = {
    formLogin: null,
    btnLogin: null,
    txtUsername: null,
    txtPassword: null,
    init() {
        this.formLogin = document.querySelector("form[name='login-form']");
        this.btnLogin = document.querySelector("input[name='btnLogin']");
        this.txtUsername = document.querySelector("input[name='username']");
        this.txtPassword = document.querySelector("input[name='password']");
    },

    bindElements() {
        let self = loginView;
        function submitForm(e) {
            if (e.type === "keypress" && e.keyCode !== 13) return;

            loginModel.checkLogin(
                self.txtUsername.value,
                self.txtPassword.value);
        }

        self.btnLogin.addEventListener("click", submitForm);
        self.txtUsername.addEventListener("keypress", submitForm);
        self.txtPassword.addEventListener("keypress", submitForm);
    }
};

let loginModel = {
    checkLogin(username, password) {
        callAjax(pageContext + "/login",
            POST,
            { username, password },
            true,
            (data) => {
                window.location.replace(pageContext + "/home");
            },
            (data) => {
                loginView.formLogin.querySelector("span.loginMessage").classList.remove("hidden");
            });
    }
};