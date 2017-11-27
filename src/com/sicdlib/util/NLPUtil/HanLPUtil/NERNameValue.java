package com.sicdlib.util.NLPUtil.HanLPUtil;

/**
 * Created by DeMH on 2017/7/17.
 */
public class NERNameValue {
    //名称, 如‘吴连伟’
    private String name;
    //类别, 如‘人名’
    private String category;
    //权重, 如‘5’
    private Integer value;

    public NERNameValue() {
    }

    public NERNameValue(String name, String category) {
        this.name = name;
        this.category = category;
        //人名
        if (this.category.equals("person")) {
            this.value = 5;
        }
        //地名
        if (this.category.equals("place")){
            this.value = 3;
        }
        //组织名
        if (this.category.equals("org")){
            this.value = 2;
        }
        //一般词
        if (this.category.equals("normal")){
            this.value = 1;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
