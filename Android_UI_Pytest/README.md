# 使用说明

## 关于框架
python3.6+

pytest + uiautomator2,还用到了一些pytest的开源插件，比如日志和htmlreport

有关uiautomator2：是网易一个游戏测试的同学开源的一份自动化框架，包含对native的很好的支持，和对截图对比功能的支持，具体参考：https://github.com/openatx/uiautomator2

插件安装：
```shell
pip install uiautomator2

#为了方便抓取UI元素，和编写自动化代码，大家可以安装这个工具
#安装完成后的启动命令 python -m weditor
pip install -U weditor

#pytest库， pytest-html是html-report插件
pip install pytest pytest-html
#pytest库，支持多次执行用例插件
pip install pytest-repeat
#json报告格式
pip install pytest-json-report --upgrade 


#--count=1  用例执行次数
#--json-report --json-report-file=testreport.json  json报告格式

```

## 关于核心目录结构和文件
* 核心目录结构
  * util: 放一些公用运行方法和公共函数
    * conf ： 
      * config.py 配置文件读取 ， 依赖python自带的configparser包读取ini格式文件
      * instance.py 设备获取文件，任何地方都可以通过Instance.get_device()拿到当前的uiautomator2的设备信息
    * common_steps:
      * 每个不同demo的差异化方法，start_pkg、stop_pkg、check_dependences 作为每个demo必须包含的方法需要重写， 这个文件名可以配置在pytest.ini代表使用不同的demo
    * demo.py: demo级别的通用方法
  * cases: 放置自动化用例，建议不同的demo的自动化代码分开目录存放
  * pytest.ini: 配置文件
  * conftest.py: pytest插件函数，在每个用例执行之后和整个运行执行前做了一些插件函数来实现初始化动作

## 配置文件pytest.ini
大概结构如下

```text
[pytest]
addopts = -s --capture=sys --html=report.html --self-contained-html
log_cli = 1
log_cli_level = INFO
log_cli_format = %(asctime)s [%(levelname)8s] %(message)s (%(filename)s:%(lineno)s)
log_cli_date_format=%Y-%m-%d %H:%M:%S

[demo]
type=plugin_demo #此处写使用的demo类型，理论上一次运行只能运行一种demo的自动化
sn=xxxxxx   #如果只挂一个手机，此行可以不写
```

## 关于pytest的运行
### 运行命令
请务必保证在sdk_test目录下执行命令，因为执行过程中会检查pytest.ini和conftest.py文件，来做为pytest执行的输入配置

运行命令如下：
```shell
pytest cases/plugin_test/
```


### 关于运行报告
命令执行完成后，会在当前运行目录下生成report.html文件，打开即可查阅所有case运行过程中的print和log日志

## 关于自动化用例的编写
* 编写自动化用例要求：
  * 文件命名必须尊重pytest的要求，以test结尾
  * 函数命名也必须尊重pytest的要求，以test结尾或者开头
  * 每个函数可以加上注解：@pytest.mark.smoke: 冒烟 @pytest.mark.major： 核心，用来做一些用例区分，这个可以通过conftest.py进行自主定义

### 建议最佳实践
* 保证所有通用操作封装都在utils/common_steps目录下来对应文件写入，保证代码依赖过程的单一，如有特殊依赖，可以单独在util/目录下进行python文件添加，此类依赖请务必保证各demo测试间的通用性
* 增加新的demo测试，请在common_steps目录下增加新的文件进行
* 暂时只支持AndroidSDK的测试，后续HTML相关的需要后续人员考虑扩展，能否做成一个框架，目前来看是有一定可能性的，需要做一些扩展重构
