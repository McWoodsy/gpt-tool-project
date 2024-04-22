// Get the current page name from the URL path
const currentPage = window.location.pathname;
console.log(currentPage)


// //  Execute showChart() when submit button is clicked
const chartForm = document.querySelector("#chart_form");
  chartForm.addEventListener("submit", function (e) {
    e.preventDefault();
    getChartInfo();
  });  //**

// Performs the POST request for fetching chart information
function getChartInfo() {

  let options = chartForm.elements.options.value;
  options = urlFormatter(options);  //  unless spaces are removed before plugging into the url theres an error

  let metric = chartForm.elements.metric.value;
  metric = urlFormatter(metric);

  const url = "http://127.0.0.1:8080/createBarChart/" + metric + options
  
//  originally this was sent in the body but now the parameters are going into the url  
  fetch("/createBarChart/" + metric + "/" + options, {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
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
    showChart();
  })
  .catch(error => {
    console.error("There was a problem with the fetch operation:", error);
  });
}

// Displays the chart image
function showChart() {
  const chartImg = document.getElementById("chart-img");
  chartImg.src = "";
  const newWidth = 400 * 2; // Set your desired width
  const newHeight = 300 * 2; // Set your desired height
  chartImg.style.width = newWidth + "px";
  chartImg.style.height = newHeight + "px";
  chartImg.src = "images/my_bar_chart.png";
  chartImg.style.marginLeft = "auto";
  chartImg.style.marginRight = "auto";
  chartImg.style.display = "block";
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
