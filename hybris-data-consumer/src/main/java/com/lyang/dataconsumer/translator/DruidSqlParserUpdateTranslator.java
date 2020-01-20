package com.lyang.dataconsumer.translator;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.ast.statement.SQLUpdateSetItem;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.lyang.dataconsumer.executor.UpdateService;
import com.lyang.dataconsumer.model.OperateModel;
import com.lyang.dataconsumer.model.UpdateDataModel;
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
public class DruidSqlParserUpdateTranslator extends DruidSqlParserTranslator<SQLUpdateStatement> {

    @Autowired
    private UpdateService updateService;

    @Override
    public void translate(SQLUpdateStatement statement) {
        SQLName tableName = statement.getTableName();
        List<SQLUpdateSetItem> items = statement.getItems();
        SQLBinaryOpExpr where = (SQLBinaryOpExpr) statement.getWhere();
        UpdateDataModel updateDataModel = new UpdateDataModel();
        List<Map<String, Object>> list = parseToValueModel(items);
        updateDataModel.setDataModified(list);
        OperateModel operateModel = parseToOperateModel(where);
        updateDataModel.setDataOperate(operateModel);
        updateDataModel.setTableName(tableName.getSimpleName());
        System.out.println(updateDataModel);
        updateService.execute(updateDataModel);
    }

    private static List<Map<String, Object>> parseToValueModel(List<SQLUpdateSetItem> items) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SQLUpdateSetItem item : items) {
            Map<String, Object> map = new HashMap<>();
            SQLIdentifierExpr column = (SQLIdentifierExpr) item.getColumn();
            SQLExpr value = item.getValue();
            if(value instanceof SQLCharExpr) {
                map.put(column.getName(), ((SQLCharExpr) value).getText());
            } else if (value instanceof SQLIntegerExpr) {
                map.put(column.getName(), ((SQLIntegerExpr) value).getNumber());
            } else if (value instanceof SQLMethodInvokeExpr) {
                //todo
            }
            list.add(map);
        }
        return list;
    }

    public static void main(String[] args) {
        /*DruidSqlParserTranslator druidSqlParserTranslator = new DruidSqlParserTranslator();
        druidSqlParserTranslator.translate("update deplyd set xx=1,yy='12' where id in (3) and b=2 and c=2");*/
    }

}
