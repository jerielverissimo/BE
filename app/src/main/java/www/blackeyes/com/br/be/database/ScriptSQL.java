package www.blackeyes.com.br.be.database;

/**
 * Created by jeriel on 11/7/15.
 */
public class ScriptSQL {

    public static String getCreateContato(){

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE IF NOT EXISTS DISPOSITIVOS ( ");
        sqlBuilder.append("_id                INTEGER       NOT NULL ");
        sqlBuilder.append("                                 PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("NOME               VARCHAR (200), ");
        sqlBuilder.append("DISPOSITIVO           VARCHAR (100) ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }
}
