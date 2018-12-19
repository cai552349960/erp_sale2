package cn.hft.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 代表房源出租对象与数据库的FUN_SALE对应
 */
public class FunSale implements Serializable {
    private Integer saleID;//房源ID，自增序列
    private Integer compID;//公司ID
    private Integer cityID;//    城市ID
    private Integer deptID;//分店ID
    private Timestamp creationTime;//登记时间
    private String saleNo;//系统编码
    private Integer saleUseage;//房屋用途
    private String saleSubject;//房源标题
    private BigDecimal saleArea;//建筑面积
    private Integer saleSource;//信息来源
    private Integer saleExplrth;//房勘，1=已勘0=未勘
    private String buildName;//楼盘名称
    private String tradeAddr;//商铺、厂房、车库、仓库：地址
    private Integer saleRoom;//几房/几室
    private BigDecimal saleInnerarea;//套内面积
    private String regionName;//行政区名称，区域名称
    private String sectionName;//片区名称，板块名称
    private Timestamp updateTime;//最后更新时间
    private BigDecimal saleTotalPrice;//售价
    private BigDecimal saleUnitPrice;//单价
    private Integer saleConsign;//委托
    private Integer saleMap;//图片张数，默认=0
    private Integer saleLevel;//级别
    private Integer plateType;//盘别
    private Integer saleStatus;//房屋状态
    private Integer infoType;//房源类型
    private Boolean shareFlag;//精英版登记
    private Boolean redFlag;//红色警示
    private Boolean fromPublic;//是否转自抢盘
    private Integer saleIdOld;//老版center库对应的SALE_ID，Def: 0
    private Boolean houseBargain;//0:未议价1:已议价，DEF:0
    private Integer panoramaMap;//720相机图片有多少张
    private Integer youyouDeal;//优优排除成交的推荐数据，0：未成交、1：成交 - 陈文超-2016-10-01
    private Integer isSaleLease;//是否是租售房源，1=是，默认=0=否
    private Integer houseSituation;//房屋现状
    private Integer oldTrueFlag;//老的真房源标签

    @Override
    public String toString() {
        return "FunSale{" +
                "saleID=" + saleID +
                ", compID=" + compID +
                ", cityID=" + cityID +
                ", deptID=" + deptID +
                ", creationTime=" + creationTime +
                ", saleNo='" + saleNo + '\'' +
                ", saleUseage=" + saleUseage +
                ", saleSubject='" + saleSubject + '\'' +
                ", saleArea=" + saleArea +
                ", saleSource=" + saleSource +
                ", saleExplrth=" + saleExplrth +
                ", buildName='" + buildName + '\'' +
                ", tradeAddr='" + tradeAddr + '\'' +
                ", saleRoom=" + saleRoom +
                ", saleInnerarea=" + saleInnerarea +
                ", regionName='" + regionName + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", updateTime=" + updateTime +
                ", saleTotalPrice=" + saleTotalPrice +
                ", saleUnitPrice=" + saleUnitPrice +
                ", saleConsign=" + saleConsign +
                ", saleMap=" + saleMap +
                ", saleLevel=" + saleLevel +
                ", plateType=" + plateType +
                ", saleStatus=" + saleStatus +
                ", infoType=" + infoType +
                ", shareFlag=" + shareFlag +
                ", redFlag=" + redFlag +
                ", fromPublic=" + fromPublic +
                ", saleIdOld=" + saleIdOld +
                ", houseBargain=" + houseBargain +
                ", panoramaMap=" + panoramaMap +
                ", youyouDeal=" + youyouDeal +
                ", isSaleLease=" + isSaleLease +
                ", houseSituation=" + houseSituation +
                ", oldTrueFlag=" + oldTrueFlag +
                '}';
    }

    public Integer getSaleID() {
        return saleID;
    }

    public void setSaleID(Integer saleID) {
        this.saleID = saleID;
    }

    public Integer getCompID() {
        return compID;
    }

    public void setCompID(Integer compID) {
        this.compID = compID;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    public Integer getSaleUseage() {
        return saleUseage;
    }

    public void setSaleUseage(Integer saleUseage) {
        this.saleUseage = saleUseage;
    }

    public String getSaleSubject() {
        return saleSubject;
    }

    public void setSaleSubject(String saleSubject) {
        this.saleSubject = saleSubject;
    }

    public BigDecimal getSaleArea() {
        return saleArea;
    }

    public void setSaleArea(BigDecimal saleArea) {
        this.saleArea = saleArea;
    }

    public Integer getSaleSource() {
        return saleSource;
    }

    public void setSaleSource(Integer saleSource) {
        this.saleSource = saleSource;
    }

    public Integer getSaleExplrth() {
        return saleExplrth;
    }

    public void setSaleExplrth(Integer saleExplrth) {
        this.saleExplrth = saleExplrth;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getTradeAddr() {
        return tradeAddr;
    }

    public void setTradeAddr(String tradeAddr) {
        this.tradeAddr = tradeAddr;
    }

    public Integer getSaleRoom() {
        return saleRoom;
    }

    public void setSaleRoom(Integer saleRoom) {
        this.saleRoom = saleRoom;
    }

    public BigDecimal getSaleInnerarea() {
        return saleInnerarea;
    }

    public void setSaleInnerarea(BigDecimal saleInnerarea) {
        this.saleInnerarea = saleInnerarea;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getSaleTotalPrice() {
        return saleTotalPrice;
    }

    public void setSaleTotalPrice(BigDecimal saleTotalPrice) {
        this.saleTotalPrice = saleTotalPrice;
    }

    public BigDecimal getSaleUnitPrice() {
        return saleUnitPrice;
    }

    public void setSaleUnitPrice(BigDecimal saleUnitPrice) {
        this.saleUnitPrice = saleUnitPrice;
    }

    public Integer getSaleConsign() {
        return saleConsign;
    }

    public void setSaleConsign(Integer saleConsign) {
        this.saleConsign = saleConsign;
    }

    public Integer getSaleMap() {
        return saleMap;
    }

    public void setSaleMap(Integer saleMap) {
        this.saleMap = saleMap;
    }

    public Integer getSaleLevel() {
        return saleLevel;
    }

    public void setSaleLevel(Integer saleLevel) {
        this.saleLevel = saleLevel;
    }

    public Integer getPlateType() {
        return plateType;
    }

    public void setPlateType(Integer plateType) {
        this.plateType = plateType;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public Boolean getShareFlag() {
        return shareFlag;
    }

    public void setShareFlag(Boolean shareFlag) {
        this.shareFlag = shareFlag;
    }

    public Boolean getRedFlag() {
        return redFlag;
    }

    public void setRedFlag(Boolean redFlag) {
        this.redFlag = redFlag;
    }

    public Boolean getFromPublic() {
        return fromPublic;
    }

    public void setFromPublic(Boolean fromPublic) {
        this.fromPublic = fromPublic;
    }

    public Integer getSaleIdOld() {
        return saleIdOld;
    }

    public void setSaleIdOld(Integer saleIdOld) {
        this.saleIdOld = saleIdOld;
    }

    public Boolean getHouseBargain() {
        return houseBargain;
    }

    public void setHouseBargain(Boolean houseBargain) {
        this.houseBargain = houseBargain;
    }

    public Integer getPanoramaMap() {
        return panoramaMap;
    }

    public void setPanoramaMap(Integer panoramaMap) {
        this.panoramaMap = panoramaMap;
    }

    public Integer getYouyouDeal() {
        return youyouDeal;
    }

    public void setYouyouDeal(Integer youyouDeal) {
        this.youyouDeal = youyouDeal;
    }

    public Integer getIsSaleLease() {
        return isSaleLease;
    }

    public void setIsSaleLease(Integer isSaleLease) {
        this.isSaleLease = isSaleLease;
    }

    public Integer getHouseSituation() {
        return houseSituation;
    }

    public void setHouseSituation(Integer houseSituation) {
        this.houseSituation = houseSituation;
    }

    public Integer getOldTrueFlag() {
        return oldTrueFlag;
    }

    public void setOldTrueFlag(Integer oldTrueFlag) {
        this.oldTrueFlag = oldTrueFlag;
    }
}
