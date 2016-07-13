package com.example.allef.sintetizandovoz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    final Context context = this;
    Button btnVozemTexto;
    Button btnTextoemvoz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        btnTextoemvoz = (Button) findViewById(R.id.btnVozemTexto);
        btnVozemTexto = (Button) findViewById(R.id.btnTextoemVoz);
        // Tirar a action bar  do layout em questao
       //  getActionBar().hide();
    btnVozemTexto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,ConvertVozemTexto.class);
            startActivity(intent);
            finish();
        }
    });
        btnTextoemvoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ConvertTextoemVoz.class);
                startActivity(intent);
                finish();
            }
        });



    }
    // Evento para precionar o botao fisico de voltar duas vezes e terminar a aplicação
    private Toast toast;
    private long lastBackPressTime = 0;

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Pressione o Botão Voltar novamente para fechar o Aplicativo.", Toast.LENGTH_LONG);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }




}
