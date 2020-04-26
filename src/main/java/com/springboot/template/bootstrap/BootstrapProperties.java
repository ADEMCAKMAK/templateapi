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

        private boolean authorityActive = false;
        private boolean roleActive = false;
        private boolean userActive = false;

        public boolean isUserActive() {
            return userActive;
        }

        public void setUserActive(boolean userActive) {
            this.userActive = userActive;
        }

        public boolean isAuthorityActive() {
            return authorityActive;
        }

        public void setAuthorityActive(boolean authorityActive) {
            this.authorityActive = authorityActive;
        }

        public boolean isRoleActive() {
            return roleActive;
        }

        public void setRoleActive(boolean roleActive) {
            this.roleActive = roleActive;
        }
    }
}
