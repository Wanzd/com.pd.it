def avg(**inList):
    inLen = len(inList)
    sum = 0
    for each in inList:
        sum += each
    return sum / inLen
