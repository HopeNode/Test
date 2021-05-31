package com.zb.test;

public class Dtodetail {
    private Dto headPoint;
    private Dto endPoint;

    public Dtodetail(Dto headPoint, Dto endPoint) {
        this.headPoint = headPoint;
        this.endPoint = endPoint;
    }

    public Dto getHeadPoint() {
        return headPoint;
    }

    public void setHeadPoint(Dto headPoint) {
        this.headPoint = headPoint;
    }

    public Dto getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Dto endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        return "Dtodetail{" +
                "headPoint=" + headPoint +
                ", endPoint=" + endPoint +
                '}';
    }
}
