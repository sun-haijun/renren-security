package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.NideshopTopicEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-24 18:54:33
 */
public interface NideshopTopicService extends IService<NideshopTopicEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据活动名称查询列表
     * @param topicName
     * @return
     */
    List<NideshopTopicEntity> queryTopicList(String topicName);
}

