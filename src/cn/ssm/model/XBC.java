package cn.ssm.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class XBC {
    // 数组: 限大小,限类型  集合泛型: 不限大小,限制类型  集合: 不限大小,不限类型
    public static void main(String[] args) {
        int[] ids = new int[2];
        ids[0] = 0;
        ids[1] = 1;
        for(int i:ids){
            System.out.println(i);
        }
        List<String> iList = new ArrayList<String>();
        iList.add("AAA");
        iList.add("BBB");
        for(Object o:iList){
            System.out.println(o);
        }
    }

}
