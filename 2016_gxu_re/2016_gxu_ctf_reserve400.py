'''
编写者 TangKID

'''
the_map = '****************' \
          'C..*****.......*' \
          '**.*****.*****.*' \
          '**...*...*.....*' \
          '****.*.***.*****' \
          '****.*...*.....*' \
          '.....***.*****.*' \
          '.*******.*.....*' \
          '.***.....*.*****' \
          '.....*****.....X' \
          '****************'

org_road = '1122 1122 2333 3222 1111 0111 1000 3300 1100 1111 ' \
           '1122 3333 2211 1122 3333 2211 1111'

new_road = '1122 1122 2333 3222 1111 0201 1110 0200 3300 1100 ' \
           '1111 1122 3333 2211 1122 3333 2211 1111'.split(' ')

temp = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!"#$%&()*+,-./:;<=>?@[\\]^_`{}|~©üìæé³'
key = ''
for i in range(0, len(new_road)):
    for j in range(0, len(temp)):
        xor_result = (ord(temp[j]) ^ 22)
        if xor_result >> 6 & 3 == int(new_road[i][0]) and xor_result >> 4 & 3 == int(new_road[i][1]) and xor_result >> 2\
                & 3 == int(new_road[i][2]) and xor_result >>0 & 3 == int(new_road[i][3]):
            key += temp[j]
print(key)

test = '©üìæé³'
test_arr = []
print('***************************************')
for i in range(0, len(test)):
    test_arr.append(hex(ord(test[i])))
print(test_arr)