//  Execute getColors() when submit button is clicked
const form = document.querySelector("#form");
    form.addEventListener("submit", function (e) {
      e.preventDefault();
      getColors();
    });

//  Execute showChart() when submit button is clicked
const chartForm = document.querySelector("#chart_form");
  chartForm.addEventListener("submit", function (e) {
    e.preventDefault();
    getChartInfo();
  });

// Performs the POST request for fetching chart information
function getChartInfo() {
  const options = chartForm.elements.options.value;
  const metric = chartForm.elements.metric.value;
  
  fetch("/bar-chart", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body: new URLSearchParams({
      options: options,
      metric: metric
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
    showChart();
  })
  .catch(error => {
    console.error("There was a problem with the fetch operation:", error);
  });
}

// Displays the chart image
function showChart(chartImageUrl) {
  const chartImg = document.getElementById("chart-img");
  chartImg.src = "";
  const newWidth = 400 * 2; // Set your desired width
  const newHeight = 300 * 2; // Set your desired height
  chartImg.style.width = newWidth + "px";
  chartImg.style.height = newHeight + "px";
  chartImg.src = "./static/images/my_bar_chart.png";
  chartImg.style.marginLeft = "auto";
  chartImg.style.marginRight = "auto";
  chartImg.style.display = "block";
}



//  Performs the POST request
function getColors() {
    const query = form.elements.query.value;
      fetch("/palette", {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
          query: query
        })
      })
      .then((response) => response.json())
      .then(data => {
        const color_array = data;
        const container = document.querySelector(".container");
        createColorBoxes(color_array,container);
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

