import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt
import json
from dotenv import dotenv_values


color_config=dotenv_values("theme.env")



#   Creates a bar chart and saves it to relative static folder
def create_bar_chart(bar_chart_JSON, metric):    
    ##########  We need to create a dict or a list from the serialized JSON array created by get_chart_info() and pass this into the rest of the function  
    print("\n\n\n" + json.dumps(bar_chart_JSON) + "\n\n\n") # For validation
    values = list(bar_chart_JSON[metric].values())
    options = list(bar_chart_JSON[metric].keys())
    options = [option.capitalize() for option in options]
    # Create bar chart
    plt.figure(figsize=(8, 6), facecolor='#4c0000')
    bars = plt.bar(options, values, color='blue')
    plt.gca().set_facecolor('#4c0000')
    # Add grid lines
    plt.grid(axis='y', linestyle='--', alpha=0.7, color='white')  # Change grid color
    # Add labels and title (capitalized)
    plt.xlabel('', fontsize=12, fontname='Arial', color='white', fontweight='bold')
    plt.ylabel('', fontsize=12, fontname='Arial', color='white', fontweight='bold')
    plt.title(metric.title(), fontsize=14, fontname='Arial', color='white', fontweight='bold')
    # Capitalize labels of each bar
    for bar in bars:
        height = bar.get_height()
        plt.text(bar.get_x() + bar.get_width() / 2, height, '%d' % int(height), ha='center', va='bottom', color='white', fontweight='bold')  # Set text color to white and make it bold    
    plt.xticks(fontsize=10, fontname='Arial', color='white', fontweight='bold')
    plt.yticks(fontsize=10, fontname='Arial', color='white', fontweight='bold') 
    plt.gca().spines['top'].set_color('white')
    plt.gca().spines['bottom'].set_color('white')
    plt.gca().spines['left'].set_color('white')
    plt.gca().spines['right'].set_color('white')
    # Save plot to static folder
    plt.savefig('./static/images/my_bar_chart.png', facecolor='#4c0000', bbox_inches='tight')  # Set background color and save with tight bounding box
    plt.close()
    

def create_pie_chart(pie_chart_JSON, group, criteria): #    
    return

def create_table(table_JSON):
    data = json.loads(table_JSON)

    # Get all countries
    countries = list(data[next(iter(data))])

    # Prepare rows for table
    rows = [["Country"] + countries]  # First row contains "Country" and all country names

    # Iterate over each category (e.g., "gdp per capita", "average salary in euros", "years of existence")
    for category, values in data.items():
        row = [category]  # Start row with category name
        for country in countries:
            row.append(values.get(country, "-"))  # Append value for each country, or "-" if not found
        rows.append(row)

    fig, ax = plt.subplots()

    # Creating the table
    table = ax.table(cellText=rows,
                     loc='center')

    # Hide axes
    ax.axis('off')

    # Adjust layout to fit the table
    plt.subplots_adjust(left=0.2, top=0.8)
    table.scale(4, 6)
    plt.savefig('./static/images/my_table.png')  # Save the table as an image



def create_table(table_JSON):
    data = json.loads(table_JSON)

    # Get all countries
    countries = list(data[next(iter(data))])

    # Prepare rows for table
    rows = []  

    # Append column headers (categories) as the first row
    category_headers = [""] + list(data.keys())
    rows.append(category_headers)

    # Iterate over each country
    for country in countries:
        row = [country]  # Start row with country name
        for category in data.keys():
            row.append(data[category].get(country, "-"))  # Append value for each category, or "-" if not found
        rows.append(row)

    fig, ax = plt.subplots()

    # Creating the table
    table = ax.table(cellText=rows,
                     loc='center')

    # Hide axes
    ax.axis('off')

    # Adjust layout to fit the table
    plt.subplots_adjust(left=0.2, top=0.8)
    plt.savefig('./static/images/my_table.png')  # Save the table as an image




    

def create_graph(): # do we need a seperate funciton for correlation graphs?
    return