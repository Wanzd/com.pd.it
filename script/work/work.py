# -*- coding=utf-8 -*-
from weatherImpl import WeatherVO
import json
import matplotlib.pyplot as plt
import numpy as np
import requests as req

plt.rcParams['font.sans-serif'] = ['Kaiti']
plt.rcParams['axes.unicode_minus'] = False

def avg(*inList):
    inLen = len(inList)
    sum = 0
    for each in inList:
        sum += each
    return sum / inLen

def getWeatherList(location):
    rb = req.get('http://wthrcdn.etouch.cn/weather_mini?city=' + location)
    rb.encoding = 'utf-8'
    data = json.loads(rb.text)
    list = data['data']['forecast']
    weatherList = []
    for each in list:
        weather = WeatherVO()
        weather.location = location
        weather.date = each['date']
        weather.type = each['type']
        weather.low = int(each['low'].replace('低温 ', '').replace('℃', ''))
        weather.high = int(each['high'].replace('高温 ', '').replace('℃', ''))
        weather.windDirect = each['fengxiang']
        weather.windPower = each['fengli'].replace('<![CDATA[', '').replace(']]>', '')
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
    locations = ['黄冈', '武汉']
    for eachLocation in locations:
        show(eachLocation, 'plt')
        
main()
