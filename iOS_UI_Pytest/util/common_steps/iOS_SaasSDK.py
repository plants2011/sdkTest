__author__ = "leixin"


import  wda
import os
import time
import  logging

from util.conf.instance import Instance


bundle_id = "com.hmcloud.CloudPlayerDemo"

C = wda.USBClient()
s = None

def create_session():
    s = C.session(bundle_id)
    #s.set_alert_callback(alert_callback)
    return  s

def setup_function():
    global s
    logging.info(C.status())
    s = create_session()

def alert_callback(session):
    btns = set([u'不再提醒', 'OK', u'知道了', 'Allow']).intersection(session.alert.buttons())
    if len(btns) == 0:
        raise RuntimeError("Alert can not handled, buttons: " + ', '.join(session.alert.buttons()))
    session.alert.click(list(btns)[0])

def teardown_function():
    s.close()


def start_pkg():
    c = Instance.get_device()
    s = Instance.app(bundle_id)

def  stop_pkg():
    c = Instance.get_device()

