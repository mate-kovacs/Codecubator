

function takeAssignmentListener() {
    let buttons = document.getElementsByClassName("take-assignment");
    console.log(buttons);
    for (button of buttons) {
        console.log(button);
        button.addEventListener("click", function() {
            let id = button.dataset.id;
            let uri = location.pathname.substr(1);
            console.log(id);
            console.log(uri);
            uri = uri.slice(0, -1);
            console.log(uri);
            document.location.href = "/" + uri + "?assignment_id=" + id;
        });
    }
}

takeAssignmentListener();