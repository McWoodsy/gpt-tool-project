from flask import Flask, render_template, request
from openai import OpenAI
import json
from dotenv import dotenv_values
import matplotlib.pyplot as plt

config=dotenv_values(".env")

client = OpenAI(api_key=config["OPENAPI_KEY"])

def get_chart_info(option_list, metric):
    #   Creating a string from the list so that it can be passed in at once to the GPT   
        
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

print(get_chart_info("france, england", "population size"))


