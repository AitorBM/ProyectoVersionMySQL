/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author AitorBM
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        VP ventanaPrincipal = new VP();
        ventanaPrincipal.setVisible(true);
        
        /*
        try {
            // Driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Cadena de conexión
            String bd = "scotchbox";
            String login = "root";
            String password = "root";
            String url = "jdbc:mysql://192.168.33.10:3306/" + bd;

            // Establecimiento de conexión
            Connection conexion = DriverManager.getConnection(url, login, password);

            // [2] Consulta
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("select * from CATEGORIAS");

            while (resultado.next() == true) {
                System.out.println("Id: " + resultado.getInt("ID_CAT"));
                System.out.println("  Nombre: " + resultado.getString("NOMBRE"));               
            }

            // [3] Desconexión
            conexion.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: Driver MySQL no disponible...");
            System.out.println(ex.getCause());
        } catch (SQLException ex) {
            System.out.println("ERROR: No se pudo realizar la consulta...");
            System.out.println(ex.getCause());
        }*/
    }
}
