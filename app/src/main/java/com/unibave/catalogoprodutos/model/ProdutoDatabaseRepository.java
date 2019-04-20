package com.unibave.catalogoprodutos.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class ProdutoDatabaseRepository {

    private SQLiteDatabase db;
    private Database banco;

    public ProdutoDatabaseRepository(Context context){
        banco = new Database(context);
    }

    public String insert(String nome, String fornecedor, String valor){
        ContentValues values;
        long resultado;

       db = banco.getWritableDatabase();
       values = new ContentValues();
       values.put(banco.NOME, nome);
       values.put(banco.FORNECEDOR, fornecedor);
       values.put(banco.VALOR, valor);

       resultado = db.insertOrThrow(banco.TABELA, null, values);
       db.close();

       if (resultado ==-1)
           return "Erro ao inserir registro";
       else
           return "Registro Inserido com sucesso";



    }

    public Cursor findAll(){
            Cursor cursor;
            String[] campos =  {banco.ID,banco.NOME};
            db = banco.getReadableDatabase();
            cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

            if(cursor!=null){
                cursor.moveToFirst();
            }

            return cursor;
    }

    public Cursor findById(int id){
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

    public void update(int id, String nome, String fornecedor, String valor){
        ContentValues values;
        String where;

        db = banco.getWritableDatabase();

        where = Database.ID + "=" + id;

        values = new ContentValues();
        values.put(banco.NOME, nome);
        values.put(banco.FORNECEDOR, fornecedor);
        values.put(banco.VALOR, valor);

        db.update(Database.TABELA,values,where,null);
    }

    public void remove(int id){
        String where = Database.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(Database.TABELA,where,null);
    }
}
