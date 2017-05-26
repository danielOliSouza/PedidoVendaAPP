package br.com.adsddm.pedidovenda.reqServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.*;


/**
 * Created by Daniel on 25/05/2017.
 */

public class PedidoVendaReqServer {

    public String enviaPedidoVenda() {
        InputStream stream = null;
        HttpURLConnection  connection = null;
        String result = null;
        String myUrl= "http://viacep.com.br/ws/74916100/json"; // "http://192.168.1.5:8080/PedidoVenda/pedidovenda";
        URL url = null;

        try{
            url = new URL(myUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10*1000);//10s
            connection.setConnectTimeout(10*1000);//10s

            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw  new Exception("Erro HTTP Code: " + connection.getResponseCode());
            }

            stream = connection.getInputStream();

            if(stream != null){
                result = readStream(stream);
            }
        }catch (Exception e){
            return "Erro: " + e.getMessage();
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
            total.append(line);
        }
        total.append("OI");
        return total.toString();
    }

}
