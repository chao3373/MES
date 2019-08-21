package com.shenke.controller.admin;

import com.shenke.entity.Storage;
import com.shenke.service.BigDrawingService;
import com.shenke.service.DrawingService;
import com.shenke.service.SaleListService;
import com.shenke.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 仓库Controller
 *
 */
@RestController
@RequestMapping("/admin/storage")
public class StorageAdminController {

    @Resource
    private SaleListService saleListService;

    @Resource
    private BigDrawingService bigDrawingService;

    @Resource
    private DrawingService drawingService;

    @Resource
    private StorageService storageService;

    /**
     * 新增
     * @param saleListId
     * @param bigDrawingId
     * @param drawingId
     * @param state
     * @param standard
     * @param remark
     * @return
     */
    @RequestMapping("/save")
    public Map<String,Object> save(Integer saleListId,String bigDrawingId,Integer drawingId,String state,String standard,String remark){
        Map<String,Object> map = new HashMap<>();
        Storage storage = new Storage();
        storage.setSaleList(saleListService.findById(saleListId));
        storage.setBigDrawing(bigDrawingService.findBigDrawingId(bigDrawingId));
        storage.setDrawing(drawingService.findById(drawingId));
        storage.setState(state);
        storage.setStandard(standard);
        storage.setRemark(remark);

        storageService.save(storage);
        saleListService.setState(saleListId,standard+"入库");

        Integer id = storageService.selectByMaxId().getId();
        map.put("success",true);
        map.put("id",id);
        return map;
    }

    /**
     * 根据ID查找对象
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Map<String,Object> findById(Integer id){
        Map<String,Object> map = new HashMap<>();
        map.put("data",storageService.findById(id));
        return map;
    }
}
