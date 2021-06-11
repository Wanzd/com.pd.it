class WeatherVO:
    location = ""
    date = ""
    type = ""
    low = 0
    high = 0
    windPower = ""
    windDirect = ""
    
    def __init__(self):pass
    def __str__(self):
        return "%s %s %s~%s %s %s" % (self.date.ljust(10), self.type.center(5), self.low.rjust(5), self.high.rjust(5), self.windDirect.center(5), self.windPower.rjust(5))
