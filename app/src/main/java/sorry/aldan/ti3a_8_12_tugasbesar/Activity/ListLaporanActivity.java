package sorry.aldan.ti3a_8_12_tugasbesar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

public class ListLaporanActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<Laporan> dataset = new ArrayList<>();
    private SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_laporan);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        sessionManagement = new SessionManagement(this);

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


        mRecyclerView.addOnItemTouchListener(new RecycleTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // ToDo 5 buat intent untuk pindah Ke DetailActivity
                Intent i = new Intent(getApplicationContext(),DetailActivity.class);

                // ToDo 6 buat intent untuk membawa data (image, nama, url) ke DetailActivity
                i.putExtra("judul",dataset.get(position).getJudul().toString());
                i.putExtra("kategori",dataset.get(position).getDeskripsi().toString());
                i.putExtra("lattitude",dataset.get(position).getLattitude().toString());
                i.putExtra("longtitude",dataset.get(position).getLongtitude().toString());
                startActivity(i);



            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
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
}