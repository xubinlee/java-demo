package com.ex.framework.database;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@ApiModel(value = "数据分页对象")
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Page<T> implements Serializable {

    @ApiModelProperty(value = "列表数据")
    private List<T> data;

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码")
    private Integer pageNo;


    /**
     * 分页大小
     */
    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;

    /**
     * 总计录数
     */
    @ApiModelProperty(value = "总计录数")
    private Long dataTotal;


    /**
     * 构造方法
     *
     * @param data      数据列表
     * @param pageNo    当前页码
     * @param pageSize  页大小
     * @param dataTotal 总计录数
     */
    public Page(Integer pageNo, Long dataTotal, Integer pageSize, List<T> data) {
        this.data = data;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.dataTotal = dataTotal;
    }

    public Page(){}

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getDataTotal() {
        return dataTotal;
    }

    public void setDataTotal(Long dataTotal) {
        this.dataTotal = dataTotal;
    }

    @Override
    public String toString() {
        return "Page{" +
                "data=" + data +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", dataTotal=" + dataTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page<?> page = (Page<?>) o;
        return Objects.equals(data, page.data) &&
                Objects.equals(pageNo, page.pageNo) &&
                Objects.equals(pageSize, page.pageSize) &&
                Objects.equals(dataTotal, page.dataTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, pageNo, pageSize, dataTotal);
    }
}
