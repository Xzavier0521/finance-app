#金榕家服务端

##接口验签
由于接口是对外公开，为防止恶意攻击者通过修改参数(eg.充值提现事件)、重放请求来攻击系统(eg.check/mobile被批量请求)。

### 字段说明
字段 		| 说明  
------		| ------
timestamp | 时间戳  
noncestr 	| 随机字符串  
signature	| 签名
appId		| 业务线id

appKey生成规则：appId的md5结果取8~24位
> 请根据`appId`动态生成，切勿直接保存`appKey`

appId		| appKey
----		| ----
jrj			| 8ce620067b979ff2


### 客户端  

为解决用户手动修改系统时间，须通过`/common/current`接口获取服务器时间，保存时间差。此接口开放，不做签名验证。`timestamp`参数须与该接口返回的格式一致，为到毫秒级的13位数字。


* 请求参数添加`timestamp`、`noncestr`
    * timestamp通过服务端接口获取 GET systemCurrentTime
* 按字母顺序排序并序列化，然后`URI encode`   
	* 目前只允许一级参数
* 尾部拼接`appKey`值
* 做<code>MD5</code>加密，得到`signature`  
* 原始参数上加入`timestamp`、`noncestr`、`appId`、`signature`作为最终请求参数  


### 服务端

* 验证`timestamp`时效性（5分钟）
	* 过期：请求无效 
	* 在有效期内：继续下一步验证
* 验证`signature`有效性  
	* 与客户端同样的加密方式生成`signature`以验证是否一致
	* 否：请求无效（<mark>数据篡改</mark>）
	* 是：继续下一步验证
* 验证`signature`是否已在`redis`中
	* 是：请求无效（<mark>重放攻击</mark>）
	* 否：请求有效，把`signature`写入`redis`，时效与`timestamp`一致

> 强验证：请求无效`http status`为`403`   


### 举例说明

以 **/userinfo/teamInfo** 接口为例，请求参数如下：

~~~~
{
	"type":"2",
	"maxCount":"300"
}
~~~~

加入`timestamp`以及`noncestr`后排序并序列化，再拼接appKey值：

~~~~
maxCount=300&noncestr=8ojl72qrxrm&timestamp=1536653855763&type=28ce620067b979ff2
~~~~

<code>MD5</code>加密结果：

~~~~
c323f75cbcdbc769970806537002c5fd
~~~~

最终的请求参数：

~~~~
{
	"type":"2",
	"maxCount":"300",
	"timestamp":"1536653855763",
	"noncestr":"8ojl72qrxrm",
	"signature":"c323f75cbcdbc769970806537002c5fd",
	"appId":"jrj"
}
~~~~


#JWT
在登录成功之后，会在请求体中返回 jwt 的值xxxx，在请求需要登录的接口时，需要在 header 中传递 jwt:xxxxx；

1. jwt 是用户登录的唯一标识，允许一个账号在多个终端同时在线；

1. 服务端会对 jwt做自动刷新，前端无需主动刷新；

1. 过期时间有服务端控制，目前生产环境为100天；
