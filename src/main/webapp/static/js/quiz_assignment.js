quizAssignment = {
    init: function () {
        this.fillQuizAssignment();
    },

    fillQuizAssignment: function () {
        let assignmentID = document.getElementsByClassName("container")[0].getAttribute("data-assignment-id");
        $.ajax({
            type: "POST",
            url: "/quiz-assignment",
            data: {
                "assignment_id": assignmentID
            },
            success: function (response) {
            },
            dataType: JSON
        });
    }
};

quizAssignment.init();