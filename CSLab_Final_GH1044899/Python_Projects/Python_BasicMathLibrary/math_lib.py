def factorial(n):
    if n < 0:
    if n == 0 or n == 1:
        return 1
    return n * factorial(n - 1)

def median(data):
    sorted_data = sorted(data)
    n = len(sorted_data)
    middle = n // 2
    if n % 2 == 0:
        return (sorted_data[middle - 1] + sorted_data[middle]) / 2
    else:
        return sorted_data[middle]

def mean(data):
    return sum(data) / len(data)

def mode(data):
    freq = {}
    for num in data:
        freq[num] = freq.get(num, 0) + 1
    max_freq = max(freq.values())
    modes = [k for k, v in freq.items() if v == max_freq]
    return modes if len(modes) > 1 else modes[0]

def power(base, exponent):
    return base ** exponent

def is_prime(n):
    if n <= 1:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True

def variance(data):
    mu = mean(data)
    return sum((x - mu) ** 2 for x in data) / len(data)

def standard_deviation(data):
    return variance(data) ** 0.5

def square_root(x):
    if x < 0:
    return x ** 0.5
