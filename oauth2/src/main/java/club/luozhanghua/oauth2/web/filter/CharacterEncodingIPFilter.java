package club.luozhanghua.oauth2.web.filter;

import club.luozhanghua.oauth2.web.WebUtils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CharacterEncodingFilter;


/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 05:00:02
 * @modified By
 */
public class CharacterEncodingIPFilter extends CharacterEncodingFilter {

    private static final Logger LOG = LoggerFactory.getLogger(CharacterEncodingIPFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        recordIP(request);
        super.doFilterInternal(request, response, filterChain);
    }

    private void recordIP(HttpServletRequest request) {
        final String ip = WebUtils.retrieveClientIp(request);
        WebUtils.setIp(ip);
        LOG.debug("Send request uri: {}, from IP: {}", request.getRequestURI(), ip);
    }
}
