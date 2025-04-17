document.getElementById("categoryForm").addEventListener("submit", async function (event) {
    event.preventDefault(); // Prevent the default form submission

    const requestBody = {
        name: document.getElementById("name").value,
        description: document.getElementById("description").value,
    };

    try {
        const response = await fetch("http://localhost:8081/v1/service_category/create", {
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
            handleErrorResponse(response, responseData);
        } else {
            document.getElementById("formError").innerHTML = "";
            alert(`New Service Category created successfully!`);
        }
    } catch (error) {
        document.getElementById("formError").innerHTML =
            `<p class="text-red-500">Network error! Please try again.</p>`;
    }
});