package www.blackeyes.com.br.be.Interface;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import www.blackeyes.com.br.be.R;
import www.blackeyes.com.br.be.logica.FalaTexto;

public class Apresentacao extends FalaTexto {

    Button btnAvancar;
    TextView tutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        btnAvancar = (Button)findViewById(R.id.btnAvancar);

        tutorial = (TextView)findViewById(R.id.tuto);


        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Apresentacao.this, MainActivity.class);
                startActivity(intent);

                tts.stop();
                finish();
            }
        });
    }



    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){
            tts.speak(getString(R.string.apresentacao), TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        tts.stop();

    }

}
