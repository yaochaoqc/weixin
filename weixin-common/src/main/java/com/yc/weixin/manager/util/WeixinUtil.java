package com.yc.weixin.manager.util;

import com.yc.weixin.entity.AccessToken;
import com.yc.weixin.menu.Menu;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 类名: WeixinUtil </br>
 * 包名： com.souvc.weixin.util
 * 描述: 公众平台通用接口工具类 </br>
 * 开发人员： souvc  </br>
 * 创建时间：  2015-12-1 </br>
 * 发布版本：V1.0  </br>
 */
public class WeixinUtil {

    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

    // 获取access_token的接口地址（GET） 限2000（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    // 菜单创建（POST） 限1000（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    // 菜单创建（POST） 限1000（次/天）
    public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    public static String meun_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    /**
     * 获取access_token
     *
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;

        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = CommonUtil.httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }

    /**
     * 创建菜单
     *
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;
        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = CommonUtil.httpRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }

    /**
     * 删除菜单
     *
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int deleteMenu(String accessToken) {
        int result = 0;
        // 拼装删除菜单的url
        String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
        String jsonMenu = "";
        // 调用接口创建菜单
        JSONObject jsonObject = CommonUtil.httpRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    /**
     * 获取菜单
     *
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static String getMenu(String accessToken) {
        String result = "";
        // 拼装删除菜单的url
        String url = meun_get_url.replace("ACCESS_TOKEN", accessToken);
        String jsonMenu = "";
        // 调用接口创建菜单
        JSONObject jsonObject = CommonUtil.httpRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
//            Iterator<String> items = jsonObject.keys();
//            while(items.hasNext()){
//                // 获得key
//                String key = items.next();
//                // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
//                String value = jsonObject.getString(key);
//                System.out.println("key: "+key+",value"+value);
//                System.out.println();
//            }
            result = jsonObject.toString();
        }
        return result;
    }

}