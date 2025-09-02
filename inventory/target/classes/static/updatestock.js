document.getElementById('updateBtn').addEventListener('click', function () {
event.preventDefault();
    const product_id = document.getElementById('updateProductId').value;
    const product_name = document.getElementById('updateName').value;
    const price = document.getElementById('updatePrice').value;
    const quantity = document.getElementById('updateQuantity').value;

    if (!product_id || !product_name || isNaN(price) || isNaN(quantity)) {
        document.getElementById('div1').textContent = "❌ Please fill in all fields correctly.";
        return;
    }

    const product = {
        product_name: product_name,
        price: parseFloat(price),
        quantity: parseInt(quantity)
    };

    fetch(`http://localhost:8080/edit/${product_id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("❌ Update failed");
        }
        // Try parsing JSON if available
        return response.text().then(text => text ? JSON.parse(text) : null);
    })
    .then(updatedProduct => {
        // If backend sends a proper JSON, use it
        if (updatedProduct) {
            document.getElementById('div1').textContent =
                `✅ Product updated: ${updatedProduct.product_name}, Quantity: ${updatedProduct.quantity}`;
        } else {
            // If backend doesn't return body, fall back to input values
            document.getElementById('div1').textContent =
                `✅ Product updated: ${product_name}, Quantity: ${quantity}`;
        }
    })
    .catch(err => {
        document.getElementById('div1').textContent = err.message;
        console.error(err);
    });
});
document.getElementById('deleteBtn').addEventListener('click', function () {
event.preventDefault();
    const productId = document.getElementById('updateProductId').value;

    if (!productId) {
        document.getElementById('updateMessage').textContent = "Please enter Product ID.";
        return;
    }

    fetch(`http://localhost:8080/delete/${product_id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            document.getElementById('updateMessage').textContent = "Product deleted successfully!";
        } else {
            document.getElementById('updateMessage').textContent = "Failed to delete product.";
        }
    })
    .catch(err => {
        console.error('Error:', err);
        document.getElementById('updateMessage').textContent = "Error deleting product.";
    });
});

