package com.mgtv.autoplug.cloudcenter.service.fallback;

import org.springframework.stereotype.Component;

import com.mgtv.autoplug.cloudcenter.service.AliyunCloudService;

import net.sf.json.JSONObject;

@Component
public class AliyunCloudFallbackService implements AliyunCloudService{

	@Override
	public String updateScalImage(String intraIp, String autoScalId, String imagePrefix) {
		//服务降级，没法更新镜像时直接失败
		return "error";
	}

	@Override
	public String scalImageStatus(String intraIp) {
		//服务降级，获取不到状态信息时返回失败 
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "Failed");
		jsonObject.put("progress", "0%");
		return jsonObject.toString();
	}

}
