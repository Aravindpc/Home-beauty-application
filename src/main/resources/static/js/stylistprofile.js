$.ajax({
        url: "/stylist/getStylist",
        type: 'GET',
        dataType: 'json', 
        success: function(res) {
            var stylists_details=`<li><b>Full name</b> <input type="text" name="fname" id="fname" maxlength="100"
                                        value="`+res.name+`" required />
                                <li><b>Email</b> <input type="email" name="email" id="email" maxlength="150"
                                        value="`+res.email+`" required />
                                </li>
                                <li><b>Contact number</b> <input type="tel" name="mobile" id="mobile" maxlength="10"
                                        value="`+res.number+`" required />
                                </li>`;
            $('#stylist').html(stylists_details);
            $('.stylistname').html(res.name);
        }
});