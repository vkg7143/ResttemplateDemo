package com.vcs.restconfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

	@Bean
	public RestTemplate restTemplate() throws Exception {
		SSLContext sslContext = null;
		RestTemplate restTemplate = new RestTemplate();

		try {
			sslContext = SSLContextBuilder.create().loadKeyMaterial(loadKeyStore(), "secret".toCharArray())
					.loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
			HttpClient client = getHTTPClientWithSSL(sslContext);
			restTemplate = new RestTemplateBuilder()
					.requestFactory(() -> new HttpComponentsClientHttpRequestFactory(client)).build();

		} catch (Exception e) {
			System.out.println("exception--->" + e.getMessage());
		}
		return restTemplate;
	}

	private HttpClient getHTTPClientWithSSL(SSLContext sslContext) {

		return HttpClientBuilder.create().setSSLContext(sslContext).build();

	}

	private KeyStore loadKeyStore() throws FileNotFoundException, IOException, NoSuchAlgorithmException,
			CertificateException, KeyStoreException {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		try (InputStream in = new FileInputStream(
				"/Users/vivekkumar/Documents/workspace-spring-tool-suite-4-4.16.0.RELEASE/demo/src/main/resources/identity.jks")) {

			keyStore.load(in, "secret".toCharArray());
		}
		return keyStore;
	}

}