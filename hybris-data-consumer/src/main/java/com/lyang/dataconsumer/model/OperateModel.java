package com.lyang.dataconsumer.model;

import lombok.Data;

/**
 * FileName: OperateModel
 * Author:   lujy7
 * Date:     2019/12/31 15:58
 * Description:
 */
@Data
public class OperateModel {

    private OperateModel left;
    private OperateModel right;
    private OperateTypeEnum operateType;
    private String key;
    private Object value;

    public OperateModel() {}

}
