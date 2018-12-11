package com.mgtv.autoplug.cloudcenter.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mgtv.autoplug.cloudcenter.service.fallback.AliyunCloudFallbackService;

@FeignClient(name="ci-aliclient",fallback=AliyunCloudFallbackService.class)
public interface AliyunCloudService {
	
	@RequestMapping(value="/updateScalImage",method=RequestMethod.POST)
	String updateScalImage(@RequestParam("intraIp") String intraIp, @RequestParam("autoScalId") String autoScalId, @RequestParam("imagePrefix") String imagePrefix);
	
	@RequestMapping(value="/scalImageStatus",method=RequestMethod.GET)
	String scalImageStatus(@RequestParam("intraIp") String intraIp);
}
