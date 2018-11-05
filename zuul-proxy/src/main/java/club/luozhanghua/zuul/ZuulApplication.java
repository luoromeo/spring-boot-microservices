package club.luozhanghua.zuul;

import club.luozhanghua.zuul.common.Constants;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import com.netflix.config.ConfigurationManager;

/**
 * description:
 * author: romeo
 * date: 17/4/10 下午5:23
 */
@SpringCloudApplication
@EnableZuulProxy
public class ZuulApplication {

    private static String appName = null;

    private static Logger LOGGER = LoggerFactory.getLogger(ZuulApplication.class);


    public static void main(String[] args) {
        ConfigurationManager.getConfigInstance().setProperty(Constants.DEPLOYMENT_APPLICATION_ID, "application");
        loadConfiguration();
        SpringApplication.run(ZuulApplication.class, args);
    }

    private static void loadConfiguration() {
        appName = ConfigurationManager.getDeploymentContext().getApplicationId();

        // Loading properties via archaius.
        if (null != appName) {
            try {
                LOGGER.info(String.format("Loading application properties with app id: %s and environment: %s", appName,
                        ConfigurationManager.getDeploymentContext().getDeploymentEnvironment()));
                ConfigurationManager.loadCascadedPropertiesFromResources(appName);
            } catch (IOException e) {
                LOGGER.error(String.format(
                        "Failed to load properties for application id: %s and environment: %s. This is ok, if you do not have application level properties.",
                        appName, ConfigurationManager.getDeploymentContext().getDeploymentEnvironment()), e);
            }
        } else {
            LOGGER.warn(
                    "Application identifier not defined, skipping application level properties loading. You must set a property 'archaius.deployment.applicationId' to be able to load application level properties.");
        }

    }
}
