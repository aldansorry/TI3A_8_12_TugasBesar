package sorry.aldan.ti3a_8_12_tugasbesar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sorry.aldan.ti3a_8_12_tugasbesar.Adapter.ClickListener;
import sorry.aldan.ti3a_8_12_tugasbesar.Adapter.LaporanAdapter;
import sorry.aldan.ti3a_8_12_tugasbesar.Adapter.RecycleTouchListener;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.Laporan;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.ResponseLaporan;
import sorry.aldan.ti3a_8_12_tugasbesar.R;
import sorry.aldan.ti3a_8_12_tugasbesar.Rest.ApiClient;
import sorry.aldan.ti3a_8_12_tugasbesar.Rest.ApiInterface;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<Laporan> dataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

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
            }
            @Override
            public void onFailure(Call<ResponseLaporan> call, Throwable t) {
                Log.d("Get Pembeli",t.getMessage());
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
                startActivity(i);



            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}