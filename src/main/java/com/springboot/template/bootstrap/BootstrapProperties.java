package com.springboot.template.bootstrap;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "commandline")
public class BootstrapProperties {

    private final Bootstrap bootstrap = new Bootstrap();

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public static class Bootstrap {

        private boolean userActive = false;

        public boolean isUserActive() {
            return userActive;
        }

        public void setUserActive(boolean userActive) {
            this.userActive = userActive;
        }
    }
}
