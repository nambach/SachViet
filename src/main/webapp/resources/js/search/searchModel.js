let searchModel = {
    search(searchValue, callback) {
        callAjax("/books/search",
            GET,
            { searchValue },
            true,
            (data) => {
                data = JSON.parse(data);
                if (callback) {
                    callback(data);
                }
            },
            (data) => {
                alert("Some error happened");
            });
    },

    getAllCategory(callback) {
        callAjax("/categories/all", GET, {}, true, callback);
    },

    getBooksByCategory(cateId, callback) {
        callAjax("/books/cate", GET, { cateId }, true, callback);
    }
};