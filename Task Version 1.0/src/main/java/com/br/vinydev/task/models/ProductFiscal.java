/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.vinydev.task.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinicius
 */
public class ProductFiscal {
    private String ref;
    private String name;
    private String orig;
    private String cfop;
    private String undBuy;
    private String valueUnit;
    private String valueTotal;
    private String qtdProd;
    private String infoAdd;
    private String supplier;
    private String ncm;
    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }
   

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrig() {
        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getUndBuy() {
        return undBuy;
    }

    public void setUndBuy(String undBuy) {
        this.undBuy = undBuy;
    }

    public String getValueUnit() {
        return valueUnit;
    }

    public void setValueUnit(String valueUnit) {
        this.valueUnit = valueUnit;
    }

    public String getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(String valueTotal) {
        this.valueTotal = valueTotal;
    }

    public String getQtdProd() {
        return qtdProd;
    }

    public void setQtdProd(String qtdProd) {
        this.qtdProd = qtdProd;
    }

    public String getInfoAdd() {
        return infoAdd;
    }

    public void setInfoAdd(String infoAdd) {
        this.infoAdd = infoAdd;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public static List ListCamps(ProductFiscal prod){
        List<String> values = new ArrayList<>();
        values.add(prod.getRef());
        values.add(prod.getName());
        values.add(prod.getNcm());
        values.add(prod.getUndBuy());
        values.add(prod.getOrig());
        values.add(prod.getCfop());
        values.add(prod.getSupplier());
        values.add(prod.getValueUnit());
        values.add(prod.getInfoAdd());
        return values;
    }
    

}
