package com.zb.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFullPosition {
    public static void main(String[] args) {
        /**
         *   集合列表中，当前点和前一个点的差值大于>=`1且小于10并且连续段小于等于5段， 自第六段起间记为不连续轨迹，轨迹恢复后清零重新计算。
         *
         */
        List<Dto> dtos = new ArrayList<Dto>();
        dtos.add(new Dto("A", 0));
        dtos.add(new Dto("B", 3));
        dtos.add(new Dto("C", 7));
        dtos.add(new Dto("D", 10));
        dtos.add(new Dto("E", 14));
        dtos.add(new Dto("F", 17));
        dtos.add(new Dto("G", 22));
        dtos.add(new Dto("H", 28));
        dtos.add(new Dto("I", 29));
        dtos.add(new Dto("J", 30));
        dtos.add(new Dto("K", 33));
        dtos.add(new Dto("L", 37));
//        List list1 = Arrays.asList(0, 3, 7, 10, 14, 17, 22, 28, 29, 30, 33, 37);
//        List list2 = Arrays.asList(2, 3, 6, 18, 26);
        //前一个点

        Integer count = 0;
        List<Dto> continuePoint = new ArrayList<Dto>();

        /**
         *       1、满足条件的第一个点和第五个点放入一个Map集合中，也就是开始点和结束点 key和value对应。
         *       2、中间需要条件去判断是上面是否成立
         *       3、成立则放入连续Map中
         *       4、反之就是放到不连续集合中
         *       A-B=3
         *       B-C=4
         *       C-D=3
         *       D-E=4
         *       E-F=3
         *       F-G=5
         *       G-H=6
         *       H-I=1
         *       I-J=1
         *       J-K=3
         *       K-L=4
         *
         */
        Dto frontPoint = null;
        Dto nextPoint = null;
        Map<Dto, Dto> continueMap = new HashMap<Dto, Dto>();
        // 创建存储队列
        List<Dto> continueQueue = new ArrayList<Dto>();
        List<Dto> disContinueQueue = new ArrayList<Dto>();
        // 现在需要解决 前一个点和后一个点  的比较   需要定位当前点
        for (int i = 0; i < dtos.size() - 1; i++) {
            if (i + 1 <= dtos.size()) {
                frontPoint = dtos.get(i);
                nextPoint = dtos.get(i + 1);
                int lagNum = nextPoint.getNum() - frontPoint.getNum();
                if (lagNum > 1 && lagNum < 10 && count < 6) {
                    continueQueue.add(frontPoint);
                    count++;
                } else {

                    if (!continueQueue.isEmpty()) {
                        Dto continueStart = continueQueue.get(0);
                        Dto  continueEnd = continueQueue.get(continueQueue.size() - 1);
                        //  continueMap.put(start,end);
                        System.out.println("连续轨迹：" + continueStart + "------" + continueEnd);
                        continueQueue.clear();
                    }

                    Dto  continueEnd = continueQueue.get(continueQueue.size() - 1);
                    disContinueQueue.clear();
                    disContinueQueue.add(continueEnd);
                    disContinueQueue.add(nextPoint);
                    if (!disContinueQueue.isEmpty()) {
                        Dto disContinueStart = disContinueQueue.get(0);
                        Dto disContinueEnd = disContinueQueue.get(disContinueQueue.size() - 1);
                        System.out.println("不连续轨迹：" + disContinueStart + "------" + disContinueEnd);
                    }
                    count = 0;

                }
            }
        }
    }


    public static List<Dtodetail> fullPosition(List<Dto> dtos) {
        int count = 0;
        Dto beginPoint = null;
        boolean isFirst = true;
        for (int i = 0; i < dtos.size(); i++) {
            if (isFirst) {
                isFirst = false;
            } else {
                Dto currentPoint = dtos.get(i);
                Integer currentNum = currentPoint.getNum();
                Integer beginNum = beginPoint.getNum();
            }
        }
        return null;
    }
}
