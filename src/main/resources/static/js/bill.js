$.ajax({
        url: "/user/salonServiceDetail/getbill",
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            var hair_elements=`<tr><th class="bill-heading" width="52%" align="left">Item
                   </th><th class="bill-heading" align="center"> Quantity </th>
                    <th class="bill-heading" align="right"> Subtotal</th></tr><tr>
                     <td height="1" style="background: #bebebe;" colspan="4"></td> </tr>
                     <tr><td height="10" colspan="4"></td> </tr>`;
            var total=1+15;
            $.each(res , function(index, item) { 
                  hair_elements+=`<tr><td  class="article bill-details">`+item.name+`</td>
                  <td class="bill-details" align="center">`+item.count +` (`+item.salonServiceCategory+`)</td>                   
                   <td class="bill-details" align="right"> Rs `+item.total_price+`</td>
                   </tr><tr><td height="1" colspan="4" style="border-bottom:1px solid #e4e4e4"></td>
                                                    </tr>`;
                                                    total+=item.total_price;
            });
            $('#elements').html(hair_elements);
            $('#grand_total').html(`<strong>Rs `+ total+`</strong>`);
        }
});
