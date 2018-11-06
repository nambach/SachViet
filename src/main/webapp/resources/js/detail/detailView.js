let detailView = {

    templateCompareItem: null,
    compareContainer: null,

    suggestContainer: null,

    compareId: null,
    suggestId: null,
    compareGroup: null,

    init(compareId, suggestId) {
        appView.getTemplateBookItem();

        let self = detailView;

        self.compareId = compareId;
        self.suggestId = suggestId;

        self.templateCompareItem = document.querySelector("div[data-compare-template]");
        self.compareContainer = document.querySelector(".group-info");

        self.suggestContainer = document.querySelector(".suggest-list");
    },

    renderSuggestGroup() {
        let self = detailView;

        detailModel.getSuggestGroup(self.suggestId, (data) => {
            data = JSON.parse(data);
            let suggestGroup = data.filter(item => item["id"] !== self.compareId);

            for (let i = 0; i < suggestGroup.length; i++) {
                let suggestGroupItemData = suggestGroup[i];
                let newNode = self.newSuggestItem(suggestGroupItemData);
                self.suggestContainer.appendChild(newNode);
            }
            
            if (suggestGroup.length === 0) {
                document.querySelector(".bottom-container-title").classList.add("hidden");
                document.querySelector(".bottom-container").classList.add("hidden");
            }
        });
    },

    renderCompareGroup() {
        let self = detailView;

        detailModel.getCompareGroup(self.compareId, (data) => {
            data = JSON.parse(data);
            let compareItems = data["members"];
            for(let i = 0; i < compareItems.length; i++) {
                let compareItemData = compareItems[i];
                let newNode = self.newCompareItem(compareItemData);
                self.compareContainer.appendChild(newNode);
            }
        });
    },

    newCompareItem(book) {
        let node = detailView.templateCompareItem.cloneNode(true);
        node.classList.remove("hidden");
        node.removeAttribute("data-compare-template");

        let img = node.querySelector(".item-img img");
        img.setAttribute("src", book["image"]);
        img.setAttribute("title", book["title"]);
        img.setAttribute("alt", book["title"]);

        let title = node.querySelector(".item-title .title a");
        title.setAttribute("href", book["link"]);
        title.setAttribute("title", book["title"]);
        title.textContent = book["title"];

        let author = node.querySelector(".item-title .authors");
        if (book["authors"] && book["authors"].trim() !== "") {
            author.textContent = book["authors"];
        } else {
            author.classList.add("hidden");
        }

        let price = node.querySelector(".item-title .price");
        price.textContent = book["price"];
        if (!price.textContent.includes("đ") && !price.textContent.includes("d")) price.textContent += "đ";

        let shopLink = node.querySelector(".item-external-link a");
        shopLink.setAttribute("href", book["link"]);
        shopLink.setAttribute("title", book["title"]);
        shopLink.querySelector("img").setAttribute("src", appView.getShopLogo(book["id"]));

        return node;
    },

    newSuggestItem(book) {
        return appView.newBookItem(book);
    }

};