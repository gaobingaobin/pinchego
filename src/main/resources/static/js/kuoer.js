/**
 * Created by Administrator on 2016-12-19.
 */
// JavaScript Document
function showalert(content,title,btn,url){
    var title = title ? title : '��ʾ';
    var btn = btn ? btn : 'ȷ��';
    $("#dialog-message p").html(content);
    $( "#dialog-message" ).dialog({
        modal: true,
        title: title,
        buttons: [{
            text:btn,
            click: function() {
                $( this ).dialog( "close" );
                if(url) location.href=url;
            }
        }]
    });
}

