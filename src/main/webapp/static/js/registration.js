

function registrationListener() {
    let button = document.getElementById("submit");
    button.addEventListener("click" , function () {
        let message = document.getElementById("alertMessage");
        let password = document.getElementById('password').value;
        let confirmPassword = document.getElementById('confirmPassword').value;
        let name = document.getElementById('name').value;
        let email = document.getElementById('email').value;

        if (password.length < 8) {
            message.style.display = "inline-block";
            message.innerHTML = "Password is too short!";
        } else if (password !== confirmPassword) {
            message.style.display = "inline-block";
            message.innerHTML = "Passwords are not matching!";
        } else {
            $.ajax({
                type: "POST",
                url: "/registration",
                data: {"name": name, "password": password, "email": email},
                success: function (response) {
                    if (response.isNameAddedToDB) {
                        document.location.href = "/";
                    } else {
                        message.style.display = "inline-block";
                        message.innerHTML = "Name or email already in use!";
                    }
                },
            });
        }
    })
}


registrationListener();