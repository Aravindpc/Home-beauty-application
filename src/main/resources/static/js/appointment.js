$("#failed").hide();
$("#booked").hide();
$("#internet").hide();
$("#bookfailed").hide();
var stylist={name:"name", email:"email"};;
$.ajax({
        url: "/user/getAllStylists",
        type: 'GET',
        dataType: 'json', 
        success: function(res) {
            var stylists_elements=``;
            $.each(res , function(index, item) { 
                  stylists_elements+=`<div class="member">
                    <div class="avatar"></div>
                    <div class="email" style="display: none;">`+item.email+`</div>
                    <div class="name">`+item.name+`</div>
                    <div class="deselect-member">change</div>
                    <div class="deselect-date">change</div>
                    <div class="deselect-slot">change</div>
                    <div class="calendar"></div>
                    <ul  class="slots"></ul>
                    <div class="formid"></div></div>`;
            });
            $('#stylists').html(stylists_elements);
        }
});

$('#stylists' ).on( 'click', '.member',function(e){
if (!$(this).hasClass('selected')){
stylist.name=$(this).find('.name').html();
stylist.email=$(this).find('.email').html();
  $(this).addClass('selected');
  $('.wrap').addClass('member-selected');
  addCalendar($(this).find('.calendar'));
  addForm($(this).find('.formid'));
}
e.preventDefault();
e.stopPropagation();
});

$( '#stylists' ).on( 'click', '.deselect-member, .restart', function(e){
$('.member').removeClass('selected');
$('.formid').empty();
$('.slots').empty();
$('.calendar').empty();
$('.wrap').removeClass('member-selected date-selected slot-selected booking-complete');
e.preventDefault();
e.stopPropagation();
});

$( '#stylists' ).on( 'click', '.deselect-date', function(e){
$('.wrap').removeClass('date-selected slot-selected');
$('.slots').empty();
$('.calendar *').removeClass('selected');
e.preventDefault();
e.stopPropagation();
});

$( '#stylists' ).on( 'click', '.deselect-slot',function(e){
$('.wrap').removeClass('slot-selected');
$('.slots *').removeClass('selected');
e.preventDefault();
e.stopPropagation();
});

$('.form').on('submit', function(e){
$('.wrap').toggleClass('booking-complete');
e.preventDefault();
e.stopPropagation();
})

function invokeCalendarListener(){
$('.calendar td:not(.disabled)').on('click', function(e){
  addSlots();
  var date = $(this).html();
  var day = $(this).data('day');
var month = $(this).data('month');
  $('.date').html(day + ',  ' + date);
  $(this).addClass('selected');
  setTimeout(function(){
    $('.wrap').addClass('date-selected');
  },10);
var today = new Date();
var month = today.getMonth()+1;
var year = today.getFullYear();
var dateinput = year+"-"+("0" + month).slice(-2) + "-"+("0" + date).slice(-2);
$("#date").val(dateinput);
e.preventDefault();
e.stopPropagation();
});
}

function removeAll()
{
   $('.member').removeClass('selected');
   $('.wrap').removeClass('member-selected date-selected slot-selected booking-complete');
}
function invokeSlotsListener(){
$('.slots li').on('click', function(e){
  $(this).addClass('selected');
  $('.wrap').addClass('slot-selected');
  setTimeout(function(){
    $('.selected.member input[name="name"]').focus();
  }, 700);
        var span=$(this).html();
        var startTime=$('.selected.member .slots .selected span.startTime').html();
        var endTime=$('.selected.member .slots .selected span.endTime').html();

	    var date=$("#date").val();
		var startDateTime=date+" "+startTime;
		var endDateTime=date+" "+endTime;
		var formData = {"slotStartDateTime": startDateTime,
       "slotEndDateTime":endDateTime
        };
		 $.ajax({
		 type: "POST",
        contentType: "application/json",
        url: "/user/isAvailable?stylist="+stylist.email,
         data: JSON.stringify(formData),
       processData: false,
         success: function (e) {
          if(e==="Available"){  
		     $("#start_time").val(startTime);
             $("#end_time").val(endTime);
          }
          else{ 
             $("#failed").show();
             $('.wrap').removeClass('slot-selected');
             $('.slots *').removeClass('selected');           
          }
         },
        error: function (e) {
         $("#failed").show();
        }
         });

  e.preventDefault();
  e.stopPropagation();
});
}

function addSlots(container){
var number = 9;
var time = 0;
var endings = [':00',':30'];
var starttimeDisplay = '';
var endtimeDisplay = '';
var slots = ''
for(var i = 1; i < 7; i++){
  starttimeDisplay =("0" + number).slice(-2) + endings[time];
endtimeDisplay=(number+1)+endings[time];
  slots += '<li>'+'<span class="startTime">'+starttimeDisplay+'</span>  -  <span class="endTime">'+endtimeDisplay +'</span></li>';
if(i%2===0)
{
number+=2;
time=0;  
}
else{
number+=1;
time=1;
}
}
$('.selected .slots').html(slots);  
invokeSlotsListener();

}
function make_appointment()
{
        var startTime=$("#start_time").val();
        var endTime=$("#end_time").val();
	    var date=$("#date").val();
		var startDateTime=date+" "+startTime;
		var endDateTime=date+" "+endTime;
		var formData = {"slotStartDateTime": startDateTime,
       "slotEndDateTime":endDateTime
        };
     $.ajax({
		 type: "POST",
         contentType: "application/json",
         url: "/user/bookSlot?stylist="+stylist.email,
         data: JSON.stringify(formData),
         processData: false,
         success: function (e) {
          if(e==="Booked"){  
           removeAll();
		   $("#booked").show();
		   
          }
          else if(e==="Not Booked")
          {
           $("#bookfailed").show();
          }
          else if(e==="No Internet")
          {
            $("#internet").show();
          }
          
         },
        error: function (e) {
         $("#bookfailed").show();
        }
         });
}
function addForm(container){
var form=`<form class="form" ><div class="form-row">
         <div class="col form-group">
           <label>Date</label>
           <input class="form-control date-input" type="text" id="date" placeholder="dd/mm/yyyy" title="Date" required readonly>
         </div>
       </div>
       <div class="form-row">
         <div class="col form-group">
           <label>Start Time</label>
           <input class="form-control time-input" type="text" placeholder="hh:mm" id="start_time" required readonly>
         </div>
         <div class="col form-group">
           <label>End Time</label>
           <input class="form-control time-input" type="text" placeholder="hh:mm" id="end_time" required readonly>
         </div>
       </div>
       <div class="form-row">
         <div class="col form-group">
           <button class="button" onclick="make_appointment()">Make Appointment</button>
         </div>
       </div></form>`
$('.member.selected div.formid').html(form);
}


function addCalendar(container){
//get dates
var today = new Date();
var day = today.getDay()
var date = today.getDate();
var month = today.getMonth();
var year = today.getFullYear();
var first = new Date();
first.setDate(1);
var startDay = first.getDay();
var dayLabels = ['S', 'M', 'T', 'W', 'T', 'F', 'S'];
var monthLengths = [31,28,31,30,31,30,31,31,30,31,30,31];
var monthNames = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
var dayNames = ['Sat', 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri'];

var current = 1 - startDay;

//assemble calendar
var calendar = '<label class="date"></label><label class="month">'+monthNames[month]+'</label> <label class="year">'+ year+'</label>';

calendar += '<table><tr>';
dayLabels.forEach(function(label){
  calendar += '<th>'+label+'</th>';
})
calendar += '</tr><tr>';
var dayClasses = '';
while( current <= 30){
  if (current > 0){
    dayClasses = '';
    today.setDate(current);
    if (today.getDay() == 0 ){
      dayClasses += ' disabled';
    }
    if (current < date){
      dayClasses += ' disabled';
    }
    if (current == date){
      dayClasses += ' today';
    }
    calendar += '<td class="'+dayClasses+'" data-day="'+dayNames[(current + startDay)%7]+'">'+current+'</td>';
  } else {
    calendar += '<td></td>';
  }
 
  if ( (current + startDay) % 7 == 0){
    calendar += '</tr><tr>';
  }
 
  current++
}

calendar += '</tr></table>';
container.html(calendar);

invokeCalendarListener();
}
