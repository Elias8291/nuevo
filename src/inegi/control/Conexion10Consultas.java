/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inegi.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Conexion10Consultas {

    Conexion con;

    public Conexion10Consultas() {
        con = new Conexion();
    }

    public void consulta1(JTable tblDatos, DefaultTableModel modelo) {

        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
        tblDatos.revalidate();
        modelo.setColumnIdentifiers(new Vector<>());

// Actualiza la vista del JTable
        tblDatos.getTableHeader().repaint();
        modelo.addColumn("Nom_entidad");
        modelo.addColumn("Suma_Pob_Femenina");

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT inegi.entidades.nom_entidad, SUM(inegi.conteo2005.pob_femenina) FROM inegi.entidades INNER JOIN inegi.conteo2005 ON inegi.entidades.id_entidad = inegi.conteo2005.id_entidad GROUP BY inegi.entidades.nom_entidad");
            ResultSet resultado = pstm.executeQuery();

            while (resultado.next()) {
                String nomEntidad = resultado.getString("nom_entidad");
                int sumaPobFemenina = resultado.getInt("SUM(inegi.conteo2005.pob_femenina)");
                modelo.addRow(new Object[]{nomEntidad, sumaPobFemenina});
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }

    public void consulta2(JTable tblDatos, DefaultTableModel modelo) {

        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
        tblDatos.revalidate();
        modelo.setColumnIdentifiers(new Vector<>());
        tblDatos.getTableHeader().repaint();
        modelo.addColumn("Nombre Entidad");
        modelo.addColumn("Nombre Municipio");

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT entidades.nom_entidad, municipios.nom_municipio FROM entidades INNER JOIN municipios ON entidades.id_entidad = municipios.id_entidad WHERE entidades.nom_entidad IN ('Chiapas', 'Guerrero', 'Veracruz de Ignacio de la Llave', 'Oaxaca') GROUP BY entidades.nom_entidad, municipios.nom_municipio");
            ResultSet resultado = pstm.executeQuery();

            while (resultado.next()) {
                String nomEntidad = resultado.getString("nom_entidad");
                String nomMunicipio = resultado.getString("nom_municipio");
                modelo.addRow(new Object[]{nomEntidad, nomMunicipio});
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }

    public void consulta3(JTable tblDatos, DefaultTableModel modelo) {

        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
        tblDatos.revalidate();
        modelo.setColumnIdentifiers(new Vector<>());
        tblDatos.getTableHeader().repaint();
        modelo.addColumn("Nombre Entidad");
        modelo.addColumn("Suma de Hombres");

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT inegi.entidades.nom_entidad, SUM(inegi.habitantes.hombres) FROM inegi.entidades INNER JOIN inegi.habitantes ON inegi.entidades.id_entidad = inegi.habitantes.id_entidad WHERE nom_entidad IN ('Oaxaca', 'Puebla', 'Zacatecas', 'Veracruz de Ignacio de la llave') GROUP BY inegi.entidades.nom_entidad");
            ResultSet resultado = pstm.executeQuery();

            while (resultado.next()) {
                String nomEntidad = resultado.getString(1);
                int sumHombres = resultado.getInt(2);
                modelo.addRow(new Object[]{nomEntidad, sumHombres});
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }

    public void consulta4(JTable tblDatos, DefaultTableModel modelo) {

        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
        tblDatos.revalidate();
        modelo.setColumnIdentifiers(new Vector<>());
        tblDatos.getTableHeader().repaint();
        modelo.addColumn("Nombre Entidad");
        modelo.addColumn("Promedio de Habitantes");

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT inegi.entidades.nom_entidad, AVG(inegi.habitantes.habitantes) FROM inegi.entidades INNER JOIN inegi.habitantes ON inegi.entidades.id_entidad = inegi.habitantes.id_entidad WHERE nom_entidad = 'Puebla' GROUP BY inegi.entidades.nom_entidad");
            ResultSet resultado = pstm.executeQuery();

            while (resultado.next()) {
                String nomEntidad = resultado.getString(1);
                double avgHabitantes = resultado.getDouble(2);
                modelo.addRow(new Object[]{nomEntidad, avgHabitantes});
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }

    public void consulta5(JTable tblDatos, DefaultTableModel modelo) {

        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
        tblDatos.revalidate();
        modelo.setColumnIdentifiers(new Vector<>());
        tblDatos.getTableHeader().repaint();
        modelo.addColumn("Nombre Entidad");
        modelo.addColumn("Población");

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT inegi.entidades.nom_entidad, SUM(inegi.edades.e0_4 + inegi.edades.e5_9 + inegi.edades.e10_14) AS poblacion FROM inegi.entidades INNER JOIN inegi.edades ON inegi.entidades.id_entidad = inegi.edades.id_entidad GROUP BY inegi.entidades.nom_entidad HAVING poblacion = (SELECT MIN(poblacion) FROM (SELECT SUM(e0_4 + e5_9 + e10_14) AS poblacion FROM inegi.edades GROUP BY id_entidad) AS tabla_poblacion)");
            ResultSet resultado = pstm.executeQuery();

            while (resultado.next()) {
                String nomEntidad = resultado.getString("nom_entidad");
                int poblacion = resultado.getInt("poblacion");
                modelo.addRow(new Object[]{nomEntidad, poblacion});
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }

    public void consulta6(JTable tblDatos, DefaultTableModel modelo) {

        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
        tblDatos.revalidate();
        modelo.setColumnIdentifiers(new Vector<>());
        tblDatos.getTableHeader().repaint();
        modelo.addColumn("Nombre Entidad");
        modelo.addColumn("Cantidad de Municipios");

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT inegi.entidades.nom_entidad, COUNT(inegi.municipios.id_municipio) AS cantidad FROM inegi.entidades INNER JOIN inegi.municipios ON inegi.entidades.id_entidad = inegi.municipios.id_entidad GROUP BY inegi.entidades.nom_entidad HAVING cantidad < 40");
            ResultSet resultado = pstm.executeQuery();

            while (resultado.next()) {
                String nomEntidad = resultado.getString("nom_entidad");
                int cantidadMunicipios = resultado.getInt("cantidad");
                modelo.addRow(new Object[]{nomEntidad, cantidadMunicipios});
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }

    public void consulta7(JTable tblDatos, DefaultTableModel modelo) {

        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
        tblDatos.revalidate();
        modelo.setColumnIdentifiers(new Vector<>());
        tblDatos.getTableHeader().repaint();
        modelo.addColumn("Nombre Entidad");
        modelo.addColumn("Nombre Municipio");
        modelo.addColumn("Cantidad de Habitantes");

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT inegi.entidades.nom_entidad, inegi.municipios.nom_municipio, MIN(inegi.habitantes.habitantes) AS cantidad_de_habitantes FROM inegi.entidades INNER JOIN inegi.municipios ON inegi.entidades.id_entidad = inegi.municipios.id_entidad INNER JOIN inegi.habitantes ON inegi.municipios.id_municipio = inegi.habitantes.id_municipio AND inegi.entidades.id_entidad = inegi.habitantes.id_entidad GROUP BY inegi.entidades.nom_entidad, inegi.municipios.nom_municipio HAVING MIN(inegi.habitantes.habitantes) = MIN(inegi.habitantes.habitantes) ORDER BY cantidad_de_habitantes ASC LIMIT 5");
            ResultSet resultado = pstm.executeQuery();

            while (resultado.next()) {
                String nomEntidad = resultado.getString("nom_entidad");
                String nomMunicipio = resultado.getString("nom_municipio");
                int cantidadHabitantes = resultado.getInt("cantidad_de_habitantes");
                modelo.addRow(new Object[]{nomEntidad, nomMunicipio, cantidadHabitantes});
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }

    public void consulta8(JTable tblDatos, DefaultTableModel modelo) {

        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
        tblDatos.revalidate();
        modelo.setColumnIdentifiers(new Vector<>());
        tblDatos.getTableHeader().repaint();
        modelo.addColumn("Nombre Municipio");
        modelo.addColumn("Cantidad de Habitantes");
        modelo.addColumn("Suma de Edades");

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT municipios.nom_municipio, habitantes.habitantes, SUM(edades.e20_24 + edades.e25_29 + edades.e30_34 + edades.e35_39 + edades.e40_44) AS suma_edades FROM edades INNER JOIN municipios ON municipios.id_municipio = edades.id_municipio INNER JOIN habitantes ON habitantes.id_municipio = municipios.id_municipio AND habitantes.id_entidad = municipios.id_entidad WHERE habitantes.habitantes >= 1000000 GROUP BY municipios.nom_municipio, habitantes.habitantes");
            ResultSet resultado = pstm.executeQuery();

            while (resultado.next()) {
                String nomMunicipio = resultado.getString("nom_municipio");
                int cantidadHabitantes = resultado.getInt("habitantes");
                int sumaEdades = resultado.getInt("suma_edades");
                modelo.addRow(new Object[]{nomMunicipio, cantidadHabitantes, sumaEdades});
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }

    public void consulta9(JTable tblDatos, DefaultTableModel modelo) {

        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
        tblDatos.revalidate();
        modelo.setColumnIdentifiers(new Vector<>());
        tblDatos.getTableHeader().repaint();
        modelo.addColumn("Nombre Entidad");
        modelo.addColumn("Suma Total");

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT entidades.nom_entidad, SUM(edades.e20_24) + SUM(edades.e25_29) + SUM(edades.e30_34) + SUM(edades.e35_39) + SUM(edades.e40_44) AS SumaT FROM entidades JOIN edades ON entidades.id_entidad = edades.id_entidad JOIN habitantes ON edades.id_entidad = habitantes.id_entidad AND edades.id_municipio = habitantes.id_municipio GROUP BY entidades.nom_entidad HAVING SUM(habitantes.habitantes) > 5000000");
            ResultSet resultado = pstm.executeQuery();

            while (resultado.next()) {
                String nomEntidad = resultado.getString("nom_entidad");
                int sumaTotal = resultado.getInt("SumaT");
                modelo.addRow(new Object[]{nomEntidad, sumaTotal});
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }

    public void consulta10(JTable tblDatos, DefaultTableModel modelo) {

        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
        tblDatos.revalidate();
        modelo.setColumnIdentifiers(new Vector<>());
        tblDatos.getTableHeader().repaint();
        modelo.addColumn("Nombre Entidad");
        modelo.addColumn("Nombre Municipio");

        try {

            PreparedStatement pstm = con.getConnection().prepareStatement("SELECT entidades.nom_entidad, municipios.nom_municipio FROM entidades JOIN municipios ON entidades.id_entidad = municipios.id_entidad ORDER BY LENGTH(municipios.nom_municipio) ASC LIMIT 1;");
            ResultSet resultado = pstm.executeQuery();

             while (resultado.next()) {
                String nomEntidad = resultado.getString("nom_entidad");
                String nomMunicipio = resultado.getString("nom_municipio");
                modelo.addRow(new Object[]{nomEntidad, nomMunicipio});
            } 
            tblDatos.setModel(modelo);

        } catch (Exception e) {
            System.out.println("eror" + e);
        }

    }
}
