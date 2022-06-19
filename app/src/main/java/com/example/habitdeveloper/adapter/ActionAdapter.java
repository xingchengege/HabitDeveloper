package com.example.habitdeveloper.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habitdeveloper.R;
import com.example.habitdeveloper.activity.CountDownActivity;
import com.example.habitdeveloper.fragment.MainFragment;
import com.example.habitdeveloper.habitdb.DBUtils;
import com.example.habitdeveloper.habitdb.MyDatabaseHelper;
import com.example.habitdeveloper.habitdb.entity.Action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.InnerHolder> {

    private final List<Action> actions;
    private final Context context;
    private final MainFragment parent;
    private static final List<Integer> colorResList = new ArrayList<>();
    static {
        colorResList.add(R.color.blue);
        colorResList.add(R.color.brown);
        colorResList.add(R.color.yellow);
        colorResList.add(R.color.pink);
        colorResList.add(R.color.green);
        Collections.shuffle(colorResList);
    }

    public ActionAdapter(Context context, List<Action> data, MainFragment parent){
        this.actions = data;
        this.context = context;
        this.parent = parent;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_action_view, null);
        return new InnerHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.setData(actions.get(position));
        holder.setColor(colorResList.get(position%colorResList.size()));
    }

    @Override
    public int getItemCount() {
        if (actions != null) {
            return actions.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private final TextView textClock;
        private final CardView cardView;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.action_name);
            this.textClock = itemView.findViewById(R.id.action_time);
            this.cardView = itemView.findViewById(R.id.action_card);
        }

        public void setData(Action action){
            textView.setText(action.getName());
            textClock.setText(String.format("%d min", action.getTimes()));
            cardView.setOnClickListener(e -> {
                Intent intent = new Intent(context, CountDownActivity.class);
                intent.putExtra("name", action.getName());
                intent.putExtra("time", action.getTimes());
                context.startActivity(intent);
            });

            cardView.setOnLongClickListener(view -> {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("确认删除该活动?");
                builder.setPositiveButton("确认", (dialog, arg1) -> {
                    MyDatabaseHelper instance = DBUtils.getInstance(context);
                    SQLiteDatabase database = instance.getWritableDatabase();
                    DBUtils db = new DBUtils(database);
                    db.deleteAction(action);
                    dialog.dismiss();
                    Toast.makeText(context, "已删除对应活动", Toast.LENGTH_SHORT).show();
                    database.close();
                    parent.showAllActions();
                });
                builder.setNegativeButton("取消", (dialog, which) -> {
                    dialog.dismiss();
                    Toast.makeText(context, "取消删除", Toast.LENGTH_SHORT).show();
                });
                builder.create().show();
                return true;
            });
        }

        public void setColor(int color){
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, color));
        }

        @Deprecated
        public void setData(){
            textView.setText("健身运动");
            textClock.setText("jjim");
            bindClickListener();
        }

        @Deprecated
        public void bindClickListener(){
            cardView.setOnClickListener(e -> {
                Intent intent = new Intent(context, CountDownActivity.class);
                intent.putExtra("name", "健身运动");
                intent.putExtra("time", 30);
                context.startActivity(intent);
            });
        }
    }
}
