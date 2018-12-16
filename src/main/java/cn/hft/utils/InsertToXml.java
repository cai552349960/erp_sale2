package cn.hft.utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import cn.hft.entity.FunSale;
import cn.hft.service.impl.FunSaleServiceImpl;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;


//把从数据库中读到的数据，生成xml文件
public class InsertToXml {
    private Set<Integer> hset=null;
    /**
     * @athor centre
     * @return b xml文件生成成功返回true，否则返回false
     */
    public boolean createXml(){
        boolean b=false;
        hset = new HashSet<>();
        Document doc=DocumentHelper.createDocument();//创建document
        Element schoolEle=doc.addElement("shool");//添加根元素
        schoolEle.addComment("文档的根school已经创建。");//添加注释
        List<FunSale> al=new FunSaleServiceImpl().findAll();
        for (int i = 0; i < al.size(); i++) {
            FunSale funSale=al.get(i);
            hset.add(funSale.getSaleID());
        }
        for (Iterator<Integer> it = hset.iterator(); it.hasNext();) {
            Integer saleId=it.next();
            Element classEle=schoolEle.addElement("saleId");
            classEle.addAttribute("saleId", saleId+"");
            for (int i = 0; i < al.size(); i++) {
                FunSale funSale=al.get(i);
                if (saleId== funSale.getSaleID()) {
                    Element UserEle=classEle.addElement("funSale");
                    UserEle.addElement("saleID").addText(funSale.getSaleID()+"");
                    UserEle.addElement("saleSubject").addText(funSale.getSaleSubject()+"");
                    UserEle.addElement("buildName").addText(funSale.getBuildName()+"");
                    UserEle.addElement("tradeAddr").addText(funSale.getTradeAddr()+"");
                    UserEle.addElement("saleRoom").addText(funSale.getSaleRoom()+"");
                    UserEle.addElement("saleInnerarea").addText(funSale.getSaleInnerarea()+"");
                    UserEle.addElement("regionName").addText(funSale.getRegionName()+"");
                    UserEle.addElement("sectionName").addText(funSale.getSectionName()+"");
                    UserEle.addElement("updateTime").addText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(funSale.getUpdateTime()));
                    UserEle.addElement("saleTotalPrice").addText(funSale.getSaleTotalPrice().toString());
                    UserEle.addElement("saleUnitPrice").addText(funSale.getSaleUnitPrice().toString());
                }
            }
        }

        try {
            /*自己查帮助文档
             * OutputFormat format=new OutputFormat("  ",true,"gb2312");
             */
            /*
             * 创建一个漂亮的打印格式的OutputFormat
             * 可以通过setEncoding来设置其传输字符串，默认为utf-8
             */
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
   /*创建缩进格式的OutputFormat
   format = OutputFormat.createCompactFormat();
   */
            File file = new File("D://centre.xml");
            if (file.exists()) {
                file.createNewFile();
            }
            XMLWriter writer = new XMLWriter(new FileWriter(file),format);
            writer.write(doc);
            writer.close();
            b=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

}
