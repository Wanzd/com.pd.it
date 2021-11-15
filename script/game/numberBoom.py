import random
def genResult():
    arr = random.sample(range(1, 100), 1)
    return arr[0]
def guess(result):
    activeFlag = 1
    start = 1
    end = 100
    while(activeFlag):
        inStr = input("清输入一个" + str(start) + "到" + str(end) + "之间的数字！")
        try:
            intValue = int(inStr) 
            if(intValue < start or intValue > end):
                continue
            if(intValue == result):
                print('Boom !')
                print('You lose')
                activeFlag = 0
                return
            if(intValue > result):
                end = intValue-1
                continue
            if(intValue < result):
                start = intValue+1
                continue
        except:
            pass
    return
def main():
    result = genResult()
    print('Game start:')
    guess(result)
main()