package io.renren.modules.shop.controller;

import java.util.Arrays;
import java.util.List;
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

import io.renren.modules.shop.entity.NideshopAttributeEntity;
import io.renren.modules.shop.service.NideshopAttributeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-27 14:04:06
 */
@RestController
@RequestMapping("shop/nideshopattribute")
public class NideshopAttributeController {
    @Autowired
    private NideshopAttributeService nideshopAttributeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("shop:nideshopattribute:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = nideshopAttributeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 选择部门(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("shop:nideshopattribute:list")
    public R select(){
        List<NideshopAttributeEntity> attrList = nideshopAttributeService.selectList(null);
        return R.ok().put("attrList", attrList);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("shop:nideshopattribute:info")
    public R info(@PathVariable("id") Integer id){
        NideshopAttributeEntity nideshopAttribute = nideshopAttributeService.selectById(id);

        return R.ok().put("nideshopAttribute", nideshopAttribute);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shop:nideshopattribute:save")
    public R save(@RequestBody NideshopAttributeEntity nideshopAttribute){

        ValidatorUtils.validateEntity(nideshopAttribute, AddGroup.class);
        nideshopAttributeService.insert(nideshopAttribute);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shop:nideshopattribute:update")
    public R update(@RequestBody NideshopAttributeEntity nideshopAttribute){
        ValidatorUtils.validateEntity(nideshopAttribute);
        nideshopAttributeService.updateAllColumnById(nideshopAttribute);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shop:nideshopattribute:delete")
    public R delete(@RequestBody Integer[] ids){
        nideshopAttributeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
