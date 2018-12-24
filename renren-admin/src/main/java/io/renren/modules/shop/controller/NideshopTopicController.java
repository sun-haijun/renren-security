package io.renren.modules.shop.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.shop.entity.NideshopTopicEntity;
import io.renren.modules.shop.service.NideshopTopicService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-24 18:54:33
 */
@RestController
@RequestMapping("shop/nideshoptopic")
public class NideshopTopicController {
    @Autowired
    private NideshopTopicService nideshopTopicService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("shop:nideshoptopic:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = nideshopTopicService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 查询
     */
    @RequestMapping("/select")
    @RequiresPermissions("shop:nideshoptopic:list")
    public R select(@RequestParam Map<String, Object> params){
        List<NideshopTopicEntity> list = nideshopTopicService.queryTopicList(params.get("topicName").toString());
        JSONArray arr = JSONArray.parseArray(JSON.toJSONString(list, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero));
        return R.ok().put("data", arr);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("shop:nideshoptopic:info")
    public R info(@PathVariable("id") Integer id){
        NideshopTopicEntity nideshopTopic = nideshopTopicService.selectById(id);

        return R.ok().put("nideshopTopic", nideshopTopic);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shop:nideshoptopic:save")
    public R save(@RequestBody NideshopTopicEntity nideshopTopic){
        nideshopTopicService.insert(nideshopTopic);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shop:nideshoptopic:update")
    public R update(@RequestBody NideshopTopicEntity nideshopTopic){
        ValidatorUtils.validateEntity(nideshopTopic);
        nideshopTopicService.updateAllColumnById(nideshopTopic);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shop:nideshoptopic:delete")
    public R delete(@RequestBody Integer[] ids){
        nideshopTopicService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
