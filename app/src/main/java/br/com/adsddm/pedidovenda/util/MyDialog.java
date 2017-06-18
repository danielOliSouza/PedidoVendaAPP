package br.com.adsddm.pedidovenda.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Daniel on 17/06/2017.
 */

public class MyDialog {

    public static void confirm(Context ctxt, String title, String msg,
                               DialogInterface.OnClickListener positiveButton,
                               DialogInterface.OnClickListener negativeButton){

        AlertDialog.Builder builder = new AlertDialog.Builder(ctxt);
        builder.setTitle(title);
        builder.setMessage(msg);

        AlertDialog dialog = builder.create();
    }
}
