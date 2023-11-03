package br.com.rest.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig { //Config for HttpMultiPartFiles

	private String uploadDir; //for yml attribute upload-dir / uploaddir / upload_dir thats matches attribute in yml

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	
}
