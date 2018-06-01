index = {
    init: function () {
        this.changeColorOfUsername();
        this.tbcArrowSlide();
    },

    tbcArrowSlide: function () {
        let music = document.getElementById("music");
        music.play();
        let tbc_image = document.getElementById("tbc");
        setTimeout(function () {
            tbc_image.style.opacity = "1";
            tbc_image.style.marginRight = "68%";
        }, 3500);
    },

    changeColorOfUsername: function () {
        let welcomeText = document.getElementById("welcome_h1");
        let text = welcomeText.innerText.split(' ');
        if(text.length < 2)
            return;

        text[1] = '<span class="lots-font-color-gold">'+text[1]+'</span>';
        welcomeText.innerHTML = text.join(' ')
    }
};

window.onload = function () {
    index.init();
};
