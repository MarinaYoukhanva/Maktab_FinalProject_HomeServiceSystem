document.getElementById("signupForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Prevent the default form submission

    // // Get the role from the dropdown
    // const role = document.getElementById("role").value;
    //
    // // If role is 'customer', redirect to customer signup page
    // if (role === "customer") {
    //     window.location.href = "customer_signup.html"; // Redirect to customer signup page (use .html or .js as per your file structure)
    //     return; // Stop further execution of the form submission logic
    // }

    // Proceed with expert signup if role is expert
    const formData = new FormData();
    formData.append("firstname", document.getElementById("firstname").value);
    formData.append("lastname", document.getElementById("lastname").value);
    formData.append("username", document.getElementById("username").value);
    formData.append("email", document.getElementById("email").value);
    formData.append("password", document.getElementById("password").value);
    formData.append("phoneNumber", document.getElementById("phoneNumber").value);

    // Append photo if it's an expert
    // if (role === "expert") {
        const photoInput = document.getElementById("profileImage");
        if (photoInput.files.length > 0) {
            formData.append("profileImage", photoInput.files[0]);
        }
    // }

    try {
        const response = await fetch("http://localhost:8081/v1/expert/signup", {
            method: "POST",
            body: formData
        });

        const responseData = await response.json();

        // Handle any form validation errors
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
            }else if (response.status === 413){
                document.getElementById("formError")
                    .innerHTML = "too large input value! (up to 300kb for photo)";

            }
            else {
                document.getElementById("formError").innerHTML = "An error occurred!";
                // document.getElementById("formError").innerHTML =
                //     `<p class="text-red-500">${responseData.message}</p>`;

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

// // Toggle the photo section visibility when role changes
// document.getElementById("role").addEventListener("change", function() {
//     const photoSection = document.getElementById("photoSection");
//     if (this.value === "expert") {
//         photoSection.classList.remove("hidden");
//     } else {
//         photoSection.classList.add("hidden");
//     }
// });