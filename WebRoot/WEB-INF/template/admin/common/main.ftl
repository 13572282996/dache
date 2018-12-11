<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>小孙专车运营管理平台</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" type="text/css" href="${base}/resources/dist/layuiadmin/layui/css/layui.css" media="all"> 
  <link rel="stylesheet" type="text/css" href="${base}/resources/dist/layuiadmin/style/admin.css" media="all"> 
  <link rel="stylesheet" type="text/css" href="${base}/resources/dist/layuiadmin/style/login.css" media="all"> 

  <link rel="stylesheet" type="text/css" href="${base}/resources/src/layuiadmin/layui/css/layui.css" media="all"> 
  <link rel="stylesheet" type="text/css" href="${base}/resources/src/layuiadmin/style/admin.css" media="all"> 
  <link rel="stylesheet" type="text/css" href="${base}/resources/src/layuiadmin/style/login.css" media="all"> 

  <script src="/resources/src/layuiadmin/layui/layui.js" type="text/javascript"></script>
  
  <script>
  /^http(s*):\/\//.test(location.href) || alert('请先部署到 localhost 下再访问');
  </script>
</head>
<body class="layui-layout-body">
  
  <div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
      <div class="layui-header">
        <!-- 头部区域 -->
        <ul class="layui-nav layui-layout-left">
          <li class="layui-nav-item layadmin-flexible" lay-unselect>
            <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
              <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
            </a>
          </li>
<!--      <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="http://www.layui.com/admin/" target="_blank" title="前台">
              <i class="layui-icon layui-icon-website"></i>
            </a>
          </li>
-->          
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;" layadmin-event="refresh" title="刷新">
              <i class="layui-icon layui-icon-refresh-3"></i>
            </a>
          </li>

        </ul>
        <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
        
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="theme">
              <i class="layui-icon layui-icon-theme"></i>
            </a>
          </li>          
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="note">
              <i class="layui-icon layui-icon-note"></i>
            </a>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="fullscreen">
              <i class="layui-icon layui-icon-screen-full"></i>
            </a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;">
              <cite>管理员</cite>
            </a>
            <dl class="layui-nav-child">
              <dd layadmin-event="logout" style="text-align: center;">
              	<a>退出</a>
              </dd>
            </dl>
          </li>
<li class="layui-nav-item">
            <a>
              <cite>&nbsp&nbsp</cite>
            </a>
</li>
          
<!--     <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li>
          <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
            <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li>
--> 
        </ul>
      </div>
      
      <!-- 侧边菜单 -->
      <div class="layui-side layui-side-menu">
        <div class="layui-side-scroll">
          <div class="layui-logo" lay-href="../common/index.jhtml" target="iframe">
            <span>小孙专车</span>
          </div>
          
          <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
            
            <li data-name="car" class="layui-nav-item">
              <a href="javascript:;" lay-tips="司机" lay-direction="2">
                <i class="layui-icon layui-icon-friends"></i>
                <cite>司机管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="car1">
                  <a lay-href="../driver/list.jhtml"  target="iframe">司机管理</a>
                </dd>
                <dd data-name="car2">
                  <a lay-href="../car/list.jhtml"  target="iframe">车辆管理</a>
                </dd>
              </dl>
            </li>
            <li data-name="peo" class="layui-nav-item">
              <a href="javascript:;" lay-tips="乘客管理" lay-direction="2">
                <i class="layui-icon layui-icon-group"></i>
                <cite>乘客管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="peo1">
                  <a lay-href="../member/list.jhtml">乘客管理</a>  
                </dd>
               </dl>
            </li>
            
            <li data-name="order" class="layui-nav-item">
              <a href="javascript:;" lay-tips="订单管理" lay-direction="2">
                <i class="layui-icon layui-icon-form"></i>
                <cite>订单管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="order1">
                  <a lay-href="../order/list.jhtml">订单管理</a>
                </dd>
              </dl>
            </li>
 
             <li data-name="caiwu" class="layui-nav-item">
              <a href="javascript:;" lay-tips="财务管理" lay-direction="2">
                <i class="layui-icon layui-icon-rmb"></i>
                <cite>财务管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="caiwu1">
                  <a lay-href="../income/incomeInfo.jhtml">财务列表</a>
                </dd>
                <dd data-name="caiwu2">
                  <a lay-href="../income/driverIcomeList.jhtml">未结算列表</a>
                </dd>
                <dd data-name="caiwu3">
                  <a lay-href="../income/driverInComeed.jhtml">已结算列表</a>
                </dd>                
              </dl>
            </li>           

             <li data-name="bill" class="layui-nav-item">
              <a href="javascript:;" lay-tips="发票管理" lay-direction="2">
                <i class="layui-icon layui-icon-tabs"></i>
                <cite>发票管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="bill1">
                  <a lay-href="../bill/list.jhtml">财务列表</a>
                </dd>               
              </dl>
            </li>    
            
             <li data-name="cause" class="layui-nav-item">
              <a href="javascript:;" lay-tips="配置管理" lay-direction="2">
                <i class="layui-icon layui-icon-senior"></i>
                <cite>配置管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="cause1">
                  <a lay-href="../priceConfig/list.jhtml">计价配置</a>
                </dd>
                <dd data-name="cause2">
                  <a lay-href="../cause/list.jhtml">取消原因配置</a>
                </dd>            
              </dl>
            </li>  

             <li data-name="article" class="layui-nav-item">
              <a href="javascript:;" lay-tips="内容管理" lay-direction="2">
                <i class="layui-icon layui-icon-read"></i>
                <cite>内容管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="article1">
                  <a lay-href="../article/list.jhtml">文章列表</a>
                </dd>
                <dd data-name="article2">
                  <a lay-href="../message/list.jhtml">消息列表</a>
                </dd>            
              </dl>
            </li>  
            
             <li data-name="set" class="layui-nav-item">
              <a href="javascript:;" lay-tips="系统设置" lay-direction="2">
                <i class="layui-icon layui-icon-set"></i>
                <cite>系统设置</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="set1">
                  <a lay-href="../setting/edit.jhtml">系统设置</a>
                </dd>
                <dd data-name="set2">
                  <a lay-href="../area/list.jhtml">地区管理</a>
                </dd>            
                <dd data-name="set3">
                  <a lay-href="../admin/list.jhtml">管理员</a>
                </dd> 
                <dd data-name="set4">
                  <a lay-href="../role/list.jhtml">角色管理</a>
                </dd> 
                <dd data-name="set5">
                  <a lay-href="../resource/list.jhtml">资源管理</a>
                </dd>           
                <dd data-name="set6">
                  <a lay-href="../log/list.jhtml">日志管理</a>
                </dd> 
                <dd data-name="set7">
                  <a lay-href="../cache/clear.jhtml">缓存管理</a>
                </dd> 
              </dl>
            </li>  
            
          </ul>
        </div>
      </div>

      <!-- 页面标签 -->
      <div class="layadmin-pagetabs" id="LAY_app_tabs">
        <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-down">
          <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
            <li class="layui-nav-item" lay-unselect>
              <a href="javascript:;"></a>
              <dl class="layui-nav-child layui-anim-fadein">
                <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
              </dl>
            </li>
          </ul>
        </div>
        <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
          <ul class="layui-tab-title" id="LAY_app_tabsheader">
            <li lay-id="home/console.html" lay-attr="home/console.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
          </ul>
        </div>
      </div>
      
      
      <!-- 主体内容 -->
      <div class="layui-body" id="LAY_app_body">
        <div class="layadmin-tabsbody-item layui-show">
          <iframe src="../common/index.jhtml" frameborder="0" class="layadmin-iframe"></iframe>
        </div>
      </div>
      
      <!-- 辅助元素，一般用于移动设备下遮罩 -->
      <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
  </div>


    <script>
      layui.config({
          base: '/resources/src/layuiadmin/'//静态资源所在路径
      }).extend({
          index: 'lib/index' //主入口模块
      }).use('index');
  </script>
  <script>
  layui.config({
    base: '/resources/dist/layuiadmin/'//静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use('index');
  </script>

</body>
</html>


