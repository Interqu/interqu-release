// Add your JavaScript code here

// Example event listeners

// document.getElementById("update-profile-btn").addEventListener("click", () => {
//   // Handle profile update functionality
// });

// document.getElementById("update-password-btn").addEventListener("click", () => {
//   // Handle password update functionality
// });

// document.getElementById("delete-account-btn").addEventListener("click", () => {
//   // Handle account deletion functionality
// });
 


document.querySelector("#contactSubmit").addEventListener("click", (e) =>{
  e.preventDefault();
  let isValid = document.querySelector("#contactForm").checkValidity();
  console.log(isValid);
  if(isValid){
    e.preventDefault();
    
    reqBody = {
      "name" : document.querySelector("#contactingName").value,
      "email" : document.querySelector("#contactingEmail").value,
      "subject" : document.querySelector("#contactingSubject").value,
      "content" : document.querySelector("#contactingMessage").value,
    }

      const res = fetch("/api/user/updateUser",{
        method: 'POST',
        body: JSON.stringify(reqBody),
        headers: {
          "Content-Type" :"application/json",
        },
      
      })
      const ret = res.json();
      console.log(ret)
    
    
     

    
    

  }


});


// Tab Menu
function openTab(evt, cityName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}

document.getElementById("defaultOpen").click();


