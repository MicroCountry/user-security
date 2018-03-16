package com.hannea.model;

/**
 * Class
 *
 * @author wgm
 * @date 2017/12/27
 */
public enum ResourceType {
    MENU(1,"菜单"),
    API(2,"接口");

    private int typeCode;
    private String typeName;

    ResourceType(int typeCode, String typeName){
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public String getTypeName() {
        return typeName;
    }
}
