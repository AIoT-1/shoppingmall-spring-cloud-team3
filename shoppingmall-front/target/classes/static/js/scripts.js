window.onload = function () {
    renderProducts();
};

var soldOutProductIndexes = [];

function toggleSoldOutProducts() {
    var soldOutButton = document.getElementById("hideSoldOutButton");
    var soldOutToggleText = document.getElementById("soldOutToggleText");

    soldOutProductIndexes = [];

    var productCards = document.getElementsByClassName("product-card");
    for (var i = 0; i < productCards.length; i++) {
        var productCard = productCards[i];
        if (productCard.querySelector(".badge")) {
            var isSoldOutHidden = productCard.style.display === "none";
            if (isSoldOutHidden) {
                var index = soldOutProductIndexes.indexOf(i);
                if (index !== -1) {
                    soldOutProductIndexes.splice(index, 1);
                }
            } else {
                soldOutProductIndexes.push(i);
            }
        }
    }

    renderProducts();
}

function renderProducts() {
    var productCards = document.getElementsByClassName("product-card");
    for (var i = 0; i < productCards.length; i++) {
        var productCard = productCards[i];
        if (soldOutProductIndexes.includes(i)) {
            productCard.style.display = "none";
        } else {
            productCard.style.display = "block";
        }
    }

    var soldOutButton = document.getElementById("hideSoldOutButton");
    var soldOutToggleText = document.getElementById("soldOutToggleText");
    if (soldOutProductIndexes.length > 0) {
        soldOutToggleText.textContent = " 품절 제외";
        soldOutButton.innerHTML = '<i class="bi bi-check-circle-fill"></i>' + soldOutToggleText.textContent;
    } else {
        soldOutToggleText.textContent = " 품절 제외";
        soldOutButton.innerHTML = '<i class="bi bi-circle"></i>' + soldOutToggleText.textContent;
    }
}
document.addEventListener("DOMContentLoaded", function () {
    const ratingSpans = document.querySelectorAll(".btn-star");
    const ratingInput = document.getElementById("ratingInput");
    const commentInput = document.getElementById('comment');
    const submitButton = document.getElementById('submitButton');

    // 초기에는 버튼을 비활성화
    submitButton.disabled = true;

    // 별을 클릭할 때 이벤트 처리
    ratingSpans.forEach(function (span) {
        span.addEventListener("click", function () {
            const ratingValue = parseInt(span.getAttribute("data-rating"));
            ratingInput.value = ratingValue;
            updateSubmitButtonState();
            // 별 아이콘 업데이트
            ratingSpans.forEach(function (s, index) {
                const icon = s.querySelector('i');
                if (index < ratingValue) {
                    icon.classList.remove('bi-star');
                    icon.classList.add('bi-star-fill');
                } else {
                    icon.classList.remove('bi-star-fill');
                    icon.classList.add('bi-star');
                }
            });
        });
    });

    // 코멘트 입력시 이벤트 처리
    commentInput.addEventListener('input', function () {
        updateSubmitButtonState();
    });

    // 리뷰 작성 폼에 대한 이벤트 리스너 수정
    const reviewForm = document.querySelector('form');
    reviewForm.addEventListener('submit', function (event) {
        // 코멘트나 평점이 비어있는 경우 submit을 막음
        if (!commentInput.value.trim() || !ratingInput.value) {
            event.preventDefault();
            alert('별점과 코멘트를 모두 입력해주세요.');
        }
    });

    // 등록 버튼 상태 업데이트 함수
    function updateSubmitButtonState() {
        submitButton.disabled = !commentInput.value.trim() || !ratingInput.value;
    }
});




function toggleEditForm(button) {
    var editActions = button.parentElement.nextElementSibling;
    var editForm = button.parentElement.nextElementSibling.nextElementSibling;
    var deleteButton = button.parentElement.nextElementSibling.nextElementSibling.nextElementSibling.querySelector("button");

    if (editActions.style.display === 'none') {
        editActions.style.display = 'inline-block';
        button.style.display = 'none';
        editForm.style.display = 'block';
        deleteButton.style.display = 'none';
    } else {
        editActions.style.display = 'none';
        button.parentElement.previousElementSibling.querySelector(".edit-review-button").style.display = 'inline-block';
        editForm.style.display = 'none';
        deleteButton.style.display = 'inline-block';
    }
}

function cancelEditForm(cancelButton) {
    var editActions = cancelButton.parentElement;
    var editButton = editActions.previousElementSibling.querySelector(".edit-review-button");
    var editForm = editActions.nextElementSibling;
    var deleteButton = editActions.nextElementSibling.nextElementSibling.querySelector(".delete-form button");

    editActions.style.display = 'none';
    editButton.style.display = 'inline-block';
    editForm.style.display = 'none';

    deleteButton.style.display = 'inline-block';
}


var images = [];
var currentIndex = 0;
var productImage = document.getElementById('productImage');

function showPreviousImage() {
    currentIndex = (currentIndex - 1 + images.length) % images.length;
    updateImage();
}

function showNextImage() {
    currentIndex = (currentIndex + 1) % images.length;
    updateImage();
}

function updateImage() {
    productImage.src = images[currentIndex].image;
}

document.getElementById("orderButton").addEventListener("click", function () {
    var cartItems = [];
    var items = document.querySelectorAll(".cart-item");
    items.forEach(function (item) {
        var cartItem = {};
        cartItem.id = item.querySelector("[name='id']").value;
        cartItem.productId = item.querySelector("[name='productId']").value;
        cartItem.modelNumber = item.querySelector("[name='modelNumber']").value;
        cartItem.modelName = item.querySelector("[name='modelName']").value;
        cartItem.thumbnail = item.querySelector("[name='thumbnail']").value;
        cartItem.unitCost = item.querySelector("[name='unitCost']").value;
        cartItem.cartQuantity = item.querySelector("[name='cartQuantity']").value;
        cartItems.push(cartItem);
    });

    document.getElementById("cartItems").value = JSON.stringify(cartItems);
    document.getElementById("orderForm").submit();
});
