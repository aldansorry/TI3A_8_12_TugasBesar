package sorry.aldan.ti3a_8_12_tugasbesar.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sorry.aldan.ti3a_8_12_tugasbesar.R;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.Laporan;
import sorry.aldan.ti3a_8_12_tugasbesar.Rest.ApiClient;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.LaporanHolder> {

    private List<Laporan> listLaporan;
    Context mContext;

    public LaporanAdapter(List<Laporan> mdataset, Context context) {
        mContext = context;
        listLaporan = mdataset;
    }

    @NonNull
    @Override
    public LaporanHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_laporan,viewGroup,false);
        LaporanHolder viewHolder = new LaporanHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanHolder laporanHolder, int i) {
        Laporan um = listLaporan.get(i);
        laporanHolder.txtJudul.setText(um.getJudul());
        laporanHolder.txtStatus.setText(um.getDeskripsi());
        Picasso.with(mContext).load(ApiClient.BASE_URL+"application/upload/" +um.getGambar()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(laporanHolder.imgGambar);

    }

    @Override
    public int getItemCount() {
        return listLaporan.size();
    }

    public class LaporanHolder extends RecyclerView.ViewHolder{
        ImageView imgGambar;
        TextView txtJudul, txtStatus;
        View listItem;
        public LaporanHolder(@NonNull View itemView) {
            super(itemView);
            imgGambar = itemView.findViewById(R.id.imgGambar);
            txtJudul = itemView.findViewById(R.id.txtJudul);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            listItem = itemView;
        }
    }
}