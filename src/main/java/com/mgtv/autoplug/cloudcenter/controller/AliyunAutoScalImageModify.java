package com.mgtv.autoplug.cloudcenter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mgtv.autoplug.cloudcenter.service.AliyunCloudService;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/aliyun")
public class AliyunAutoScalImageModify {
	
	public final Logger logger = LoggerFactory.getLogger(AliyunAutoScalImageModify.class);
	
	@Autowired
	private AliyunCloudService aliyunCloudService;
	
	@Value("${cloud.aliyun.autoScalImageUpdate.statusURLPrefix}")
	private String statusURLPrefix;
	
	@RequestMapping(value="/updateScalImage", method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String updateScalImage(@RequestBody JSONObject jsonParam) {
		logger.info(jsonParam.toString());
		String intraIp = jsonParam.getString("intraIp");
		String autoScalId = jsonParam.getString("autoScalId");
		String imagePrefix = jsonParam.getString("imagePrefix");
		String result = aliyunCloudService.updateScalImage(intraIp, autoScalId, imagePrefix);
		if("OK".equals(result)) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("statusURL", statusURLPrefix+intraIp);
			return jsonObject.toString();
		}else {
			return result;
		}	
	}
	
	@RequestMapping(value="/scalImageStatus", method=RequestMethod.GET)
	public String scalImageStatus(@RequestParam(required=true) String intraIp) {
		return aliyunCloudService.scalImageStatus(intraIp);
	}

}
