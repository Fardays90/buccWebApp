document.addEventListener('DOMContentLoaded', function() {
    // Get user info from localStorage
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));

    // Display user info on the page
    document.getElementById('displayUsername').textContent = userInfo.username;
    document.getElementById('displayFirstName').textContent = userInfo.firstname;
    document.getElementById('displayLastName').textContent = userInfo.lastname;
    document.getElementById('displayAge').textContent = userInfo.age;
    document.getElementById('displayPersonalExp').textContent = userInfo.personalExp;
});
