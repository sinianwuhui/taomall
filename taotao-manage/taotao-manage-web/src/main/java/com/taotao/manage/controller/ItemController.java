package com.taotao.manage.controller;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemDescService;
import com.taotao.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveItem(Item item,String desc){
        try {
            itemService.saveItem(item,desc);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e){
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
