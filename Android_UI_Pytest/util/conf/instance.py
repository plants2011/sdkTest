import os

import uiautomator2

from util.conf import config


class Instance:
    __device = None
    @staticmethod
    def get_device() -> uiautomator2.Device:
        Instance.__device = Instance._init_device()
        return Instance.__device
    @staticmethod
    def _init_device():
        try:
            if os.path.exists("config.ini") == True:
                sn = config.config_get("pytest.ini", "demo", "sn")
                if sn != None:
                    return uiautomator2.connect(sn)
            return uiautomator2.connect()
        except:
            return None
