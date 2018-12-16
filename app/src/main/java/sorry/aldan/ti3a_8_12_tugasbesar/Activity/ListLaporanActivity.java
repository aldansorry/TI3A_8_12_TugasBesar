package sorry.aldan.ti3a_8_12_tugasbesar.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sorry.aldan.ti3a_8_12_tugasbesar.Adapter.ClickListener;
import sorry.aldan.ti3a_8_12_tugasbesar.Adapter.LaporanAdapter;
import sorry.aldan.ti3a_8_12_tugasbesar.Adapter.RecycleTouchListener;
import sorry.aldan.ti3a_8_12_tugasbesar.Helper.SessionManagement;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.Laporan;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.ResponseLaporan;
import sorry.aldan.ti3a_8_12_tugasbesar.R;
import sorry.aldan.ti3a_8_12_tugasbesar.Rest.ApiClient;
import sorry.aldan.ti3a_8_12_tugasbesar.Rest.ApiInterface;

public class ListLaporanActivity extends AppCompatActivity implements OnMapReadyCallback {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<Laporan> dataset = new ArrayList<>();
    private SessionManagement sessionManagement;

    EditText edtSearch;
    Button btnSearch;

    FloatingActionButton btnAddLaporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_laporan);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btnAddLaporan = findViewById(R.id.btnAddLaporan);
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);

        sessionManagement = new SessionManagement(this);

        refreshList();

        mRecyclerView.addOnItemTouchListener(new RecycleTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // ToDo 5 buat intent untuk pindah Ke DetailActivity
                Intent i = new Intent(getApplicationContext(),DetailActivity.class);

                // ToDo 6 buat intent untuk membawa data (image, nama, url) ke DetailActivity
                i.putExtra(DetailActivity.EXTRA_LAPORAN,dataset.get(position));
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        btnAddLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(ListLaporanActivity.this, AddLaporanActivity.class);
                startActivity(mIntent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kata= edtSearch.getText().toString();
                refreshListSearch(kata);
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
            case R.id.menuAddLaporan:
                mIntent = new Intent(this, AddLaporanActivity.class);
                startActivity(mIntent);
                return true;
            case R.id.menuLogout:
                sessionManagement.logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void refreshList(){

        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLaporan> mPembeliCall = mApiInterface.getLaporan();
        mPembeliCall.enqueue(new Callback<ResponseLaporan>() {
            @Override
            public void onResponse(Call<ResponseLaporan> call,
                                   Response<ResponseLaporan> response) {
                Log.d("Get Pembeli",response.body().getStatus());
                dataset = response.body().getResult();
                mAdapter = new LaporanAdapter(dataset, getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ResponseLaporan> call, Throwable t) {
                Log.d("Get Pembeli",t.getMessage());
                Toast.makeText(getApplicationContext(),"Gagal"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void refreshListSearch(String kata){

        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLaporan> mPembeliCall = mApiInterface.getLaporan(kata);
        mPembeliCall.enqueue(new Callback<ResponseLaporan>() {
            @Override
            public void onResponse(Call<ResponseLaporan> call,
                                   Response<ResponseLaporan> response) {
                Log.d("Get Pembeli",response.body().getStatus());
                dataset = response.body().getResult();
                mAdapter = new LaporanAdapter(dataset, getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ResponseLaporan> call, Throwable t) {
                Log.d("Get Pembeli",t.getMessage());
                Toast.makeText(getApplicationContext(),"Gagal"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}