package com.ms.workable.flow.modeler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("deprecation")
@Slf4j
public class HttpClientUtil {
	private static final Charset CHAR_SET_UTF_8 = Charset.forName("utf-8");

	@SuppressWarnings("unused")
	public static void main(String[] args) {
//		String url = "http://localhost:8080/flowable-idm/api/idm/tokens/6Kfd%2BAdOXAB7tb5E8%2BwI7w%3D%3D";
//		String username = "admin";
//		String password = "test";
//		callRemoteIdmService(url, username, password);
		
		String urls = "http://localhost:8280/cache/head/hello";

        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建请求方法实例
        HttpGet httpGet = new HttpGet(urls);
        CloseableHttpResponse innerResponse = null;
        HttpEntity entity = null;
        // 发送请求并接收response
        try {
			innerResponse = httpclient.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}
        // response植入cookie
        Header[] ssoResponseHeader = innerResponse.getHeaders("Set-Cookie");
        if (ssoResponseHeader != null && ssoResponseHeader.length != 0) {
            for (Header stepHeader : ssoResponseHeader) {
                if (stepHeader != null) {
                    log.info("name:{},value:{}",stepHeader.getName(), stepHeader.getValue());
                }
            }
        }
	}

	public Object userLogin(HttpServletRequest request, HttpServletResponse response, String email, String password,
            String captcha) {


		//获取sessionId
        String jsessionIdSt = getCookieStringByKey(request, "JSESSIONID");

        if (StringUtils.isEmpty(jsessionIdSt)) {
            return ResultVOUtil.retFailed("登录缓存信息为空");
        }

        if (StringUtils.isNotBlank(jsessionIdSt)) {
            if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password) || StringUtils.isEmpty(captcha)) {
                ResultVOUtil.retFailed("用户名/用户密码/验证码不能为空");
            }

            // 创建默认的httpClient实例.
            CloseableHttpClient httpclient = HttpClients.createDefault();
            // 创建请求方法实例
            HttpPost httpPost = new HttpPost("http://www.test.com/user/login");
            CloseableHttpResponse innerResponse = null;
            HttpEntity entity = null;

            httpPost.addHeader(new BasicHeader("Cookie", "JSESSIONID=" + jsessionIdSt));
            // 创建参数队列
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("email", email));
            formparams.add(new BasicNameValuePair("password", password));


            UrlEncodedFormEntity uefEntity;
            try {
                uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
                httpPost.setEntity(uefEntity);

                // 发送请求并接收response
                innerResponse = httpclient.execute(httpPost);

                //解析response
                entity = innerResponse.getEntity();

                if (entity != null) {

                    // 成功
                    String ssoResultSt = EntityUtils.toString(entity, CHAR_SET_UTF_8);

                    JSONObject ssoResultJson = JSONObject.parseObject(ssoResultSt);

                    String ssoData = ssoResultJson.getString("data");
                    Integer ssoCode = ssoResultJson.getInteger("code");
                    String ssoMsg = ssoResultJson.getString("msg");

                    if (ssoCode == null) {
                        return ResultVOUtil.retFailed("SSO登录返回状态为空");
                    }

                    // 登录成功，返回码为预设的值
                    if (ssoCode.intValue() == 1) {
                        // response植入cookie
                        Header[] ssoResponseHeader = innerResponse.getHeaders("Set-Cookie");

                        if (ssoResponseHeader != null && ssoResponseHeader.length != 0) {
                            for (Header stepHeader : ssoResponseHeader) {
                                if (stepHeader != null) {
                                    response.addHeader(stepHeader.getName(), stepHeader.getValue());
                                }
                            }
                        }
                        return ResultVOUtil.retSuccess(ssoData);
                    }
                    // 登录失败
                    else {
                        return ResultVOUtil.retFailed(ssoMsg);
                    }

                } else {
                    return ResultVOUtil.retFailed("登录端没有响应");
                }

            } catch (ClientProtocolException protocolException) {

                log.error(protocolException.getMessage(), protocolException);

            } catch (UnsupportedEncodingException uException) {

                log.error(uException.getMessage(), uException);

            } catch (IOException ioException) {

                log.error(ioException.getMessage(), ioException);

            } finally {

                // 关闭连接,释放资源
                try {
                    if (innerResponse != null) {
                        innerResponse.close();
                    }
                    httpclient.close();

                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }

            return ResultVOUtil.retFailed("业务异常，导致登录失败");

        } else {
            return ResultVOUtil.retFailed("缓存信息丢失");
        }

    }

	private String getCookieStringByKey(HttpServletRequest request, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static JSONObject postJsonData(String url, Map<String, String> params) {
		CloseableHttpClient httpclient = HttpClientUtil.createDefault();
		HttpPost httpPost = new HttpPost(url);
		// 拼接参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue().toString();
			System.out.println("key=" + key + " value=" + value);
			NameValuePair pair = new BasicNameValuePair(key, value);
			list.add(pair);
		}
		CloseableHttpResponse response = null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(list));
			response = httpclient.execute(httpPost);

			response.getHeaders("Cookie");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* 请求发送成功，并得到响应 **/
		JSONObject jsonObject = null;
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity = response.getEntity();
			String result = null;
			try {
				result = EntityUtils.toString(httpEntity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} // 返回json格式：
			jsonObject = JSONObject.parseObject(result);
		}
		return jsonObject;
	}

	/*
	 * Creates {@link CloseableHttpClient} instance with default configuration.
	 */
	public static CloseableHttpClient createDefault() {
		return HttpClientBuilder.create().build();
	}

	/*
	 * http发送post请求
	 */
	@SuppressWarnings({ "resource" })
	public static JSONObject sendPost(String url, Map<String, String> params) {
		DefaultHttpClient client = new DefaultHttpClient();
		/* NameValuePair是传送给服务器的请求参数 param.get("name") **/
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue().toString();
			System.out.println("key=" + key + " value=" + value);
			NameValuePair pair = new BasicNameValuePair(key, value);
			list.add(pair);
		}
		UrlEncodedFormEntity entity = null;
		try {
			/* 设置编码 **/
			entity = new UrlEncodedFormEntity(list, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		/* 新建一个post请求 **/
		HttpPost post = new HttpPost(url);
		post.setEntity(entity);
		HttpResponse response = null;
		try {
			/* 客服端向服务器发送请求 **/
			response = client.execute(post);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* 请求发送成功，并得到响应 **/
		JSONObject jsonObject = null;
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity httpEntity = response.getEntity();
			String result = null;
			try {
				result = EntityUtils.toString(httpEntity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} // 返回json格式：
			jsonObject = JSONObject.parseObject(result);
		}
		return jsonObject;
	}

	static ObjectMapper objectMapper = new ObjectMapper();

	@SuppressWarnings("resource")
	protected static JsonNode callRemoteIdmService(String url, String username, String password) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + new String(
				Base64.getEncoder().encode((username + ":" + password).getBytes(Charset.forName("UTF-8")))));

		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			sslsf = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
			clientBuilder.setSSLSocketFactory(sslsf);
		} catch (Exception e) {
			log.warn("Could not configure SSL for http client", e);
		}

		CloseableHttpClient client = clientBuilder.build();
		DefaultHttpClient client2 = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(httpGet);

			client2.getCookieStore().getCookies();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return objectMapper.readTree(response.getEntity().getContent());
			}
		} catch (Exception e) {
			log.warn("Exception while getting token", e);
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					log.warn("Exception while closing http client", e);
				}
			}
		}
		return null;
	}
}
