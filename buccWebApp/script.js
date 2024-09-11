document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Data to send to backend
    const loginData = {
        username: username,
        password: password
    };

    // Send POST request to login endpoint
    fetch('http://localhost:8080/api/bucc/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        if (response.ok) {
            return response.json(); // Assuming the response contains user data
        } else {
            throw new Error('Login failed');
        }
    })
    .then(userInfo => {
        // Store user info in localStorage
        localStorage.setItem('userInfo', JSON.stringify(userInfo));

        // Redirect to user info page
        window.location.href = 'userInfo.html';
    })
    .catch(error => {
        // Show error message
        document.getElementById('loginErrorMessage').classList.remove('hidden');
        document.getElementById('loginErrorMessage').textContent = 'Login failed. Please try again.';
    });
});
