package com.swpu.uchain.takeawayapplet.util.wechatUtil;

import com.alibaba.fastjson.JSONObject;
import com.swpu.uchain.takeawayapplet.VO.WeChatVO;
import com.swpu.uchain.takeawayapplet.config.WeChatProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GetOpenIdUtil
 * @Author hobo
 * @Date 19-4-3 下午1:47
 * @Description
 **/
@Service
public class GetOpenIdUtil {

    @Autowired
    private WeChatProperties weChatProperties;

    public String getOpenId(String code) {
        Map map = new HashMap();
        if (code == null) {
            return "Code_Error";
        }
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + weChatProperties.getAppid() + "&secret=" + weChatProperties.getSecret() + "&js_code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        WeChatVO weChatVO = JSONObject.parseObject(response, WeChatVO.class);
        return weChatVO.getOpenId();
    }
}