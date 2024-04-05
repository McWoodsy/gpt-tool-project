from flask import Flask, render_template, request
import completion_calls
import render_charts
import json

app = Flask(__name__,
            template_folder = 'templates',
            )

@app.route("/")
def index():
    return render_template("index.html")

@app.route("/bar_chart")
def bar_chart():
    return render_template("bar_chart.html")

@app.route("/color_palette")
def color_palette():
    return render_template("/color_palette.html")


#   MAYBE SOMEWHERE INTHIS FUNCTION IS WHERE WE FIX THE REFRESH ISSUE??????
@app.route("/palette", methods=["POST"])
def prompt_to_palette():
    #   Completion call
    query = request.form.get("query")
    color_array = completion_calls.get_colors(query)
    return json.loads(color_array) # NOT serialized in the function so we do it here before returning

@app.route("/bar-chart", methods=["POST"])
def prompt_to_bar_chart():
    ####### How do we pass in the JSON???
    options = request.form.get("options")
    metric = request.form.get("metric")
    bar_chart_JSON = completion_calls.get_chart_info(options, metric)
    render_charts.create_bar_chart(bar_chart_JSON, metric)
    return "bar chart created" 

#################
    
if __name__ == "__main__":
    app.run(debug=True)