const video = document.getElementById("video");
const recordBtn = document.getElementById("record-btn");
const stopBtn = document.getElementById("stop-btn");
const countdown = document.getElementById("countdown");
const countdownNum = document.getElementById("countdown-num");
const question = document.getElementById("question");

let chunks = [];
let mediaRecorder;

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
      recordBtn.style.display = "none";
      stopBtn.style.display = "block";
    }
  }, 1000);
};

recordBtn.addEventListener("click", async () => {
  try {
    video.srcObject = await navigator.mediaDevices.getUserMedia({
      video: true,
      audio: true,
    });
    mediaRecorder = new MediaRecorder(video.srcObject);
    mediaRecorder.addEventListener("dataavailable", (event) => {
      chunks.push(event.data);
    });
    mediaRecorder.addEventListener("stop", () => {
      const blob = new Blob(chunks, { type: "video/webm" });
      const url = URL.createObjectURL(blob);
      const xhr = new XMLHttpRequest();
      xhr.open("POST", "http://localhost:8080/api/processInterview");
      xhr.send(blob);
      chunks = [];
      video.srcObject = null;
      recordBtn.style.display = "block";
      stopBtn.style.display = "none";
    });
    startCountdown();
  } catch (error) {
    console.error(error);
  }
});

stopBtn.addEventListener("click", () => {
  mediaRecorder.stop();
});

question.textContent = "What is your name?"; // Replace with current question
