package www.blackeyes.com.br.be.Interface;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import www.blackeyes.com.br.be.R;
import www.blackeyes.com.br.be.database.DataBase;
import www.blackeyes.com.br.be.dominio.entidades.Dispositivo;
import www.blackeyes.com.br.be.dominio.repositorioDispositivo;

public class Dispositivos extends AppCompatActivity implements View.OnClickListener,  AdapterView.OnItemClickListener {

    Button btnBusca;
    ListView lstDispositivos;

    public static String nomedisp;

    public ArrayAdapter<Dispositivo> adpDispositivo;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private repositorioDispositivo repositorioDispositivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetos);

        btnBusca = (Button)findViewById(R.id.btnProcura);
        lstDispositivos = (ListView)findViewById(R.id.lstBusca);

        lstDispositivos.setOnItemClickListener(this);

        try {

            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioDispositivo = new repositorioDispositivo(conn);

            adpDispositivo = repositorioDispositivo.buscaDispositivos(this);

            lstDispositivos.setAdapter(adpDispositivo);

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o bd " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adpDispositivo = repositorioDispositivo.buscaDispositivos(this);

        lstDispositivos.setAdapter(adpDispositivo);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Dispositivo dispositivo = adpDispositivo.getItem(position);

        Intent it = new Intent(this, Alterar.class);

        //nomedisp = dispositivo.getNome();
        it.putExtra("DISPOSITIVO", dispositivo);
        startActivityForResult(it, 0);

    }


    @Override
    public void onClick(View v) {
        Intent it = new Intent(Dispositivos.this, Alterar.class);
        startActivityForResult(it, 0);
    }
}
