const form = document.getElementById("form");
const questions = form.querySelectorAll(".question");
const prevBtns = form.querySelectorAll("#prev-btn");
const nextBtns = form.querySelectorAll("#next-btn");
const inputFields = form.querySelectorAll(".input-field");
let currentQuestion = 0;

function showQuestion(index) {
  questions[currentQuestion].classList.remove("active");
  questions[currentQuestion].classList.add("fade-out");

  currentQuestion = index;

  questions[currentQuestion].classList.remove("fade-out");
  questions[currentQuestion].classList.add("active");
  inputFields[currentQuestion].focus();
}

nextBtns.forEach((btn, index) => {
  btn.addEventListener("click", () => {
    if (index !== questions.length - 1) {
      showQuestion(currentQuestion + 1);
    } else {
      form.submit();
    }
  });
});

prevBtns.forEach((btn, index) => {
  btn.addEventListener("click", () => {
    if (index !== -3) {
      showQuestion(currentQuestion - 1);
    }
  });
});

inputFields.forEach((input, index) => {
  input.addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
      event.preventDefault();
      nextBtns[index].click();
    }
  });
});

showQuestion(currentQuestion);
