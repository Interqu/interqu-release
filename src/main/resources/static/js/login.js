const signUpButton = document.getElementById("signUp");
const signInButton = document.getElementById("signIn");
const container = document.getElementById("container");

signUpButton.addEventListener("click", () => {
  container.classList.add("right-panel-active");
});

signInButton.addEventListener("click", () => {
  container.classList.remove("right-panel-active");
});

function inputValidity() {
  let valid = document.getElementsByClassName("field_input");
  console.log(valid);
  for (let i = 0; i < valid.length; i++) {
    if (!valid[i].checkValidity()) {
      valid.classList.add("input_error");
    } else {
      valid.classList.remove("input_error");
    }
  }
}
