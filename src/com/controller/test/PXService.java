package com.controller.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.controller.test.Util;

public class PXService {

	public static String ip = "";
	
	public static HttpServletRequest request = null;
	
	public PXService(HttpServletRequest request){
		this.request = request;
	}

	// 静态方法，类名可直接调用
	public static String isGet(String uri, TreeMap<String, String> map) {
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		// 配置连接超时时间
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(50000).setConnectionRequestTimeout(50000)
				.setSocketTimeout(50000).setRedirectsEnabled(true).build();
		// 装配get请求参数
		
		
		try {

			URIBuilder uriBuilder = new URIBuilder(uri);
			for (String key : map.keySet()) {
				
				uriBuilder.setParameter(key, String.valueOf(map.get(key)));
			}
			
			System.out.println(uriBuilder.build());
			HttpGet httpGet = new HttpGet(uriBuilder.build());
			// 此处增加浏览器端访问IP
			httpGet.addHeader("x-forwarded-for", Util.getIpAddr(request));
			// 设置超时时间
			httpGet.setConfig(requestConfig);
			// 执行get请求
			CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
			String strRequest = "";
			if (null != closeableHttpResponse && !"".equals(closeableHttpResponse)) {
				if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity httpEntity = closeableHttpResponse.getEntity();
					strRequest = EntityUtils.toString(httpEntity);
				} else {
					strRequest = "Error Response" + closeableHttpResponse.getStatusLine().getStatusCode();
				}
			}
			return strRequest;

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "协议异常";
		} catch (ParseException e) {
			e.printStackTrace();
			return "解析异常";
		} catch (IOException e) {
			e.printStackTrace();
			return "传输异常";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "解析异常";
		} finally {
			try {
				if (closeableHttpClient != null) {
					closeableHttpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 静态方法，类名可直接调用
	public static String isPost(String uri, TreeMap<String, String> map) {
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		// 配置连接超时时间
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(50000).setConnectionRequestTimeout(50000)
				.setSocketTimeout(50000).setRedirectsEnabled(true).build();
		HttpPost httpPost = new HttpPost(uri);
		// 设置超时时间
		httpPost.setConfig(requestConfig);
		if (!ip.equals("")) {
			httpPost.addHeader("x-forwarded-for", ip);
		}
		// 装配post请求参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (String key : map.keySet()) {
			list.add(new BasicNameValuePair(key, String.valueOf(map.get(key))));
		}

		try {
			// 将参数进行编码为合适的格式,如将键值对编码为param1=value1&param2=value2
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "utf-8");
			httpPost.setEntity(urlEncodedFormEntity);

			// 执行 post请求
			CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
			String strRequest = "";
			if (null != closeableHttpResponse && !"".equals(closeableHttpResponse)) {
				if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity httpEntity = closeableHttpResponse.getEntity();
					strRequest = EntityUtils.toString(httpEntity);
				} else {
					strRequest = "Error Response" + closeableHttpResponse.getStatusLine().getStatusCode();
				}
			}
			return strRequest;

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "协议异常";
		} catch (ParseException e) {
			e.printStackTrace();
			return "解析异常";
		} catch (IOException e) {
			e.printStackTrace();
			return "传输异常";
		} finally {
			try {
				if (closeableHttpClient != null) {
					closeableHttpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// PXService pxservice = new PXService();
		// TreeMap<String, String> map = new TreeMap<String, String>();
		// map.put("app_id", "TKS_web");
		// map.put("secret_id", "AKIDiWSPobnqU4iCzO4IPyRhBKOODjiHDcGT");
		// map.put("secret_key", "e2i7zcpdmQFCb48F4uSlInpIjZcWYNYc");
		// String url =
		// "http://www.pengxuanyun.com/System/api/router/tks.web_index_cn.query";
		// String strRequest = pxservice.isGet(url, map);
		// //JSONObject data = new JSONObject(strRequest);
		//// JSONArray dataList =
		// data.getJSONObject("site_channel_list").getJSONArray("data");
		////
		// Map data =new JSONObject(strRequest).toMap();
		// Map d = (Map)data.get("site_channel_list");
		// List list = (List)d.get("data");
		// System.out.println(list.size());

		URIBuilder uriBuilder;
		try {
			uriBuilder = new URIBuilder("https://api.aideapi.com/api/router/news.query?app_id=ISSAT_CN_WEB");
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("234", "234"));
			uriBuilder.addParameters(list);
			System.out.println(uriBuilder.build());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		String method = "tks.news_cn.query";
//		method = method.substring(0, method.lastIndexOf(".")) + ".detail";
//		System.out.println(method);
	}

}
