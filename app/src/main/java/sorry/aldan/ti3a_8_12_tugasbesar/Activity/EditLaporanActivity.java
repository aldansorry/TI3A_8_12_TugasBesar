package sorry.aldan.ti3a_8_12_tugasbesar.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sorry.aldan.ti3a_8_12_tugasbesar.Helper.SessionManagement;
import sorry.aldan.ti3a_8_12_tugasbesar.MainActivity;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.Kategori;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.Laporan;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.ResponseKategori;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.ResponseLaporan;
import sorry.aldan.ti3a_8_12_tugasbesar.R;
import sorry.aldan.ti3a_8_12_tugasbesar.Rest.ApiClient;
import sorry.aldan.ti3a_8_12_tugasbesar.Rest.ApiInterface;

public class EditLaporanActivity extends AppCompatActivity {

    public static final String EXTRA_LAPORAN = "extra_laporan";
    //variable untuk komponen view(layout)
    ImageView imgGambar;

    TextView edtJudul;
    TextView edtDeskripsi;
    TextView edtLattitude;
    TextView edtLongtitude;

    Button btnAddGambar;
    Button btnAddLaporan;
    Button btnTakePicture;

    Spinner spnKategori;

    //variable untuk menyimpan url gambar saat ambil galeri dan take photo
    String imagePath = "";

    //variable untuk object class
    SessionManagement sessionManagement;

    //variable list
    List<Kategori> kategoriArray;
    List<String> spinnerArray;

    //variable take picture
    private static final int REQUEST_IMAGE_PICTURE = 101;

    Intent myIntent;
    Laporan myLaporan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_laporan);

        myIntent = getIntent();
        //get view
        imgGambar = findViewById(R.id.imgGambar);
        edtJudul = findViewById(R.id.edtJudul);
        edtDeskripsi = findViewById(R.id.edtDeskripsi);
        edtLattitude = findViewById(R.id.edtLattitude);
        edtLongtitude = findViewById(R.id.edtLongtitude);
        btnAddGambar = findViewById(R.id.btnAddGambar);
        btnAddLaporan = findViewById(R.id.btnAddLaporan);
        btnTakePicture = findViewById(R.id.btnTakePicture);
        spnKategori = findViewById(R.id.spnKategori);

        //instance object
        sessionManagement = new SessionManagement(this);
        kategoriArray = new ArrayList<Kategori>();
        spinnerArray = new ArrayList<String>();

        //call kategori
        getKategori();

        myLaporan = (Laporan) myIntent.getParcelableExtra(EXTRA_LAPORAN);

        //membuka galeri
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

        //menyimpan data
        btnAddLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                MultipartBody.Part body = null;
                if (!imagePath.isEmpty()) {
                    File file = new File(imagePath);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    body = MultipartBody.Part.createFormData("gambar", file.getName(), requestFile);
                }
                //mengambil data dari view
                RequestBody regId = MultipartBody.create(MediaType.parse("multipart/form-data"),myLaporan.getId().toString());
                RequestBody regNama = MultipartBody.create(MediaType.parse("multipart/form-data"), sessionManagement.getUserInformation().get(sessionManagement.KEY_USERNAME));
                RequestBody regEmail = MultipartBody.create(MediaType.parse("multipart/form-data"), sessionManagement.getUserInformation().get(sessionManagement.KEY_USERNAME));
                RequestBody regJudul = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtJudul.getText().toString().isEmpty()) ? "" : edtJudul.getText().toString());
                RequestBody regDeskripsi = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtDeskripsi.getText().toString().isEmpty()) ? "" : edtDeskripsi.getText().toString());
                RequestBody regLattitude = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtLattitude.getText().toString().isEmpty()) ? "" : edtLattitude.getText().toString());
                RequestBody regLongtitude = MultipartBody.create(MediaType.parse("multipart/form-data"), (edtLongtitude.getText().toString().isEmpty()) ? "" : edtLongtitude.getText().toString());
                RequestBody regStatus = MultipartBody.create(MediaType.parse("multipart/form-data"), "1");
                RequestBody regKategori = MultipartBody.create(MediaType.parse("multipart/form-data"), spnKategori.getSelectedItem().toString());

                //memanggil postLaporan
                Call<ResponseLaporan> mPostLaporan = mApiInterface.putLaporan(body,regId, regNama, regEmail, regJudul, regDeskripsi, regLattitude, regLongtitude, regStatus, regKategori);
                mPostLaporan.enqueue(new Callback<ResponseLaporan>() {
                    @Override
                    public void onResponse(Call<ResponseLaporan> call, Response<ResponseLaporan> response) {
                        Log.d("Insert Retrofit", response.body().getStatus());
                        Toast.makeText(EditLaporanActivity.this, ":" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseLaporan> call, Throwable t) {
                        Log.d("Insert Retrofit", t.getMessage());
                        Toast.makeText(EditLaporanActivity.this, ":" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        Picasso.with(EditLaporanActivity.this).load(ApiClient.BASE_URL+"application/upload/" +myLaporan.getGambar()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(imgGambar);

        edtJudul.setText(myLaporan.getJudul());
        edtDeskripsi.setText(myLaporan.getDeskripsi());
        edtLattitude.setText(String.valueOf(myLaporan.getLattitude()));
        edtLongtitude.setText(String.valueOf(myLaporan.getLongtitude()));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode ==10){
            if (data==null){
                Toast.makeText(EditLaporanActivity.this, "Gambar Gagal Di load",
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

                Glide.with(EditLaporanActivity.this).load(new File(imagePath)).into(imgGambar);
                cursor.close();
            }else{
                Toast.makeText(EditLaporanActivity.this, "Gambar Gagal Di load",
                        Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == REQUEST_IMAGE_PICTURE && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            imgGambar.setImageBitmap(imageBitmap);

            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(getRealPathFromURI(tempUri));

            imagePath = getRealPathFromURI(tempUri);
        }
    }
    public void takePicture(View view){
        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (imageTakeIntent.resolveActivity(getPackageManager())  != null ){
            startActivityForResult(imageTakeIntent,REQUEST_IMAGE_PICTURE);
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

    public void getKategori(){
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseKategori> mGetKategori = mApiInterface.getKategori();
        mGetKategori.enqueue(new Callback<ResponseKategori>() {
            @Override
            public void onResponse(Call<ResponseKategori> call,
                                   Response<ResponseKategori> response) {
                Log.d("Get Kategori",response.body().getStatus());
                kategoriArray = response.body().getResult();

                for (int i=0;i<kategoriArray.size();i++){
                    spinnerArray.add(kategoriArray.get(i).getNama());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        getApplicationContext(), android.R.layout.simple_spinner_item, spinnerArray);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnKategori.setAdapter(adapter);

                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<ResponseKategori> call, Throwable t) {
                Log.d("Get Kategori",t.getMessage());
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
                sessionManagement.logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
