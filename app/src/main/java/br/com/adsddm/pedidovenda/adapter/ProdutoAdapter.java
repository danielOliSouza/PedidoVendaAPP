package br.com.adsddm.pedidovenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.support.v7.appcompat.*;
import android.widget.TextView;
import br.com.adsddm.pedidovenda.R;

/**
 * Created by Daniel on 24/05/2017.
 */

public class ProdutoAdapter extends BaseAdapter {
    private String[] produtos = new String[] {"Produto A", "Produto B", "Produto C", "Produto D", "Produto E", "Produto F"};
    private Context ctx;

    public ProdutoAdapter(Context ctx){
        super();
        this.ctx = ctx;
    }
    @Override
    public int getCount(){
        return  produtos.length;
    }

    @Override
    public Object getItem(int position) {
        return produtos[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.adapter_produto, parent, false);
        String produto = produtos[position];
        TextView descricao =(TextView) v.findViewById(R.id.tdDescricao);
        TextView qtd = (TextView) v.findViewById(R.id.tdQtd);
        TextView preco = (TextView) v.findViewById(R.id.tdPreco);

        descricao.setText(produto);
        qtd.setText(String.valueOf(position));
        preco.setText(String.valueOf(position));
        return v;
    }
}
