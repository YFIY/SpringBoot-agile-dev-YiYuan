package com.yiyuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyuan.entity.CfgEntity;
import com.yiyuan.entity.dto.CaptchaDto;

/**
 * 验证码业务接口
 * @author MoLi
 */
public interface CaptchaDao extends BaseMapper<CaptchaDto> {
}
