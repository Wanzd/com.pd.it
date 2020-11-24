package com.pd.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;

public class WebUtil {
	public static String get(String urlStr, String encode) {
		HttpClient instance = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
		HttpResponse response;
		try {
			response = instance.execute(new HttpGet(urlStr));
			String html = EntityUtils.toString(response.getEntity(), encode);
			return html;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String post(String urlStr, String data, String encode) {
		// 装饰器 缓存读取
		BufferedReader bReader = null;
		// 输入流
		InputStreamReader myReader = null;
		StringBuffer sbuf = new StringBuffer();
		// URL对象
		URL url;
		try {
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
			url = new URL(urlStr);
			myReader = new InputStreamReader(url.openStream(), encode);
			bReader = new BufferedReader(myReader);
			String s = null;
			while ((s = bReader.readLine()) != null) {
				sbuf.append(s);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bReader != null) {
					bReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (myReader != null) {
					myReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sbuf.toString();
	}

	public static String post(String urlStr, String data) {
		return post(urlStr, data, "utf-8");
	}
}
