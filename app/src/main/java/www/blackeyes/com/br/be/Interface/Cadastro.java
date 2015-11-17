package www.blackeyes.com.br.be.Interface;


import android.app.AlertDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

import www.blackeyes.com.br.be.R;
import www.blackeyes.com.br.be.database.DataBase;
import www.blackeyes.com.br.be.dominio.entidades.Dispositivo;
import www.blackeyes.com.br.be.dominio.repositorioDispositivo;
import www.blackeyes.com.br.be.logica.FalaTexto;

public class Cadastro extends FalaTexto {

    Button btnExcluir, btnCadastrar;
    TextView txtNome;
    ListView lstDispositivos;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private repositorioDispositivo repositorioDispositivo;
    private Dispositivo dispositivo;

    private ArrayAdapter<String> adpDispositivos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        txtNome = (TextView) findViewById(R.id.edtNome);
        lstDispositivos = (ListView) findViewById(R.id.lstDispositivos);

        adpDispositivos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);


        Snackbar.make(toolbar, getString(R.string.cadastroHelp), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Primeiro selecione um dispositivo disponivel na parte superior", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey("lista"))) {

            dispositivo = (Dispositivo) bundle.getSerializable("lista");
            preencheDados();

        } else
            dispositivo = new Dispositivo();


        try {

            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioDispositivo = new repositorioDispositivo(conn);


        } catch (SQLException ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o bd " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();

        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                salvar();

                finish();

            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                excluir();

                finish();
            }
        });

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.speak(getString(R.string.cadastroHelp), TextToSpeech.QUEUE_ADD, null);
        }
    }

    private void preencheDados() {

        txtNome.setText(dispositivo.getNome());
        //lstDispositivos.setSelection(Integer.parseInt(dispositivo.getDispositivo()));

    }

    private void excluir() {

        try {

            repositorioDispositivo.excluir(dispositivo.getId());

        } catch (Exception ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao excluir os dados " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();

        }
    }


    private void salvar() {


        try {


            dispositivo.setNome(txtNome.getText().toString());
            dispositivo.setDispositivo("teste");

            if (dispositivo.getId() == 0)
                repositorioDispositivo.inserir(dispositivo);
            else
                repositorioDispositivo.alterar(dispositivo);

        } catch (Exception ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao inserir os dados " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();

        }
    }


}
