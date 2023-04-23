from courseUtil import *
import pymysql
from faker import Faker

# 连接数据库
conn = pymysql.connect(host='localhost', user='root', password='123456', database='school')
cursorStu = conn.cursor()
cursorCourse = conn.cursor()
cursorSc = conn.cursor()

# 创建Faker对象
fake = Faker()
rand = random.Random()
num_of_courses = [160, 96, 48, 16]
years = [2019, 2020, 2021, 2022]

affected_rows = 0

for i in range(4):
    sql = 'select Sid from student where Sid like \'' + str(years[i]) + '%\';'
    print(sql)
    cursorStu.execute(sql)
    rowStu = cursorStu.fetchone()
    while rowStu is not None:
        Sid = rowStu[0]
        rowStu = cursorStu.fetchone()
        sql = 'SELECT Cid from course ORDER BY RAND() LIMIT ' + str(num_of_courses[i]) + ";"
        cursorCourse.execute(sql)
        rowCourse = cursorCourse.fetchone()
        while rowCourse is not None:
            Cid = rowCourse[0]
            rowCourse = cursorCourse.fetchone()
            sql = 'insert into sc (Sid,Cid,Score) values (\'%s\',\'%s\',%s);' % (
                Sid, Cid, rand.randint(0, 99) + (0.5 if rand.randint(0, 1) % 2 == 0 else 0))
            cursorSc.execute(sql)
            affected_rows += 1
            if affected_rows % 10000 == 0:
                print(affected_rows)

print("受影响的行数为：", affected_rows)

# 提交事务
conn.commit()
# 关闭游标和连接
cursorStu.close()
conn.close()
