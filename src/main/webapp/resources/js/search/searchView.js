let searchView = {

    txtSearch: null,
    btnClearSearch: null,
    btnSearch: null,

    templateBookItem: null,
    containerResult: null,

    init() {
        let self = searchView;

        self.txtSearch = document.querySelector("div.searchBox>input[type='text']");
        self.btnClearSearch = document.querySelector("div.searchBox>span");
        self.btnSearch = document.querySelector("div.searchBox>button");

        self.templateBookItem = document.querySelector("[data-book-template]");
        self.containerResult = document.querySelector("div.book-list");

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
                    paginationView.setDataSource(data);
                    paginationView.trigger();
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
    },

    renderSearchResult(data) {
        let self = searchView;

        clearChildNodes(self.containerResult);

        for (let i = 0; i < data.length; i++) {
            let node = self.newBookItem(data[i]);
            self.containerResult.appendChild(node);
        }
    },

    newBookItem(book) {
        let self = searchView;
        let node = self.templateBookItem.cloneNode(true);
        node.classList.remove("hidden");
        node.removeAttribute("data-book-template");

        let img = node.querySelector("img");
        img.setAttribute("src", book["image"]);
        img.setAttribute("title", book["title"]);
        img.setAttribute("alt", book["title"]);

        let title = node.querySelector(".book-title>a");
        title.setAttribute("href", book["link"]);
        title.setAttribute("title", book["title"]);
        title.textContent = book["title"];

        let author = node.querySelector(".book-author>small");
        if (book["authors"] && book["authors"].trim() !== "") {
            author.textContent = book["authors"];
        } else {
            author.classList.add("hidden");
        }

        let price = node.querySelector(".book-price");
        price.textContent = book["price"];

        let oldPrice = node.querySelector(".book-old-price");
        if (book["oldPrice"] && book["oldPrice"].trim() !== "") {
            oldPrice.textContent = book["oldPrice"];
        } else {
            oldPrice.classList.add("hidden");
        }

        return node;
    }

};