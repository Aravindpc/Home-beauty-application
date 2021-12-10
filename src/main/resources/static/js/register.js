function register(){
    	var formData = {"name": $("#Name").val(),
    	"number" : $("#number").val(),
       "email" : $("#email").val(),
       "address":$("#address").val(),
       "password":$("#password").val()
       };
       $("#registerForm")[0].reset();
		$.ajax({
		type: "POST",
        contentType: "application/json",
        url: "/registration/register",
        data: JSON.stringify(formData),
        processData: false,
        success: function (data) {
        if(data==="Registered"){
              $("#success").show();
           }else if(data==="Already Registered")
           {
              $("#Alreadyregistered").show();
           }            
        }
        });
   }
$("#registerForm").submit(function(e) {
    e.preventDefault();
});
 $("#success").hide(); 
 $("#Alreadyregistered").hide();