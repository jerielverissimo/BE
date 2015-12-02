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
import www.blackeyes.com.br.be.dominio.RepositorioDispositivo;

public class Dispositivos extends AppCompatActivity implements View.OnClickListener,  AdapterView.OnItemClickListener {

    Button btnBusca;
    ListView lstDispositivos;


    public ArrayAdapter<Dispositivo> adpDispositivo;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioDispositivo RepositorioDispositivo;

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

            RepositorioDispositivo = new RepositorioDispositivo(conn);

            adpDispositivo = RepositorioDispositivo.buscaDispositivos(this);

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
        adpDispositivo = RepositorioDispositivo.buscaDispositivos(this);

        lstDispositivos.setAdapter(adpDispositivo);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Dispositivo dispositivo = adpDispositivo.getItem(position);

        Intent it = new Intent(this, Acao.class);

        it.putExtra("DISPOSITIVO", dispositivo);
        startActivityForResult(it, 0);

    }


    @Override
    public void onClick(View v) {
        Intent it = new Intent(Dispositivos.this, Acao.class);
        startActivityForResult(it, 0);
    }
}
