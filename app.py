from flask import Flask, render_template, request
from openai import OpenAI
import json
from dotenv import dotenv_values
from threading import Thread
import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt
import time
import completion_calls

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
    color_array = completion_calls.get_colors(query)
    return json.loads(color_array) # NOT serialized in the funciton so we do it here before returning

@app.route("/bar-chart", methods=["POST"])
def prompt_to_bar_chart():
    ####### How do we pass in the JSON???
    options = request.form.get("options")
    metric = request.form.get("metric")
    bar_chart_JSON = completion_calls.get_chart_info(options, metric)
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



def create_bar_chart(bar_chart_JSON, metric):    
    ##########  We need to create a dict or a list from the serialized JSON array created by get_chart_info() and pass this into the rest of the function  
    print("\n\n\n" + json.dumps(bar_chart_JSON) + "\n\n\n") # For validation
    values = list(bar_chart_JSON[metric].values())
    options = list(bar_chart_JSON[metric].keys())
    options = [option.capitalize() for option in options]
    # Create bar chart
    plt.figure(figsize=(8, 6), facecolor='#4c0000')  # Adjust figure size for better visualization
    bars = plt.bar(options, values, color='blue')  # Set color inside the bars
    
    # Set face color of the entire plot area
    plt.gca().set_facecolor('#4c0000')
    
    # Add grid lines for better readability
    plt.grid(axis='y', linestyle='--', alpha=0.7, color='white')  # Change grid color
    
    # Add labels and title (capitalized)
    plt.xlabel('', fontsize=12, fontname='Arial', color='white', fontweight='bold')  # Set label color to white and make it bold
    plt.ylabel('', fontsize=12, fontname='Arial', color='white', fontweight='bold')  # Set label color to white and make it bold  
    plt.title(metric.title(), fontsize=14, fontname='Arial', color='white', fontweight='bold')  # Capitalize the title, set color to white, and make it bold
    
    # Capitalize labels of each bar
    for bar in bars:
        height = bar.get_height()
        plt.text(bar.get_x() + bar.get_width() / 2, height, '%d' % int(height), ha='center', va='bottom', color='white', fontweight='bold')  # Set text color to white and make it bold
    
    # Customize ticks and tick labels
    plt.xticks(fontsize=10, fontname='Arial', color='white', fontweight='bold')  # Set tick label color to white and make it bold
    plt.yticks(fontsize=10, fontname='Arial', color='white', fontweight='bold')  # Set tick label color to white and make it bold
    
    # Save plot to static folder
    plt.savefig('./static/images/my_bar_chart.png', facecolor='#4c0000', bbox_inches='tight')  # Set background color and save with tight bounding box
    plt.close()  # Close the plot to release memory

    
if __name__ == "__main__":
    app.run(debug=True)