package io.renren.modules.shop.dao;

import io.renren.modules.shop.entity.NideshopTopicEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 
 * 
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-24 18:54:33
 */
public interface NideshopTopicDao extends BaseMapper<NideshopTopicEntity> {

    /**
     * 查询子部门ID列表
     * @param topicName  活动名称
     */
    List<NideshopTopicEntity> queryTopicList(String topicName);
}
