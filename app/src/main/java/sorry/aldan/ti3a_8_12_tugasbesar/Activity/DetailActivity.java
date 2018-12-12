package sorry.aldan.ti3a_8_12_tugasbesar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import sorry.aldan.ti3a_8_12_tugasbesar.R;

public class DetailActivity extends AppCompatActivity {

    TextView txtJudul;
    TextView txtDeskripsi;
    ImageView imgGambar;
    Button btnMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtJudul = findViewById(R.id.txtJudul);
        txtDeskripsi = findViewById(R.id.txtDeskrpsi);
        imgGambar = findViewById(R.id.imgGambar);
        btnMaps = findViewById(R.id.btnMaps);

        final Intent myIntent = getIntent();

        txtJudul.setText(myIntent.getStringExtra("judul"));
        txtDeskripsi.setText(myIntent.getStringExtra("deskripsi"));
        Picasso.with(getApplicationContext()).load(myIntent.getStringExtra("gambar"));

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,MapsActivity.class);
                intent.putExtra("longtitude",myIntent.getStringExtra("longtitude"));
                intent.putExtra("lattitude",myIntent.getStringExtra("lattitude"));
                startActivity(intent);
            }
        });
    }
}
