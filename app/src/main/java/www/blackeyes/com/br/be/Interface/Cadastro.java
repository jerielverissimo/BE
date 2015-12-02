  package www.blackeyes.com.br.be.Interface;



import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

import www.blackeyes.com.br.be.R;
import www.blackeyes.com.br.be.app.MessageBox;

import www.blackeyes.com.br.be.database.DataBase;
import www.blackeyes.com.br.be.dominio.entidades.Dispositivo;
import www.blackeyes.com.br.be.dominio.RepositorioDispositivo;
import www.blackeyes.com.br.be.logica.Falar;

  public class Cadastro extends Falar implements View.OnClickListener{



      Button btnExcluir, btnCadastrar;
      ImageButton btnFalar;
      EditText txtNome;
      ListView lstDispositivos;

      private DataBase dataBase;
      private SQLiteDatabase conn;
      private RepositorioDispositivo RepositorioDispositivo;
      private Dispositivo dispositivo;

      private ArrayList<String> mDeviceList = new ArrayList<String>();
      private BluetoothAdapter mBluetoothAdapter;


      @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnFalar = (ImageButton) findViewById(R.id.btnFalar);
        txtNome = (EditText) findViewById(R.id.edtNome);
        lstDispositivos = (ListView)findViewById(R.id.lstDispositivos);


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.startDiscovery();

          IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
          registerReceiver(mReceiver, filter);

        pegaDadosIntent();

        criaBD();

        btnFalar.setOnClickListener(this);

        btnCadastrar.setOnClickListener(this);

        btnExcluir.setOnClickListener(this);

    }

    public void criaBD(){
        try {

            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            RepositorioDispositivo = new RepositorioDispositivo(conn);


        } catch (SQLException ex) {

            MessageBox.show(this,"Erro","Erro ao tentar criar o banco");

        }
    }

    public void pegaDadosIntent(){
        Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey("lista"))) {

            dispositivo = (Dispositivo) bundle.getSerializable("lista");
            preencheDados();

        } else {
            dispositivo = new Dispositivo();
        }

    }

    private void preencheDados() {

        txtNome.setText(dispositivo.getNome());
        lstDispositivos.setSelection(Integer.parseInt(dispositivo.getDispositivo()));

    }

    private void excluir() {

        try {

            RepositorioDispositivo.excluir(dispositivo.getId());
            MessageBox.show(this, "Excluir", "Dispositivo excluido");

        } catch (Exception ex) {

            MessageBox.show(this,"Erro","Erro ao tentar excluir dados");

        }
    }


    private void salvar() {

        try {

            dispositivo.setNome(txtNome.getText().toString());
            dispositivo.setDispositivo("teste");

            if (dispositivo.getId() == 0){

                RepositorioDispositivo.inserir(dispositivo);

                MessageBox.show(this, "Cadastro", "Dispositivo cadastrado");

            }

            else{

                RepositorioDispositivo.alterar(dispositivo);
                MessageBox.show(this, "Cadastro", "Dispositivo alterado");

            }

        } catch (Exception ex) {

            MessageBox.show(this,"Erro","Erro ao tentar inserir dados");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnCadastrar:

                salvar();

                break;

            case R.id.btnExcluir:

                excluir();

                break;
            case R.id.btnFalar:

                displaySpeechRecognizer();
                //txtNome.setText(feedBack(spokenText));
                break;
        }
    }

      private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
          public void onReceive(Context context, Intent intent) {
              String action = intent.getAction();
              if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                  BluetoothDevice device = intent
                          .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                  mDeviceList.add(device.getName() + "\n" + device.getAddress());
                  Log.i("BT", device.getName() + "\n" + device.getAddress());
                  lstDispositivos.setAdapter(new ArrayAdapter<String>(context,
                          android.R.layout.simple_list_item_1, mDeviceList));
              }
          }
      };
  }
