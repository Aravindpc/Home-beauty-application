$.ajax({
        url: "/admin/getStylists",
        type: 'GET',
        dataType: 'json',       
        success: function(res) { 
        var stylists_details=``;
          
            $.each(res , function(index, item) {
                  stylists_details+=`<tr>
                            <td>`+item.name+`</td>
                            <td class="number">`+item.number+`</td>
                            <td>`+item.completed+`</td>                            
                            <td>`+item.notcompleted+`</td>
                        </tr>`;
            });
            $('#stylists').html(stylists_details);
            $('.stylistname').html(res.name);
        }
});
