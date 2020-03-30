package com.yiyuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yiyuan.core.Result;
import com.yiyuan.core.ResultGenerator;
import com.yiyuan.entity.UserInfoEntity;
import com.yiyuan.service.UserInfoService;
import com.yiyuan.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.alibaba.fastjson.JSON;

/**
 * @Description UserInfoController
 * @Author MoLi
 * @CreateTime 2019/6/8 16:27
 */
@RestController
@RequestMapping("/UserInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据ID获取用户信息
     * @Author MoLi
     * @CreateTime 2019/6/8 16:34
     * @Param  id  用户ID
     * @Return UserInfoEntity 用户实体
     */
    @RequestMapping(value = "/getInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result getInfo(@RequestBody String jsonStr){
        UserInfoEntity model = JSON.parseObject(jsonStr, UserInfoEntity.class);
        UserInfoEntity userInfoEntity = userInfoService.getById(model.getId());

        //TODO 雪花算法ID生成测试
        SnowflakeIdWorker snowflakeIdWorker = SnowflakeIdWorker.getSnowflakeIdWorker();
        System.out.println("===============================");
        System.out.println(snowflakeIdWorker.nextId());

        return ResultGenerator.genSuccessResult(userInfoEntity);
    }
    /**
     * 查询全部信息
     * @Author MoLi
     * @CreateTime 2019/6/8 16:35
     * @Return List<UserInfoEntity> 用户实体集合
     */
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public Result getList(){
        List<UserInfoEntity> userInfoEntityList = userInfoService.list();
        return ResultGenerator.genSuccessResult(userInfoEntityList);
    }
    /**
     * 分页查询全部数据
     * @Author MoLi
     * @CreateTime 2019/6/8 16:37
     * @Param  current  当前页
     * @Param  size  每页条数
     * @Return IPage<UserInfoEntity> 分页数据
     */
    @RequestMapping(value = "/getInfoListPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result getInfoListPage(@RequestBody String jsonStr){
        //需要在Config配置类中配置分页插件
        Map<String,Object> jsonMap = JSON.parseObject(jsonStr);
        IPage<UserInfoEntity> page = new Page<>();
        page.setCurrent(Long.parseLong(jsonMap.get("current").toString())); //当前页
        page.setSize(Long.parseLong(jsonMap.get("size").toString()));    //每页条数
        page = userInfoService.page(page);
        return ResultGenerator.genSuccessResult(page);
    }
    /**
     * 根据指定字段查询用户信息集合
     * @Author MoLi
     * @CreateTime 2019/6/8 16:39
     * @Param  任意字段都可当做条件传入  kay是字段名 value是字段值  例如{"id":"123","password":"123"}
     * @Return Collection<UserInfoEntity> 用户实体集合
     */
    @RequestMapping(value = "/getListMap", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result getListMap(@RequestBody String jsonStr){
        Map<String,Object> jsonMap = JSON.parseObject(jsonStr);
        Collection<UserInfoEntity> userInfoEntityList = userInfoService.listByMap(jsonMap);
        return ResultGenerator.genSuccessResult(userInfoEntityList);
    }
    /**
     * 新增用户信息
     * @Author MoLi
     * @CreateTime 2019/6/8 16:40
     */
    @RequestMapping("/saveInfo")
    public Result saveInfo(){
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setName("小龙");
        userInfoEntity.setSkill("JAVA");
        userInfoEntity.setAge(18);
        userInfoEntity.setFraction(59L);
        userInfoEntity.setEvaluate("该学生是一个在改BUG的码农");
        userInfoService.save(userInfoEntity);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 批量新增用户信息
     * @Author MoLi
     * @CreateTime 2019/6/8 16:42
     */
    @RequestMapping("/saveInfoList")
    public Result saveInfoList(){
        //创建对象
        UserInfoEntity MoLi = new UserInfoEntity();
        MoLi.setName("MoLi");
        MoLi.setSkill("睡觉");
        MoLi.setAge(18);
        MoLi.setFraction(60L);
        MoLi.setEvaluate("MoLi是一个爱睡觉,并且身材较矮骨骼巨大的骷髅小胖子");
        UserInfoEntity papyrus = new UserInfoEntity();
        papyrus.setName("papyrus");
        papyrus.setSkill("JAVA");
        papyrus.setAge(18);
        papyrus.setFraction(58L);
        papyrus.setEvaluate("Papyrus是一个讲话大声、个性张扬的骷髅，给人自信、有魅力的骷髅小瘦子");
        //批量保存
        List<UserInfoEntity> list =new ArrayList<>();
        list.add(MoLi);
        list.add(papyrus);
        userInfoService.saveBatch(list);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 更新用户信息
     * @Author MoLi
     * @CreateTime 2019/6/8 16:47
     */
    @RequestMapping("/updateInfo")
    public Result updateInfo(){
        //根据实体中的ID去更新,其他字段如果值为null则不会更新该字段,参考yml配置文件
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId(1L);
        userInfoEntity.setAge(19);
        userInfoService.updateById(userInfoEntity);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 新增或者更新用户信息
     * @Author MoLi
     * @CreateTime 2019/6/8 16:50
     */
    @RequestMapping("/saveOrUpdateInfo")
    public Result saveOrUpdate(){
        //传入的实体类userInfoEntity中ID为null就会新增(ID自增)
        //实体类ID值存在,如果数据库存在ID就会更新,如果不存在就会新增
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId(1L);
        userInfoEntity.setAge(20);
        userInfoService.saveOrUpdate(userInfoEntity);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 根据ID删除用户信息
     * @Author MoLi
     * @CreateTime 2019/6/8 16:52
     */
    @RequestMapping("/deleteInfo")
    public Result deleteInfo(String userId){
        userInfoService.removeById(userId);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 根据ID批量删除用户信息
     * @Author MoLi
     * @CreateTime 2019/6/8 16:55
     */
    @RequestMapping("/deleteInfoList")
    public Result deleteInfoList(){
        List<String> userIdlist = new ArrayList<>();
        userIdlist.add("12");
        userIdlist.add("13");
        userInfoService.removeByIds(userIdlist);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 根据指定字段删除用户信息
     * @Author MoLi
     * @CreateTime 2019/6/8 16:57
     */
    @RequestMapping("/deleteInfoMap")
    public Result deleteInfoMap(){
        //kay是字段名 value是字段值
        Map<String,Object> map = new HashMap<>();
        map.put("skill","删除");
        map.put("fraction",10L);
        userInfoService.removeByMap(map);
        return ResultGenerator.genSuccessResult();
    }
}
