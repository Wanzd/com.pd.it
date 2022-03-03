from datetime import datetime as dt
def f9():
    print(dt.now())
    return (dt.now().weekday()+2)%7

print(f9())
print(dt.now().year)
print(dt.now().month)
print(dt.now().day)
print(dt.now().hour)
print(dt.now().minute)
print(dt.now().second)
print(dt.now().microsecond)