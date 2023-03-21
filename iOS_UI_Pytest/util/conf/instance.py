import os
import wda

from util.conf import config


class Instance:
    __device  =None
    @staticmethod
    def get_device() -> wda.Client:
        return  wda.USBClient()






