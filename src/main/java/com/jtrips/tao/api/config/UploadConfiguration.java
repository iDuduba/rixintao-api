/**
 * duduba
 * Created at 2016年5月2日 上午10:28:24
 */
package com.jtrips.tao.api.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;

/**
 * @author duduba
 *
 */

@Slf4j
@Configuration
public class UploadConfiguration {
  @Value("${multipart.fileSizeThreshold:-1}")
  private int maxInMemorySize;
   
  @Value("${multipart.maxFileSize:10240}")
  private String uploadMaxFileSize;
   
  @Value("${multipart.location}")
  private String uploadTempDir;

  @Bean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
  public CommonsMultipartResolver multipartResolver() {
      final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
      if (!Strings.isNullOrEmpty(uploadTempDir)) {
          try {
              commonsMultipartResolver.setUploadTempDir(new FileSystemResource(uploadTempDir));
          } catch (IOException e) {
              log.warn("Illegal or not existing folder {} (temporary upload directory)!", uploadTempDir, e);
          }
      }
      commonsMultipartResolver.setMaxUploadSize(parseSize(uploadMaxFileSize));
      commonsMultipartResolver.setMaxInMemorySize(maxInMemorySize);
      commonsMultipartResolver.setDefaultEncoding("utf-8");
      commonsMultipartResolver.setResolveLazily(true);
      return commonsMultipartResolver;
  }
 
  long parseSize(String size) {
      size = size.toUpperCase();
      if (size.endsWith("KB")) {
          return Long.valueOf(size.substring(0, size.length() - 2)) * 1024;
      }
      if (size.endsWith("MB")) {
          return Long.valueOf(size.substring(0, size.length() - 2)) * 1024 * 1024;
      }
      return Long.valueOf(size);
  }
}
