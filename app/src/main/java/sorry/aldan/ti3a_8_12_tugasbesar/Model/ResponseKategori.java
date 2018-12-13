package sorry.aldan.ti3a_8_12_tugasbesar.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseKategori {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Kategori> result = new ArrayList<Kategori>();
    @SerializedName("message")
    private String message;

    public ResponseKategori(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Kategori> getResult() {
        return result;
    }

    public void setResult(List<Kategori> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
