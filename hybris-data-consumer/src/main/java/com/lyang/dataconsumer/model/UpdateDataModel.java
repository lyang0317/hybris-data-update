package com.lyang.dataconsumer.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * FileName: UpdateDataModel
 * Author:   lujy7
 * Date:     2019/12/31 15:42
 * Description:
 */
@Data
public class UpdateDataModel extends DataModel {

    private List<Map<String, Object>> dataModified;
    private OperateModel dataOperate;

}
