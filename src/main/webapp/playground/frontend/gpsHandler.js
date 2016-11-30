

var basicData = JSON.parse(BasicData);
alert(basicData[0].name);
alert(basicData[0].alter);
alert(basicData[1].name);
alert(basicData[1].alter);

var refreshedData = JSON.parse(RefreshedData);
alert(refreshedData[0].name);
alert(refreshedData[0].alter);
alert(refreshedData[1].name);
alert(refreshedData[1].alter);


var basicData = $.getJSON( "example.json", function() {
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

var refreshedData = $.getJSON( "example.json", function() {
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