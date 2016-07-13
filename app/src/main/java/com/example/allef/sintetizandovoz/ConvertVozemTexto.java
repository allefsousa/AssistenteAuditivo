package com.example.allef.sintetizandovoz;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ConvertVozemTexto extends Activity {

    ImageView btnFala;
    TextView txtTextoCapturado;
    private final int Id_textto_para_voz = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_vozem_texto);

        btnFala = (ImageView) findViewById(R.id.btnSpeak);
        txtTextoCapturado = (TextView) findViewById(R.id.txtFalar);

        /**
         *
         */
        btnFala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ação de reconhecimento de voz
                Intent iVoz = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                // informando qual sera a forma da fala
                iVoz.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                // Utilizando a liguagem padrao do sistema operacional
                iVoz.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.JAPANESE);
                iVoz.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale algo Normalmente !! ");
                try {
                    startActivityForResult(iVoz, Id_textto_para_voz);
                    // startActivityForResult(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS), 3);
                } catch (ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "Seu Dispositivo Não suporta comando de voz .. ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void onActivityResult(int id,int resultCodeid,Intent dados){
        super.onActivityResult(id,resultCodeid,dados);
        switch (id){
            case Id_textto_para_voz:
                if (resultCodeid == RESULT_OK && null != dados){
                    ArrayList<String>result = dados.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String ditado = result.get(0);
                    Toast.makeText(getApplicationContext(),ditado,Toast.LENGTH_LONG).show();
                    txtTextoCapturado.setText(ditado);
                }
                break;
        }
    }
    // mudando a ação do botão voltar (FISICO) do android
    public void onBackPressed()
    {
        Intent intent = new Intent();
        intent.setClass(ConvertVozemTexto.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
