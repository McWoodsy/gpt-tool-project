from flask import Flask, render_template, request
from openai import OpenAI
import json
from dotenv import dotenv_values
from threading import Thread
import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt
import time
import render_charts

config=dotenv_values(".env")

client = OpenAI(api_key=config["OPENAPI_KEY"])

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

#   JACKSON COMPATIBLE FORMATTING
def get_table_info(option_list, characteristics_list):
    response = client.chat.completions.create(model="gpt-3.5-turbo-0125",
        messages=[
        {"role": "system", "content": """
        YOU WILL PROVIDE NO OUTPUT EXCEPT JSON. You will be asked to compare 2 or more things to eachother in topics provided by the user.
        respond with a JSON of each of these topics in a list, and do the same with the options. The information lists should contain data 
        for each option on each topic.
        Keep the information to the point and include statistics where relevant. Like this:
        {
        *topic*:[
            *topic 1*,
            *topic 2*,
            *topic 3*
            ],
        *information* : [
            *information on topic 1 for option 1*,
            *information on topic 1 for option 2*,
            *information on topic 1 for option 3*
        ],
        [
            *information on topic 2 for option 1*,
            *information on topic 2 for option 2*,
            *information on topic 2 for option 3*
        ],
        *options* : [
            *option 1*,
            *option 2*,
            *option 3*
        ]
        }
    }
        """},
        {"role": "user", "content": " in terms of " + characteristics_list + " compare " + option_list},
        ],
        max_tokens = 200)
    print("\n\n\n"+response.choices[0].message.content+"\n\n\n")
    return response.choices[0].message.content
