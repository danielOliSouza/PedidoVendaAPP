package br.com.adsddm.pedidovenda.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.adsddm.pedidovenda.dadosview.DadosView;
import br.com.adsddm.pedidovenda.model.Produto;

public class ProdutoRepository extends Repository{

    public ProdutoRepository(Context context) {
        super(context);
    }

    public Produto guardar(Produto produto){
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        produtos = guardar(produtos);

        return produtos.get(0);
    }

    public List<Produto> guardar(List<Produto> produtos) {
        SQLiteDatabase db = getWritableDatabase();

        try {
            for(int i = 0; i < produtos.size(); i++){
                Produto produto = produtos.get(i);
                Long id = produto.get_id();
                ContentValues values = new ContentValues();

                values.put("id_servidor", produto.getIdServidor());
                values.put("nome", produto.getNome());
                values.put("preco", produto.getPreco());

                if (id != 0) {
                    String _id = String.valueOf(produto.get_id());
                    String[] where = new String[]{_id};
                    db.update("produto", values, "_id=?", where);
                }
                else {
                    id = db.insert("produto", "", values);
                    produto.set_id(id);
                }
            }

        }catch (Exception e){
            Log.e(DadosView.TAG_APP, e.getMessage());
        }finally {
            db.close();
        }
        return produtos;
    }

    public List<Produto> pesquisar() {
        SQLiteDatabase db = getWritableDatabase();

        try {
            Cursor cursor = db.query("produto", null, null, null, null, null, null);
            return toList(cursor);
        } finally {
            db.close();
        }
    }

    public void deleteAll() throws Exception{
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        try {
            values.put("seq",0);
            db.delete("produto",null,null);
            db.update("sqlite_sequence", values, "name=?", new String[]{"produto"});
        } finally {
            db.close();
        }
    }
    private List<Produto> toList(Cursor cursor) {
        List<Produto> produtos = new ArrayList<Produto>();

        if (cursor.moveToFirst()) {
            do {
                Produto produto = new Produto();
                produtos.add(produto);

                produto.set_id(cursor.getLong(cursor.getColumnIndex("_id")));
                produto.setIdServidor(cursor.getLong(cursor.getColumnIndex("id_servidor")));
                produto.setPreco(cursor.getFloat(cursor.getColumnIndex("preco")));
                produto.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            } while (cursor.moveToNext());
        }
        return produtos;
    }
}
