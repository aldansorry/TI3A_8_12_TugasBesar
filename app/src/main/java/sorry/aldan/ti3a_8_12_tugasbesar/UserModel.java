package sorry.aldan.ti3a_8_12_tugasbesar;

public class UserModel{
    private String judul, kategori;

    public UserModel(String judul, String kategori){
        this.judul = judul;
        this.kategori = kategori;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
