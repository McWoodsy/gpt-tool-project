// Get the current page name from the URL path
const currentPage = window.location.pathname;


//  Original code: **{
// //  Execute getColors() when submit button is clicked
const form = document.querySelector("#form");
    form.addEventListener("submit", function (e) {
      e.preventDefault();
      getColors();
    });


//  NEW
//  Performs the POST request
// function getColors() {
//     let query = form.elements.query.value;
//     query = urlFormatter(query);
//     console.log("QUERY :   " + query);
  
//     const url = "http://127.0.0.1:8080/createColorPalette/" + query
//       fetch("/palette/" + query, {
//         method: "POST",
//         headers: {
//           "Content-Type": "application/x-www-form-urlencoded"
//         } !!! ","
//       })
//       .then((response) => response.json())
//       .then(data => {
//         const color_array = data;
//         console.log("color array : " + color_array);
//         const container = document.querySelector(".container");
//         createColorBoxes(color_array,container);
//       });
// }

//  ORIGINAL
function getColors() {
  let query = form.elements.query.value;
  query = urlFormatter(query);
  console.log("QUERY :   " + query);    
  fetch("/createColorPalette/" + query, {
      method: "POST",
      headers: {
          "Content-Type": "application/x-www-form-urlencoded"
      }
  })
  .then((response) => {
      if (!response.ok) {
          throw new Error('Network response was not ok');
      }
      // Check if response is empty
      if (response.status === 204) {
          throw new Error('Empty response from server');
      }
      return response.json(); // Parse response as JSON and return the result 
  })
  .then(data => {
      const color_array = data;
      const container = document.querySelector(".container");
      createColorBoxes(color_array, container);
  })
  .catch(error => {
      console.error('Error fetching color data:', error);
      // Handle error here
  });
}



//  Renders color boxes based on array size and contents
function createColorBoxes(color_array,parent) {
    parent.innerHTML = "";
    for(const color of color_array) {
        const div = document.createElement("div");
        div.classList.add("color");
        div.style.backgroundColor = color;
        div.style.width = `calc(100%/ ${color_array.length})`
        div.addEventListener("click", function () {
        navigator.clipboard.writeText(color);
        })
        const span = document.createElement("span");
        span.innerText = color;
        div.appendChild(span);
        parent.appendChild(div);
    }
}

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

function urlDeformatter(src) {
  return src.replace(/\+/g, " ");
}


function urlFormatter(src) {
  return src.replace(/ /g, "+");
}
