package www.blackeyes.com.br.be.app;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by jeriel on 2015/11/30.
 */
public class MessageBox {

    public static String show(Context ctx, String title, String msg){

        AlertDialog.Builder dlg = new AlertDialog.Builder(ctx);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setNeutralButton("OK", null);
        dlg.show();
        return msg;
    }
}
