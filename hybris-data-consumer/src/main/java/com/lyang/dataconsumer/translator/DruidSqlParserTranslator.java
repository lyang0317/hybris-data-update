package com.lyang.dataconsumer.translator;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.util.JdbcConstants;
import com.lyang.dataconsumer.model.OperateModel;
import com.lyang.dataconsumer.model.OperateTypeEnum;

import java.util.List;

/**
 * FileName: DruidSqlParserTranslator
 * Author:   lujy7
 * Date:     2019/12/31 14:13
 * Description:
 */
public abstract class DruidSqlParserTranslator<T extends SQLStatement> {

    public abstract void translate(T statement);

    protected static OperateModel parseToOperateModel(SQLBinaryOpExpr expr) {
        SQLExpr left = expr.getLeft();
        SQLExpr right = expr.getRight();
        SQLBinaryOperator operator = expr.getOperator();
        if(left != null) {
            OperateModel operateModel = new OperateModel();
            operateModel.setOperateType(OperateTypeEnum.getEnum(operator.getName()));
            if(left instanceof SQLIdentifierExpr) {
               return parseValueToOperateModel(expr);
            } else {
                if(left instanceof SQLBinaryOpExpr) {
                    operateModel.setLeft(parseToOperateModel((SQLBinaryOpExpr)left));
                }
                if(right instanceof SQLBinaryOpExpr) {
                    operateModel.setRight(parseToOperateModel((SQLBinaryOpExpr)right));
                }
            }
            return operateModel;
        }
        return null;
    }

    protected static OperateModel parseValueToOperateModel(SQLBinaryOpExpr expr) {
        SQLIdentifierExpr left = (SQLIdentifierExpr) expr.getLeft();
        SQLExpr right = expr.getRight();
        SQLBinaryOperator operator = expr.getOperator();
        OperateModel operateModel = new OperateModel();
        operateModel.setKey(left.getName());
        if(right instanceof SQLCharExpr) {
            operateModel.setValue(((SQLCharExpr) right).getText());
        } else if (right instanceof SQLIntegerExpr) {
            operateModel.setValue(((SQLIntegerExpr) right).getNumber());
        } else if (right instanceof SQLMethodInvokeExpr) {
            //todo
        }
        operateModel.setOperateType(OperateTypeEnum.getEnum(operator.getName()));
        return operateModel;
    }

}
