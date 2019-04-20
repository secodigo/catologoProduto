package com.unibave.catalogoprodutos.view;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.unibave.catalogoprodutos.R;
import com.unibave.catalogoprodutos.controller.ProdutoController;
import com.unibave.catalogoprodutos.model.Database;


public class ConsultaActivity extends Activity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        ProdutoController controller = new ProdutoController(getBaseContext());
        final Cursor cursor = controller.findAll();

        String[] nomeCampos = new String[] {Database.ID, Database.NOME};
        int[] idViews = new int[] {R.id.idProduto, R.id.nomeProduto};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.produtos_layout,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(Database.ID));
                Intent intent = new Intent(ConsultaActivity.this, AlterarActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ConsultaActivity.this, CadastrarActivity.class);
                startActivity(it);
                finish();
            }
        });
    }


}
