package www.blackeyes.com.br.be.Interface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import www.blackeyes.com.br.be.R;
import www.blackeyes.com.br.be.dominio.entidades.Dispositivo;

public class Alterar extends Dispositivos {

    Button btnAlterar, btnBuscar;
    ProgressBar progressBar;
    private Dispositivo dispositivo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        btnAlterar = (Button)findViewById(R.id.btnAlterar);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        final Bundle bundle = getIntent().getExtras();

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ((bundle != null) && (bundle.containsKey("DISPOSITIVO"))) {

                    Intent it = new Intent(Alterar.this, Cadastro.class);

                    it.putExtra("lista", bundle.getSerializable("DISPOSITIVO"));
                    startActivityForResult(it, 0);

                }

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
}
