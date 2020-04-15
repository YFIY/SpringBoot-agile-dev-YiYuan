package com.yiyuan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yiyuan.config.DataScope;
import com.yiyuan.entity.Dept;
import com.yiyuan.entity.User;
import com.yiyuan.entity.dto.RoleSmallDto;
import com.yiyuan.entity.dto.UserDto;
import com.yiyuan.exception.ServiceException;
import com.yiyuan.query.UserQueryCriteria;
import com.yiyuan.service.DeptService;
import com.yiyuan.service.RoleService;
import com.yiyuan.service.UserService;
import com.yiyuan.service.VerificationCodeService;
import com.yiyuan.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Api(tags = "系统：用户管理")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Value("${rsa.private_key}")
    private String privateKey;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final DataScope dataScope;

    private final DeptService deptService;

    private final RoleService roleService;

    private final VerificationCodeService verificationCodeService;

    public UserController(PasswordEncoder passwordEncoder, UserService userService, DataScope dataScope, DeptService deptService, RoleService roleService, VerificationCodeService verificationCodeService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.dataScope = dataScope;
        this.deptService = deptService;
        this.roleService = roleService;
        this.verificationCodeService = verificationCodeService;
    }


    @ApiOperation("查询用户")
    @GetMapping(value = "/getUsers")
    @PreAuthorize("@dokit.check('user:list')")
    public IPage<UserDto> getUsers(UserQueryCriteria criteria,Long current,Long size) {

        //参数校验
        if( null == current || null == size ){
            throw new ServiceException("当前页current|每页条数size,不允许为空");
        }

        //期望查询部门集合
        Set<Long> deptSet = new HashSet<>();
        //确切查询部门集合
        Set<Long> result = new HashSet<>();

        //创建分页模型
        IPage<UserDto> page = new Page<>();
        //从接口参数中获取当前页数据和每页条数数据
        page.setCurrent(current);
        page.setSize(size);

        //如果条件对象中的部门ID不为空
        if (!ObjectUtils.isEmpty(criteria.getDeptId())) {
            //将此部门ID注入部门集合
            deptSet.add(criteria.getDeptId());
            //获取此部门下的所有部门数据
            List<Dept> deptList = deptService.findByPid(criteria.getDeptId());
            //递归获取此部门下所有部门的ID
            List<Long> deptChildren = dataScope.getDeptChildren(deptList);
            //将ID放入期望查询部门集合
            deptSet.addAll(deptChildren);
        }
        //获取此用户允许查询的部门ID
        Set<Long> deptIds = dataScope.getDeptIds();
        // 查询条件不为空并且数据权限不为空则取交集
        if (!CollectionUtils.isEmpty(deptIds) && !CollectionUtils.isEmpty(deptSet)) {
            // 取交集
            result.addAll(deptSet);
            result.retainAll(deptIds);
            //部门ID放入条件对象
            criteria.setDeptIds(result);
            // 若无交集，则代表无数据权限
            if (result.size() == 0) {
                //返回空数据对象
                return page;
            } else {
                //获取用户集合数据
                page = userService.queryAll(page,criteria);
                return page;
            }
            // 否则取并集
        } else {
            //【期望查询部门集合】数据放入【确切查询部门集合】
            result.addAll(deptSet);
            //【用户允许查询的部门集合】数据放入【确切查询部门集合】
            result.addAll(deptIds);
            //部门ID放入条件对象
            criteria.setDeptIds(result);
            //获取用户集合数据
            page = userService.queryAll(page,criteria);
            return page;
        }
    }

    @ApiOperation("新增用户")
    @PostMapping
    @PreAuthorize("@dokit.check('user:add')")
    public UserDto create(@RequestBody User user) {
        //调用权限鉴定方法
        checkLevel(user);
        // 默认密码 123456
        user.setPassword(passwordEncoder.encode("123456"));
        //TODO 未写实现代码
        UserDto userDto = userService.create(user);
        return userDto;
    }
/*
    @ApiOperation("修改用户")
    @PutMapping
    @PreAuthorize("@dokit.check('user:edit')")
    public ResponseEntity<Object> update(@Validated(User.Update.class) @RequestBody User resources) {
        checkLevel(resources);
        userService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("修改用户：个人中心")
    @PutMapping(value = "center")
    public ResponseEntity<Object> center(@Validated(User.Update.class) @RequestBody User resources) {
        if(!resources.getId().equals(SecurityUtils.getCurrentUserId())){
            throw new BadRequestException("不能修改他人资料");
        }
        userService.updateCenter(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除用户")
    @DeleteMapping
    @PreAuthorize("@dokit.check('user:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        for (Long id : ids) {
            Integer currentLevel =  Collections.min(roleService.findByUsersId(SecurityUtils.getCurrentUserId()).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
            Integer optLevel = Collections.min(roleService.findByUsersId(id).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
            if (currentLevel > optLevel) {
                throw new BadRequestException("角色权限不足，不能删除：" + userService.findById(id).getUsername());
            }
        }
        userService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "/updatePass")
    public ResponseEntity<Object> updatePass(@RequestBody UserPassVo passVo) {
        // 密码解密
        RSA rsa = new RSA(privateKey, null);
        String oldPass = new String(rsa.decrypt(passVo.getOldPass(), KeyType.PrivateKey));
        String newPass = new String(rsa.decrypt(passVo.getNewPass(), KeyType.PrivateKey));
        UserDto user = userService.findByName(SecurityUtils.getCurrentUsername());
        if (!passwordEncoder.matches(oldPass, user.getPassword())) {
            throw new BadRequestException("修改失败，旧密码错误");
        }
        if (passwordEncoder.matches(newPass, user.getPassword())) {
            throw new BadRequestException("新密码不能与旧密码相同");
        }
        userService.updatePass(user.getUsername(), passwordEncoder.encode(newPass));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("修改头像")
    @PostMapping(value = "/updateAvatar")
    public ResponseEntity<Object> updateAvatar(@RequestParam MultipartFile file) {
        userService.updateAvatar(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("修改邮箱")
    @PostMapping(value = "/updateEmail/{code}")
    public ResponseEntity<Object> updateEmail(@PathVariable String code, @RequestBody User user) {
        // 密码解密
        RSA rsa = new RSA(privateKey, null);
        String password = new String(rsa.decrypt(user.getPassword(), KeyType.PrivateKey));
        UserDto userDto = userService.findByName(SecurityUtils.getCurrentUsername());
        if (!passwordEncoder.matches(password, userDto.getPassword())) {
            throw new BadRequestException("密码错误");
        }
        VerificationCode verificationCode = new VerificationCode(code, DoKitConstant.RESET_MAIL, "email", user.getEmail());
        verificationCodeService.validated(verificationCode);
        userService.updateEmail(userDto.getUsername(), user.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

*/
    //如果当前用户的角色级别低于创建用户的角色级别，则抛出权限不足的错误
    private void checkLevel(User resources) {
        Integer currentLevel =  Collections.min(roleService.findByUsersId(SecurityUtils.getCurrentUserId()).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
        //TODO 未写实现代码
        Integer optLevel = roleService.findByRoles(resources.getRoles());
        if (currentLevel > optLevel) {
            throw new ServiceException("角色权限不足");
        }
    }
}

