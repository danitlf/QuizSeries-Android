package com.example.dani.quizseries.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dani.quizseries.models.User;

/**
 * Created by dani on 10/09/17.
 */

public class UserQueries {
    private SQLiteDatabase db;
    private static final String TABELA = "user";
    private static final String ID_USER = "id_user";
    private static final String NOME_USER = "nome";

    public UserQueries(SQLiteDatabase db) {
        this.db = db;
    }

    public void insereUser(User user){
        ContentValues values = new ContentValues();
        values.put(ID_USER, user.getUser_id());
        values.put(NOME_USER, user.getName());
        db.insert(TABELA, null, values);

    }

    public User getUser(){
        Cursor cur = db.rawQuery("SELECT * FROM "+TABELA, null);

        cur.moveToFirst();
        User user = new User(cur.getString(1), cur.getString(2));

        return user;
    }

    public Boolean verificaUsuario(){
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM "+TABELA, null);
        if (cur != null) {
            cur.moveToFirst();
            if (cur.getInt (0) == 0) {
               return false;
            } else {
                return true;
            }
        }
        return false;
    }

}
