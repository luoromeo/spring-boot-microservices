package club.luozhanghua.oauth2.web.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 05:00:20
 * @modified By
 */
public class SOSSiteMeshFilter extends ConfigurableSiteMeshFilter {


    public SOSSiteMeshFilter() {
    }


    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {

        builder.addDecoratorPath("/*", "/WEB-INF/jsp/decorators/main.jsp")

                .addExcludedPath("/static/**");


    }
}
