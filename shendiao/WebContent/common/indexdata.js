
$(function ()
{	

$('#framecenter').tabs();
			$('#framecenter').tabs('add', {
				title : '主页',
				content : '<iframe frameborder="0" name="home" id="home" src="page/welcome.html" height="99%" width="100%"></iframe>',
				closable : false,
				iconCls : 'home'
			});
	$("#accordion1").accordion( {
		animate : false
	});
	//menu
	//请求后台菜单数据集合中的一条数据

	//var menu=$.myAjax.getDatas(this,"navigationmenu.do?method=json",false);


	addNavs([
	        /* {"code":"01","name":"基本功能","icon":"video32",
					"menu":[{"id":2,"icon":"videoGroup32min","code":"0101","name":"test","url":"http://www.baidu.com"},
					        {"id":3,"icon":"videoGroup32min","code":"0102","name":"test2"}
						]},*/
					
				/*{"code":"02","name":"启动界面管理","icon":"video32",
						  "menu":[{"id":2,"icon":"videoGroup32min","code":"0101","name":"启动界面管理","url":"http://www.baidu.com"}
			    		]}, */
		        
		        {"code":"03","name":"身份审核管理","icon":"video32",
					  "menu":[{"id":2,"icon":"videoGroup32min","code":"0101","name":"身份审核","url":"user/checkIndex.do"}
					  	]}, 
		    		        
		       {"code":"04","name":"案件审核管理","icon":"video32",
		    		   "menu":[{"id":2,"icon":"videoGroup32min","code":"0101","name":"案件审核","url":"task/caseCheckIndex.do"}
		    		   	]},
		    		   	
		    	{"code":"05","name":"商家管理","icon":"video32",
					    "menu":[{"id":2,"icon":"videoGroup32min","code":"0101","name":"添加商户","url":"merchant/merchantIndex.do"}
					    	]},	   	
		    		   	
		    	/*{"code":"06","name":"发布任务管理","icon":"video32",
				    	"menu":[{"id":2,"icon":"videoGroup32min","code":"0101","name":"发布任务","url":"http://www.baidu.com"}
				    	]},*/
					    	
					    	
					    	
					    	
		    		   	
		    		   	
		    		   	/*  
    		    {"code":"05","name":"去学习管理","icon":"video32",
    		    	    "menu":[{"id":2,"icon":"videoGroup32min","code":"0101","name":"课程管理","url":"http://www.baidu.com"},
    		    	            {"id":3,"icon":"videoGroup32min","code":"0102","name":"机构管理","url":"http://www.baidu.com"}
    		    	    ]},
    		    	    
    		    {"code":"06","name":"育儿说管理","icon":"video32",
        		    	    "menu":[{"id":2,"icon":"videoGroup32min","code":"0101","name":"育儿有方","url":"http://www.baidu.com"},
        		    	            {"id":3,"icon":"videoGroup32min","code":"0102","name":"育儿交流","url":"http://www.baidu.com"}
        		    	    ]},
        		{"code":"07","name":"BABY书屋管理","icon":"video32",
        			          "menu":[{"id":2,"icon":"videoGroup32min","code":"0101","name":"BABY书屋管理","url":"http://www.baidu.com"}
        			        ]},
        		    	    
        	    
          			    		   	{"code":"08","name":"用户管理","icon":"video32",
          			    		   		"menu":[{"id":2,"icon":"videoGroup32min","code":"0101","name":"用户管理","url":"http://www.baidu.com"}
          			    		   		]},
          	     
          	  
                    			    {"code":"09","name":"推荐管理","icon":"video32",
                    			    	"menu":[{"id":2,"icon":"videoGroup32min","code":"0101","name":"推荐管理","url":"http://www.baidu.com"}
                    			    	]},*/
                    			    
               /*  {"code":"10","name":"展示框管理","icon":"video32",
                    	        "menu":[{"id":2,"icon":"videoGroup32min","code":"0101","name":"热门赛事","url":"http://www.baidu.com"},
                    	        		{"id":3,"icon":"videoGroup32min","code":"0102","name":"精彩活动","url":"http://www.baidu.com"},
                    	        		{"id":4,"icon":"videoGroup32min","code":"0102","name":"精选课程","url":"http://www.baidu.com"}
                    	        	]},
                    	        		    	    
                 {"code":"09","name":"BANNER管理","icon":"video32",
                    	         "menu":[{"id":2,"icon":"videoGroup32min","code":"0101","name":"BANNER管理","url":"http://www.baidu.com"}
                    	               ]},*/	    
    		    	    
	
	]);

	InitLeftMenu();
	
});


function addNavs(data) {

	$.each(data, function(i, sm) {
		var menulist = "";
		//菜单部分
	
		if(typeof(sm.menu) != "undefined" && sm.menu.length != 0)
		{
			menulist += '<div class="easyui-panel" fit="true" border="false"><ul>';
			$.each(sm.menu, function(j, o) {
				menulist += '<li><div align="center"><a ref="' + o.code + '" icon="' + o.icon + '" name="' + o.name + '" href="#" rel="'+ o.url + '" ><span class="' + o.icon+ '" >&nbsp;</span><span>' + o.name+ '</span></a></div></li> ';
			});
			
		}
		//树部分
		if(typeof(sm.tree) != "undefined" && sm.tree.length != 0)
		{
			menulist += '<div class="easyui-panel" fit="true" border="false"><ul id="doorTree" >';

			
		}
		//console.info(menulist);
		menulist += '</ul></div>';
		$('#accordion1').accordion('add', {
			title : sm.name,
			content : menulist,
			iconCls :  sm.icon
		});
		
	});

	var pp = $('#accordion1').accordion('panels');
	var t = pp[0].panel('options').title;
	$('#accordion1').accordion('select', t);
	$("#accordion1").accordion( {
		animate : true
	});
}

//增加
function addNav(data,name,icon) {

        var menulist = "";
        menulist += '<ul>';
        $.each(data, function(j, o) {
            menulist += '<li><div ><a ref="' + o.code + '" icon="' + o.icon + '" name="' + o.name + '" href="#" rel="'
                    + o.url + '" ><span class="' + o.icon
					+ '" >&nbsp;</span><span>' + o.name
                    + '</span></a></div></li> ';
        });
        menulist += '</ul>';
        $('#accordion1').accordion('add', {
            title : name,
            content : menulist,
            iconCls : icon
        });

}

//树
function addNavTree(data) {

     

}
//初始化左侧
function InitLeftMenu() {
	$('#accordion1').delegate('a','click',function(){
			
		var tabTitle = $(this).attr("name");

		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = $(this).attr("icon")+"min";
		
		addTab(tabTitle, url,icon);
		$('#accordion1 li div').removeClass("selected");
		$(this).parent().addClass("selected");
	});
	//$('#accordion1 li a').live('click',function(){
		
	//});
			
	hoverMenuItem();
}

/**
 * 菜单项鼠标Hover
 */
function hoverMenuItem() {
	$(".easyui-accordion").find('a').hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});
}

function addTab(subtitle, url, icon) {
	
	if (!$('#framecenter').tabs('exists', subtitle)) {
		/**
		var tab = $('#framecenter').tabs('getSelected');
		if (tab){
			var index = $('#framecenter').tabs('getTabIndex', tab);
			if(index==1){
				$('#framecenter').tabs('close', index);
			}
		}
		*/
		$('#framecenter').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			icon : icon
		});
	} else {
		$('#framecenter').tabs('select', subtitle);
		
	}
	
}


function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:99.5%;"></iframe>';
	return s;
}