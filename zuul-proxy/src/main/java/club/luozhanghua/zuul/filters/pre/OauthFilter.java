package club.luozhanghua.zuul.filters.pre;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @description 鉴权拦截器
 * @author zhanghua.luo
 * @date 2018年10月29日 23:25
 * @modified By
 */
public class OauthFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //获取Request
        HttpServletRequest request = ctx.getRequest();
        //从请求头中获取token
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            //不会继续执行，直接返回错误信息给前端
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("token is null");
            ctx.setResponseStatusCode(401);
            return null;
        }

        return null;
    }
}
