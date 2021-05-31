package com.zb.test;

public class Dto {
    private String label;
    private Integer num;

    public Dto() {
    }

    public Dto(String label, Integer num) {
        this.label = label;
        this.num = num;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Dto{" +
                "label='" + label + '\'' +
                ", num=" + num +
                '}';
    }
}
