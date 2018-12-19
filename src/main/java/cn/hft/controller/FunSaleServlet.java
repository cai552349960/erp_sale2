package cn.hft.controller;

import cn.hft.entity.FunSale;
import cn.hft.entity.PageData;
import cn.hft.entity.Result;
import cn.hft.service.IFunSaleService;
import cn.hft.service.impl.FunSaleServiceImpl;
import cn.hft.utils.InsertToXml;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/funSale/*")
public class FunSaleServlet extends BaseServlet {
    private static boolean flag = true;
   

    private IFunSaleService funSaleService = new FunSaleServiceImpl();

    public void findAll(HttpServletRequest request,HttpServletResponse response ) throws IOException {

        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        if (flag) {
            
            Integer totalCount = funSaleService.findTotalCount();
            InsertToXml insertToXml1 = new InsertToXml();
            insertToXml1.createXml(1,totalCount);
            InsertToXml insertToXml = new InsertToXml();
            boolean fg = insertToXml.createXml(1, totalCount);
            if (fg) {
                flag = false;
            }

        }
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
        String saleId = request.getParameter("saleId");
        Integer saleIdInteger = Integer.parseInt(saleId);
        FunSale funSale = funSaleService.findById(saleIdInteger);
        writeValue(funSale, response);
    }

    public void updateByFunSale( HttpServletRequest request,HttpServletResponse response) throws IOException {
        Result result = null;
        try {
            BufferedReader br = request.getReader();
            String str, wholeStr = "";
            while((str = br.readLine()) != null){
                wholeStr += str;
            }

            ObjectMapper mapper = new ObjectMapper();
            FunSale funSale = mapper.readValue(wholeStr, FunSale.class);
            funSale.setUpdateTime(new Timestamp(new Date().getTime()));
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
    ObjectMapper mapper = new ObjectMapper();
    FunSale funSale = mapper.readValue(wholeStr, FunSale.class);
    funSale.setCreationTime(new Timestamp(new Date().getTime()));
    funSale.setUpdateTime(new Timestamp(new Date().getTime()));

//    funSale.setCreationTime();


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
            String saleID = request.getParameter("saleId");
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

    public HttpServletResponse  createXml(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try {
            // path是指欲下载的文件的路径。
            File file = new File("F:\\IdeaProjects\\erp_sale2\\src\\main\\webapp\\funSaleAll.xml");
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;

    }
}
