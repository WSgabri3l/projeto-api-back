//Ambiente de Configuração da Propriedade de Arquivos

package br.com.havan;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="file") //Todas as propriedades que comecem com "file" terão esse esquema
public class FileStorageProperties {
    
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    
}
