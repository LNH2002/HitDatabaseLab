import random
import pymysql
from faker import Faker

# 连接数据库
conn = pymysql.connect(host='localhost', user='root', password='123456', database='school')
cursor = conn.cursor()

# 创建Faker对象
fake = Faker()
rand = random.Random()

cnt = {
    '计算机': 0, '化学': 0, '电信': 0, '物理': 0,
    '自动化': 0, '电气': 0, '机械': 0, '土木': 0,
    '建筑': 0, '生物': 0, '环境': 0, '材料': 0
}

Sdepts = [
    '计算机', '化学', '电信', '物理',
    '自动化', '电气', '机械', '土木',
    '建筑', '生物', '环境', '材料'
]

name = {
    '计算机': 'cs', '化学': 'hx', '电信': 'dx', '物理': 'wl',
    '自动化': 'zdh', '电气': 'dq', '机械': 'jx', '土木': 'tm',
    '建筑': 'jz', '生物': 'sw', '环境': 'hj', '材料': 'cl'
}

years = [2019, 2020, 2021, 2022]

for year in years:
    for dept in Sdepts:
        affected_rows = 0
        cursor.execute("SELECT * FROM information_schema.views where TABLE_NAME = 'my_view';")
        if cursor.rowcount != 0:
            cursor.execute("drop view my_view")
        sql = "create VIEW my_view (`id`, Sid, Sclass, Sdept) as " \
              "SELECT row_number() OVER ( ORDER BY Sdept ) AS `id`, Sid, Sclass, Sdept " \
              "FROM student where Sdept = \'%s\' and Sid like %s;" % (dept, "\'" + str(year) + "%\'")
        print(sql)
        cursor.execute(sql)
        sql = "select * from my_view;"
        cursor.execute(sql)
        rows = cursor.fetchall()
        for row in rows:
            # print(row)
            id = row[0]
            Sid = row[1]
            cnt[dept] += 1
            sql = "update student set Sclass = \'%s\' where Sid = \'%s\' " % (
                name[dept] + str(year) + '{:04d}'.format(cnt[dept] // 30 + 1), Sid)
            affected_rows += cursor.execute(sql)
        print("受影响的行数为：", affected_rows)

print(cnt)
# 提交事务
conn.commit()
# 关闭游标和连接
cursor.close()
conn.close()
