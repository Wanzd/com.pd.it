class WeatherVO:
    location = ""
    date = ""
    low = 0
    high = 0
    
    def __init__(self):pass
    def __str__(self):
        return "%s %s~%s" % (self.date.ljust(10),  self.low, self.high)
