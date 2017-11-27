package com.red.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 第三方插件导出Excel
 * @author Red
 */
public class WriteExcelAction extends ActionSupport {
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public void writeExcel() throws RowsExceededException, WriteException, IOException {
        OutputStream os = response.getOutputStream();// 取得输出流
        response.reset();// 清空输出流
        response.setHeader("Content-disposition", "attachment; filename=testRed.xls");// 设定输出文件头
        response.setContentType("application/msexcel");// 定义输出类型

        WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
        String tmptitle = "测试数据"; // 标题
        WritableSheet wsheet = wbook.createSheet(tmptitle, 0); // sheet名称

        // 设置excel标题
        WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
                                              UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        WritableCellFormat wcfFC = new WritableCellFormat(wfont);
        wcfFC.setBackground(Colour.AQUA);
        wsheet.addCell(new Label(1, 0, tmptitle, wcfFC));
        wfont = new jxl.write.WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,
                                           UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        wcfFC = new WritableCellFormat(wfont);

        // 开始生成主体内容
        wsheet.addCell(new Label(0, 2, "姓名"));
        wsheet.addCell(new Label(1, 2, "邮箱"));
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Map<String, String> map = new HashMap<String, String>();
        map.put("Red1", "it_red@sina.com");
        map.put("Red2", "it_red@sohu.com");
        map.put("Red3", "it_red@163.com");
        int count = 0;
        for (String key : map.keySet()) {
            wsheet.addCell(new Label(0, count + 3, key));
            wsheet.addCell(new Label(1, count + 3, map.get(key)));
            count++;
        }

        // 主体内容生成结束
        wbook.write(); // 写入文件
        wbook.close();
        os.close(); // 关闭流
    }
}
