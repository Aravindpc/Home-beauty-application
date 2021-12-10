function myFunction() {
var x = document.getElementById("myTopnav");
if (x.className === "topnav") {
x.className += " responsive";
} else {
x.className = "topnav";
}
}
function sendMail(){
	var tempParams ={
		from_name:document.getElementById("fromName").value,
		to_name:document.getElementById("toName").value,
		message:document.getElementById("msg").value,
	};
	emailjs.send('gmail','template_luvvqog',tempParams)
	.then(function(res){
		
	})
}
 
   function sendemail() {
            var name = $('#Name').val();
            var email = $('#Email').val();
            var message = $('#message').val();
    
            var Body = 'Name: ' + name + '<br>Email: ' + email + '<br>Message: ' + message;
            Email.send({
                SecureToken: "621a7a5e-a8b5-46a2-b529-e50db61b71f5",
                To : 'your_mail@gmail.com',
        	    From : "customer@gmail.com",
        	    Subject : "Contact Us",
                Body : Body
            }).then(
                message => {
                    //console.log (message);
                    if (message == 'OK') {
                        window.location.replace("/user/thankyou")
                    }
                    else {
                        console.error(message);
                        window.location.replace("/user/error")
                    }
                }
            );
        }