package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Java不良人
 * @date 2023/3/29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
