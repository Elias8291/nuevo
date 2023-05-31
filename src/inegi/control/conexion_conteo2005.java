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

    public conexion_conteo2005() {
        con = new Conexion();

    }

    public void caragrDatos(JTable tblDatos, DefaultTableModel modelo) {
        modelo.setRowCount(0);
        modelo.fireTableDataChanged();

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


    public void insertData(String id_entidad, String idMunicipio, int pob_total, int pob_masculina, int pob_femenina, int tot_vivienda) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("INSERT INTO conteo2005(id_entidad, id_municipio, pob_total, pob_masculina, pob_femenina, tot_vivienda) VALUES (?, ?, ?, ?, ?, ?)");
            pstm.setString(1, id_entidad);
            pstm.setString(2, idMunicipio);
            pstm.setInt(3, pob_total);
            pstm.setInt(4, pob_masculina);
            pstm.setInt(5, pob_femenina);
            pstm.setInt(6, tot_vivienda);

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void eliminarRegistro(int idEntidad, int idMunicipio) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("DELETE FROM conteo2005 WHERE id_entidad = ? AND id_municipio = ?");
            pstm.setInt(1, idEntidad);
            pstm.setInt(2, idMunicipio);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void modificarRegistro(String id_entidad, String idMunicipio, int pob_total, int pob_masculina, int pob_femenina, int tot_vivienda) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("UPDATE conteo2005 SET pob_total = ?, pob_masculina = ?, pob_femenina = ?, tot_vivienda = ? WHERE id_entidad = ? AND id_municipio = ?");

            pstm.setInt(1, pob_total);
            pstm.setInt(2, pob_masculina);
            pstm.setInt(3, pob_femenina);
            pstm.setInt(4, tot_vivienda);
            pstm.setString(5, id_entidad);
            pstm.setString(6, idMunicipio);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
}
