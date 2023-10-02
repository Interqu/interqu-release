// const videoScoreBar = document.getElementById('video-progress');
// const audioScoreBar = document.getElementById('audio-progress');
// const contentScoreBar = document.getElementById('content-progress');
// const overallScoreDiv = document.getElementById('overall-progress');

// // Update the scores based on the test results
// videoScoreBar.style.width = '30%';
// videoScoreBar.style.backgroundColor = "red";
// audioScoreBar.style.width = '90%';
// audioScoreBar.style.backgroundColor = "green";
// contentScoreBar.style.width = '60%';
// contentScoreBar.style.backgroundColor = "orange";

// // Calculate and display the overall score
// const overallScore = (30 + 90 + 60) / 3;
// overallScoreDiv.textContent = `Overall Score: ${overallScore}%`;
var inputBarValue = 67; // Example input value (can be changed dynamically)

var progressBar = document.getElementById("videoBar");
progressBar.style.width = inputBarValue + "%";

var hue = (inputBarValue / 100) * 120; // Calculate hue value (from green to red)
var saturation = 100; // Set saturation to 100%
var lightness = 50; // Set lightness to 50%

progressBar.style.backgroundColor =
  "hsl(" + hue + ", " + saturation + "%, " + lightness + "%)";

var inputBarValue = 19; // Example input value (can be changed dynamically)

var progressBar = document.getElementById("audioBar");
progressBar.style.width = inputBarValue + "%";

var hue = (inputBarValue / 100) * 120; // Calculate hue value (from green to red)
var saturation = 100; // Set saturation to 100%
var lightness = 50; // Set lightness to 50%

progressBar.style.backgroundColor =
  "hsl(" + hue + ", " + saturation + "%, " + lightness + "%)";

var inputBarValue = 86; // Example input value (can be changed dynamically)

var progressBar = document.getElementById("contentBar");
progressBar.style.width = inputBarValue + "%";

var hue = (inputBarValue / 100) * 120; // Calculate hue value (from green to red)
var saturation = 100; // Set saturation to 100%
var lightness = 50; // Set lightness to 50%

progressBar.style.backgroundColor =
  "hsl(" + hue + ", " + saturation + "%, " + lightness + "%)";

var inputBarValue = 62; // Example input value (can be changed dynamically)

var progressBar = document.getElementById("overallBar");
progressBar.style.width = inputBarValue + "%";

var hue = (inputBarValue / 100) * 120; // Calculate hue value (from green to red)
var saturation = 100; // Set saturation to 100%
var lightness = 50; // Set lightness to 50%

progressBar.style.backgroundColor =
  "hsl(" + hue + ", " + saturation + "%, " + lightness + "%)";
