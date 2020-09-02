
/* 
build alter to alert()

assignment: 
https://developer.mozilla.org/en-US/docs/Learn/JavaScript/Building_blocks/Build_your_own_function

Active learning: Let's build a function
The custom function we are going to build will be called displayMessage(). It will display a custom message box on a web page and will act as a customized replacement for a browser's built-in alert() function. 
*/

const btn = document.querySelector('button');
btn.onclick = function() {
    displayMessage('Your inbox is almost full — delete some mails', 'warning');
    // displayMessage('Brian: Hi there, how are you today?','chat');
};

function displayMessage(msgText, msgType) {

    // DOM API function
    // select the <html> element and store a reference to it in a constant called html
    const html = document.querySelector('html');

    const panel = document.createElement('div');
    panel.setAttribute('class', 'msgBox');
    html.appendChild(panel);

    const msg = document.createElement('p');
    msg.textContent = msgText;
    panel.appendChild(msg);

    const closeBtn = document.createElement('button');
    closeBtn.textContent = 'x';
    panel.appendChild(closeBtn);
    
    closeBtn.onclick = function() {
      panel.parentNode.removeChild(panel);
    }

    if (msgType === 'warning') {
        msg.style.backgroundImage = 'url(imag/warning.png)';
        panel.style.backgroundColor = 'red';
      } else if (msgType === 'chat') {
        msg.style.backgroundImage = 'url(imag/chat.png)';
        panel.style.backgroundColor = 'aqua';
      } else {
        msg.style.paddingLeft = '20px';
      }
}
