package br.com.adsddm.pedidovenda.reqServer;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;

import br.com.adsddm.pedidovenda.dadosview.DadosView;

public class PedidoVendaReqServer{

    public String enviaPedidoVenda(String param) throws Exception {
        InputStream stream = null;
        HttpURLConnection connection = null;
        String result = null;
        String myUrl= DadosView.URL_SERVIDOR +  "PedidoVenda/pedidovenda?json=";
        URL url = null;

        try{
            url = new URL(myUrl + URLEncoder.encode(param,"UTF-8"));
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5*1000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);

            connection.connect();
            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw  new ConnectException("Erro HTTP Code: " + connection.getResponseCode());
            }

            stream = connection.getInputStream();

            if(stream != null){
                result = readStream(stream);
            }
        }catch (SocketTimeoutException e){
            throw new Exception("Tempo de Conecxão Com Servidor Expirado");
        }catch (ConnectException e){
            throw new Exception("Servidor indisponivel no momento");
        }catch (Exception e){
            Log.e(DadosView.TAG_APP, "Connect NetWork: " + e.getMessage());
            throw  new Exception("Não foi Possivel Conectar Com o Servidor");
        } finally {
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
