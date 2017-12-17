package com.taotao.manage.controller;

import com.taotao.manage.pojo.ContentCategory;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ContentCategoryService;
import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("content/category")
@Controller
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @GetMapping
    public ResponseEntity<List<ContentCategory>> queryListByCategoryId
            (@RequestParam(value = "id", defaultValue = "0") Long id) {
        try {
            ContentCategory contentCategory = new ContentCategory();
            contentCategory.setParentId(id);
            List<ContentCategory> contentCategoryList = contentCategoryService.queryListByWhere(contentCategory);
            if(null == contentCategoryList || contentCategoryList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return  ResponseEntity.ok(contentCategoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }




}
