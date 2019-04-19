package com.unibave.catalogoprodutos.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unibave.catalogoprodutos.R;
import com.unibave.catalogoprodutos.model.DatabaseRepository;
import com.unibave.catalogoprodutos.model.Produto;

import java.util.UUID;


public class CadastrarController extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        Button buttonSave = (Button)findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseRepository crud = new DatabaseRepository(getBaseContext());
                Produto p = new Produto();
                EditText titulo = (EditText)findViewById(R.id.editText);
                EditText autor = (EditText)findViewById((R.id.editText2));
                EditText editora = (EditText)findViewById(R.id.editText3);
                p.setId(UUID.randomUUID().toString());
                p.setTitulo(titulo.getText().toString());
                p.setAutor(autor.getText().toString());
                p.setEditora(editora.getText().toString());

                             String resultado = crud.insereDado(p.getTitulo(),p.getAutor(),p.getEditora());
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(CadastrarController.this, ConsultaController.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
