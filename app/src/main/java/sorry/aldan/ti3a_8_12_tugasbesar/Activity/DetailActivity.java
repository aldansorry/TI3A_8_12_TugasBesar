package sorry.aldan.ti3a_8_12_tugasbesar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import sorry.aldan.ti3a_8_12_tugasbesar.Model.Laporan;
import sorry.aldan.ti3a_8_12_tugasbesar.R;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_LAPORAN = "extra_laporan";
    //variable komponen view
    TextView txtJudul;
    TextView txtDeskripsi;
    ImageView imgGambar;
    Button btnMaps;

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

        //get intent
        myIntent = getIntent();

        final Laporan mLaporan = (Laporan) myIntent.getParcelableExtra(EXTRA_LAPORAN);
        //merubah text sesuai data yang telah di intent
        txtJudul.setText(mLaporan.getJudul());
        txtDeskripsi.setText(mLaporan.getDeskripsi());
        Picasso.with(getApplicationContext()).load(mLaporan.getGambar());

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
    }
}
