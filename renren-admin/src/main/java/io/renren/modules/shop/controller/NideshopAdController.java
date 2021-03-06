package io.renren.modules.shop.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.utils.DateUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.shop.entity.NideshopAdEntity;
import io.renren.modules.shop.service.NideshopAdService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-24 11:28:42
 */
@RestController
@RequestMapping("shop/nideshopad")
public class NideshopAdController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NideshopAdService nideshopAdService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("shop:nideshopad:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = nideshopAdService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("shop:nideshopad:info")
    public R info(@PathVariable("id") Integer id){
        NideshopAdEntity nideshopAd = nideshopAdService.selectById(id);

        return R.ok().put("nideshopAd", nideshopAd);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shop:nideshopad:save")
    public R save(@RequestBody NideshopAdEntity nideshopAd){

        ValidatorUtils.validateEntity(nideshopAd, AddGroup.class);
        if(StringUtils.equals(nideshopAd.getLinkType(),"topic")){
            nideshopAd.setLink("/pages/topicDetail/topicDetail?id="+nideshopAd.getLinkId());
        }else{
            nideshopAd.setLink("/pages/goods/goods?id="+nideshopAd.getLinkId());
        }
        nideshopAd.setStartTime(Integer.valueOf(String.valueOf(DateUtils.stringToDate(nideshopAd.getAdStartTime(),"yyyy-MM-dd").getTime()).substring(0, 10)));
        nideshopAd.setEndTime(Integer.valueOf(String.valueOf(DateUtils.stringToDate(nideshopAd.getAdEndTime(),"yyyy-MM-dd").getTime()).substring(0, 10)));
        nideshopAd.setAdPositionId(1);
        nideshopAd.setMediaType(1);
        nideshopAdService.insert(nideshopAd);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shop:nideshopad:update")
    public R update(@RequestBody NideshopAdEntity nideshopAd){
        ValidatorUtils.validateEntity(nideshopAd);
        nideshopAdService.updateAllColumnById(nideshopAd);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shop:nideshopad:delete")
    public R delete(@RequestBody Integer[] ids){
        nideshopAdService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
