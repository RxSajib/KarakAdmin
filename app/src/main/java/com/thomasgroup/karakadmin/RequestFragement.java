package com.thomasgroup.karakadmin;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
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


public class RequestFragement extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference Mrequest_database;
    private RelativeLayout relativeLayout;

    public RequestFragement() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request_fragement, container, false);


        relativeLayout = view.findViewById(R.id.RequestID);

        Mrequest_database = FirebaseDatabase.getInstance().getReference().child("Request_post");
        recyclerView = view.findViewById(R.id.RequestBloodAdinID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onStart() {

        Query firebase_qury = Mrequest_database.orderByChild("counter");

        FirebaseRecyclerAdapter<UsersData, RequestHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UsersData, RequestHolder>(
                UsersData.class,
                R.layout.requrest_layout,
                RequestHolder.class,
                firebase_qury
        ) {
            @Override
            protected void populateViewHolder(final RequestHolder requestHolder, UsersData usersData, int i) {

                final String UID = getRef(i).getKey();
                Mrequest_database.child(UID)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {


                                    relativeLayout.setVisibility(View.GONE);

                                    if (dataSnapshot.hasChild("display")) {

                                        String displayget = dataSnapshot.child("display").getValue().toString();

                                        if (displayget.equals("visiable")) {
                                            requestHolder.rootlayout.setVisibility(View.VISIBLE);


                                            if (dataSnapshot.hasChild("blood_group")) {
                                                String blood_groupget = dataSnapshot.child("blood_group").getValue().toString();
                                                requestHolder.setBloodset(blood_groupget);
                                            }

                                            if (dataSnapshot.hasChild("location")) {
                                                String locationget = dataSnapshot.child("location").getValue().toString();
                                                requestHolder.setLocationset(locationget);
                                            }

                                            if (dataSnapshot.hasChild("loginusername")) {
                                                String loginusernameget = dataSnapshot.child("loginusername").getValue().toString();
                                                requestHolder.setUsernameset(loginusernameget);
                                            }

                                            if (dataSnapshot.hasChild("message")) {
                                                String messageget = dataSnapshot.child("message").getValue().toString();
                                                requestHolder.setMessageset(messageget);
                                            }

                                            if (dataSnapshot.hasChild("mobilenumber")) {
                                                String mobilenumberget = dataSnapshot.child("mobilenumber").getValue().toString();
                                                requestHolder.setMobilenumberset(mobilenumberget);
                                            }

                                            if (dataSnapshot.hasChild("patentname")) {
                                                String patentnameget = dataSnapshot.child("patentname").getValue().toString();
                                                requestHolder.imagename(patentnameget);
                                            }

                                            if (dataSnapshot.hasChild("Image_downloadurl")) {
                                                String Image_downloadurlget = dataSnapshot.child("Image_downloadurl").getValue().toString();
                                                requestHolder.setProfileimageset(Image_downloadurlget);
                                            }


                                            requestHolder.Mview.setOnLongClickListener(new View.OnLongClickListener() {
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
                                                               Mrequest_database.child(UID).child("display").setValue("invisiable");
                                                            }
                                                        }
                                                    });

                                                    AlertDialog alertDialog = Mbuilder.create();
                                                    alertDialog.show();

                                                    return true;
                                                }
                                            });

                                        } else if (displayget.equals("invisiable")) {
                                            requestHolder.rootlayout.setVisibility(View.GONE);
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

    public static class RequestHolder extends RecyclerView.ViewHolder {

        private CircleImageView profileimage;
        private MaterialTextView username, imagebellowname, mobilenumber, location, blood, message;
        private Context context;
        private View Mview;
        private MaterialCardView rootlayout;


        public RequestHolder(@NonNull View itemView) {
            super(itemView);

            Mview = itemView;
            context = Mview.getContext();
            profileimage = Mview.findViewById(R.id.UProfileImageID);
            username = Mview.findViewById(R.id.UUsernames);
            imagebellowname = Mview.findViewById(R.id.UNameOFdonarID);
            mobilenumber = Mview.findViewById(R.id.UDonarPhoneNumberID);
            location = Mview.findViewById(R.id.UDonarocationID);
            blood = Mview.findViewById(R.id.UDonarBlooad);
            message = Mview.findViewById(R.id.RewuestMessageTextID);
            rootlayout = Mview.findViewById(R.id.RootlayoutID);

        }

        public void setProfileimageset(String img) {
            Picasso.with(context).load(img).placeholder(R.drawable.default_image).into(profileimage);
        }

        public void setUsernameset(String nam) {
            username.setText(nam);
        }

        public void imagename(String nam) {
            imagebellowname.setText(nam);
        }

        public void setMobilenumberset(String number) {
            mobilenumber.setText(number);
        }

        public void setLocationset(String locati) {
            location.setText(locati);
        }

        public void setBloodset(String bloo) {
            blood.setText(bloo);
        }

        public void setMessageset(String mess) {
            message.setText(mess);
        }
    }
}