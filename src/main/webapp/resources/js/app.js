let appView = {

    templateBookItemString: '<div class="book-item hidden" data-book-template><img class="box-shadow box-shadow-hover" src="" alt="" title=""/><div class="book-title"><a href="#" title="" target="_blank" rel="noopener noreferrer"></a></div><div class="book-author"><small></small></div><div class="book-price"></div><div class="book-shops"></div></div>',
    templateBookItem: null,
    getTemplateBookItem() {
        appView.templateBookItem = document.querySelector("[data-book-template]");
    },
    newBookItem(book) {
        let node = appView.templateBookItem.cloneNode(true);
        node.classList.remove("hidden");
        node.removeAttribute("data-book-template");

        let img = node.querySelector("img");
        img.setAttribute("src", book["image"]);
        img.setAttribute("title", book["title"]);
        img.setAttribute("alt", book["title"]);

        let title = node.querySelector(".book-title>a");
        let bookLink = book["link"];
        if (book["memberList"].length > 1) {
            bookLink = `${pageContext}/compare/${book["id"]}`;
        }
        title.setAttribute("href", bookLink);
        title.setAttribute("title", book["title"]);
        title.textContent = book["title"];

        let author = node.querySelector(".book-author>small");
        if (book["authors"] && book["authors"].trim() !== "") {
            author.textContent = book["authors"];
        } else {
            author.classList.add("hidden");
        }

        let price = node.querySelector(".book-price");
        price.textContent = book["minPrice"];
        if (book["minPrice"] !== book["maxPrice"]) {
            price.textContent = "Giá từ " + price.textContent;
        }

        let shop = node.querySelector(".book-shops");
        if (book["memberList"].length === 1) {
            shop.innerHTML = `<img src=${appView.getShopLogo(book["id"])} alt=${book["id"]}/>`;
        } else {
            shop.innerHTML = `<p>Có ${book["memberList"].length} nơi bán</p>`;
        }

        return node;
    },

    getShopLogo(id) {
        let shopName = id.substr(0, id.indexOf("_")).toLowerCase();
        return `${pageContext}/resources/img/${shopName}-logo.png`;
    }
};