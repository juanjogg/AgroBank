package juan.findbank;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by JuanJoseGomezGuarin on 2/05/2017.
 */

public class Mensaje {
    Context context;
    String mensaje;
    public static void mensaje(Context context,String mensaje){
        Toast.makeText(context,mensaje,Toast.LENGTH_SHORT).show();
    }
}
