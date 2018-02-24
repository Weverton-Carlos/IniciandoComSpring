package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//nova linha add
public class Conexao {
    private Connection connection ;
    private static Conexao instance = null;
//padrão singleon
    private Conexao(){}

    public static Conexao getInstance(){
        if(instance == null){
            instance = new Conexao();
        }
        return instance;
    }
    
    public Connection criaConexao() {
        try {
            System.out.println("criando conexão com banco iniciandospring");
            //Class.forName("com.mysql.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection("jdbc:mysql://localhost/iniciandospring", "root", "yrmaoMySQL" );
            System.out.println("conexão criada ");
            return connection;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
