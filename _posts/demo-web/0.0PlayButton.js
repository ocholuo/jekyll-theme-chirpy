

// <button>Display video</button>

// <div class="hidden">
//   <video>
//     <source src="rabbit320.mp4" type="video/mp4">
//     <source src="rabbit320.webm" type="video/webm">
//     <p>Your browser doesn't support HTML5 video. Here is a <a href="rabbit320.mp4">link to the video</a> instead.</p>
//   </video>
// </div>

// shows and hides a <div> with a <video> element inside it: 


// hanging the class attribute on the <div> from hidden to showing
btn.onclick = function() {
  videoBox.setAttribute('class', 'showing');
}

// when the area of the <div> outside the video is clicked, the box should be hidden again;
videoBox.onclick = function() {
  videoBox.setAttribute('class', 'hidden');
};


// when the video itself is clicked, the video should start to play.
video.onclick = function() {
  e.stopPropagation();
  video.play();
};

