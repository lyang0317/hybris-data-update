package com.lyang.dataconsumer.executor;

import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.lyang.dataconsumer.model.OperateModel;
import com.lyang.dataconsumer.model.UpdateDataModel;
import com.lyang.dataconsumer.utils.MongoClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * FileName: UpdateService
 * Author:   lujy7
 * Date:     2020/1/14 17:54
 * Description:
 */
@Component
public class UpdateService extends MongoService<UpdateDataModel> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void execute(UpdateDataModel updateDataModel) {
        List<Map<String, Object>> dataModified = updateDataModel.getDataModified();
        Document document = new Document();
        for (Map<String, Object> map : dataModified) {
            for (Map.Entry<String, Object> mapEntry : map.entrySet()) {
                document.append(mapEntry.getKey(), mapEntry.getValue());
            }
        }
        OperateModel dataOperate = updateDataModel.getDataOperate();
        Bson filter = parseToFilter(dataOperate);
        //mongoTemplate.getCollection(updateDataModel.getTableName()).updateMany(filter, new Document("$set", document));
        mongoTemplate.getCollection(updateDataModel.getTableName()).updateMany(filter, new Document("$set", document), new UpdateOptions().upsert(true));
    }

}
