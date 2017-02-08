package com.vpiaotong.elephant.core.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * 描述信息：防sql注入的过滤器
 *
 * @author 李志永
 * @version 1.0 Created on 2016年9月21日 下午7:16:54
 * 
 */
public class AntiSqlInjectionFilter implements Filter {
    
    public void destroy() {
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void doFilter(ServletRequest args0, ServletResponse args1, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) args0;
        // 获得所有请求参数名
        Enumeration<String> params = req.getParameterNames();
        List<String> sqlList = new ArrayList<>();
        while (params.hasMoreElements()) {
            // 得到参数名
            String name = params.nextElement().toString();
            // 得到参数对应值
            String[] value = req.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
            	sqlList.add(i, value[i]);
            }
        }
        // 有sql关键字，跳转到error.html
        if (sqlValidate(sqlList)) {
            req.setAttribute("errorMess", "含有非法字符");
            args0.getRequestDispatcher("/js/errorPage.jsp").forward(args0, args1);
        } else {
            chain.doFilter(args0, args1);
        }
    }

    // 效验
    protected static boolean sqlValidate(List<String> sqlList) {
        String badStr = "and|exec|execute|insert|select|delete|update|%|chr|mid|master|truncate|"
                + "char|declare|sitename|net user|xp_cmdshell|or|like'|create|drop|"
                + "table|from|grant|use|group_concat|column_name|"
                + "information_schema.columns|table_schema|union|where|order|by|count|"
                + "chr|mid|like|%|#|trim";// 过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
			for (int j = 0; j < sqlList.size(); j++) {
				String str = sqlList.get(j).toLowerCase();// 统一转为小写
				if (str.equals(badStrs[i]) && str.indexOf(badStrs[i]) >= 0) {
					return true;
				}
			}
		}
        return false;
    }
}