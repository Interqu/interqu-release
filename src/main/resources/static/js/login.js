const signUpButton = document.getElementById("signUp");
const signInButton = document.getElementById("signIn");
const container = document.getElementById("container");

signUpButton.addEventListener("click", () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener("click", () => {
    container.classList.remove("right-panel-active");
});

function isValid(input) {
    return ((input.type == "email" && !(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(input.value))) || input.value.trim() == '');
}

let signInForm = document.getElementById("signIn-form");

signInForm.addEventListener("submit", function (e) {
    let inputs = signInForm.getElementsByClassName("field__input");
    for (var i = 0; i < inputs.length; i++) {
        if (isValid(inputs[i])) {
            let button = signInForm.getElementsByTagName("button")[0];
            button.classList.remove("wiggle")
            button.offsetWidth
            button.classList.add("wiggle")
            e.preventDefault();
            inputs[i].classList.add("input_error");
        } else {
            inputs[i].classList.remove("input_error");
        }
    }
})

let registerForm = document.getElementById("register-form");

registerForm.addEventListener("submit", function (e) {
    let inputs = registerForm.getElementsByClassName("field__input");
    for (var i = 0; i < inputs.length; i++) {
        if (isValid(inputs[i])) {
            let button = registerForm.getElementsByTagName("button")[0];
            button.classList.remove("wiggle")
            button.offsetWidth
            button.classList.add("wiggle")
            e.preventDefault();
            inputs[i].classList.add("input_error");
        } else {
            inputs[i].classList.remove("input_error");
        }
    }
})

function inputValid() {
    let doc = document.getElementsByClassName("field__input");
    for (var i = 0; i < doc.length; i++) {
        if (!isValid(doc[i])) {
            doc[i].classList.remove("input_error");
        }
    }
}
