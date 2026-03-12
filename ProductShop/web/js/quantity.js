function increase() {
    const qty = document.getElementById("qty");
    qty.value = parseInt(qty.value) + 1;
}

function decrease() {
    const qty = document.getElementById("qty");

    if (qty.value > 1) {
        qty.value = parseInt(qty.value) - 1;
    }
}