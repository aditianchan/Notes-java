package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.common.base.Strings;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageButton button;

    //private ArrayList<New> mDataset =new ArrayList<New>();;
    private List<New> blog_list;
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recycler;

    private FirestoreRecyclerAdapter adapter;
    LinearLayoutManager mLayoutManager;
   /* public void adapter(Context context, ArrayList<New> myDataset) {
        mDataset = myDataset;

    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (ImageButton) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent(MainActivity.this, typeactivity.class);
                startActivity(regIntent);
                finish();


            }

        });
        recycler = findViewById(R.id.aditi);
        firebaseFirestore = FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection("post");
        FirestoreRecyclerOptions<New> options = new FirestoreRecyclerOptions.Builder<New>().setQuery(query, New.class).build();
        adapter = new FirestoreRecyclerAdapter<New, ViewHolder>(options) {

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
                return new ViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull final ViewHolder holder, final int position, @NonNull final New model) {
                final String h = model.getValue();
                holder.text.setText(h);

                holder.check.setChecked(Update(h));
                holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        SaveIntoSharedPrefs(h, b);
                    }
                });





            }
            private void SaveIntoSharedPrefs(String key, boolean value) {
                SharedPreferences sp = getSharedPreferences("aditi", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean(key, value);
                editor.apply();
            }

            private boolean Update(String key) {
                SharedPreferences sp = getSharedPreferences("aditi", MODE_PRIVATE);
                return sp.getBoolean(key, false);

            }


        };
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        CheckBox check;
        ImageButton butt;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            //text = (TextView)itemView.findViewById(R.id.t);


            text = itemView.findViewById(R.id.t);
            check = itemView.findViewById(R.id.c);
            //butt = itemView.findViewById(R.id.but);


            /*check.setChecked(Update("one"));
            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    SaveIntoSharedPrefs("one",b);
                }
            });}
            private void SaveIntoSharedPrefs(String key ,boolean value){
                SharedPreferences sp = getSharedPreferences("aditi",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean(key,value);
                editor.apply();
            }
            private boolean Update(String key){
                SharedPreferences sp = getSharedPreferences("aditi",MODE_PRIVATE);
                return sp.getBoolean(key,false);*/

        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


}

    /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        blog_list = new ArrayList<>();
        list_view = view.findViewById(R.id.aditi);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("posts").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException error) {
                if (error != null) {

                    Log.d(TAG, "Error:" + error.getMessage());
                }//assert documentSnapshots != null;

                else {
                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {
                            New Final = doc.getDocument().toObject(New.class);
                            blog_list.add(Final);
                            recycler.notifyDataSetChanged();
                        }
                    }
                }

            }
        });
        return view;


    }*/


