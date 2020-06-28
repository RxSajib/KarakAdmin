package com.thomasgroup.karakadmin;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserFragement extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference MuserDatabasel;

    private RelativeLayout relativeLayout;

   public UserFragement(){

   }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_fragement, container, false);

        relativeLayout = view.findViewById(R.id.UserinfoID);




        MuserDatabasel = FirebaseDatabase.getInstance().getReference().child("Users");
        recyclerView = view.findViewById(R.id.UserRecylaerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;
   }

    @Override
    public void onStart() {

        FirebaseRecyclerAdapter<UsersData, UserHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UsersData, UserHolder>(
                UsersData.class,
                R.layout.user_layout,
                UserHolder.class,
                MuserDatabasel
        ) {
            @Override
            protected void populateViewHolder(final UserHolder userHolder, UsersData usersData, int i) {

                String UID = getRef(i).getKey();
                MuserDatabasel.child(UID)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){

                                    relativeLayout.setVisibility(View.INVISIBLE);

                                    if(dataSnapshot.hasChild("Name")){
                                        String Nameget = dataSnapshot.child("Name").getValue().toString();
                                        userHolder.setUsernameset(Nameget);
                                    }

                                    if(dataSnapshot.hasChild("imageurl")){
                                        String imageurlget = dataSnapshot.child("imageurl").getValue().toString();
                                        userHolder.setProfileimageset(imageurlget);
                                    }

                                    if(dataSnapshot.hasChild("phone")){
                                        String phoneget = dataSnapshot.child("phone").getValue().toString();
                                        userHolder.setPhonenumnberset(phoneget);
                                    }
                                }
                                else {
                                    relativeLayout.setVisibility(View.VISIBLE);

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
        };


        recyclerView.setAdapter(firebaseRecyclerAdapter);
        super.onStart();
    }

    public static class UserHolder extends RecyclerView.ViewHolder{

       private Context context;
       private View Mview;
       private MaterialTextView username, phonenumnber, blood;
       private CircleImageView profileimage;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            Mview = itemView;
            context = Mview.getContext();
            username = Mview.findViewById(R.id.usernameID);
            phonenumnber = Mview.findViewById(R.id.PhoneNumber);
            profileimage = Mview.findViewById(R.id.userprofileiamgeID);
            blood = Mview.findViewById(R.id.BloodID);
        }

        public void setbloodset(String bloodtext){
            blood.setText(bloodtext);
        }

        public void setPhonenumnberset(String number){
            phonenumnber.setText(number);
        }

        public void setProfileimageset(String img){
            Picasso.with(context).load(img).placeholder(R.drawable.default_image).into(profileimage);
        }

        public void setUsernameset(String nam){
            username.setText(nam);
        }
    }
}