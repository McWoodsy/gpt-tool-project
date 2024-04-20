from flask import Flask, render_template, request, url_for, redirect
import completion_calls
import render_charts
import json
import os
import requests

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

@app.route("/table_generator")
def table_generator():
    return render_template("/table_generator.html")


#   MAYBE SOMEWHERE IN THIS FUNCTION IS WHERE WE FIX THE REFRESH ISSUE??????
@app.route("/palette", methods=["POST"])
def prompt_to_palette():
    #   Completion call
    query = request.form.get("query")
    color_array = completion_calls.get_colors(query)
    return json.loads(color_array) # NOT serialized in the function so we do it here before returning

# Set this to receive parameters in the body
@app.route("/bar-chart", methods=["POST"])
def prompt_to_bar_chart():
    ####### How do we pass in the JSON???
    options = request.form.get("options")
    metric = request.form.get("metric")
    bar_chart_JSON = completion_calls.get_chart_info(options, metric)
    # Write to static folder before rendering
    with open("static/json/table_data.json", "w") as file:
        json.dump(bar_chart_JSON, file)
    render_charts.create_bar_chart(bar_chart_JSON, metric)
    return "bar chart created" 
'''
@app.route("/table-generator", methods=["POST"])
def prompt_to_table():
    characteristics = request.form.get("characteristics")
    print("CHARACTERISTICS   "+ characteristics)
    options = request.form.get("options")
    print("OPTIONS         " + options)
    table_JSON = completion_calls.get_table_info(options, characteristics)
    render_charts.create_table(table_JSON)
    print(table_JSON)
    # Write to static folder before rendering
    #with open("static/json/tables.json", "w") as file:
     #   json.dump(table_JSON, file)
    return json.loads(table_JSON)
'''

#   We can rewrite this to accept parameters from the body instead of the URL
@app.route("/table-generator/<characteristics>/<options>", methods=["POST"])
def prompt_to_table(characteristics, options):
    print("\n\nCHARACTERISTICS   "+ characteristics)
    print("\n\nOPTIONS         " + options)
    table_JSON = completion_calls.get_table_info(options, characteristics)
    #return table_JSON
    print(table_JSON)
    response = requests.post("http://127.0.0.1:8080/getTable",json=json.loads(table_JSON))
    if (response.status_code == 200):
        #In Flask, when you return a tuple from a view function, the first element of the tuple is 
        #considered the response body, and the second element is considered the HTTP status code.
        return "\n\n Posted to Spring Boot endpoint\n\n", 200
    else:
        return "Error", 500

#################
    
if __name__ == "__main__":
    app.run(debug=True)