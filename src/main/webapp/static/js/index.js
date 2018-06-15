index = {
    init: function () {
        this.changeColorOfUsername();
        this.tbcArrowSlide();
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
