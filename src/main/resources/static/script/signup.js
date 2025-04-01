document.getElementById("signupForm").addEventListener(
    "submit", async function(event) {
    event.preventDefault();

    const requestBody = {
        firstname: document.getElementById("firstname").value,
        lastname: document.getElementById("lastname").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        phoneNumber: document.getElementById("phoneNumber").value,
        // status: document.getElementById("status").value
    };

    try {
        const response = await fetch("http://localhost:8081/v1/customer/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        });

        const responseData = await response.json();

        document.querySelectorAll(".error-message").forEach(el => el.innerHTML = "");

        if (!response.ok) {
            if (response.status === 400 && responseData.errors) {
                Object.keys(responseData.errors).forEach(field => {
                    const errorElement = document.getElementById(field + "Error");
                    if (errorElement) {
                        // Join multiple errors into a single string with bullet points
                        errorElement.innerHTML = responseData.errors[field]
                            .map(msg => `<p class="text-red-500">${msg}</p>`)
                            .join("");
                    }
                });
            }else if (response.status === 409) {
                // Conflict error (like email already taken)
                document.getElementById("formError").innerHTML =
                    `<p class="text-red-500">${responseData.message}</p>`;
            } else {
                document.getElementById("formError").innerHTML = "An error occurred!";
            }
        } else {
            document.getElementById("formError").innerHTML = ""; // Clear form error
            alert("Signup successful!");
        }
    } catch (error) {
        document.getElementById("formError").innerHTML =
            `<p class="text-red-500">Network error! Please try again.</p>`;
    }
});