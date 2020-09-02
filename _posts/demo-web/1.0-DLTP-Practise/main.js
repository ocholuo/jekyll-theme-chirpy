

function dochange(){
    alert('clicked buttom')
}

function myConfirmFunction(){
    var text;
    var r = confirm("Press a button!\nEither OK or Cancel.");
    if (r == true) {
        alert('You pressed OK!');
    }
    else {
        alert('Are you sure you want to cancel?');
    }
}

function changecolor(){
    var idd1 = document.getElementById('d1');
    var idd2 = document.getElementById('d2');
    idd1.className = "blueback";
    idd2.className = "yellowback";
}

function changeText(){
    var idd1 = document.getElementById('d1');
    var idd2 = document.getElementById('d2');
    idd1.innerHTML = "Good afternoon";
    idd2.innerHTML = "Good night";
}

function doLime(){
    var idd3 = document.getElementById('d3');
    idd3.style.backgroundColor = "lime";
}

function doYellow(){
    var idd3 = document.getElementById("d3");
    var ctext = idd3.getContext("2d");
    ctext.fillStyle = "yellow";
    ctext.fillRect(10,10,40,40);  
    ctext.fillRect(60,10,40,40);  
    // ctext.fillRect(x,y,40,40); 
    ctext.fillStyle = "black";
    ctext.font = "30px Arial"
    ctext.fillText("Hello", 10, 80)
}


var img;

function upLoad(){
    var imgcanvas = document.getElementById('pic');
    var fileinput = document.getElementById('finput');
    // var filename = fileinput.value;
    img = new SimpleImage(fileinput);
    img.drawTo(imgcanvas);
    // alert("Chose: " + filename);
}


function doGrey(){
    for(var pixel of img.values()){
        var avg = (pixel.getRed()+pixel.getBlue()+pixel.getGreen())/3
        pixel.setRed(avg);
        pixel.setBlue(avg);
        pixel.setGreen(avg);
    }
    var imgcanvas = document.getElementById('pic');
    img.drawTo(imgcanvas);
}
