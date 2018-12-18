package cn.hft.dao.impl;

import cn.hft.dao.IFunSaleDao;
import cn.hft.entity.FunSale;
import cn.hft.utils.JDBCUtils;
import com.alibaba.fastjson.JSON;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunSaleDaoImpl implements IFunSaleDao {

    @Override
    public Integer findTotalCount() {

        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        Integer totalCount = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select count(1) totalCount from [FUN_SALE]";
            pst = connection.prepareStatement(sql);
            resultSet = pst.executeQuery();
            while (resultSet.next()) {
                totalCount = (Integer) resultSet.getObject("totalCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, pst, connection);
        }
        return totalCount;
    }

    @Override
    public List<FunSale> findAll(Integer pageNum, Integer pageSize) {
        List<Map<String, Object>> returnResultToList = null;
        List<FunSale> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select top "+pageSize+" SALE_ID, SALE_SUBJECT ,BUILD_NAME, TRADE_ADDR, SALE_ROOM, SALE_INNERAREA, REGION_NAME, SECTION_NAME, UPDATE_TIME," +
                    " SALE_TOTAL_PRICE, SALE_UNIT_PRICE from [FUN_SALE] where SALE_ID not in (select top "+pageNum+" SALE_ID from [FUN_SALE] order by SALE_ID) order by UPDATE_TIME desc" ;
            pst = connection.prepareStatement(sql);
            resultSet = pst.executeQuery();
            returnResultToList = returnResultToList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, pst, connection);
        }
        for (int i = 0; i < returnResultToList.size(); i++) {
            Map<String, Object> funSaleMap = returnResultToList.get(i);
            FunSale funSale = JSON.parseObject(JSON.toJSONString(funSaleMap), FunSale.class);
            list.add(funSale);
        }
        return list;
    }

    @Override
    public FunSale findById(Integer saleID) {
        List<Map<String, Object>> list = null;
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select SALE_ID, SALE_SUBJECT ,BUILD_NAME, TRADE_ADDR, SALE_ROOM, SALE_INNERAREA, REGION_NAME, SECTION_NAME, UPDATE_TIME, SALE_TOTAL_PRICE, SALE_UNIT_PRICE ,SHARE_FLAG from [FUN_SALE] where SALE_ID=?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, saleID);
            resultSet = pst.executeQuery();
            list = returnResultToList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, pst, connection);
        }
        FunSale funSale = JSON.parseObject(JSON.toJSONString(list.get(0)), FunSale.class);
        return funSale;
    }


    @Override
    public Boolean updateByFunSale(FunSale funSale) {
        Boolean flag = true;
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "update [FUN_SALE] set  SALE_SUBJECT=?,BUILD_NAME=?,TRADE_ADDR=?,SALE_ROOM=?,SALE_INNERAREA=?,REGION_NAME=?,SECTION_NAME=?,UPDATE_TIME=?,SALE_TOTAL_PRICE=?,SALE_UNIT_PRICE=? wherer SALE_ID=? ";
            pst = connection.prepareStatement(sql);
            pst.setString(1, funSale.getSaleSubject());
            pst.setString(2, funSale.getBuildName());
            pst.setString(3, funSale.getTradeAddr());
            pst.setInt(4, funSale.getSaleRoom());
            pst.setBigDecimal(5, funSale.getSaleInnerarea());
            pst.setString(6, funSale.getRegionName());
            pst.setDate(7, (Date) funSale.getUpdateTime());
            pst.setBigDecimal(8, funSale.getSaleTotalPrice());
            pst.setBigDecimal(9, funSale.getSaleUnitPrice());
            int i = pst.executeUpdate();
            if (i == 0) {
                flag = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.close(resultSet, pst, connection);
        }
        return flag;
    }


    @Override
    public Boolean insert(FunSale funSale) {
        Boolean flag = true;
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into [FUN_SALE](SALE_ID,COMP_ID,CITY_ID,DEPT_ID,CREATION_TIME,SALE_NO,SALE_USEAGE,SALE_SUBJECT" +
                    ",NUMERIC,SALE_SOURCE,SALE_EXPLRTH,BUILD_NAME,TRADE_ADDR,SALE_ROOM,SALE_INNERAREA,REGION_NAME,SECTION_NAME," +
                    "UPDATE_TIME,SALE_TOTAL_PRICE,SALE_UNIT_PRICE,SALE_CONSIGN,SALE_MAP,PLATE_TYPE,SALE_STATUS,INFO_TYPE,SHARE_FLAG," +
                    "RED_FLAG,FROM_PUBLIC,SALE_ID_OLD,HOUSE_BARGAIN,PANORAMA_MAP,YOUYOU_DEAL,IS_SALE_LEASE,HOUSE_SITUATION,OLD_TRUE_FLAG)" +
                    " VALUES("+"NEXT VALUE  FOR SEQ_FUN_SALE__SALE_ID,"+"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, funSale.getCompID());
            pst.setInt(2, funSale.getCityID());
            pst.setInt(3, funSale.getDeptID());
            pst.setDate(4, (Date) funSale.getCreationTime());
            pst.setString(5, funSale.getSaleNo());
            pst.setInt(6, funSale.getSaleUseage());
            pst.setString(7, funSale.getSaleSubject());
            pst.setBigDecimal(8, funSale.getNumeric());
            pst.setInt(9, funSale.getSaleSource());
            pst.setInt(10, funSale.getSaleExplrth());
            pst.setString(11, funSale.getBuildName());
            pst.setString(12, funSale.getTradeAddr());
            pst.setInt(13, funSale.getSaleRoom());
            pst.setBigDecimal(14, funSale.getSaleInnerarea());
            pst.setString(15, funSale.getRegionName());
            pst.setString(16, funSale.getSectionName());
            pst.setDate(17, (Date) funSale.getUpdateTime());
            pst.setBigDecimal(18, funSale.getSaleTotalPrice());
            pst.setBigDecimal(19, funSale.getSaleUnitPrice());
            pst.setInt(20, funSale.getSaleConsign());
            pst.setInt(21, funSale.getSaleMap());
            pst.setInt(22, funSale.getPlateType());
            pst.setInt(23, funSale.getSaleStatus());
            pst.setInt(24, funSale.getInfoType());
            pst.setBoolean(25, funSale.getShareFlag());
            pst.setBoolean(26, funSale.getRedFlag());
            pst.setBoolean(27, funSale.getFromPublic());
            pst.setInt(28, funSale.getSaleIdOld());
            pst.setBoolean(29, funSale.getHouseBargain());
            pst.setInt(30, funSale.getPanoramaMap());
            pst.setInt(31, funSale.getYouyouDeal());
            pst.setInt(32, funSale.getIsSaleLease());
            pst.setInt(33, funSale.getHouseSituation());
            pst.setInt(34, funSale.getOldTrueFlag());


            int i = pst.executeUpdate();
            if (i == 0) {
                flag = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.close(resultSet, pst, connection);
        }
        return flag;
    }

    @Override
    public Boolean deleteById(Integer saleID) {
        Boolean flag = true;
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete from [FUN_SALE] where SALE_ID=?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, saleID);
            int i = pst.executeUpdate();
            if (i == 0) {
                flag = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.close(resultSet, pst, connection);
        }
        return flag;

    }

    /**
     *  * 数据返回集合  * @param resultSet  * @return  * @throws SQLException  
     */
    public static List<Map<String, Object>> returnResultToList(ResultSet resultSet) {
        List<Map<String, Object>> values = null;
        try {
            // 键: 存放列的别名, 值: 存放列的值.
            values = new ArrayList<>();
            // 存放字段名
            List<String> columnName = new ArrayList<>();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // 字段名
                columnName.add(rsmd.getColumnLabel(i + 1));
            }

            System.out.println("表字段为：");
            System.out.println(columnName);
            System.out.println("表数据为：");
            Map<String, Object> map = null;
            // 处理 ResultSet, 使用 while 循环
            while (resultSet.next()) {
                map = new HashMap<>();
                for (String column : columnName) {
                    Object value = resultSet.getObject(column);
                    map.put(column, value);
                    System.out.print(value + "\t");
                }
                // 把一条记录的 Map 对象放入准备的 List 中
                values.add(map);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("异常提醒：" + e);
        }
        return values;
    }

}