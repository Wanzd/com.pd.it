# -*- coding=utf-8 -*-
from weatherImpl import WeatherVO
import json
import matplotlib.pyplot as plt
import numpy as np
import requests as req

plt.rcParams['font.sans-serif'] = ['Kaiti']
plt.rcParams['axes.unicode_minus'] = False

weatherServer = 'http://192.168.0.240:10080/weatherRest'

def avg(*inList):
    inLen = len(inList)
    sum = 0
    for each in inList:
        sum += each
    return sum / inLen

def getWeatherList(location):
    url = weatherServer + '/queryList' 
    postData = {"city":location}
    
    print(url)
    print(postData)
    rb = req.post(url , data=json.dumps(postData), headers={'content-type': 'application/json'})
    rb.encoding = 'utf-8'
    queryList = json.loads(rb.text)
    print(queryList)
    weatherList = []
    for each in queryList:
        weather = WeatherVO()
        weather.location = location
        weather.date = each['weatherDate']
        weather.low = int(each['low'])
        weather.high = int(each['high'])
        weatherList.append(weather)
    return weatherList
  
def showText(location, weatherList):
    print('in showText')
    print(location)
    for eachVO in weatherList:
        print(eachVO)

def showHtml(location, weatherList):
    print('in showHtml')
  
def showPlt(location, weatherList):
    print('in showPlt')
    plt.cla()
    y1 = []
    y2 = []
    y3 = []
    xLabel = []
    for vo in weatherList :
        y1.append(vo.high)
        y2.append(vo.low)
        y3.append(avg(vo.high, vo.low))
        xLabel.append(vo.date)
    print(y1)
    print(y2)
    x1 = range(0, 5) 
    x2 = range(0, 5)
    x3 = range(0, 5) 
    N = len(weatherList)
    xIndex = np.arange(N)
    plt.xticks(xIndex, xLabel)
    plt.plot(x1, y1, label='高温') 
    plt.plot(x2, y2, label='低温') 
    plt.plot(x3, y3, label='平均') 
    plt.xlabel('日期') 
    plt.ylabel('温度') 
    plt.title(location) 
    plt.legend() 
    plt.show() 
    
def show(location, type):
    showMap = {
             'text':showText,
             'html':showHtml,
             'plt':showPlt
             }
    weatherList = getWeatherList(location)
    showMap[type](location, weatherList)
        
def main():
    locations = ['上海']
    for eachLocation in locations:
        show(eachLocation, 'text')
        
main()
