import matplotlib.pyplot as plt

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