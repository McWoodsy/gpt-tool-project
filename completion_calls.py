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

#   Completion call that returns serialized JSON of table info
def get_table_info(option_list, characteristics_list):
    response = client.chat.completions.create(model="gpt-3.5-turbo-0125",
        messages=[
        {"role": "system", "content": """
        YOU WILL PROVIDE NO OUTPUT EXCEPT JSON. You recieve one or more options. You will also recieve a list of characteristics. You will compare each option
        to eachother in terms of each of the characteristics provided EXACTLY AS WORDED BY THE USER. Your output format should be each characteristic as a key, and then a key value pair of options and its corresponding
        information (low word count please) as a string. RESPONSE SHOULD BE IN LOWER CASE. Here is an example:
        {
        *characteristic*: {
            *option 1*: *information*,
            *option 2*: *information*
        }
    }
        """},
        {"role": "user", "content": " in terms of " + characteristics_list + " compare " + option_list},
        ],
        max_tokens = 200)
    print("\n\n\n"+response.choices[0].message.content+"\n\n\n")
    return json.loads(response.choices[0].message.content)

#get_table_info("Germany, USA, Iraq", "life expectancy, gdp per capita, smoking rate")