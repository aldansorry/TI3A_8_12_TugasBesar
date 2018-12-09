package sorry.aldan.ti3a_8_12_tugasbesar.Rest;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.Laporan;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.ResponseLaporan;

public interface ApiInterface {
    @GET("laporan/laporan")
    Call<ResponseLaporan> getLaporan();

    @Multipart
    @POST("laporan/laporan")
    Call<ResponseLaporan> postLaporan(
            @Part MultipartBody.Part file,
            @Part("id") RequestBody id,
            @Part("judul") RequestBody judul,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("longtitude") RequestBody longtitude,
            @Part("lattitude") RequestBody lattitude,
            @Part("status") RequestBody status);
}