package com.taotao.manage.service;

import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends BaseService<Item>{
    @Autowired
    private ItemDescService itemDescService;

    public void saveItem(Item item,String desc) {
        item.setStatus(1);
        item.setId(null);
        save(item);
        ItemDesc itemDesc =new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDescService.save(itemDesc);
    }

    public void updateItem(Item item, String desc) {
        Item itemQuery = queryById(item.getId());
        if(null == itemQuery){
            return;
        }
        item.setStatus(null);
        item.setCreated(null);
        updateSelective(item);
        ItemDesc itemDesc =new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDescService.updateSelective(itemDesc);


    }
}
