package cn.hft.controller;

import cn.hft.entity.FunSale;
import cn.hft.entity.Result;
import cn.hft.service.IFunSaleService;
import cn.hft.service.impl.FunSaleServiceImpl;
import cn.hft.utils.InsertToXml;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/funSale/*")
public class FunSaleServlet extends BaseServlet {
   

    private IFunSaleService funSaleService = new FunSaleServiceImpl();

    public void findAll(HttpServletRequest request,HttpServletResponse response ) throws IOException {
        List<FunSale> funSales = funSaleService.findAll();
        writeValue(funSales, response);
    }

    public void findBySaleId(HttpServletRequest request,HttpServletResponse response ) throws IOException {
        String saleId = request.getParameter("saleID");
        Integer saleIdInteger = Integer.parseInt(saleId);
        System.out.println(saleIdInteger);
        FunSale funSale = funSaleService.findById(saleIdInteger);
        writeValue(funSale, response);
    }

    public void updateByFunSale( HttpServletRequest request,HttpServletResponse response) throws IOException {
        Result result = null;
        try {
            Enumeration<String> enu = request.getParameterNames();
            StringBuffer json = new StringBuffer("{");
            while (enu.hasMoreElements()) {
                String paraName = (String) enu.nextElement();
                String parameterValue = request.getParameter(paraName);
                json.append("\"").append(paraName).append("\"").append(":").append("\"").append(parameterValue).append("\"").append(",");
            }
            String substring = json.substring(0, json.length());
            substring = substring + "}";
            ObjectMapper mapper = new ObjectMapper();
            FunSale funSale = mapper.readValue(substring, FunSale.class);
            Boolean flag = funSaleService.updateByFunSale(funSale);
            if (flag) {
                result = new Result(111, "修改成功");
                writeValue(result, response);
            } else {
                result = new Result(000, "修改失败");
                writeValue(result, response);
            }

        } catch (IOException e) {
            e.printStackTrace();
            result = new Result(000, "修改失败");
            writeValue(result, response);
        }


    }

    public void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = null;
        try {
            Enumeration<String> enu = request.getParameterNames();
            StringBuffer json = new StringBuffer("{");
            while (enu.hasMoreElements()) {
                String paraName = (String) enu.nextElement();
                String parameterValue = request.getParameter(paraName);
                json.append("\"").append(paraName).append("\"").append(":").append("\"").append(parameterValue).append("\"").append(",");
            }
            String substring = json.substring(0, json.length());
            substring = substring + "}";
            ObjectMapper mapper = new ObjectMapper();
            FunSale funSale = mapper.readValue(substring, FunSale.class);
            Boolean flag = funSaleService.insert(funSale);
            if (flag) {

                result = new Result(111, "添加成功");
                writeValue(result, response);
            } else {
                result = new Result(000, "添加失败");
                writeValue(result, response);
            }

        } catch (IOException e) {
            e.printStackTrace();
            result = new Result(000, "添加失败");
            writeValue(result, response);
        }

    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = null;
        try {
            String saleID = request.getParameter("saleID");
            Integer saleId = Integer.parseInt(saleID);
            Boolean flag = funSaleService.deleteById(saleId);
            if (flag) {

                result = new Result(111, "删除成功");
                writeValue(result, response);
            } else {
                result = new Result(000, "删除失败");
                writeValue(result, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(000, "删除失败");
            writeValue(result, response);
        }
    }

    public void createXml(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            InsertToXml insertToXml = new InsertToXml();
            boolean flag = insertToXml.createXml();
            if (flag) {
                Result result = new Result(1, "生成xml文件成功，请查看D://centre.xml文件。");
                writeValue(request,response);
            } else {
                Result result = new Result(0, "对不起生成xml文件失败。");
                writeValue(request,response);
            }
        } catch (Exception e) {
            Result result = new Result(0, "对不起生成xml文件失败。");
            writeValue(request,response);
            e.printStackTrace();
        }

    }
}
