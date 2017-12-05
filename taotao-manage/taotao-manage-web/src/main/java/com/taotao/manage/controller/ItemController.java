package com.taotao.manage.controller;


import com.github.pagehelper.PageInfo;
import com.taotao.common.vo.EasyUIResult;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("item")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    /**
     * 新增商品
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveItem(Item item,String desc,String itemParams){
        try {
            if(logger.isDebugEnabled()) {
                logger.debug("******新增商品{}开始！****", item.getTitle());
            }
            itemService.saveItem(item,desc,itemParams);

            if(logger.isDebugEnabled()) {
            logger.debug("******新增商品成功！****** " );
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e){
            logger.error("新增商品{}出错啦！原因是{}",item.getTitle(),e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



    /**
     * 查询商品列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryItemList(Integer page ,Integer rows ){
        try{
            PageInfo<Item> itemPageInfo = itemService.queryPageListByWhere(page, rows, null);
            EasyUIResult easyUIResult = new EasyUIResult();
            easyUIResult.setTotal((int) itemPageInfo.getTotal());
            easyUIResult.setRows(itemPageInfo.getList());
            return ResponseEntity.status(HttpStatus.OK).body(easyUIResult);
        }catch (Exception e){

        }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);


    }

    /**
     * 修改商品
     * @param item
     * @param desc
     * @return
     */
   @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateItem(Item item, String desc){
       try {
           if(logger.isDebugEnabled()) {
               logger.debug("******修改商品{}开始！****", item.getTitle());
           }
           itemService.updateItem(item,desc);

           if(logger.isDebugEnabled()) {
               logger.debug("******修改商品成功！****** " );
           }
           return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       } catch (Exception e){
           logger.error("修改商品{}出错啦！原因是{}",item.getTitle(),e);
       }
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
   }






}

