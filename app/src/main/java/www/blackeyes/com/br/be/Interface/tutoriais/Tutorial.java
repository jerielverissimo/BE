package www.blackeyes.com.br.be.Interface.tutoriais;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import www.blackeyes.com.br.be.R;

public class Tutorial extends AppCompatActivity implements View.OnClickListener{

    Button btnTutoCad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        btnTutoCad = (Button)findViewById(R.id.btnTutoCad);

        btnTutoCad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnTutoCad:

                Intent intent = new Intent(this, TutoCad.class);
                startActivity(intent);
                break;
        }
    }
}
