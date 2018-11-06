let detailModel = {
    getCompareGroup(compareId, callback, fallback) {
        callAjax("/books/compare", GET, { compareGroupId: compareId }, false, callback, fallback);
    },

    getSuggestGroup(suggestId, callback, fallback) {
        callAjax("/books/suggest", GET, { suggestId }, false, callback, fallback);
    }
};