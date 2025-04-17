document.addEventListener("DOMContentLoaded", function () {
    const categorySelect = document.getElementById("categorySelect");
    const subServiceSelect = document.getElementById("subServiceSelect");

    // Load service categories on page load
    fetch("/v1/service_category/find/all")
        .then(res => res.json())
        .then(categories => {
            categories.forEach(cat => {
                const option = document.createElement("option");
                option.value = cat.id;
                option.text = cat.name;
                categorySelect.appendChild(option);
            });
        });

    // Load sub-services when a category is selected
    categorySelect.addEventListener("change", function () {
        const categoryId = categorySelect.value;
        subServiceSelect.innerHTML = '<option value="">-- Select Sub-Service --</option>'; // Reset

        if (!categoryId) return;

        fetch(`/v1/sub_service/find/all/${categoryId}`)
            .then(res => res.json())
            .then(subServices => {
                subServices.forEach(sub => {
                    const option = document.createElement("option");
                    option.value = sub.id;
                    option.text = sub.name;
                    subServiceSelect.appendChild(option);
                });
            });
    });

    // Handle search submit
    document.getElementById("orderSearchForm").addEventListener("submit", function (e) {
        e.preventDefault();

        const fromDateValue = document.getElementById("fromDate").value;
        const toDateValue = document.getElementById("toDate").value;

        // // Check for optional fields and set to null if empty
        // const categoryIdValue = categorySelect.value || null;
        // const subServiceIdValue = subServiceSelect.value || null;
        // const statusValue = document.getElementById("statusSelect").value || null;

        // Create the payload
        const payload = {
            serviceCategory: categorySelect.value ? categorySelect.options[categorySelect.selectedIndex].text : null,
            subService: subServiceSelect.value ? subServiceSelect.options[subServiceSelect.selectedIndex].text : null,
            orderStatus: document.getElementById("statusSelect").value || null,
            fromDate: fromDateValue ? fromDateValue + "T00:00:00" : null,
            toDate: toDateValue ? toDateValue + "T23:59:59" : null,
        };

        console.log("Payload:", payload);
        // Send the request
        fetch("/v1/order/search", {
            method: "POST",  // Ensure it's POST method
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(payload),
        })
            .then(res => res.json())
            .then(data => {
                const tableContainer = document.getElementById("resultsContainer");
                const tbody = document.getElementById("resultsBody");

                tbody.innerHTML = ""; // Clear previous results

                if (data.length === 0) {
                    tableContainer.classList.add("hidden");
                    return;
                }

                data.forEach(order => {
                    const tr = document.createElement("tr");
                    tr.innerHTML = `
                    <td class="px-4 py-2">${order.id}</td>
                    <td class="px-4 py-2">${order.suggestedPriceByCustomer}</td>
                    <td class="px-4 py-2">${order.description}</td>
                    <td class="px-4 py-2">${order.orderPlacementDateTime?.replace("T", " ")}</td>
                    <td class="px-4 py-2">${order.orderExecutionDateTime?.replace("T", " ")}</td>
                    <td class="px-4 py-2">
                           ${order.address
                        ? `${order.address.city}, ${order.address.street} , ${order.address.plaque}`
                        : "N/A"}
                    </td>
                    <td class="px-4 py-2">${order.status}</td>
                    <td class="px-4 py-2">${order.serviceCategoryName}</td>
                    <td class="px-4 py-2">${order.subServiceName}</td>
                `;
                    tbody.appendChild(tr);
                });

                tableContainer.classList.remove("hidden");
            });
    });
});