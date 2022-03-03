sxName='鼠牛虎兔龙蛇马羊猴鸡狗猪'

def f11(year):
    index=(year-4)%12
    return sxName[index]

print(f11(2022))
print(f11(2020))
print(f11(2019))