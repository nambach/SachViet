let paginationView = {

    pageCount: 20,
    firstPage: 1,
    lastPage: 1,
    currentPage: 1,
    pagination: {
        pagination: null,
        first: null,
        prev: null,
        one: null,
        two: null,
        three: null,
        next: null,
        last: null
    },
    dataSource: [],

    renderData: ()=>{},

    init(renderFunction) {
        let self = paginationView;

        let pagination = document.querySelector("span.pagination");

        self.pagination.pagination = pagination;

        self.pagination.first = pagination.querySelector("a[data-type='btnFirst']");
        self.pagination.prev = pagination.querySelector("a[data-type='btnPrev']");
        self.pagination.one = pagination.querySelector("a[data-type='btn1']");
        self.pagination.two = pagination.querySelector("a[data-type='btn2']");
        self.pagination.three = pagination.querySelector("a[data-type='btn3']");
        self.pagination.next = pagination.querySelector("a[data-type='btnNext']");
        self.pagination.last = pagination.querySelector("a[data-type='btnLast']");

        self.setRenderFunction(renderFunction);
    },

    setRenderFunction(f) {
        paginationView.renderData = f;
    },

    setDataSource(dataSource) {
        paginationView.dataSource = dataSource;

        // Calculate lastPage number in programming index
        let lastPage = divide(dataSource.length, paginationView.pageCount);
        if (dataSource.length % paginationView.pageCount === 0) {
            lastPage--;
        }

        // Update real index
        paginationView.lastPage = lastPage + 1;

        if (lastPage + 1 === 0) paginationView.lastPage = 1;
    },

    bindElements() {
        let self = paginationView;
        let pagination = self.pagination;

        pagination.first.addEventListener("click", () => {
            self.currentPage = self.firstPage;
            self.renderData(self.directPage());
        });

        pagination.prev.addEventListener("click", () => {
            if (self.currentPage > self.firstPage) {
                self.currentPage--;
                self.renderData(self.directPage());
            }
        });

        pagination.next.addEventListener("click", () => {
            if (self.currentPage < self.lastPage) {
                self.currentPage++;
                self.renderData(self.directPage());
            }
        });

        pagination.last.addEventListener("click", () => {
            self.currentPage = self.lastPage;
            self.renderData(self.directPage());
        });

        pagination.one.addEventListener("click", commonDirect);
        pagination.two.addEventListener("click", commonDirect);
        pagination.three.addEventListener("click", commonDirect);

        function commonDirect(e) {
            let pageNumber = e.target.textContent.trim();
            self.currentPage = parseInt(pageNumber);
            self.renderData(self.directPage());
        }

    },

    trigger() {
        paginationView.pagination.one.dispatchEvent(new Event("click"));
    },

    directPage() {
        let self = paginationView;
        let pageCount = self.pageCount;
        let dataSource = self.dataSource;

        let currentPage = self.currentPage;

        let startPos = (currentPage - 1) * pageCount;
        let endPos = currentPage * pageCount - 1;

        let result = [];

        if (startPos > dataSource.length - 1) {
            // Get lastPage number in programming index
            let lastPage = self.lastPage - 1;

            startPos = lastPage * pageCount;

            for (let i = startPos; i < dataSource.length; i++) {
                result.push(dataSource[i]);
            }

            // Update the page number (lastPage is calculated in programming index)
            currentPage = lastPage + 1;
        } else if (endPos > dataSource.length - 1) {
            for (let i = startPos; i < dataSource.length; i++) {
                result.push(dataSource[i]);
            }
        } else {
            for (let i = startPos; i <= endPos; i++) {
                result.push(dataSource[i]);
            }
        }

        self.currentPage = currentPage;

        // Adjust the display of pagination
        self.adjustPageNumber();

        return result;
    },

    adjustPageNumber() {
        let self = paginationView;

        let firstPage = self.firstPage;
        let currentPage = self.currentPage;
        let lastPage = self.lastPage;

        let totalPages = lastPage - firstPage + 1;

        let pagination = self.pagination;

        function blockButton(btn, bool=true) {
            let dataType = btn.getAttribute("data-type") + "Stub";

            let stub = self.pagination.pagination.querySelector(`a[data-type='${dataType}']`);

            if (bool) {
                hide(btn);
                show(stub);
            } else {
                show(btn);
                hide(stub);
            }
        }

        function hide(node) {
            node.classList.add("hidden");
        }

        function show(node) {
            node.classList.remove("hidden");
        }

        function select(btn) {
            btn.classList.add("selected");
        }

        function deselectAll() {
            for (let key in pagination) {
                if (pagination.hasOwnProperty(key)) {
                    pagination[key].classList.remove("selected");
                }
            }
        }

        if (currentPage === firstPage) {
            blockButton(pagination.prev);
            blockButton(pagination.first);
        } else {
            blockButton(pagination.prev, false);
            blockButton(pagination.first, false);
        }

        if (currentPage === lastPage) {
            blockButton(pagination.next);
            blockButton(pagination.last);
        } else {
            blockButton(pagination.next, false);
            blockButton(pagination.last, false);
        }

        deselectAll();

        if (totalPages === 1) {
            select(pagination.one);
            hide(pagination.two);
            hide(pagination.three);

            pagination.one.textContent = firstPage;
        } else if (totalPages === 2) {
            select(pagination.pagination.querySelector(`a[data-type='btn${currentPage}']`));
            show(pagination.two);
            hide(pagination.three);

            pagination.one.textContent = firstPage;
            pagination.two.textContent = firstPage + 1;
        } else if (totalPages >= 3) {
            show(pagination.two);
            show(pagination.three);

            let startPageShowing = 1;

            if (currentPage === firstPage) {
                select(pagination.one);
                startPageShowing = firstPage;
            } else if (currentPage === lastPage) {
                select(pagination.three);
                startPageShowing = lastPage - 2;
            } else {
                select(pagination.two);
                startPageShowing = currentPage - 1;
            }

            pagination.one.textContent = startPageShowing;
            pagination.two.textContent = startPageShowing + 1;
            pagination.three.textContent = startPageShowing + 2;
        }
    }
};