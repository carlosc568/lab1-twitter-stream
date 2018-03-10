var stompClient = null;
var subscription = undefined;
var query = "";
function registerTemplate() {
    template = $("#template").html();
    Mustache.parse(template);
}

function registerSearch() {
    $("#search").submit(function(event){
    	
    	if(subscription!=undefined){
    		$("#resultsBlock").empty();
    		stompClient.send('/app/unsub',{},query);
    		subscription.unsubscribe();
    		subscription = undefined;
            
    	}
    	query = $("#q").val();
        event.preventDefault();
        
        console.log(stompClient);
        stompClient.send("/app/search",{},query);
        subscription = stompClient.subscribe('/queue/search/'+query,function(tweet){
        	//console.log(tweet);
        	showTweet(JSON.parse(tweet.body));
        	//$("#resultsBlock").prepend(tweet);
        });
    });
}
function htmltweet(tweet){
	
	
}
function showTweet(tweet){
	var tweethtml = '<div class="card p-3"><div class="card-body"><h5 class="card-title">'
	tweethtml += '<a href="https://twitter.com/'+tweet.fromUser;
	tweethtml += 'target="_blank"><span class="fas fa-user"></span>'+tweet.fromUser+'</a>'
	tweethtml += ' dice en ';
	tweethtml += '<a href="https://twitter.com/'+tweet.fromUser+'/status/'+tweet.idStr+'"';
    tweethtml += 'target="_blank"><span class="fab fa-twitter-square"></span>'+tweet.idStr+'</a>';
    tweethtml += '</h5><p class="card-text">'+tweet.unmodifiedText+'</p></div></div>';
    //console.log(tweethtml)
    $("#resultsBlock").prepend(tweethtml);
}
function connect() {
	
    var socket = new SockJS('/twitter');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
    	console.log(frame);
    	console.log(stompClient);
    	console.log("Conectado");
    	$("#resultsBlock").empty();
    	$("#resultsBlock").append("Polla de camello");
    });
}

$(document).ready(function() {
	connect();
    registerTemplate()
	registerSearch();
});


