package cn.hft.dao;

import cn.hft.entity.FunSale;

import java.util.List;

public interface IFunSaleDao {
    /**
     *
     * @return 总记录数
     */
    public Integer findTotalCount();
    /**
     * 查询所有列表
     * @return
     */
    public List<FunSale> findAll(Integer pageNum,Integer pageSize);

    /**
     * 根据saleId查询结果
     * @param saleID 房源ID
     * @return
     */
    public FunSale findById(Integer saleID);

    /**
     * 根saleID修改对对象
     * @param funSale 房源对象
     */
    public Boolean updateByFunSale(FunSale funSale);

    /**
     * 添加出售房源
     * @param funSale
     */
    public Boolean insert(FunSale funSale);

    /**
     *删除出售房源数据
     * @param saleID
     */
    public Boolean deleteById(Integer saleID);

}
