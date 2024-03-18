from flask import Flask, render_template, request
from openai import OpenAI
import json
from dotenv import dotenv_values
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

#   Completion call that returns a serialized JSON file of options as keys and then terms-info pairs as the value
#   CONCEPT SHOULD BE THE KEY
def get_table_info(option_list, concept_list):
    #   Creating a string from the list so that it can be passed in at once to the GPT 
    formatted_concept_list = ""
    formatted_option_list = ""
    for concept in concept_list:
        formatted_concept_list.append(concept + ", ")
    for option in option_list:
        formatted_option_list.append(option + ", ")  
        
    response = client.chat.completions.create(model="gpt-3.5-turbo-0125",
    messages=[
    {"role": "system", "content": """
    YOU WILL PROVIDE NO OUTPUT EXCEPT JSON. You recieve a list of concepts. You will also recieve a list of options. You will compare each option
    to eachother in terms of the concepts provided. Your output format should be the concepts as keys, and then a key value pair of options and its corresponding
    information.
    """},
    {"role": "user", "content": " in terms of " + concept_list + " compare " + option_list},
    ],
    max_tokens = 200)
    return response.choices[0].message.content


if __name__ == "__main__":
    app.run(debug=True)