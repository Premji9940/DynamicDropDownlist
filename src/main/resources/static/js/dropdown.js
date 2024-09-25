
  $(document).ready(function(event){
	  //when we select the country onchange event is performed then this line executed 
	 $("#countryId").change( function (event) {
		 //it is used to clear the statesid options because if we select othe country ,then the selected states adding to existing options tag
		 //then duplicate option values are added.so to over come this proble we need to use  $("#statesId").find('option').remove(); this it cleare
		 //the state form 
		 $("#statesId").find('option').remove();
		 //this line to add --select--- option value to states id other wise directly select the first option value of states	 
		$('<option>').val('').text('----select-----').appendTo("#statesId")
		
		$("#citiesId").find('option').remove();
		  $('<option>').val('').text('-----select------').appendTo("#citiesId")
		  
        $.ajax({
            url: "states?cid=" + $("#countryId").val(), //this line is used to get the states based on country code
            type: "GET",
            success: function (res) { //the result is success then this is executes and result stored in res object
				$.each(res,function(key,value){ //we get the data from res object and iterating and setting to states drop down
					$('<option>').val(key).text(value).appendTo("#statesId")
					
					
				});
            }
        });
    });
    
    //to get cities in drop down
    
    $("#statesId").change(function(event){
		
		  $("#citiesId").find('option').remove();
		  $('<option>').val('').text('-----select------').appendTo("#citiesId")

		$.ajax({
			url:"cities?sid=" + $("#statesId").val(),
			type:"GET",
			success:function(cities){
				$.each(cities,function(citiesKey,CitiesValue){
					$('<option>').val(citiesKey).text(CitiesValue).appendTo("#citiesId")
				});
				
			}
			
			
		});//ajax call
		
	});//states id
	
	//for onblur event to validate email
	
	$("#emailId").blur(function(event){
	var mail=	$("#emailId").val();
	$.ajax({
			type:"GET",
			url:"check?mail=" + mail,
			success:function(msg){
				$("#msgId").text(msg);
				}
				
			});//ajax call
	
	});//blur event for email
	
});//main


