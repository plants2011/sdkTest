__author__ = "leixin"

import  logging
import time

from util import demo
from util.conf.instance import Instance

from util.common_steps import h5_Android
#from util.common_steps import android_common
#from util.common_steps import android_demo_setting


#log = logging.getLogger(__name__)

def test_setup():
    demo.start_demo("H5")

    time.sleep(20)
    h5_Android.getcid()




    #demo.stop_demo("H5")