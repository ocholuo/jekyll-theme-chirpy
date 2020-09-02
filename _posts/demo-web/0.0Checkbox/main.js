function nicknameFunction(){
    if (document.getElementById('yesNick').checked){
        document.getElementById('nick').style.display="inline";
        document.getElementById('nickname').setAttribute('required',true);
    }
    else{ 
        document.getElementById('nickname').removeAttribute('required');
        document.getElementById('nick').style.display="none";
    }
}


var fruitlist = ['banana', 'orange']; 

function loadFruits(){
    document.getElementById('fruits').innerHTML = fruitlist;
}

function myFruits(){
    var singlefruit = prompt("what's you favorite fruit?:");
    fruitlist[fruitlist.length] = singlefruit;
    document.getElementById('fruits').innerHTML = fruitlist; // refresh the displayed list
}