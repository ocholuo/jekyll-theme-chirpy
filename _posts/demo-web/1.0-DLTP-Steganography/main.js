
var imgcanvas1 = document.getElementById('baseimg');
var baseimg = new SimpleImage(imgcanvas1);

var imgcanvas2 = document.getElementById('hideimg');
var hideimg = new SimpleImage(imgcanvas2);

var imgcanvas3 = document.getElementById('outimg');

// function checkloaded(){
//     if(img == null | !img.complete()){
//         alert("please chose your image");
//         return;
//     }
//     return 1;
// }

function upload(fileid, imgid){
    var fileinput = document.getElementById(fileid);
    var img = new SimpleImage(fileinput);
    var imgcanvas = document.getElementById(imgid);
    img.drawTo(imgcanvas);
}

function clearbitsforbasepixel(colornum){
    var clearnum =  Math.floor(colornum/16)*16;
    return clearnum;
}

function clearbitsforhidepixel(colornum){
    var clearnum =  Math.floor(colornum / 16);
    return clearnum;
}

function basechop(baseimg){
    for(var pixel of baseimg.values()){
        pixel.setRed(clearbitsforbasepixel(pixel.getRed()));
        pixel.setGreen(clearbitsforbasepixel(pixel.getGreen()));
        pixel.setBlue(clearbitsforbasepixel(pixel.getBlue()));
    }
    return baseimg;
}

function hidechop(hideimg){
    for(var pixel of hideimg.values()){
        pixel.setRed(clearbitsforhidepixel(pixel.getRed()));
        pixel.setGreen(clearbitsforhidepixel(pixel.getGreen()));
        pixel.setBlue(clearbitsforhidepixel(pixel.getBlue()));
    }
    return hideimg;
}

function combine(baseimg, hideimg){
    var outimg = new SimpleImage(baseimg.getWidth(), baseimg.getHeight());
    for(var pixel of outimg.values()){
        var x = pixel.getX();
        var y = pixel.getY();
        var bp = baseimg.getPixel(x,y);
        var hp = hideimg.getPixel(x,y);
        pixel.setBlue(bp.getBlue() + hp.getBlue());
        pixel.setRed(bp.getRed() + hp.getRed());
        pixel.setGreen(bp.getGreen() + hp.getGreen());
    }
    return outimg;
}



function create(){
    // var ctext = imgcanvas2.getContext("2d");
    // ctext.clearRect(0,0,hideimg.getWidth(), hideimg.getHeight());
    baseimg = basechop(baseimg);
    baseimg.drawTo(imgcanvas1);
  
    hideimg = hidechop(hideimg);
    var stego = combine(baseimg, hideimg);
    
}

// print(Math.floor(255 % 16));
// print(Math.floor(255 /16)*16);




