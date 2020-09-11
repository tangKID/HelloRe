data = "GXUISCC"  # 用户名
serial = ''  #注册码
for i in range(0, len(data)):
    serial += chr(ord(data[i])-i)

print(serial)