
package br.com.pdm.unemat.persistenciadadoslocal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //DECLARAR AS VARIÁVEIS
    EditText user, email, pwd;
    Button send;

    TextView txtResultado;

    //DECLARAR O ARQUIVO DE PREFERÊNCIA
    public static final String MyPREFERENCES = "arquivo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)  findViewById(R.id.username);
        email=(EditText)  findViewById(R.id.email);
        pwd=(EditText)  findViewById(R.id.password);
        send=(Button) findViewById(R.id.button);
        txtResultado=(TextView) findViewById(R.id.textView);

        //CLASSE SHAREDPREFERENCES
        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES,0);
        //SharedPreferences sharedPreferences = getSharedPreferences("arquivo", 0);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DECLARAÇÃO DE VARIÁVEIS LOCAIS
                String usuarioLocal = user.getText().toString();
                String emailLocal = email.getText().toString();
                String senhaLocal = pwd.getText().toString();

                //DECLARAÇÃO DO EDITOR - SHAREDPREFERENCES NO MODO DE EDIÇÃO
                SharedPreferences.Editor editor = sharedPreferences.edit();

                //FAZER A PERSISTÊNCIA DOS DADOS
                editor.putString("usuario", usuarioLocal);
                editor.putString("email", emailLocal);
                editor.putString("senha", senhaLocal);

                //CONFIRMAR A PERSISTÊNCIA
                editor.commit();

                //NOTIFICAÇÃO NO APP
                Toast.makeText(MainActivity.this, "Dados Cadastrados no arquivo .xml com sucesso!", Toast.LENGTH_LONG).show();

                //LIMPAR O FORMULÁRIO (GLOBAL) PARA O PRÓXIMO CADASTRO
                user.getText().clear();
                email.getText().clear();
                pwd.getText().clear();
                user.requestFocus();
            }
        });

        //RECUPERAR OS DADOS SALVOS
        SharedPreferences preferencia = getSharedPreferences(MyPREFERENCES, 0);

        if (preferencia.contains("usuario")){
            // RECUPERAR OS DSADOS
            String usuario = preferencia.getString("usuario", "Olá! Usuário não definido");
            txtResultado.setText("Olá " + usuario);

        }else {
            txtResultado.setText("Olá! Usuário não definido");
        }

    }
}

