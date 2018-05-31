index = {
    init: function () {
        this.tbc_arrow_slide();
    },

    tbc_arrow_slide: function () {
        let tbc_image = document.getElementById("tbc");
        window.onload = function () {
            tbc_image.style.opacity = "1";
            tbc_image.style.marginRight = "68%";
        };
    }
};

index.init();