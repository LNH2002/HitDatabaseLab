from courseUtil import *
import pymysql
from faker import Faker

# 连接数据库
conn = pymysql.connect(host='localhost', user='root', password='123456', database='school')
cursor = conn.cursor()

# 创建Faker对象
fake = Faker()
rand = random.Random()

num_courses = 1000 + rand.randint(0, 100)
course_names = get_courses_list(num_courses=num_courses)
Cid_list = get_course_Cid_list(num_courses=num_courses)
hours_list = get_course_hours(num_courses=num_courses)

affected_rows = 0

for i in range(num_courses):
    sql = "INSERT INTO course (Cid,Cname,Chours)" \
          "VALUES (\'%s\',\'%s\',\'%s\')" \
          % (Cid_list[i], course_names[i], hours_list[i])
    affected_rows = affected_rows + cursor.execute(sql)

print("受影响的行数为：", affected_rows)

# 提交事务
conn.commit()
# 关闭游标和连接
cursor.close()
conn.close()
