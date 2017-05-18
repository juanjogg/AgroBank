package juan.findbank;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button iniciar;
    int estado=0;

    AdapterSQLite.AdminSQLiteOpenHelper helper;
    SQLiteDatabase db;
    AdapterSQLite adapter;
    ProgressBar barra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        barra=(ProgressBar) findViewById(R.id.barra);
        barra.setProgress(0);
        helper=new AdapterSQLite.AdminSQLiteOpenHelper(this);
        adapter=new AdapterSQLite(this);
        db=helper.getWritableDatabase();
        setContentView(R.layout.activity_main);
        this.iniciar=(Button) findViewById(R.id.buttonIniciar2);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iniciar.setVisibility(View.INVISIBLE);


                    alta(v);

                    Intent comenzar = new Intent(MainActivity.this, Buscar.class);
                    startActivity(comenzar);

                }
                catch (Exception e){
                    Log.e("Error",e.getMessage());
                }



            }
        });


    }
    public static final List<String[]> readCSV(Context context){
        List<String[]> questionList=new ArrayList<>();
        AssetManager assetManager=context.getAssets();
        try{
            InputStream csvStream=assetManager.open("archivo/documentocsv.csv");
            InputStreamReader csvStreamReader= new InputStreamReader(csvStream);
            CSVReader csvReader=new CSVReader(csvStreamReader);

            String[] line;
            csvReader.readNext();
            while ((line=csvReader.readNext())!=null){
                questionList.add(line);
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }

        return questionList;
    }



    public  void alta(View v){

        List lista=readCSV(this);

        String row[];
        String ar[]={"oficina","departamento","horario_atencion","dias_de_apertura","dias_de_cierre","direccion","indicativo","telefono","numero_telefonico_no_1","numero_telefonico_no_2","numero_telefonico_no_3","numero_telefonico_no_4","numero_telefonico_no_5","numero_telefonico_no_6"};



        //String n=this.name.getText().toString();

        long id=0;
        for(Object o: lista){
            row=(String[]) o;


                id=this.adapter.insetarDatos(row);




        }

        

        Toast.makeText(this,id+"",Toast.LENGTH_SHORT).show();






    }



    public class Asynctask_cargar extends AsyncTask<Void,Integer,Void>{
        @Override
        protected void onPreExecute(){
            estado=0;
            iniciar.setVisibility(View.INVISIBLE);
            barra.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            while(estado<100){
                estado++;
                publishProgress(estado);
                SystemClock.sleep(20);
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer...values){
            barra.setProgress(values[0]);
        }
        @Override
        protected void onPostExecute(Void result){

        }


    }





}


