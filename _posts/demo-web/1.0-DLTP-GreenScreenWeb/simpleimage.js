// image needed to seed "sized" image
var EMPTY_IMAGE = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAQAAAAnZu5uAAAAAXNSR0IArs4c6QAAABVJREFUeJxiYPgPhyQwAQAAAP//AwCgshjoJhZxhgAAAABJRU5ErkJggg=='
// number of canvases created to hold images
var globalCanvasCount = 0;


// Note there is an Image built in, so don't use that name.
// Represents one pixel in a SimpleImage, supports rgb get/set.
SimplePixel = function(image, x, y) {
    __funCheck("SimplePixel", 3, arguments.length);
    this.simple_image = image;
    this.x = x;
    this.y = y;
};

SimplePixel.prototype = {
    constructor: SimplePixel,
    getX: function () {
        __funCheck("getX", 0, arguments.length);
        return this.x;
    },
    getY: function () {
        __funCheck("getY", 0, arguments.length);
        return this.y;
    },
    getRed: function () {
        __funCheck("getRed", 0, arguments.length);
        return this.simple_image.getRed(this.x, this.y);
    },
    getGreen: function () {
        __funCheck("getGreen", 0, arguments.length);
        return this.simple_image.getGreen(this.x, this.y);
    },
    getBlue: function () {
        __funCheck("getBlue", 0, arguments.length);
        return this.simple_image.getBlue(this.x, this.y);
    },
    getAlpha: function () {
        __funCheck("getAlpha", 0, arguments.length);
        return this.simple_image.getAlpha(this.x, this.y);
    },
    setRed: function (val) {
        __funCheck("setRed", 1, arguments.length);
        this.simple_image.setRed(this.x, this.y, val);
    },
    setGreen: function (val) {
        __funCheck("setGreen", 1, arguments.length);
        this.simple_image.setGreen(this.x, this.y, val);
    },
    setBlue: function (val) {
        __funCheck("setBlue", 1, arguments.length);
        this.simple_image.setBlue(this.x, this.y, val);
    },
    setAlpha: function (val) {
        __funCheck("setAlpha", 1, arguments.length);
        this.simple_image.setAlpha(this.x, this.y, val);
    },
    setAllFrom: function (pixel) {
        __funCheck("setAllFrom", 1, arguments.length);
        this.simple_image.setRed(this.x, this.y, pixel.getRed());
        this.simple_image.setGreen(this.x, this.y, pixel.getGreen());
        this.simple_image.setBlue(this.x, this.y, pixel.getBlue());
        this.simple_image.setAlpha(this.x, this.y, pixel.getAlpha());
    },
    toString: function () {
        return "r:" + this.getRed() + " g:" + this.getGreen() + " b:" + this.getBlue();
    },
    // Render pixel as string
    getString: function () {
        return this.toString();
    }
};


// Makes an invisible canvas, inited either with a url, size, or an existing htmlImage
// maybe: could make this work with another SimpleImage too.
SimpleImage = function () {
    if (arguments.length < 0 || arguments.length > 2) {
        __funCheck("SimpleImage", 1, arguments.length);
        return null;
    }
    // function map for to support "overloaded constructor"
    var funMap = [
        function () {
            return __makeHTMLImage(EMPTY_IMAGE);
        },
        function (source) {
            if (source instanceof HTMLImageElement) {
                return source;
            }
            else if (typeof source == "string") {
                return __makeHTMLImageFromURL(source, this);
            }
            else if (source instanceof HTMLInputElement && source.type == 'file') {
                return __makeHTMLImageFromInput(source.files[0], this);
            }
            else if (source instanceof SimpleImage) {
                return __makeHTMLImage(source.canvas.toDataURL(), source.name, this);
            }
            else if (source instanceof HTMLCanvasElement) {
                return __makeHTMLImage(source.toDataURL(), source.id, this);
            }
            else {
                __throwError('Unrecognized value used to create a SimpleImage: ' + source);
            }
        },
        function (width, height) {
            if (width > 0 && height > 0) {
                return __makeHTMLImageFromSize(width, height, this);
            }
            else {
                __throwError('You tried to create a SimpleImage with a negative width or height [' + width + 'x' + height + ']');
            }
        }
    ];

    var htmlImage = funMap[arguments.length].apply(this, arguments);
    this.canvas = __makeHTMLCanvas('SimpleImageCanvas');
    this.context = this.canvas.getContext('2d');
	if (htmlImage != null && htmlImage.complete) {
        this.__init(htmlImage);
	}
	else {
        // when image is loaded, it will fill this in
        this.imageData = null;
	}
    this.ACCEPTED_FILES = 'image.*';
}


SimpleImage.prototype = {
    constructor: SimpleImage,
    // this should not be called publicly, but it should not hurt things if it does
    __init: function (htmlImage, imageData) {
        try {
            this.name = htmlImage.id;
            this.width = htmlImage.width;
            this.height = htmlImage.height;
            this.canvas.width = this.width;
            this.canvas.height = this.height;
            this.context.drawImage(htmlImage, 0, 0, this.width, this.height);
            if (imageData == null) {
                this.imageData = this.context.getImageData(0, 0, this.width, this.height);
            }
            else {
                this.imageData = imageData;
            }
        }
        catch (err) {
            console.log(err);
            __throwError('The name you used to create a SimpleImage was not correct: ' + htmlImage.id);
        }
    },
    complete: function () {
        return this.imageData != null;
    },
    getWidth: function () {
        __funCheck("getWidth", 0, arguments.length);
        return this.width;
    },
    getHeight: function () {
        __funCheck("getHeight", 0, arguments.length);
        return this.height;
    },
    getRed: function (x, y) {
        __funCheck("getRed", 2, arguments.length);
        return this.imageData.data[this.getIndex("getRed", x, y)];
    },
    getGreen: function (x, y) {
        __funCheck("getGreen", 2, arguments.length);
        return this.imageData.data[this.getIndex("getGreen", x, y) + 1];
    },
    getBlue: function (x, y) {
        __funCheck("getBlue", 2, arguments.length);
        return this.imageData.data[this.getIndex("getBlue", x, y) + 2];
    },
    getAlpha: function (x, y) {
        __funCheck("getAlpha", 2, arguments.length);
        return this.imageData.data[this.getIndex("getAlpha", x, y) + 3];
    },
    // Computes index into 1-d array, and checks correctness of x,y values
    getIndex: function (funName, x, y) {
        __funCheck("getIndex", 3, arguments.length);
        __rangeCheck(x, 0, this.width, funName, "x", "wide");
        __rangeCheck(y, 0, this.height, funName, "y", "tall");
        return (Math.floor(x) + Math.floor(y) * this.width) * 4;
    },
    // Gets the pixel object for this x,y.
    // Changes to the pixel write back to the image.
    getPixel: function (x, y) {
        __funCheck("getPixel", 2, arguments.length);
        __rangeCheck(x, 0, this.width, "getPixel", "x", "wide");
        __rangeCheck(y, 0, this.height, "getPixel", "y", "tall");
        return new SimplePixel(this, x, y);
    },

    setRed: function (x, y, value) {
        __funCheck("setRed", 3, arguments.length);
        this.imageData.data[this.getIndex("getRed", x, y)] = __clamp(value);
    },
    setGreen: function (x, y, value) {
        __funCheck("setGreen", 3, arguments.length);
        this.imageData.data[this.getIndex("getGreen", x, y) + 1] = __clamp(value);
    },
    setBlue: function (x, y, value) {
        __funCheck("setBlue", 3, arguments.length);
        this.imageData.data[this.getIndex("getBlue", x, y) + 2] = __clamp(value);
    },
    setAlpha: function (x, y, value) {
        __funCheck("setAlpha", 3, arguments.length);
        this.imageData.data[this.getIndex("getAlpha", x, y) + 3] = __clamp(value);
    },
    setPixel: function (x, y, pixel) {
        __funCheck("setPixel", 3, arguments.length);
        __rangeCheck(x, 0, this.width, "setPixel", "x", "wide");
        __rangeCheck(y, 0, this.height, "setPixel", "y", "tall");
        this.setRed(x, y, pixel.getRed());
        this.setBlue(x, y, pixel.getBlue());
        this.setGreen(x, y, pixel.getGreen());
        this.setAlpha(x, y, pixel.getAlpha());
    },
    setSize: function (width, height) {
        __funCheck("setSize", 2, arguments.length);
        width = Math.floor(width);
        height = Math.floor(height);
        if (width > 0 && height > 0) {
            __flush(this.context, this.imageData);
            this.imageData = __changeSize(this.canvas, width, height);
            this.width = width;
            this.height = height;
            this.canvas.width = width;
            this.canvas.height = height;
        }
        else {
            __throwError("You tried to set the size of a SimpleImage to a negative width or height [" + width + "x" + height + "]");
        }
    },
    // Draws to the given canvas, setting its size.
    // Used to implement printing of an image.
    drawTo: function (toCanvas) {
        __flush(this.context, this.imageData);
        if (this.imageData != null) {
            toCanvas.width = this.width;
            toCanvas.height = this.height;
            __flush(this.context, this.imageData);
            toCanvas.getContext("2d").drawImage(this.canvas, 0, 0, toCanvas.width, toCanvas.height);
        }
        else {
            var myself = this;
            setTimeout(function() {
                myself.drawTo(toCanvas);
            }, 100);
        }
    },
    // Export an image as an array of pixels for the for-loop.
    toArray: function () {
        __funCheck("toArray", 0, arguments.length);
        var array = new Array();
        // 1. simple-way (this is as good or faster in various browser tests)
        // var array = new Array(this.getWidth() * this.getHeight()); // 2. alloc way
        // var i = 0;  // 2.
        // nip 2012-7  .. change to cache-friendly y/x ordering
        // Non-firefox browsers may benefit.
        for (var y = 0; y < this.getHeight(); y++) {
            for (var x = 0; x < this.getWidth(); x++) {
                  //array[i++] = new SimplePixel(this, x, y);  // 2.
                  array.push(new SimplePixel(this, x, y));  // 1.
            }
        }
        return array;
    },
    // Support iterator within for loops (eventually)
    values: function() {
        __funCheck("values", 0, arguments.length);
        return this.toArray();
    },
    // better name than values if we have to use it
    pixels: function() {
        return this.values();
    }
};


// Private helper functions, add __ to reduce chance they will conflict with user's method names
function __makeHTMLCanvas (prefix) {
    var canvas = document.createElement("canvas");
    canvas.id = prefix + globalCanvasCount;
    canvas.style = 'display:none';
    canvas.innerHTML = 'Your browser does not support HTML5.'
    globalCanvasCount++;
    return canvas;
}

// wrap image data in HTML element
function __makeHTMLImage (url, name, simpleImage, loadFunc) {
    var img = new Image();
    if (loadFunc == null) {
        loadFunc = function() {
            simpleImage.__init(this);
            console.log('loaded image: ' + simpleImage.name);
        }
    }
    img.onload = loadFunc;
    img.src = url;
    img.id = name;
    img.style = 'display:none';
    return img;
}

// get image from uploaded file input
function __makeHTMLImageFromInput (file, simpleImage) {
    console.log("creating image: " + file.name);
    var reader = new FileReader();
    reader.onload = function() {
        __makeHTMLImage(this.result, file.name.substr(file.name.lastIndexOf('/') + 1), simpleImage);
    }
    reader.readAsDataURL(file);
    return null;
}

// create an empty image of the given size
function __makeHTMLImageFromSize (width, height, simpleImage) {
    var name = width + "x" + height;
    console.log("creating image: " + name);
    var img = __makeHTMLImage(EMPTY_IMAGE, name, simpleImage);
    img.width = width;
    img.height = height;
    return img;
}

function __makeHTMLImageFromURL (url, simpleImage) {
    console.log("creating image: " + url);
    if (url.substr(0, 4) != 'http') {
        return __makeHTMLImage(url, url, simpleImage);
    }
    else {
        // doesn't work --- loading an image taints the canvas so we cannot use it :(
        //var canvas = __makeHTMLCanvas("url");
        //var contaminatedImage = __makeHTMLImage(url, url, simpleImage, function() {
        //    this.id = url;
        //    this.crossOrigin = 'Anonymous';
        //    __drawImageToCanvas(this, canvas);
        //    var imageData = canvas.getContext('2d').getImageData(0, 0, this.width, this.height);
        //    simpleImage.__init(this, imageData);
        //});
        alert('Sorry, unfortunately you cannot create a SimpleImage from an abritrary URL: ' + url);
        return null;
    }
}

// Clamp values to be in the range 0..255. Used by setRed() et al.
function __clamp (value) {
    return Math.max(0, Math.min(Math.floor(value), 255));
}

// Push accumulated local changes out to the screen
function __flush (context, imageData) {
    if (imageData != null) {
        context.putImageData(imageData, 0, 0, 0, 0, imageData.width, imageData.height);
    }
}

function __drawImageToCanvas (htmlImage, canvas) {
    if (htmlImage.complete) {
        canvas.width = htmlImage.width;
        canvas.height = htmlImage.height;
        canvas.getContext('2d').drawImage(htmlImage, 0, 0, htmlImage.width, htmlImage.height);
    }
    else {
        setTimeout(function() {
            __drawImageToCanvas(htmlImage, canvas);
        }, 100);
    }
}

// Change the size of the image to the given, scaling the pixels.
function __changeSize (canvasOld, newWidth, newHeight) {
    var canvasNew = __makeHTMLCanvas('setSize_');
    canvasNew.width = newWidth;
    canvasNew.height = newHeight;
    // draw old canvas to new canvas
    var contextNew = canvasNew.getContext("2d");
    contextNew.drawImage(canvasOld, 0, 0, newWidth, newHeight);
    return contextNew.getImageData(0, 0, newWidth, newHeight);
}


// Some general utility functions
// Call this to abort with a message.
function __throwError (message) {
    throw new Error(message);
}

// Called from user-facing functions to check number of arguments
function __funCheck (funcName, expectedLen, actualLen) {
    if (expectedLen != actualLen) {
        var s1 = (actualLen == 1) ? "" : "s";  // pluralize correctly
        var s2 = (expectedLen == 1) ? "" : "s";
        var message = "You tried to call " + funcName + " with " + actualLen + " value" + s1 + 
                      ", but it expects " + expectedLen + " value" + s2 + ".";
        // someday: think about "values" vs. "arguments" here
        __throwError(message);
    }
}

function __rangeCheck (value, low, high, funName, coordName, size) {
    if (value < low || value >= high) {
        var message = "You tried to call " + funName + " for a pixel with " + coordName + "-coordinate of " + value + 
                      " in an image that is only " + high + " pixels " + size + 
                      " (valid " + coordName + " coordinates are " + low + " to " + (high-1) + ").";
        __throwError(message);
    }
}











var FGimage;
var BGimage;

function uploadFG() {
  var imgCanvas = document.getElementById("C1");
  var fileinput = document.getElementById("FGinput");
  FGimage = new SimpleImage(fileinput);
  FGimage.drawTo(imgCanvas);
}

function uploadBG() {
  var imgCanvas = document.getElementById("C2");
  var fileinput = document.getElementById("BGinput");
  BGimage = new SimpleImage(fileinput);
  BGimage.drawTo(imgCanvas)
  ;
}

function clearCanvas() {
  var FGCanvas = document.getElementById("C1");
  var BGCanvas = document.getElementById("C2");
  var ctxFG = FGCanvas.getContext("2d");
  var ctxBG = BGCanvas.getContext("2d");  
  ctxFG.clearRect(0,0, FGCanvas.width, FGCanvas.height);
  ctxBG.clearRect(0,0, BGCanvas.width, BGCanvas.height);
  FGCanvas.height = 2;
  BGCanvas.height = 2;
  FGCanvas.width = 2;
  BGCanvas.width = 2;
}

function composite() {
  if (FGimage == null || ! FGimage.complete()) {
    alert("Foreground not loaded!")
    return;
  }
    if (BGimage == null || ! BGimage.complete()) {
    alert("Background not loaded!")
  }
  var outputImage = new SimpleImage(FGimage.getWidth(),FGimage.getHeight());
  var FGCanvas = document.getElementById("C1");
  for (var pixel of FGimage.values()) {
    var x = pixel.getX();
    var y = pixel.getY();
    if (pixel.getGreen() > 230) {
      var BGPixel = BGimage.getPixel(x, y);
      outputImage.setPixel(x, y, BGPixel);
    }
    else {
      outputImage.setPixel(x, y, pixel);
    }
  }
  clearCanvas()
  outputImage.drawTo(FGCanvas);
}






<html>
  <script src='https://www.dukelearntoprogram.com/course1/common/js/image/SimpleImage.js'></script>
  <body>
  <h1> Green Screen Algorithm</h1>
  <canvas id="C1">
  </canvas>
    <canvas id="C2">
  </canvas>
    <p> 
      Foreground: 
      <input type="file" multiple="false" accept="image/*" id="FGinput" onchange="uploadFG()" >
    </p>
    <p>
      Background: <input type="file" multiple="false" accept="image/*" id="BGinput" onchange="uploadBG()" >
    </p>
    <p> 
      <input type="button" value="Create Composite" onclick="composite()">
      <input type="button" value="Clear Canvas" onclick="clearCanvas()">
    </p>
  </body>     
</html>
  
