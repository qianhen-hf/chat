package com.fan.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ObjectMapperConfig {
	@Bean
	public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
		final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper());
		return converter;
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper ob = new ObjectMapper();
		ob.setSerializationInclusion(Include.NON_NULL);
		ob.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS,true);
		return ob;
	}


//	@Configuration
//	public class CorsConfig {
//		private CorsConfiguration buildConfig() {
//			CorsConfiguration corsConfiguration = new CorsConfiguration();
//			corsConfiguration.addAllowedOrigin("*"); // 1
//			corsConfiguration.addAllowedHeader("*"); // 2
//			corsConfiguration.addAllowedMethod("*"); // 3
//			return corsConfiguration;
//		}
//
//		@Bean
//		public CorsFilter corsFilter() {
//			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//			source.registerCorsConfiguration("/**", buildConfig()); // 4
//			return new CorsFilter(source);
//		}
//	}
}
