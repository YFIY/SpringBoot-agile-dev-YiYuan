package com.yiyuan.service.impl;

import com.yiyuan.config.SecurityProperties;
import com.yiyuan.utils.*;
import com.yiyuan.utils.ip.AddressUtils;
import com.yiyuan.utils.ip.IpUtils;
import com.yiyuan.vo.JwtUserDto;
import com.yiyuan.vo.OnlineUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 在线用户业务类
 * @author MoLi
 */
@Service
@Slf4j
@SuppressWarnings({"unchecked","all"})//抑制警告
public class OnlineUserService {

    private final SecurityProperties properties;

    private RedisUtils redisUtils;

    public OnlineUserService(SecurityProperties properties, RedisUtils redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
    }

    /**
     * 保存在线用户信息
     * @param jwtUserDto
     * @param token
     * @param request
     */
    public void save(JwtUserDto jwtUserDto, String token, HttpServletRequest request){
        //获取部门名和姓名
        String job = jwtUserDto.getUser().getDept().getName() + "/" + jwtUserDto.getUser().getJob().getName();
        //获取IP
        String ip = IpUtils.getIpAddr(request);
        //获取设备名
        String browser = StringUtils.getBrowser(request);
        //获取IP所在位置
        String address = AddressUtils.getRealAddressByIP(ip);
        OnlineUserDto onlineUserDto = null;
        try {
            onlineUserDto = new OnlineUserDto(jwtUserDto.getUsername(), jwtUserDto.getUser().getNickName(), job, browser , ip, address, EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisUtils.set(properties.getOnlineKey() + token, onlineUserDto, properties.getTokenValidityInSeconds()/1000);

    }

    /**
     * 查询全部数据
     * @param filter
     * @param pageable
     * @return
     */
    public Map<String,Object> getAll(String filter, Pageable pageable){
        List<OnlineUserDto> onlineUserDtos = getAll(filter);
        System.out.println("=====================");
        System.out.println(pageable);
        return PageUtil.toPage(
                PageUtil.toPage(pageable.getPageNumber(), pageable.getPageSize(), onlineUserDtos),
                onlineUserDtos.size());
    }

    /**
     * 查询全部数据，不分页
     * @param filter
     * @return
     */
    public List<OnlineUserDto> getAll(String filter){
        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
        Collections.reverse(keys);
        List<OnlineUserDto> onlineUserDtos = new ArrayList<>();
        for (String key : keys) {
            OnlineUserDto onlineUserDto = (OnlineUserDto) redisUtils.get(key);
            if(StringUtils.isNotBlank(filter)){
                if(onlineUserDto.toString().contains(filter)){
                    onlineUserDtos.add(onlineUserDto);
                }
            } else {
                onlineUserDtos.add(onlineUserDto);
            }
        }
        onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUserDtos;
    }

    /**
     * 踢出用户
     * @param val
     * @throws Exception
     */
    public void kickOut(String key) {
        key = properties.getOnlineKey() + key;
        redisUtils.del(key);
    }


    /**
     * 退出登录
     * @param token
     */
    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
        redisUtils.del(key);
    }

    /**
     * 检测用户是否在之前已经登录，已经登录踢下线
     * @param userName
     */
    public void checkLoginOnUser(String userName, String igoreToken){
        List<OnlineUserDto> onlineUserDtos = getAll(userName);
        if(onlineUserDtos ==null || onlineUserDtos.isEmpty()){
            return;
        }
        for(OnlineUserDto onlineUserDto : onlineUserDtos){
            if(onlineUserDto.getUserName().equals(userName)){
                try {
                    String token =EncryptUtils.desDecrypt(onlineUserDto.getKey());
                    if(StringUtils.isNotBlank(igoreToken)&&!igoreToken.equals(token)){
                        this.kickOut(token);
                    }else if(StringUtils.isBlank(igoreToken)){
                        this.kickOut(token);
                    }
                } catch (Exception e) {
                    log.error("checkUser is error",e);
                }
            }
        }
    }

    /**
     * 导出
     * @param all
     * @param response
     * @throws IOException
     */
    public void download(List<OnlineUserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (OnlineUserDto user : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户名", user.getUserName());
            map.put("岗位", user.getJob());
            map.put("登录IP", user.getIp());
            map.put("登录地点", user.getAddress());
            map.put("浏览器", user.getBrowser());
            map.put("登录日期", user.getLoginTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }


    /**
     * 根据key查询Redis中的单个用户信息
     * @param key
     * @return
     */
    public OnlineUserDto getOne(String key) {
        return (OnlineUserDto)redisUtils.get(key);
    }
}
