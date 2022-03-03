from datetime import datetime as dt


def f15(id):
    sex="男" if int(id[16])%2==1 else "女"
    print(dt.now().year)
    print(int(id[6:10]))
    age=dt.now().year-int(id[6:10])
    return "性别："+sex+" 年龄："+str(age)

print(f15("421102198506130835"))