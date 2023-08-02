package org.apache.management.utils;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.management.entities.DeviceInfo;
import org.apache.management.mapper.DeviceMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Component
public class ExcelExportUtils {
    @Resource
    DeviceMapper mapper;

    public static void exportExcelByResponse(ExcelWriter excelWriter, HttpServletResponse response, String fileName, HttpServletRequest request) throws IOException {
        String userAgent = request.getHeader("USER-AGENT");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        if (StringUtils.contains(userAgent, "MSIE") || StringUtils.contains(userAgent, "Trident") || StringUtils.contains(userAgent, "Edge")) {//IE 浏览器
            fileName = URLEncoder.encode(fileName, "UTF8");
        } else {//火狐，google等其他浏览器
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        //客户端不缓存
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        Workbook workbook = excelWriter.getWorkbook();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void exportDailyReportDetailExcel(DeviceInfo reportDetailVO, HttpServletResponse response, HttpServletRequest request) {
        ExcelWriter bigWriter = ExcelUtil.getBigWriter();
        try {
            // 导出数据
            List<DeviceInfo> reportDetailVOList;
            LambdaQueryWrapper wrapper = new QueryWrapper<DeviceInfo>().lambda();
            reportDetailVOList = mapper.selectList(wrapper);
            // 设置列宽
            bigWriter.setColumnWidth(0, 15);
            bigWriter.setColumnWidth(1, 10);
            bigWriter.setColumnWidth(2, 10);
            bigWriter.setColumnWidth(3, 20);
            bigWriter.setColumnWidth(4, 20);
            bigWriter.setColumnWidth(5, 15);
            // 导出列名，对应实体类属性名
            bigWriter.addHeaderAlias("companyName", "单位");
            bigWriter.addHeaderAlias("deptName", "部门");
            bigWriter.addHeaderAlias("nickName", "姓名");
            bigWriter.addHeaderAlias("idCard", "身份证");
            bigWriter.addHeaderAlias("address", "身份证地址");
            bigWriter.addHeaderAlias("phone", "联系电话");
            bigWriter.setOnlyAlias(true);
            bigWriter.write(reportDetailVOList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        try {
            ExcelExportUtils.exportExcelByResponse(bigWriter, response, "报备统计", request);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }

}