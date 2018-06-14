function take_assignment_listener() {
    let take_asssignment_button = document.getElementById("take-assignment-button");
    take_asssignment_button.addEventListener("click", function () {
        let assignment_start = document.getElementById("assignment-start");
        assignment_start.style.visibility = "hidden";
        let question_list = document.getElementById("question-list");
        question_list.style.visibility = "visible";

        let assignment_id = document.getElementById("assignment_id").getAttribute("data-assignment_id");
        get_next_question(0, assignment_id);
    })
}

function submit_question_listener() {
    let submit_answer_button = document.getElementById("submit_question_button");
    submit_answer_button.addEventListener("click", function () {

        if (!is_every_answer_filled()){
            return;
        }

        let question_id = document.getElementById("question_id").getAttribute("data-question_id");
        let assignment_id = document.getElementById("assignment_id").getAttribute("data-assignment_id");

        console.log(question_id);
        console.log(assignment_id);

        get_next_question(question_id, assignment_id);
    })
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
                document.getElementById("question-list").style.visibility = "hidden";
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
    for (answer of answer_parts){
        if (answer.value === ""){
            return false;
        }
    }
    return true;
}

take_assignment_listener();
submit_question_listener();