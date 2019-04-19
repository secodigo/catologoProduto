package com.unibave.catalogoprodutos.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseRepository {

    private SQLiteDatabase db;
    private Database banco;

    public DatabaseRepository(Context context){
        banco = new Database(context);
    }

    public String insereDado(String nome, String fornecedor, String valor){
        ContentValues valores;
        long resultado;

       db = banco.getWritableDatabase();
       valores = new ContentValues();
       valores.put(banco.NOME, nome);
       valores.put(banco.FORNECEDOR, fornecedor);
       valores.put(banco.VALOR, valor);

       resultado = db.insertOrThrow(banco.TABELA, null, valores);
       db.close();

       if (resultado ==-1)
           return "Erro ao inserir registro";
       else
           return "Registro Inserido com sucesso";



    }

    public Cursor carregaDados(){
            Cursor cursor;
            String[] campos =  {banco.ID,banco.NOME};
            db = banco.getReadableDatabase();
            cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

            if(cursor!=null){
                cursor.moveToFirst();
            }

            return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOME,banco.FORNECEDOR,banco.VALOR};
        String where = Database.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(Database.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public void alteraRegistro(int id, String nome, String fornecedor, String valor){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = Database.ID + "=" + id;

        valores = new ContentValues();
        valores.put(banco.NOME, nome);
        valores.put(banco.FORNECEDOR, fornecedor);
        valores.put(banco.VALOR, valor);

        db.update(Database.TABELA,valores,where,null);
    }

    public void deletaRegistro(int id){
        String where = Database.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(Database.TABELA,where,null);
    }
}
