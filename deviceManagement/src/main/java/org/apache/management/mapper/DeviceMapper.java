package org.apache.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.management.entities.DeviceInfo;

import java.util.List;

/**
 * @Author YangRuiHong
 * @Create 2023-03-19 13:02
 * @Description
 */
@Mapper
public interface DeviceMapper extends BaseMapper<DeviceInfo> {

    public List<DeviceInfo> getByPage(@Param("page") int page);
}
