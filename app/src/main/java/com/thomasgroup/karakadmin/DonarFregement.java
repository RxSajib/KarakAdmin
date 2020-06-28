package com.thomasgroup.karakadmin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class DonarFregement extends Fragment {


    private RecyclerView recyclerView;
    private DatabaseReference MdonarDatabase;
    private RelativeLayout relativeLayout;


    public DonarFregement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donar_fregement, container, false);

        relativeLayout = view.findViewById(R.id.DonarInfoID);

        MdonarDatabase = FirebaseDatabase.getInstance().getReference().child("DonarPost");

        recyclerView = view.findViewById(R.id.DonarRecylearViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onStart() {

        Query firebasequry = MdonarDatabase.orderByChild("short");

        FirebaseRecyclerAdapter<DnarData, DonarHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DnarData, DonarHolder>(
                DnarData.class,
                R.layout.donar_layout,
                DonarHolder.class,
                firebasequry
        ) {
            @Override
            protected void populateViewHolder(final DonarHolder donarHolder, DnarData dnarData, int i) {
                final String UID = getRef(i).getKey();

                MdonarDatabase.child(UID)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {


                                    relativeLayout.setVisibility(View.INVISIBLE);

                                    if (dataSnapshot.hasChild("display")) {
                                        String displayget = dataSnapshot.child("display").getValue().toString();

                                        if (displayget.equals("visiable")) {
                                            donarHolder.toplayoutID.setVisibility(View.VISIBLE);


                                            if (dataSnapshot.hasChild("login_name")) {
                                                String login_nameget = dataSnapshot.child("login_name").getValue().toString();
                                                donarHolder.setUsernameset(login_nameget);
                                            }

                                            if (dataSnapshot.hasChild("donar_name")) {
                                                String donar_nameget = dataSnapshot.child("donar_name").getValue().toString();
                                                donarHolder.setimagename(donar_nameget);
                                            }

                                            if (dataSnapshot.hasChild("donar_profile_imageURL")) {
                                                String imageuri = dataSnapshot.child("donar_profile_imageURL").getValue().toString();
                                                donarHolder.setuserprofileimage(imageuri);
                                            }

                                            if (dataSnapshot.hasChild("donar_number")) {
                                                String donar_numberget = dataSnapshot.child("donar_number").getValue().toString();
                                                donarHolder.setPhonenumberset(donar_numberget);
                                            }

                                            if (dataSnapshot.hasChild("donar_location")) {
                                                String donar_locationget = dataSnapshot.child("donar_location").getValue().toString();
                                                donarHolder.setLocationset(donar_locationget);
                                            }

                                            if (dataSnapshot.hasChild("donar_bloodgroup")) {
                                                String donar_bloodgroupget = dataSnapshot.child("donar_bloodgroup").getValue().toString();
                                                donarHolder.setBloodgroupset(donar_bloodgroupget);
                                            }


                                            donarHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                                                @Override
                                                public boolean onLongClick(View view) {

                                                    CharSequence options[] = new CharSequence[]{

                                                            "Profile",
                                                            "Feedback",
                                                            "Remove"


                                                    };

                                                    MaterialAlertDialogBuilder Mbuilder = new MaterialAlertDialogBuilder(getContext());


                                                    Mbuilder.setItems(options, new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            if (i == 2) {
                                                                MdonarDatabase.child(UID).child("display").setValue("invisiable");
                                                            }
                                                        }
                                                    });

                                                    AlertDialog alertDialog = Mbuilder.create();
                                                    alertDialog.show();


                                                    return true;
                                                }
                                            });


                                        } else if (displayget.equals("invisiable")) {

                                            donarHolder.toplayoutID.setVisibility(View.GONE);


                                        }
                                    }


                                } else {
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

    public static class DonarHolder extends RecyclerView.ViewHolder {

        private Context context;
        private View Mview;
        private CircleImageView profileimage;
        private MaterialTextView username, profilebuttom_username, phonenumber, location, bloodgroup, message;
        private RelativeLayout relativeLayout;
        private MaterialCardView toplayoutID;

        public DonarHolder(@NonNull View itemView) {
            super(itemView);

            Mview = itemView;
            context = Mview.getContext();
            profileimage = Mview.findViewById(R.id.ProfileImageID);
            username = Mview.findViewById(R.id.RUsernames);
            profilebuttom_username = Mview.findViewById(R.id.NameOFdonarID);
            phonenumber = Mview.findViewById(R.id.RDonarPhoneNumberID);
            location = Mview.findViewById(R.id.RDonarocationID);
            bloodgroup = Mview.findViewById(R.id.RDonarBlooad);
            message = Mview.findViewById(R.id.MessageTextID);


            toplayoutID = Mview.findViewById(R.id.CardID);
        }


        private void setuserprofileimage(String img) {
            Picasso.with(context).load(img).placeholder(R.drawable.default_image).into(profileimage);
        }

        private void setUsernameset(String nam) {
            username.setText(nam);
        }

        private void setimagename(String nam) {
            profilebuttom_username.setText(nam);
        }

        public void setPhonenumberset(String number) {
            phonenumber.setText(number);
        }

        public void setLocationset(String loca) {
            location.setText(loca);
        }

        public void setBloodgroupset(String group) {
            bloodgroup.setText(group);
        }

        public void setMessageset(String mess) {
            message.setText(mess);
        }
    }
}