package com.lyang.dataconsumer.executor;

import com.lyang.dataconsumer.model.DataModel;
import com.lyang.dataconsumer.model.OperateModel;
import com.lyang.dataconsumer.model.OperateTypeEnum;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

/**
 * FileName: MongoService
 * Author:   lujy7
 * Date:     2020/1/15 14:50
 * Description:
 */
public abstract class MongoService<T extends DataModel> {

    public abstract void execute(T dataModel);

    protected Bson parseToFilter(OperateModel dataOperate) {
        OperateTypeEnum operateType = dataOperate.getOperateType();
        Bson filter = null;
        switch (operateType.getName()) {
            case "AND" :
                filter = Filters.and(parseToFilter(dataOperate.getLeft()), parseToFilter(dataOperate.getRight()));
                break;
            case "OR" :
                filter = Filters.or(parseToFilter(dataOperate.getLeft()), parseToFilter(dataOperate.getRight()));
                break;
            case "=" :
                filter = Filters.eq(dataOperate.getKey(), dataOperate.getValue());
                break;
            case ">" :
                filter = Filters.gt(dataOperate.getKey(), dataOperate.getValue());
                break;
            case "<" :
                filter = Filters.lt(dataOperate.getKey(), dataOperate.getValue());
                break;
        }
        return filter;
    }

}
