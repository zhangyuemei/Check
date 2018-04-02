package com.example.admin.check.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;

import com.example.admin.check.model.bean.Appendix;
import com.example.admin.check.model.bean.Photo;
import com.example.admin.check.view.MyToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 作者：柏云飞 on 2015/9/23.
 * 邮箱：306200335@qq.com
 * 图片处理工具类
 */
public class MyImageUtils {
    public static final int PHOTO_REQUEST_TAKE_PHOTO = 0;// 拍照
    public static final int PHOTO_REQUEST_GALLERY = 1;// 从本地选择
    public static final int PHOTO_REQUEST_CUT = 2;// 裁剪

    public static final int REQUEST_CODE_CAMERA = 1000;
    public static final int REQUEST_CODE_GALLERY = 1001;
    public static final int REQUEST_CODE_CROP = 1002;

    //应用文件夹
    public static final String APP_FILE = Environment.getExternalStorageDirectory() + "/TTekPM";

    //添加图片文件夹
    public static final String ADD_PHOTO_LOCATION = Environment.getExternalStorageDirectory() + "/TTekPM/photo/add/";
    //图片文件夹
    public static final String PHOTO_LOCATION = Environment.getExternalStorageDirectory() + "/TTekPM/download/photo/";
    //下载文件夹
    public static final String DOWNLOAD_LOCATION = Environment.getExternalStorageDirectory() + "/TTekPM/download/";

    public static List<Photo> getPhotoList(List<Appendix> appendixList) {
        List<Photo> photoList = new ArrayList<>();

        for (Appendix appendix : appendixList) {
            if (MyImageUtils.isImage(appendix.fileName)) {
                Photo photo = new Photo();
                photo.photoName = appendix.fileName;
                photo.photoPath = appendix.fileUrl;
                photo.copyPath = appendix.fileCopyUrl;
                photo.id = appendix.FILE_UID;
                photoList.add(photo);
            }
        }

        return photoList;
    }

    /**
     * 拍照获取图片
     */
    public static void startCameraPicCut(Activity activity, Uri takePhotoUri) {

        //判断文件夹是否存在
        File myFile = new File(MyImageUtils.PHOTO_LOCATION);
        if (!myFile.exists()) {
            myFile.mkdirs();
        }

        // 调用系统的拍照功能
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
        // 指定调用相机拍照后照片的储存路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, takePhotoUri);
        activity.startActivityForResult(intent, PHOTO_REQUEST_TAKE_PHOTO);
    }

    /**
     * 从本地获取图片
     */
    public static void startImageLocal(Activity activity) {
        //判断文件夹是否存在
        File myFile = new File(MyImageUtils.PHOTO_LOCATION);
        if (!myFile.exists()) {
            myFile.mkdirs();
        }

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        activity.startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    /**
     * 系统修改照片地址
     *
     * @param context
     * @param imageUri
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String versionChange(Context context, Uri imageUri) {

        //是否含有图片的文件格式，没有的话需要转换地址
        if (!isImage(imageUri.getPath())) {

            String filePath = "";

            if (DocumentsContract.isDocumentUri(context, imageUri)) {
                String wholeID = DocumentsContract.getDocumentId(imageUri);
                String id = wholeID.split(":")[1];
                String[] column = {MediaStore.Images.Media.DATA};
                String sel = MediaStore.Images.Media._ID + "=?";
                Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column,
                        sel, new String[]{id}, null);
                int columnIndex = cursor.getColumnIndex(column[0]);
                if (cursor.moveToFirst()) {
                    filePath = cursor.getString(columnIndex);
                }
                cursor.close();
            } else {
                String[] projection = {MediaStore.Images.Media.DATA};
                Cursor cursor = context.getContentResolver().query(imageUri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                filePath = cursor.getString(column_index);
            }
            return filePath;
        }

        return imageUri.getPath();
    }

    /**
     * 裁剪图片
     * uri获取图片的地址，imageUri保存图片的地址
     */
    public static void startPhotoZoom(Activity activity, Uri uri, Uri imageUri, int aspectX, int aspectY, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是裁剪框宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);

        // outputX outputY 是裁剪后生成图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size / aspectX * aspectY);
        intent.putExtra("return-data", true);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /**
     * 转换图片为bitmap
     *
     * @param uri 图片路径
     * @return
     */
    public static Bitmap decodeUriAsBitmap(Context context, Uri uri) {
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * copy图片
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean copyImage(String start, String end) {
        try {
            //要拷贝的图片
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(start)));
            //目标的地址
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(end)));
            try {
                int val = -1;
                while ((val = bis.read()) != -1) {

                    bos.write(val);
                }
                bos.flush();
                bos.close();
                bis.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 图片下载
     */
    public static void downloadImage(final Context context, String photoPath) {

        final String savePath = MyImageUtils.PHOTO_LOCATION;//下载的图片存放的路径
        final String fileName = DateUtils.getNowTime() + ".jpg";//图片名称

        OkHttpUtils
                .get()
                .url(photoPath)
                .build()
                .execute(new FileCallBack(savePath, fileName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        MyToast.showToast(context, "下载失败...");
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        MyToast.showToast(context, "下载成功! " + savePath + fileName);
                    }
                });
    }


    /**
     * 判断文件是否是图片
     *
     * @param fileName 文件名
     * @return 是否存在
     */
    public static boolean isImage(String fileName) {
        return fileName.contains("jpg") || fileName.contains("JPG") ||
                fileName.contains("jpeg") || fileName.contains("JPEG") ||
                fileName.contains("png") || fileName.contains("PNG")||
                fileName.contains("gif")|| fileName.contains("GIF");
    }
}
