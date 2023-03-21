import os
from configparser import ConfigParser


def config_get(config_path, section, key):
    # get full path of config.ini
    try:
        curret_path = os.getcwd()
        filepath = os.path.join(curret_path, config_path)
        with open(filepath) as fp:
            cf = ConfigParser()
            cf.read_file(fp)
            return cf.get(section, key)
    except:
        return None
