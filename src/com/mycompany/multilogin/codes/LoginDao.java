
package com.mycompany.multilogin.codes;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

    

public class LoginDao implements Dao{
    Connection con;
    PreparedStatement st;
    ResultSet rs;
    
    @Override
    public boolean conectar (){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadastro","root","");
            return true;
        }catch(ClassNotFoundException | SQLException ex){
            return false;
        }
    }
    
    @Override
    public int salvar(Login l){
        try{
            int status;
            st = con.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?)");
            st.setString(1,l.getNomeComp());
            st.setInt(2,l.idade(l.getAnoNasc()));
            st.setString(3,l.getEmail());
            st.setString(4,l.getSenha());
            st.setString(5,l.getUsername());
            status = st.executeUpdate();
            return status;
        }catch(SQLException ex){
            return ex.getErrorCode();
        }
    }
    
    @Override
    public void desconectar(){
        try {  
            con.close();
        } catch (SQLException ex) {
        
        }
    }
    @Override
    public ResultSet consultar(Pessoa p){
        
        try {
            String sql = "SELECT * FROM user WHERE senha = ? AND username = ?";
            st = con.prepareStatement(sql);
            st.setString(1, p.getPasswordL());
            st.setString(2, p.getUsernameL()); 
            
            rs = st.executeQuery();
            return rs;
       } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "LoginView: "+ ex);
           return null;
       } 
    }
}
