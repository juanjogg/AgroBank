package juan.findbank;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String departamento, oficina, direccion;
    private Buscar buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        buscar = Buscar.b;

        try {
            this.direccion = buscar.getR();
            this.oficina = buscar.getTextm();
            this.departamento = buscar.getTextd();

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            Mensaje.mensaje(this, e.getMessage());
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> adr = geocoder.getFromLocationName(this.direccion + " , " + this.oficina + " , " + this.departamento + " , Colombia", 1);
            Address dir = adr.get(0);
            // Add a marker in Sydney and move the camera
            LatLng oficina = new LatLng(dir.getLatitude(), dir.getLongitude());
            mMap.addMarker(new MarkerOptions().position(oficina).title("Marcandor en " + this.oficina));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(oficina));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(oficina, 1f));

        } catch (Exception e) {

        }
    }

}
