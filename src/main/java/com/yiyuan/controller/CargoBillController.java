package com.yiyuan.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yiyuan.annotation.AnonymousAccess;
import com.yiyuan.core.Result;
import com.yiyuan.core.ResultGenerator;
import com.yiyuan.entity.UserInfoEntity;
import com.yiyuan.entity.sql.CargoBillSqlEntity;
import com.yiyuan.service.CargoBillService;
import com.yiyuan.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 货运单功能接口
 * @author MoLi
 */
@Slf4j
@RestController
@RequestMapping("/CargoBill")
public class CargoBillController {

    /**
     * 用户业务接口
     */
    @Autowired
    private CargoBillService cargoBillService;

    /**
     * 雪花ID生成
     */
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;


    /**
     * 根据ID获取一个货运单数据
     * @author MoLi
     */
    @AnonymousAccess//免登访问
    @GetMapping(value = "/getInfo")
    public CargoBillSqlEntity getInfo(@RequestParam(name = "id", required = true) String id){

        CargoBillSqlEntity cargoBillSqlEntity = cargoBillService.getById(id);

        return cargoBillSqlEntity;
    }

    /**
     * 查询全部货运单数据
     * @author MoLi
     */
    @AnonymousAccess//免登访问
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public Result getList(){
        List<CargoBillSqlEntity> cargoBillSqlEntityList = cargoBillService.list();
        return ResultGenerator.genSuccessResult(cargoBillSqlEntityList);
    }

    /**
     * 分页查询全部货运单数据
     * @author MoLi
     * @param  jsonStr [current 当前页][size 每页条数]
     * @return IPage<CargoBillSqlEntity> 分页数据
     */
    @RequestMapping(value = "/getInfoListPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result getInfoListPage(@RequestBody String jsonStr){
        //需要在Config配置类中配置分页插件
        Map<String,Object> jsonMap = JSON.parseObject(jsonStr);
        IPage<CargoBillSqlEntity> pageData = new Page<>();

        //参数校验
        if( null == jsonMap.get("current") || null == jsonMap.get("size") ){
            return ResultGenerator.genFailResult("当前页|每页条数,不允许为空");
        }

        //当前页
        pageData.setCurrent(Long.parseLong(jsonMap.get("current").toString()));
        //每页条数
        pageData.setSize(Long.parseLong(jsonMap.get("size").toString()));
        pageData = cargoBillService.page(pageData);
        return ResultGenerator.genSuccessResult(pageData);
    }

    /**
     * 根据指定字段查询货运单数据集合
     * @author MoLi
     * @param  jsonStr  kay是字段名 value是字段值  例如{"id":"123","password":"123"}
     * @return Collection<CargoBillSqlEntity>
     */
    @RequestMapping(value = "/getListMap", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result getListMap(@RequestBody String jsonStr){
        Map<String,Object> jsonMap = JSON.parseObject(jsonStr);
        Collection<CargoBillSqlEntity> CargoBillSqlEntityList = cargoBillService.listByMap(jsonMap);
        return ResultGenerator.genSuccessResult(CargoBillSqlEntityList);
    }

    /**
     * 新增货运单数据
     * @author MoLi
     */
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result saveInfo(@RequestBody String jsonStr){
        //json字符串解析数据放入模型
        CargoBillSqlEntity cargoBillSqlEntity = JSON.parseObject(jsonStr, CargoBillSqlEntity.class);
        //雪花ID注入
        cargoBillSqlEntity.setId(snowflakeIdWorker.nextId());
        //执行保存
        cargoBillService.save(cargoBillSqlEntity);
        return ResultGenerator.genSuccessResult();
    }


}
