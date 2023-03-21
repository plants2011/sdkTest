import importlib
import time
import logging


from util.conf.config import config_get
from util.conf.instance import Instance

def start_demo(type):
    demo_type = config_get("pytest.ini", "demo", type)
    demo_steps = importlib.import_module("util.common_steps.%s"%demo_type)
    return demo_steps.start_pkg()

def stop_demo(type):
    demo_type = config_get("pytest.ini", "demo", type)
    demo_steps = importlib.import_module("util.common_steps.%s"%demo_type)
    return demo_steps.stop_pkg()
def check_run_dependences(type):
    demo_type = config_get("pytest.ini", "demo", type)
    demo_steps = importlib.import_module("util.common_steps.%s"%demo_type)
    return demo_steps.check_dependences()

def wait_for_toast(startswith=None, equal=None, greaterequal=None, timeout=10):
    d = Instance.get_device()
    if startswith == None and equal == None:
        return False

    for i in range(timeout*2):
            try:
                if startswith != None:
                    assert(str(d.toast.get_message()).startswith(startswith))
                elif equal != None:
                    assert(str(d.toast.get_message()) == equal)
                elif greaterequal != None:
                    assert(str(d.toast.get_message()) == greaterequal)
                break
            except:
                time.sleep(0.5)
    if startswith != None:
        assert(str(d.toast.get_message()).startswith(startswith))
    elif equal != None:
        assert(str(d.toast.get_message()) == equal)
    elif greaterequal != None:
        logging.info(greaterequal)
        logging.info(d.toast.get_message())
        assert(str(d.toast.get_message()) >= greaterequal)
    #pass