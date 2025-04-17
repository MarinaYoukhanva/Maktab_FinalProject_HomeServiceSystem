document.getElementById("orderForm")
    .addEventListener("submit", async function (event) {
    event.preventDefault(); // Prevent the default form submission

    const requestBody = {
        suggestedPriceByCustomer: document.getElementById("suggestedPriceByCustomer").value,
        description: document.getElementById("description").value,
        city: document.getElementById("city").value,
        street: document.getElementById("street").value,
        plaque: document.getElementById("plaque").value,
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

        document.querySelectorAll(".error-message")
            .forEach(el => el.innerHTML = "");

        if (!response.ok) {
            handleErrorResponse(response, responseData);
        }
        else {
            document.getElementById("formError").innerHTML = "";
            localStorage.setItem("orderId", responseData.id); // Store orderId
            alert(`Order placed successfully!`);
            window.location.href = "location-create.html";
        }
    } catch (error) {
        document.getElementById("formError").innerHTML =
            `<p class="text-red-500">Network error! Please try again.</p>`;
    }
});