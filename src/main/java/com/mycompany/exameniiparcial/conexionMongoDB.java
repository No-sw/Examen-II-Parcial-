/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exameniiparcial;

import com.mongodb.MongoClientException;
import javax.swing.JOptionPane;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
/**
 *
 * @author Toshiba
 */
public class conexionMongoDB {
    String servidor = "localhost";
    int puerto = 27017;
    MongoClient conn;
    
    MongoDatabase dataBaseSelect;
    
    MongoCollection <Document> Maquinaria;
    
        public conexionMongoDB(){
        try{
            this.conn = MongoClients.create("mongodb://"+servidor+":"+ puerto);
            System.out.println("Conexion Exitosa");
        }catch (MongoClientException error){
            System.out.println("Error en conexion; "+error.toString());
        }
    }
    
    public void setBD(){
    dataBaseSelect = conn.getDatabase("Produccion");
     System.out.println("DB Selecionada: " + dataBaseSelect.toString());
    }
    
    public MongoDatabase getDB(){
        return dataBaseSelect;
    }
    
    public boolean insertDocuments(MongoCollection<Document> collection,Document newMaquinaria){
        try{
         collection.insertOne(newMaquinaria);   
            JOptionPane.showMessageDialog(null, "Registro creado con exito!","Importante", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch(MongoClientException error){
            JOptionPane.showMessageDialog(null, "Registro no pudo ser ingresado","Importante", JOptionPane.ERROR_MESSAGE);
            return false;
        }  
    }
    
    public FindIterable<Document> getDocuments(MongoCollection<Document> collection){
    FindIterable<Document> iterable = null;
    try{
        iterable = collection.find();
    } catch(MongoClientException error){
        JOptionPane.showMessageDialog(null, "Registro no pudo ser ingresado","Importante!",JOptionPane.ERROR_MESSAGE);
    }
      return iterable;
    }
    
    public boolean deleteDocuments(MongoCollection<Document> collection ,String id){
        try{
        Bson filter = eq("_id", new ObjectId(id));
        DeleteResult result = collection.deleteOne(filter);
        return result.getDeletedCount()>0 ? true : false;
        } catch(MongoClientException error){
            JOptionPane.showMessageDialog(null, "Registro no pudo ser ingresado","Importante!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean actualizarDocuments(MongoCollection<Document> collection, Document data ,String id){
        try{
            Bson filter = eq("_id", new ObjectId(id));
            UpdateResult updateResult = collection.replaceOne(filter, data);
            return updateResult.getModifiedCount()>0 ? true : false;            
        } catch(MongoClientException error){
            JOptionPane.showMessageDialog(null, "Registro no pudo ser ingresado","Importante!",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
