var count = 0;
var nQueens = 1;
var password = "javin-past-proj";

$(document).ready(function() {
	addNumbers();
});

function addNumbers(){
	var lst = 0;
	var lst2 = 0;
	var lst3 = 0;
	var lst4 = 0;

	for(i = 1; i <= 118; i++){
		if(i != 118)
			lst = lst + i + "<br>";
		else if(i == 1)
			lst = i + "<br>";
		else
			lst = lst + i;
	}
	for(i = 1; i <= 44; i++){
		if(i != 44)
			lst2 = lst2 + i + "<br>";
		else if(i == 1)
			lst2 = i + "<br>";
		else
			lst2 = lst2 + i;
	}
	for(i = 1; i <= 62; i++){
		if(i != 62)
			lst3 = lst3 + i + "<br>";
		else if(i == 1)
			lst3 = i + "<br>";
		else
			lst3 = lst3 + i;
	}
	for(i = 1; i <= 203; i++){
		if(i != 203)
			lst4 = lst4 + i + "<br>";
		else if(i == 1)
			lst4 = i + "<br>";
		else
			lst4 = lst4 + i;
	}
	document.getElementById("numbers1").innerHTML = lst;
	document.getElementById("numbers2").innerHTML = lst2;
	document.getElementById("numbers3").innerHTML = lst3;
	document.getElementById("numbers4").innerHTML = lst4;
	document.getElementById("navName").style.fontSize = "25px";
	document.getElementById("navName").style.paddingTop = "16px";
	document.getElementById("navPrWork").style.fontSize = "15px";
	document.getElementById("navPrWork").style.paddingTop = "20px";
	document.getElementById("navAbout").style.fontSize = "15px";
	document.getElementById("navAbout").style.paddingTop = "20px";
	$("#codeSection1").slideUp();
	$("#codeSection2").slideUp();
	$("#codeSection3").slideUp();
	$("#codeSection4").slideUp();
}

function incorPass() {
	alert("Sorry wrong password, try again..");
}

$("#physDown").mousedown(function() {
	var pass = prompt("Please enter the password to download this file\nYou can get this password by contacting me!", "Put password here!");
	if(pass == password){
		console.log("correct password");
		window.location = "Physics Simulator.jar";
	}else{
		incorPass();
		console.log("incorrect password");
	}
});

$("#openNQueens").click(function() {
	if($("#codeSection1").is(":hidden")){
		$("#codeSection1").show("slow", function() {
			$("#b").scrollTop(($("#codeSection1").offset().top - 150) + $("#b").scrollTop());
		});
		$("#extraSpace").slideUp();
		var fieldNameElement = document.getElementById("openNQueens");
		fieldNameElement.innerHTML = "Hide";
	}else{
		$("#codeSection1").slideUp();
		var o2 = $("#codeSection2").is(":visible");
		var o3 = $("#codeSection3").is(":visible");
		var o4 = $("#codeSection4").is(":visible");
		if(o2 === false && o3 === false && o4 === false){
			$("#extraSpace").show("slow", function() {
				$("#b").scrollTop(($("#titleOfSampleAlogorithm").offset().top - 100) + $("#b").scrollTop());
			});
		}
		var fieldNameElement1 = document.getElementById("openNQueens");
		fieldNameElement1.innerHTML = "Show";
	}
});

$("#openSQueue").click(function() {
	if($("#codeSection2").is(":hidden")){
		$("#codeSection2").show("slow", function() {
			$("#b").scrollTop(($("#codeSection2").offset().top - 150) + $("#b").scrollTop());
		});
		$("#extraSpace").slideUp();
		var fieldNameElement = document.getElementById("openSQueue");
		fieldNameElement.innerHTML = "Hide";
	}else{
		$("#codeSection2").slideUp();
		var o1 = $("#codeSection1").is(":visible");
		var o3 = $("#codeSection3").is(":visible");
		var o4 = $("#codeSection4").is(":visible");
		if(o1 === false && o3 === false && o4 === false){
			$("#extraSpace").show("slow", function() {
				$("#b").scrollTop(($("#titleOfSampleAlogorithm").offset().top - 100) + $("#b").scrollTop());
			});
		}
		var fieldNameElement2 = document.getElementById("openSQueue");
		fieldNameElement2.innerHTML = "Show";
	}
});

$("#openSudoku1").click(function() {
	if($("#codeSection4").is(":hidden")){
		$("#codeSection4").show("slow", function() {
			$("#b").scrollTop(($("#codeSection4").offset().top - 150) + $("#b").scrollTop());
		});
		$("#extraSpace").slideUp();
		var fieldNameElement = document.getElementById("openSudoku1");
		fieldNameElement.innerHTML = "Hide";
	}else{
		$("#codeSection4").slideUp();
		var o1 = $("#codeSection1").is(":visible");
		var o2 = $("#codeSection2").is(":visible");
		var o3 = $("#codeSection3").is(":visible");
		if(o1 === false && o2 === false && o3 === false){
			$("#extraSpace").show("slow", function() {
				$("#b").scrollTop(($("#titleOfSampleAlogorithm").offset().top - 100) + $("#b").scrollTop());
			});
		}
		var fieldNameElement3 = document.getElementById("openSudoku1");
		fieldNameElement3.innerHTML = "Show";
	}
});

$("#openSudoku").click(function() {
	if($("#codeSection3").is(":hidden")){
		$("#codeSection3").show("slow", function() {
			$("#b").scrollTop(($("#codeSection3").offset().top - 150) + $("#b").scrollTop());
		});
		$("#extraSpace").slideUp();
		var fieldNameElement = document.getElementById("openSudoku");
		fieldNameElement.innerHTML = "Hide";
	}else{
		$("#codeSection3").slideUp();
		var o1 = $("#codeSection1").is(":visible");
		var o2 = $("#codeSection2").is(":visible");
		var o4 = $("#codeSection4").is(":visible");
		if(o1 === false && o2 === false && o4 === false){
			$("#extraSpace").show("slow", function() {
				$("#b").scrollTop(($("#titleOfSampleAlogorithm").offset().top - 100) + $("#b").scrollTop());
			});
		}
		var fieldNameElement3 = document.getElementById("openSudoku");
		fieldNameElement3.innerHTML = "Show";
	}
});
