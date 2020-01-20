package com.lyang.dataconsumer.executor;

import com.lyang.dataconsumer.model.DeleteDataModel;
import com.lyang.dataconsumer.model.OperateModel;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * FileName: DeleteService
 * Author:   lujy7
 * Date:     2020/1/14 17:54
 * Description:
 */
@Component
public class DeleteService extends MongoService<DeleteDataModel> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void execute(DeleteDataModel deleteDataModel) {
        OperateModel dataOperate = deleteDataModel.getDataOperate();
        Bson filter = parseToFilter(dataOperate);
        mongoTemplate.getCollection(deleteDataModel.getTableName()).deleteMany(filter);
    }

}
