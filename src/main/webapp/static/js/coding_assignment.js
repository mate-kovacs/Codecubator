function take_assignment_listener() {
    let take_asssignment_button = document.getElementById("take-assignment-button");
    take_asssignment_button.addEventListener("click", function () {
        document.getElementById("assignment-start").style.visibility = "hidden";
        document.getElementById("question-list").style.visibility = "visible";
        document.getElementById("submit_error_message").style.visibility = "hidden";

        let assignment_id = document.getElementById("assignment_id").getAttribute("data-assignment_id");
        get_next_question(0, assignment_id);
    })
}

function submit_question_listener() {
    let submit_answer_button = document.getElementById("submit_question_button");
    submit_answer_button.addEventListener("click", function () {

        if (!is_every_answer_filled()) {
            let submit_error = document.getElementById("submit_error_message");
            submit_error.innerHTML = "Fill out all input fields to submit your answer!";
            submit_error.style.visibility = "visible";
            return;
        }
        document.getElementById("submit_error_message").style.visibility = "hidden";

        let question_id = document.getElementById("question_id").getAttribute("data-question_id");
        let assignment_id = document.getElementById("assignment_id").getAttribute("data-assignment_id");
        let answer_texts = [];

        let answers = document.getElementsByClassName("answer_part");
        for (answer of answers) {
            answer_texts.push(answer.value);
        }

        console.log(question_id);
        console.log(assignment_id);
        console.log(answer_texts);

        submit_answer(assignment_id, question_id, answer_texts);
    });
}

function submit_answer(assignment_id, question_id, answers) {
    $.ajax({
        type: "POST",
        url: "/coding-assignment",
        data: {
            "question_id": question_id,
            "assignment_id": assignment_id,
            "answers": answers
        },
        success: function (response) {
            console.log(response);
            //TODO animation reacts to correct or incorrect answer
            get_next_question(question_id, assignment_id);
        },
    });
}

function get_next_question(question_id, assignment_id) {
    // let question_data;

    $.ajax({
        type: "POST",
        url: "/coding-assignment",
        data: {"question_id": question_id, "assignment_id": assignment_id},
        success: function (response) {
            console.log("success");
            if (response.points_achieved != null) {
                console.log(response);
                document.getElementById("question-list").style.visibility = "hidden";
                let score = document.getElementById("assignment-score");
                html_string = "You have scored " +
                    response.points_achieved + " / " + response.max_points +
                    " points on this assignment.";
                score.innerHTML = html_string;
                document.getElementById("assignment-finished").style.visibility = "visible";

            } else {
                console.log("next question");
                console.log(response);
                let coding_question_id = document.getElementById("question_id");
                coding_question_id.setAttribute("data-question_id", response.question_id);

                let question_parts = response.question_text.split("$");
                let answer_ids = response.answer_ids;
                let coding_question_body = document.getElementById("question_holder");

                let html_string =
                    "<div class='question_part'>" +
                    question_parts[0] +
                    "</div>";
                for (let i = 0; i < answer_ids.length; i++) {
                    html_string +=
                        "<input class='answer_part' type='text' id='" + answer_ids[i] + "'>" +
                        "<div class='question_part'>" +
                        question_parts[i + 1] +
                        "</div>"
                }

                coding_question_body.innerHTML = html_string;

            }
        },
    });
}

function is_every_answer_filled() {
    let answer_parts = document.getElementsByClassName("answer_part");
    for (answer of answer_parts) {
        if (answer.value === "") {
            return false;
        }
    }
    return true;
}

take_assignment_listener();
submit_question_listener();