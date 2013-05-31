package org.nextplus.htb2.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.util.Assert;
import org.springframework.web.util.UriUtils;

public class AlipayUtils {

	public static String getRequestUrl(Map<String, String> paramMap) {
		paramMap.put("_input_charset", "UTF-8");
		paramMap.put("partner", Constants.ALIPAY_PID);
		paramMap.put("seller_email", Constants.ALIPAY_EMAIL);

		SortedMap<String, String> nvps = new TreeMap<String, String>();
		for (Entry<String, String> e : paramMap.entrySet()) {
			nvps.put(e.getKey(), e.getValue());
		}
		StringBuilder requestUrlBuilder = new StringBuilder();
		for (Entry<String, String> e : nvps.entrySet()) {
			requestUrlBuilder.append(e.getKey()).append("=")
					.append(e.getValue()).append("&");
		}
		requestUrlBuilder.deleteCharAt(requestUrlBuilder.length() - 1);
		String sign = DigestUtils.md5Hex(requestUrlBuilder.toString() + Constants.ALIPAY_KEY);

		requestUrlBuilder = new StringBuilder();
		for (Entry<String, String> e : nvps.entrySet()) {
			try {
				requestUrlBuilder.append(e.getKey()).append("=")
						.append(UriUtils.encodePathSegment(e.getValue(), "UTF-8")).append("&");
			} catch (UnsupportedEncodingException ex) {
				throw new IllegalStateException(ex);
			}
		}
		requestUrlBuilder.deleteCharAt(requestUrlBuilder.length() - 1);

		requestUrlBuilder.append("&sign_type=MD5&sign=").append(sign);
		requestUrlBuilder.insert(0, "https://mapi.alipay.com/gateway.do?");

		return requestUrlBuilder.toString();
	}

	public static String getVerificationUrl(String notificationId) {
		Assert.hasText(notificationId);
		StringBuilder verificationUrlBuilder = new StringBuilder();
		try {
			verificationUrlBuilder.append("https://www.alipay.com/cooperate/gateway.do?service=notify_verify&partner=")
				.append(Constants.ALIPAY_PID).append("&notify_id=").append(UriUtils.encodePathSegment(notificationId, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return verificationUrlBuilder.toString();
	}

	public static void main(String[] args) throws HttpException, IOException {
//		Map<String, String> paramMap = new HashMap<String, String>();

		// 支付

//		paramMap.put("service", "create_direct_pay_by_user");
//		paramMap.put("paymethod", "bankPay");
//		paramMap.put("notify_url", "http://phantom.grid.nextplus-inc.com:8080/hv2/cs/postTransactionHandler");
//		paramMap.put("return_url", "http://phantom.grid.nextplus-inc.com:8080/hv2/cs/postTransactionHandler");
		// 商品信息
//		paramMap.put("out_trade_no", "11111");
//		paramMap.put("subject", "汉王电子书");
//		paramMap.put("payment_type", "1");
//		paramMap.put("total_fee", "0.01");

		// 验证通知

//		System.out.println(getRequestUrl(paramMap));

		String verificationUrl = getVerificationUrl("RqPnCoPT3K9%2Fvwbh3I7w5vSDDsyxgv5yvnqFyMbcpFZe%2FClUR%2B1fHVrCxosirqL2Z3C9");

		System.out.println(verificationUrl);
		HttpClient httpClient = new HttpClient();
		GetMethod get = new GetMethod(verificationUrl);
		httpClient.executeMethod(get);
		System.out.println(
				get.getStatusLine() + " -> " +
				get.getResponseBodyAsString());
	}

}
