package com.example.habitdeveloper.fragment;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.habitdeveloper.R;
import com.example.habitdeveloper.habitdb.DBUtils;
import com.example.habitdeveloper.habitdb.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AboutFragment extends Fragment {
    private TextView dayCount;
    private TextView wordName;

    static private final List<String> words = new ArrayList<>();
    static {
        words.add("每一天都是新的一天，明天会变的更好！");
        words.add("你像夏至的分界线，是我一生里最长的那个白天~");
        words.add("盛意以山河，山河不及你~");
        words.add("我的心是旷野的鸟，在你的眼里找到了天空。");
        Collections.shuffle(words);
    }

    static private int index = 0;

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        dayCount = view.findViewById(R.id.day_count_textview);
        wordName = view.findViewById(R.id.word_name);

        nextWord();
        return view;
    }

    public void nextWord(){
        MyDatabaseHelper instance = DBUtils.getInstance(getActivity());
        SQLiteDatabase database = instance.getReadableDatabase();
        DBUtils dbUtils = new DBUtils(database);

        int num = dbUtils.getALLRecord_days();
        dayCount.setText(String.format("%02d", num));
        wordName.setText(words.get((index++)% words.size()));
    }
}
