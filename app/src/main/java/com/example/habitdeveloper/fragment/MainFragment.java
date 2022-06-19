package com.example.habitdeveloper.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habitdeveloper.R;
import com.example.habitdeveloper.adapter.ActionAdapter;
import com.example.habitdeveloper.customview.CustomInputDialog;
import com.example.habitdeveloper.habitdb.DBUtils;
import com.example.habitdeveloper.habitdb.MyDatabaseHelper;
import com.example.habitdeveloper.habitdb.entity.Action;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Action> actions;
    private FloatingActionButton button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        button = view.findViewById(R.id.floatingButton);
        button.setOnClickListener(e->showAddDialog());

        recyclerView = view.findViewById(R.id.act_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        showAllActions();

        return view;
    }

    public void showAddDialog(){
        CustomInputDialog inputDialog = new CustomInputDialog(getActivity());
        inputDialog.setOnClickCancelListener(e->{
            Toast.makeText(getActivity(), "取消添加行为", Toast.LENGTH_SHORT).show();
            inputDialog.dismiss();
        });
        inputDialog.setOnClickSureListener(e->{
            MyDatabaseHelper instance = DBUtils.getInstance(getActivity());
            SQLiteDatabase database = instance.getWritableDatabase();
            DBUtils dbUtils = new DBUtils(database);

            Action action = new Action();
            action.setName(inputDialog.getActionName());
            action.setTimes(inputDialog.getActionTime());
            dbUtils.AddAction(action);

            database.close();

            showAllActions();

            Toast.makeText(getActivity(), "新增成功", Toast.LENGTH_LONG).show();
            inputDialog.dismiss();
        });
        inputDialog.show();
    }

    @Deprecated
    public List<Action> fakeData(){
        return null;
    }

    public void showAllActions(){
        MyDatabaseHelper instance = DBUtils.getInstance(getActivity());
        SQLiteDatabase database = instance.getReadableDatabase();
        DBUtils dbUtils = new DBUtils(database);

        dbUtils.createTable();

        actions = dbUtils.getAllAction();

        if(actions != null) {
            ActionAdapter actionAdapter = new ActionAdapter(getActivity(), actions, this);
            recyclerView.setAdapter(actionAdapter);
        }
        database.close();
    }
}
