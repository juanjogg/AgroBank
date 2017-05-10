package juan.findbank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JuanJoseGomezGuarin on 30/04/2017.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Banco(oficina text primary key, departamento text, horario_atencion text, dias_de_apertura text, dias_de_cierre text, direccion text, indicativo integer, telefono text, numero_telefonico_no_1 text, numero_telefonico_no_2 text, numero_telefonico_no_3 text, numero_telefonico_no_4 text,numero_telefonico_no_5 text, numero_telefonico_no_6 text  )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists banco");
        db.execSQL("create table Banco(oficina text primary key, departamento text, horario_atencion text, dias_de_apertura text, dias_de_cierre text, direccion text, indicativo integer, telefono text, numero_telefonico_no_1 text, numero_telefonico_no_2 text, numero_telefonico_no_3 text, numero_telefonico_no_4 text,numero_telefonico_no_5 text, numero_telefonico_no_6 text  )");

    }
}
