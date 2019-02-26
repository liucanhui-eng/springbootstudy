package com.util;

import java.util.List;

public class CollectionUtils{


    /** 
    * @Description: listCopy 复制集合的一段数据出来
    * @Param: [rList 源数据, tList 目标数据, startIndex 开始下标, endIndex结束下标]
    * @return: java.util.List<T> 
    * @Author: liucanhui 
    * @Date: 2019/2/26 
    */ 
    public static <T> void listCopy(List<T> rList , List<T> tList,int startIndex,int endIndex)throws Exception{
        if(startIndex<0||startIndex>endIndex||endIndex>rList.size()+1){
            throw new Exception("下标不正确");
        }
        for (int i=startIndex;i<=endIndex;i++){
            T t=rList.get(i);
            tList.add(t);
        }
    }
}
