import random
import string
from faker import Faker

fake = Faker()
rand = random.Random()
# 定义要生成的课程名称数量

# 定义每个课程名称的长度范围
min_length = 5
max_length = 10
prefix = ('cs', 'hx', 'dx', 'wl',
          'zdh', 'dq', 'jx'  'tm',
          'jz', 'sw', 'hj', 'cl')


# 生成指定数量的随机课程名称
def get_courses_list(num_courses):
    course_names = []
    for i in range(num_courses):
        # 随机生成课程名称长度
        length = random.randint(min_length, max_length)

        # 随机从字符串中选择字符来构成课程名称
        course_name = ''.join(random.choices(string.ascii_uppercase + string.digits, k=length))
        course_names.append(course_name)
    return course_names


def get_course_Cid_list(num_courses):
    Cid_list = set()
    while Cid_list.__len__() != num_courses:
        element = fake.random_element(elements=prefix) + '{:04d}'.format(rand.randint(1, 10000))
        Cid_list.add(element)
    return list(Cid_list)


def get_course_hours(num_courses):
    hours_list = []
    for i in range(num_courses):
        hours_list.append(rand.randint(1, 24) * 8)
    return hours_list
