package com.sparrow.controller;

import com.sparrow.constant.SparrowError;
import com.sparrow.constant.User;
import com.sparrow.exception.CacheNotFoundException;
import com.sparrow.mvc.RequestParameters;
import com.sparrow.protocol.AuthorizingSupport;
import com.sparrow.protocol.BusinessException;
import com.sparrow.protocol.ClientInformation;
import com.sparrow.protocol.LoginToken;
import com.sparrow.servlet.ServletContainer;
import com.sparrow.vo.HelloVO;
import com.sparrow.mvc.ViewWithModel;

/**
 * @author harry
 */
public class HelloController {

    private AuthorizingSupport authorizingSupport;

    private ServletContainer servletContainer;

    public void setAuthorizingSupport(AuthorizingSupport authorizingSupport) {
        this.authorizingSupport = authorizingSupport;
    }

    public void setServletContainer(ServletContainer servletContainer) {
        this.servletContainer = servletContainer;
    }

    public ViewWithModel hello() throws BusinessException {
        return ViewWithModel.forward("hello", new HelloVO("我来自遥远的sparrow 星球,累死我了..."));
    }

    public ViewWithModel exception() throws BusinessException {
        throw new BusinessException(SparrowError.SYSTEM_SERVER_ERROR);
    }

    public ViewWithModel fly() throws BusinessException {
        return ViewWithModel.redirect("fly-here", new HelloVO("我是从fly.jsp飞过来，你是不是没感觉？"));
    }

    public ViewWithModel transit() throws BusinessException {
        return ViewWithModel.transit(new HelloVO("我从你这歇一会，最终我要到transit-here"));
    }

    @RequestParameters("threadId,pageIndex")
    public ViewWithModel thread(Long threadId, Integer pageIndex) {
        return ViewWithModel.forward("thread", new HelloVO("服务器的threadId=" + threadId + ",pageIndex=" + pageIndex));
    }

    public HelloVO json() {
        return new HelloVO("够意思吧，json不用页面");
    }

    public ViewWithModel welcome() {
        return ViewWithModel.forward(new HelloVO("jsp page content from server ..."));
    }

    public ViewWithModel login() throws BusinessException, CacheNotFoundException {
        LoginToken loginToken = new LoginToken();
        loginToken.setNickName("nick-zhangsan");
        loginToken.setAvatar("http://localhost");
        loginToken.setDeviceId("0");
        loginToken.setCent(100L);
        loginToken.setExpireAt(System.currentTimeMillis() + 1000*60*60);
        loginToken.setDays(20);
        loginToken.setUserId(1L);
        loginToken.setUserName("zhangsan");
        loginToken.setActivate(true);
        String sign= authorizingSupport.sign(loginToken,"111111");
        servletContainer.rootCookie(User.PERMISSION,sign, 6);
        return ViewWithModel.redirect("welcome", loginToken);
    }

    public ViewWithModel authorizing() {
        return ViewWithModel.forward(new HelloVO("你成功了"));
    }
}
