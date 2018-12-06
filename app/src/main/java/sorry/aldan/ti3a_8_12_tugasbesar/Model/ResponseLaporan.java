package sorry.aldan.ti3a_8_12_tugasbesar.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseLaporan {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Laporan> result = new ArrayList<Laporan>();
    @SerializedName("message")
    private String message;
    public ResponseLaporan() {}
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<Laporan> getResult() {
        return result;
    }
    public void setResult(List<Laporan> result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
