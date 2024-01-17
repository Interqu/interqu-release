
var posDoc = document.getElementById("questions");
onsole.log(questions)
for (let question in questions) {
    console.log(question)
    var option = document.createElement("option");
    option.id = question;
    option.value = question;
    option.innerHTML = question;
    posDoc.append(option)
}