package br.com.adsddm.pedidovenda.reqServer;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Daniel on 04/06/2017.
 */

public class ProdutoReqServer {
    public String pegarListaProdutos(){
        InputStream stream = null;
        HttpURLConnection connection = null;
        String result = null;
        String myUrl= "http://192.168.1.6:8080/PedidoVenda/produto";
        URL url = null;

        try{
            url = new URL(myUrl);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            connection.connect();
            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw  new Exception("Erro HTTP Code: " + connection.getResponseCode());
            }

            stream = connection.getInputStream();

            if(stream != null){
                result = readStream(stream);
            }
        }catch (Exception e){
            Log.e("PEDIDOVENDA", "Erro: " + e.getMessage());
        }finally {
            if (stream != null) {
                try {
                    stream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }
    /**
     * Converts the contents of an InputStream to a String.
     */
    private String readStream(InputStream stream) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(stream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line).append("\n");
        }
        return total.toString();
    }
}
