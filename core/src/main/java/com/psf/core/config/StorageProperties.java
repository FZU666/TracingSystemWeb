package com.psf.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author psf
 */
@ConfigurationProperties("storage")
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	@Value("${storage.location.images}")
	private String imageLocation;
//	private String location = "classpath:/static/images/avatar";

	@Value("${storage.location.files}")
	private String fileLocation;

	public String getCurrentPath() {
		return System.getProperty("user.dir");
	}

	public String getImageLocation() {
		return getCurrentPath() + imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public String getFileLocation() {
		return getCurrentPath() + fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
}
