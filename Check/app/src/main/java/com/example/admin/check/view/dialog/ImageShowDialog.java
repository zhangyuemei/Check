package com.example.admin.check.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.kzcpm.scene.R;
import com.kzcpm.scene.app.AppManager;
import com.kzcpm.scene.model.bean.Photo;
import com.kzcpm.scene.utils.Constants;
import com.kzcpm.scene.view.ExpandableTextView;
import com.kzcpm.scene.view.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 图片浏览对话框
 * Created by 30620 on 2017/12/4.
 */

public class ImageShowDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private RelativeLayout bottomRl;
    public ImageView leftIv, centerIv, rightIv;
    private TextView photoNumberTv;
    private ExpandableTextView expandableTv;
    private View lineView;
    private PhotoViewPager photoViewPager;

    private OnClickBack onClickBack;
    private ImageClickBack imageClickBack;

    private List<Photo> photoList;//显示的图片集合
    private List<ImageView> imageViewList;
    private List<PhotoViewAttacher> photoViewAttacherList;

    private int position = 0;//显示图片的位置
    private boolean isBrushes = false;//是否有涂鸦图片
    private boolean isTopInfo = true;//顶部信息是否显示
    private boolean isBottomInfo = true;//底部信息是否显示
    private boolean isDescribe = false;//是否显示描述

    public final static int LEFT_CLICK = -1;//左侧按钮点击
    public final static int CENTER_CLICK = 0;//中间按钮点击
    public final static int RIGHT_CLICK = 1;//右侧按钮点击

    public ImageShowDialog(Context context) {
        super(context);
        this.context = context;
    }

    public ImageShowDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_image_show);

        //设置对话框宽度填满屏幕
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(layoutParams);

        leftIv = findViewById(R.id.left_iv_add_image_d);
        leftIv.setOnClickListener(this);
        centerIv = findViewById(R.id.center_iv_add_image_d);
        centerIv.setOnClickListener(this);
        rightIv = findViewById(R.id.right_iv_add_image_d);
        rightIv.setOnClickListener(this);
        photoNumberTv = findViewById(R.id.photo_number_tv_add_image_d);
        expandableTv = findViewById(R.id.expandable_tv_add_image_d);
        bottomRl = findViewById(R.id.bottom_rl_add_image_d);
        lineView = findViewById(R.id.line_add_image_d);

        photoViewPager = findViewById(R.id.photo_view_pager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_iv_add_image_d://左侧按钮
                onClickBack.clickBack(LEFT_CLICK, position);
                break;
            case R.id.center_iv_add_image_d://中间按钮
                onClickBack.clickBack(CENTER_CLICK, position);
                break;
            case R.id.right_iv_add_image_d://右侧按钮
                onClickBack.clickBack(RIGHT_CLICK, position);
                break;
        }
    }

    /**
     * 设置要显示的图片集合
     */
    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    /**
     * 设置要显示的单个图片
     */
    public void setPhoto(Photo photo) {
        photoList = new ArrayList<>();
        photoList.add(photo);
    }

    /**
     * 设置要显示的图片位置
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * 设置是否有涂鸦图片
     *
     * @param isBrushes 是否已有涂鸦图片
     */
    public void setIsBrushes(boolean isBrushes) {
        this.isBrushes = isBrushes;
    }

    /**
     * 设置顶部信息是否显示
     *
     * @param isShow 是否显示
     */
    public void setTopInfo(boolean isShow) {
        if (isShow) {
            leftIv.setVisibility(View.VISIBLE);
            centerIv.setVisibility(View.VISIBLE);
            rightIv.setVisibility(View.VISIBLE);
            isTopInfo = true;
        } else {
            leftIv.setVisibility(View.GONE);
            centerIv.setVisibility(View.GONE);
            rightIv.setVisibility(View.GONE);
            isTopInfo = false;
        }
    }

    /**
     * 设置底部信息是否显示
     *
     * @param isShow 是否显示
     */
    public void setBottomInfo(boolean isShow) {
        if (isShow) {
            bottomRl.setVisibility(View.VISIBLE);
            isBottomInfo = true;
        } else {
            bottomRl.setVisibility(View.GONE);
            isBottomInfo = false;
        }
    }

    /**
     * 设置描述信息是否显示
     *
     * @param isShow 是否显示
     */
    public void setDescribeInfo(boolean isShow) {
        if (isShow) {
            lineView.setVisibility(View.VISIBLE);
            expandableTv.setVisibility(View.VISIBLE);
            isBottomInfo = true;
        } else {
            lineView.setVisibility(View.GONE);
            expandableTv.setVisibility(View.GONE);
            isBottomInfo = false;
        }
    }

    @Override
    public void show() {
        super.show();

        if (!isDescribe) {//没有描述的话，一张图片不显示底部信息
            if (photoList.size() == 1) {
                setBottomInfo(false);
            } else {
                setBottomInfo(true);
            }
        } else {
            expandableTv.setText(photoList.get(position).describe);
        }

        photoNumberTv.setText(position + 1 + "/" + photoList.size());

        imageViewList = new ArrayList<>();
        photoViewAttacherList = new ArrayList<>();

        for (int i = 0; i < photoList.size(); i++) {
            ImageView imageView = new ImageView(context);

            //添加图片放大缩小功能
            final PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(imageView);
            photoViewAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    //点击图片的时间回调
                    if (imageClickBack != null) {
                        imageClickBack.clickPhoto(position);
                    }
                }

                @Override
                public void onOutsidePhotoTap() {
                    if (imageClickBack != null) {
                        imageClickBack.clickOutside();
                    }
                }
            });
            photoViewAttacherList.add(photoViewAttacher);

            //不同的图片显示类型
            if (photoList.get(i).type.equals(Constants.LOCAL_PHOTO)) {
                if (photoList.get(i).brushesPath == null || photoList.get(i).brushesPath.equals("")) {
                    imageView.setImageURI(Uri.parse(photoList.get(i).localPath));
                } else {
                    imageView.setImageURI(Uri.parse(photoList.get(i).brushesPath));
                }
            } else if (photoList.get(i).type.equals(Constants.NETWORK_PHOTO)) {

                Glide.with(AppManager.getAppManager().currentActivity()).load(photoList.get(i).photoPath)
                        .error(R.mipmap.load_fail).crossFade()
                        .into(new GlideDrawableImageViewTarget(imageView) {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                                super.onResourceReady(resource, animation);
                                photoViewAttacher.update();//调用PhotoViewAttacher的update()方法，使图片重新适配布局
                            }

                            @Override
                            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                super.onLoadFailed(e, errorDrawable);
                                photoViewAttacher.update();//调用PhotoViewAttacher的update()方法，使图片重新适配布局
                            }
                        });
            }
            imageViewList.add(imageView);
        }

        photoViewPager.setAdapter(new ImageAddPagerAdapter(imageViewList));
        photoViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ImageShowDialog.this.position = position;
                photoNumberTv.setText(position + 1 + "/" + photoList.size());

                if (isDescribe) {
                    expandableTv.setText(photoList.get(position).describe);
                }

                if (isBrushes) {
                    if (photoList.get(position).brushesPath == null ||
                            photoList.get(position).brushesPath.equals("")) {
                        centerIv.setVisibility(View.GONE);
                    } else {
                        centerIv.setImageResource(R.mipmap.paint);
                        centerIv.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        photoViewPager.setOffscreenPageLimit(photoList.size());
        photoViewPager.setCurrentItem(position);
    }

    /**
     * 设置某个位置的图片显示
     */
    public void setPhoto(String photoPath, final int position) {

        Glide.with(AppManager.getAppManager().currentActivity()).load(photoPath)
                .crossFade().into(new GlideDrawableImageViewTarget(imageViewList.get(position)) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                super.onResourceReady(resource, animation);
                photoViewAttacherList.get(position).update();//调用PhotoViewAttacher的update()方法，使图片重新适配布局
            }
        });
    }

    /**
     * 设置按钮点击事件回调
     *
     * @param onClickBack 按钮点击事件回调
     */
    public void setOnClickBack(OnClickBack onClickBack) {
        this.onClickBack = onClickBack;
    }

    /**
     * 设置图片点击事件回调
     *
     * @param imageClickBack 图片点击事件回调
     */
    public void setImageClickBack(ImageClickBack imageClickBack) {
        this.imageClickBack = imageClickBack;
    }

    /**
     * 添加图片时点击图片适配器
     * Created by yunfei on 2016/5/11.
     */
    public class ImageAddPagerAdapter extends PagerAdapter {

        private List<ImageView> imageViewList;

        public ImageAddPagerAdapter(List<ImageView> imageViewList) {
            this.imageViewList = imageViewList;//构造方法，参数是我们的页卡，这样比较方便。
        }

        @Override
        public int getCount() {
            return imageViewList.size();//返回页卡的数量
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {  //这个方法用来实例化页卡
            container.addView(imageViewList.get(position), 0);//添加页卡
            return imageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewList.get(position));//删除页卡
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;//官方提示这样写
        }
    }

    /**
     * 按钮点击事件回调接口
     */
    public interface OnClickBack {
        void clickBack(int type, int position);
    }

    /**
     * 图片点击事件回调接口
     */
    public interface ImageClickBack {
        void clickPhoto(int position);

        void clickOutside();
    }
}

