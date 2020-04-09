package com.yiyuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiyuan.dao.CaptchaDao;
import com.yiyuan.dao.CfgDao;
import com.yiyuan.entity.CfgEntity;
import com.yiyuan.entity.dto.CaptchaDto;
import com.yiyuan.service.CaptchaService;
import com.yiyuan.service.CfgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 验证码业务
 * @author MoLi
 */
@Service
@Transactional
public class CaptchaServiceImpl extends ServiceImpl<CaptchaDao, CaptchaDto> implements CaptchaService {
}
