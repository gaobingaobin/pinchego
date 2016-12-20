/**
 * Created by Administrator on 2016-12-19.
 */
/**
 * ThinkSNS����Js����
 * @author jason <yangjs17@yeah.net>
 * @version TS3.0
 */
var _core = function() {
    // ����ͨ�õļ���Դ����
    var obj = this;
    // �����ļ�����
    this._coreLoadFile = function() {
        var temp = new Array();
        var tempMethod = function(url, callback) {
            // �ڶ��ε��õ�ʱ��Ͳ�=0��
            var flag = 0;
            for(i in temp) {
                if(temp[i] == url) {
                    flag = 1;
                }
            }
            if(flag == 0) {
                // δ�����
                temp[temp.length] = url;
                // JQuery��ajax�����ļ���ʽ���������ʽ�ļ���ͬ���ڴ����������ʽ�ļ�
                $.getScript(url, function() {
                    if("undefined" != typeof(callback)) {
                        if("function" == typeof(callback)) {
                            callback();
                        } else {
                            eval(callback);
                        }
                    }
                });
            } else {
                if("undefined" != typeof(callback)) {
                    // ����setTimeout ����δ�������
                    setTimeout(callback, 200);
                }
            }
        };
        // �����ڲ������������ⲿ���ò����Ը���temp��ֵ
        return tempMethod;
    };
    // ����CSS�ļ�
    this._loadCss = function() {
        var temp = new Array();
        var tempMethod = function(url) {
            // �ڶ��ε��õ�ʱ��Ͳ�=0��
            var flag = 0;
            for(i in temp) {
                if(temp[i] == url) {
                    flag = 1;
                }
            }
            if(flag == 0) {
                // δ�����
                temp[temp.length] = url;
                var css = '<link href="'+THEME_URL+'/js/tbox/box.css" rel="stylesheet" type="text/css">';
                $('head').append(css);
            }
        };
        // �����ڲ�������,���ⲿ���ò����Ը���temp��ֵ
        return tempMethod;
    };
    /**
     * ʱ����Դ����
     * ���ñذ�ԭ��ֻ����һ��js�ļ�,�������ƹ��ܶ����Բ��մ˷���
     * ��Ҫ��ǰ����jquery.js�ļ�
     * @author yangjs
     */
    this._rcalendar = function(text, mode, refunc) {
        // ���ֵ
        var temp = 0;
        var tempMethod = function(t, m, r) {
            // �ڶ��ε��õ�ʱ��Ͳ�=0��
            if(temp == 0) {
                // JQuery��ajax�����ļ���ʽ���������ʽ�ļ���ͬ���ڴ����������ʽ�ļ�
                $.getScript(THEME_URL+'/js/rcalendar.js', function() {
                    rcalendar(t, m, r);
                });
            } else {
                rcalendar(t, m, r);
            }
            temp++;
        };
        // �����ڲ������������ⲿ���ò����Ը���temp��ֵ
        return tempMethod;
    };
    /**
     * ����IMG��html��
     */
    this._createImageHtml = function() {
        var _imgHtml = '';
        var _c = function() {
            if(_imgHtml == '') {
                $.post(U('public/Feed/getSmile'), {}, function(data) {
                    for(var k in data) {
                        _imgHtml += '<a href="javascript:void(0)" title="'+data[k].title+'" onclick="core.face.face_chose(this)";><img src="'+ THEME_URL +'/image/expression/'+data[k].type+'/'+ data[k].filename +'" width="24" height="24" /></a>';
                    }
                    _imgHtml += '<div class="c"></div>';
                    $('#emot_content').html(_imgHtml);
                }, 'json');
            } else {
                $('#emot_content').html(_imgHtml);
            }
        };
        return _c;
    };
}

// ���Ķ���
var core = new _core();

/**
 * ���ĵĲ���б�
 */

//΢�������ļ���֧�ֻص����� ���÷�ʽcore.loadFile(url,callback)
core.loadFile = core._coreLoadFile();
core.loadCss = core._loadCss();

/**
 * ���Ĳ���Զ����ɵĹ�������
 * �����õ���js�Ĺ���ģʽ�����ģʽ
 *
 * ʹ�÷������������д��plugins/�µĶ�Ӧ�ļ��£��ļ���������������ͬ������core.at.js
 * JS ���������Ҫ��һ��_init ���������ݴ�������������� init����
 *
 * �磺core.plugInit('searchUser',$(this))��
 * ����searchUser��ʾ�����������core.searchUser.js
 * $(this) Ϊ init�ĵ�һ������
 *
 * @author yangjs
 */
core.plugInit = function() {
    if(arguments.length > 0) {
        var arg = arguments;
        var back = function() {
            eval("var func = core." + arg[0] + ";");
            if("undefined" != typeof(func)) {
                func._init(arg);
            }
        };
        var file = THEME_URL + '/js/plugins/core.' + arguments[0] + '.js';
        core.loadFile(file, back);
    }
};
//�����淽������ ֻ���������Լ�д�ص�������������ִ��init��
core.plugFunc = function(plugName,callback){
    var file = THEME_URL+'/js/plugins/core.'+plugName+'.js';
    core.loadFile(file,callback);
};

/**
 * �Ż�setTimeout����
 * @param func
 * @param time
 */
core.setTimeout = function(func,time){
//	var type = typeof(func);
//	if("undefined" == type){
    setTimeout(func, time);
//	}else{
//		if("string" == type){
//			eval(func);
//		}else{
//			func();
//		}
//	}

};
// ��ȡ����༭���ڵĿ���������
core.getLeftNums = function(obj) {
    var str = obj.innerHTML;
    // �滻br��ǩ
    var imgNums = $(obj).find('img').size();
    // �ж��Ƿ�Ϊ��
    var _str = str.replace(new RegExp("<br>","gm"),"");
    _str = _str.replace(/[ ]|(&nbsp;)*/g, "");
    // �ж������Ƿ񳬹���һ���ո���һ����
    _str = str.replace(/<[^>]*>/g, "");		// ȥ������HTML��ǩ
    _str = trim(_str,' ');

    if(imgNums <1 ) {
        var emptyStr = _str.replace(/&nbsp;/g,"").replace(/\s+/g,"");
        if(emptyStr.length == 0) {
            return initNums;
        }
    }
    _str = _str.replace(/&nbsp;/g,"a"); 	// ���ڿɱ༭DIV�Ŀո���nbsp ������ô��

    return initNums - getLength(_str) - imgNums;
};
core.getLength = function(str, shortUrl) {
    str = str + '';
    if (true == shortUrl) {
        // һ��URL����ʮ���ֳ��ȼ���
        return Math.ceil(str.replace(/((news|telnet|nttp|file|http|ftp|https):\/\/){1}(([-A-Za-z0-9]+(\.[-A-Za-z0-9]+)*(\.[-A-Za-z]{2,5}))|([0-9]{1,3}(\.[0-9]{1,3}){3}))(:[0-9]*)?(\/[-A-Za-z0-9_\$\.\+\!\*\(\),;:@&=\?\/~\#\%]*)*/ig, 'xxxxxxxxxxxxxxxxxxxx')
                .replace(/^\s+|\s+$/ig,'').replace(/[^\x00-\xff]/ig,'xx').length/2);
    } else {
        return Math.ceil(str.replace(/^\s+|\s+$/ig,'').replace(/[^\x00-\xff]/ig,'xx').length/2);
    }
};
// һЩ�Զ���ķ���
// ���ɱ���ͼƬ
core.createImageHtml = core._createImageHtml();
//���ڿؼ�,���÷�ʽ core.rcalendar(this,'full')
//this Ҳ�����滻Ϊ����ID,full��ʾʱ����ʾģʽ,Ҳ���Բο�rcalendar.js�ڵ�����ģʽ
core.rcalendar = core._rcalendar();


//��ʱ�洢���� �����ڷָ�洢������

core.stringDb = function(obj,inputname,tags){
    this.inputname = inputname;
    this.obj  = obj;
    if(tags != ''){
        this.tags = tags.split(',');
    }else{
        this.tags = new Array();
    }
    this.taglist = $(obj).find('ul');
    this.inputhide = $(obj).find("input[name='"+inputname+"']");
};

core.stringDb.prototype = {
    init:function(){
        if(this.tags.length > 0){
            var html = '';
            for(var i in this.tags){
                if(this.tags[i] != ''){
                    html +='<li><label>'+this.tags[i]+'</label><em>X</em></li>';
                }
            }
            this.taglist.html(html);
            this.bindUl();
        }
    },
    bindUl:function(){
        var _this = this;
        this.taglist.find('li').click(function(){
            _this.remove($(this).find('label').html());
        });
    },
    add:function(tag){
        var _tag = tag.split(',');
        var _this = this;
        var add = function(t){
            for(var i in _this.tags){
                if(_this.tags[i] == t){
                    return false;
                }
            }
            var html = '<li><label>'+t+'</label><em>X</em></li>';
            _this.tags[_this.tags.length] = t;
            _this.taglist.append(html);
        };

        for(var ii in _tag){
            if(_tag[ii] != ''){
                add(_tag[ii]);
            }
        }

        this.inputhide.val(this.tags.join(','));
        this.bindUl();
    },
    remove:function(tag){
        var del = function(arr,n){
            if(n<0){
                return arr;
            }else{
                return arr.slice(0,n).concat(arr.slice(n+1,arr.length))
            }
        }

        for(var i in this.tags){
            if(this.tags[i] == tag){
                this.tags = del(this.tags,parseInt(i));
                this.taglist.find('li').eq(i).remove();
                this.inputhide.val(this.tags.join(','));
            }
        }
    }
};

/*** ����Js������ ***/
/**
 * ģ��TS��U��������ҪԤ�ȶ���JSȫ�ֱ���SITE_URL��APPNAME
 * @param string url ���ӵ�ַ
 * @param array params ���Ӳ���
 * @return string ��װ������ӵ�ַ
 */
var U = function(url, params) {
    var website = SITE_URL+'/index.php';
    url = url.split('/');
    if(url[0]=='' || url[0]=='@')
        url[0] = APPNAME;
    if (!url[1])
        url[1] = 'Index';
    if (!url[2])
        url[2] = 'index';
    website = website+'?app='+url[0]+'&mod='+url[1]+'&act='+url[2];
    if(params) {
        params = params.join('&');
        website = website + '&' + params;
    }
    return website;
};
/**
 * �������ȫվʹ�ã�ͳһ����ӿ�
 */
var ui = {
    /**
     * ������ʾ��Ϣ����ʾ��Ϣ��
     * @param string message ��Ϣ����
     * @param integer error �Ƿ��Ǵ�����ʽ��1��ʾ������ʽ��0��ʾ�ɹ���ʽ
     * @param integer lazytime ��ʾʱ��
     * @return void
     */
    showMessage: function(message, error, lazytime) {
        var style = (error=="1") ? "html_clew_box clew_error" : "html_clew_box";
        var ico = (error == "1") ? 'ico-error' : 'ico-ok';
        var html = '<div class="'+style+'" id="ui_messageBox" style="display:none">\
					<div class="html_clew_box_con" id="ui_messageContent">\
					<i class="'+ico+'"></i>'+message+'</div></div>';
        var _u = function() {
            for (var i = 0; i < arguments.length; i++) {
                if (typeof arguments[i] != 'undefined') return false;
            }
            return true;
        };
        // ��ʾ��ʾ����
        ui.showblackout();
        // ���������ص�body��
        $(html).appendTo(document.body);
        // ��ȡ�߿�
        var _h = $('#ui_messageBox').height();
        var _w = $('#ui_messageBox').width();
        // ��ȡ��λֵ
        var left = ($('body').width() - _w)/2 ;
        var top  = $(window).scrollTop() + ($(window).height()-_h)/2;
        // ��ӵ�����ʽ�붯��Ч�������֣�
        $('#ui_messageBox').css({
            left:left + "px",
            top:top + "px"
        }).fadeIn("slow",function(){
            $('#ui_messageBox').prepend('<iframe style="z-index:;position: absolute;visibility:inherit;width:'+_w+'px;height:'+_h+'px;top:0;left:0;filter=\'progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)\'"'+
                'src="about:blank"  border="0" frameborder="0"></iframe>');
        });
        // ��ӵ�������Ч������ʧ��
        setTimeout(function() {
            $('#ui_messageBox').find('iframe').remove();
            $('#ui_messageBox').fadeOut("fast", function() {
                ui.removeblackout();
                $('#ui_messageBox').remove();
            });
        } , lazytime*1000);
    },
    /**
     * ��ӵ���
     * @return void
     */
    showblackout: function() {
        if($('.boxy-modal-blackout').length > 0) {
            // TODO:???
        } else {
            var height = $('body').height() > $(window).height() ? $('body').height() : $(window).height();
            $('<div class ="boxy-modal-blackout" ><iframe style="z-index:-1;position: absolute;visibility:inherit;width:'+$('body').width()+'px;height:'+height+'px;top:0;left:0;filter=\'progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)\'"'+
                'src="about:blank"  border="0" frameborder="0"></iframe></div>')
                .css({
                    height:height+'px',width:$('body').width()+'px',zIndex: 999, opacity: 0.5
                }).appendTo(document.body);
        }
    },
    /**
     * �Ƴ�����
     * @return void
     */
    removeblackout: function() {
        if($('#tsbox').length > 0) {
            if(document.getElementById('tsbox').style.display == 'none'){
                $('.boxy-modal-blackout').remove();
            }
        } else {
            $('.boxy-modal-blackout').remove();
        }
    },
    /**
     *  �����ɹ���ʾAPI
     * @param string message ��Ϣ����
     * @param integer time չʾʱ��
     * @return void
     */
    success: function(message, time) {
        var t = "undefined" == typeof(time) ? 1 : time;
        ui.showMessage(message, 0, t);
    },
    /**
     * ����������ʾAPI
     * @param string message ��Ϣ����
     * @param integer time չʾʱ��
     * @return void
     */
    error: function(message, time) {
        var t = "undefined" == typeof(time) ? 2 : time;
        ui.showMessage(message, 1, t);
    },
    /**
     * ȷ�ϵ�����ʾAPI - ������
     * @example
     * ���Լ���callback���ص�����
     * @param object o ��λ����
     * @param string text ��ʾ����
     * @param string|function _callback �ص���������
     * @return void
     */
    confirm: function(o, text, _callback) {
        // �жϵ����Ƿ����
        document.getElementById('ts_ui_confirm') !== null && $('#ts_ui_confirm').remove();
        var callback = "undefined" == typeof(_callback) ? $(o).attr('callback') : _callback;
        text = text || L('PUBLIC_ACCONT_TIPES');
        text = "<i class='ico-error'></i>"+text;
        this.html = '<div id="ts_ui_confirm" class="ts_confirm"><div class="layer-mini-info"><dl><dt class="txt"> </dt><dd class="action"><a class="btn-green-small mr10 btn_ok" href="javascript:void(0)"><span>'+L('PUBLIC_QUEDING')+'</span></a><a class="btn-cancel" href="javascript:void(0)"><span>'+L('PUBLIC_QUXIAO')+'</span></a></dd></dl></div></div>';
        $('body').append(this.html);
        var position = $(o).offset();
        $('#ts_ui_confirm').css({"top":position.top+"px","left":position.left+"px","display":"none"});
        $("#ts_ui_confirm .txt").html(text);
        $('#ts_ui_confirm').fadeIn("fast");
        $("#ts_ui_confirm .btn-cancel").one('click',function(){
            $('#ts_ui_confirm').fadeOut("fast");
            // �޸�ԭ��: ts_ui_confirm .btn_b��ť���ظ��ύ
            $('#ts_ui_confirm').remove();
            return false;
        });
        $("#ts_ui_confirm .btn_ok").one('click',function(){
            $('#ts_ui_confirm').fadeOut("fast");
            // �޸�ԭ��: ts_ui_confirm .btn_b��ť���ظ��ύ
            $('#ts_ui_confirm').remove();
            if("undefined" == typeof(callback)){
                return true;
            }else{
                if("function"==typeof(callback)){
                    callback();
                }else{
                    eval(callback);
                }
            }
        });
        $('body').bind('keyup', function(event) {
            $("#ts_ui_confirm .btn_ok").click();
        });
        return false;
    },
    /**
     * ȷ�Ͽ���ʾAPI - ������
     * @param string title ��������
     * @param string text ��ʾ����
     * @param string|function _callback �ص���������
     * @return void
     */
    confirmBox: function(title, text, _callback) {
        this.box.init(title);
        text = text || L('PUBLIC_ACCONT_TIPES');
        text = "<i class='ico-error'></i>"+text;

        var content = '<div class="pop-create-group"><dl><dt class="txt">'+ text + '</dt><dd class="action"><a class="btn-green-small mr10" href="javascript:void(0)"><span>'+L('PUBLIC_QUEDING')+'</span></a><a class="btn-cancel" href="javascript:void(0)"><span>'+L('PUBLIC_QUXIAO')+'</span></a></dd></dl></div>';

        this.box.setcontent(content);
        this.box.center();

        var callback = "undefined" == typeof(_callback) ? $(o).attr('callback') : _callback;

        var _this = this;
        $("#tsbox .btn-cancel").one('click',function(){
            $('#tsbox').fadeOut("fast",function(){
                $('#tsbox').remove();
            });
            _this.box.close();
            return false;
        });
        $("#tsbox .btn-green-small").one('click',function(){
            $('#tsbox').fadeOut("fast",function(){
                $('#tsbox').remove();
            });
            _this.box.close();
            if("undefined" == typeof(callback)){
                return true;
            }else{
                if("function"==typeof(callback)){
                    callback();
                }else{
                    eval(callback);
                }
            }
        });
        return false;
    },
    /**
     * ˽�ŵ���API
     * @param string touid �ռ���ID
     * @return void
     */
    sendmessage: function(touid, editable) {
        if(typeof(editable) == "undefined" ) {
            editable = 1;
        }
        touid = touid || '';
        this.box.load(U('public/Message/post')+'&touid='+touid+'&editable='+editable, L('PUBLIC_SETPRIVATE_MAIL'));
    },
    /**
     * @Me����API
     * @param string touid @��ID
     * @return void
     */
    sendat: function(touid) {
        touid = touid || '';
        this.box.load(U('public/Mention/at')+'&touid='+touid, '@TA');
    },
    /**
     * ��������΢��
     * @param string title ��������
     * @param string initHTML ��������
     * @return void
     */
    sendbox: function(title, initHtml,channelID) {
        if($.browser.msie) {
            initHtml = encodeURI(initHtml);
        }
        initHtml = initHtml.replace(/\#/g, "%23");
        this.box.load(U('public/Index/sendFeedBox')+'&initHtml='+initHtml+'&channelID='+channelID, title,function(){
            $('#at-view').hide();
        });
    },
    /**
     * �ظ�����API
     * @param integer comment_id ����ID
     * @return void
     */
    reply: function(comment_id) {
        this.box.load(U('public/Comment/reply')+'&comment_id='+comment_id,L('PUBLIC_RESAVE'),function (){$('#at-view').hide();});
    },
    groupreply: function(comment_id,gid) {
        this.box.load(U('group/Group/reply')+'&gid='+gid+'&comment_id='+comment_id,L('PUBLIC_RESAVE'),function (){$('#at-view').hide();});
    },
    /**
     * ѡ���ŵ���API - �ݲ�ʹ��
     */
    changeDepartment: function(hid,showname,sid,nosid,notop) {
        this.box.load(U('widget/Department/change')+'&hid='+hid+'&showName='+showname+'&sid='+sid+'&nosid='+nosid+'&notop='+notop,L('PUBLIC_DEPATEMENT_SELECT'));
    },
    /**
     * �Զ�����API�ӿ�
     */
    box: {
        WRAPPER: '<div class="wrap-layer" id="tsbox" style="display:none">\
     			  <div class="content-layer">\
     			  <div class="layer-content" id="layer-content"></div>\
     			  </div></div>',
        inited: false,
        IE6: (jQuery.browser.msie && jQuery.browser.version < 7),
        init: function(title, callback) {
            this.callback = callback;
            // ����������С��Ƭ
            if("undefined" != typeof(core.facecard)){
                core.facecard.dohide();
            }

            if($('#tsbox').length >0) {
                return false;
            } else {
                $('body').prepend(this.WRAPPER);
            }
            var url = THEME_URL+'/js/tbox/box.css';
            core.loadCss(url);
            // ���ͷ��
            if("undefined" != typeof(title)) {
                $("<div class='hd'>"+title+"<a class='ico-close' href='#'></a></div>").insertBefore($('#tsbox .layer-content'));
            }

            ui.showblackout();

            $('#tsbox').stop().css({width: '', height: ''});
            // ��Ӽ����¼�
            jQuery(document.body).bind('keypress.tsbox', function(event) {
                var key = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
                if (key == 27) {
                    jQuery(document.body).unbind('keypress.tsbox');
                    ui.box.close(callback);
                    return false;
                }
            });
            // �رյ������ص�����
            $('#tsbox').find('.ico-close').click(function() {
                ui.box.close(callback);
                return false;
            });

            this.center();
            var show = function(){
                $('#tsbox').show();
            }
            setTimeout(show, 200);

            $('#tsbox').draggable({ handle: '.hd' });

            $('.hd').mousedown(function(){
                $('.mod-at-wrap').remove();
            });
        },
        /**
         * ���õ����е�����
         * @param string content ������Ϣ
         * @return void
         */
        setcontent: function(content) {
            $('#layer-content').html(content);
        },
        /**
         * �رմ���
         * @param function fn �ص���������
         * @return void
         */
        close: function(fn) {
            $('#ui-fs .ui-fs-all .ui-fs-allinner div.list').find("a").die("click");
            $('.talkPop').remove();
            $('#tsbox').remove();
            $('.mod-at-wrap').remove();
            jQuery('.boxy-modal-blackout').remove();
            var back ='';
            if("undefined" != typeof(fn)){
                back = fn;
            }else if("undefined" != typeof(this.callback)){
                back = this.callback;
            }
            if("function" == typeof(back)){
                back();
            }else{
                eval(back);
            }
        },
        /**
         * ��ʾ����
         * @param string data ��Ϣ����
         * @param string title ������Ϣ
         * @param function callback �ص�����
         * @return void
         */
        alert:function(data,title,callback){
            this.init(title,callback);
            this.setcontent('<div class="question">'+data+'</div>');
            this.center();
        },
        /**
         * ��ʾ����
         * @param string content ��Ϣ����
         * @param string title ������Ϣ
         * @param function callback �ص�����
         * @return void
         */
        show:function(content,title,callback){
            this.init(title,callback);
            this.setcontent(content);
            this.center();
        },
        /**
         * ���뵯��API
         * @param string requestUrl �����ַ
         * @param string title ��������
         * @param string callback ���ڹرպ�Ļص��¼�
         * @param object requestData requestData
         * @param string type Ajax����Э�飬Ĭ��ΪGET
         * @return void
         */
        load:function(requestUrl,title,callback,requestData,type) {
            this.init(title,callback);
            if("undefined" != typeof(type)) {
                var ajaxType = type;
            }else{
                var ajaxType = "GET";
            }
            this.setcontent('<div style="width:150px;height:70px;text-align:center"><div class="load">&nbsp;</div></div>');
            var obj = this;
            if("undefined" == requestData) {
                var requestData = {};
            }
            jQuery.ajax({url:requestUrl,
                type:ajaxType,
                data:requestData,
                cache:false,
                dataType:'html',
                success:function(html){
                    obj.setcontent(html);
                    obj.center();
                }
            });
        },
        /**
         * ������λ
         * @return void
         */
        _viewport: function() {
            var d = document.documentElement, b = document.body, w = window;
            return jQuery.extend(
                jQuery.browser.msie ? { left: b.scrollLeft || d.scrollLeft, top: b.scrollTop || d.scrollTop } : { left: w.pageXOffset, top: w.pageYOffset },
                !ui.box._u(w.innerWidth) ? { width: w.innerWidth, height: w.innerHeight } : (!ui.box._u(d) && !ui.box._u(d.clientWidth) && d.clientWidth != 0 ? { width: d.clientWidth, height: d.clientHeight } : { width: b.clientWidth, height: b.clientHeight }) );
        },
        /**
         * ��֤����
         * @return void
         */
        _u: function() {
            for(var i = 0; i < arguments.length; i++) {
                if(typeof arguments[i] != 'undefined') return false;
            }
            return true;
        },
        /**
         * ��ʽ����
         * @return void
         */
        _cssForOverlay: function() {
            if (ui.box.IE6) {
                return ui.box._viewport();
            } else {
                return {width: '100%', height: jQuery(document).height()};
            }
        },
        /**
         * �м䶨λ
         * @param string axis ��������
         * @return void
         */
        center: function(axis) {
            var v = ui.box._viewport();
            var o =  [v.left, v.top];
            if (!axis || axis == 'x') this.centerAt(o[0] + v.width / 2 , null);
            if (!axis || axis == 'y') this.centerAt(null, o[1] + v.height / 2);
            return this;
        },
        /**
         * �����ƶ�
         * @param integer x ��ֵ
         * @return void
         */
        moveToX: function(x) {
            if (typeof x == 'number') $('#tsbox').css({left: x});
            else this.centerX();
            return this;
        },
        /**
         * �����ƶ�
         * @param integer y ��ֵ
         * @return void
         */
        moveToY: function(y) {
            if (typeof y == 'number') $('#tsbox').css({top: y});
            else this.centerY();
            return this;
        },
        centerAt: function(x, y) {
            var s = this.getSize();
            if (typeof x == 'number') this.moveToX(x - s[0]/2 );
            if (typeof y == 'number') this.moveToY(y - s[1]/2 );
            return this;
        },
        centerAtX: function(x) {
            return this.centerAt(x, null);
        },
        centerAtY: function(y) {
            return this.centerAt(null, y);
        },
        getSize: function() {
            return [$('#tsbox').width(), $('#tsbox').height()];
        },
        getContent: function() {
            return $('#tsbox').find('.boxy-content');
        },
        getPosition: function() {
            var b = $('#tsbox');
            return [b.offsetLeft, b.offsetTop];
        },
        getContentSize: function() {
            var c = this.getContent();
            return [c.width(), c.height()];
        },
        _getBoundsForResize: function(width, height) {
            var csize = this.getContentSize();
            var delta = [width - csize[0], height - csize[1]];
            var p = this.getPosition();
            return [Math.max(p[0] - delta[0] / 2, 0), Math.max(p[1] - delta[1] / 2, 0), width, height];
        }
    }
};