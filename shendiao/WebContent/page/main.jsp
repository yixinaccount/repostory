<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>华米</title>
	
	<jsp:include page="/common/easyui.jsp"></jsp:include>
	<style type="text/css"> 

        .footer {

            width: 100%;

            text-align: center;

            line-height: 35px;

        }

        .top-bg {

            background-color: #d8e4fe;

            height: 80px;

        }

    </style>
<script type="text/javascript">







	$(document).ready(function () {
	
		    $('.easyui-accordion li a').click(function () {
		
		        var tabTitle = $(this).text();
		
		        var url = $(this).attr("href");
		
		        addTab(tabTitle, url);
		
		        $('.easyui-accordion li div').removeClass("selected");
		
		        $(this).parent().addClass("selected");
		
		    }).hover(function () {
		
		        $(this).parent().addClass("hover");
		
		    }, function () {
		
		        $(this).parent().removeClass("hover");
		
	    });



    function addTab(subtitle, url) {


        if (!$('#tabs').tabs('exists', subtitle)) {

            $('#tabs').tabs('add', {

                title: subtitle,

                content: createFrame(url),

                closable: true,

                width: $('#mainPanle').width() - 10,

                height: $('#mainPanle').height() - 26

            });

        } else {

            $('#tabs').tabs('select', subtitle);

       }

        tabClose();

    }





    function createFrame(url) {

        var s = '<iframe  name="mainFrame" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';

        return s;

    }





    function tabClose() {

        /*双击关闭TAB选项卡*/

        $(".tabs-inner").dblclick(function () {

            var subtitle = $(this).children("span").text();

            $('#tabs').tabs('close', subtitle);

        })



        $(".tabs-inner").bind('contextmenu', function (e) {

            $('#mm').menu('show', {

                left: e.pageX,

                top: e.pageY,

            });

            var subtitle = $(this).children("span").text();

            $('#mm').data("currtab", subtitle);

            return false;

        });

    }



    //绑定右键菜单事件

	function tabCloseEven() {
	
	        //关闭当前
	
	 $('#mm-tabclose').click(function () {
	
	            var currtab_title = $('#mm').data("currtab");
	
	            $('#tabs').tabs('close', currtab_title);
	
	        })
	
	        //全部关闭
	
	 $('#mm-tabcloseall').click(function () {
	
	            $('.tabs-inner span').each(function (i, n) {
	
	                var t = $(n).text();
	
	               $('#tabs').tabs('close', t);
	
	            });
	
	   });



        //关闭除当前之外的TAB

        $('#mm-tabcloseother').click(function () {

            var currtab_title = $('#mm').data("currtab");

            $('.tabs-inner span').each(function (i, n) {

                var t = $(n).text();

                if (t != currtab_title)

                    $('#tabs').tabs('close', t);

            });

        });

        //关闭当前右侧的TAB

        $('#mm-tabcloseright').click(function () {

            var nextall = $('.tabs-selected').nextAll();

            if (nextall.length == 0) {

               //msgShow('系统提示','后边没有啦~~','error');

                alert('后边没有啦~~');

                return false;

            }

            nextall.each(function (i, n) {

                var t = $('a:eq(0) span', $(n)).text();

                $('#tabs').tabs('close', t);

            });

            return false;

       });

        //关闭当前左侧的TAB

        $('#mm-tabcloseleft').click(function () {

            var prevall = $('.tabs-selected').prevAll();

            if (prevall.length == 0) {

                alert('到头了，前边没有啦~~');

                return false;

            }

            prevall.each(function (i, n) {

                var t = $('a:eq(0) span', $(n)).text();

                $('#tabs').tabs('close', t);

            });

            return false;

        });



        //退出

        $("#mm-exit").click(function () {

            $('#mm').menu('hide');



        })

    }

}); 

</script>
  </head>


 
 






 <!--1. 在整个页面创建布局面板-->
<body class="easyui-layout">



    <div region="north" border="true" split="true" style="overflow: hidden; height: 80px;">

        <div class="top-bg" style="padding: 5px; overflow: hidden;">
         <!-- <table>
	        <tr>
		        <th style="padding-top:0px;font-size:36px;height: 0px">
		       		 baby去哪儿后台管理系统
		        	<span style="padding-top:30px;font-size:36px;height: 20px">baby去哪儿后台管理系统</span>
		        </th>
		        <th style="padding-left: 80%;font-size:24px;">
		      		  欢迎你,affsdsf
		        	<span style="padding-left: 400%;font-size:24px;" >欢迎你,affsdsf</span>
		        </th>
	        </tr>
        </table>  -->
        
        
       		<h1>大人来也后台管理系统</h1> 
           
        </div>
        
        

    </div>

    <div region="south" border="true" split="true" style="overflow: hidden; height: 40px;">

        <div class="footer">版权所有：<a href="http://www.baidu.com.com">华米文化</a></div>

    </div>

    <div region="west" split="true" title="导航菜单" style="width: 200px;">
    		<div id="aa" class="easyui-accordion" style="position: absolute;top: 27px;left:0px;right: 0px;bottom: 0px;">
				
					<div title="常用功能模块"  iconcls="icon-save" styple="overflow:auto;padding:10px;">
					
							<ul class="easyui-tree">
						
								<li>
									<span>
										<a target="mainFrame" href="welcome.action">常用功能</a>
									</span>
								</li>
							</ul>
			
					</div>
				
				
				
					<div title="首页内容管理"  iconcls="icon-save" styple="overflow:auto;padding:10px;">
						
						
						<ul class="easyui-tree">
					
							<li>
								<span>
									<a target="mainFrame" href="http://www.baidu.com">文字类资讯列表</a>
								</span>
							</li>
							
							<li>
								<span>
									<a target="mainFrame" href="http://www.baidu.com">图片类资讯列表</a>
								</span>
							</li>
						</ul>
				
					</div>
					
					<div title="去比赛管理"  iconcls="icon-save" styple="overflow:auto;padding:10px;">
					
							<ul class="easyui-tree">
						
								<li>
									<span>
										<a target="mainFrame" href="match/index.do">去比赛</a>
									</span>
								</li>
							</ul>
			
					</div>
					
					<div title="去活动管理"  iconcls="icon-save" styple="overflow:auto;padding:10px;">
					
							<ul class="easyui-tree">
						
								<li>
									<span>
										<a target="mainFrame" href="activity/index.do">去活动</a>
									</span>
								</li>
							</ul>
			
					</div>
					
					<div title="去学习管理"  iconcls="icon-save" styple="overflow:auto;padding:10px;">
					
							<ul class="easyui-tree">
						
								<li>
									<span>
										<a target="mainFrame" href="match/matchApplygoto.do?matchId=1&&username=aasd">去学习</a>
									</span>
								</li>
							</ul>
			
					</div>
					
					<div title="育儿说管理"  iconcls="icon-save" styple="overflow:auto;padding:10px;">
					
							<ul class="easyui-tree">
						
								<li>
									<span>
										<a target="mainFrame" href="activity/aa.do">育儿说</a>
									</span>
								</li>
							</ul>
			
					</div>
					
					<div title="限时优惠活动管理"  iconcls="icon-save" styple="overflow:auto;padding:10px;">
					
							<ul class="easyui-tree">
						
								<li>
									<span>
										<a target="mainFrame" href="http://www.baidu.com">限时优惠活动</a>
									</span>
								</li>
							</ul>
			
					</div>
					
					<div title="基础设置"  iconcls="icon-save" styple="overflow:auto;padding:10px;">
					
							<ul class="easyui-tree">
						
								<li>
									<span>
										<a target="mainFrame" href="http://www.baidu.com">FAQ</a>
									</span>
								</li>
								
								<li>
									<span>
										<a target="mainFrame" href="http://www.baidu.com">关于我们</a>
									</span>
								</li>
								
								<li>
									<span>
										<a target="mainFrame" href="http://www.baidu.com">会员支付宝账号显示</a>
									</span>
								</li>
								
								
								
								
								
							</ul>
			
					</div>
					
					<div title="会员管理"  iconcls="icon-save" styple="overflow:auto;padding:10px;">
					
							<ul class="easyui-tree">
						
								<li>
									<span>
										<a target="mainFrame" href="http://www.baidu.com">会员</a>
									</span>
								</li>
							</ul>
			
					</div>
					
			
		</div>

    </div>

    <div id="mainPanle" region="center" style="overflow: hidden;">
    
    		  <div id="tabs" class="easyui-tabs" fit="true" border="false">

	            	<div title="欢迎使用" style="padding: 100px; overflow: hidden;" id="home">
	
	                	<h1>欢迎使用大人来也后台管理系统</h1>
	
	           		 </div>

        	  </div>

     </div>



	
</body>
</html>
