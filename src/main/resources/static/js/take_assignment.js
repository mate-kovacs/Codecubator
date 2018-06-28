

function takeAssignmentListener() {
    let quizzes = document.getElementsByClassName("quiz");
    console.log(quizzes);
    for (quiz of quizzes) {
        quiz.addEventListener("click", function() {
            document.location.href = "/quiz-assignment?assignment_id=" + this.dataset.id;
        });
    }
    let codings = document.getElementsByClassName("coding");
    console.log(codings);
    for (coding of codings) {
        coding.addEventListener("click", function() {
            document.location.href = "/coding-assignment?assignment_id=" + this.dataset.id;
        });
    }
}

takeAssignmentListener();