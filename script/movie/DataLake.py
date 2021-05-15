# -*- coding=utf-8 -*-
from pymysql import *
class DataLakeVO():
    def __init__(self,type,source,url,name,detail):
        self.type=type
        self.source=source
        self.url=url
        self.name=name
        self.detail=detail     
        
class DataLakeDao():
    def insertList(self,list):
        listValues = []
        for eachItem in list:
            listValues.append((eachItem.type,eachItem.source,eachItem.url,eachItem.name,eachItem.detail))  # 注意要用两个括号扩起来
        conn = connect(host='localhost', port=3306, user='dev', password='dev', database='dev', charset='utf8')
        cs = conn.cursor()  # 获取光标
        # 注意这里使用的是executemany而不是execute，下边有对executemany的详细说明
        cs.executemany('replace into data_lake_t(type,source,url,name,detail) values(%s,%s,%s,%s,%s)', listValues)
        conn.commit()
        cs.close()
        conn.close()
        print('OK')