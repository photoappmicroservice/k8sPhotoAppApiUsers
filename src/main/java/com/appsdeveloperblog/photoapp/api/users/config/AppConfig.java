package com.appsdeveloperblog.photoapp.api.users.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private String welcomemessage; 
    private String message;
    private String gatewayip;
    private String tokenexpirationtime;
    private String loginurlpath;
    private String tokensecret;
    
	public String getWelcomemessage() {
		return welcomemessage;
	}
	public void setWelcomemessage(String welcomemessage) {
		this.welcomemessage = welcomemessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getGatewayip() {
		return gatewayip;
	}
	public void setGatewayip(String gatewayip) {
		this.gatewayip = gatewayip;
	}
	public String getTokenexpirationtime() {
		return tokenexpirationtime;
	}
	public void setTokenexpirationtime(String tokenexpirationtime) {
		this.tokenexpirationtime = tokenexpirationtime;
	}
	public String getLoginurlpath() {
		return loginurlpath;
	}
	public void setLoginurlpath(String loginurlpath) {
		this.loginurlpath = loginurlpath;
	}
	public String getTokensecret() {
		return tokensecret;
	}
	public void setTokensecret(String tokensecret) {
		this.tokensecret = tokensecret;
	}

	
}
