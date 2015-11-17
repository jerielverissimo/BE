package www.blackeyes.com.br.be.logica;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;

import www.blackeyes.com.br.be.R;

/**
 * Created by jeriel on 11/7/15.
 */
public class FalaTexto extends AppCompatActivity implements TextToSpeech.OnInitListener{


    public TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts = new TextToSpeech(this, this);
    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){

        }
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        tts.stop();

    }


}