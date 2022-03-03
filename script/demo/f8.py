def f8(name,sex):
    return name[0:1]+('女士' ,'先生')[sex==1]
    
print(f8('万正东',1))
print(f8('胡翠',0))
print(f8('万秉成',1))


print('abcde'[4])