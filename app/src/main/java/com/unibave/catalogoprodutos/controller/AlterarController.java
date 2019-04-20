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
import com.unibave.catalogoprodutos.model.ProdutoDatabaseRepository;


public class AlterarController extends Activity {
    EditText nome;
    EditText fornecedor;
    EditText valor;
    Button buttonDelete;
    Button buttonBack;
    Button buttonAlter;
    Cursor cursor;
    ProdutoDatabaseRepository crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");
        crud = new ProdutoDatabaseRepository(getBaseContext());
        nome = (EditText)findViewById(R.id.nome);
        fornecedor = (EditText)findViewById(R.id.fornecedor);
        valor = (EditText)findViewById(R.id.valor);

        buttonAlter = (Button)findViewById(R.id.buttonAlter);
        buttonDelete = (Button)findViewById(R.id.buttonDelete);
        buttonBack = (Button)findViewById(R.id.buttonBack);

        cursor = crud.findById(Integer.parseInt(codigo));
        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow(Database.NOME)));
        fornecedor.setText(cursor.getString(cursor.getColumnIndexOrThrow(Database.FORNECEDOR)));
        valor.setText(cursor.getString(cursor.getColumnIndexOrThrow(Database.VALOR)));

        buttonAlter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.update(Integer.parseInt(codigo), nome.getText().toString(), fornecedor.getText().toString(),
                        valor.getText().toString());
                Intent intent = new Intent(AlterarController.this, ConsultaController.class);
                startActivity(intent);
                finish();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlterarController.this,ConsultaController.class);
                startActivity(intent);
                finish();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.remove(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterarController.this, ConsultaController.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
