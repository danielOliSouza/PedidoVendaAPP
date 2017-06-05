package br.com.adsddm.pedidovenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.adsddm.pedidovenda.R;
import br.com.adsddm.pedidovenda.model.Produto;

/**
 * Created by Daniel on 04/06/2017.
 */

public class ProdutoAdapter extends BaseAdapter{
    private Context ctx;
    private List<Produto> produtos;

    public ProdutoAdapter(Context ctx, List<Produto> produtos){
        super();
        this.ctx = ctx;
        this.produtos = produtos;
    }
    @Override
    public int getCount(){
        return  produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.adapter_produto, parent, false);

        Produto item = produtos.get(position);
        TextView descricao =(TextView) v.findViewById(R.id.colDescricao);
        TextView preco = (TextView) v.findViewById(R.id.colPreco);

        descricao.setText(item.getNome());
        preco.setText(String.valueOf(item.getPreco()));
        return v;
    }
}
