package com.unibave.catalogoprodutos.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.unibave.catalogoprodutos.R;
import com.unibave.catalogoprodutos.model.Database;
import com.unibave.catalogoprodutos.model.DatabaseRepository;


public class AlterarController extends Activity {
    EditText livro;
    EditText autor;
    EditText editora;
    Button alterar;
    Button deletar;
    Cursor cursor;
    DatabaseRepository crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");
        crud = new DatabaseRepository(getBaseContext());
        livro = (EditText)findViewById(R.id.editText4);
        autor = (EditText)findViewById(R.id.editText5);
        editora = (EditText)findViewById(R.id.editText6);

        alterar = (Button)findViewById(R.id.button2);
        deletar = (Button)findViewById(R.id.button3);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        livro.setText(cursor.getString(cursor.getColumnIndexOrThrow(Database.TITULO)));
        autor.setText(cursor.getString(cursor.getColumnIndexOrThrow(Database.AUTOR)));
        editora.setText(cursor.getString(cursor.getColumnIndexOrThrow(Database.EDITORA)));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.alteraRegistro(Integer.parseInt(codigo), livro.getText().toString(),autor.getText().toString(),
                        editora.getText().toString());
                Intent intent = new Intent(AlterarController.this, ConsultaController.class);
                startActivity(intent);
                finish();
            }
        });

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterarController.this, ConsultaController.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
