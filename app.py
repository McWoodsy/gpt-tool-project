from flask import Flask, render_template, request
from openai import OpenAI
import json
from dotenv import dotenv_values
from threading import Thread
import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt
import time

config=dotenv_values(".env")

client = OpenAI(api_key=config["OPENAPI_KEY"])

app = Flask(__name__,
            template_folder = 'templates',
            )

@app.route("/")
def index():
    return render_template("index.html")

@app.route("/palette", methods=["POST"])
def prompt_to_palette():
    #   Completion call
    query = request.form.get("query")
    color_array = get_colors(query)
    return json.loads(color_array) # NOT serialized in the funciton so we do it here before returning

@app.route("/bar-chart", methods=["POST"])
def prompt_to_bar_chart():
    ####### How do we pass in the JSON???
    options = request.form.get("options")
    metric = request.form.get("metric")
    bar_chart_JSON = get_chart_info(options, metric)
    #print("\n\n\n"+bar_chart_JSON+"\n\n\n")
    create_bar_chart(bar_chart_JSON, metric)
    return "bar chart created" 
'''
@app.route("/bar-chart", methods=["POST"])
def prompt_to_bar_chart():
    ####### How do we pass in the JSON???
    options = request.form.get("options")
    metric = request.form.get("metric")
    # Execute Matplotlib operations in a separate thread
    thread = Thread(target=create_bar_chart, args=(metric, options))
    thread.start()
    return "bar chart created" 
'''

#################

#   Completion call that returns a serialized JSON array
def get_colors(text):
    response = client.chat.completions.create(model="gpt-3.5-turbo-0125",
    messages=[
    {"role": "system", "content": """
    You are a colour palette generating assistant that responds to text prompts for colour palettes and provides only their hex
    in a json array . You will provide 5 colours unless the user requests more. YOU WILL PROVIDE NO OUTPUT EXCEPT THE JSON ARRAY.
    if the user provides no input or nonsensical, mispelled input, provide rainbow colors.
    """},
    {"role": "user", "content": " convert the following prompt into a json array of colors, with no newlines, just a pure json array: " + text},
    ],
    max_tokens = 200)
    return response.choices[0].message.content

#   Competion call that returns a serialized JSON with title as key and key value pairs as value
def get_chart_info(option_list, metric):
    response = client.chat.completions.create(model="gpt-3.5-turbo-0125",
    messages=[
    {"role": "system", "content": """
    YOU WILL PROVIDE NO OUTPUT EXCEPT JSON. You recieve a metric. You will also recieve a list of options. You will compare each option
    to eachother in terms of the metric EXACTLY AS WORDED BY THE USER. The key should never be "metric". Your output format should be the concepts as keys, and then a key value pair of options and its corresponding
    metric information as an integer. RESPONSE SHOULD BE IN LOWER CASE. Here is an example:
    {
    *metric*: {
        *option 1*: *integer*,
        *option 2*: *integer*
    }
}
    """},
    {"role": "user", "content": " in terms of " + metric + " compare " + option_list},
    ],
    max_tokens = 200)
    print("\n\n\n"+response.choices[0].message.content+"\n\n\n")
    return json.loads(response.choices[0].message.content)

#   Generates bar chart and saves to Spring Boot static folder
def create_bar_chart(bar_chart_JSON, metric):    
    ##########  We need to create a dict or a list from the serialized JSON array created by get_chart_info() and pass this into the rest of the function  
    print("\n\n\n" + json.dumps(bar_chart_JSON) + "\n\n\n") # For validation
    values = list(bar_chart_JSON[metric].values())
    options = list(bar_chart_JSON[metric].keys())
    options = [option.capitalize() for option in options]

    # Create bar chart
    plt.figure(figsize=(8, 6))  # Adjust figure size for better visualization
    bars = plt.bar(options, values)
    # Add grid lines for better readability
    plt.grid(axis='y', linestyle='--', alpha=0.7)
    # Add labels and title (capitalized)
    plt.xlabel('', fontsize=12, fontname='Arial')
    plt.ylabel('', fontsize=12, fontname='Arial')    
    plt.title(metric.title(), fontsize=14, fontname='Arial')  # Capitalize the title
    # Capitalize labels of each bar
    for bar in bars:
        height = bar.get_height()
        plt.text(bar.get_x() + bar.get_width() / 2, height, '%d' % int(height), ha='center', va='bottom')
    # Customize ticks and tick labels
    plt.xticks(fontsize=10, fontname='Arial')
    plt.yticks(fontsize=10, fontname='Arial')
    # Save plot to static folder
    #plt.savefig('./gptgenerator/src/main/resources/static/images/my_bar_chart.png')
    plt.savefig('./static/images/my_bar_chart.png')
    
if __name__ == "__main__":
    app.run(debug=True)