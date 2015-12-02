package www.blackeyes.com.br.be.Interface;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import www.blackeyes.com.br.be.R;
import www.blackeyes.com.br.be.logica.FalaTexto;


public class Acao extends FalaTexto implements View.OnClickListener {

    Button btnAlterar, btnBuscar;
    ProgressBar progressBar;
    TextView txtResultado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acao);

        btnAlterar = (Button)findViewById(R.id.btnAlterar);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        btnAlterar.setOnClickListener(this);
        btnBuscar.setOnClickListener(this);
        txtResultado = (TextView)findViewById(R.id.txtResultado);
    }

    public void onClick(View v){

      switch (v.getId()) {

          case R.id.btnAlterar:
              final Bundle bundle = getIntent().getExtras();
            if ((bundle != null) && (bundle.containsKey("DISPOSITIVO"))) {

                Intent it = new Intent(Acao.this, Cadastro.class);

                it.putExtra("lista", bundle.getSerializable("DISPOSITIVO"));
                startActivityForResult(it, 0);
                finish();
            }

          break;

          case R.id.btnBuscar:

            progressBar.setVisibility(View.VISIBLE);
            txtResultado.setText("Buscando... ");
            tts.speak("Buscando dispositivo...", TextToSpeech.QUEUE_FLUSH, null);
          break;

      }
    }
}
