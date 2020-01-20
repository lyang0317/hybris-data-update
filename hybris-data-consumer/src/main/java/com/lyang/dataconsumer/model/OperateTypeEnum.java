package com.lyang.dataconsumer.model;

public enum OperateTypeEnum {

    AND("AND"), OR("OR"), EQUAL("="), GREATER(">"), LESSER("<"), IN("");

    private String name;

    OperateTypeEnum (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static OperateTypeEnum getEnum(String name) {
        OperateTypeEnum[] operateTypeEnums = OperateTypeEnum.values();
        for (OperateTypeEnum operateTypeEnum : operateTypeEnums) {
            if(name.equals(operateTypeEnum.getName())) {
                return operateTypeEnum;
            }
        }
        return null;
    }

}
