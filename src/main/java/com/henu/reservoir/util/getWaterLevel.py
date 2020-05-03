from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import pymysql
import time
import datetime
import sys


def get_data(rname):
    # 无界面设置
    chrome_options = Options()
    chrome_options.add_argument('--headless')
    driver_path = r'C:\Program Files (x86)\Google\Chrome Dev\Application\chromedriver.exe'
    url = "http://xxfb.mwr.cn/ssIndex.html"
    # 使用chrome驱动打开网站
    driver = webdriver.Chrome(executable_path=driver_path, chrome_options = chrome_options)
    driver.get(url=url)
    # 强制等待页面加载
    time.sleep(2)
    # 找到 大型水库 按钮id
    sk = driver.find_element_by_id('skimg')
    # 点击 大型水库 按钮
    sk.click()
    time.sleep(3)
    # 获取所有水库
    sktable = driver.find_element_by_id('sktable')
    # 转成列表
    all_sk = str(sktable.text)
    all_sk = all_sk.split('\n')
    # 找到丹江口所在列
    Danjiangkou = list()
    for sk in all_sk:
        if rname in sk:
            Danjiangkou = sk
    if len(Danjiangkou) == 0:
        print('error')
        return False
    # 获取待插入的参数
    helper = DbHelper()
    param = Danjiangkou.split()
    site_name = param[3]
    water_level = param[4]
    measured_storage = param[6]
    date = datetime.date.today()
    reservoir_id = helper.get_reservoir_id(site_name)
    data = [water_level, measured_storage, site_name, date, reservoir_id]
    return data


class DbHelper:
    # 连接数据库
    def __init__(self):
        self.conn = pymysql.connect(host="localhost", user="root", password="123456", database="reservoir",
                                    charset="utf8")
        self.cursor = self.conn.cursor()

    # 插入实测水位数据
    def insert(self, data):
        sql = "INSERT INTO measured_result(water_level, measured_storage, site_name, date, reservoir_id) " \
              "VALUES (%s, %s, %s, %s, %s);"
        try:
            self.cursor.execute(sql, data)
            self.conn.commit()
        except Exception:
            # 错误回滚
            self.conn.rollback()

    # 根据水库名称获得水库id
    def get_reservoir_id(self, reservoir_name):
        sql = "SELECT * FROM reservoir_info WHERE name = %s"
        try:
            self.cursor.execute(sql, reservoir_name)
            result = self.cursor.fetchall()
            for row in result:
                return row[0]
        except Exception:
            self.conn.rollback()


if __name__ == "__main__":
    # 读取传入的参数
    data = get_data(sys.argv[1])
    if data:
        db = DbHelper()
        db.insert(data)

