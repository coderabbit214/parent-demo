package com.jsh.filter;

import com.jsh.entity.InterfaceStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author mac
 */
@WebFilter
@Slf4j
public class InterfaceStatisticsFilter implements Filter {

    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST = "127.0.0.1";
    private static final String SEPARATOR = ",";

    /**
     * 主要的业务代码编写方法 <br/>
     * 记录接口的访问频次、接口的响应时间、访问的 IP<br/>
     * 然后实现日志的存储和下载功能
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("我是过滤器的执行方法，客户端向Servlet发送的请求被我拦截到了");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        //访问ip（网上考的 不知道通过网关是不是还好用）
        String ipAddress;
        try {
            ipAddress = httpRequest.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = httpRequest.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = httpRequest.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = httpRequest.getRemoteAddr();
                if (LOCALHOST.equals(ipAddress)) {
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            // "***.***.***.***".length()
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(SEPARATOR) > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        log.info(ipAddress);
        //请求路径
        String requestUri = httpRequest.getRequestURI();
        log.info(requestUri);
        Date bDate = new Date();
        filterChain.doFilter(servletRequest, servletResponse);
        Date aDate = new Date();
        //请求时间 单位：毫秒
        long l = aDate.getTime() - bDate.getTime();
        log.info(l + "耗秒");
        //信息持久化 提升用户访问速度 发送mq消息
        log.info("发送消息");
        InterfaceStatistics interfaceStatistics = new InterfaceStatistics();
        interfaceStatistics.setIp(ipAddress);
        interfaceStatistics.setDate(l);
        interfaceStatistics.setUri(requestUri);

        System.out.println("我是过滤器的执行方法，Servlet向客户端发送的响应被我拦截到了");

    }

    @Async
    void saveInterfaceStatistics(InterfaceStatistics interfaceStatistics){
        //存储
    }
}