$(document).ready(function () {
  $("body").prepend(function () {
    var showData = $('#show');

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
    
    alert(json.Boot1position.lat);
    
   

    
  });
});


