let adminView = {

    numberOfResults: null,

    txtSearchBook: null,
    bookListContainer: null,

    adminOptions: [],
    adminPanels: [],

    btnCrawl: null,
    btnStopCrawl: null,
    txtMsgWait: null,
    txtMsgResult: null,

    init() {
        let self = adminView;

        self.txtSearchBook = document.querySelector("input[name='txtSearchBook']");
        self.bookListContainer = document.querySelector(".book-list");

        self.numberOfResults = document.querySelector("#numberOfResults");

        self.adminOptions = document.querySelectorAll(".content-aside-item");
        self.adminPanels = document.querySelectorAll(".content-main");

        self.btnCrawl = document.querySelector("#btnCrawl");
        self.btnStopCrawl = document.querySelector("#btnStopCrawl");
        self.txtMsgWait = document.querySelector(".crawling-pls-wait");
        self.txtMsgResult = document.querySelector(".crawling-result");
    },

    bindElements() {
        let self = adminView;

        function search(e) {
            if (e.type === "keyup" && e.keyCode !== 13) return;

            if (self.txtSearchBook.value.trim() === "") return;

            let result = adminOctopus.searchByApplyingXSL(self.txtSearchBook.value);

            clearChildNodes(self.bookListContainer);
            self.bookListContainer.appendChild(result);

            //Update number of search results
            let lastTr = self.bookListContainer.querySelector("tr:last-child td");
            if (lastTr === null) {
                self.bookListContainer.classList.add("hidden");
                self.numberOfResults.textContent = "0";
            } else {
                self.bookListContainer.classList.remove("hidden");
                self.numberOfResults.textContent = lastTr.textContent;
            }
        }

        self.txtSearchBook.addEventListener("keyup", (e) => {
            search(e);
        });

        function addClassAll(nodeList, className) {
            for (let i = 0; i < nodeList.length; i++) {
                nodeList[i].classList.add(className);
            }
        }
        function removeClassAll(nodeList, className) {
            for (let i = 0; i < nodeList.length; i++) {
                nodeList[i].classList.remove(className);
            }
        }
        for (let i = 0; i < self.adminOptions.length; i++) {
            let option = self.adminOptions[i];
            option.addEventListener("click", () => {
                removeClassAll(self.adminOptions, "selected");
                option.classList.add("selected");

                addClassAll(self.adminPanels, "hidden");
                for (let j = 0; j < self.adminPanels.length; j++) {
                    let panel = self.adminPanels[i];
                    if (panel.getAttribute("data-option") === option.getAttribute("data-option")) {
                        panel.classList.remove("hidden");
                        break;
                    }
                }
            })
        }

        //Init dot loading
        setInterval(() => {
            let dotLoading = self.txtMsgWait.querySelector(".dot-loading");
            let dotNo = (dotLoading.textContent.length + 1) % 4;
            let dot = "";
            for (let i = 0; i < dotNo; i++) {
                dot += ".";
            }
            dotLoading.textContent = dot;
        }, 1000);

        self.btnCrawl.addEventListener("click", () => {
            if (self.btnCrawl.getAttribute("disabled")) return;

            self.txtMsgWait.classList.remove("hidden");
            self.txtMsgResult.classList.add("hidden");
            self.btnCrawl.setAttribute("disabled", "disabled");
            self.btnStopCrawl.removeAttribute("disabled");

            adminModel.startCrawling(
                (data) => {
                    data = JSON.parse(data);

                    self.btnCrawl.removeAttribute("disabled");
                    self.txtMsgWait.classList.add("hidden");

                    self.txtMsgResult.classList.remove("hidden");
                    self.txtMsgResult.textContent = `Hoàn tất trong ${data["totalMillis"]} mili giây`;
                },
                () => {
                    alert("Some error happened!!!");
                });
        });

        self.btnStopCrawl.addEventListener("click", () => {
            if (self.btnStopCrawl.getAttribute("disabled")) return;

            adminModel.stopCrawling(() => {
                self.btnStopCrawl.setAttribute("disabled", "disabled");

                self.txtMsgWait.classList.add("hidden");
                self.txtMsgResult.classList.remove("hidden");
                self.txtMsgResult.textContent = `Đang dừng, xin chờ...`;
            })
        });
    }
};