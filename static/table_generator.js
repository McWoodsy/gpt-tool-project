// Get the current page name from the URL path
const currentPage = window.location.pathname;
console.log(currentPage)


//  Original code: **{
// //  Execute getColors() when submit button is clicked
const tableForm = document.querySelector("#table_form");
tableForm.addEventListener("submit", function (e) {
      e.preventDefault();
      getTableInfo();
    });


    // Performs the POST request for fetching chart information
function getTableInfo() {
  const options = table_form.elements.options.value;
  const characteristics = table_form.elements.characteristics.value;
  
  fetch("/table-generator", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body: new URLSearchParams({
      options: options,
      characteristics: characteristics
    })
  })
  .then((response) => {
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    return response.text();
  })
  .then(data => {
    if (data === "bar chart created") {
      //return sleep(100);
    }
  })
  .then(() => {
    // After the delay, show the chart
    showTable();
  })
  .catch(error => {
    console.error("There was a problem with the fetch operation:", error);
  });
}

function showTable(tableImageUrl) {
  const tableImg = document.getElementById("table-img");
  tableImg.src = "";
  const newWidth = 400 * 2; // Set your desired width
  const newHeight = 300 * 2; // Set your desired height
  tableImg.style.width = newWidth + "px";
  tableImg.style.height = newHeight + "px";
  tableImg.src = "./static/images/my_table.png";
  tableImg.style.marginLeft = "auto";
  tableImg.style.marginRight = "auto";
  tableImg.style.display = "block";
}

















//     function generateHtmlTable() {
//   const options = table_form.elements.options.value;
//   const characteristics = table_form.elements.characteristics.value;
//   // Fetch JSON data from the endpoint
//   fetch('/table-generator', {
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/x-www-form-urlencoded'
//     },
//     body: JSON.stringify({
//       characteristics: characteristics,
//       options: options
//     })
//   }) 
//   .then(response => {
//       if (!response.ok) {
//           throw new Error('Failed to fetch data from the endpoint');
//       }
//       return response.json();
//   })
//   .then(data => {
//       // Construct HTML table
//       let html = '<table border="1"><tr>';
      
//       // Extract column headers dynamically
//       const headers = Object.keys(data[Object.keys(data)[0]]);
//       html += headers.map(header => `<th>${header.replace('_', ' ').toUpperCase()}</th>`).join('');
//       html += '</tr>';
      
//       // Iterate through each country's data
//       Object.entries(data).forEach(([country, info]) => {
//           html += '<tr>';
//           headers.forEach(header => {
//               html += `<td>${info[header]}</td>`;
//           });
//           html += '</tr>';
//       });
      
//       html += '</table>';
      
//       // Render HTML table
//       document.getElementById('container').innerHTML = html;
//   })
//   .catch(error => {
//       console.error(error);
//   });
// }


function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

