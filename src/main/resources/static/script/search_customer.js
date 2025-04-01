function handleSearch() {
    const searchParams = {
        firstname: document.getElementById("firstname").value.trim(),
        lastname: document.getElementById("lastname").value.trim(),
        email: document.getElementById("email").value.trim(),
        phoneNumber: document.getElementById("phoneNumber").value.trim()
    };

    const fields = [];
    const values = [];

    Object.entries(searchParams).forEach(([key, value]) => {
        if (value) {  // Only add non-empty values
            fields.push(key);
            values.push(value);
        }
    });

    const queryParams = new URLSearchParams();
    fields.forEach((field, i) => {
        queryParams.append("fields", field);
        queryParams.append("values", values[i]);
    });

    let url = "/v1/customer/search";

    if (fields.length > 0) {
        const queryParams = new URLSearchParams();
        fields.forEach((field, i) => {
            queryParams.append("fields", field);
            queryParams.append("values", values[i]);
        });
        url += `?${queryParams.toString()}`;
    }

    fetch(url, {method: 'GET'})
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch customers");
            }
            return response.json();
        })
        .then(data => {
            displayResults(data);
        })
        .catch(error => {
            // console.error("Error fetching data:", error);
            alert("Error fetching customer data. Please try again.");
        });
}

function displayResults(customers) {
    const resultsDiv = document.getElementById("results");
    resultsDiv.innerHTML = "";

    if (customers.length === 0) {
        resultsDiv.innerHTML = "<p class='text-red-500 text-center'>No customers found.</p>";
        return;
    }

    let tableHTML = `
        <table class="w-full border-collapse border border-gray-300 mt-4">
            <thead>
                <tr class="bg-gray-200">
                    <th class="border border-gray-300 px-4 py-2">ID</th>
                    <th class="border border-gray-300 px-4 py-2">Firstname</th>
                    <th class="border border-gray-300 px-4 py-2">Lastname</th>
                    <th class="border border-gray-300 px-4 py-2">Email</th>
                    <th class="border border-gray-300 px-4 py-2">Register Date</th>
                    <th class="border border-gray-300 px-4 py-2">Status</th>
                    <th class="border border-gray-300 px-4 py-2">Phone</th>
                </tr>
            </thead>
            <tbody>
    `;

    customers.forEach(customer => {
        tableHTML += `
            <tr class="text-center bg-white hover:bg-gray-100">
                <td class="border border-gray-300 px-4 py-2">${customer.id}</td>
                <td class="border border-gray-300 px-4 py-2">${customer.firstname}</td>
                <td class="border border-gray-300 px-4 py-2">${customer.lastname}</td>
                <td class="border border-gray-300 px-4 py-2">${customer.email}</td>
                <td class="border border-gray-300 px-4 py-2">${formatDate(customer.registerDateTime)}</td>
                <td class="border border-gray-300 px-4 py-2">${customer.status}</td>
                <td class="border border-gray-300 px-4 py-2">${customer.phoneNumber}</td>
            </tr>
        `;
    });

    tableHTML += `</tbody></table>`;
    resultsDiv.innerHTML = tableHTML;
}

function formatDate(dateTime) {
    if (!dateTime) return "N/A";
    const date = new Date(dateTime);
    return date.toLocaleDateString() + " " + date.toLocaleTimeString();
}