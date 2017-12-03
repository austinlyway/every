package com.lyways.bizcommon.business;

import com.lyways.bizcommon.business.entity.SequenceType;
import com.lyways.framework.spring.BeanLocator;
import com.lyways.tool.jdbc.adapter.mybatis.SequenceDAO;

/**
 * @author austin
 * @createDate 2017/12/03.
 */
public class SequenceGenerator {

    private static SequenceGenerator instance = new SequenceGenerator();

    private SequenceDAO sequenceDAO = BeanLocator.getBean("sequenceDAO", SequenceDAO.class);

    private SequenceGenerator(){

    }

    public static SequenceGenerator getInstance(){
        return instance;
    }

    public String generateSequence(String sequenceName, String prefix, int length){
        return sequenceDAO.selectSequence(sequenceName, prefix, length);
    }

    public String generateSequence(SequenceType sequenceType){
        return generateSequence(sequenceType.getSequenceName(), sequenceType.getPrefix(), sequenceType.getLength());
    }
}
