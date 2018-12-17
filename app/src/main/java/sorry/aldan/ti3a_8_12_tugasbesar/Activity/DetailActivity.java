package sorry.aldan.ti3a_8_12_tugasbesar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sorry.aldan.ti3a_8_12_tugasbesar.Adapter.LaporanAdapter;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.Laporan;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.ResponseLaporan;
import sorry.aldan.ti3a_8_12_tugasbesar.R;
import sorry.aldan.ti3a_8_12_tugasbesar.Rest.ApiClient;
import sorry.aldan.ti3a_8_12_tugasbesar.Rest.ApiInterface;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_LAPORAN = "extra_laporan";
    //variable komponen view
    TextView txtJudul;
    TextView txtDeskripsi;
    ImageView imgGambar;
    Button btnMaps;
    Button btnEdit;
    Button btnDelete;

    //variable intent
    Intent myIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //get komponen
        txtJudul = findViewById(R.id.txtJudul);
        txtDeskripsi = findViewById(R.id.txtDeskrpsi);
        imgGambar = findViewById(R.id.imgGambar);
        btnMaps = findViewById(R.id.btnMaps);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        //get intent
        myIntent = getIntent();

        final Laporan mLaporan = (Laporan) myIntent.getParcelableExtra(EXTRA_LAPORAN);
        //merubah text sesuai data yang telah di intent
        txtJudul.setText(mLaporan.getJudul());
        txtDeskripsi.setText(mLaporan.getDeskripsi());
        Picasso.with(DetailActivity.this).load(ApiClient.BASE_URL+"application/upload/" +mLaporan.getGambar()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(imgGambar);

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,MapsActivity.class);
                intent.putExtra(MapsActivity.EXTRA_JUDUL,mLaporan.getJudul());
                intent.putExtra(MapsActivity.EXTRA_LAT,mLaporan.getLattitude());
                intent.putExtra(MapsActivity.EXTRA_LONG,mLaporan.getLongtitude());
                startActivity(intent);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,EditLaporanActivity.class);
                intent.putExtra(EditLaporanActivity.EXTRA_LAPORAN,mLaporan);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<ResponseLaporan> mPembeliCall = mApiInterface.deleteLaporan(mLaporan.getId());
                mPembeliCall.enqueue(new Callback<ResponseLaporan>() {
                    @Override
                    public void onResponse(Call<ResponseLaporan> call,
                                           Response<ResponseLaporan> response) {
                        Log.d("Get Pembeli",response.body().getStatus());
                        Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<ResponseLaporan> call, Throwable t) {
                        Log.d("Get Pembeli",t.getMessage());
                        Toast.makeText(getApplicationContext(),"Gagal"+t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
