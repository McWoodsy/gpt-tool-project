import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt
import json


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
    # Save plot to static folder
    plt.savefig('./static/images/my_bar_chart.png', facecolor='#4c0000', bbox_inches='tight')  # Set background color and save with tight bounding box
    plt.close()