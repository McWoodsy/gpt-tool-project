from flask import Flask, render_template, request
from openai import OpenAI
import json
from dotenv import dotenv_values
import matplotlib.pyplot as plt

#from matplotlib import graph

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
    return json.loads(color_array)

@app.route("/bar-chart", methods=["POST"])
def prompt_to_bar_chart():
    ####### How do we pass in the JSON???
    return

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
    to eachother in terms of the metric. Your output format should be the concepts as keys, and then a key value pair of options and its corresponding
    metric information as an integer.
    """},
    {"role": "user", "content": " in terms of " + metric + " compare " + option_list},
    ],
    max_tokens = 200)
    return response.choices[0].message.content

#   Generates bar chart and saves to Spring Boot static folder
def create_bar_chart(categories, values):
    
    ##########  We need to create a dict or a list from the serialized JSON array created by get_chart_info() and pass this into the rest of the function
    
    # Sample data
    categories = ['A', 'B', 'C', 'D']
    values = [10, 20, 15, 25]

    # Create bar chart
    plt.figure(figsize=(8, 6))  # Adjust figure size for better visualization
    bars = plt.bar(categories, values)

    # Add data labels on top of each bar
    for bar in bars:
        yval = bar.get_height()
        plt.text(bar.get_x() + bar.get_width()/2, yval + 0.5, round(yval, 1), ha='center', color='black', fontsize=10)

    # Add grid lines for better readability
    plt.grid(axis='y', linestyle='--', alpha=0.7)

    # Add labels and title
    plt.xlabel('Categories', fontsize=12, fontname='Arial')  # Change font to Arial
    plt.ylabel('Values', fontsize=12, fontname='Arial')      # Change font to Arial
    plt.title('Bar Chart Example', fontsize=14, fontname='Arial')  # Change font to Arial

    # Customize ticks and tick labels
    plt.xticks(fontsize=10, fontname='Arial')  # Change font to Arial
    plt.yticks(fontsize=10, fontname='Arial')  # Change font to Arial

    # Save plot to static folder
    plt.savefig('./gptgenerator/src/main/resources/static/images/my_bar_chart.png')

    # Show plot
    plt.show()


if __name__ == "__main__":
    app.run(debug=True)