package com.example.internshipchatapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<UsersChat> users;
    private UsersChatAdapter usersAdapter;
    UsersChatAdapter.OnUserClickListener onUserClickListener;


    private SwipeRefreshLayout swipeRefreshLayout;

    String myImageUrl;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat,container,false);

        swipeRefreshLayout = view.findViewById(R.id.swiper1);
        progressBar = view.findViewById(R.id.progress_bar);

        recyclerView = view.findViewById(R.id.recyclerViewChatList);

        users = new ArrayList<>();
        //when swipes and refreshes
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //to open the chat area
        onUserClickListener = new UsersChatAdapter.OnUserClickListener() {
            @Override
            public void OnUserClicked(int position) {
                startActivity(new Intent(getActivity(),Message.class)
                        .putExtra("username_of_roommate",users.get(position).getUsername())
                        .putExtra("email_of_roommate",users.get(position).getEmailID())
                        .putExtra("image_of_roommate",users.get(position).getProfile_pic())
                        .putExtra("My_image",myImageUrl));
            }
        };

        return view;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    private void getUsers() {

        //get the details of friends list and show
        users.clear();
        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    users.add(dataSnapshot.getValue(UsersChat.class));
                }
                usersAdapter = new UsersChatAdapter(users,getActivity(),onUserClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(usersAdapter);

                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                //for checking whether it is the host user himself or not
                for (UsersChat user : users)
                {
                    if (user.getEmailID().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()))
                    {
                        myImageUrl = user.getProfile_pic();
                        return;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    }
