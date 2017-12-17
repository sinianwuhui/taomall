package com.taotao.manage.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.fabric.Response;
import com.taotao.common.vo.ItemCatData;
import com.taotao.common.vo.ItemCatResult;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("api/item/cat")
@Controller
public class ApiItemCatController {


    @Autowired
    private ItemCatService itemCatService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @ResponseBody
    @GetMapping
    public String queryItemCat(String callback){

        try {
            ItemCatResult itemCatResult = itemCatService.queryAllToTree();
            String string = objectMapper.writeValueAsString(itemCatResult);
            if(StringUtils.isEmpty(string)){
                return string;
            }else {
                return callback+"("+string+")";
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }



//    @GetMapping
//    public ResponseEntity<ItemCatResult> queryItemCat() {
//        try {
//            ItemCatResult itemCatResult = itemCatService.queryAllToTree();
//            return ResponseEntity.status(HttpStatus.OK).body(itemCatResult);
//        }catch (Exception e){
//
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//
//    }
}
