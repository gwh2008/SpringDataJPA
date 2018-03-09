package com.vcyber.user.util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : gaowh
 * @group : vcyber
 * @Date : 2018年2月2日 上午11:32:03
 * @Comments : HTTP请求工具类（包括Get请求和Post请求）
 * @Version : 1.0.0
 */
@SuppressWarnings("deprecation")
public class HttpClientUtils {
	private static final char QP_SEP_A = '&';
	private static final char QP_SEP_B = '?';


	/**
	 * 使用GET方式进行连接
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@SuppressWarnings({ "resource" })
	public static HttpMessageVO get(String url, Map<String, String> params, Map<String, String> headers)
			throws IllegalStateException, IOException {

		HttpMessageVO mvo = new HttpMessageVO();

		HttpClient httpclient = new DefaultHttpClient();
		// 拼接参数
		if (params != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append(params.get(key));
				i++;
			}
			url = url + param.toString();
		}
		HttpGet get = new HttpGet(url);
		if (headers != null) {
			for (String key : headers.keySet()) {
				get.setHeader(key, headers.get(key));
			}
		}
		HttpResponse response = httpclient.execute(get);
		mvo.setCode(response.getStatusLine().getStatusCode() + "");
		mvo.setContent(EntityUtils.toString(response.getEntity(), "UTF-8"));
		return mvo;
	}

	/**
	 * 使用POST方式进行连接
	 * @throws IOException
	 * @throws
	 */
	@SuppressWarnings({ "resource" })
	public static HttpMessageVO post(String url, Map<String, String> params, Map<String, String> headers)
			throws ClientProtocolException, IOException {
		HttpMessageVO mvo = new HttpMessageVO();

		// 创建HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		// 拼接参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if (params != null) {
			for (String key : params.keySet()) {
				NameValuePair pair = new BasicNameValuePair(key, params.get(key));
				list.add(pair);
			}
		}
		HttpPost post = new HttpPost(url);
		if (headers != null) {
			for (String key : headers.keySet()) {
				post.setHeader(key, headers.get(key));
			}
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
		post.setEntity(entity);
		HttpResponse response = httpclient.execute(post);


		mvo.setCode(response.getStatusLine().getStatusCode() + "");
		mvo.setContent(EntityUtils.toString(response.getEntity(), "UTF-8"));
		return mvo;
	}

	/**
	 * 重载方法，http post 调用 直接传json
	 * 
	 * @param uri
	 *            访问地址
	 * @param json
	 *            json格式的参数
	 * @param headers
	 * @return
	 * @throws
	 * @throws IOException
	 */
	public static HttpMessageVO postJson(String uri, String json, Map<String, String> headers)
			throws ClientProtocolException, IOException {
		HttpMessageVO mvo = new HttpMessageVO();
		// 创建HttpClient
		@SuppressWarnings("resource")
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost(uri);
		if (headers != null) {
			for (String key : headers.keySet()) {
				post.setHeader(key, headers.get(key));
			}
		}
		StringEntity entity = new StringEntity(json, "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		post.setEntity(entity);
		HttpResponse response = httpclient.execute(post);
		// StatusLine st = response.getStatusLine();
		// InputStream ins = response.getEntity().getContent();
		// byte[] b = new byte[1024];
		// StringBuilder sb = new StringBuilder();
		// while (ins.read(b) != -1) {
		// sb.append(new String(b, "UTF-8"));
		// }
		// mvo.setCode(st.getStatusCode() + "");
		// mvo.setContent(sb.toString().trim());

		mvo.setCode(response.getStatusLine().getStatusCode() + "");
		mvo.setContent(EntityUtils.toString(response.getEntity(), "UTF-8"));
		return mvo;
	}



}
