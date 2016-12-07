$(document).ready(function () {
  $("body").prepend(function () {
    var showData = $('#show');

    $.getJSON('testData.json', function (data) {
      console.log(data);
      
      var items = [];
      $.each( data, function(key,val) {
        items.push( "<li id='" + key + "'>" + val + "</li>" );
      });
     
      $( "<ul/>", {
        "class": "my-new-list",
        html: items.join( "" )
      }).appendTo( "body" );
 
    });

    showData.text('test');
    
    var jqxhr = $.getJSON( "testData.json", function() {
    	  console.log( "success" );
    	})
    	  .done(function() {
    	    console.log( "second success" );
    	  })
    	  .fail(function() {
    	    console.log( "error" );
    	  })
    	  .always(function() {
    	    console.log( "complete" );
    	  });
    	 
    	// Perform other work here ...
    	 
    	// Set another completion function for the request above
    	jqxhr.complete(function() {
    	  console.log( "second complete" );
    	});


    
  });
});


