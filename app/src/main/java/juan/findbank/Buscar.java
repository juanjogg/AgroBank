package juan.findbank;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Buscar extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner spinner;
    EditText editText2;
    Button buscar;
    String [] dptos;
    AdapterSQLite adapter;
    TextView text;
    ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        spinner=(Spinner) findViewById(R.id.spinner);
        editText2=(EditText) findViewById(R.id.editText2);
        editText2.setText("");
        adapter=new AdapterSQLite(this);
        dptos=adapter.obtenerDptos();
        adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dptos);
        spinner.setAdapter(adaptador);
        spinner.setOnItemSelectedListener(this);
        buscar=(Button) findViewById(R.id.button);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String dpto=""+text.getText();
                    String municipio=""+editText2.getText();
                    String[]r=adapter.obtenerOficina(dpto.toUpperCase(),municipio.toUpperCase());
                    Mensaje.mensaje(getApplicationContext(), r[0]);
                }
                catch(NullPointerException e){
                    Mensaje.mensaje(getApplicationContext(), "Por favor complete ambos campos");
                }
            }
        });

    }







    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text=(TextView) view;
        Mensaje.mensaje(this,"Seleccionaste: "+text.getText());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
