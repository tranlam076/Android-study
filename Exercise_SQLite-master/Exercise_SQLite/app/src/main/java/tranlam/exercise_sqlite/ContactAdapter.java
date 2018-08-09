package tranlam.exercise_sqlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;


/**
 * Created by LamTran on 10/20/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private List<Contact> contacts;
    private Context mContext;
    private LayoutInflater mInflater;

    public ContactAdapter(List<Contact> contacts, Context context) {
        this.mContext = context;
        this.contacts = contacts;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_contact_recycleview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        Contact contact = contacts.get(position);
        holder.mTvName.setText(contact.getName());

        String firstLetter = (contact.getName()).substring(0, 1);
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstLetter, color);
        holder.imgview_name.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTvName;
        ImageView imgview_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            imgview_name=(ImageView) itemView.findViewById(R.id.image_view_name);
        }
    }
}
