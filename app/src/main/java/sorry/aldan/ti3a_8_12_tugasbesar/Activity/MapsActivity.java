package sorry.aldan.ti3a_8_12_tugasbesar.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sorry.aldan.ti3a_8_12_tugasbesar.Adapter.LaporanAdapter;
import sorry.aldan.ti3a_8_12_tugasbesar.Helper.SessionManagement;
import sorry.aldan.ti3a_8_12_tugasbesar.MainActivity;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.Laporan;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.ResponseLaporan;
import sorry.aldan.ti3a_8_12_tugasbesar.R;
import sorry.aldan.ti3a_8_12_tugasbesar.Rest.ApiClient;
import sorry.aldan.ti3a_8_12_tugasbesar.Rest.ApiInterface;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String EXTRA_LAT = "extra_lat";
    public static final String EXTRA_LONG = "extra_long";
    public static final String EXTRA_JUDUL = "extra_judul";
    private GoogleMap mMap;
    private Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        myIntent = getIntent();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera


        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLaporan> mPembeliCall = mApiInterface.getLaporan();
        mPembeliCall.enqueue(new Callback<ResponseLaporan>() {
            @Override
            public void onResponse(Call<ResponseLaporan> call,
                                   Response<ResponseLaporan> response) {
                Log.d("Get Laporan",response.body().getStatus());
                List<Laporan> dataset = response.body().getResult();
                for (int i=0;i<dataset.size();i++){
                    Laporan lap = dataset.get(i);
                    LatLng myLatLang = new LatLng(lap.getLattitude(),lap.getLongtitude());
                    mMap.addMarker(new MarkerOptions().position(myLatLang).title(lap.getJudul()));
                    if (myIntent.getExtras() != null){
                        if (lap.getJudul().equals(myIntent.getStringExtra(EXTRA_JUDUL))){
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLang));
                        }
                    }
                }
                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ResponseLaporan> call, Throwable t) {
                Log.d("Get Laporan",t.getMessage());
                Toast.makeText(getApplicationContext(),"Gagal"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent;
        switch (item.getItemId()) {
            case R.id.menuHome:
                mIntent = new Intent(this, MainActivity.class);
                startActivity(mIntent);
                return true;
            case R.id.menuAddLaporan:
                mIntent = new Intent(this, AddLaporanActivity.class);
                startActivity(mIntent);
                return true;
            case R.id.menuListLaporan:
//                mIntent = new Intent(this, ListLaporanActivity.class);
//                startActivity(mIntent);
                return true;
            case R.id.menuLogout:
                new SessionManagement(getApplicationContext()).logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
