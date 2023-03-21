__author__ = "leixin"

import importlib

from util.conf.config import config_get
from util.conf.instance import Instance




def start_demo(type):
    demo_type = config_get("pytest.ini","demo",type)
    demo_steps = importlib.import_module("util.common_steps.%s"%demo_type)
    return  demo_steps.setup_function()

def stop_demo(type):
    demo_type = config_get("pytest.ini","demo",type)
    demo_steps = importlib.import_module("util.common_steps.%s"%demo_type)
    return  demo_steps.teardown_function()




