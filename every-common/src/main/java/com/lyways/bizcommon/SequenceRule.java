package com.lyways.bizcommon;

import com.lyways.bizcommon.business.entity.SequenceType;

/**
 * @author austin
 * @createDate 2017/12/03.
 */
public enum SequenceRule implements SequenceType{
    USER_ID("USER", "U", 6), ACCOUNT_ID("ACCOUNT_ID", "AC", 6);

    SequenceRule(String sequenceName, String prefix, int length){
        this.sequenceName = sequenceName;
        this.prefix = prefix;
        this.length = length;
    }

    private String sequenceName;

    private String prefix;

    private int length;

    @Override
    public String getSequenceName() {
        return this.sequenceName;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public int getLength() {
        return this.length;
    }
}
