package com.lyang.dataconsumer.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * FileName: InsertDataModel
 * Author:   lujy7
 * Date:     2019/12/31 15:41
 * Description:
 */
@Data
public class InsertDataModel extends DataModel {

    private List<Map<String, Object>> dataModified;

}
