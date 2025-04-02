document.getElementById("categoryForm").addEventListener("submit", async function(event) {
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
            if (response.status === 400 && responseData.errors) {
                Object.keys(responseData.errors).forEach(field => {
                    const errorElement = document.getElementById(field + "Error");
                    if (errorElement) {
                        errorElement.innerHTML = responseData.errors[field]
                            .map(msg => `<p class="text-red-500">${msg}</p>`)
                            .join("");
                    }
                });
            } else if (response.status === 409) {
                if (responseData.errorType === "ExistenceException") {
                    document.getElementById("formError").innerHTML =
                        `<p class="text-red-500">${responseData.message}</p>`;
                } else {
                    document.getElementById("formError").innerHTML =
                        `<p class="text-red-500">A conflict occurred! Please try again.</p>`;
                }
            } else {
                document.getElementById("formError").innerHTML = "An error occurred!";
            }
        } else {
            document.getElementById("formError").innerHTML = ""; // Clear form error
            alert("new service category created successfully! ");
        }
    } catch (error) {
        document.getElementById("formError").innerHTML =
            `<p class="text-red-500">Network error! Please try again.</p>`;
    }
});