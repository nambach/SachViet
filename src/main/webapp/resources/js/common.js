function ready(fn) {
    if (document.attachEvent ? document.readyState === "complete" : document.readyState !== "loading"){
        fn();
    } else {
        document.addEventListener('DOMContentLoaded', fn);
    }
}

function callAjax(url, method, data, async = true, success = () => {}, fail = () => {}) {
    let request = new XMLHttpRequest();

    data = convertObjectIntoParams(data);

    if (method === GET && data !== "") {
        url = url + "?" + data;
    }

    request.open(method, url, async);
    request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    request.onreadystatechange = function() {
        if (request.readyState !== 4) {
            return;
        }

        let resp = request.responseText;
        if (request.status >= 200 && request.status < 400) {
            // Success!
            if (success && typeof success === "function") {
                success(resp);
            }
        } else {
            // We reached our target server, but it returned an error
            if (fail && typeof fail === "function") {
                fail(resp);
            }
        }
    };

    if (data === "") {
        request.send();
    } else  {
        request.send(data);
    }
}

function convertObjectIntoParams(object) {
    if (!object) return "";

    let arr = [];
    for (let key in object) {
        if (object.hasOwnProperty(key)) {
            arr.push(key + "=" + object[key]);
        }
    }
    return arr.join("&");
}

function clearChildNodes(node) {
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
}

function divide(a, b) {
    if (isNaN(a) || isNaN(b)) return 0;
    return Math.floor(a / b);
}

