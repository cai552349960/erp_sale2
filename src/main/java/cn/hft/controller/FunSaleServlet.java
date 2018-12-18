package cn.hft.controller;

import cn.hft.entity.FunSale;
import cn.hft.entity.PageData;
import cn.hft.entity.Result;
import cn.hft.service.IFunSaleService;
import cn.hft.service.impl.FunSaleServiceImpl;
import cn.hft.utils.InsertToXml;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/funSale/*")
public class FunSaleServlet extends BaseServlet {
   

    private IFunSaleService funSaleService = new FunSaleServiceImpl();

    public void findAll(HttpServletRequest request,HttpServletResponse response ) throws IOException {
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 20;
        }
        PageData<FunSale> pageData = funSaleService.findAll(pageNum, pageSize);
        writeValue(pageData, response);
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
        BufferedReader br = request.getReader();

try {
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
    String[] split = wholeStr.split("&");
    Map<String, Object> map = new HashMap<>();
    for (int i = 0; i < split.length; i++) {
        String[] split1 = split[i].split("=");
        map.put(split1[0], split1[1]);
    }
    FunSale funSale = new FunSale();
//    funSale.setCreationTime();


            System.out.println(funSale);
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
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 20;
        }
        try {
            InsertToXml insertToXml = new InsertToXml();
            boolean flag = insertToXml.createXml(pageNum,pageSize);
            if (flag) {
                Result result = new Result(1, "生成xml文件成功，请查看D://centre.xml文件。");
                writeValue(result,response);
            } else {
                Result result = new Result(0, "对不起生成xml文件失败。");
                writeValue(request,response);
            }
        } catch (Exception e) {
            Result result = new Result(0, "对不起生成xml文件失败。");
            writeValue(result,response);
            e.printStackTrace();
        }

    }
}
