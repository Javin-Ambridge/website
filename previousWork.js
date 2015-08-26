var count = 0;
var password = "javin-past-proj";

$(document).ready(function() {
	addNumbers();
});

function addNumbers(){
	var lst = 0;
	var lst2 = 0;
	var lst3 = 0;

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
	document.getElementById("numbers1").innerHTML = lst;
	document.getElementById("numbers2").innerHTML = lst2;
	document.getElementById("numbers3").innerHTML = lst3;
	document.getElementById("navName").style.fontSize = "25px";
	document.getElementById("navName").style.paddingTop = "16px";
	document.getElementById("navPrWork").style.fontSize = "15px";
	document.getElementById("navPrWork").style.paddingTop = "20px";
	document.getElementById("navAbout").style.fontSize = "15px";
	document.getElementById("navAbout").style.paddingTop = "20px"
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
