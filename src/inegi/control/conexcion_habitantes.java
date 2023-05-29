/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inegi.control;

import inegi.control.Conexion;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class conexcion_habitantes {

    Conexion con;

    public conexcion_habitantes() {
        con = new Conexion();

    }

    public void caragrDatos(JTable tblDatos, DefaultTableModel modelo) {
        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
        String datos[] = new String[8];

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("select * from habitantes");
            ResultSet resultado = pstm.executeQuery();

            while (resultado.next()) {
                datos[0] = resultado.getString("id_entidad");
                datos[1] = resultado.getString("id_municipio");
                datos[2] = resultado.getString("habitantes");
                datos[3] = resultado.getString("hombres");
                datos[4] = resultado.getString("mujeres");
                modelo.addRow(datos);
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }

    public void insertData(int id_entidad, int idMunicipio, String habitantes, String hombre, String mujeres) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("INSERT INTO habitantes (id_entidad, id_municipio, habitantes, hombres, mujeres) VALUES (?, ?, ?, ?, ?)");
            pstm.setInt(1, id_entidad);
            pstm.setInt(2, idMunicipio);
            pstm.setString(3, habitantes);
            pstm.setString(4, hombre);
            pstm.setString(5, mujeres);

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void eliminarRegistro(int idEntidad, int idMunicipio) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("DELETE FROM habitantes WHERE id_entidad = ? AND id_municipio = ?");
            pstm.setInt(1, idEntidad);
            pstm.setInt(2, idMunicipio);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void modificarRegistro(String id_entidad, String idMunicipio, int habitantes, int hombres, int mujeres) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("UPDATE habitantes SET habitantes = ?, hombres = ?, mujeres = ? WHERE id_entidad = ? AND id_municipio = ?");

            pstm.setInt(1, habitantes);
            pstm.setInt(2, hombres);
            pstm.setInt(3, mujeres);
            pstm.setString(4, id_entidad);
            pstm.setString(5, idMunicipio);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
