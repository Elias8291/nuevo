/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inegi.control;

import inegi.control.Conexion;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class conexion_municipio {

    Conexion con;

    public conexion_municipio() {
        con = new Conexion();

    }

    public void caragrDatos(JTable tblDatos, DefaultTableModel modelo) {
        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
        String datos[] = new String[8];

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("select * from municipios");
            ResultSet resultado = pstm.executeQuery();

            while (resultado.next()) {
                datos[0] = resultado.getString("id_entidad");
                datos[1] = resultado.getString("id_municipio");
                datos[2] = resultado.getString("nom_municipio");
                datos[3] = resultado.getString("nom_cabecera");
                modelo.addRow(datos);
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }

    public void insertData(int id_entidad, int idMunicipio, String nomMunicipio, String nomCabecera) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("INSERT INTO municipios (id_entidad, id_municipio, nom_municipio, nom_cabecera) VALUES (?, ?, ?, ?)");
            pstm.setInt(1, id_entidad);
            pstm.setInt(2, idMunicipio);
            pstm.setString(3, nomMunicipio);
            pstm.setString(4, nomCabecera);

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void eliminarRegistro(int idEntidad,int idMunicipio) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("DELETE FROM municipios WHERE id_entidad = ? AND id_municipio = ?");
            pstm.setInt(1, idEntidad);
            pstm.setInt(2, idMunicipio);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void modificarRegistro(int id_entidad, int idMunicipio, String nomMunicipio, String nomCabecera) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("UPDATE municipios SET nom_municipio = ?, nom_cabecera = ? WHERE id_entidad = ? AND id_municipio = ?");

            pstm.setString(1, nomMunicipio);
            pstm.setString(2, nomCabecera);
            pstm.setInt(3, id_entidad);
            pstm.setInt(4, idMunicipio);

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
