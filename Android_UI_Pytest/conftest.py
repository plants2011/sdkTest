import logging

import pytest

import util.demo
from util.conf.instance import Instance
from util.demo_server import demoServer

log = logging.getLogger(__name__)

def pytest_configure(config):
    marker_list = ["smoke", "major"]  # 标签名集合
    for markers in marker_list:
        config.addinivalue_line("markers", markers)


def pytest_runtest_setup(item):
    d = Instance.get_device()
    d.screen_on()
    pass

def pytest_runtest_teardown(item, nextitem):
    if item.function.__module__.startswith("cases.demo_server_test") == False:
        d = Instance.get_device()
        d.app_stop_all()
    # else:
    #     #demoserver每次运行完毕汇报覆盖率数据
    #     result = demoServer().uploadJacocoReport()
    #     log.info("jacocoReport: "+result)
    pass

def pytest_sessionstart(session):
    if Instance.get_device() == None:
        pytest.exit("设备初始化错误，请检查设备是否连接")
    # if util.demo.check_run_dependences() != True:
    #     pytest.exit("检查依赖失败，请确认依赖包和相关数据是否准备完成")

    pass