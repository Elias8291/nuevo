/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inegi.control;

import inegi.control.Conexion;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class conexcion_entidades {

    Conexion con;
    String[] columnNames = {
        "id_entidad",
        "nom_entidad",
        "abreviatura",
        "nom_capital"
    };

    public conexcion_entidades() {
        con = new Conexion();

    }

    public void caragrDatos(JTable tblDatos, DefaultTableModel modelo) {

        for (String nombre : columnNames) {
            modelo.addColumn(nombre);
        }

        String datos[] = new String[8];

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("select * from entidades");
            ResultSet resultado = pstm.executeQuery();

            while (resultado.next()) {
                datos[0] = resultado.getString("id_entidad");
                datos[1] = resultado.getString("nom_entidad");
                datos[2] = resultado.getString("abreviatura");
                datos[3] = resultado.getString("nom_capital");

                modelo.addRow(datos);
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }

    public void nuevoRegistro(String Entidad, String Municipio, int e0_4, int e5_9, int e10_14, int e15_19, int e20_24, int e25_29, int e30_34, int e35_39, int e40_44, int e45_49, int e50_54, int e55_59, int e60_64, int e65_69, int e70_74,
            int e75_79, int e80_84, int e85, String no_espec) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("INSERT INTO conteo2005 "
                    + "(entidad, municipio, pob_total, pob_masculina, pob_femenina, tot_vivienda) "
                    + "VALUES (?, ?, ?, ?, ?, ?)");

            pstm.setString(1, Entidad);
            pstm.setString(2, Municipio);
            pstm.setInt(3, e0_4);
            pstm.setInt(4, e5_9);
            pstm.setInt(5, e10_14);
            pstm.setInt(6, e15_19);
            pstm.setInt(7, e20_24);
            pstm.setInt(8, e25_29);
            pstm.setInt(9, e30_34);
            pstm.setInt(10, e35_39);
            pstm.setInt(11, e40_44);
            pstm.setInt(12, e45_49);
            pstm.setInt(13, e50_54);
            pstm.setInt(14, e55_59);
            pstm.setInt(15, e60_64);
            pstm.setInt(16, e65_69);
            pstm.setInt(17, e70_74);
            pstm.setInt(18, e75_79);
            pstm.setInt(19, e80_84);
            pstm.setInt(20, e85);
            pstm.setString(21, no_espec);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
