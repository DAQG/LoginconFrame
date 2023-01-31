import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login2 {
    PreparedStatement ps;
    private JTextField tfID;
    private JTextField tfnombre;
    private JTextField tfcelular;
    private JTextField tfcorreo;
    private JTextField tfcarrera;
    private JTextField tfsemestre;
    private JButton Guardar;
    private JPanel panel1;

    public Login2(){


        Guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;

                                try {
                                    con = getConection();
                                    ps = con.prepareStatement("INSERT INTO new_table (ID, nombre, Celular, correo,Carrera,Semestre) VALUES(?,?,?,?,?,?) ");
                                    ps.setString(1, tfID.getText());
                                    ps.setString(2, tfnombre.getText());
                                    ps.setString(3, tfcelular.getText());
                                    ps.setString(4, tfcorreo.getText());
                                    ps.setString(5,tfcarrera.getText());
                                    ps.setString(6,tfsemestre.getText());
                                    System.out.println(ps);//imprimo en consola para verificación

                                    int res = ps.executeUpdate();

                                    if (res > 0) {
                                        JOptionPane.showMessageDialog(null, "Persona Guardada");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error al Guardar persona");
                                    }
                                    //limpiartxt();
                                    //txtId.setText("");
                                    //txtNombre.setText("");
                                    //txtCelular.setText("");
                                    //txtCorreo.setText("");
                                    con.close();//importante!!!!

                                } catch (HeadlessException | SQLException f) {
                                    System.err.println(f);
                                }


                            }
                        });
                    }

                    public static void main(String[] args) {
                        JFrame frame = new JFrame("insertar");
                        frame.setContentPane(new Login2().panel1);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setVisible(true);
                    }
                    public static Connection getConection() {
                        Connection con = null;
                        String base = "registro2"; //Nombre de la base de datos
                        String url = "jdbc:mysql://localhost:3306/" + base; //Direccion, puerto y nombre de la Base de Datos
                        String user = "root"; //Usuario de Acceso a MySQL
                        String password = "Hiphop1511@"; //Password del usuario

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            con = DriverManager.getConnection(url, user, password);
                        } catch (ClassNotFoundException | SQLException e) {
                            System.err.println(e);
                        }
                        return con;
                    }

                }