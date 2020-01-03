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
    private CutAlgoDaoMapper cutAlgoDaoMapper;

    @Autowired
    private void setCutAlgoService(CutAlgoDaoMapper cutAlgoDaoMapper){
        this.cutAlgoDaoMapper = cutAlgoDaoMapper;
    }

    public List<CutAlgoDao> getAll(){
        //返回所有分割算法
        List<CutAlgoDao> list = cutAlgoDaoMapper.selectAll();
        //修改这里，查询数据库

        return list;
    }

    public boolean process(MultipartFile file, CutAlgoDao algo){
        //处理图像
        return true;
    }

    public boolean process(String path, CutAlgoDao algo){
        //处理图像
        return true;
    }

    public boolean process(MultipartFile file, int algoId){
        //处理图像
        return true;
    }

    public boolean process(String path, int algoId){
        //处理图像
        return true;
    }
}
