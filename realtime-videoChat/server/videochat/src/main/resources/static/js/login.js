
function handleLogin(event) {
    event.preventDefault();
    // Get user input
    const emailInput = document.getElementById("email");
    const passwordInput = document.getElementById("password");

    console.log("Email input:", emailInput);  // Check if the input elements are found
    console.log("Email value:", emailInput.value);  // Check the actual value

    const email = emailInput.value.trim();  // Avoid leading/trailing spaces
    const password = passwordInput.value;

    if (!email || !password) {
        alert("Please fill in both email and password");
        return;
    }
    const user = {
        email: email,
        password: password
    }
    fetch("http://localhost:8080/api/v1/users/login", {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    })
        .then(async (response) => {
            if (!response.ok) {
                const error = await response.json();  // Try to parse error message
                throw new Error(error.error || 'An unknown error occurred');
            }
            return response.json();
        })
        .then((data) => {
            localStorage.setItem('connectedUser', JSON.stringify(data));
            window.location.href = 'index.html';
        })
        .catch((error) => {
            console.error('POST request error:', error);
            alert(error.message);
        });

}

const loginForm = document.getElementById("loginForm");
loginForm.addEventListener("submit", handleLogin);