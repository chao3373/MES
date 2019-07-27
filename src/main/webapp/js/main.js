//点击查看图纸
function chaKanTZ() {
    var src = "/tz/" + $("#dg").datagrid("getSelected").drawing.url;
    var tuzhi = "<embed src='" + src + "' width='100%' height='100%'>";
    myWindow = window.open('', '', 'width=$(window).width(),height=$(window).height()')
    myWindow.document.write(tuzhi);
}

//小图查看图纸
function chaKanXTZ() {
    var src = "/tz/" + $("#dg").datagrid("getSelected").url;
    var tuzhi = "<embed src='" + src + "' width='100%' height='100%'>";
    myWindow = window.open('', '', 'width=$(window).width(),height=$(window).height()')
    myWindow.document.write(tuzhi);
}