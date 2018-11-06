let adminModel = {
    getAllBooks(callback) {
        callAjax("/books/v-1/search", GET, { searchValue: "" }, true, callback);
    },

    startCrawling(callback, fallback) {
        document.stopLoading = true;
        callAjax("/books/crawl", POST, {}, true, callback, fallback);
    },

    stopCrawling(callback, fallback) {
        document.stopLoading = true;
        callAjax("/books/stop-crawling", POST, {}, true, callback, fallback);
    }
};