var colors = ["#5346B3", "#32A88A", "#FFA719", "#3D6DAA", "#806915"]

$(document).ready(function() {
	addLanguageColors();
	var width = $('#titleResume').width();
	var height = $('#titleResume').height();
    console.log("Title container height: " + height + ", width: " + width);
    height = height - 22;
	$("#titleResume").css("font-size", "" + height + "px");
    console.log("new height: " + height);
});

function addLanguageColors(){
	document.getElementById("language1").style.background = colors[0];
	document.getElementById("language2").style.background = colors[3];
	document.getElementById("language3").style.background = colors[2];
	document.getElementById("language4").style.background = colors[1];
	document.getElementById("language5").style.background = colors[0];
	document.getElementById("language7").style.width = "34%";
	document.getElementById("language6").style.background = colors[3];
	document.getElementById("language10").style.width = "34%";
	document.getElementById("language7").style.background = colors[2];
	document.getElementById("language2").style.width = "37%";
	document.getElementById("language8").style.background = colors[1];
	document.getElementById("language9").style.background = colors[0];
	document.getElementById("reducedHeight").style.margin = "5% 0% 0% 10%";
	document.getElementById("language10").style.background = colors[3];
	document.getElementById("language11").style.background = colors[2];
	document.getElementById("language12").style.background = colors[1];
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
