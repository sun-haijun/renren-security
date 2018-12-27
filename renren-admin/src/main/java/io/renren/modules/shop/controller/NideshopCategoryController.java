package io.renren.modules.shop.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.common.utils.Constant;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysDeptEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.shop.entity.NideshopCategoryEntity;
import io.renren.modules.shop.service.NideshopCategoryService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author haijun.sun
 * @email jason_sunhj@163.com
 * @date 2018-12-27 11:07:56
 */
@RestController
@RequestMapping("shop/nideshopcategory")
public class NideshopCategoryController extends AbstractController {
    @Autowired
    private NideshopCategoryService nideshopCategoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("shop:nideshopcategory:list")
    public List<NideshopCategoryEntity> list(){
        List<NideshopCategoryEntity> categoryList = nideshopCategoryService.queryList(new HashMap<String, Object>());
        return categoryList;
    }

    /**
     * 上级部门Id(管理员则为0)
     */
    @RequestMapping("/info")
    @RequiresPermissions("shop:nideshopcategory:list")
    public R info(){
        long categoryId = 0;
        if(getUserId() != Constant.SUPER_ADMIN){

        }
        return R.ok().put("id", categoryId);
    }

    /**
     * 选择部门(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("shop:nideshopcategory:list")
    public R select(){
        List<NideshopCategoryEntity> categoryList = nideshopCategoryService.queryList(new HashMap<String, Object>());
        //添加一级部门
        if(getUserId() == Constant.SUPER_ADMIN){
            NideshopCategoryEntity root = new NideshopCategoryEntity();
            root.setId(0);
            root.setName("一级分类");
            root.setParentId(-1);
            categoryList.add(root);
        }
        return R.ok().put("categoryList", categoryList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("shop:nideshopcategory:info")
    public R info(@PathVariable("id") Integer id){
        NideshopCategoryEntity nideshopCategory = nideshopCategoryService.selectById(id);

        return R.ok().put("nideshopCategory", nideshopCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("shop:nideshopcategory:save")
    public R save(@RequestBody NideshopCategoryEntity nideshopCategory){

        ValidatorUtils.validateEntity(nideshopCategory, AddGroup.class);
        nideshopCategoryService.insert(nideshopCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("shop:nideshopcategory:update")
    public R update(@RequestBody NideshopCategoryEntity nideshopCategory){
        ValidatorUtils.validateEntity(nideshopCategory);
        nideshopCategoryService.updateAllColumnById(nideshopCategory);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("shop:nideshopcategory:delete")
    public R delete(int deptId){
        //判断是否有子部门
        List<Integer> deptList = nideshopCategoryService.queryCategoryList(deptId);
        if(deptList.size() > 0){
            return R.error("请先删除子分类");
        }
        nideshopCategoryService.deleteById(deptId);
        return R.ok();
    }

}
