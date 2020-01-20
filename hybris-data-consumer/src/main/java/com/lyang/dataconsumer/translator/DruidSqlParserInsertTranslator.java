package com.lyang.dataconsumer.translator;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.lyang.dataconsumer.executor.InsertService;
import com.lyang.dataconsumer.model.InsertDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: DruidSqlParserInsertTranslator
 * Author:   lujy7
 * Date:     2019/12/31 14:18
 * Description:
 */
@Component
public class DruidSqlParserInsertTranslator extends DruidSqlParserTranslator<SQLInsertStatement> {

    @Autowired
    private InsertService insertService;

    @Override
    public void translate(SQLInsertStatement statement) {
        SQLName tableName = statement.getTableName();
        List<SQLExpr> columns = statement.getColumns();
        List<SQLExpr> values = statement.getValues().getValues();
        InsertDataModel insertDataModel = new InsertDataModel();
        List<Map<String, Object>> list = parseToValueModel(columns, values);
        insertDataModel.setDataModified(list);
        insertDataModel.setTableName(tableName.getSimpleName());
        System.out.println(insertDataModel);
        insertService.execute(insertDataModel);
    }

    private static List<Map<String, Object>> parseToValueModel(List<SQLExpr> columns, List<SQLExpr> values) {
        List<Map<String, Object>> list = new ArrayList<>();
        if(!(values.get(0) instanceof SQLListExpr)){
            Map<String, Object> map = new HashMap<>();
            parseToValueModelMap(columns, values, map);
            list.add(map);
        } else {
            for (int i = 0; i < values.size(); i++){
                Map<String, Object> map = new HashMap<>();
                SQLExpr valueSQLExpr = values.get(i);
                if(valueSQLExpr instanceof SQLListExpr) {
                    List<SQLExpr> items = ((SQLListExpr) valueSQLExpr).getItems();
                    parseToValueModelMap(columns, items, map);
                }
                list.add(map);
            }
        }
        return list;
    }

    private static void parseToValueModelMap(List<SQLExpr> columns, List<SQLExpr> values, Map<String, Object> map) {
        for (int i = 0; i < values.size(); i++){
            SQLExpr valueSQLExpr = values.get(i);
            SQLExpr nameSQLExpr = columns.get(i);
            String fieldName = ((SQLIdentifierExpr) nameSQLExpr).getName();
            if(valueSQLExpr instanceof SQLCharExpr) {
                map.put(fieldName, ((SQLCharExpr) valueSQLExpr).getText());
            } else if (valueSQLExpr instanceof SQLIntegerExpr) {
                map.put(fieldName, ((SQLIntegerExpr) valueSQLExpr).getNumber());
            } else if (valueSQLExpr instanceof SQLMethodInvokeExpr) {
                //todo
            }
        }
    }

    public static void main(String[] args) {
        /*DruidSqlParserTranslator druidSqlParserTranslator = new DruidSqlParserTranslator();
        druidSqlParserTranslator.translate("insert into deplyd (idname,typename) values ((123, 'bad'),(ceil(234), 'bad'))");*/
    }

}
