$("#completion").hide();
$.ajax({
        url: "/stylist/getSlots",
        type: 'GET',
        dataType: 'json',       
        success: function(res) { 
        var customers_details=``;
            
            $.each(res , function(index, item) {
           var endDate=new Date(item.slotEndDateTime);
           const startDate=new Date(item.slotStartDateTime);
                  customers_details+=`<tr>
                            <td>`+item.name+`</td>
                            <td class="number">`+item.number+`</td>
                            <td>`+item.address+`</td>
                            <td>`+startDate.getDate()+`-`+startDate.getMonth()+`-`+startDate.getFullYear()+`  `+tConvert(('0' +startDate.getHours()).slice(-2)+':'+('0' + startDate.getMinutes()).slice(-2))+`</td>
                            <td>`+endDate.getDate()+`-`+endDate.getMonth()+`-`+endDate.getFullYear()+`  `+tConvert(('0' + endDate.getHours()).slice(-2)+':'+('0' + endDate.getMinutes()).slice(-2))+`</td>
                            <td><button class="button button1 bill">Bill</button></td>
                            <td><button class="button button1 completed">Completed</button></td>
                        </tr>`;
            });
            $('#customers').html(customers_details);
            $('.stylistname').html(res.name);
        }
});


$('#customers' ).on( 'click', '.completed',function(){
var value=$(this).parents().eq(2).html();
var number=$(value).find('.number').html();
$(this).parents().eq(1).attr('id', 'selected');
$.ajax({
        url: "/stylist/changeStatus?customer="+number,
        type: 'GET',
       contentType: "application/json",     
        success: function(res) { 
        if(res==="successcompleted")
        {
           $("#completion").show();
           $('#selected').addClass('completedcustomer');
           $('#selected').find('.completed').html(`Not Completed`);
           $('.completedcustomer').removeAttr('id');
        }
        else if(res==="successnotcompleted")
        {
           $("#completion").show();
           $('#selected').removeClass('completedcustomer');
           $('#selected').addClass('notcompletedcustomer');
           $('#selected').find('.completed').html(`Completed`);
           $('.notcompletedcustomer').removeAttr('id');
        }
        }       
        
     });
});


$('#customers' ).on( 'click', '.bill',function(){
var value=$(this).parents().eq(2).html();
var number=$(value).find('.number').html();
$.ajax({
        url: "/stylist/getBill?customer="+number,
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            var hair_elements=`<tr><th class="bill-heading" width="52%" align="center">Item
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
            $("#billtable").show();
            $(".closebtn").show();
            $(".home-content").hide();
        }
});
});
$('#billtable' ).on( 'click', '.closebtn',function(){
$("#billtable").hide();	
$(".home-content").show();
});			
$("#billtable").hide();			
function tConvert (time) {
  time = time.toString ().match (/^([01]\d|2[0-3])(:)([0-5]\d)$/) || [time];

  if (time.length > 1) {
    time = time.slice (1); 
    time[5] = +time[0] < 12 ? 'AM' : 'PM';
    time[0] = +time[0] % 12 || 12;
  }
  return time.join ('');
}
