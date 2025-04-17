function reloadCaptcha() {
    document.getElementById('captchaImage').src = '/v1/payment/captcha?' + new Date().getTime();
}
async function fetchPaymentAmount(orderId) {
    const display = document.getElementById("amountDisplay");
    display.textContent = "";
    display.className = "text-gray-700 text-center font-medium";

    if (!orderId) return;

    try {
        const response = await fetch(`/v1/payment/order_price/${orderId}`);
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText);
        }

        const amount = await response.text();
        display.textContent = `Amount to pay: $${amount}`;
    } catch (error) {
        display.textContent = error.message;
        display.className = "text-red-600 text-center font-medium";
    }
}
document.getElementById('orderIdInput').addEventListener('change', function() {
    fetchPaymentAmount(this.value);
});

document.getElementById('paymentForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const form = e.target;
    const formData = new FormData(form);
    const orderId = formData.get("orderId");

    // Build request payload
    const cardInfo = {
        cardNumber: formData.get("cardNumber"),
        cvv2: formData.get("cvv2"),
        expiryDate: formData.get("expiryDate"),
        cardPassword: formData.get("cardPassword")
    };

    const captchaInput = formData.get("captchaInput");

    const response = await fetch(
        `/v1/payment/pay_with_card/${orderId}?captchaInput=${captchaInput}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cardInfo)
    });

    const msg = await response.text();
    const msgDiv = document.getElementById("message");
    msgDiv.textContent = msg;
    msgDiv.className = response.ok ? "text-green-600 text-center mt-2" : "text-red-600 text-center mt-2";

    if (!response.ok) reloadCaptcha();
    form.reset();
});