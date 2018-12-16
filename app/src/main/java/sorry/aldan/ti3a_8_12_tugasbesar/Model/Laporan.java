package sorry.aldan.ti3a_8_12_tugasbesar.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Laporan implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("email")
    private String email;
    @SerializedName("judul")
    private String judul;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("lattitude")
    private Double lattitude;
    @SerializedName("longtitude")
    private Double longtitude;
    @SerializedName("gambar")
    private String gambar;
    @SerializedName("status")
    private String status;
    @SerializedName("nama_kategori")
    private String nama_kategori;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.nama);
        dest.writeString(this.email);
        dest.writeString(this.judul);
        dest.writeString(this.deskripsi);
        dest.writeValue(this.lattitude);
        dest.writeValue(this.longtitude);
        dest.writeString(this.gambar);
        dest.writeString(this.status);
        dest.writeString(this.nama_kategori);
    }

    public Laporan() {
    }

    protected Laporan(Parcel in) {
        this.id = in.readString();
        this.nama = in.readString();
        this.email = in.readString();
        this.judul = in.readString();
        this.deskripsi = in.readString();
        this.lattitude = (Double) in.readValue(Double.class.getClassLoader());
        this.longtitude = (Double) in.readValue(Double.class.getClassLoader());
        this.gambar = in.readString();
        this.status = in.readString();
        this.nama_kategori = in.readString();
    }

    public static final Creator<Laporan> CREATOR = new Creator<Laporan>() {
        @Override
        public Laporan createFromParcel(Parcel source) {
            return new Laporan(source);
        }

        @Override
        public Laporan[] newArray(int size) {
            return new Laporan[size];
        }
    };
}
