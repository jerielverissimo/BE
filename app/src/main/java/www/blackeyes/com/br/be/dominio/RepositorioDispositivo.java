package www.blackeyes.com.br.be.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.widget.*;

import www.blackeyes.com.br.be.dominio.entidades.Dispositivo;

/**
 * Created by jeriel on 11/7/15.
 */
public class RepositorioDispositivo {

    private SQLiteDatabase conn;

    public RepositorioDispositivo(SQLiteDatabase conn) {

        this.conn = conn;
    }

    private ContentValues preencheContentValues(Dispositivo dispositivo){

        ContentValues values = new ContentValues();

        values.put(Dispositivo.NOME       , dispositivo.getNome());
        values.put(Dispositivo.DISPOSITIVO   , dispositivo.getDispositivo());


        return values;

    }
    public void excluir(long id){

        conn.delete(Dispositivo.TABELA, " _id = ?", new String[]{String.valueOf(id)});
    }

    public void alterar(Dispositivo dispositivo) {


        ContentValues values = preencheContentValues(dispositivo);

        conn.update(Dispositivo.TABELA, values, " _id = ? ", new String[]{String.valueOf(dispositivo.getId())});
    }

    public void inserir(Dispositivo dispositivo){

        ContentValues values = preencheContentValues(dispositivo);
        conn.insertOrThrow(Dispositivo.TABELA, null, values);

    }

    public ArrayAdapter<Dispositivo> buscaDispositivos(Context context){

        ArrayAdapter<Dispositivo> adbDispositivo = new ArrayAdapter<Dispositivo>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conn.query(Dispositivo.TABELA, null, null, null, null, null, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do {

                Dispositivo dispositivo = new Dispositivo();

                dispositivo.setId(cursor.getLong(cursor.getColumnIndex(dispositivo.ID)));
                dispositivo.setNome(cursor.getString(cursor.getColumnIndex(dispositivo.NOME)));
                dispositivo.setDispositivo(cursor.getString(cursor.getColumnIndex(dispositivo.DISPOSITIVO)));

                adbDispositivo.add(dispositivo);

            }while (cursor.moveToNext());

        }

        return adbDispositivo;
    }
}
