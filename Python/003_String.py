# 24.03.30

# len() : 문자열 길이 구하는 함수
str1 = '한글 길이'
str2 = 'english length'
str3 = '12345'
print(len(str1))
print(len(str2))
print(len(str3))
print('\n')

# 문자열 포매팅 1
# 특정 포맷에 맞춰 문자열을 재배치하는 것
# %s : 문자열
# %d : 정수형 숫자 / %f : 실수형 숫자 / %.2f 소수점 둘째 자리의 실수형 숫
param = 'ynjch'
msg = 'My ID is %s ^^' % param
print(msg)
param = 28
msg = 'My age is %d' % param
print(msg)
msg = 'My age is %f' % param
print(msg)
msg = 'My age is %.3f' % param
print(msg)
print('\n')

# 문자열 포매팅 2
name = 'Jennie'
year = 1996
str1 = '이름 : {}'.format(name)
str2 = '출생년도 : {}'.format(year)
print(str1)
print(str2)
str3 = '이름 : {}, 출생년도 : {}'.format(name, year)
print(str3)
