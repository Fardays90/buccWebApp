document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    
    const loginData = {
        username: username,
        password: password
    };

    
    fetch('http://localhost:8080/api/bucc/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        if (response.ok) {
            return response.json(); 
        } else {
            throw new Error('Login failed');
        }
    })
    .then(userInfo => {
       
        localStorage.setItem('userInfo', JSON.stringify(userInfo));

       
        window.location.href = 'userInfo.html';
    })
    .catch(error => {
        document.getElementById('loginErrorMessage').classList.remove('hidden');
        document.getElementById('loginErrorMessage').textContent = 'Login failed. Please try again.';
    });
});
document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('registerUsername').value;
    const firstName = document.getElementById('registerFirstName').value;
    const lastName = document.getElementById('registerLastName').value;
    const password = document.getElementById('registerPassword').value;
    const age = document.getElementById('registerAge').value;
    const personalExp = document.getElementById('registerPersonalExp').value;

    const registerData = {
        username: username,
        firstName: firstName,
        lastName: lastName,
        password: password,
        age: age,
        personalExp: personalExp
    };

    fetch('http://localhost:8080/api/bucc/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerData)
    })
    .then(response => {
        if (response.ok) {
            return response.text();
        } else {
            throw new Error('Registration failed');
        }
    })
    .then(message => {
        alert('Registration successful!');
        document.getElementById('registerForm').reset();
    })
    .catch(error => {
        document.getElementById('registerErrorMessage').classList.remove('hidden');
        document.getElementById('registerErrorMessage').textContent = 'Registration failed. Please try again.';
    });
});
