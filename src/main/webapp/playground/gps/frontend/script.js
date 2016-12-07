
var width = 0;


/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

function ersteFunction() {
     
	document.getElementById("select-button").innerHTML = "Erste Boje"
	document.getElementById("myDropdown").classList.toggle("show");	
}

function zweiteFunction() {
	document.getElementById("select-button").innerHTML = "Zweite Boje"
    document.getElementById("myDropdown").classList.toggle("show");
}

function dritteFunction() {
	document.getElementById("select-button").innerHTML = "Dritte Boje"
    document.getElementById("myDropdown").classList.toggle("show");
}

function vierteFunction() {
	document.getElementById("select-button").innerHTML = "Vierte Boje"
    document.getElementById("myDropdown").classList.toggle("show");
}

function sendeFunction(){
	
//	document.getElementById("progress-bar").width = 25%
	
	konniFunction();
	bojeweniger();
	
}

function konniFunction(){
	//alert("konnifunction")
}

function bojeweniger(){
	if(document.getElementById("select-button").innerHTML != "Wähle eine Boje"){
		alert("Passt.. andere Boje");
		width += 25;
		alert(width);
	}
	else 
		alert("Wählen Sie bitte eine Boje");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
    /*  if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }*/
    }
  }
}