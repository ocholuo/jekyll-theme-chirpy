
var ftimg = null;
var bgimg = null;


function ftupLoad(){
    var imgcanvas = document.getElementById('ftpic');
    var fileinput = document.getElementById('ftinput');
    ftimg = new SimpleImage(fileinput);
    ftimg.drawTo(imgcanvas);
}


function bgupLoad(){
    var imgcanvas = document.getElementById('bgpic');
    var fileinput = document.getElementById('bginput');
    bgimg = new SimpleImage(fileinput);
    bgimg.drawTo(imgcanvas);
}


function clearComposite() {
    var FGCanvas = document.getElementById("ftpic");
    var BGCanvas = document.getElementById("bgpic");
    var ctxFG = FGCanvas.getContext("2d");
    var ctxBG = BGCanvas.getContext("2d");  
    ctxFG.clearRect(0,0, FGCanvas.width, FGCanvas.height);
    ctxBG.clearRect(0,0, BGCanvas.width, BGCanvas.height);
    FGCanvas.height = 2;
    BGCanvas.height = 2;
    FGCanvas.width = 2;
    BGCanvas.width = 2;
}
  

function doComposite(){
    if (ftimg == null || !ftimg.complete()){
        alert("Foreground image not loaded");
        return;
    }
    if (bgimg == null || !bgimg.complete()){
        alert("Background image not loaded");
        return;
    }
    var outimg = new SimpleImage(ftimg.getWidth(), ftimg.getHeight());
    var imgcanvas = document.getElementById('ftpic');

    for(var pixel of ftimg.values()){
        var x = pixel.getX();
        var y = pixel.getY();
        
        if(pixel.getGreen() > 230){
            var newPixel = bgimg.getPixel(x,y);
            outimg.setPixel(x,y,newPixel);
        }
        else {
            outimg.setPixel(x,y,pixel);
        }
    }
    clearComposite();
    outimg.drawTo(imgcanvas);
}