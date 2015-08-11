
$(document).ready(function() {
	document.getElementById("navName").style.fontSize = "25px";
	document.getElementById("navName").style.paddingTop = "16px";
	document.getElementById("navPrWork").style.fontSize = "15px";
	document.getElementById("navPrWork").style.paddingTop = "20px";
	document.getElementById("navAbout").style.fontSize = "15px";
	document.getElementById("navAbout").style.paddingTop = "20px";
	console.log("In here");
})

$("#textBox").keypress(function(e) {
	if(e.keyCode == 13)
		addToSticky();
});

function addToSticky() {
	if(count < 4){		
		var text = document.getElementById("textBox").value;
		var node = document.createElement("LI");
    	var textnode = document.createTextNode(text);
    	var font = document.createElement("b");
		node.appendChild(font);
    	node.appendChild(textnode);
		document.getElementById("list").appendChild(node);
		document.getElementById("textBox").value = "";
		count = count + 1;
		if(count == 4)
			hideBox("textBox");
	}
}

function hideBox(id) {
	var image = document.getElementById(id);
	image.style.visibility = 'hidden';
}