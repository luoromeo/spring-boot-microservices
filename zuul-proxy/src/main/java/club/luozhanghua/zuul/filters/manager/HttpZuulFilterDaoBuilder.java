package club.luozhanghua.zuul.filters.manager;


import club.luozhanghua.zuul.common.Constants;
import club.luozhanghua.zuul.common.IZuulFilterDao;
import club.luozhanghua.zuul.common.IZuulFilterDaoBuilder;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

public class HttpZuulFilterDaoBuilder implements IZuulFilterDaoBuilder {

    private static final DynamicStringProperty appName = DynamicPropertyFactory.getInstance()
            .getStringProperty(Constants.DEPLOYMENT_APPLICATION_ID, Constants.APPLICATION_NAME);

    public HttpZuulFilterDaoBuilder() {

    }

    @Override
    public IZuulFilterDao build() {
        return new HttpZuulFilterDao(appName.get());

    }

}
