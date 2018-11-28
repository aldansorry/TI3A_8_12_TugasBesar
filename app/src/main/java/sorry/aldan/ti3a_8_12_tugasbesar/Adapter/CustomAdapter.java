package sorry.aldan.ti3a_8_12_tugasbesar.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sorry.aldan.ti3a_8_12_tugasbesar.R;
import sorry.aldan.ti3a_8_12_tugasbesar.Model.UserModel;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomHolder> {

    private List<UserModel> dataset;
    Context mContext;

    public CustomAdapter( List<UserModel> mdataset, Context context) {
        mContext = context;
        dataset = mdataset;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_rv,viewGroup,false);
        CustomHolder viewHolder = new CustomHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder customHolder, int i) {
        UserModel um = dataset.get(i);
        customHolder.judul.setText(um.getJudul());
        customHolder.kategori.setText(um.getKategori());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder{
        TextView judul,kategori;
        View listItem;
        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.name);
            kategori = itemView.findViewById(R.id.kategori);
            listItem = itemView;
        }
    }
}