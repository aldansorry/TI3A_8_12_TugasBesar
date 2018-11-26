package sorry.aldan.ti3a_8_12_tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sorry.aldan.ti3a_8_12_tugasbesar.Adapter.CustomAdapter;
import sorry.aldan.ti3a_8_12_tugasbesar.Adapter.RecycleTouchListener;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<UserModel> dataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CustomAdapter(dataset, this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecycleTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // ToDo 5 buat intent untuk pindah Ke DetailActivity
                Intent i = new Intent(getApplicationContext(),DetailActivity.class);

                // ToDo 6 buat intent untuk membawa data (image, nama, url) ke DetailActivity
                i.putExtra("judul",dataset.get(position).getJudul().toString());
                i.putExtra("kategori",dataset.get(position).getKategori().toString());
                startActivity(i);



            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}