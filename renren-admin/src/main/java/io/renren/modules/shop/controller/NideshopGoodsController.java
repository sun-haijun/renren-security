package io.renren.modules.shop.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.modules.shop.entity.NideshopBrandEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.shop.entity.NideshopGoodsEntity;
import io.renren.modules.shop.service.NideshopGoodsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-27 14:25:28
 */
@RestController
@RequestMapping("shop/nideshopgoods")
public class NideshopGoodsController {
    @Autowired
    private NideshopGoodsService nideshopGoodsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("shop:nideshopgoods:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = nideshopGoodsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("shop:nideshopgoods:info")
    public R info(@PathVariable("id") Integer id){
        NideshopGoodsEntity nideshopGoods = nideshopGoodsService.selectById(id);

        return R.ok().put("nideshopGoods", nideshopGoods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shop:nideshopgoods:save")
    public R save(@RequestBody NideshopGoodsEntity nideshopGoods){

        ValidatorUtils.validateEntity(nideshopGoods, AddGroup.class);
        nideshopGoodsService.insert(nideshopGoods);

        return R.ok();
    }

    /**
     * 选择部门(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("shop:nideshopgoods:list")
    public R select(){
        List<NideshopGoodsEntity> goodsList = nideshopGoodsService.selectList(null);
        return R.ok().put("goodsList", goodsList);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shop:nideshopgoods:update")
    public R update(@RequestBody NideshopGoodsEntity nideshopGoods){
        ValidatorUtils.validateEntity(nideshopGoods);
        nideshopGoodsService.updateAllColumnById(nideshopGoods);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shop:nideshopgoods:delete")
    public R delete(@RequestBody Integer[] ids){
        nideshopGoodsService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
