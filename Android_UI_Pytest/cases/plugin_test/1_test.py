# -*- encoding=utf8 -*-
__author__ = "chenlei"

import logging
import time

from util import demo
from util.common_steps import plugin_demo

log = logging.getLogger(__name__)


def test_setup():
    demo.start_demo("AndroidPlug")
    #demo.start_demo()


    #plugin_demo.download_plugin()

    #plugin_demo.install_plugin()
    plugin_demo.install_initplugin()
    plugin_demo.load_plugin()
    plugin_demo.change_test_env(True,"rel1")
    plugin_demo.init_plugin()
    time.sleep(1)

    plugin_demo.play_plugin()



    time.sleep(20)

    plugin_demo.getcid_plugin()
    #plugin_demo.d_back()







    pass

# def test_setup_2():
#
#     pass





