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

    public conexcion_entidades() {
        con = new Conexion();

    }

    public void caragrDatos(JTable tblDatos, DefaultTableModel modelo) {
        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
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

    public void insertData(String id_entidad, String nom_entidad, String abreviatura, String nom_capital) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("INSERT INTO entidades(id_entidad, nom_entidad, abreviatura, nom_capital) VALUES (?, ?, ?, ?)");
            pstm.setString(1, id_entidad);
            pstm.setString(2, nom_entidad);
            pstm.setString(3, abreviatura);
            pstm.setString(4, nom_capital);
            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void eliminarRegistro(String idEntidad) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("DELETE FROM entidades WHERE id_entidad = ?");
            pstm.setString(1, idEntidad);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void modificarRegistro(String id_entidad, String nom_entidad, String abreviatura, String nom_capital) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("UPDATE entidades SET nom_entidad = ?, abreviatura = ?, nom_capital = ? WHERE id_entidad = ?");

            pstm.setString(1, nom_entidad);
            pstm.setString(2, abreviatura);
            pstm.setString(3, nom_capital);
            pstm.setString(4, id_entidad);

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
