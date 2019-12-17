package com.shenke.util;

import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.awt.image.BufferedImage;
import java.io.*;

public class TiaoMaUtil {
    public static File generateFile(String msg, String path) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    /**
     * 生成字节
     *
     * @param msg
     * @return
     */


    public static byte[] generate(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous);
        return ous.toByteArray();
    }

    /**
     * 生成到流
     *
     * @param msg
     * @param ous
     */


    public static void generate(String msg, OutputStream ous) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return;
        }

        //创建128编码对象
        Code128Bean bean = new Code128Bean();


        //设置分辨率
        final int dpi = 300;
    /*    // module宽度
        final double moduleWidth = UnitConv.in2mm(1.2f / dpi);
        //设置两侧是否留白
        bean.doQuietZone(false);
        bean.setModuleWidth(moduleWidth);
        System.out.println("********************************");
        bean.getModuleWidth();
        bean.getBarHeight();

        System.out.println("********************************");*/



        String format = "image/png";
        try {

            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);

            // 生成条形码
            bean.generateBarcode(canvas, msg);

            // 结束绘制
            canvas.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
