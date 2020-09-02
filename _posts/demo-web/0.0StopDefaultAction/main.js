const form = document.querySelector('form');
const fname = document.getElementById('fname');
const lname = document.getElementById('lname');
const para = document.querySelector('p');

form.onsubmit = function(e) {
    if (fname.value === '' || lname.value === '') {
      e.preventDefault();
      para.textContent = 'You need to fill in both names!';
    }
}