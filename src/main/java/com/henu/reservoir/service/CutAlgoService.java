package com.henu.reservoir.service;

import com.henu.reservoir.dao.CutAlgoDaoMapper;
import com.henu.reservoir.domain.CutAlgoDao;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class CutAlgoService {
    public List<CutAlgoDao> getAll(){
        //返回所有分割算法
        List<CutAlgoDao> list = new ArrayList<>();
        //修改这里，查询数据库
        list.add(new CutAlgoDao(1, "算法1"));
        list.add(new CutAlgoDao(2, "算法2"));
        list.add(new CutAlgoDao(3, "算法3"));
        list.add(new CutAlgoDao(4, "算法4"));
        return list;
    }

    public void process(MultipartFile file, CutAlgoDao algo){
        //处理图像
    }

    public void process(String path, CutAlgoDao algo){
        //处理图像
    }

    public void process(MultipartFile file, int algoId){
        //处理图像
    }

    public void process(String path, int algoId){
        //处理图像
    }
}
