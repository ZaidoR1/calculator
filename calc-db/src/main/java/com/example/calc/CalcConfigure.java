package com.example.calc;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
public class CalcConfigure {


  @Value("${server.port}")
  private int serverPort;

  @Value("${server.http.port}")
  private int serverHttpPort;

  /*
   * Erreicht:
   * - Requests via https auf Port 9002 klappen, sowohl POST als auch GET
   * - GET-Requests via http auf Port 9001 werden weitergeleitet auf https/9002.
   * - POST-Requests werden nicht vollständig weitergeleitet, der Body fehlt. Fehlermeldung:
   * "status": 400,
   * "error": "Bad Request",
   * "exception": "org.springframework.http.converter.HttpMessageNotReadableException",
   * "message":
   * "Required request body is missing: public org.springframework.http.ResponseEntity<?> com.example.calc.CalcController.add(com.example.calc.CalcEntry)"
   * 
   */


  @Bean
  public EmbeddedServletContainerFactory servletContainer() {
    TomcatEmbeddedServletContainerFactory tomcat =
        new TomcatEmbeddedServletContainerFactory() {

          @Override
          protected void postProcessContext(Context context) {
            SecurityConstraint securityConstraint = new SecurityConstraint();
            securityConstraint.setUserConstraint("CONFIDENTIAL");
            SecurityCollection collection = new SecurityCollection();
            collection.addPattern("/*");
            securityConstraint.addCollection(collection);
            context.addConstraint(securityConstraint);
          }
        };
    tomcat.addAdditionalTomcatConnectors(initHttpConnector());
    return tomcat;
  }

  // Redirect from http to https
  private Connector initHttpConnector() {
    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    connector.setScheme("http");
    connector.setPort(serverHttpPort);
    connector.setRedirectPort(serverPort);
    connector.setSecure(false);
    return connector;
  }


}
