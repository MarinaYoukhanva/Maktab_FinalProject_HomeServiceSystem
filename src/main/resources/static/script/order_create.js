document.getElementById("orderForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Prevent the default form submission

    const requestBody = {
        suggestedPriceByCustomer: document.getElementById("suggestedPriceByCustomer").value,
        description : document.getElementById("description").value,
        city: document.getElementById("city").value,
        street: document.getElementById("street").value,
        plaque: document.getElementById("plaque").value,
        customerId: document.getElementById("customerId").value,
        subServiceId: document.getElementById("subServiceId").value
    };

    try {
        const response = await fetch("http://localhost:8081/v1/order/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        });

        const responseData = await response.json();

        // Handle form errors
        document.querySelectorAll(".error-message").forEach(el => el.innerHTML = "");

        if (!response.ok) {
            if (response.status === 400 && responseData.errors) {
                Object.keys(responseData.errors).forEach(field => {
                    const errorElement = document.getElementById(field + "Error");
                    if (errorElement) {
                        errorElement.innerHTML = responseData.errors[field]
                            .map(msg => `<p class="text-red-500">${msg}</p>`)
                            .join("");
                    }
                });
            }else if (response.status === 404 ){
                if (responseData.errorType === "NotFound")
                    document.getElementById("formError").innerHTML =
                        `<p class="text-red-500">${responseData.message}</p>`;
            }
            else if (response.status === 409) {
                if (responseData.errorType === "ExistenceException") {
                    document.getElementById("formError").innerHTML =
                        `<p class="text-red-500">${responseData.message}</p>`;
                } else {
                    document.getElementById("formError").innerHTML =
                        `<p class="text-red-500">A conflict occurred! Please try again.</p>`;
                }
            } else {
                document.getElementById("formError").innerHTML = "An error occurred!";
                // document.getElementById("formError").innerHTML =
                //     `<p class="text-red-500">${responseData.message}</p>`;

            }
        } else {
            document.getElementById("formError").innerHTML = ""; // Clear form error
            alert("new sub service created successfully! ");
        }
    } catch (error) {
        document.getElementById("formError").innerHTML =
            `<p class="text-red-500">Network error! Please try again.</p>`;
    }
});