package www.blackeyes.com.br.be.database;

import android.content.Context;
import android.database.sqlite.*;

/**
 * Created by jeriel on 11/7/15.
 */
public class DataBase extends SQLiteOpenHelper{

    public DataBase(Context context){
        super(context,"DISPOSITIVO", null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptSQL.getCreateContato());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
