package www.blackeyes.com.br.be.Interface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import www.blackeyes.com.br.be.Interface.tutoriais.Tutorial;
import www.blackeyes.com.br.be.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnCadastro, btnProcura, btnTutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastro = (Button)findViewById(R.id.btnCadastro);
        btnProcura = (Button)findViewById(R.id.btnProcura);
        btnTutorial = (Button)findViewById(R.id.btnTutorial);

        btnCadastro.setOnClickListener(this);

        btnProcura.setOnClickListener(this);

        btnTutorial.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnCadastro:
                Intent intent = new Intent(MainActivity.this, Cadastro.class);
                startActivity(intent);
                break;
            case R.id.btnProcura:
                intent = new Intent(MainActivity.this, Dispositivos.class);
                startActivity(intent);
                break;
            case R.id.btnTutorial:
                intent = new Intent(MainActivity.this, Tutorial.class);
                startActivity(intent);
                break;
        }
    }
}
