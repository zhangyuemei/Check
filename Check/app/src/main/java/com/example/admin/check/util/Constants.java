package com.example.admin.check.util;

/**
 * 作者：柏云飞 on 2015/9/9.
 * 邮箱：306200335@qq.com
 */
public class Constants {

    //SharedPreferences标识
    public static final String SCENE = "scene";

    //token标识
    public static final String TOKEN = "token";

    //token值
    public static final String token = "704DBY3HR8VJDEZM6RA";

    //移动移动设备类型(android or ios)
    public static final String MOBILE_TYPE = "mobileType";

    //版本
    public static final String VERSION = "version";

    //版本地址(本地，内测，云端)
    public static final String ADDRESS_TYPE = "addressType";

    //是否是首次打开app
    public static final String IS_FIRST_START = "isFirstStart";

    //登录
    public static final String LOGIN = "login";

    //是否记住密码
    public static final String GET_PASSWORD = "getPassword";

    //项目对应的URL
    public static final String SERVER_URL = "serverUrl";

    //项目
    public static final String PROJECT_NAME = "project";

    //项目简称
    public static final String PROJECT_SIMPLE_NAME = "projectSimpleName";

    //项目IDclientGroupUid
    public static final String PROJECT_ID = "projectId";

    //项目组ID
    public static final String PROJECT_GROUP_ID = "clientGroupUid";

    //选择项目项目用的ID
    public static final String SELECT_PROJECT_USER_ID = "selectProjectUserId";

    //创建项目用户ID
    public static final String CREATE_PROJECT_USER_ID = "createProjectUserId";

    //微现场左侧菜单是否已请求
    public static final String IS_LEFT_MENU = "isLeftMenu";

    //微现场左侧菜单
    public static final String LEFT_MENU = "leftMenu";

    //模块
    public static final String ACCEPTANCE = "acceptance";//工程验收
    public static final String CHANGE = "change";//变更
    public static final String CHANGE_VISA = "changeVisa";//签证
    public static final String CHECK = "check";//现场检查
    public static final String CHECK_MANAGE = "checkManage";//检查管理
    public static final String CONDITION = "condition";//现场工况
    public static final String PROGRESS = "progress";//进度工况
    public static final String LOG = "log";//项目日志
    public static final String LOG_SG = "logSg";//施工日志
    public static final String LOG_JL = "logJl";//监理日志
    public static final String LOG_YZ = "logYz";//业主日志
    public static final String MATERIAL = "material";//设备材料
    public static final String TOPIC = "topic";//问题讨论

    //用户信息常量
    public static final String USER_ID = "userId";
    public static final String PROJECT_USER_ID = "projectUserId";
    public static final String USER_USERNAME = "username";//用户帐号
    public static final String USER_PASSWORD = "password";
    public static final String USER_NAME = "name";//用户名
    public static final String USER_COMPANY = "company";
    public static final String USER_PHONE = "phone";
    public static final String USER_EMAIL = "email";
    public static final String USER_IMAGE = "image";
    public static final String USER_SIG = "usersig";//聊天使用的用户密钥
    public static final String ACCOUNT = "account";//帐号
    public static final String USER_TAG = "user_tag";//用户类型

    //类型
    public static final String TYPE = "type";
    //消息类型
    public static final String MESSAGE_TYPE = "messageType";
    //会话消息
    public static final String MESSAGE_CHAT = "messageChat";
    //任务消息
    public static final String MESSAGE_TASK = "messageTask";
    //模块内的类型
    public static final String MODULE_TYPE = "moduleType";
    //日期时间
    public static final String DATE = "date";
    //状态
    public static final String STATE = "state";
    //施工位置
    public static final String LOCATION = "location";
    //文档
    public static final String DOCUMENT = "document";

    //全部类型
    public static final String ALL_TYPE = "all";
    //我发布的类型
    public static final String MY_PUBLISH_TYPE = "my";
    //我关注类型
    public static final String MY_FOLLOW_TYPE = "myFollow";
    //我处理过类型
    public static final String MY_PROCESS_TYPE = "myProcess";
    //我的草稿
    public static final String MY_DRAFT_TYPE = "myDraft";

    //添加类型
    public static final String ADD_TYPE = "addType";
    //编辑类型
    public static final String UPDATE_TYPE = "updateType";

    //自带数据类型
    public static final String DATA_TYPE = "dataType";
    //需通过id请求类型
    public static final String GET_BY_ID_TYPE = "getByIdType";

    //当前项目类型
    public static final String CURRENT_PROJECT_TYPE = "currentProjectType";

    //获取可以@的人员类型
    public static final String REMIND_TYPE = "remindType";
    //获取可以创建聊天的人员类型
    public static final String CREATE_CHAT_TYPE = "createChatType";

    //文件类型编号
    public static final String FILE_TYPE = "file_type";
    //文件存在的表名
    public static final String TARGET_TABLE = "target_table";
    //文件存在的列名
    public static final String TARGET_COL = "target_col";
    //文件存在的UID
    public static final String TARGET_UID = "target_uid";
    //文件创建人id
    public static final String CREATE_USER = "create_user";

    //返回类型-添加
    public static final int RESULT_ADD = 11;
    //返回类型-更新
    public static final int RESULT_UPDATE = 12;
    //返回类型-删除
    public static final int RESULT_DELETE = 13;
    //详情类型-添加评论
    public static final int COMMENT_ADD = 21;
    //详情类型-删除评论
    public static final int COMMENT_DELETE = 22;
    //详情类型-添加处理
    public static final int PROCESS_ADD = 23;
    //详情类型-关闭处理
    public static final int PROCESS_CLOSE = 24;
    //文档-文件移动
    public static final int FILE_MOVE = 31;
    //文档-文件复制
    public static final int FILE_COPY = 32;

    //评论ID
    public static final String COMMENT_ID = "commentId";

    //处理ID
    public static final String PROCESS_ID = "processId";

    //本地图片
    public static final String LOCAL_PHOTO = "localPhoto";

    //网络图片
    public static final String NETWORK_PHOTO = "networkPhoto";

    /*
    设置相关
     */

    //无网络
    public static String NO_NETWORK = "noNetwork";

    //移动网络
    public static String MOBILE_NETWORK = "mobileNetwork";

    //无线网络
    public static String WIFI_NETWORK = "wifiNetwork";

    //是否推送
    public static String CLOUD_CHANNEL = "cloudChannel";

    //推送相关
    public static String DEVICE_ID = "deviceId";

    //基坑监测
    //天气
    public static final String WEATHER = "weather";

    //我关注的点集合
    public static final String MY_POINTS = "my_points";

    //测点ID
    public static final String POINT_ID = "pointId";

    //测点编号
    public static final String POINT_NUMBER = "number";

    //测点类型ID
    public static final String POINT_TYPE_ID = "typeId";

    //测点类型
    public static final String POINT_TYPE = "type";

    //开始日期
    public static final String START_DATE = "startDate";

    //结束日期
    public static final String END_DATE = "endDate";

    //莹石云accessToken
    public static final String ACCESS_TOKEN = "accessToken";
}
