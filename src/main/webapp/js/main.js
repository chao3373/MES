//点击查看图纸
function chaKanTZ() {
    var src = "/tz/" + $("#dg").datagrid("getSelected").drawing.url;
    window.open(src);
}

//小图查看图纸
function chaKanXTZ() {
    var src = "/tz/" + $("#dg").datagrid("getSelected").url;

    //var aa = "/tz/" + $("#dg").datagrid("getSelected").wuliaoId + ".jpg";

    window.open(src);
}

//打开dlg按钮
function openDlg(dlg) {
    $(dlg).dialog("open");
}

//关闭dig
function closeDlg(dlg) {
    $(dlg).dialog("close");
}

//提交表单信息
function subimtFm(fm, url, dlg, dg) {
    $(fm).form('submit', {
        url: url,
        success: function (result) {
            if (result) {
                clertInput(fm);
                $(dg).datagrid("reload");
                $(dlg).dialog("close");
                alert(result);
                return;
            }
            alert("保存失败！");
            return;
        }
    });
}

//清空表单的数据
function clertInput(fm) {
    $(':input', fm)
        .not(':button, :submit, :reset, :hidden')
        .val('')
        .removeAttr('checked')
        .removeAttr('selected');
}

//将datagrid选中的行信息加载到form中,并打开所在的form所在的dlg
function dgToFm(dg, fm, dlg) {
    var rows = $(dg).datagrid("getSelections");
    if (rows.length != 1) {
        alert("请选择一条要操作的数据！");
        return;
    }
    $(fm).form("load", rows[0]);
    $(dlg).dialog("open");
}

//删除选中的信息
function deleteDg(dg, url) {
    var row = $(dg).datagrid("getSelected");
    if (row.length < 1) {
        alert("请选择要删除的数据！");
        return;
    }
    $.messager.confirm("系统提示", "<span style='color: red;'>确定要删除选中的信息吗！</span>", function (r) {
        if (r) {

            $.ajax({
                type: "POST",
                url: url,
                data: {id: row.id},
                success: function (result) {
                    if (result) {
                        $(dg).datagrid("reload");
                        alert("删除成功");
                        return;
                    }
                    alert("删除失败！");
                    return;
                }
            })
        }
    });

}

//根据条件刷新dg中的数据
function shuaXinDg(dg, url){
    $.ajax({
        type:"POST",
        url:url,
        success: function (result) {
            if (result){
                $(dg).datagrid("loadData", result);
            }
        }
    })
}

//获取from表单中的所有name和对应的值
function getFrom(from) {
    var from = $(from);
    var obj = {};
    var t = from.serializeArray();
    $.each(t, function () {
        obj[this.name] = this.value;
    });
    return obj;
}

