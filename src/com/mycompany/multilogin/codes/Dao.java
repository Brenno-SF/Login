
package com.mycompany.multilogin.codes;
import java.sql.ResultSet;

public interface Dao {
    public int salvar(Login l);
    public boolean conectar ();
    public void desconectar();
    public ResultSet consultar(Pessoa p);    
}
