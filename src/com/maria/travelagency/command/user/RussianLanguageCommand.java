package com.maria.travelagency.command.user;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.controller.TravelController;
import com.maria.travelagency.resource.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class RussianLanguageCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(RussianLanguageCommand.class);

    private final static String COOKIE_NAME = "locale";
    
    private final static String COOKIE_VALUE = "ru_RU";
    
    private final static String LOCALE_VALUE = "ru";
    
    private final static int COOKIE_AGE_IN_SEC = 86_400;

    private static final String PARAM_HEADER_REFERER = "referer";

    private static final String PARAM_NAME_SERVLET = "travel?";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Locale rus = new Locale(LOCALE_VALUE, LOCALE_VALUE.toUpperCase());
        Locale.setDefault(rus);
        TravelController.messageManager.changeResource(rus);
        TravelController.resourceManager.changeResource(rus);
        HttpSession session = request.getSession(true);
        Cookie cookie = new Cookie(COOKIE_NAME, COOKIE_VALUE);
        cookie.setMaxAge(COOKIE_AGE_IN_SEC);
        response.addCookie(cookie);
        request.setAttribute("lang", COOKIE_VALUE);
        session.setAttribute("lang", COOKIE_VALUE);
        try {
            URL url = new URL(request.getHeader(PARAM_HEADER_REFERER));
            request.setAttribute("redirect", PARAM_NAME_SERVLET + url.getQuery());
        } catch (MalformedURLException e) {
            throw new CommandException("URL malformed.");
        }
        if (session.getAttribute("user") == null) {
            page = ConfigurationManager.getProperty("path.page.login");
        }
        else {
            page = ConfigurationManager.getProperty("path.page.main");
        }
        return page;
    }
}
