$("#success").hide();
$.ajax({
        url: "/user/salonServiceDetail/salonServiceDetailByName/Male?Type=Hair",
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            
            var hair_elements=``;
            $.each(res , function(index, item) { 
                  hair_elements+=`<div class="product">
<img src="`+item.pictureLocation+`" alt="">
<h5 class="subtitle">`+item.name+`</h5>
<p>`+item.description+`</p>
<p class="price">Rs `+item.price+`</p>
<button class="addtocart">Add</button>
</div>`;
            });
            $('#hair').html(hair_elements);
        }
});

$("#Logout").on("click",emptyCart);

$.ajax({
        url: "/user/salonServiceDetail/salonServiceDetailByName/Male?Type=Skin",
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
         
            var skin_elements=``;
            $.each(res , function(index, item) { 
                  skin_elements+=`<div class="product">
<img src="`+item.pictureLocation+`" alt="">
<h5 class="subtitle">`+item.name+`</h5>
<p>`+item.description+`</p>
<p class="price">Rs `+item.price+`</p>
<button class="addtocart">Add</button>
</div>`;
            });
            $('#skin').html(skin_elements);
        }
});


$.ajax({
        url: "/user/salonServiceDetail/salonServiceDetailByName/Male?Type=Body",
        type: 'GET',
        dataType: 'json', // added data type
        success: function(res) {
            var body_elements=``;
            $.each(res , function(index, item) { 
                 body_elements+=`<div class="product">
<img src="`+item.pictureLocation+`" alt="">
<h5 class="subtitle">`+item.name+`</h5>
<p>`+item.description+`</p>
<p class="price">Rs `+item.price+`</p>
<button class="addtocart">Add</button>
</div>`;
            });
            $('#body').html(body_elements);
        }
});

function myFunction() {
var x = document.getElementById("myTopnav");
if (x.className === "topnav") {
x.className += " responsive";
} else {
x.className = "topnav";
}
}
/* get cart total from session on load */
updateCartTotal();

/* button event listeners */
$("#emptycart").on("click", emptyCart);

$(".cards").on("click", ".addtocart",function() {addToCart(this);});


/* ADD TO CART functions */

function addToCart(elem) {
    //init
    var sibs = [];
    var getprice;
    var getsubtitle;
    var cart = [];
     var stringCart;
    //cycles siblings for product info near the add button
    while(elem =elem.previousSibling) {
        if (elem.nodeType === 3) continue; // text node
        if($(elem).hasClass("price")){
            getprice = $(elem).html();
        }
        if ($(elem).hasClass("subtitle")) {
            getsubtitle = $(elem).html();
        }
        sibs.push(elem);
    }
    //create product object
    var product = {
        subtitle : getsubtitle,
        price : getprice
    };
    //convert product data to JSON for storage
    var stringProduct = JSON.stringify(product);
    /*send product data to session storage */
    if(!sessionStorage.getItem('cart')){
        //append product JSON object to cart array
        cart.push(stringProduct);
        //cart to JSON
        stringCart = JSON.stringify(cart);
        //create session storage cart item
        sessionStorage.setItem('cart', stringCart);
        updateCartTotal();
    }
    else {
        //get existing cart data from storage and convert back into array
       cart = JSON.parse(sessionStorage.getItem('cart'));
        //append new product JSON object
        cart.push(stringProduct);
        //cart back to JSON
        stringCart = JSON.stringify(cart);
        //overwrite cart data in sessionstorage
        sessionStorage.setItem('cart', stringCart);
        updateCartTotal();
    }
}
/* Calculate Cart Total */
function updateCartTotal(){
    //init
    var total = 0;
    var price = 0;
    var items = 0;
    var subtitle = "";
    var carttable = "";
    if(sessionStorage.getItem('cart')) {
        //get cart data & parse to array
        var cart = JSON.parse(sessionStorage.getItem('cart'));
        //get no of items in cart
        items = cart.length;
        //loop over cart array
        for (var i = 0; i < items; i++){
            //convert each JSON product in array back into object
            var x = JSON.parse(cart[i]);
            //get property value of price
            price = parseFloat(x.price.split('Rs ')[1]);
            subtitle = x.subtitle;
            //add price to total
            carttable += "<tr><td>" + subtitle + "</td><td>Rs " + price.toFixed(2) + "</td></tr>";
            total += price;
        }
       
    }
    //update total on website HTML
    $("#total").html(total.toFixed(2));
    //insert saved products to cart table
    $("#carttable").html(carttable);
    //update items in cart on website HTML
    $("#itemsquantity").html(items);
}

/* User Manually empty cart */
function emptyCart() {
    //remove cart session storage object & refresh cart totals
    if(sessionStorage.getItem('cart')){
        sessionStorage.removeItem('cart');
        updateCartTotal();
    }
}
function produceBill(){
    var cart=JSON.parse(sessionStorage.getItem('cart'));
    
    var names=[];
    if(cart!=null)
    {
     items = cart.length;
        //loop over cart array
        for (var i = 0; i < items; i++){
            //convert each JSON product in array back into object
            var x = JSON.parse(cart[i]);
            names.push(x.subtitle);
        }
       
    }
    var formData = {"type": "Male",
       "names" : names
       };

		$.ajax({
		type: "POST",
        contentType: "application/json",
        url: "/user/salonServiceDetail/addServices",
        data: JSON.stringify(formData),
        processData: false,
        success: function (data) {
        if(data==="Added"){
        if(sessionStorage.getItem('cart')){
        sessionStorage.removeItem('cart');
        updateCartTotal();
        }
              $("#success").show();
              $('body').scrollTop(0);
          }          
        }
        });
}