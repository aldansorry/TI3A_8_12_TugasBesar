package sorry.aldan.ti3a_8_12_tugasbesar.Model;

import com.google.gson.annotations.SerializedName;

public class Laporan {
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

    public Laporan() {
    }

    public Laporan(String id, String nama, String email, String judul, String deskripsi, Double lattitude, Double longtitude, String gambar, String status, String nama_kategori) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.lattitude = lattitude;
        this.longtitude = longtitude;
        this.gambar = gambar;
        this.status = status;
        this.nama_kategori = nama_kategori;
    }

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
}
