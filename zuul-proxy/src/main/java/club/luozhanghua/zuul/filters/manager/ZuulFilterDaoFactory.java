package club.luozhanghua.zuul.filters.manager;


import club.luozhanghua.zuul.common.Constants;
import club.luozhanghua.zuul.common.IZuulFilterDao;

import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

public class ZuulFilterDaoFactory {
    private static final DynamicStringProperty daoType = DynamicPropertyFactory.getInstance().getStringProperty(Constants.ZUUL_FILTER_DAO_TYPE, "jdbc");

    private static ConcurrentMap<String, IZuulFilterDao> daoCache = Maps.newConcurrentMap();

    private ZuulFilterDaoFactory() {

    }

    public static IZuulFilterDao getZuulFilterDao() {
        IZuulFilterDao dao = daoCache.get(daoType.get());

        if (dao != null) {
            return dao;
        }

        if ("jdbc".equalsIgnoreCase(daoType.get())) {
            dao = new JDBCZuulFilterDaoBuilder().build();
        } else if ("http".equalsIgnoreCase(daoType.get())) {
            dao = new HttpZuulFilterDaoBuilder().build();
        } else {
            dao = new JDBCZuulFilterDaoBuilder().build();
        }

        daoCache.putIfAbsent(daoType.get(), dao);

        return dao;
    }

    public static String getCurrentType() {
        return daoType.get();
    }

}
