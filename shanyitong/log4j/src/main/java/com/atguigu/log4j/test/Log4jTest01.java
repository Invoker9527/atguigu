package com.atguigu.log4j.test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @Author YangRuiHong
 * @Create 2023-03-03 23:06
 * @Description
 */
public class Log4jTest01 {
    @Test
    public void test01() {
        /**
         * log4j入门案例
         * 注意加载初始化信息： BasicConfigurator.configure();
         * 日志级别说明：
         *  log4j提供了8个级别的日志输出，分别为：
         *  all最低级别 用于打开所有级别的日志
         *  trace程序推进下的追踪信息，这个追踪信息的日志级别非常低，一般情况下是不会使用的
         *  debug 指出细粒度信息事件对调试应用程序是非常有帮助的，主要是配合开发，在开发过程中打印一些重要的运行信息
         *  info 消息在粗粒度级别运行信息
         *  warn 表示警告，程序在运行过程中有可能会出现的隐形的错误，注意，有些信息不是错误，但这个级别的输出目的是为了给程序员以提示
         *  error系统的错误信息，发生的错误不会影响系统的运行，一般情况下，如果不想输出太多的日志，则使用该级别即可
         *  fatal 表示严重错误，他是那种一旦发生系统就不可能继续运行的严重错误，如果这种级别的错误出现了，表示程序可以停止运行了
         *  off最高级别，用于关闭所有日志输出
         *  其中debug是我们在没有设置的情况下默认的日志级别
         */
        //加载初始化配置

        Logger logger = Logger.getLogger(Log4jTest01.class);
        logger.fatal("fatal信息");
        logger.error("error信息");
        logger.warn("warn信息");
        logger.info("info信息");
        logger.debug("debug信息");
        logger.trace("trace信息");

    }

    @Test
    public void test02() {
        /**
         * 配置文件的使用
         *      1.观察源码BasicConfigurator.configure();
         *      可以得到两条信息：
         *          （1）.创建了根节点的对象，  Logger root = Logger.getRootLogger();
         *          (2).根节点添加了ConsoleAppender对象（默认打印到控制台，自定义的格式化输出 new PatternLayout("%r [%t] %p %c %x - %m%n")）
         *      2.我们这一次不使用 BasicConfigurator.configure();
         *      使用自定义配置文件来实现功能
         *      通过我们对以上第一点的分析
         *      我们的配置文件需要提供logger，appender、layout这三个组件信息（通过配置来代替以上代码）
         *
         *     log4j.properties的加载时机
         *     在static中找到    url = Loader.getResource("log4j.properties");这行代码告诉我们系统默认从当前类路路径下找到log4j。properties，maven工程的话从resource里找
         */

        Logger logger = Logger.getLogger(Log4jTest01.class);
        logger.fatal("fatal信息");
        logger.error("error信息");
        logger.warn("warn信息");
        logger.info("info信息");
        logger.debug("debug信息");
        logger.trace("trace信息");
    }
}
