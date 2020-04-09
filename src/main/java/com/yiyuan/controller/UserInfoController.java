package com.yiyuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yiyuan.annotation.AnonymousAccess;
import com.yiyuan.cache.ConfigCache;
import com.yiyuan.core.Result;
import com.yiyuan.core.ResultGenerator;
import com.yiyuan.entity.UserInfoEntity;
import com.yiyuan.service.UserInfoService;
import com.yiyuan.utils.SnowflakeIdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.alibaba.fastjson.JSON;

/**
 * 演示类 UserInfoController
 * @author MoLi
 * @CreateTime 2019/6/8 16:27
 */
@RestController
@RequestMapping("/UserInfo")
@Api(tags = "演示接口-学生用户")
public class UserInfoController {

    /**
     * 用户业务接口
     */
    @Autowired
    private UserInfoService userInfoService;
    /**
     * 雪花ID生成
     */
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    /**
     * 缓存service
     */
    @Autowired
    private ConfigCache configCache;


    /**
     * 根据ID获取用户信息
     * @author MoLi
     * @CreateTime 2019/6/8 16:34
     * @param  id [id 用户ID]
     * @return UserInfoEntity 用户实体
     */
    @AnonymousAccess
    @ApiOperation(value = "根据ID获取用户信息", notes = "根据ID获取用户信息")
    @GetMapping(value = "/getInfo")
    public UserInfoEntity getInfo(@ApiParam(name = "id", value = "用户ID", required = true) @RequestParam(name = "id", required = true) String id){

        UserInfoEntity userInfoEntity = userInfoService.getById(id);

        //TODO 缓存测试
        System.out.println("==============缓存测试=================");
        String huancun = configCache.get("api.tencent.sms.appid");
        System.out.println(huancun);

        return userInfoEntity;
    }
    /**
     * 查询全部信息
     * @author MoLi
     * @return List<UserInfoEntity> 用户实体集合
     */
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public Result getList(){
        List<UserInfoEntity> userInfoEntityList = userInfoService.list();
        return ResultGenerator.genSuccessResult(userInfoEntityList);
    }
    /**
     * 分页查询全部数据
     * @author MoLi
     * @param  jsonStr [current 当前页][size 每页条数]
     * @return IPage<UserInfoEntity> 分页数据
     */
    @RequestMapping(value = "/getInfoListPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result getInfoListPage(@RequestBody String jsonStr){
        //需要在Config配置类中配置分页插件
        Map<String,Object> jsonMap = JSON.parseObject(jsonStr);
        IPage<UserInfoEntity> pageData = new Page<>();

        //参数校验
        if( null == jsonMap.get("current") || null == jsonMap.get("size") ){
            return ResultGenerator.genFailResult("当前页|每页条数,不允许为空");
        }

        //当前页
        pageData.setCurrent(Long.parseLong(jsonMap.get("current").toString()));
        //每页条数
        pageData.setSize(Long.parseLong(jsonMap.get("size").toString()));
        pageData = userInfoService.page(pageData);
        return ResultGenerator.genSuccessResult(pageData);
    }
    /**
     * 根据指定字段查询用户信息集合
     * @author MoLi
     * @param  jsonStr  kay是字段名 value是字段值  例如{"id":"123","password":"123"}
     * @return Collection<UserInfoEntity> 用户实体集合
     */
    @RequestMapping(value = "/getListMap", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result getListMap(@RequestBody String jsonStr){
        Map<String,Object> jsonMap = JSON.parseObject(jsonStr);
        Collection<UserInfoEntity> userInfoEntityList = userInfoService.listByMap(jsonMap);
        return ResultGenerator.genSuccessResult(userInfoEntityList);
    }
    /**
     * 新增用户信息
     * @author MoLi
     * @CreateTime 2019/6/8 16:40
     */
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result saveInfo(@RequestBody String jsonStr){
        //json字符串解析数据放入模型
        UserInfoEntity userInfoEntity = JSON.parseObject(jsonStr, UserInfoEntity.class);
        //雪花ID注入
        userInfoEntity.setId(snowflakeIdWorker.nextId());
        //执行保存
        userInfoService.save(userInfoEntity);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 批量新增用户信息
     * @author MoLi
     * @CreateTime 2019/6/8 16:42
     */
    @RequestMapping(value = "/saveInfoList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result saveInfoList(@RequestBody String jsonStr){

        //从json字符串中获取多个模型数据注入list
        List<UserInfoEntity> userInfoEntityList=new ArrayList(JSON.parseArray(jsonStr,UserInfoEntity.class));

        //遍历集合
        for (int i = 0; i < userInfoEntityList.size(); i++) {
            //雪花ID注入
            userInfoEntityList.get(i).setId(snowflakeIdWorker.nextId());
        }

        //批量保存
        userInfoService.saveBatch(userInfoEntityList);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 更新用户信息
     * @author MoLi
     * @CreateTime 2019/6/8 16:47
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result updateInfo(@RequestBody String jsonStr){

        //json字符串解析数据放入模型
        UserInfoEntity userInfoEntity = JSON.parseObject(jsonStr, UserInfoEntity.class);

        //根据实体中的ID去更新,其他字段如果值为null则不会更新该字段,参考yml配置文件
        userInfoService.updateById(userInfoEntity);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 新增或者更新用户信息
     * @author MoLi
     * @CreateTime 2019/6/8 16:50
     */
    @RequestMapping(value = "/saveOrUpdateInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result saveOrUpdate(@RequestBody String jsonStr){

        //json字符串解析数据放入模型
        UserInfoEntity userInfoEntity = JSON.parseObject(jsonStr, UserInfoEntity.class);

        //传入的实体类userInfoEntity中ID为null就会新增(ID自增)
        //实体类ID值存在,如果数据库存在ID就会更新,如果不存在就会新增
        userInfoService.saveOrUpdate(userInfoEntity);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 根据ID删除用户信息
     * @author MoLi
     * @CreateTime 2019/6/8 16:52
     */
    @RequestMapping("/deleteInfo")
    public Result deleteInfo(String id){
        userInfoService.removeById(id);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 根据ID批量删除用户信息
     * @author MoLi
     * @CreateTime 2019/6/8 16:55
     */
    @RequestMapping(value = "/deleteInfoList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result deleteInfoList(@RequestBody String jsonStr){

        //从json字符串中获取数据注入Map
        Map<String,List> mapType = JSON.parseObject(jsonStr,Map.class);

        //将Map中的数组注入List
        List<String> userIdlist = mapType.get("list");

        userInfoService.removeByIds(userIdlist);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 根据指定字段删除用户信息
     * @author MoLi
     * @CreateTime 2019/6/8 16:57
     */
    @RequestMapping(value = "/deleteInfoMap", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result deleteInfoMap(@RequestBody String jsonStr){

        //从json字符串中获取数据注入Map
        Map<String,Object> mapType = JSON.parseObject(jsonStr,Map.class);

        //kay是字段名 value是字段值
        userInfoService.removeByMap(mapType);
        return ResultGenerator.genSuccessResult();
    }
}
