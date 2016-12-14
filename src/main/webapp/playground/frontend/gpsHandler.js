$(document).ready(function () {
 
	var json = (function () {
        var json = null;
        $.ajax({
            'async': false,
            'global': false,
            'url': "testData.json",
            'dataType': "json",
            'success': function (data) {
                json = data;
            }
        });
        return json;
    })(); 
	
    
   
  $( "#test" ).val(json.Boot1position.time);
 
});


