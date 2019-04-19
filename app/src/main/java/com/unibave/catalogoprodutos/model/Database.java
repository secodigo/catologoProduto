package com.unibave.catalogoprodutos.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "catalago_produtos";
    public static final String TABELA = "lista_produtos";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String FORNECEDOR = "fornecedor";
    public static final String VALOR = "valor";
    public static final int VERSAO = 1;

    public Database(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + FORNECEDOR + " text,"
                + VALOR + " text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }
}
