
let oltext = document.getElementById('image').textContent;

function upDate(previewPic){
    let div = document.getElementById('image');
    let newpiclink = previewPic.src
    div.style.backgroundImage = "url("+newpiclink+")";
    div.style.backgroundSize = "contain";
    div.style.backgroundColor = 'white';
    div.style.color = 'pink';
    div.textContent = previewPic.alt;
  }

function unDo() {
    let div = document.getElementById('image');
    div.style.backgroundColor = '';
    div.style.color = '';
    div.style.backgroundImage = '';
    div.textContent = 'Hover over an image below to display here.';
}