package com.example.habitdeveloper.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habitdeveloper.R;
import com.example.habitdeveloper.adapter.ActionAdapter;
import com.example.habitdeveloper.habitdb.entity.Action;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Action> actions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = view.findViewById(R.id.act_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        actions = fakeData();
        ActionAdapter actionAdapter = new ActionAdapter(getActivity(), actions);
        recyclerView.setAdapter(actionAdapter);
        return view;
    }

    public List<Action> fakeData(){
        ArrayList<Action> actions = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            actions.add(new Action());
        return actions;
    }
}
