/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inegi.control;

import inegi.control.Conexion;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class conexion_conteo2005 {

    Conexion con;
    String[] columnNames = {
       "id_entidad", "id_municipio", "pob_total", "pob_masculina", "pob_femenina", "tot_vivienda"
    };

    public conexion_conteo2005() {
        con = new Conexion();

    }

    public void caragrDatos(JTable tblDatos, DefaultTableModel modelo) {

        for (String nombre : columnNames) {
            modelo.addColumn(nombre);
        }

        String datos[] = new String[8];

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("select * from conteo2005");
            ResultSet resultado = pstm.executeQuery();

            while (resultado.next()) {
                datos[0] = resultado.getString("id_entidad");
                datos[1] = resultado.getString("id_municipio");
                datos[2] = resultado.getString("pob_total");
                datos[3] = resultado.getString("pob_masculina");
                datos[4] = resultado.getString("pob_femenina");
                datos[5] = resultado.getString("tot_vivienda");
                modelo.addRow(datos);
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }

    
    

}
