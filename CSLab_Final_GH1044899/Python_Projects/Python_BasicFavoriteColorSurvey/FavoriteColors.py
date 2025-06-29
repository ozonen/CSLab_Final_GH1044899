# Data of the colors
colors = ['red', 'blue', 'green', 'yellow', 'purple', 'orange', 'white', 'black']
votes = {color: 0 for color in colors}

# The number of people joined in this survey
people = int(input("How many people did you ask? "))

# To ask people their fav color
print("\nAvailable colors:", ", ".join(colors))
print("Now enter each person's favorite color:")

# Loop method for counting
for i in range(people):
    choice = input(f"Person {i+1}'s favorite color: ").lower() 
    if choice in votes:
        votes[choice] += 1
    else:
        print("There is no such color in our data xd, sorry")

# To print the survey results (unsorted)
print("\nSurvey Results (Unsorted):")
for color in colors:
    bar = '-' * votes[color]
    print(f"{color}: {bar} ({votes[color]} votes)")

# For sorting the data
sorted_votes = sorted(votes.items(), key=lambda x: x[1], reverse=True)

# Another loop to generate the percentage of the fav color in this survey
print("\nSurvey Results (Sorted by Votes):")
for color, count in sorted_votes:
    percentage = (count / people * 100) if people > 0 else 0
    bar = '-' * count
    print(f"{color}: {bar} ({count} votes - {percentage:.1f}%)")

# To show which one is the favorite one
most_voted_color, max_votes = sorted_votes[0]
print(f"\nMost popular color is: {most_voted_color} with {max_votes} votes!")
