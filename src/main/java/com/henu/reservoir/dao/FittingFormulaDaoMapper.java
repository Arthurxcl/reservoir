package com.henu.reservoir.dao;

import com.henu.reservoir.domain.FittingFormulaDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FittingFormulaDaoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FittingFormulaDao record);

    FittingFormulaDao selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(FittingFormulaDao record);

    FittingFormulaDao selectRecentlyByType(String type);

    FittingFormulaDao selectByNameAndReservoirId(FittingFormulaDao record);

    List<FittingFormulaDao> selectByTypeAndReservoirId(FittingFormulaDao record);
}