package com.shenke.controller.admin;


import com.shenke.entity.BigDrawing;
import com.shenke.entity.Log;
import com.shenke.service.BigDrawingService;
import com.shenke.service.LogService;
import com.shenke.util.GetFileName;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图纸Controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/admin/bigdrawing")
public class BigDrawingAdminController {

    @Resource
    private BigDrawingService bigDrawingService;

    @Resource
    private LogService logService;
    /**
     * 查询所有图纸信息
     *
     * @param
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String, Object> list(BigDrawing bigDrawing) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<BigDrawing> list = bigDrawingService.list(bigDrawing);
        map.put("rows", list);
        logService.save(new Log(Log.SEARCH_ACTION, "查询所有图纸信息"));
        return map;
    }

    /**
     * 添加或修改大图纸信息
     */
    @RequestMapping("/save")
    public Map<String, Object> save(BigDrawing bigDrawing) {

        Map<String, Object> resultMap = new HashMap<>();

        if(bigDrawingService.findByWuLiaoId(bigDrawing.getWuliaoId()) == null){
            if (!bigDrawing.getDrawingURL().isEmpty()) {
                String fileName = bigDrawing.getDrawingURL().getOriginalFilename();
                String path = "D:/drawing/";
                File filepath = new File(path, fileName);
                //判断路径是否存在不存在则创建
                if (!filepath.getParentFile().exists()){
                    filepath.getParentFile().mkdirs();
                }
                //将文件上传到目标文件中
                try {
                    bigDrawing.getDrawingURL().transferTo(new File(path + File.separator + fileName));
                    bigDrawing.setUrl(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bigDrawingService.save(bigDrawing);
        }else {
            resultMap.put("error","此图纸已存在");
        }

        resultMap.put("success", true);
        logService.save(new Log(Log.UPDATE_ACTION, "添加或修改大图纸信息"));
        return resultMap;
    }

    /**
     * 删除图纸信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new HashMap<>();
        bigDrawingService.delete(id);
        map.put("success", true);
        logService.save(new Log(Log.DELETE_ACTION, "删除图纸信息"));
        return map;
    }


    /**
     * 下拉框模糊查询
     *
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/bigdrawingList")
    public List<BigDrawing> comboList(String q) {
        if (q == null) {
            q = "";
        }
        logService.save(new Log(Log.SEARCH_ACTION, "下拉框模糊查询图纸信息"));
        return bigDrawingService.conboList("%" + q + "%");
    }

    /***
     * 文件下载
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response,String fileName) throws Exception {
        System.out.println("下载");
        System.out.println(fileName);
        if (fileName != null) {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
            String realPath = "d:/drawing/";
            System.out.println(realPath);
            System.out.println(fileName);
            File file = new File(realPath, fileName);
            String filena = GetFileName.getFilename(request, fileName);
            if (file.exists()) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + filena);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    System.out.println("开始读取");
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    System.out.println("**********************");
                    System.out.println(i);
                    System.out.println("**********************");
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                        System.out.println("("+i+")");
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "下载失败";
                } finally {
                    if (bis != null && fis != null) {
                        try {
                            bis.close();
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    /**
     * 保存图纸展开的开始 结束时间
     * @param code
     * @param wuliaoId
     * @return
     */
    @RequestMapping("/jiShi")
    public Map<String,Object> jiShi(Integer code,String wuliaoId){
        Map<String,Object> map = new HashMap<>();

        BigDrawing bigDrawing = bigDrawingService.findByWuLiaoId(wuliaoId);

        if(code == 1){
            bigDrawing.setStartDate(new Date());
            bigDrawingService.save(bigDrawing);
        }else if(code == 2){
            if(bigDrawing.getStartDate() != null){
                bigDrawing.setStopDate(new Date());
                //计算实际展开工时
                Long between = bigDrawing.getStopDate().getTime() - bigDrawing.getStartDate().getTime();
                Double d= between.doubleValue();

                if(bigDrawing.getShiJiGongShi() != null){
                    bigDrawing.setShiJiGongShi(bigDrawing.getShiJiGongShi() + (d/60000));
                }else {
                    bigDrawing.setShiJiGongShi(d/60000);
                }
                bigDrawingService.save(bigDrawing);
            }
        }
        map.put("success",true);
        return map;
    }

    /**
     * 物料号模糊搜索
     * @param wuliaoId
     * @return
     */
    @RequestMapping("/search")
    public Map<String,Object> search(String wuliaoId){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",bigDrawingService.findLikeWuliaoId("%"+wuliaoId+"%"));
        map.put("success",true);
        return map;
    }

    /**
     * 修改展开工时
    * @return
     */
    @RequestMapping("/updateTime")
    public Map<String,Object> updateTime(Double time,Integer id){
        Map<String,Object> map = new HashMap<>();
        bigDrawingService.updateTime(time,id);
        map.put("success",true);
        return map;
    }



}
