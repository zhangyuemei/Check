package com.example.admin.check.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.admin.check.R;


/**
 * 网络请求进度
 * 作者：柏云飞 on 2015/9/14.
 * 邮箱：306200335@qq.com
 */
public class ProgressDialog extends Dialog {

    private static ProgressDialog progressDialog = null;
    private TextView messageTv;

    public ProgressDialog(Context context) {
        super(context);
    }

    public ProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public static ProgressDialog createDialog(Context context) {
        progressDialog = new ProgressDialog(context, R.style.ProgressDialog);
        progressDialog.setContentView(R.layout.dialog_progress);
        progressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }

    public void onWindowFocusChanged(boolean hasFocus) {

        if (progressDialog == null) {
            return;
        }
    }

    /**
     * [Summary]
     * setTitile 标题
     *
     * @param strTitle
     * @return
     */
    public ProgressDialog setTitile(String strTitle) {
        return progressDialog;
    }

    /**
     * [Summary]
     * setMessage 提示内容
     *
     * @param strMessage
     * @return
     */
    public void setMessage(String strMessage) {
        messageTv = (TextView) progressDialog.findViewById(R.id.content_tv_d);
        messageTv.setVisibility(View.VISIBLE);

        if (messageTv != null) {
            messageTv.setText(strMessage);
        }
    }

    /**
     * 去除提示内容
     */
    public void dismissMessage() {
        messageTv.setVisibility(View.VISIBLE);
    }
}
