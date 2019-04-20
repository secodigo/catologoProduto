package com.unibave.catalogoprodutos.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unibave.catalogoprodutos.R;
import com.unibave.catalogoprodutos.controller.ProdutoController;
import com.unibave.catalogoprodutos.model.Produto;

import java.util.UUID;


public class CadastrarActivity extends Activity {

    Button buttonBack;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        buttonSave = (Button)findViewById(R.id.buttonSave);
        buttonBack = (Button)findViewById(R.id.buttonBack);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProdutoController controller = new ProdutoController(getBaseContext());
                Produto p = new Produto();
                EditText nome = (EditText)findViewById(R.id.nome);
                EditText fornecedor = (EditText)findViewById((R.id.fornecedor));
                EditText valor = (EditText)findViewById(R.id.valor);
                p.setId(UUID.randomUUID().toString());
                p.setNome(nome.getText().toString());
                p.setFornecedor(fornecedor.getText().toString());
                p.setValor(valor.getText().toString());

                String resultado = controller.create(p.getNome(),p.getFornecedor(),p.getValor());
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(CadastrarActivity.this, ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastrarActivity.this, ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
