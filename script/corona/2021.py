# -*- coding=utf-8 -*-
# https://www.66e.cc/动作电影数据挖掘
import time
import requests  # 导入requests包
from bs4 import BeautifulSoup
from DataLake import DataLakeVO, DataLakeDao
base_url = 'https://www.66e.cc/dmq/'
type = '电影/动漫'
source = 'www.66e.cc'
dataLakeDao=DataLakeDao()
def calUrl(idx):
    print("calUrl:" + str(idx))
    rsUrl = ""
    if(idx == 1):
        rsUrl = base_url + "index.htm"
    else:
        rsUrl = base_url + "index_" + str(idx) + ".htm"
    print("rsUrl:" + rsUrl)
    return rsUrl
def calList(url):
    strhtml = str(requests.get(url).content,encoding='gb2312', errors='ignore')  # Get方式获取网页数据
    print(strhtml)
    soup = BeautifulSoup(strhtml)
    list = soup.find_all(attrs={"class":"listInfo"})
    #print(strhtml)
    rsList = []
    for eachItem in list:
        #print(eachItem)
        url = eachItem.find("h3").find("a")["href"]
        name = eachItem.find("h3").find("a").text
        detail=eachItem.find_all("p")[2].text
        movieVO = DataLakeVO(type, source, url, name, detail)
        rsList.append(movieVO)
    return rsList
def insertList(list):
    print("insertList start:" + str(len(list)))
    dataLakeDao.insertList(list)
def main():
    print("main start:")
    for i in range(1, 2):
        try:
            url = calUrl(i)
            list = calList(url)
            if(len(list)==0):
                break
            insertList(list)
        except Exception as e:
            print(e)
main()