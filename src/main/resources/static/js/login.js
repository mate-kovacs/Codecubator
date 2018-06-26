function loginListener() {
    let button = document.getElementById("submit");
    button.addEventListener("click", function () {
        let message = document.getElementById("alertMessage");
        let password = document.getElementById('password').value;
        let name = document.getElementById('name').value;

        $.ajax({
            type: "POST",
            url: "/login",
            data: {"name": name, "password": password},
            success: function (response) {
                if (response === "Not matching") {
                    message.style.display = "inline-block";
                    message.innerHTML = "Wrong name or password!";
                } else {
                    document.location.href = "/";
                }
            },
        });
    })
}

loginListener();
