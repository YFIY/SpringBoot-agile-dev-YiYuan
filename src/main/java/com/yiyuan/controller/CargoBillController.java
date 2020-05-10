package com.yiyuan.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yiyuan.annotation.AnonymousAccess;
import com.yiyuan.core.Result;
import com.yiyuan.core.ResultGenerator;
import com.yiyuan.exception.ServiceException;
import com.yiyuan.entity.sql.CargoBillSqlEntity;
import com.yiyuan.service.CargoBillService;
import com.yiyuan.utils.SnowflakeIdWorker;
import com.yiyuan.utils.StringUtil;
import com.yiyuan.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Date;
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
     * 分页查询全部货运单数据
     * @author MoLi
     * @param  jsonStr [current 当前页][size 每页条数]
     * @return IPage<CargoBillSqlEntity> 分页数据
     */
    @AnonymousAccess//免登访问
    @RequestMapping(value = "/getInfoListPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public IPage<CargoBillSqlEntity> getInfoListPage(@RequestBody String jsonStr){

        Map<String,Object> jsonMap = JSON.parseObject(jsonStr);
        CargoBillSqlEntity cargoBillSqlEntity = JSON.parseObject(jsonStr, CargoBillSqlEntity.class);

        IPage<CargoBillSqlEntity> pageData = new Page<>();

        //参数校验
        if( null == jsonMap.get("current") || null == jsonMap.get("size") ){
            throw new ServiceException("当前页current|每页条数size,不允许为空");
        }


        //当前页
        pageData.setCurrent(Long.parseLong(jsonMap.get("current").toString()));
        //每页条数
        pageData.setSize(Long.parseLong(jsonMap.get("size").toString()));
        pageData = cargoBillService.getListMapPage(pageData,cargoBillSqlEntity);
        return pageData;
    }

    /**
     * 新增或者更新货运单数据
     * @author MoLi
     */
    @AnonymousAccess//免登访问
    @RequestMapping(value = "/saveOrUpdateInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Result saveOrUpdateInfo(@RequestBody String jsonStr){
        //json字符串解析数据放入模型
        CargoBillSqlEntity cargoBillSqlEntity = JSON.parseObject(jsonStr, CargoBillSqlEntity.class);

        //如果ID为空,执行新增参数注入
        if(StringUtil.isEmpty(cargoBillSqlEntity.getId())){
            //雪花ID注入
            cargoBillSqlEntity.setId(snowflakeIdWorker.nextId());
            //创建时间注入
            cargoBillSqlEntity.setCreationTime(new Date());
        }

        //TODO 暂时先固定用户ID
        cargoBillSqlEntity.setUserId(8068996691l);
        //执行保存
        cargoBillService.saveOrUpdate(cargoBillSqlEntity);
        return ResultGenerator.genSuccessResult();
    }



}
