package com.red.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * jsp形式导出excel
 * @author Red
 */
public class ExcelAction extends ActionSupport {
    private static final long serialVersionUID = -3673769122296267756L;

    protected HttpServletRequest request = ServletActionContext.getRequest();
    protected HttpServletResponse response = ServletActionContext.getResponse();

    public void getXls() throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("<table><tr><td>用户名称</td><td>邮箱地址</td></tr>");
        Map<String, String> map = new HashMap<String, String>();
        map.put("red1", "it_red@sina.com");
        map.put("red2", "it_red@sohu.com");
        map.put("red3", "it_red@163.com");
        for (String key : map.keySet()) {
            sb.append("<tr><td>").append(key).append("</td><td>").append(map.get(key)).append("</td></tr>");
        }
        request.getSession().setAttribute("excel", sb.toString());
        response.sendRedirect(request.getContextPath() + "/export.jsp");
    }
}
