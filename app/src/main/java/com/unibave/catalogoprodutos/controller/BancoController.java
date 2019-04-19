package com.unibave.catalogoprodutos.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import com.unibave.catalogoprodutos.view.CriaBanco;

/**
 * Created by allanromanato on 5/28/15.
 */
public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String insereDado(String titulo, String autor, String editora){
        ContentValues valores;
        long resultado;

       db = banco.getWritableDatabase();
       valores = new ContentValues();
       valores.put(banco.TITULO, titulo);
       valores.put(banco.AUTOR, autor);
       valores.put(banco.EDITORA, editora);

       resultado = db.insertOrThrow(banco.TABELA, null, valores);
       db.close();

       if (resultado ==-1)
           return "Erro ao inserir registro";
       else
           return "Registro Inserido com sucesso";



    }

    public Cursor carregaDados(){
            Cursor cursor;
            String[] campos =  {banco.ID,banco.TITULO};
            db = banco.getReadableDatabase();
            cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

            if(cursor!=null){
                cursor.moveToFirst();
            }

            return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.TITULO,banco.AUTOR,banco.EDITORA};
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public void alteraRegistro(int id, String titulo, String autor, String editora){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriaBanco.ID + "=" + id;

        valores = new ContentValues();
        valores.put(banco.TITULO, titulo);
        valores.put(banco.AUTOR, autor);
        valores.put(banco.EDITORA, editora);

        db.update(CriaBanco.TABELA,valores,where,null);
    }

    public void deletaRegistro(int id){
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.TABELA,where,null);
    }
}
