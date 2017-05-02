function changeMap(style) {
	localStorage.setItem("mapStyle", style);
	location.reload();
}

function initate() {
	document.getElementById("mapStyleList").onchange = function() {
		changeMap(this.value);
		return false
	};
}

window.onload = initate;