package br.com.adsddm.pedidovenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.adsddm.pedidovenda.R;
import br.com.adsddm.pedidovenda.model.ItemPedidoVenda;

/**
 * Created by Daniel on 24/05/2017.
 */

public class ItemPedidoVendaAdapter extends BaseAdapter {
    private Context ctx;
    private List<ItemPedidoVenda> itemsPedidoVenda;

    public ItemPedidoVendaAdapter(Context ctx, List<ItemPedidoVenda> itemsPedidoVendas){
        super();
        this.ctx = ctx;
        this.itemsPedidoVenda = itemsPedidoVendas;
    }
    @Override
    public int getCount(){
        return  itemsPedidoVenda.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsPedidoVenda.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.adapter_item_pedido_venda, parent, false);
        ItemPedidoVenda item = itemsPedidoVenda.get(position);
        TextView descricao =(TextView) v.findViewById(R.id.tdDescricao);
        TextView qtd = (TextView) v.findViewById(R.id.tdQtd);
        TextView preco = (TextView) v.findViewById(R.id.tdPreco);

        descricao.setText(item.getProduto().getNome());
        qtd.setText(String.valueOf(item.getQtd()));
        preco.setText(String.valueOf(item.getProduto().getPreco()));
        return v;
    }
}
