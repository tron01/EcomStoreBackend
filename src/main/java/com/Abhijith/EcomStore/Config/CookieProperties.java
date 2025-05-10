package com.Abhijith.EcomStore.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cookie")
@Setter
@Getter
public class CookieProperties {
	private boolean secure;
}
