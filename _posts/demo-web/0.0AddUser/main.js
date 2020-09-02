// EVENTS
const btn = document.querySelector('.btn');


// USER FORM SCRIPT

// Put DOM elements into variables
const myForm = document.querySelector('#my-form');
const nameInput = document.querySelector('#name');
const emailInput = document.querySelector('#email');
const msg = document.querySelector('.msg');
const userList = document.querySelector('#users');  // add user, add a item to ul 


// Listen for form submit
myForm.addEventListener('submit', onSubmit);

function onSubmit(e) {   //creat the function
  e.preventDefault();

  console.log(nameInput);        // it gives the actual element
  console.log(nameInput.value);  // it gives value
  
  if(nameInput.value === '' || emailInput.value === '') {
    // alert('Please enter all fields');  // alert will stop your code
    msg.classList.add('error');           // add the error class to the item
    msg.innerHTML = 'Please enter all fields';

    // Remove error after 3 seconds
    setTimeout(() => msg.remove(), 3000);
  } else {
    // Create new list item with user
    const li = document.createElement('li');

    // Add text node with input values
    li.appendChild(document.createTextNode(`${nameInput.value}: ${emailInput.value}`));

    // Add HTML
    // li.innerHTML = `<strong>${nameInput.value}</strong>e: ${emailInput.value}`;

    // Append to ul
    userList.appendChild(li);

    // Clear fields
    nameInput.value = '';
    emailInput.value = '';
  }
}