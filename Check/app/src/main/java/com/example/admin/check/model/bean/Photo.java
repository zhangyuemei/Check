package com.example.admin.check.model.bean;


import com.example.admin.check.util.Constants;

import java.io.Serializable;

/**
 * 图片列表类
 * 作者：柏云飞 on 2015/10/9.
 * 邮箱：306200335@qq.com
 */
public class Photo extends Base implements Serializable {
    public String id;//图片id
    public String photoName;//图片名称
    public String position;//图片位置
    public String type = Constants.NETWORK_PHOTO;//图片地址类型(本地or网络or)
    public String photoPath;//图片地址(网络)
    public String localPath;//图片地址(本地)
    public String copyPath;//图片缩略图地址
    public String brushesPath;//涂鸦图片地址
    public String describe;//图片描述
}
