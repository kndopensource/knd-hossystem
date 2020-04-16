knd-hossysem快速开发平台（前后端分离版本）
===============

当前最新版本： 1.0.0（发布日期：2020-04-16）


项目介绍：
-----------------------------------

<h3 align="center">Java RAD framework for enterprise web applications</h3>

Hos.dev 是一款基于代码生成器的J2EE快速开发平台！采用前后端分离架构：SpringBoot2.x，Ant Design&Vue，Mybatis-plus，Shiro，JWT。强大的代码生成器让前后端代码一键生成，无需写任何代码! Hos.dev引领新的开发模式(Online Coding模式-> 代码生成器模式-> 手工MERGE智能开发)， 帮助解决Java项目70%的重复工作，让开发更多关注业务逻辑。既能快速提高开发效率，帮助公司节省成本，同时又不失灵活性！Hos.dev还独创在线开发模式（No代码概念）：在线表单配置（表单设计器）、移动配置能力、工作流配置（在线设计流程）、报表配置能力、在线图表配置、插件能力（可插拔）等等！


KND宗旨是: 简单功能由Online Coding配置实现（在线配置表单、在线配置报表、在线图表设计、在线设计流程、在线设计表单），复杂功能由代码生成器生成进行手工Merge，既保证了智能又兼顾了灵活; 
业务流程采用工作流来实现、扩展出任务接口，供开发编写业务逻辑，表单提供多种解决方案： 表单设计器、online配置表单、编码表单。同时实现了流程与表单的分离设计（松耦合）、并支持任务节点灵活配置，既保证了公司流程的保密性，又减少了开发人员的工作量。


适用项目
-----------------------------------
Hos.dev快速开发平台，可以应用在任何J2EE项目的开发中，尤其适合企业信息管理系统（MIS）、内部办公系统（OA）、企业资源计划系统（ERP）、客户关系管理系统（CRM）等，其半智能手工Merge的开发方式，可以显著提高开发效率70%以上，极大降低开发成本。



技术文档
-----------------------------------

- 在线演示 ： [http://boot.KND.com](http://boot.KND.com)

- 技术官网：  [http://www.KND.com](http://www.KND.com)

- 开发文档：  [http://doc.KND.com](http://doc.KND.com/1273753)

- 视频教程  ：[Hos.dev入门系列视频](https://space.bilibili.com/454617261/channel/detail?cid=84186)

- 常见问题：  [入门常见问题大全](http://bbs.KND.com/forum.php?mod=viewthread&tid=7816&extra=page%3D1)

- 更新日志：  [版本日志](http://www.KND.com/#/doc/changelog)

 
前端技术架构：
-----------------------------------
#### 开发环境
  node.js
  yarn
  idea
- 默认登录账号： admin/123456

#### 前端
 
- [Vue 2.6.10](https://cn.vuejs.org/),[Vuex](https://vuex.vuejs.org/zh/),[Vue Router](https://router.vuejs.org/zh/)
- [Axios](https://github.com/axios/axios)
- [ant-design-vue](https://vuecomponent.github.io/ant-design-vue/docs/vue/introduce-cn/)
- [webpack](https://www.webpackjs.com/),[yarn](https://yarnpkg.com/zh-Hans/)
- [vue-cropper](https://github.com/xyxiao001/vue-cropper) - 头像裁剪组件
- [@antv/g2](https://antv.alipay.com/zh-cn/index.html) - Alipay AntV 数据可视化图表
- [Viser-vue](https://viserjs.github.io/docs.html#/viser/guide/installation)  - antv/g2 封装实现
- eslint，[@vue/cli 3.2.1](https://cli.vuejs.org/zh/guide)
- vue-print-nb - 打印




### 功能模块
```
├─系统管理
│  ├─用户管理
│  ├─角色管理
│  ├─菜单管理
│  ├─权限设置（支持按钮权限、数据权限）
│  ├─表单权限（控制字段禁用、隐藏）
│  ├─部门管理
│  └─字典管理
│  └─树分类字典
│  └─系统公告
│  └─我的组织机构
│  └─职务管理
│  └─通讯录
├─消息中心
│  ├─消息管理
│  ├─模板管理
├─智能化功能
│  ├─代码生成器功能（一键生成前后端代码，生成后无需修改直接用，绝对是后端开发福音）
│  ├─代码生成器模板（提供4套模板，分别支持单表和一对多模型，不同风格选择）
│  ├─代码生成器模板（生成代码，自带excel导入导出）
│  ├─查询过滤器（查询逻辑无需编码，系统根据页面配置自动生成）
│  ├─高级查询器（弹窗自动组合查询条件）
│  ├─Excel导入导出工具集成（支持单表，一对多 导入导出）
│  ├─平台移动自适应支持
├─系统监控
│  ├─性能扫描监控
│  │  ├─监控 Redis
│  │  ├─Tomcat
│  │  ├─jvm
│  │  ├─服务器信息
│  │  ├─请求追踪
│  │  ├─磁盘监控
│  ├─定时任务
│  ├─系统日志
│  ├─消息中心（支持短信、邮件、微信推送等等）
│  ├─数据日志（记录数据快照，可对比快照，查看数据变更情况）
│  ├─系统通知
│  ├─SQL监控
│  ├─swagger-ui(在线接口文档)
│─报表示例
│  ├─曲线图
│  └─饼状图
│  └─柱状图
│  └─折线图
│  └─面积图
│  └─雷达图
│  └─仪表图
│  └─进度条
│  └─排名列表
│  └─等等
│─大屏模板
│  ├─作战指挥中心大屏
│  └─物流服务中心大屏
│─常用示例
│  ├─自定义组件
│  ├─对象存储(对接阿里云)
│  ├─单表模型例子
│  └─一对多模型例子
│  └─打印例子
│  └─一对多TAB例子
│  └─内嵌table例子
│  └─常用选择组件
│  └─异步树table
│  └─接口模拟测试
│  └─表格合计示例
│  └─异步树列表示例
│  └─一对多JEditable
│  └─JEditable组件示例
│  └─图片拖拽排序
│  └─图片翻页
│  └─图片预览
│  └─PDF预览
│  └─分屏功能
│─封装通用组件	
│  ├─行编辑表格JEditableTable
│  └─省略显示组件
│  └─时间控件
│  └─高级查询
│  └─用户选择组件
│  └─报表组件封装
│  └─字典组件
│  └─下拉多选组件
│  └─选人组件
│  └─选部门组件
│  └─通过部门选人组件
│  └─封装曲线、柱状图、饼状图、折线图等等报表的组件（经过封装，使用简单）
│  └─在线code编辑器
│  └─上传文件组件
│  └─验证码组件
│  └─树列表组件
│  └─表单禁用组件
│  └─等等
│─更多页面模板
│  ├─各种高级表单
│  ├─各种列表效果
│  └─结果页面
│  └─异常页面
│  └─个人页面
├─高级功能
│  ├─系统编码规则
│  ├─提供单点登录CAS集成方案
│  ├─提供APP发布方案
│  ├─集成Websocket消息通知机制
├─Online在线开发(暂未开源)
│  ├─Online在线表单 - 功能已开放
│  ├─在线代码生成器 - 功能已开放
│  ├─Online在线报表 - 功能已开放
│  ├─Online在线图表
│  ├─Online图表模板配置
│  ├─高级表单设计器
│─流程模块功能 (暂不开源)
│  ├─流程设计器
│  ├─在线表单设计
│  └─我的任务
│  └─历史流程
│  └─历史流程
│  └─流程实例管理
│  └─流程监听管理
│  └─流程表达式
│  └─我发起的流程
│  └─我的抄送
│  └─流程委派、抄送、跳转
│  └─。。。
└─其他模块
   └─更多功能开发中。。
   
```
   
前端开发环境和依赖
----
- node
- yarn
- webpack
- eslint
- @vue/cli 3.2.1
- [ant-design-vue](https://github.com/vueComponent/ant-design-vue) - Ant Design Of Vue 实现
- [vue-cropper](https://github.com/xyxiao001/vue-cropper) - 头像裁剪组件
- [@antv/g2](https://antv.alipay.com/zh-cn/index.html) - Alipay AntV 数据可视化图表
- [Viser-vue](https://viserjs.github.io/docs.html#/viser/guide/installation)  - antv/g2 封装实现
- [KND-boot-angular 版本](https://gitee.com/dangzhenghui/KND-boot)

项目下载和运行
----

- 拉取项目代码
```bash
git clone https://github.com/zhangdaiscott/KND-boot.git
cd  KND-boot/ant-design-KND-vue
```

1. 安装node.js
2. 切换到ant-design-KND-vue文件夹下
```
# 安装yarn
npm install -g yarn

# 下载依赖
yarn install

# 启动
yarn run serve

# 编译项目
yarn run build

# Lints and fixes files
yarn run lint
```


系统效果
----
##### 大屏模板
![输入图片说明](https://static.oschina.net/uploads/img/201912/25133248_Ag1C.jpg "在这里输入图片标题")

![输入图片说明](https://static.oschina.net/uploads/img/201912/25133301_k9Kc.jpg "在这里输入图片标题")

##### PC端
![输入图片说明](https://static.oschina.net/uploads/img/201904/14155402_AmlV.png "在这里输入图片标题")
![输入图片说明](https://static.oschina.net/uploads/img/201904/14160657_cHwb.png "在这里输入图片标题")
![输入图片说明](https://static.oschina.net/uploads/img/201904/14160813_KmXS.png "在这里输入图片标题")
![输入图片说明](https://static.oschina.net/uploads/img/201904/14160935_Nibs.png "在这里输入图片标题")
![输入图片说明](https://static.oschina.net/uploads/img/201904/14161004_bxQ4.png "在这里输入图片标题")


##### 在线接口文档
![输入图片说明](https://static.oschina.net/uploads/img/201908/27095258_M2Xq.png "在这里输入图片标题")
![输入图片说明](https://static.oschina.net/uploads/img/201904/14160957_hN3X.png "在这里输入图片标题")


##### 报表
![输入图片说明](https://static.oschina.net/uploads/img/201904/14160828_pkFr.png "在这里输入图片标题")
![输入图片说明](https://static.oschina.net/uploads/img/201904/14160834_Lo23.png "在这里输入图片标题")
![输入图片说明](https://static.oschina.net/uploads/img/201904/14160842_QK7B.png "在这里输入图片标题")
![输入图片说明](https://static.oschina.net/uploads/img/201904/14160849_GBm5.png "在这里输入图片标题")
![输入图片说明](https://static.oschina.net/uploads/img/201904/14160858_6RAM.png "在这里输入图片标题")

##### 流程
![输入图片说明](https://static.oschina.net/uploads/img/201904/14160623_8fwk.png "在这里输入图片标题")
![输入图片说明](https://static.oschina.net/uploads/img/201904/14160917_9Ftz.png "在这里输入图片标题")
![输入图片说明](https://static.oschina.net/uploads/img/201904/14160633_u59G.png "在这里输入图片标题")
![输入图片说明](https://static.oschina.net/uploads/img/201907/05165142_yyQ7.png "在这里输入图片标题")


##### 手机端
![](https://oscimg.oschina.net/oscnet/da543c5d0d57baab0cecaa4670c8b68c521.jpg)
![](https://oscimg.oschina.net/oscnet/fda4bd82cab9d682de1c1fbf2060bf14fa6.jpg)

##### PAD端
![](https://oscimg.oschina.net/oscnet/e90fef970a8c33790ab03ffd6c4c7cec225.jpg)
![](https://oscimg.oschina.net/oscnet/d78218803a9e856a0aa82b45efc49849a0c.jpg)
![](https://oscimg.oschina.net/oscnet/0404054d9a12647ef6f82cf9cfb80a5ac02.jpg)
![](https://oscimg.oschina.net/oscnet/59c23b230f52384e588ee16309b44fa20de.jpg)


其他说明
----

- 项目使用的 [vue-cli3](https://cli.vuejs.org/guide/), 请更新您的 cli

- 关闭 Eslint (不推荐) 移除 `package.json` 中 `eslintConfig` 整个节点代码

- 修改 Ant Design 配色，在文件 `vue.config.js` 中，其他 less 变量覆盖参考 [ant design](https://ant.design/docs/react/customize-theme-cn) 官方说明
```ecmascript 6
  css: {
    loaderOptions: {
      less: {
        modifyVars: {
          /* less 变量覆盖，用于自定义 ant design 主题 */

          'primary-color': '#F5222D',
          'link-color': '#F5222D',
          'border-radius-base': '4px',
        },
        javascriptEnabled: true,
      }
    }
  }
```

附属文档
----
- [Ant Design Vue](https://vuecomponent.github.io/ant-design-vue/docs/vue/introduce-cn)

- [报表 viser-vue](https://viserjs.github.io/demo.html#/viser/bar/basic-bar)

- [Vue](https://cn.vuejs.org/v2/guide)

- [路由/菜单说明](https://github.com/zhangdaiscott/KND-boot/tree/master/ant-design-KND-vue/src/router/README.md)

- [ANTD 默认配置项](https://github.com/zhangdaiscott/KND-boot/tree/master/ant-design-KND-vue/src/defaultSettings.js)

- 其他待补充...


备注
----

> @vue/cli 升级后，eslint 规则更新了。由于影响到全部 .vue 文件，需要逐个验证。既暂时关闭部分原本不验证的规则，后期维护时，在逐步修正这些 rules


## 捐赠 
不需要捐赠