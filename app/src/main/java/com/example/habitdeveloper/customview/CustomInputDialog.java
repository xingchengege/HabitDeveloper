package com.example.habitdeveloper.customview;

import android.app.Dialog;
import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.habitdeveloper.R;

public class CustomInputDialog extends Dialog {

    private final TextView btnSure;
    private final TextView btnCancel;
    private final TextView nameInput;
    private final TextView timeInput;

    public CustomInputDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_input, null);

        setContentView(view);

        btnSure = findViewById(R.id.dialog_confirm_sure);
        btnCancel = findViewById(R.id.dialog_confirm_cancel);
        nameInput = findViewById(R.id.action_name_editor);
        timeInput = findViewById(R.id.action_time_editor);
        timeInput.setFilters(new InputFilter[]{(charSequence, i, i1, spanned, i2, i3) -> {
            try {
                int judge = Integer.parseInt(spanned.subSequence(0, i2).toString() + charSequence + spanned.subSequence(i3, spanned.length()));
                if (judge > 0 && judge < 100)
                    return null;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return "";
        }});


    }

    public String getActionName(){
        return nameInput.getText().toString();
    }

    public int getActionTime(){
        try {
            return Integer.parseInt(timeInput.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public void setOnClickSureListener(View.OnClickListener listener){
        btnSure.setOnClickListener(listener);
    }

    public void setOnClickCancelListener(View.OnClickListener listener){
        btnCancel.setOnClickListener(listener);
    }
}
