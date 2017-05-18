package juan.findbank;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by JuanJoseGomezGuarin on 30/04/2017.
 */



public class AdapterSQLite{
    AdminSQLiteOpenHelper helper;
    SQLiteDatabase db;
    AdapterSQLite(Context c){
        helper=new AdminSQLiteOpenHelper(c);
        db=helper.getWritableDatabase();
    }
    public long insetarDatos(String[] row){
        ContentValues registros=new ContentValues();
        db=helper.getWritableDatabase();
        registros.put(AdminSQLiteOpenHelper.ar[0], row[0]);
        registros.put(AdminSQLiteOpenHelper.ar[1], row[1]);
        registros.put(AdminSQLiteOpenHelper.ar[2], row[2]);
        registros.put(AdminSQLiteOpenHelper.ar[3], row[3]);
        registros.put(AdminSQLiteOpenHelper.ar[4], row[4]);
        registros.put(AdminSQLiteOpenHelper.ar[5], row[5]);
        registros.put(AdminSQLiteOpenHelper.ar[6], row[6]);
        registros.put(AdminSQLiteOpenHelper.ar[7], row[7]);
        registros.put(AdminSQLiteOpenHelper.ar[8], row[8]);
        registros.put(AdminSQLiteOpenHelper.ar[9], row[9]);
        registros.put(AdminSQLiteOpenHelper.ar[10], row[10]);
        registros.put(AdminSQLiteOpenHelper.ar[11], row[11]);
        registros.put(AdminSQLiteOpenHelper.ar[12], row[12]);
        registros.put(AdminSQLiteOpenHelper.ar[13], row[13]);
        long id=db.insert(AdminSQLiteOpenHelper.TABLE_NAME,null,registros);
        db.close();
        return id;
    }
    public String[] obtenerDptos(){
        SQLiteDatabase db=helper.getWritableDatabase();
        String[] columns={AdminSQLiteOpenHelper.ar[1]};
        Cursor c=db.query(true,AdminSQLiteOpenHelper.TABLE_NAME,columns,null,null,null,null,AdminSQLiteOpenHelper.ar[1]+" ASC ",null);


        String [] deps=new String[33];
        int contador=0;
        StringBuffer buf=new StringBuffer();
        while(c.moveToNext()){


            int dpto=c.getColumnIndex(AdminSQLiteOpenHelper.ar[1]);



            deps[contador]=c.getString(dpto);

            buf.append( deps[contador]+" \n");

            contador++;
        }


        return deps;
    }
    public String[] obtenerOficina(String dpto,String municipio) {
        String[] r=new String[5];
        try {

            SQLiteDatabase db = helper.getWritableDatabase();
            String[] columns = {AdminSQLiteOpenHelper.ar[0],AdminSQLiteOpenHelper.ar[2], AdminSQLiteOpenHelper.ar[3], AdminSQLiteOpenHelper.ar[5], AdminSQLiteOpenHelper.ar[7]};
            Cursor c = db.query(AdminSQLiteOpenHelper.TABLE_NAME, columns, AdminSQLiteOpenHelper.ar[1]+"= '"+dpto+"' AND "+AdminSQLiteOpenHelper.ar[0]+" LIKE '%"+municipio+"%'",null,null,null,AdminSQLiteOpenHelper.ar[0]+" ASC ");
            int cont=0;
            while (c.moveToNext()){
                int index[]={c.getColumnIndex(AdminSQLiteOpenHelper.ar[0]),c.getColumnIndex(AdminSQLiteOpenHelper.ar[2]),c.getColumnIndex(AdminSQLiteOpenHelper.ar[3]),c.getColumnIndex(AdminSQLiteOpenHelper.ar[5]),c.getColumnIndex(AdminSQLiteOpenHelper.ar[7])};

                r[0]=c.getString(index[0]);
                r[1]=c.getString(index[1]);
                r[2]=c.getString(index[2]);
                r[3]=c.getString(index[3]);
                r[4]=c.getString(index[4]);


            }

        }
        catch(SQLException e){

        }
        return r;
    }
    public ArrayList obtenerOficinas(String departamento){
        SQLiteDatabase db=helper.getWritableDatabase();
        String[] colums={AdminSQLiteOpenHelper.ar[0]};
        Cursor c=db.query(AdminSQLiteOpenHelper.TABLE_NAME,colums,AdminSQLiteOpenHelper.ar[1]+" = '"+departamento+"'",null,null,null,AdminSQLiteOpenHelper.ar[0]+" ASC ");
        ArrayList<String> oficinas=new ArrayList<>();
        //int contador=0;
        while (c.moveToNext()){
            int oficina=c.getColumnIndex(AdminSQLiteOpenHelper.ar[0]);
            oficinas.add(c.getString(oficina));
            //contador++;
        }
        return oficinas;
    }
    public static class AdminSQLiteOpenHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME="Bancos";
        private static final String TABLE_NAME="Banco";
        private static final int DATABASE_VERSION=12;
        private static final String ar[]={"oficina","departamento","horario_atencion","dias_de_apertura","dias_de_cierre","direccion","indicativo","telefono","numero_telefonico_no_1","numero_telefonico_no_2","numero_telefonico_no_3","numero_telefonico_no_4","numero_telefonico_no_5","numero_telefonico_no_6"};
        private Context context;
        public AdminSQLiteOpenHelper(Context context) {
            super(context, DATABASE_NAME,null,DATABASE_VERSION);
            this.context=context;
            Mensaje.mensaje(context,"Ingreso al constructor");

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("create table Banco(oficina text primary key, departamento text, horario_atencion text, dias_de_apertura text, dias_de_cierre text, direccion text, indicativo integer, telefono text, numero_telefonico_no_1 text, numero_telefonico_no_2 text, numero_telefonico_no_3 text, numero_telefonico_no_4 text,numero_telefonico_no_5 text, numero_telefonico_no_6 text  )");
                Mensaje.mensaje(this.context, "Ingreso al Oncreate");
            }
            catch (SQLException e){
                Mensaje.mensaje(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL("drop table if exists banco");
                db.execSQL("create table Banco(oficina text primary key, departamento text, horario_atencion text, dias_de_apertura text, dias_de_cierre text, direccion text, indicativo integer, telefono text, numero_telefonico_no_1 text, numero_telefonico_no_2 text, numero_telefonico_no_3 text, numero_telefonico_no_4 text,numero_telefonico_no_5 text, numero_telefonico_no_6 text  )");
                Mensaje.mensaje(this.context,"Ingreso al OnUpgrade");
            }
            catch (SQLException e){
                Mensaje.mensaje(context,e+"");
            }

        }
    }
}
