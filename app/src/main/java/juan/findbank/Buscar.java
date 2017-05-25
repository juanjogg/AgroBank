package juan.findbank;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Buscar extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    static Buscar b;
    Spinner spinner, spinner2;
    Button buscar;
    String [] dptos;
    TableLayout lista;
    AdapterSQLite adapter;
    TextView textd, textm;
    ArrayAdapter<String> adaptador;
    Button ubicacion;
    String[] r;
    ArrayList oficinas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        b = new Buscar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        spinner=(Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        lista = (TableLayout) findViewById(R.id.lista);
        adapter=new AdapterSQLite(this);
        dptos=adapter.obtenerDptos();
        adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dptos);
        spinner.setAdapter(adaptador);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);
        buscar=(Button) findViewById(R.id.button);
        ubicacion = (Button) findViewById(R.id.button2);
        ubicacion.setVisibility(View.INVISIBLE);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ubicacion.setVisibility(View.VISIBLE);
                    lista.removeAllViews();

                    String dpto = textd.getText() + "";
                    String municipio = "" + textm.getText();
                    r = adapter.obtenerOficina(dpto.toUpperCase(), municipio.toUpperCase());
                    TextView textView, textViewr;
                    String[] c = {"Oficina", "Horario", "Disponibilidad", "Direccion", "Telefono"};
                    b.r = r;


                    TableRow[] rows = new TableRow[5];
                    /*rows[1]=new TableRow(getBaseContext());
                    rows[2]=new TableRow(getBaseContext());
                    TableLayout.LayoutParams lp=new TableLayout.LayoutParams();
                    lp.width= TableLayout.LayoutParams.MATCH_PARENT;
                    lp.height=TableLayout.LayoutParams.WRAP_CONTENT;
                    lp.weight=0.7f;
                    rows[1].setLayoutParams(lp);

                    lp = new TableLayout.LayoutParams();
                    lp.width = TableLayout.LayoutParams.MATCH_PARENT;
                    lp.height = TableLayout.LayoutParams.WRAP_CONTENT;
                    lp.weight = 0.3f;
                    rows[2].setLayoutParams(lp);


                    TableRow.LayoutParams lptr = new TableRow.LayoutParams();
                    lptr.width = TableLayout.LayoutParams.MATCH_PARENT;
                    lptr.height = TableLayout.LayoutParams.WRAP_CONTENT;
                    textView.setLayoutParams(lp);
                    textViewr.setLayoutParams(lp);
                    */
                    //row0.addView(textView1);

                    //lista.addView(row0);

                    //Mensaje.mensaje(getApplicationContext(), r);

                    for (int j = 0; j < r.length; j++) {
                        //     if(j!=1||j!=2)
                        rows[j] = new TableRow(getBaseContext());

                        textViewr = new TextView(getBaseContext());
                        textView = new TextView(getBaseContext());
                        //textView.setGravity(Gravity.CENTER_HORIZONTAL);
                        textView.setPadding(1, 3, 1, 3);
                        textView.setBackgroundResource(R.color.colorPrimaryDark);

                        textView.setText(c[j]);
                        textView.setTextColor(Color.WHITE);
                        textViewr.setGravity(Gravity.CENTER_HORIZONTAL);
                        textViewr.setBackgroundResource(R.color.colorPrimary);
                        textViewr.setTextColor(Color.WHITE);
                        textViewr.setPadding(1, 3, 1, 3);
                        textViewr.setText(r[j]);
                        textViewr.setTextSize(13);
                        textView.setText(c[j]);
                        textView.setTextSize(13);
                        rows[j].addView(textView, 0);
                        rows[j].addView(textViewr, 1);

                        lista.setGravity(Gravity.CENTER_VERTICAL);
                        lista.addView(rows[j], j);
                    }


                } catch (Exception e) {
                    Mensaje.mensaje(getApplicationContext(), "Por favor complete ambos campos");
                    Log.e("Error", e.getMessage());
                }
            }
        });
        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapAct = new Intent(Buscar.this, MapsActivity.class);
                startActivity(mapAct);
            }
        });

    }







    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int pid = parent.getId();
        switch (pid) {
            case R.id.spinner:
                try {
                    textd = (TextView) view;
                    Mensaje.mensaje(this, "Seleccionaste: " + textd.getText());
                    oficinas = adapter.obtenerOficinas("" + textd.getText());

                    ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, oficinas);
                    adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    this.spinner2.setAdapter(adaptador2);
                    this.spinner2.setOnItemSelectedListener(this);
                    b.textd = textd;
                } catch (Exception e) {
                    Log.e("error", e.getMessage());
                }
                break;
            case R.id.spinner2:
                textm = (TextView) view;
                b.textm = textm;

                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public String getTextm() {
        return this.textm.getText() + "";
    }

    public String getTextd() {
        return this.textd.getText() + "";

    }

    public String getR() {
        return this.r[3];
    }
  /*  public  Buscar getB(){
        return b;
    }
   */
}
