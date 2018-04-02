package com.example.admin.check.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.kzcpm.scene.R;
import com.kzcpm.scene.interfaces.IDateDialogBack;
import com.kzcpm.scene.utils.DateUtils;

/**
 * 日期选择对话框
 * Created by yunfei on 2016/3/31.
 */
public class DateDialog {

    private IDateDialogBack IDateDialogBack;

    private AlertDialog dateDialog;

    private Context context;

    private String date;//默认时间
    private boolean isSetDate = false;//是否设置默认时间

    public DateDialog(Context context) {
        this.context = context;
    }

    public void setDate(String date) {
        this.date = date;
        isSetDate = true;
    }

    public void show() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_select_date, null);

        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);

        if (isSetDate) {//设置默认时间
            String[] dateString = date.split("-");
            datePicker.updateDate(Integer.parseInt(dateString[0]), Integer.parseInt(dateString[1]) - 1,
                    Integer.parseInt(dateString[2]));
        }

        Button sure_bt = (Button) view.findViewById(R.id.sure_bt);
        sure_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IDateDialogBack.getDate(datePicker.getYear() + "", DateUtils.getNumber(datePicker.getMonth() + 1),
                        DateUtils.getNumber(datePicker.getDayOfMonth()), dateDialog);
            }
        });

        builder.setView(view);
        dateDialog = builder.create();
        dateDialog.show();
    }

    /**
     * 设置回调接口
     *
     * @param IDateDialogBack 回调接口
     */
    public void setIDateDialogBack(IDateDialogBack IDateDialogBack) {
        this.IDateDialogBack = IDateDialogBack;
    }
}