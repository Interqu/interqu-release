const video = document.getElementById("video");
const videoAnalyze = document.getElementById("video-analyze");
const accessBtn = document.getElementById("access-camera");
const recordBtn = document.getElementById("record-btn");
const stopBtn = document.getElementById("stop-btn");
const restartBtn = document.getElementById("restart-btn");
const submitBtn = document.getElementById("submit-btn");
const countdown = document.getElementById("countdown");
const countdownNum = document.getElementById("countdown-num");
const question = document.getElementById("question");
const time = document.getElementById("time");
const progressBarFill = document.querySelector(".progress-bar-fill");
const duration = 120;
let chunks = [];
let mediaRecorder;

var countDownInterval;

const startCountdown = () => {
  countdown.style.display = "block";
  let count = 3;
  countdownNum.textContent = count;
  const countdownInterval = setInterval(() => {
    count--;
    countdownNum.textContent = count;
    if (count === 0) {
      clearInterval(countdownInterval);
      countdown.style.display = "none";
      mediaRecorder.start();
      stopBtn.style.display = "inline-block";
      // Set the duration in seconds

      let currentTime = 0;

      // Update the progress bar every second
      countDownInterval = setInterval(() => {
        currentTime += 1;
        const progressPercentage = (currentTime / duration) * 100;
        progressBarFill.style.width = `${progressPercentage}%`;
        var minutes = Math.floor((duration - currentTime) / 60); // Calculate minutes
        var seconds = (duration - currentTime) % 60; // Calculate seconds

        // Display the countdown
        time.innerHTML = minutes + " m " + seconds + " s";
        // Check if the progress has reached the desired duration
        if (currentTime >= duration) {
          mediaRecorder.stop();
          clearInterval(interval);
        }
      }, 1000);
    }
  }, 1000);
};

function getPresignedURL(questionId) {
  return new Promise(function (resolve, reject) {
    $.ajax({
      url: "/api/presigned-url?questionId=" + questionId,
      type: "GET",
      processData: false,
      success: resolve,
      error: reject,
    });
  });
}

async function accessCamera() {
  try {
    video.srcObject = await navigator.mediaDevices.getUserMedia({
      video: {
        width: { ideal: 1920 },
        height: { ideal: 1080 },
        frameRate: { ideal: 30 },
      },
      audio: true,
    });
    mediaRecorder = new MediaRecorder(video.srcObject);
    mediaRecorder.addEventListener("dataavailable", (event) => {
      chunks.push(event.data);
    });
    mediaRecorder.addEventListener("stop", () => {
      stopBtn.style.display = "none";
      restartBtn.style.display = "inline-block";
      submitBtn.style.display = "inline-block";
      progressBarFill.style.width = "100%";
      let source = document.createElement("source");
      source.src = URL.createObjectURL(new Blob(chunks, { type: "video/mp4" }));
      clearInterval(countDownInterval);
      video.appendChild(source);
      video.srcObject = null;
      video.autoplay = false;
      video.muted = false;
      video.controls = true;
      video.play();
    });
    recordBtn.style.display = "inline-block";
    accessBtn.style.display = "none";
  } catch (error) {
    alert("An unexpected error has occured!");
    console.error(error);
  }
}

accessBtn.addEventListener("click", accessCamera);

recordBtn.addEventListener("click", () => {
  try {
    startCountdown();
    recordBtn.style.display = "none";
  } catch (error) {
    alert("An unexpected error has occured!");
    console.error(error);
  }
});

stopBtn.addEventListener("click", () => {
  try {
    mediaRecorder.stop();
  } catch (error) {
    alert("An unexpected error has occured!");
    console.error(error);
  }
});

submitBtn.addEventListener("click", () => {
  const questionId = questionData.questionId;
  getPresignedURL(questionId)
    .then(function (response) {
      const blob = new Blob(chunks, { type: "video/mp4" });
      const formData = new FormData();
      formData.append("questionId", questionId);
      formData.append("video", blob, response["fileName"]);

      blob.metadata = response["metadata"];
      const headers = {};
      headers["Content-Type"] = "video/mp4";
      //insert metadata into headers
      Object.keys(response["metadata"]).forEach((key) => {
        headers["x-amz-meta-" + key] = response["metadata"][key];
        console.log(headers);
      });

      return new Promise(function (resolve, reject) {
        $.ajax({
          url: response["presignedURL"],
          type: "PUT",
          processData: false,
          data: blob,
          headers: headers,
          success: resolve,
          error: reject,
        });
      });
    })
    .then(function (response) {
      window.onbeforeunload = null;
      window.location = "/user/completedInterviews";
    })
    .catch(function (error) {
      alert(error);
    });
});
restartBtn.addEventListener("click", () => {
  try {
    location.reload();
  } catch (error) {
    alert("An unexpected error has occured");
    console.error("error");
  }
});

window.onbeforeunload = function () {
  return "Are you sure you want to exit?";
};

// question.textContent = ; // Replace with current question
var minutes = Math.floor(duration / 60); // Calculate minutes
var seconds = duration % 60; // Calculate seconds

// Display the countdown
time.innerHTML = minutes + " m " + seconds + " s";

accessCamera();
