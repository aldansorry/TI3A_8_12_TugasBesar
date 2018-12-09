package sorry.aldan.ti3a_8_12_tugasbesar.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sorry.aldan.ti3a_8_12_tugasbesar.R;

public class DetailActivity extends AppCompatActivity {

    Button btnMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnMaps = findViewById(R.id.btnMaps);

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
