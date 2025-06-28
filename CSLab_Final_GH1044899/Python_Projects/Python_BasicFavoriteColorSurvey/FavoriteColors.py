
possible_colors = ['Red', 'Blue', 'Green', 'Yellow', 'Purple']
votes = {color: 0 for color in possible_colors}

num_persons = int(input("how many people did you ask? "))

print("\nAvailable colors:", ", ".join(possible_colors))
print("now enter each person's favorite color:")

for i in range(num_persons):
    choice = input(f"Person {i+1}'s favorite color: ").capitalize()
    if choice in votes:
        votes[choice] += 1
    else:
        print("invalid color. dummy...")

print("\nSurvey Results (Unsorted):")
for color in possible_colors:
    bar = '-' * votes[color]
    print(f"{color}: {bar} ({votes[color]} votes)")

sorted_votes = sorted(votes.items(), key=lambda x: x[1], reverse=True)

print("\nSurvey Results (Sorted by Votes):")
for color, count in sorted_votes:
    percentage = (count / num_persons * 100) if num_persons > 0 else 0
    bar = '-' * count
    print(f"{color}: {bar} ({count} votes - {percentage:.1f}%)")

most_voted_color, max_votes = sorted_votes[0]
print(f"\nMost Popular Color: {most_voted_color} with {max_votes} votes!")
