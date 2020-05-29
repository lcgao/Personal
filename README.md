## 概要
在这一页，我们将会概述 SDK 中所有的 Util 相关的API。

|API|说明|
|-|-|
|[getSerialNumber()](#getSerialNumber)|获取 **序列号** |
|[getBatteryData()](#getBatteryData)|获取 **电池数据** |
|[showTopBar()](#showTopBar)|显示 **顶部栏** |
|[hideTopBar()](#hideTopBar)|隐藏 **顶部栏** |
|[isHardButtonsDiabled()](#isHardButtonsDiabled)|获取 **硬件按钮** 是否被禁用 |
|[setHardButtonsDisabled(boolean disable)](#setHardButtonsDisabled)|禁用（开启）**硬件按钮** |
|[getPrivacyMode()](#getPrivacyMode)|获取 **隐私模式** 是否开启|
|[setPrivacyMode(boolean on)](#setPrivacyMode)|开启（关闭） **隐私模式** |
|[getLauncherVersion()](#getLauncherVersion)|获取 **Launcher** 版本|
|[getRoboxVersion()](#getRoboxVersion)|获取 **Robox** 版本|
|[OnBatteryStatusChangedListener](#OnBatteryStatusChangedListener)| **电池状态变化** 监听器|
|[OnPrivacyModeChangedListener](#OnPrivacyModeChangedListener)| **隐私模式状态变化**监听器|

</br>  

### <span id="getSerialNumber">获取序列号</span>
用这个方法来获取 temi 的序列号。如果需要用一个唯一编号来标识你的temi，那么可以使用这个方法。

- 返回值

  |类型|说明|
  |-|-|
  |String|temi 的序列号|

- 原型

  ``` java
  String getSerialNumber();
  ```

#  

### <span id="getBatteryData">获取电池数据</span>
用这个方法可以请求获取temi的电池相关信息，信息包括剩余电量的百分比以及充电状态。

- 返回值

  |类型|说明|
  |-|-|
  |[BatteryData](#batteryData)|电池数据|

- 原型

  ``` java
  BatteryData getBatteryData();
  ```

#  

### <span id="showTopBar">显示顶部栏</span>
用这个方法来显示 Launcher 的顶部栏。

- 原型

  ``` java
  void showTopBar();
  ```

#  

### <span id="hideTopBar">隐藏顶部栏</hideTopBar>
用这个方法可以隐藏 Launcher 的顶部栏。

- 原型

  ``` java
  void hideTopBar();
  ```

#  

### <span id="getPrivacyMode">获取隐私模式是否开启</getPrivacyMode>
通过这个方法来获取隐私模式当前是否开启。

- 返回值

  |类型|说明|
  |-|-|
  |boolean|是否已开启隐私模式，true 表示隐私模式已开启，false 表示已关闭|

- 原型

  ``` java
  boolean getPrivacyMode();
  ```

#  

### <span id="setPrivacyMode">开启或关闭隐私模式</span>
通过这个方法来开启或关闭隐私模式。

- 参数

  |参数|类型|说明|
  |-|-|-|
  |on|boolean|开启或关闭隐私模式，true 表示开启隐私模式，false 表示关闭|

- 原型

  ``` java
  void setPrivacyMode(boolean on);
  ```

#  

### <span id="isHardButtonsDiabled">获取硬件按钮是否被禁用</span>
通过这个方法来获取硬件按钮当前是否被禁用。

- 返回值

  |类型|说明|
  |-|-|
  |boolean|是否已禁用硬件按钮，true 表示硬件按钮已禁用，false 表示未禁用|

- 原型

  ``` java
  boolean isHardButtonsDisabled();
  ```

#  

### <span id="setHardButtonsDisabled">禁用或开启硬件按钮</span>
通过这个方法来禁用或启用硬件按钮

- 参数

  |参数|类型|说明|
  |-|-|-|
  |disable|boolean|禁用或启用硬件按钮，**true** 表示禁用，**false** 表示启用|

- 原型

  ``` java
  void setHardButtonsDisabled(boolean disable);
  ```

#  

### <span id="getLauncherVersion">获取 Launcher 版本</span>
用这个方法来获取 Launcher 的版本。

- 返回值

  |类型|说明|
  |-|-|
  |String|Launcher 的版本|

- 原型

  ``` java
  String getLauncherVersion();
  ```

#  

### <span id="getRoboxVersion">获取 Robox 版本</span>
用这个方法来获取 Robox 的版本。

- 返回值

  |类型|说明|
  |-|-|
  |String|Robox 的版本|

- 原型

  ``` java
  String getRoboxVersion();
  ```

</br>

## 监听器
以下是工具相关的监听器详细信息。

### <span id="OnBatteryStatusChangedListener">电池状态变化监听器</span>
可监听电池电量、充电状态的变化。在你的上下文中实现这个监听器接口，并重写接口中的方法以获取电池状态变化信息。

``` java
package com.robotemi.sdk.listener;

interface OnBatteryStatusChangedListener{};
```

**回调方法**

- 参数

  |参数|类型|说明|
  |-|-|-|
  |batteryData|[BatteryData](https://github.com/robotemi/sdk/wiki/Utils_chn#battery-data)|电池数据发生变化后的对象|

- 原型

  ``` java
  void onBatteryStatusChanged(BatteryData batteryData);
  ```

**添加监听的方法**

- 参数

  |参数|类型|说明|
  |-|-|-|
  |listener|OnBatteryStatusChangedListener|实现了此接口的类的实例|

- 原型

  ``` java
  void addOnBatteryStatusChangedListener(OnBatteryStatusChangedListener listener);
  ```

**移除监听的方法**

- 参数

  |参数|类型|说明|
  |-|-|-|
  |listener|OnBatteryStatusChangedListener|实现了此接口的类的实例|

- 原型

  ``` java
  void removeOnBatteryStatusChangedListener(OnBatteryStatusChangedListener listener);
  ```

#  

### <span id="OnPrivacyModeChangedListener">隐私模式状态变化监听器</span>
可监听隐私模式开关状态的变化。在你的上下文中实现这个监听器接口，并重写接口中的方法以获取隐私模式状态变化。

``` java
package com.robotemi.sdk.listener;

interface OnPrivacyModeChangedListener {};
```

**回调方法**

- 参数

  |参数|类型|说明|
  |-|-|-|
  |state|boolean|电池数据发生变化后的对象|

- 原型

  ``` java
  void onPrivacyModeChanged(boolean state);
  ```

**添加监听的方法**

- 参数

  |参数|类型|说明|
  |-|-|-|
  |listener|OnPrivacyModeChangedListener|实现了此接口的类的实例|

- 原型

  ``` java
  void addOnPrivacyModeStateChangedListener(OnPrivacyModeChangedListener listener);
  ```

**移除监听的方法**

- 参数

  |参数|类型|说明|
  |-|-|-|
  |listener|OnBatteryStatusChangedListener|实现了此接口的类的实例|

- 原型

  ``` java
  void removeOnPrivacyModeStateChangedListener(OnPrivacyModeChangedListener listener);
  ```

</br>

## 模型
以下是上述方法中用到的数据模型。

### <span id="batteryData">电池数据</span>
用于保存电池相关的信息的类。

``` java
package com.robotemi.sdk;

class BatteryData {};
```

**属性**

|属性|类型|说明|
|-|-|-|
|level|int|电池电量的百分比 1~100 %|
|isCharging|boolean|是否在充电中，true 表示正在充电，false 反之|

