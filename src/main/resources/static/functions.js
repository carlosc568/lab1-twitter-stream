fhfjjgfunction registerTemplate() {
    template = $("#template").html();
    Mustache.parse(template);
}

function registerSearch() {
    $("#search").submit(function(event){
        event.preventDefault();
        var target = $(this).attr('action');
        var query = $("#q").val();
        $.get(target, { q: query } )
            .done( function(data) {
                $("#resultsBlock").empty().append(Mustache.render(template, data));
            }).fail(function() {
            $("#resultsBlock").empty();
        });
    });
}

$(document).ready(function() {
    registerTemplate()
	registerSearch();
});


