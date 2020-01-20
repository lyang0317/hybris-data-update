package com.lyang.dataconsumer.translator;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.util.JdbcConstants;
import com.lyang.dataconsumer.executor.DeleteService;
import com.lyang.dataconsumer.executor.InsertService;
import com.lyang.dataconsumer.executor.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * FileName: DruidSqlParserRouter
 * Author:   lujy7
 * Date:     2020/1/19 14:38
 * Description:
 */
@Component
public class DruidSqlParserRouter {

    @Autowired
    private DruidSqlParserInsertTranslator insertTranslator;

    @Autowired
    private DruidSqlParserUpdateTranslator updateTranslator;

    @Autowired
    private DruidSqlParserDeleteTranslator deleteTranslator;

    public void process(String msg) {
        List<SQLStatement> statements = SQLUtils.parseStatements(msg, JdbcConstants.MYSQL);
        for (SQLStatement statement : statements) {
            if(statement instanceof SQLInsertStatement) {
                insertTranslator.translate((SQLInsertStatement) statement);
            } else if(statement instanceof SQLUpdateStatement) {
                updateTranslator.translate((SQLUpdateStatement) statement);
            } else if (statement instanceof SQLDeleteStatement) {
                deleteTranslator.translate((SQLDeleteStatement) statement);
            }
        }
    }

}
