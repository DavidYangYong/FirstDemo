// package com.fl.testpi;
//
// import java.io.IOException;
//
// import org.apache.http.HttpEntity;
// import org.apache.http.client.config.RequestConfig;
// import org.apache.http.client.methods.CloseableHttpResponse;
// import org.apache.http.client.methods.HttpPost;
// import org.apache.http.entity.StringEntity;
// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.client.HttpClients;
// import org.apache.http.util.EntityUtils;
//
// public class Test {
//
// public static void main(String[] args) {
// String url =
// "http://piqas:8000/sap/xi/adapter_plain?namespace=urn%3Aeam%3Ainterface%3Azeamfm025&interface=MI_ZEAMFM025_OUT&service=BC_EAM&party=&agency=&scheme=&QOS=BE&sap-user=ema_01&sap-password=654321&sap-client=721&sap-language=ZH";
// /*
// * String data = "<?xml version='1.0' encoding='UTF-8'" + "?>";
// * data +=
// * "<ns0:ZEAMFM023 xmlns:ns0='urn:sap-com:document:sap:rfc:functions'>";
// * data += "<TPRA></TPRA>";
// * data += "</ns0:ZEAMFM023>";
// */
//
// String data = "<?xml version='1.0' encoding='UTF-8'" + "?>";
// data += "<ns0:ZEAMFM025 xmlns:ns0='urn:sap-com:document:sap:rfc:functions'>";
// data += "<ERDATE>20161018</ERDATE>";
// data += "<ERDATS>20161018</ERDATS>";
// data += "<KUNNR>0000300004</KUNNR>";
// data += "<VKORG>8300</VKORG>";
// data += "</ns0:ZEAMFM025>";
// System.out.println(data);
// CloseableHttpClient httpclient = getHttpClient();
// HttpPost post = new HttpPost(url);
// String info = null;
// CloseableHttpResponse response = null;
// try {
//
// StringEntity entityString = new StringEntity(data);
//
// post.setEntity(entityString);
// response = httpclient.execute(post);
// HttpEntity entity = response.getEntity();
// String content = EntityUtils.toString(entity);
// System.out.println("content:" + content);
// } catch (Exception ex) {
// ex.printStackTrace();
// } finally {
// post.releaseConnection();
// }
// }
//
// private static CloseableHttpClient getHttpClient() {
// RequestConfig defaultRequestConfig = RequestConfig.custom()
// .setSocketTimeout(500000).setConnectTimeout(500000)
// .setConnectionRequestTimeout(500000)
// .setStaleConnectionCheckEnabled(true).build();
// CloseableHttpClient httpclient = HttpClients.custom()
// .setDefaultRequestConfig(defaultRequestConfig).build();
// return httpclient;
// }
//
// private static void closeHttpClient(CloseableHttpClient client)
// throws IOException {
// if (client != null) {
// client.close();
// }
// }
// }
