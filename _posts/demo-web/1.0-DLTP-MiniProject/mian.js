
var imgcanvas = document.getElementById('pic');
var fileinput = document.getElementById('finput');
var img = null;

function upload(){
  img = new SimpleImage(fileinput);
  img.drawTo(imgcanvas);
}

function imageIsLoaded(){
    if( img == null || !img.complete()){
        alert("please upload a image.");
        return 0;
    }
    return 1;
}


function doGray() {
    if(imageIsLoaded()){
        for(var pixel of img.values()){
            var avg = ( pixel.getGreen() + pixel.getRed() + pixel.getBlue() )/3 ;
            pixel.setRed(avg);
            pixel.setBlue(avg);
            pixel.setGreen(avg);
        }
        img.drawTo(imgcanvas);
    }
}

function doRed() {
    if(imageIsLoaded()){
        for(var pixel of img.values()){
            var avg = ( pixel.getGreen() + pixel.getRed() + pixel.getBlue() )/3 ;
            if(avg < 128){
                pixel.setRed(2*avg);
                pixel.setGreen(0);
                pixel.setBlue(0);
            }
            else {
                pixel.setRed(255);
                pixel.setGreen(2*avg-255);
                pixel.setBlue(2*avg-255);
            }
        }
        img.drawTo(imgcanvas);
    }
}

function doRainbow(){
    if(imageIsLoaded()){
        for(var pixel of img.values()){
            var avg = ( pixel.getGreen() + pixel.getRed() + pixel.getBlue() )/3 ;
            if(pixel.getY() < img.getHeight()/7){
                //red
                if(avg < 128){
                    pixel.setRed(2*avg);
                    pixel.setGreen(0);
                    pixel.setBlue(0);
                }
                else {
                    pixel.setRed(255);
                    pixel.setGreen(2*avg-255);
                    pixel.setBlue(2*avg-255);
                }
            }
            else if(pixel.getY() < 2*img.getHeight()/7){
                //orange
                if (avg < 128) {
                    pixel.setRed(2 * avg);
                    pixel.setGreen(0.8*avg);
                    pixel.setBlue(0);
                } else {
                    pixel.setRed(255);
                    pixel.setGreen(1.2*avg-51);
                    pixel.setBlue(2 * avg - 255);
                }
            }
            else if(pixel.getY() < 3*img.getHeight()/7){
                //yellow
                if (avg < 128) {
                    pixel.setRed(2 * avg);
                    pixel.setGreen(2*avg);
                    pixel.setBlue(0);
                } else {
                    pixel.setRed(255);
                    pixel.setGreen(255);
                    pixel.setBlue(2 * avg - 255);
                }
            }
            else if(pixel.getY() < 4*img.getHeight()/7){
                //green
                if (avg < 128) {
                    pixel.setRed(0);
                    pixel.setGreen(2*avg);
                    pixel.setBlue(0);
                } else {
                    pixel.setRed(2*avg-255);
                    pixel.setGreen(255);
                    pixel.setBlue(2 * avg - 255);
                }
            }
            else if(pixel.getY() < 5*img.getHeight()/7){
                //blue
                if (avg < 128) {
                    pixel.setRed(0);
                    pixel.setGreen(0);
                    pixel.setBlue(2*avg);
                } else {
                    pixel.setRed(2*avg-255);
                    pixel.setGreen(2 * avg - 255);
                    pixel.setBlue(255);
                }
            }
            else if (pixel.getY() < 6*img.getHeight()/7) {
                //indigo
                if (avg < 128) {
                pixel.setRed(.8*avg);
                pixel.setGreen(0);
                pixel.setBlue(2*avg);
                } else {
                pixel.setRed(1.2*avg-51);
                pixel.setGreen(2 * avg - 255);
                pixel.setBlue(255);
                }
            }
            else {
                //violet
                if (avg < 128) {
                    pixel.setRed(1.6*avg);
                    pixel.setGreen(0);
                    pixel.setBlue(1.6*avg);
                } else {
                    pixel.setRed(0.4*avg+153);
                    pixel.setGreen(2 * avg - 255);
                    pixel.setBlue(0.4*avg+153);
                        }
            }
        img.drawTo(imgcanvas);
    }
}

// function ensureInImage (coordinate, size) {
//     // coordinate cannot be negative
//     if (coordinate < 0) {
//         return 0;
//     }
//     // coordinate must be in range [0 .. size-1]
//     if (coordinate >= size) {
//         return size - 1;
//     }
//     return coordinate;
// }

// function getPixelNearby (image, x, y, diameter) {{
//     var dx = Math.random() * diameter - diameter / 2;
//     var dy = Math.random() * diameter - diameter / 2;
//     var nx = ensureInImage(x + dx, image.getWidth());
//     var ny = ensureInImage(y + dy, image.getHeight());
//     return image.getPixel(nx, ny);
// }

function doBlur(){
    var outImg = new SimpleImage(img.getWidth(), img.getHeight());
    for(var pixel of img.values()){
        var x = pixel.getX();
        var y = pixel.getY();
        if (Math.random() > 0.5) {
            outImg.setPixel(x,y, pixel);
        }
        // else {
        //     var newpixel = getPixelNearby (img, x, y, 10);
        //     outImg.setPixel(x,y, newpixel);
        // }
    }
    outImg.drawTo(imgcanvas);
}

function reset() {
    var content = imgcanvas.getContext('2d');
    content.clearRect(0,0,imgcanvas.width, imgcanvas.height);
}
