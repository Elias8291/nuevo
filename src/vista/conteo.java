/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import inegi.control.Conexion;
import java.sql.*;

public class conteo {
    Conexion con;

    public conteo() {
        con = new Conexion();
    }

    /*AÃ±ade un nuevo registro*/
    public void nuevoRegistro(String Entidad, String Municipio, int Pob_total, int Pob_masculina, int Pob_femenina, int Tot_vivienda) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("INSERT INTO conteo2005 " +
                    "(entidad, municipio, pob_total, pob_masculina, pob_femenina, tot_vivienda) " +
                    "VALUES (?, ?, ?, ?, ?, ?)");

            pstm.setString(1, Entidad);
            pstm.setString(2, Municipio);
            pstm.setInt(3, Pob_total);
            pstm.setInt(4, Pob_masculina);
            pstm.setInt(5, Pob_femenina);
            pstm.setInt(6, Tot_vivienda);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Object[][] getDatos() {
        int registros = 0;

        // Obtenemos la cantidad de registros existentes en la tabla
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT COUNT(id_entidad) AS total FROM conteo2005");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[][] data = new String[registros][6];

        // Realizamos la consulta SQL y llenamos los datos en "Object"
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT " +
                    "entidad, municipio, pob_total, pob_masculina, pob_femenina, tot_vivienda " +
                    "FROM conteo2005 " +
                    "ORDER BY id_entidad");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                String Entidad = res.getString("entidad");
                String Municipio = res.getString("municipio");
                String Pob_total = res.getString("pob_total");
                String Pob_masculina = res.getString("pob_masculina");
                String Pob_Femenina = res.getString("pob_femenina");
                String Tot_vivienda = res.getString("tot_vivienda");

                data[i][0] = Entidad;
                data[i][1] = Municipio;
                data[i][2] = Pob_total;
                data[i][3] = Pob_masculina;
                data[i][4] = Pob_Femenina;
                data[i][5] = Tot_vivienda;

                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

    public void eliminarRegistro(String idEntidad) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("DELETE FROM conteo2005 WHERE id_entidad = ?");
            pstm.setString(1, idEntidad);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
public void modificarRegistro(String Entidad, String Municipio, int Pob_total, int Pob_masculina, int Pob_femenina, int Tot_vivienda) {
        try {
            PreparedStatement pstm = con.getConnection().prepareStatement("UPDATE conteo2005 SET " +
                    "municipio = ?, " +
                    "pob_total = ?, " +
                    "pob_masculina = ?, " +
                    "pob_femenina = ?, " +
                    "tot_vivienda = ? " +
                    "WHERE entidad = ?");

            pstm.setString(1, Entidad);
            pstm.setInt(2, Municipio);
            pstm.setInt(3, Pob_total);
            pstm.setInt(4, Pob_masculina);
            pstm.setInt(5, Pob_femenina);
            pstm.setString(6, Tot_vivienda);

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}