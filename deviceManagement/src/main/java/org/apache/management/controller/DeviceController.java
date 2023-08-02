package org.apache.management.controller;

import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.management.entities.CommonResult;
import org.apache.management.entities.DeviceInfo;
import org.apache.management.entities.MyExcelHandler;
import org.apache.management.mapper.DeviceMapper;
import org.apache.management.utils.ExcelExportUtils;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * @Author YangRuiHong
 * @Create 2023-03-19 12:17
 * @Description
 */
@Controller
@CrossOrigin
public class DeviceController {
    @Resource
    private DeviceMapper mapper;
    @Resource
    private CommonResult commonResult;

    @RequestMapping("/index")
    public String index(Model model) {
        LambdaQueryWrapper wrapper = new QueryWrapper<DeviceInfo>().lambda().eq(DeviceInfo::getId, 1);
        List list = mapper.selectList(wrapper);
        model.addAttribute("arr", list);
        model.addAttribute("user", "abc");
        return "index";
    }

    /**
     * 查找所有设备信息
     *
     * @return
     */

    @RequestMapping("/device")
    public String getInfo(Model model) {

        LambdaQueryWrapper wrapper = new QueryWrapper<DeviceInfo>().lambda();
        ArrayList<DeviceInfo> list = (ArrayList<DeviceInfo>) mapper.selectList(wrapper);
        model.addAttribute("devices", list);
        return "info";
    }

    @RequestMapping("/add")
    public String add() {
        return "add";
    }

    /**
     * 保存设备信息
     * "name": name,
     * "zzs": zzs,
     * "xhgg": xhgg,
     * "number": number,
     * "posi": posi,
     * "type": type,
     * "unit": unit,
     * "cycle": cycle,
     * "time": time,
     * "Cert": Cert,
     * "Nexttime": Nexttime,
     * "Stand": Stand
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/save")
    public CommonResult<DeviceInfo> save(@RequestParam("name") String name,
                                         @RequestParam("manufacturer") String zzs,
                                         @RequestParam("specifi") String xhgg,
                                         @RequestParam("number") String number,
                                         @RequestParam("posi") String posi,
                                         @RequestParam("verifi") String type,
                                         @RequestParam("verifiUnit") String unit,
                                         @RequestParam("verifiCycle") String cycle,
                                         @RequestParam("verrfiInfo") String time,
                                         @RequestParam("certificate") String Cert,
                                         @RequestParam("nextVerfi") String Nexttime,
                                         @RequestParam("verfiStandard") String Stand) {
        DeviceInfo deviceInfo = new DeviceInfo(0, name, zzs, xhgg, number, posi, type, unit, cycle, time, Cert, Nexttime, Stand);
        System.out.println(deviceInfo);
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(zzs)
                && StringUtils.isNotBlank(xhgg) && StringUtils.isNotBlank(number) && StringUtils.isNotBlank(posi) && StringUtils.isNotBlank(type) && StringUtils.isNotBlank(unit) && StringUtils.isNotBlank(cycle) && StringUtils.isNotBlank(time) && StringUtils.isNotBlank(Cert) && StringUtils.isNotBlank(Nexttime) && StringUtils.isNotBlank(Stand)) {

            LambdaQueryWrapper wrapper = new QueryWrapper<DeviceInfo>().lambda()
                    .eq(DeviceInfo::getName, name)
                    .eq(DeviceInfo::getNumber, number)
                    .eq(DeviceInfo::getManufacturer, zzs)
                    .eq(DeviceInfo::getSpecifi, xhgg)
                    .eq(DeviceInfo::getPosi, posi)
                    .eq(DeviceInfo::getVerifi, type)
                    .eq(DeviceInfo::getVerifiUnit, unit)
                    .eq(DeviceInfo::getVerifiCycle, cycle)
                    .eq(DeviceInfo::getVerrfiInfo, time)
                    .eq(DeviceInfo::getCertificate, Cert)
                    .eq(DeviceInfo::getNextVerfi, Nexttime)
                    .eq(DeviceInfo::getVerfiStandard, Stand);
            if (mapper.selectList(wrapper).stream().count() > 0) {
                commonResult.setCode(500);
                commonResult.setMessage("设备信息已存在，不能重复添加");
                return commonResult;
            }
            if (mapper.insert(deviceInfo) > 0) {
                commonResult.setCode(200);
                commonResult.setMessage("设备信息添加成功");

                return commonResult;
            }

        }
        commonResult.setCode(500);
        commonResult.setMessage("设备信息不能为空，设备添加失败");

        return commonResult;


    }

    /**
     * 根据设备名称查找
     *
     * @param name
     * @return
     */

    @RequestMapping("/query/{name}")
    public String getInfoByName(@PathVariable("name") String name, Model model) {

        LambdaQueryWrapper wrapper = new QueryWrapper<DeviceInfo>().lambda().like(DeviceInfo::getName, name);


        List list = mapper.selectList(wrapper);
        model.addAttribute("devices", list);
        return "info";
    }

    /**
     * 根据设备编号删除设备信息
     *
     * @param number
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/del")
    public CommonResult<DeviceInfo> del(@RequestParam("number") String number, Model model) {

        LambdaQueryWrapper delw = new QueryWrapper<DeviceInfo>().lambda().eq(DeviceInfo::getNumber, number);
        mapper.delete(delw);


        LambdaQueryWrapper wrapper = new QueryWrapper<DeviceInfo>().lambda();
        commonResult.setCode(200);
        commonResult.setMessage("删除成功");
        return commonResult;
    }

    @RequestMapping("/exp")
    @ResponseBody
    public CommonResult goodsInfoDownload() {
        LambdaQueryWrapper wrapper = new QueryWrapper<DeviceInfo>().lambda();
        ArrayList<DeviceInfo> list = (ArrayList<DeviceInfo>) mapper.selectList(wrapper);
        System.out.println("list = " + list);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String excelName1 = "设备信息-" + formatter.format(date) + ".xlsx";
        String excelName = "D:\\" + excelName1;

        ExcelWriter excelWriter = null;

        try {
            excelWriter = EasyExcel
                    .write(excelName, DeviceInfo.class)
                    //限制excel的列宽在5~30之间（方法在下面）
                    .registerWriteHandler(new MyExcelHandler())
                    .build();
            WriteSheet writeSheet = EasyExcel.writerSheet("devices").build();
            excelWriter.write(list, writeSheet);
            commonResult.setCode(200);
            commonResult.setMessage("导出数据成功");
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        return commonResult;
    }

    @RequestMapping("/edit/{number}")
    public String edit(@PathVariable("number") String number, Model model) {
        LambdaQueryWrapper<DeviceInfo> lambda = new QueryWrapper<DeviceInfo>().lambda().eq(DeviceInfo::getNumber, number);
        List<DeviceInfo> deviceInfos = mapper.selectList(lambda);
        model.addAttribute("device", deviceInfos.get(0));
        return "edit";
    }

    @ResponseBody
    @PostMapping("/modify")
    public CommonResult modify(@RequestParam("name") String name,
                               @RequestParam("manufacturer") String zzs,
                               @RequestParam("specifi") String xhgg,
                               @RequestParam("number") String number,
                               @RequestParam("posi") String posi,
                               @RequestParam("verifi") String type,
                               @RequestParam("verifiUnit") String unit,
                               @RequestParam("verifiCycle") String cycle,
                               @RequestParam("verrfiInfo") String time,
                               @RequestParam("certificate") String Cert,
                               @RequestParam("nextVerfi") String Nexttime,
                               @RequestParam("verfiStandard") String Stand) {
        if (!(StringUtils.isNotBlank(name) || StringUtils.isNotBlank(zzs)
                || StringUtils.isNotBlank(xhgg) || StringUtils.isNotBlank(posi) || StringUtils.isNotBlank(type) || StringUtils.isNotBlank(unit) || StringUtils.isNotBlank(cycle) || StringUtils.isNotBlank(time) || StringUtils.isNotBlank(Cert) || StringUtils.isNotBlank(Nexttime) || StringUtils.isNotBlank(Stand))) {
            commonResult.setCode(502);
            commonResult.setMessage("请输入要修改的数据");
            return commonResult;
        }
        LambdaQueryWrapper<DeviceInfo> lambda = new QueryWrapper<DeviceInfo>().lambda().eq(DeviceInfo::getNumber, number);
        DeviceInfo deviceInfo = new DeviceInfo();
        if (StringUtils.isNotBlank(name)) {
            deviceInfo.setName(name);
        }
        if (StringUtils.isNotBlank(zzs)) {
            deviceInfo.setManufacturer(zzs);
        }
        if (StringUtils.isNotBlank(xhgg)) {
            deviceInfo.setSpecifi(xhgg);
        }
        if (StringUtils.isNotBlank(posi)) {
            deviceInfo.setPosi(posi);
        }
        if (StringUtils.isNotBlank(type)) {
            deviceInfo.setVerifi(type);
        }
        if (StringUtils.isNotBlank(unit)) {
            deviceInfo.setVerifiUnit(unit);
        }

        if (StringUtils.isNotBlank(cycle)) {
            deviceInfo.setVerifiCycle(cycle);
        }
        if (StringUtils.isNotBlank(time)) {
            deviceInfo.setVerrfiInfo(time);
        }
        if (StringUtils.isNotBlank(Cert)) {
            deviceInfo.setCertificate(Cert);
        }
        if (StringUtils.isNotBlank(Nexttime)) {
            deviceInfo.setNextVerfi(Nexttime);
        }
        if (StringUtils.isNotBlank(Stand)) {
            deviceInfo.setVerfiStandard(Stand);
        }
        mapper.update(deviceInfo, lambda);
        commonResult.setCode(200);
        commonResult.setMessage("设备信息修改成功");
        return commonResult;

    }

    @RequestMapping("/Export")
    public String Export() {
        return "Export";
    }


    public static void exportExcelByResponse(cn.hutool.poi.excel.ExcelWriter excelWriter, HttpServletResponse response, String fileName, HttpServletRequest request) throws IOException {
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

    @RequestMapping("/exp2")
    @ResponseBody
    public void exportDailyReportDetailExcel(DeviceInfo reportDetailVO, HttpServletResponse response, HttpServletRequest request) {
        cn.hutool.poi.excel.ExcelWriter bigWriter = ExcelUtil.getBigWriter();
        try {
            // 导出数据
            List<DeviceInfo> reportDetailVOList;
            LambdaQueryWrapper wrapper = new QueryWrapper<DeviceInfo>().lambda();
            reportDetailVOList = mapper.selectList(wrapper);
            System.out.println("reportDetailVOList = " + reportDetailVOList);
            // 设置列宽
            bigWriter.setColumnWidth(0, 15);
            bigWriter.setColumnWidth(1, 30);
            bigWriter.setColumnWidth(2, 20);
            bigWriter.setColumnWidth(3, 20);
            bigWriter.setColumnWidth(4, 20);
            bigWriter.setColumnWidth(5, 20);
            bigWriter.setColumnWidth(6, 20);
            bigWriter.setColumnWidth(7, 20);
            bigWriter.setColumnWidth(8, 20);
            bigWriter.setColumnWidth(9, 20);
            bigWriter.setColumnWidth(10, 20);
            bigWriter.setColumnWidth(11, 20);
            bigWriter.setColumnWidth(12, 20);
            bigWriter.setColumnWidth(13, 20);
            // 导出列名，对应实体类属性名  /
            bigWriter.addHeaderAlias("id", "编号");
            bigWriter.addHeaderAlias("name", "检具名称");
            bigWriter.addHeaderAlias("manufacturer", "制造商名称");
            bigWriter.addHeaderAlias("specifi", "型号规格");
            bigWriter.addHeaderAlias("number", "设备编号");
            bigWriter.addHeaderAlias("posi", "放置地点");
            bigWriter.addHeaderAlias("verifi", "检定方式");
            bigWriter.addHeaderAlias("verifiUnit", "检定单位");
            bigWriter.addHeaderAlias("verifiCycle", "检定周期");
            bigWriter.addHeaderAlias("verrfiInfo", "本年检/校时间及结果");
            bigWriter.addHeaderAlias("certificate", "有效期检/校证书号");
            bigWriter.addHeaderAlias("nextVerfi", "下次检/校时间");
            bigWriter.addHeaderAlias("verfiStandard", "检定（校准）依据标准");
            bigWriter.setOnlyAlias(true);
            bigWriter.write(reportDetailVOList);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        try {
            ExcelExportUtils.exportExcelByResponse(bigWriter, response, "设备信息.xlsx", request);
        } catch (IOException e) {
            System.out.println("导出失败");
        }
    }


}



