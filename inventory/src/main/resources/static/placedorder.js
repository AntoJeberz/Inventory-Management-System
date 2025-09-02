window.onload = function () {
    fetch('http://localhost:8080/getallorders', { cache: "no-cache" })
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector("#ordersTable tbody");
            tbody.innerHTML = "";
            data.forEach(order => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${order.order_id}</td>
                    <td>${order.product_id}</td>
                    <td>${order.quantity}</td>
                    <td>${order.order_date}</td>
                `;
                tbody.appendChild(row);
            });
        })
        .catch(err => console.error('Error fetching orders:', err));
};