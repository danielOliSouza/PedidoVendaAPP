package br.com.adsddm.pedidovenda.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Daniel on 16/06/2017.
 */

public abstract class Repository extends SQLiteOpenHelper{
    private static final String NOME_BD = "pedidovenda.db";
    private static final int VERSAO_BD = 1;

    public Repository(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);  // null = factory
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists produto " +
                "(_id integer primary key autoincrement, id_servidor integer, nome text, preco float);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVesion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists produto");
        onCreate(sqLiteDatabase);
    }
}
