# WeChatTakeawayApplet
## 基于Springboot搭建的外卖点餐微信小程序

### 主要功能
  - 商户
  
    1. 每日菜单菜谱的展示
    2. 变更营业时间
    3. 接单后打印订单并安排配送员送餐
  
  - 客户
    1. 浏览菜单并进行下单
    2. 微信支付
    3. 退货并收取退货金
    
### 技术栈
 
 - 后端：springboot+mybatis+[WxJavaSDK](https://github.com/Wechat-Group/WxJava)
 - 前端：微信小程序开发工具
 - 订单打印：飞鹅云打印机（后端对接）
 
### 运行环境
- OS:Centos7
- 数据库：Mysql8.0
- 开发基础框架：Spring boot
- web容器：Tomcat
 
 
### 使用说明
- clone本项目
- 导入sql文件[sql路径](https://github.com/TreasureJade/WeChatTakeawayApplet/blob/master/src/main/resources/sql/takeaway_applet.sql)
- 修改application.yml中的配置信息:
    - 数据库用户和密码
    - 根据需要修改jwt.secret和jwt.expiration
    - 微信支付配置
    - 微信小程序配置
- 在微信公众平台配置信息
- 启动项目（本地需要内网穿透）
- 打开微信小程序

### 修改内容
- 添加商户端自用原料及菜谱配送管理系统
