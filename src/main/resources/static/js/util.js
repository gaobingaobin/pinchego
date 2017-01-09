/**
 * Created by gaobin on 2016/11/11.
 */
function checkTip(name,nameTxt,msg) {
    if (name == null||name=="") {
        $("#"+nameTxt).tips({
            side: 3,
            msg: msg,
            bg: '#AE81FF',
            time: 2
        });
        $("#"+nameTxt).focus();
        return false;
    }else {
        return true
    }
}
/** 返回相同name属性的值的数组 */
function getNamesValues(name, type) {
    var arr = [];
    var type1 = type || "input";
    $(type1 + "[name=" + name + "]").each(function (i) {
        arr.push($(this).val());
    });
    return arr;
}