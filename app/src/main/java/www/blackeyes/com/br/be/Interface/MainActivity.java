package www.blackeyes.com.br.be.Interface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import www.blackeyes.com.br.be.R;

public class MainActivity extends AppCompatActivity {

    Button btnCadastro, btnProcura, btnTutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastro = (Button)findViewById(R.id.btnCadastro);
        btnProcura = (Button)findViewById(R.id.btnProcura);
        btnTutorial = (Button)findViewById(R.id.btnTutorial);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro.class);
                startActivity(intent);
            }
        });

        btnProcura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Dispositivos.class);
                startActivity(intent);
            }
        });

        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Tutorial.class);
                startActivity(intent);

            }
        });
    }
}
