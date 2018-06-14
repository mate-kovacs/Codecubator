quizAssignment = {
    init: function () {
        let assignment_id = document.getElementsByClassName("container")[0].getAttribute("data-assignment-id");
        this.get_next_question(0, assignment_id);
        this.submit_answer_listener();
    },

    submit_answer_listener: function () {
        let submit_answer_button = document.getElementById("submit-button");
        submit_answer_button.addEventListener("click", function () {

            if (false) {
                let submit_error = document.getElementById("submit_error_message");
                submit_error.innerHTML = "At least one answer has to be selected!";
                submit_error.style.visibility = "visible";
                return;
            }
            document.getElementById("submit_error_message").style.visibility = "hidden";

            let question_id = document.getElementById("question-title").getAttribute("data-question-id");
            let assignment_id = document.getElementsByClassName("container")[0].getAttribute("data-assignment-id");

            let answers = document.getElementsByClassName("answer-checkbox");
            let answer_booleans = [];
            for (let answer of answers) {
                answer_booleans.push(answer.checked);
            }

            quizAssignment.submit_answer(question_id, assignment_id, answer_booleans);
        });
    },

    submit_answer: function (questionID, assignmentID, answers) {
        $.ajax({
            type: "POST",
            url: "/quiz-assignment",
            data: {
                "question_id": questionID,
                "assignment_id": assignmentID,
                "answers": answers
            },
            success: function (response) {
                console.log(response);
                if (response.correct_answer) {
                    fight.playSuccefulAttack();
                } else {
                    fight.playUnsuccessfulAttack();
                }
                if (response.death) {
                    quizAssignment.create_and_show_html_failed_assignment();
                } else {
                    quizAssignment.get_next_question(questionID, assignmentID);
                }
            },
        });
    },

    get_next_question: function (questionID, assignmentID) {
        $.ajax({
            type: "POST",
            url: "/quiz-assignment",
            data: {
                "assignment_id": assignmentID,
                "question_id": questionID
            },
            success: function (response) {
                if (response.points_achieved != null) {
                    document.getElementById("submit-button").style.visibility = "hidden";
                    let max_points = response.max_points;
                    let points_achieved = response.points_achieved;
                    quizAssignment.create_and_show_html_completed_assignment(max_points, points_achieved);
                } else {
                    let quiz_question = document.getElementById("question-title");
                    quiz_question.setAttribute("data-question-id", response.question_id);
                    quiz_question.innerHTML = response.question_text;

                    quizAssignment.set_html_answers(response.answers);
                }
            }
        });
    },

    set_html_answers: function (answers) {
        let answer_container = document.getElementById("answer-container");

        let html_string = "";
        let counter = 0;
        for (answer_part of answers) {
            html_string += "<div><label for='" + counter + "'>" + answer_part + "</label>";
            html_string += "<input class='answer-checkbox' id='" + counter + "' type='checkbox'></div>";
            counter++;
        }

        answer_container.innerHTML = html_string;
    },

    create_and_show_html_failed_assignment: function () {
        document.getElementById("question-title").style.visibility = "hidden";
        document.getElementById("submit-button").style.visibility = "hidden";
        let finished_template = document.getElementById("answer-container");
        finished_template.innerHTML = "You have failed this assignment!";
    },

    create_and_show_html_completed_assignment: function (max_points, points_achieved) {
        document.getElementById("question-title").style.visibility = "hidden";
        let score = document.getElementById("answer-container");
        score.innerHTML = "You have scored " +
            points_achieved + " / " + max_points +
            " points on this assignment.";
    }
};

quizAssignment.init();