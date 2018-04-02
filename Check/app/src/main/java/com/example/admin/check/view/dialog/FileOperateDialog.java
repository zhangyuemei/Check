package com.example.admin.check.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.kzcpm.scene.R;
import com.kzcpm.scene.interfaces.ISelectDialogBack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件(夹)操作对话框
 * Created by yunfei on 2017/7/24.
 */

public class FileOperateDialog {

    private Context context;
    private Dialog dialog;
    private GridView operateGv;
    private ISelectDialogBack iSelectDialogBack;

    public final static int DOWNLOAD = 0;//下载
    public final static int RENAME = 1;//重命名
    public final static int MOVE = 2;//移动
    public final static int DELETE = 3;//删除
    public final static int COPY = 4;//复制
    public final static int SHARE = 5;//分享
    public final static int MORE = 6;//更多

    // 图片封装为一个数组
    private int[] icon = {R.mipmap.document_download, R.mipmap.document_rename, R.mipmap.document_move,
            R.mipmap.document_delete, R.mipmap.document_copy, R.mipmap.document_share,
            R.mipmap.document_more};
    private String[] iconName = {"下载", "重命名", "移动", "删除", "复制", "分享", "更多"};
    private int[] iconNumber = {DOWNLOAD, RENAME, MOVE, DELETE, COPY, SHARE, MORE};
    private List<Map<String, Object>> moduleList = new ArrayList<>();

    public FileOperateDialog(@NonNull Context context) {

        this.context = context;
    }

    /**
     * @param operateList
     * @return
     */
    public void setOperate(List<Integer> operateList) {

        if (operateList != null) {

            for (int integer : operateList) {

                for (int i = 0; i < iconName.length; i++) {
                    if (integer == i) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("ItemImage", icon[i]);
                        map.put("ItemText", iconName[i]);
                        map.put("ItemNumber", iconNumber[i]);
                        moduleList.add(map);
                    }

                    if (moduleList.size() >= 6) {
                        break;
                    }
                }
            }
        }
    }

    public void initModuleData() {

        //设置微现场子模块列表适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(context, moduleList,
                R.layout.item_operate, new String[]{"ItemImage", "ItemText"},
                new int[]{R.id.image_scene_module_i, R.id.text_scene_module_i});
        //配置适配器
        operateGv.setAdapter(simpleAdapter);
        operateGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                iSelectDialogBack.getPosition((Integer) moduleList.get(position).get("ItemNumber"));
            }
        });
    }

    public void show() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_file_operate, null);
        operateGv = (GridView) view.findViewById(R.id.operate_gv_file_operate_i);

        initModuleData();

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }

    /**
     * 设置接口
     *
     * @param iSelectDialogBack
     */
    public void setSelectDialogBack(ISelectDialogBack iSelectDialogBack) {
        this.iSelectDialogBack = iSelectDialogBack;
    }
}
