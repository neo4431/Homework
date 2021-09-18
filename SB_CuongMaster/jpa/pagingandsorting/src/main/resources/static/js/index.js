const pageNumber = document.querySelectorAll(".page-number");
const buttonMove = document.querySelectorAll(".btn-move-page");
const btn_first = buttonMove[0];
const btn_prev = buttonMove[1];
const btn_next = buttonMove[2];
const btn_last = buttonMove[3];
const list_field = document.querySelectorAll("th>span");
const url = "http://localhost:8080/products";
const locationSearch = location.search.substring(1);
const arrayOfRequestParam = locationSearch.split("&");
const pageRequest = arrayOfRequestParam.find((e) => e.indexOf("page=") !== -1);
const orderRequest = arrayOfRequestParam.find(e => e.indexOf("order=") !== -1);
const fieldRequest = arrayOfRequestParam.find(e => e.indexOf("field=") !== -1);
let currentPage = 0;

function disableButton(btn) {
    btn.disabled = true;
    btn.style.backgroundColor = "rgb(164 164 208 / 75%)";
    btn.style.color = "white";
    btn.style.cursor = "auto";
}

(function () {
    if (pageRequest === undefined) {
        disableButton(pageNumber[0]);
        pageNumber[0].style.backgroundColor = "blue";
        disableButton(btn_first);
        disableButton(btn_prev);
        location.href = `${url}?page=1`;
    } else {
        currentPage = parseInt(pageRequest.split("=")[1]);
        if (currentPage < 0 || currentPage > pageNumber.length) {
            alert("Khong co trang nay");
            location.href = `${url}?page=1`;
        }
        // if page = 1 , disabled button first and button previous
        if (currentPage === 1) {
            disableButton(btn_first);
            disableButton(btn_prev);
            //  if page = last page , disabled button last and button next
        } else if (currentPage === pageNumber.length) {
            console.log("abc");
            disableButton(btn_last);
            disableButton(btn_next);
        }
        // disabled button current page
        disableButton(pageNumber[currentPage - 1]);
        pageNumber[currentPage - 1].style.backgroundColor = "blue";
    }
})();

//  function hung su kien click numberPage
function changPage(btn){
    btn.addEventListener(
        "click",
        (e) => {
            let newLocationSearch = arrayOfRequestParam.map(ele => {
                if(ele.indexOf("page") !== -1){
                    return `page=${e.target.outerText}`;
                }
                return ele;
            })
            location.href = `${url}?${newLocationSearch.join("&")}`
        }
    );
}

// lay chuoi request params
function getLocationSearchString(targetPage){
    let newLocationSearch = arrayOfRequestParam.map(ele => {
        if(ele.indexOf("page") !== -1){
            return `page=${targetPage}`;
        }
        return ele;
    })
    return `${url}?${newLocationSearch.join("&")}`
}

pageNumber.forEach((btn) => {
    changPage(btn)
});

btn_first.addEventListener("click",(e) => location.href = getLocationSearchString(1));

btn_last.addEventListener("click", (e) => location.href = getLocationSearchString(pageNumber.length));

btn_prev.addEventListener("click", (e) => location.href = getLocationSearchString(currentPage - 1));

btn_next.addEventListener("click", (e) => location.href = getLocationSearchString(currentPage + 1));

// Sorted event
function isAsc() {
    if(orderRequest === undefined){
        return true;
    } else {
        if (orderRequest.split("=")[1] === 'asc') {
            return true;
        }
        return false;
    }
}

function isCurrentField(event){
    if(fieldRequest === undefined){
        if(event.target.id === "id"){
            return true;
        }
        return false;
    } else {
        if(fieldRequest.split("=")[1] === event.target.id){
            return true;
        }
        return false;
    }
}



function getLocationSearchStringToSort(event){
    let checkCurrentField = isCurrentField(event);
    let checkAsc = isAsc();
    if(checkCurrentField){
        if(checkAsc){
            return `${url}?page=1&field=${event.target.id}&order=desc`;
        } else {
            return `${url}?page=1&field=${event.target.id}&order=asc`;
        }
    } else {
        return `${url}?page=1&field=${event.target.id}&order=asc`;
    }
}

list_field.forEach(btn => {
    btn.addEventListener("click", e =>{
        location.href = getLocationSearchStringToSort(e);
    })
});