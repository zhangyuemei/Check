package com.example.admin.check.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kzcpm.scene.R;
import com.kzcpm.scene.interfaces.IMyEdiTextDialogBack;

/**
 * 自定义输入对话框
 * Created by yunfei on 2017/6/12.
 */

public class MyEdiTextDialog {

    private IMyEdiTextDialogBack iMyEdiTextDialogBack;

    private EditText contentEt;

    private Context context;

    private String title = "", content = "";//提示内容

    public MyEdiTextDialog(Context context) {
        this.context = context;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EditText getEdiText() {
        return contentEt;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_my_dialog, null);

        TextView titleTv = (TextView) view.findViewById(R.id.title_tv_my_d);
        TextView contentTv = (TextView) view.findViewById(R.id.content_tv_my_d);
        contentEt = (EditText) view.findViewById(R.id.content_et_my_d);

        contentTv.setVisibility(View.GONE);
        contentEt.setVisibility(View.VISIBLE);

        if (!title.equals("")) {
            titleTv.setText(title);
        } else {
            titleTv.setVisibility(View.GONE);
        }

        if (!content.equals("")) {
            contentEt.setText(content);
        }

        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                iMyEdiTextDialogBack.sure(contentEt.getText().toString().trim());
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                iMyEdiTextDialogBack.cancel();
            }
        });

        builder.create().show();
    }

    /**
     * 设置回调接口
     *
     * @param iMyEdiTextDialogBack 回调接口
     */
    public void setIMyEdiTextDialogBack(IMyEdiTextDialogBack iMyEdiTextDialogBack) {
        this.iMyEdiTextDialogBack = iMyEdiTextDialogBack;
    }
}
