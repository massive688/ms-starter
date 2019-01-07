package tp.ms.common.bean.result;

import java.util.Arrays;

public enum BaseResultCode implements ResultCode {
    // Use standard HTTP status code
    OK(2000, "OK"),
    CREATED(2001, "Created"),
    BAD_REQUEST(4000, "Bad Request"),
    UNAUTHORIZED(4001, "Unauthorized"),
    FORBIDDEN(4003, "Forbidden"),
    NOT_FOUND(4004, "Not Found"),
    CONFLICT(4009, "Conflict"),
    INTERNAL_SERVER_ERROR(5000, " Internal Server Error"),
    
    DATA_ERROR(5002, " data Error"),
    EXCEPTION(5003, "exception"),

    TAG_CATEGORY_NAME_CONFLICT(1201, "标签分类名称不能重复"),
    USER_ACCOUNT_CREATE_ERROR(1100, "创建用户账号失败"),
    USER_CELLPHONE_DUPLICATED(1105, "手机号重复"),
    USER_CELLPHONE_BINDED(1106, "手机号已绑定"),
    USER_NOT_EXIT(1107, "用户不存在"),
    PASS_ERROR(1108, "密码错误"),
    PHONE_CODE_ERROR(1200, "手机验证码错误"),
    PHONE_SMS_REJECTED(1201, "发送手机短信失败"),
    OVER_MAX_LENGTH(1300, "超出最大长度"),
    PAY_SUCCESS(2000, "支付成功"),
    PAY_FAILED(2010, "支付失败"),

    AUTH_FAILED(4000, "登陆授权失败，手机号或密码错误。"),
    //门店管理员
    USER_NOT_PATCH_CELLPHONE(4002, "用户名与手机号不匹配"),

    PARAMETER_IS_NULL(5004, "参数为空"),

    //服务模块
    SERVICE_EXIT(6001, "已存在该服务"),
    BASIC_SERVICE_EXIT(6002, "已存在该基础服务"),
    BASIC_SERVICE_NOT_EXIT(6003, "不存在该基础服务"),


    //订单模块
    ORDER_NOT_EXIT(6100, "订单不存在"),
    ORDERSTATUS_NOT_CORRECT(6101, "订单状态不为未支付"),
    VOUCHER_NOT_ENOUGH(6102, "消费券数量不足（退款）"),
    VOUCHER_NOT_EXIT(6103, "消费券不存在"),
    VOUCHER_CAN_NOT_USE(6104, "消费券没有权限使用"),
    VOUCHER_STATUS_NOT_CORRECT(6105, "消费券状态不为待消费"),
    ORDER_TOTALPRICE_NOT_CORRECT(6106, "订单金额不正确"),
    ORDER_PAYTYPE_NOT_CORRECT(6107, "订单支付方式不正确"),
    ORDERSTATUS_NOT_WAITING_FOR_USE(6108, "订单状态不正确"),
    REFUND_FAILED(6109, "退款失败"),
    ORDER_IS_PAYING(6110, "订单支付中"),
    ORDER_UNITPRICE_NOT_CORRECT(6111, "订单单价不正确"),
    ORDER_MEMBERPRICE_NOT_CORRECT(6112, "订单会员单价不正确"),
    ORDER_MEMBETOTALRPRICE_NOT_CORRECT(6113, "订单会员总价不正确"),
    BABY_GOLD_IS_NOT_ENOUGH(6114, "宝币不足"),
    ORDER_ALREADY_EVALUATE(6115, "订单已评价"),
    NO_VOUCHER_TO_REFUND(6116, "没有可退的消费券"),
    


    //门店模块
    STOREID_NOT_EXIT(6200, "门店id不能为空"),
    STORE_NAME_NOT_EXIT(6201, "门店名称不能为空"),
    STORE_TELPHONE_ID_NOT_EXIT(6202, "门店联系电话不能为空"),
    STORE_LONGITUDE_NOT_EXIT(6203, "经度不能为空"),
    STORE_LATITUDE_NOT_EXIT(6204, "纬度不能为空"),
    STOREID_EXIT(6205, "门店id重复"),

    //育婴师
    NURSERYTEACHERID_NOT_EXIT(6301, "育婴师id不存在"),
    NURSERYTEACHERNAME_NOT_EXIT(6302, "育婴师名字不存在"),
    NURSERYTEACHER_STOREID_NOT_EXIT(6303, "门店联系电话不存在"),
    NURSERYTEACHERID_EXIT(6304, "育婴师id已存在"),

    //活动
    ALREADY_PARTICIPATE_ACTIVITY(6401, "已经参加该活动"),
    ACTIVITY_NOT_EXIT(6402, "活动不存在"),
    ACTIVITY_PARTICIPATE_TOO_MANY_PEOPLE(6403, "活动参与人数超过限制"),
    ACTIVITY_SHOULD_PAY(6404, "活动不是免费活动"),
    ACTIVITY_SHOULD_NOT_PAY(6405, "活动是免费活动"),
    ACTIVITY_IS_OVERTIME(6406, "活动已过截止日期"),
    ACTIVITY_IS_PAYING(6407, "活动已正在支付中"),
    ACTIVITY_RECORD_NOT_EXIT(6408, "没有生成报名记录"),
    ACTIVITY_PRICE_NOT_CORRECT(6409, "活动金额不正确"),


    //支付
    ALIPAY_PAY_PARAMS_ERROR(6501, "支付宝支付参数错误"),

    //用户相关
    PHONE_ALREADY_EXIT(6601, "手机号码已存在"),
    PHONE_IS_EMPTY(6602, "手机号码为空"),
    USERNAME_ALREADY_EXIT(6603, "已存在该手机号码注册的用户名"),

    //帖子

    //用户相关
    CELLPHONE_ALREADY_EXIT(6700, "手机号码已注册"),

    //宝币
    RECHARGE_AMOUNT_NO_CORRECT(6800, "充值金额小于0"),
    USERATTR_NOT_EXIT(6801, "用户账户信息未创建"),
    ORDER_ALREADY_EXIT(6802, "订单已存在"),

    //门店评价
    STARRATING_OVER_MAX(6116, "星级超过最大限制"),
    //用户操作（关注、收藏）
    ALREADY_FOLLOW_USER(6121, "已关注该用户"),
    ALREADY_FOLLOW_STORE(6122, "已收藏该门店"),
    UNFOLLOW_USER(6123, "未关注该用户"),
    UNFOLLOW_STORE(6124, "未收藏该门店"),
    CAN_NOT_FOLLOW_YOURSELF(6125, "不能关注自己"),

    QQ_UNBIND(6130, "QQ登录未绑定用户"),
    WECHAT_UNBIND(6131, "微信登录未绑定用户"),
    NOT_EXTERNAL_USERINFO(6132, "没有第三方登录用户信息"),
    NOT_REGISTER(6133, "用户未注册"),
	
	GET_OPENID_FAILED(6140, "获取openid失败"),
	
	INVITATION_CODE_NOT_EXIST(6150, "邀请码不存在或已被删除"),
	INVITATION_CODE_REPEAT(6151, "邀请码存在重复的"),
	INVITATION_CODE_NOT_SPARE(6152, "邀请码不可用"),
	INVITATION_CODE_BATCH_NOT_EXIST(6153, "邀请码的批次信息不存在"),
	INVITATION_CODE_OUT_OF_USETIME(6154, "邀请码已经过了截止使用日期"),
	USER_ALREADY_INVITED(6155, "用户已被邀请了"),
	BATCH_CODE_ALREADY_EXIST(6156, "批次号已存在"), 
	NO_AUTHORITY(5001,"NO_AUTHORITY");

	
    private final Integer code;
    private final String message;

    private BaseResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    @SuppressWarnings("unlikely-arg-type")
	public static ResultCode parseForCode(String code) {
        return Arrays.asList(BaseResultCode.values()).stream().filter(v -> v.code.equals(code)).findFirst().get();
    }
    
}
