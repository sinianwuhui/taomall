package com.taotao.manage.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.common.vo.EasyUIResult;
import com.taotao.manage.pojo.ItemParam;
import com.taotao.manage.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("item/param")
@Controller
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping(value="{itemCatId}",method = RequestMethod.GET)
    public ResponseEntity<ItemParam> queryByItemCatID(@PathVariable("itemCatId") Long itemCatId ){
        try {
            ItemParam itemParam=new ItemParam();
            itemParam.setItemCatId(itemCatId);
            ItemParam queryOne = itemParamService.queryOne(itemParam);
            if(null == queryOne){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(queryOne);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @RequestMapping(value="{itemCatId}",method = RequestMethod.POST)
    public ResponseEntity<Void> saveItemParam(@PathVariable("itemCatId") Long itemCatId,ItemParam itemParam){
        try {
            itemParam.setItemCatId(itemCatId);
            itemParamService.save(itemParam);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping(value="list",method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryItemParamList(Integer page,Integer rows){
        try {
            PageInfo<ItemParam> pageInfo = itemParamService.queryPageListByWhere(page, rows, null);
            EasyUIResult easyUIResult=new EasyUIResult();
            easyUIResult.setRows(pageInfo.getList());
            easyUIResult.setTotal((int) pageInfo.getTotal());
            return ResponseEntity.status(HttpStatus.OK).body(easyUIResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
