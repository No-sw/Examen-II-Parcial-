/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.exameniiparcial;

/**
 *
 * @author Toshiba
 */
public class Main {
    static conexionMongoDB connMongo;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        connMongo = new conexionMongoDB();
        connMongo.setBD();
        
        Maquinaria newMaquinaria = new Maquinaria();
        newMaquinaria.setVisible(true);
    }
    
}
