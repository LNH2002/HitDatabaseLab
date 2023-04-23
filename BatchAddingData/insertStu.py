import random
import pymysql
import firstName
from faker import Faker

# 连接数据库
conn = pymysql.connect(host='localhost', user='root', password='123456', database='school')
cursor = conn.cursor()

# 创建Faker对象
fake = Faker()
rand = random.Random()

# 生成虚假姓名、地址和电子邮件
data = {
    'Sid': '',
    'Sname': '',
    'Sage': 0,
    'Ssex': '',
    'Sclass': "",
    'Sdept': "",
    'Saddr': ""
}

cnt = {
    '计算机': 0, '化学': 0, '电信': 0, '物理': 0,
    '自动化': 0, '电气': 0, '机械': 0, '土木': 0,
    '建筑': 0, '生物': 0, '环境': 0, '材料': 0
}


def generateDate():
    data['Sname'] = ''
    for i in range(rand.randint(2, 3)):
        data['Sname'] = data['Sname'] + fake.random_element(firstName.first_name)
    # data['Sname'] = fake.name()
    data['Sage'] = rand.randint(15, 35)
    data['Ssex'] = fake.random_element(elements=('男', '女'))
    data['Sdept'] = fake.random_element(elements=('计算机', '化学', '电信', '物理',
                                                  '自动化', '电气', '机械', '土木',
                                                  '建筑', '生物', '环境', '材料'))
    # data['Sclass'] = temp + str(rand.randint(1995, 2014))
    if data['Ssex'] == '男':
        data['Saddr'] = fake.random_element(elements=('a01', 'a02', 'a03', 'a15', 'a17', 'b02', 'b05'))
    else:
        data['Saddr'] = fake.random_element(elements=('a04', 'a15', 'b03'))


affected_rows = 0
years = [2019, 2020, 2021, 2022]
for year in years:
    num_of_student = 2500 + rand.randint(0, 100)
    for i in range(num_of_student):
        data['Sid'] = str(year) + '{:04d}'.format(i + 1)
        generateDate()
        sql = "INSERT INTO student (Sid,Sname,Sage,Ssex,Sclass,Sdept,Saddr)" \
              "VALUES (\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')" \
              % (data['Sid'], data['Sname'], data['Sage'], data['Ssex'], data['Sclass'], data['Sdept'], data['Saddr'])
        affected_rows = affected_rows + cursor.execute(sql)
    print("受影响的行数为：", affected_rows)

# 提交事务
conn.commit()
# 关闭游标和连接
cursor.close()
conn.close()
