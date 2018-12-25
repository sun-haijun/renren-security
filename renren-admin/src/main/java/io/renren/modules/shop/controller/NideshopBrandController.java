package io.renren.modules.shop.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.shop.entity.NideshopBrandEntity;
import io.renren.modules.shop.service.NideshopBrandService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-25 17:19:14
 */
@RestController
@RequestMapping("shop/nideshopbrand")
public class NideshopBrandController {
    @Autowired
    private NideshopBrandService nideshopBrandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("shop:nideshopbrand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = nideshopBrandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("shop:nideshopbrand:info")
    public R info(@PathVariable("id") Integer id){
        NideshopBrandEntity nideshopBrand = nideshopBrandService.selectById(id);

        return R.ok().put("nideshopBrand", nideshopBrand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shop:nideshopbrand:save")
    public R save(@RequestBody NideshopBrandEntity nideshopBrand){
        nideshopBrandService.insert(nideshopBrand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shop:nideshopbrand:update")
    public R update(@RequestBody NideshopBrandEntity nideshopBrand){
        ValidatorUtils.validateEntity(nideshopBrand);
        nideshopBrandService.updateAllColumnById(nideshopBrand);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shop:nideshopbrand:delete")
    public R delete(@RequestBody Integer[] ids){
        nideshopBrandService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
