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

import io.renren.modules.shop.entity.NideshopGoodsGalleryEntity;
import io.renren.modules.shop.service.NideshopGoodsGalleryService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-28 11:23:43
 */
@RestController
@RequestMapping("shop/nideshopgoodsgallery")
public class NideshopGoodsGalleryController {
    @Autowired
    private NideshopGoodsGalleryService nideshopGoodsGalleryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("shop:nideshopgoodsgallery:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = nideshopGoodsGalleryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("shop:nideshopgoodsgallery:info")
    public R info(@PathVariable("id") Integer id){
        NideshopGoodsGalleryEntity nideshopGoodsGallery = nideshopGoodsGalleryService.selectById(id);

        return R.ok().put("nideshopGoodsGallery", nideshopGoodsGallery);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shop:nideshopgoodsgallery:save")
    public R save(@RequestBody NideshopGoodsGalleryEntity nideshopGoodsGallery){
        ValidatorUtils.validateEntity(nideshopGoodsGallery, AddGroup.class);
        nideshopGoodsGalleryService.insert(nideshopGoodsGallery);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shop:nideshopgoodsgallery:update")
    public R update(@RequestBody NideshopGoodsGalleryEntity nideshopGoodsGallery){
        ValidatorUtils.validateEntity(nideshopGoodsGallery);
        nideshopGoodsGalleryService.updateAllColumnById(nideshopGoodsGallery);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shop:nideshopgoodsgallery:delete")
    public R delete(@RequestBody Integer[] ids){
        nideshopGoodsGalleryService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
