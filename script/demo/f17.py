def calStr(a, b, op):
    if op == 0:
        return a + "+" + b
    elif op == 1:
        return a + "-" + b
    elif op == 2:
        return b + "-" + a
    elif op == 3:
        return a + "*" + b
    elif op == 4:
        if b == 0:
            return ""
        return a + "/" + b
    else:
        if a == 0:
            return ""
        return a + "/" + b
    
def single(a, b, op):
    if op == 0:
        return a + b
    elif op == 1:
        return a - b
    elif op == 2:
        return b - a
    elif op == 3:
        return a * b
    elif op == 4:
        if b == 0:
            return 99999
        return a / b
    else:
        if a == 0:
            return 99999
        return b / a
    
def force(a, b, c, d):
    for i in range(5):
        tmp = single(a, b, i)
        for j in range(5):
            tmp2 = single(tmp, c, j)
            for k in range(5):
                tmp3 = single(tmp2, d, k)
                if tmp3 == 24:
                    rsStr = calStr(str(a), str(b), i)
                    rsStr = calStr(rsStr, str(c), j)
                    rsStr = calStr(rsStr, str(d), k)
                    print(rsStr + "=24")
            
def f17(a, b, c, d):
    arr4 = [a, b, c, d]
    arr4.sort()
    print("in:" + str(arr4))
    arr3 = []
    arr2 = []
    arr1 = []
    for i in arr4:
        arr3 = arr4.copy()
        arr3.remove(i)
        for j in arr3:
            arr2 = arr3.copy()
            arr2.remove(j)
            for k in arr2:
                arr1 = arr2.copy()
                arr1.remove(k)
                for l in arr1:
                    # print(str(i) + str(j) + str(k) + str(l))
                    force(i, j, k, l)

f17(2, 3, 9, 1)
