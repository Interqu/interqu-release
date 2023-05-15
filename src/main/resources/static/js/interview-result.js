const videoScoreBar = document.getElementById('video-progress');
const audioScoreBar = document.getElementById('audio-progress');
const contentScoreBar = document.getElementById('content-progress');
const overallScoreDiv = document.getElementById('overall-progress');

// Update the scores based on the test results
videoScoreBar.style.width = '30%';
videoScoreBar.style.backgroundColor = "red";
audioScoreBar.style.width = '90%';
audioScoreBar.style.backgroundColor = "green";
contentScoreBar.style.width = '60%';
contentScoreBar.style.backgroundColor = "orange";

// Calculate and display the overall score
const overallScore = (30 + 90 + 60) / 3;
overallScoreDiv.textContent = `Overall Score: ${overallScore}%`;
