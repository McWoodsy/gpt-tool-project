from flask import Flask, render_template, request, url_for, redirect
import completion_calls
import render_charts
import json
import os
import requests
import custom_utility

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
@app.route("/palette/<query>", methods=["POST"])
def prompt_to_palette(query):
    #   Completion call
    # query = request.form.get("query")
    color_array = completion_calls.get_colors(query)
    print(json.loads(color_array))
    #   Returns via body
    return json.loads(color_array) # NOT serialized in the function so we do it here before returning

# Set this to receive parameters in the body after JSONifying everything
@app.route("/bar-chart/<metric>/<options>", methods=["POST"])
def prompt_to_bar_chart(metric, options):
    bar_chart_JSON = completion_calls.get_chart_info(custom_utility.url_deformatter(options), custom_utility.url_deformatter(metric))
    # Write to static folder before rendering
    with open("static/json/table_data.json", "w") as file:
        json.dump(bar_chart_JSON, file)
    #   Here it is also saved to spring boot static
    render_charts.create_bar_chart(bar_chart_JSON)
    return "OK" , 200

#   We can rewrite this to accept parameters from the body instead of the URL
@app.route("/table-generator/<characteristics>/<options>", methods=["POST"])
def prompt_to_table(characteristics, options):
    # url deformatter
    characteristics = custom_utility.url_deformatter(characteristics)
    options = custom_utility.url_deformatter(options)
    print("\n\nCHARACTERISTICS   "+ characteristics)
    print("\n\nOPTIONS         " + options)
    table_JSON = completion_calls.get_table_info(options, characteristics)
    render_charts.create_table(table_JSON)
    #response = requests.post("http://127.0.0.1:8080/getTable",json=json.loads(table_JSON))
    """ if (response.status_code == 200):
        #In Flask, when you return a tuple from a view function, the first element of the tuple is 
        #considered the response body, and the second element is considered the HTTP status code.
        return "\n\n Posted to Spring Boot endpoint\n\n", 200
    else:
        return "Error", 500 """
    return json.loads(table_JSON)

#################
    
if __name__ == "__main__":
    app.run(debug=True)