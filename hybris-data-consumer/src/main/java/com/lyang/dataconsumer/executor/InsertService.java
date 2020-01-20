package com.lyang.dataconsumer.executor;

import org.bson.Document;
import com.lyang.dataconsumer.model.InsertDataModel;
import com.lyang.dataconsumer.utils.MongoClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * FileName: InsertService
 * Author:   lujy7
 * Date:     2020/1/14 17:54
 * Description:
 */
@Component
public class InsertService extends MongoService<InsertDataModel> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void execute(InsertDataModel insertDataModel) {
        List<Map<String, Object>> dataModified = insertDataModel.getDataModified();
        List<Document> documents = new ArrayList<>();
        for (Map<String, Object> map : dataModified) {
            Document document = new Document();
            for (Map.Entry<String, Object> mapEntry : map.entrySet()) {
                document.append(mapEntry.getKey(), mapEntry.getValue());
            }
            documents.add(document);
        }
       mongoTemplate.getCollection(insertDataModel.getTableName()).insertMany(documents);
    }

}
