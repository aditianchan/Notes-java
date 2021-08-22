/*package com.example.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Recycler extends RecyclerView.Adapter<Recycler.ViewHolder>{
    public Context context;
    public List<New> blog_list;
    public Recycler(List<New> blog_list,Context context) {
        this.blog_list = blog_list;
        this.context = context;
    }
    private FirebaseFirestore firebaseFirestore;
    // firebaseFirestore = FirebaseFirestore.getInstance()


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position,) {
        final String desc_data = blog_list.get(position).getValue();
        holder.descview.setText(desc_data);
        holder.check.setChecked(Update(h));
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SaveIntoSharedPrefs(desc_data, b);
            }
        });

        private void SaveIntoSharedPrefs(String context, boolean value) {
            SharedPreferences sp = context.getSharedPreferences("aditi", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }

        private boolean Update(String key) {
            SharedPreferences sp = context.getSharedPreferences("aditi", MODE_PRIVATE);
            return sp.getBoolean(desc_data, false);

        }




    }

    @Override
    public int getItemCount() {
        return blog_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private View mview;
        private TextView descview;
        CheckBox check;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;
        }
        public void setDescText(String  desctt){
            descview = mview.findViewById(R.id.t);
           // descview.setText(desctt);
            check = mview.findViewById(R.id.c);
        }

    }
    /*private void SaveIntoSharedPrefs(String key, boolean value) {
        SharedPreferences sp = getSharedPreferences("aditi", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private boolean Update(String key) {
        SharedPreferences sp = getSharedPreferences("aditi", MODE_PRIVATE);
        return sp.getBoolean(key, false);

    }*/



