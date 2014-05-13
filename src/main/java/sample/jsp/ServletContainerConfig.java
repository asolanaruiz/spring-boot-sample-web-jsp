package sample.jsp;

import org.apache.catalina.Context;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class ServletContainerConfig {

    @Value("${port:8080}")
    private int port;

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory = new TomcatEmbeddedServletContainerFactory(port) {
            protected void postProcessContext(Context context) {
                context.setSessionCookiePath("/");
                context.setUseHttpOnly(true);
            }
        };
        tomcatEmbeddedServletContainerFactory.setSessionTimeout(15, TimeUnit.MINUTES);
        return tomcatEmbeddedServletContainerFactory;
    }
}
