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

    public String insert(Produto domain){
        ContentValues values;
        long resultado;

       db = banco.getWritableDatabase();
       values = new ContentValues();
       values.put(banco.NOME, domain.getNome());
       values.put(banco.FORNECEDOR, domain.getFornecedor());
       values.put(banco.VALOR, domain.getValor());

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

    public String update(Produto domain){
        ContentValues values;
        String where;

        db = banco.getWritableDatabase();

        where = Database.ID + "=" + domain.getId();

        values = new ContentValues();
        values.put(banco.NOME, domain.getNome());
        values.put(banco.FORNECEDOR, domain.getFornecedor());
        values.put(banco.VALOR, domain.getValor());

        long resultado = db.update(Database.TABELA,values,where,null);
        if (resultado ==-1)
            return "Erro ao atualizar registro";
        else
            return "Registro atualizado com sucesso";
    }

    public String remove(int id){
        String where = Database.ID + "=" + id;
        db = banco.getReadableDatabase();
        long resultado = db.delete(Database.TABELA,where,null);

        if (resultado ==-1)
            return "Erro ao deletar registro";
        else
            return "Registro deletado com sucesso";
    }
}
