$.get("/project/session");
$.get("/project/messageboard/hitCount/count", function(data) {
    $("#count").html(data);
});
$.get("/project/messageboard/hitCount/active", function(data) {
    $("#active").html(data);
});
$('body').scrollspy({
    target: '.bs-docs-sidebar',
    offset: 40
});

$('body').scrollspy({
    target: ".bs-docs-sidebar"
});
$('window').on("load", function () {
    $('body').scrollspy("refresh");
});
$(".bs-docs-container [href=#]").click(function (a) {
    $('window').preventDefault()
});
setTimeout(function () {
    var b = $(".bs-docs-sidebar");
    b.affix({
        offset: {
            top: function () {
                var c = b.offset().top,
                    d = parseInt(b.children(0).css("margin-top"), 10),
                    e = $(".bs-docs-nav").height();
                return this.top = c - e - d
            },
            bottom: function () {
                return this.bottom = $(".bs-docs-footer").outerHeight(!0)
            }
        }
    })
}, 100);
setTimeout(function () {
    $(".bs-top").affix()
}, 100);
