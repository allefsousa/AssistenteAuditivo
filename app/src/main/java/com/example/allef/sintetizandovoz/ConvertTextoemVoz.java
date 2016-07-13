package com.example.allef.sintetizandovoz;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class ConvertTextoemVoz extends Activity {
    TextToSpeech textToSpeech;
    ImageView btnBoraFalar;
    EditText editexto;
    Button btnTeste;
    Intent localIntent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textoem_voz);
        editexto = (EditText)findViewById(R.id.txtTexto);

        btnTeste = (Button)findViewById(R.id.teste);
        btnBoraFalar = (ImageView)findViewById(R.id.boraFalar);

       textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.getDefault());
                }
            }
        });

        btnBoraFalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String falar = ""+editexto.getText().toString();
                Toast.makeText(getApplicationContext(),falar, Toast.LENGTH_SHORT).show();
                textToSpeech.speak(falar,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        btnTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mecanismo para configurar a fala
               localIntent.setAction("android.speech.tts.engine.INSTALL_TTS_DATA");
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.setClassName("com.android.settings", "com.android.settings.LanguageSettings.");
                //Configuração de Idioma e logo apos de audio
                Intent i = new Intent( android.provider.Settings.ACTION_LOCALE_SETTINGS );
                //startActivity(localIntent);
                startActivity(localIntent);
            }
        });
    }
    public void onPause(){
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();

        }
        super.onPause();
    }
    // mudando a ação do botão voltar (FISICO) do android
    public void onBackPressed()
    {
        Intent intent = new Intent();
        intent.setClass(ConvertTextoemVoz.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
