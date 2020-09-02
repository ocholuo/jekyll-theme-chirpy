


// Active learning: Launch countdown!

const output = document.querySelector('.output');
output.innerHTML = '';

let i = 10;

while(i >= 0) {
 let para = document.createElement('p');
 if(i === 10) {
     para.textContent = 'Countdown ' + i;
 } else if(i === 0) {
     para.textContent = 'Blast off!';
 } else {
     para.textContent = i;
 }

 output.appendChild(para);

 i--;
}


// Active learning: Filling in a guest list

const people = ['Chris', 'Anne', 'Colin', 'Terri', 'Phil', 'Lola', 'Sam', 'Kay', 'Bruce'];
const admitted = document.querySelector('.admitted');
const refused = document.querySelector('.refused');
admitted.textContent = 'Admit: ';
refused.textContent = 'Refuse: '

while (let i=0; i < people.length; i++) {
    if (people[i]==="Phil" || people[i]==="Lola") {
        refused.textContent += people[i] + ',';
    } else {
        admitted.textContent += people[i]
    }
}

refused.textContent = refused.textContent.slice(0,refused.textContent.length-2) + '.';
admitted.textContent = admitted.textContent.slice(0,admitted.textContent.length-2) + '.';









