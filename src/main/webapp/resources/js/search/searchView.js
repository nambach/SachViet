let searchView = {

    txtSearch: null,
    btnClearSearch: null,
    btnSearch: null,

    cateContainer: null,

    templateBookItem: null,
    containerResult: null,

    dataSource: null,

    txtResultMessage: null,
    txtResultNumber: null,

    chkShops: [],
    chkShopValues: {},

    init() {
        appView.getTemplateBookItem();

        let self = searchView;

        self.txtSearch = document.querySelector("div.searchBox>input[type='text']");
        self.btnClearSearch = document.querySelector("div.searchBox>span");
        self.btnSearch = document.querySelector("div.searchBox>button");

        self.cateContainer = document.querySelector(".content-aside div");

        self.templateBookItem = document.querySelector("[data-book-template]");
        self.containerResult = document.querySelector("div.book-list");

        self.txtResultMessage = document.querySelector("#resultMessage");
        self.txtResultNumber = document.querySelector("#numberOfResults");

        self.chkShops = document.querySelectorAll("input[name='chkShop']");

        paginationView.init(self.renderSearchResult);
        paginationView.bindElements();
    },

    bindElements() {
        let self = searchView;

        function search(e) {
            if (e.type === "keyup" && e.keyCode !== 13) return;

            if (self.txtSearch.value.trim() === "") return;

            searchModel.search(
                self.txtSearch.value.trim(),
                (data) => {
                    self.dataSource = data;
                    self.renderTotalSearchResult(data);
                });
        }

        self.txtSearch.addEventListener("keyup", (e) => {
            if (self.txtSearch.value.trim() !== "") {
                self.btnClearSearch.classList.remove("hidden");
            } else {
                self.btnClearSearch.classList.add("hidden");
            }

            search(e);
        });
        self.btnClearSearch.addEventListener("click", () => {
            self.txtSearch.value = "";
            self.btnClearSearch.classList.add("hidden");
            self.txtSearch.focus();
        });
        self.btnSearch.addEventListener("click", search);

        for (let i = 0; i < self.chkShops.length; i++) {
            let chkShop = self.chkShops[i];

            //init checked value
            self.chkShopValues[chkShop.value] = chkShop.checked;

            chkShop.addEventListener("click", () => {
                self.chkShopValues[chkShop.value] = chkShop.checked;
                let filteredData = self.filterDataSourceByChkValues();
                self.renderTotalSearchResult(filteredData);
            })
        }
    },

    renderSearchResult(data) {
        let self = searchView;

        clearChildNodes(self.containerResult);

        for (let i = 0; i < data.length; i++) {
            let node = self.newBookItem(data[i]);
            self.containerResult.appendChild(node);
        }
    },

    renderTotalSearchResult(data) {
        paginationView.setDataSource(data);
        paginationView.trigger();

        searchView.txtResultNumber.textContent = data.length;
    },

    filterDataSourceByChkValues() {
        let self = searchView;

        function filterBookByShops(book) {
            //iterate shops list
            for (let shopName in self.chkShopValues) {
                if (!self.chkShopValues.hasOwnProperty(shopName)) continue;

                if (self.chkShopValues[shopName]) {
                    //if shopName contained in member's ID then book is chosen
                    for (let i = 0; i < book["memberList"].length; i++) {
                        if (book["memberList"][i].toLowerCase().includes(shopName)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return self.dataSource.filter(filterBookByShops);
    },

    renderCategoryBar() {
        let self = searchView;

        searchModel.getAllCategory(data => {
            data = JSON.parse(data);

            clearChildNodes(self.cateContainer);

            for (let i = 0; i < data.length; i++) {
                let category = data[i];
                let node = document.createElement("div");
                node.classList.add("content-aside-item");
                node.textContent = category["name"];

                function searchByCateId() {
                    searchModel.getBooksByCategory(category["id"], data => {
                        data = JSON.parse(data);
                        self.dataSource = data;
                        self.renderTotalSearchResult(data);
                    })
                }

                node.addEventListener("click", searchByCateId);
                self.cateContainer.appendChild(node);
            }
        });
    },

    newBookItem(book) {
        return appView.newBookItem(book);
    }

};