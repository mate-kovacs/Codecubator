index = {
    resizeDelay: 20,
    resizeTime: 0,
    oldWidth: $(window).width(),

    init: function () {
        index.setupImageMap();
        index.onWindowResize();
    },

    setupImageMap: function () {
        $('#map-image').mapster({
            stroke: true,
            strokeColor: 'eaa411',
            strokeWidth: 5,
            staticState: false,
            clickNavigate: true
        })
    },

    resize: function (maxWidth, maxHeight) {
        let image = $('#map-image');
        let imgWidth = image.width();
        let imgHeight = image.height();
        let newWidth = 0;
        let newHeight = 0;

        if (imgWidth / maxWidth > imgHeight / maxHeight) {
            newWidth = Math.min(maxWidth - 100, 1000);
        } else {
            newHeight = Math.min(maxHeight - 200, 592);
        }

        image.mapster('resize', newWidth, newHeight, index.resizeTime);

        let wrapObject = document.getElementById("mapster_wrap_0");
        let margin = ($(window).width() - wrapObject.offsetWidth) / 2;
        wrapObject.style.marginTop = "40px";
        wrapObject.style.marginLeft = `${margin}px`;
        wrapObject.style.visibility = "visible";
        document.getElementById("map-image").style.visibility = "visible";


    },

    onWindowResize: function () {
        let curWidth = $(window).width();
        let curHeight = $(window).height();

        let checking = false;
        if (checking) {
            return;
        }
        checking = true;
        window.setTimeout(function () {
            let newWidth = $(window).width();
            let newHeigth = $(window).height();
            if (newWidth === curWidth &&
                newHeigth === curHeight) {
                index.resize(newWidth, newHeigth);
            }
            checking = false;
        }, index.resizeDelay);
    }

};

window.onload = function () {
    index.init();
};

window.onresize = function () {
    index.onWindowResize();
}
