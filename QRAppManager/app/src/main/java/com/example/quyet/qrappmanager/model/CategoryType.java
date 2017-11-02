package com.example.quyet.qrappmanager.model;

/**
 * Created by Quyet on 02/11/2017.
 */

public class CategoryType {
    private String nameOfType;
    private String typeId;


    public CategoryType(String nameOfType, String typeId) {
        this.nameOfType = nameOfType;
        this.typeId = typeId;
    }

    public String getNameOfType() {
        return nameOfType;
    }

    public void setNameOfType(String nameOfType) {
        this.nameOfType = nameOfType;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String id) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return nameOfType ;
    }
}
