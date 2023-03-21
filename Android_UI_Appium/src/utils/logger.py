# -*-coding:utf-8-*-

###
### enhance logger
### by raphyer

import src.utils.log as log
import traceback


class Logger:

  def __init__(self, tag, module="root"):

    self._tag = "[{}]".format(tag)
    self._logger = log.getLogger(module)

  def _format(self, msg, v):

    try:
      if not v:
        return "{} {}".format(self._tag, msg)

      return "{} {}".format(self._tag, msg.format(*v))
    except Exception as e:
      return "{} got invalid msg: '{}', v: {}, Exception: {}".format(self._tag, msg, v, e)

  def debug(self, msg, *v):
    self._logger.debug(self._format(msg, v))

  def info(self, msg, *v):
    self._logger.info(self._format(msg, v))

  def warn(self, msg, *v):
    self._logger.warn(self._format(msg, v))

  def error(self, msg, *v):
    self._logger.error(self._format(msg, v))

def getLogger(tag, module="root"):
  return Logger(tag, module)


if __name__ == '__main__':
  logger = Logger("UT")

  logger.debug("normal")
  logger.debug("test bad, {}, {}", "a")
  logger.debug("format OK: {}", "b", 1)

  logger.info("normal")
  logger.info("test bad, {}, {}", "a")
  logger.info("format OK: {}", "b", 1)

  logger.warn("WARN_CODE", "normal message")
  logger.warn("WARN_CODE", "test bad, {}, {}", "a")
  logger.warn("WARN_CODE", "format OK: {}", "b", 1)

  logger.error("ERROR_CODE", "normal message")
  logger.error("ERROR_CODE", "test bad, {}, {}", "a")
  logger.error("ERROR_CODE", "format OK: {}", "b", 1)
