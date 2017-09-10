package com.example.dani.quizseries.DB;

/**
 * Created by dani on 10/09/17.
 */

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper{

    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA_USER = "user";
    private static final String ID = "_id";
    private static final String ID_USER = "id_user";
    private static final String NOME_USER = "nome";

    //campos da tabela questions
    private static final String TABELA_QUESTION = "questions";
    private static final String  LABEL_QUESTION= "label";
    private static final String  OPC1_QUESTION= "opc1";
    private static final String  OPC2_QUESTION= "opc2";
    private static final String  OPC3_QUESTION= "opc3";
    private static final String  OPC4_QUESTION= "opc4";
    private static final String  CORRETA_QUESTION= "correta";
    private static final String  RESPONDEU_QUESTION= "respondeu";
    private static final String  ACERTOU_QUESTION= "acertou";



    private static final int VERSAO = 1;


    public CriaBanco(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, NOME_BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //cria tabela do usuário
        String sql = "CREATE TABLE"+TABELA_USER+"("
                + ID + "integer primary key autoincrement,"
                + ID_USER + "text,"
                + NOME_USER + "text"

                +")";

        db.execSQL(sql);

        //cria tabela de questoes
        String sql_questions = "CREATE TABLE"+TABELA_QUESTION+"("
                + ID + "integer primary key autoincrement,"
                + LABEL_QUESTION + "text,"
                + OPC1_QUESTION + "text,"
                + OPC2_QUESTION + "text,"
                + OPC3_QUESTION + "text,"
                + OPC4_QUESTION + "text,"
                + CORRETA_QUESTION + "text,"
                + RESPONDEU_QUESTION + "integer default 0,"
                + ACERTOU_QUESTION + "integer"

                +")";

        db.execSQL(sql_questions);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if(newVersion != oldVersion){
//
//            onCreate(db);
//        }
    }

}


