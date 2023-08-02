package org.apache.management.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author YangRuiHong
 * @Create 2023-03-19 12:49
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("deviceInfo")
public class DeviceInfo {
    /**
     * 序号
     */
    @TableId("id")
    private Integer id;
    /**
     * 仪器设备，检具名称
     */
    @TableField("name")
    private String name;
    /**
     * 制造商名称
     */
    @TableField("manufacturer")
    private String manufacturer;
    /**
     * 型号规格
     */
    @TableField("specifi")
    private String specifi;
    /**
     * 设备编号
     */
    @TableField("number")
    private String number;
    /**
     * 放置地点
     */
    @TableField("posi")
    private String posi;
    /**
     * 检定方式
     */
    @TableField("verifi")
    private String verifi;
    /**
     * 检定单位
     */
    @TableField("verifiUnit")
    private String verifiUnit;
    /**
     * 检定周期
     */

    @TableField("verifiCycle")
    private String verifiCycle;
    /**
     * 本年检/校时间及结果
     */
    @TableField("verrfiInfo")
    private String verrfiInfo;
    /**
     * 有效期检/校证书号
     */
    @TableField("certificate")
    private String certificate;
    /**
     * 下次检/校时间
     */
    @TableField("nextVerfi")
    private String nextVerfi;
    /**
     * 检定（校准）依据标准
     */
    @TableField("verfiStandard")
    private String verfiStandard;

}
