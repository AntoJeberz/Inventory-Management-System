document.getElementById('btn1').addEventListener('click', function() {
    event.preventDefault();
    const product_id = document.getElementById('orderproductid').value;
    const quantity = document.getElementById('orderquantityid').value;
    if (!product_id || !quantity) {
        document.getElementById('div1').textContent = "Please enter both ID and quantity.";
        return;
    }
   fetch(`http://localhost:8080/order?product_id=${product_id}&quantity=${quantity}`, {
    method: 'POST'
})

    .then(response => response.text())
    .then(message => {
        document.getElementById('div1').textContent = message;
        // alert("Order Sucessfully Placed")
    })
    .catch(err => {
        console.error('Error placing order:', err);
        document.getElementById('div1').textContent = "Error placing order.";
    });
});