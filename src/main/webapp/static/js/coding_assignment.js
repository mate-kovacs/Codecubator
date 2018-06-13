function take_assignment_listener() {
    let take_asssignment_button = document.getElementById("take-assignment-button");
    take_asssignment_button.addEventListener("click", function () {
        let assignment_start = document.getElementById("assignment-start");
        assignment_start.style.visibility = "hidden";
        let question_list = document.getElementById("question-list");
        question_list.style.visibility = "visible";
    })
}

function get_next_question(question_id, assignment_id) {
    let question_data;

    $.ajax({
            type: "POST",
            url: "/coding-assignment",
            data: {"question_id": question_id, "assignment_id": assignment_id},
            success: function (response) {
                if (response === "Last question") {
                    console.log("last question");
                } else {
                    question_data = JSON.parse(response);
                    console.log("next question");
                }
            },
        });
}

take_assignment_listener();