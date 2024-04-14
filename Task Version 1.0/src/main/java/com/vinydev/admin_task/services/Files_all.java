/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.vinydev.admin_task.services;
import com.br.vinydev.task.models.ProductFiscal;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;



/**
 *
 * @author Vinicius
 */
public class Files_all {
    public static Element readXmls(File file) {
      
    try{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        // Lê o arquivo XML e converter em um document
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();

        return root;
        
    }catch (Exception e ){
        throw new RuntimeException("Erro In Read File XMLs!\n Message: " + e.getMessage());
    }
    }
  
    
    public static Boolean witreSheet(List<Element> root,ArrayList<Boolean> list,String mySheet){
        ArrayList<ProductFiscal> listProdct = new ArrayList<>();  
        List<NodeList> listFiles = new ArrayList<>();

        int indexRow = 0;
        try{
            for (Element element:root ){

                NodeList nodeListProd = element.getElementsByTagName("det");
                NodeList supplier = element.getElementsByTagName("xNome");
                String supplierName = supplier.item(0).getTextContent();

                   for (int i = 0; i < nodeListProd.getLength(); i++){
                           Node node = nodeListProd.item(i);
                           if (node.getNodeType() == Node.ELEMENT_NODE) {
                               Element det = (Element) node;
                               ProductFiscal prod = new ProductFiscal();
                                   prod.setRef(((list.get(0)) ? det.getElementsByTagName("cProd").item(0).getTextContent() : "")); 
                                   prod.setName(((list.get(1)) ? det.getElementsByTagName("xProd").item(0).getTextContent() : ""));
                                   prod.setOrig(((list.get(2)) ? det.getElementsByTagName("orig").item(0).getTextContent(): ""));
                                   prod.setCfop(((list.get(3)) ? det.getElementsByTagName("CFOP").item(0).getTextContent(): ""));
                                   prod.setUndBuy(((list.get(4)) ?det.getElementsByTagName("uCom").item(0).getTextContent(): ""));
                                   prod.setValueUnit(((list.get(5)) ? det.getElementsByTagName("vProd").item(0).getTextContent(): ""));
                                   prod.setNcm(((list.get(6)) ? det.getElementsByTagName("NCM").item(0).getTextContent(): ""));
                                   prod.setSupplier(((list.get(7)) ? supplierName: "")); 
                                   try{
                                       prod.setInfoAdd(((list.get(8)) ? det.getElementsByTagName("infAdProd").item(0).getTextContent(): ""));
                                   }catch(Exception e){
                                       prod.setInfoAdd("Não informado");
                                   }

                               listProdct.add(indexRow, prod);
                               indexRow++;
                           }
                       }
                   } 
                try{
                     saveInSheet(listProdct, indexRow, mySheet); 
                     return true; 
                }catch(RuntimeException e){
                    return false;
                }
        }catch (Exception e ){
            throw new RuntimeException("Erro de localização de campo " + e.getMessage());
           
        }
    }
    public static Boolean saveInSheet(
            ArrayList<ProductFiscal> list,int indexRow,String mySheet
            )
    {
        try{
            FileInputStream file = new FileInputStream(new File(mySheet));
            
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            int t = 0;
            
            for(ProductFiscal prod:list){
                Row row = sheet.getRow(t);
                if (row == null) {
                    // Cria a linha se ela não existir
                    row = sheet.createRow(t);
                }
                t++;
                List<String> listCamps = ProductFiscal.ListCamps(prod);
                int p = 0;
                for(String item:listCamps){
                    Cell cell = row.getCell(p);
                    if(cell == null){
                        cell = row.createCell(p);
                        cell.setCellValue(item);
                        p++;
                    }
                }
            }
            file.close();
            // Abre o arquivo para escrita
            FileOutputStream outFile =new FileOutputStream(new File(mySheet));
            // Escreve as alterações e fecha o workbook
            workbook.write(outFile);
            outFile.close();
            return true;
        }catch(Exception e){
            throw new RuntimeException("ERRO of not found Sheet! Message: " + e.getMessage());
            
        }
    
    
    
    
   
    }
    


    
}
