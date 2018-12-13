package sorry.aldan.ti3a_8_12_tugasbesar.Model;

import com.google.gson.annotations.SerializedName;

public class Kategori {
    @SerializedName("id")
    String id;
    @SerializedName("nama")
    String nama;

    public Kategori(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public Kategori() {
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
}
