function take_assignment_listener() {
    let take_asssignment_button = document.getElementById("take-assignment-button");
    take_asssignment_button.addEventListener("click", function () {
        let assignment_start = document.getElementById("assignment-start");
        assignment_start.style.visibility = "hidden";
        let question_list = document.getElementById("question-list");
        question_list.style.visibility = "visible";
    })
}

function submit_question_listener() {
    let coding_questions = document.getElementsByClassName("coding-question");
    console.log(coding_questions);
    for (let current_question of coding_questions){
        console.log(current_question);
        current_question.addEventListener("click", function () {
            get_next_question(2, 1);
        })
    }
}

function get_next_question(question_id, assignment_id) {
    let question_data;

    $.ajax({
        type: "POST",
        url: "/coding-assignment",
        data: {"question_id": question_id, "assignment_id": assignment_id},
        success: function (response) {
            console.log("success");
            if (response === "Last question") {
                console.log("last question");
            } else {
                console.log("next question");
                console.log(response);
            }
        },
    });
}

take_assignment_listener();
submit_question_listener();