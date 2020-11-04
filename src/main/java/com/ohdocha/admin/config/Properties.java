package com.ohdocha.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "com.ohdocha.admin")
public class Properties {

    private String serverHost;
    private String serverName;
    private String serverVersion;

    private long uploadImageSize;
    private List<String> supportImageExtension;
    private String tempFolderPath;

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public List<String> getSupportImageExtension() {
        return supportImageExtension;
    }

    public void setSupportImageExtension(List<String> supportImageExtension) {
        this.supportImageExtension = supportImageExtension;
    }

    public long getUploadImageSize() {
        return uploadImageSize;
    }

    public void setUploadImageSize(long uploadImageSize) {
        this.uploadImageSize = uploadImageSize;
    }

    public String getTempFolderPath() {
        return tempFolderPath;
    }

    public void setTempFolderPath(String tempFolderPath) {
        this.tempFolderPath = tempFolderPath;
    }

}
