package com.zplay.android.sdk.online.adapter.huawei;


/**
 * 存放联盟参数和插件对象
 * 
 * @author h00193325
 * 
 */
public class HuaWeiParam
{
    /*
     * 支付页面横竖屏参数：1表示竖屏，2表示横屏，默认竖屏
     */
    public static final int PAY_ORI = 1;
   
    public interface PayParams
    {
        public static final String USER_ID = "userID";
        
        public static final String APPLICATION_ID = "applicationID";
        
        public static final String AMOUNT = "amount";
        
        public static final String PRODUCT_NAME = "productName";
        
        public static final String PRODUCT_DESC = "productDesc";
        
        public static final String REQUEST_ID = "requestId";
        
        public static final String USER_NAME = "userName";
        
        public static final String SIGN = "sign";
        
        public static final String NOTIFY_URL = "notifyUrl";
        
        public static final String SERVICE_CATALOG = "serviceCatalog";
        
        public static final String SHOW_LOG = "showLog";
        
        public static final String SCREENT_ORIENT = "screentOrient";
        
        public static final String SDK_CHANNEL = "sdkChannel";
        
        public static final String URL_VER = "urlver";
    }
    
}
