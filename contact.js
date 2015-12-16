$(document).ready(function() {
	addLanguageColors();
	var width = $('#titleResume').width();
	var height = $('#titleResume').height();
    console.log("Title container height: " + height + ", width: " + width);
    height = height - 22;
	$("#titleResume").css("font-size", "" + height + "px");
    console.log("new height: " + height);
});

function outerFunction(){
		var c = ["#5346B3", "#3D6DAA", "#FFA719", "#32A88A"];
		var place = 0;
		return function addColor(){
			var tmp = c[place];
			if(place == 3){
				place = 0;
			}else{
				place++;
			}
			return tmp;
		};
}

function addLanguageColors(){
	var grabColour = outerFunction();
	document.getElementById("language1").style.background = grabColour();
	document.getElementById("language2").style.background = grabColour();
	document.getElementById("language3").style.background = grabColour();
	document.getElementById("language4").style.background = grabColour();
	document.getElementById("language5").style.background = grabColour();
	document.getElementById("language7").style.width = "34%";
	document.getElementById("language6").style.background = grabColour();
	document.getElementById("language7").style.background = grabColour();
	document.getElementById("language2").style.width = "37%";
	document.getElementById("language8").style.background = grabColour();
	document.getElementById("language9").style.background = grabColour();
	document.getElementById("language10").style.background = grabColour();
	document.getElementById("reducedHeight").style.margin = "5% 0% 0% 10%";
	document.getElementById("navName").style.fontSize = "25px";
	document.getElementById("navName").style.paddingTop = "16px";
	document.getElementById("navPrWork").style.fontSize = "15px";
	document.getElementById("navPrWork").style.paddingTop = "20px";
	document.getElementById("navAbout").style.fontSize = "15px";
	document.getElementById("navAbout").style.paddingTop = "20px";
	document.getElementById("volunteerWork").style.width = "16%";
	document.getElementById("volunteerWork").style.marginRight = "54%";
	document.getElementById("addSpace").style.background = "#7CB5FF";
	document.getElementById("addSpace").style.color = "#7CB5FF";
	document.getElementById("addSpace").style.boxShadow = "10px 20px 30px #7CB5FF";
	var count = 0;
	while(count < 10){
		console.log("Height of browser: " + $(window).height() + ", Width of browser: " +$(window).width());
		count++;
	}
}

$(window).resize(function(){
	var width = $('#titleResume').width();
	var height = $('#titleResume').height();
    console.log("Title container height: " + height + ", width: " + width);
    height = height - 22;
	$("#titleResume").css("font-size", "" + height + "px");
    console.log("new height: " + height);
});

$("#language2").click(function() {
		console.log("how far left: " + document.getElementById("language2").style.left);
});
