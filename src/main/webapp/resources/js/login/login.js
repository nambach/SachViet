let loginView = {
    formLogin: null,
    btnLogin: null,
    txtUsername: null,
    txtPassword: null,
    txtMessage: null,
    init() {
        this.formLogin = document.querySelector("div.form");
        this.btnLogin = document.querySelector("button[name='btnLogin']");
        this.txtUsername = document.querySelector("input[name='username']");
        this.txtPassword = document.querySelector("input[name='password']");
        this.txtMessage = document.querySelector("div.loginMessage");

        this.txtUsername.focus();
    },

    bindElements() {
        let self = loginView;
        function submitForm(e) {
            self.hideError();

            if (e.type === "keypress" && e.keyCode !== 13) return;

            if (self.txtUsername.value.trim() === ""
                || self.txtPassword.value.trim() === "") {
                self.showError();
                return;
            }

            loginModel.checkLogin(
                self.txtUsername.value,
                self.txtPassword.value);
        }

        self.btnLogin.addEventListener("click", submitForm);
        self.txtUsername.addEventListener("keypress", submitForm);
        self.txtPassword.addEventListener("keypress", submitForm);
    },

    hideError() {
        removeNode(loginView.formLogin.querySelector("div.loginMessage"));
    },

    showError() {
        let msg = loginView.txtMessage.cloneNode(true);
        msg.classList.remove("hidden");
        loginView.formLogin.querySelector("div.input").appendChild(msg);
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
                loginView.showError();
            });
    }
};