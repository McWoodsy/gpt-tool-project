// Get the current page name from the URL path
const currentPage = window.location.pathname;
console.log(currentPage)


// //  Execute getColors() when submit button is clicked
const tableForm = document.querySelector("#table_form");
tableForm.addEventListener("submit", function (e) {
      e.preventDefault();
      getTableInfo();
    });

    // Performs the POST request for fetching chart information
function getTableInfo() {
  let options = table_form.elements.options.value;
  options = urlFormatter(options);
  let characteristics = table_form.elements.characteristics.value;
  characteristic = urlFormatter(characteristics);

  fetch("/createTable/" + characteristics + "/" + options, {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
  })
  .then((response) => {
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    return response.text(); //  Return it .json() ???
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

function showTable() {
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



function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
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
