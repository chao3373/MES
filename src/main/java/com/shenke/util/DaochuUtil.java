package com.shenke.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2019/5/27 16:49
 * @Description:
 */
public class DaochuUtil {
    //导出
    public static void daochuExcel(String str, String title) throws Exception {
        System.out.println("******************");
        System.out.println(str);
        System.out.println(str.getClass());
        System.out.println(title);
        System.out.println("******************");
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray asJsonArray = jsonParser.parse(str).getAsJsonArray();
        ArrayList<Map<String, String>> mapArrayList = CollUtil.newArrayList();
        Map map1 = gson.fromJson(asJsonArray.get(0), Map.class);
        int size = 0;
        for (JsonElement user : asJsonArray) {
            //使用GSON，直接转成Bean对象
            Map<String, String> map = gson.fromJson(user, Map.class);
            mapArrayList.add(map);
            size = map.size();
        }

        System.out.println(mapArrayList);


//        FileOutputStream fileOut=new FileOutputStream("d:\\工作簿.xls");
        // 通过工具类创建writer
        long name = new Date().getTime();
        String path = "D:\\下载文件\\EXCEL\\" + name + ".xls";
        System.out.println(path);
        System.out.println(name);
        ExcelWriter writer = ExcelUtil.getWriter(path);
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(size - 1, title);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(mapArrayList, true);
        // 关闭writer，释放内存
        writer.close();

    }
}
