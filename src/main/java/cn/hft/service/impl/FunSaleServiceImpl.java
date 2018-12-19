package cn.hft.service.impl;

import cn.hft.dao.IFunSaleDao;
import cn.hft.dao.impl.FunSaleDaoImpl;
import cn.hft.entity.FunSale;
import cn.hft.entity.PageData;
import cn.hft.service.IFunSaleService;

import java.util.List;

public class FunSaleServiceImpl implements IFunSaleService {
    IFunSaleDao funSaleDao = new FunSaleDaoImpl();

    @Override
    public Integer findTotalCount() {
        return funSaleDao.findTotalCount();
    }

    /**
     * 查询所有列表
     * @return
     */
    @Override
    public PageData<FunSale> findAll(Integer pageNum,Integer pageSize) {
        Integer limitStart = (pageNum - 1) * pageSize + 1;
        PageData<FunSale> pageData = new PageData<>();
        pageData.setPageNum(pageNum);
        pageData.setPageSize(pageSize);
        Integer totalCount = funSaleDao.findTotalCount();
        Integer totalPageNum = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            totalPageNum++;
        }
        pageData.setTotalCount(totalCount);
        pageData.setTotalPageNum(totalPageNum);
        List<FunSale> list = funSaleDao.findAll(limitStart, pageSize);
        pageData.setFactory(list);
        return pageData;
    }
    /**
     * 根据saleId查询结果
     * @param saleID 房源ID
     * @return
     */
    @Override
    public FunSale findById(Integer saleID) {
        return funSaleDao.findById(saleID);
    }
    /**
     * 根saleID修改对对象
     * @param funSale 房源对象
     */
    @Override
    public Boolean updateByFunSale(FunSale funSale) {
        return funSaleDao.updateByFunSale(funSale);
    }
    /**
     * 添加出售房源
     * @param funSale
     */
    @Override
    public Boolean insert(FunSale funSale) {
        return funSaleDao.insert(funSale);
    }
    /**
     *根据住建Sale_id删除出售房源数据
     * @param saleID
     */
    @Override
    public Boolean deleteById(Integer saleID) {
        return funSaleDao.deleteById(saleID);
    }
}
