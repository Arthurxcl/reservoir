package com.henu.reservoir.service;

import com.henu.reservoir.domain.CutAlgoDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CutAlgoService {
    public List<CutAlgoDao> getAll(){
        //获取所有分割算法
        List<CutAlgoDao> list = new ArrayList<>();
        //这里改成查询数据库
        list.add(new CutAlgoDao(1, "算法1"));
        list.add(new CutAlgoDao(2, "算法2"));
        list.add(new CutAlgoDao(3, "算法3"));
        list.add(new CutAlgoDao(4, "算法4"));
        return list;
    }

    public boolean process(String path, int algoId){
        //使用算法处理图像
        return true;
    }

    public boolean process(String path, CutAlgoDao algoDao){
        //使用算法处理图像
        return true;
    }

}
