window.onload = function () {
    fetch('http://localhost:8080/getallproducts', { cache: "no-cache" })
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector("#productTable tbody");
            tbody.innerHTML = "";
            data.forEach(product => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${product.product_id}</td>
                    <td>${product.product_name}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                `;
                tbody.appendChild(row);
            });
        })
        .catch(err => console.error('Error fetching products:', err));
};
