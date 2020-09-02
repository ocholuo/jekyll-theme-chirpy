
/*
Project brief
Generates a silly story when the "Generate random story" button is pressed.
Replaces the default name "Bob" in the story with a custom name, only if a custom name is entered into the "Enter custom name" text field before the generate button is pressed.
Converts the default US weight and temperature quantities and units in the story into UK equivalents if the UK radio button is checked before the generate button is pressed.
Will generate another random silly story if you press the button again (and again...)

MDN
Learn web developmentSee JavaScript — Dynamic client-side scriptingSee JavaScript First StepsSilly story generator

link: https://developer.mozilla.org/en-US/docs/Learn/JavaScript/First_steps/Silly_story_generator
*/


const customName = document.getElementById('customname');
const randomize = document.querySelector('.randomize');
const story = document.querySelector('.story');

function randomValueFromArray(array){
    const random = Math.floor(Math.random()*array.length);
    return array[random];
}

const storyText = 'It was 94 fahrenheit outside, so :insertx: went for a walk. When they got to :inserty:, they stared in horror for a few moments, then :insertz:. Bob saw the whole thing, but was not surprised — :insertx: weighs 300 pounds, and it was a hot day.'

let insertX = ['Willy the Goblin', 'Big Daddy', 'Father Christmas']

let insertY = ['the soup kitchen', 'Disneyland', 'the White House']

let insertZ = ['spontaneously combusted', 'melted into a puddle on the sidewalk', 'turned into a slug and crawled away']

randomize.addEventListener('click', result);

function result() {
    let newStory = storyText
    let xItem = randomValueFromArray(insertX)
    let yItem = randomValueFromArray(insertY)
    let zItem = randomValueFromArray(insertZ)

    newStory = newStory.replace(':insertx:',xItem)
    newStory = newStory.replace(':insertx:',xItem)
    newStory = newStory.replace(':inserty:',yItem)
    newStory = newStory.replace(':insertz:',zItem)

    if(customName.value !== '') {
      let name = customName.value;
      newStory = newStory.replace('Bob', name);
    }

    if(document.getElementById("uk").checked) {
      let weight = Math.round(3300*0.071429) + ' stones';
      let temperature =  Math.round((94-32)/1.8) + ' centigrade';
      newStory = newStory.replace('300 pounds', weight);
      newStory = newStory.replace('94 fahrenheit', temperature); 

    }

    story.textContent = newStory;
    story.style.visibility = 'visible';

  // document.querySelector('html').style.backgroundColor = 'red';
}


