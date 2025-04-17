document.getElementById("signupForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Prevent the default form submission

    // // Get the role from the dropdown
    // const role = document.getElementById("role").value;
    //
    // // Handle role-based redirection on submit
    // if (role === "expert") {
    //     window.location.href = "expert-signup.html"; // Redirect to expert signup page (use .html or .js as per your file structure)
    //     return; // Stop further form processing
    // }

    // Proceed with customer signup
    const requestBody = {
        firstname: document.getElementById("firstname").value,
        lastname: document.getElementById("lastname").value,
        username: document.getElementById("username").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        phoneNumber: document.getElementById("phoneNumber").value,
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

        // Handle form errors
        document.querySelectorAll(".error-message")
            .forEach(el => el.innerHTML = "");

        if (!response.ok) {
            handleErrorResponse(response, responseData);
        }
        else {
            document.getElementById("formError").innerHTML = "";
            alert(`Customer signed up successfully!`);
        }

    } catch (error) {
        document.getElementById("formError").innerHTML =
            `<p class="text-red-500">Network error! Please try again.</p>`;
    }
});

// Toggle the photo section visibility when role changes
// document.getElementById("role").addEventListener("change", function() {
//     const photoSection = document.getElementById("photoSection");
//     if (this.value === "expert") {
//         photoSection.classList.remove("hidden");
//     } else {
//         photoSection.classList.add("hidden");
//     }
// });