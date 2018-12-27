package io.renren.modules.shop.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.shop.entity.NideshopGoodsAttributeEntity;
import io.renren.modules.shop.service.NideshopGoodsAttributeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-27 19:02:09
 */
@RestController
@RequestMapping("shop/nideshopgoodsattribute")
public class NideshopGoodsAttributeController {
    @Autowired
    private NideshopGoodsAttributeService nideshopGoodsAttributeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("shop:nideshopgoodsattribute:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = nideshopGoodsAttributeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("shop:nideshopgoodsattribute:info")
    public R info(@PathVariable("id") Integer id){
        NideshopGoodsAttributeEntity nideshopGoodsAttribute = nideshopGoodsAttributeService.selectById(id);

        return R.ok().put("nideshopGoodsAttribute", nideshopGoodsAttribute);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shop:nideshopgoodsattribute:save")
    public R save(@RequestBody NideshopGoodsAttributeEntity nideshopGoodsAttribute){

        ValidatorUtils.validateEntity(nideshopGoodsAttribute, AddGroup.class);
        nideshopGoodsAttributeService.insert(nideshopGoodsAttribute);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shop:nideshopgoodsattribute:update")
    public R update(@RequestBody NideshopGoodsAttributeEntity nideshopGoodsAttribute){
        ValidatorUtils.validateEntity(nideshopGoodsAttribute);
        nideshopGoodsAttributeService.updateAllColumnById(nideshopGoodsAttribute);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shop:nideshopgoodsattribute:delete")
    public R delete(@RequestBody Integer[] ids){
        nideshopGoodsAttributeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
