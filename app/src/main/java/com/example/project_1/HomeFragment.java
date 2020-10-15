package com.example.project_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.project_1.Adapter.HomeBuyAdater;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    //HomeBuy RecyclerView 관련 변수
    private ArrayList<HomeBuyItem> arrayList;
    private HomeBuyAdater homeBuyAdater;
    private RecyclerView homeBuyRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private HomeListDecoration homeListDecoration;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        //HomeBuyRecyclerView
        homeBuyRecyclerView = (RecyclerView) view.findViewById(R.id.buy_listView);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        homeBuyRecyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();
        homeBuyAdater = new HomeBuyAdater(arrayList);
        homeBuyRecyclerView.setAdapter(homeBuyAdater);
        homeListDecoration = new HomeListDecoration();
        homeBuyRecyclerView.addItemDecoration(homeListDecoration);

        HomeBuyItem homeBuyItem = new HomeBuyItem(R.drawable.polarbear, "공구 제목", "OO동 XX아파트", "3명", "5명");
        arrayList.add(homeBuyItem);
        arrayList.add(homeBuyItem);
        arrayList.add(homeBuyItem);
        arrayList.add(homeBuyItem);
        arrayList.add(homeBuyItem);
        homeBuyAdater.notifyDataSetChanged();

        //글쓰기 버튼
        FloatingActionButton write_button = (FloatingActionButton) view.findViewById(R.id.write_button);
        write_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), WriteActivity.class));
            }
        });
        return view;
    }
}
