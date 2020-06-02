package com.csxs.letterbook.entity;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/15
 * description:
 */
public class GoodsSpec {

    /**
     * "id":168,
     * "specificationName":"温度"
     */
    private List<SpecListE> specPList;
    private int id;
    private String specificationName;

    public List<SpecListE> getSpecPList() {
        return specPList;
    }

    public void setSpecPList(List<SpecListE> specPList) {
        this.specPList = specPList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }
}
