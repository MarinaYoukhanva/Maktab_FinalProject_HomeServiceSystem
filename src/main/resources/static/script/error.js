function handleErrorResponse(response, responseData) {
    const formErrorElement = document.getElementById("formError");
        if (response.status === 400) {
            if (responseData.errors) {
                Object.keys(responseData.errors).forEach(field => {
                    const errorElement = document.getElementById(field + "Error");
                    if (errorElement) {
                        errorElement.innerHTML = responseData.errors[field]
                            .map(msg => `<p class="text-red-500">${msg}</p>`)
                            .join("");
                    }
                });
            } else if (responseData.errorType === "BadRequest") {
                document.getElementById("formError").innerHTML =
                    `<p class="text-red-500">${responseData.message}</p>`;
            }
        } else if (response.status === 404) {
            if (responseData.errorType === "NotFound")
                document.getElementById("formError").innerHTML =
                    `<p class="text-red-500">${responseData.message}</p>`;
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
            // document.getElementById("formError").innerHTML =
            //     `<p class="text-red-500">${responseData.message}</p>`;

        }
}