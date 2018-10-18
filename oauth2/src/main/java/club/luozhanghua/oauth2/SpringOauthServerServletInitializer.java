package club.luozhanghua.oauth2;

import club.luozhanghua.oauth2.web.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 16:35
 * @modified By
 */
public class SpringOauthServerServletInitializer extends SpringBootServletInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        //主版本号
        servletContext.setAttribute("mainVersion", WebUtils.VERSION);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Oauth2Application.class);
    }

}
