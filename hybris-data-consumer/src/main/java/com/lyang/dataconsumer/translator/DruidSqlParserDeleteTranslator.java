package com.lyang.dataconsumer.translator;

import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.lyang.dataconsumer.executor.DeleteService;
import com.lyang.dataconsumer.model.DeleteDataModel;
import com.lyang.dataconsumer.model.OperateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FileName: DruidSqlParserInsertTranslator
 * Author:   lujy7
 * Date:     2019/12/31 14:18
 * Description:
 */
@Component
public class DruidSqlParserDeleteTranslator extends DruidSqlParserTranslator<SQLDeleteStatement> {

    @Autowired
    private DeleteService deleteService;

    @Override
    public void translate(SQLDeleteStatement statement) {
        SQLName tableName = statement.getTableName();
        SQLBinaryOpExpr where = (SQLBinaryOpExpr) statement.getWhere();
        DeleteDataModel deleteDataModel = new DeleteDataModel();
        OperateModel operateModel = parseToOperateModel(where);
        deleteDataModel.setDataOperate(operateModel);
        deleteDataModel.setTableName(tableName.getSimpleName());
        System.out.println(deleteDataModel);
        deleteService.execute(deleteDataModel);
    }

    public static void main(String[] args) {
        /*String sql = "delete deplyd where id in (select name from ad where a=1) and b=2 and c='12'";
        sql = "delete deplyd where id > 5 and b=2 and c='12'";
        DruidSqlParserTranslator druidSqlParserTranslator = new DruidSqlParserTranslator();
        druidSqlParserTranslator.translate(sql);*/
    }

}
