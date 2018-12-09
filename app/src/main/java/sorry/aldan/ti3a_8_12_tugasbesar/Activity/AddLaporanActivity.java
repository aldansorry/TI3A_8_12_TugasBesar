package sorry.aldan.ti3a_8_12_tugasbesar.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.ResponseLaporan;
import sorry.aldan.ti3a_8_12_tugasbesar.R;
import sorry.aldan.ti3a_8_12_tugasbesar.Rest.ApiClient;
import sorry.aldan.ti3a_8_12_tugasbesar.Rest.ApiInterface;

public class AddLaporanActivity extends AppCompatActivity {

    ImageView imgGambar;
    TextView edtJudul;
    TextView edtDeskripsi;
    TextView edtLattitude;
    TextView edtLongtitude;

    Button btnAddGambar;
    Button btnAddLaporan;

    String imagePath = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laporan);

        imgGambar = findViewById(R.id.imgGambar);
        edtJudul = findViewById(R.id.edtJudul);
        edtDeskripsi = findViewById(R.id.edtDeskripsi);
        edtLattitude = findViewById(R.id.edtLattitude);
        edtLongtitude = findViewById(R.id.edtLongtitude);
        btnAddGambar = findViewById(R.id.btnAddGambar);
        btnAddLaporan = findViewById(R.id.btnAddLaporan);

        btnAddGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_PICK);
                Intent intentChoose = Intent.createChooser(galleryIntent, "Pilih Gambar Untuk Di upload");
                startActivityForResult(intentChoose, 10);
            }
        });

        btnAddLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                MultipartBody.Part body = null;
                if (!imagePath.isEmpty()){
                    File file = new File(imagePath);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    body = MultipartBody.Part.createFormData("gambar", file.getName(), requestFile);
                }
                RequestBody regId = MultipartBody.create(MediaType.parse("multipart/form-data"), "1");
                RequestBody regJudul = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtJudul.getText().toString().isEmpty())?"":edtJudul.getText().toString());
                RequestBody regDeskripsi = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtDeskripsi.getText().toString().isEmpty())?"":edtDeskripsi.getText().toString());
                RequestBody regLattitude = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtLattitude.getText().toString().isEmpty())?"":edtLattitude.getText().toString());
                RequestBody regLongtitude = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtLongtitude.getText().toString().isEmpty())?"":edtLongtitude.getText().toString());
                RequestBody regStatus = MultipartBody.create(MediaType.parse("multipart/form-data"), "1");
                Call<ResponseLaporan> mPembeliCall = mApiInterface.postLaporan(body,regId, regJudul, regDeskripsi, regLattitude, regLongtitude, regStatus);
                mPembeliCall.enqueue(new Callback<ResponseLaporan>() {
                    @Override
                    public void onResponse(Call<ResponseLaporan> call, Response<ResponseLaporan> response) {
 Log.d("Insert Retrofit",response.body().getStatus());
                        Toast.makeText(AddLaporanActivity.this,":"+response.body().getMessage(),Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onFailure(Call<ResponseLaporan> call, Throwable t) {
 Log.d("Insert Retrofit", t.getMessage());
                        Toast.makeText(AddLaporanActivity.this,":"+t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode ==10){
            if (data==null){
                Toast.makeText(AddLaporanActivity.this, "Gambar Gagal Di load",
                        Toast.LENGTH_LONG).show();
                return;
            }
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn,
                    null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath =cursor.getString(columnIndex);

                Glide.with(AddLaporanActivity.this).load(new File(imagePath)).into(imgGambar);
                cursor.close();
            }else{
                Toast.makeText(AddLaporanActivity.this, "Gambar Gagal Di load",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
