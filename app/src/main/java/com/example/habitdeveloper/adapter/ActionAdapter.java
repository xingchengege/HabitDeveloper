package com.example.habitdeveloper.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habitdeveloper.R;
import com.example.habitdeveloper.activity.CountDownActivity;
import com.example.habitdeveloper.habitdb.entity.Action;

import java.util.List;

public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.InnerHolder> {

    private final List<Action> actions;
    private final Context context;

    public ActionAdapter(Context context, List<Action> data){
        this.actions = data;
        this.context = context;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_action_view, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
//        holder.setData(actions.get(position));
        holder.setData();
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
            //textView.setText(action.getName());
            //textClock.setText(String.format("%d min", action.getTime()));
        }

        public void setData(){
            textView.setText("健身运动");
            textClock.setText("jjim");
            bindClickListener();
        }

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
