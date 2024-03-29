package com.maria.travelagency.command.user;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.controller.TravelController;
import com.maria.travelagency.entity.User;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.net.URL;

public class BalanceAddCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(BalanceAddCommand.class);

    private static final String PARAM_NAME_MONEY = "money";
    
    private static final String PARAM_NAME_ID_USER = "iduser";

    private static final String PARAM_HEADER_REFERER = "referer";

    private static final String PARAM_NAME_SERVLET = "travel?";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(PARAM_NAME_ID_USER);
        double moneyToAdd = Double.parseDouble(request.getParameter(PARAM_NAME_MONEY));
        UserServiceImpl userService = new UserServiceImpl();
        try {
            if (userService.checkBalanceToAdd(userId, moneyToAdd)) {
                User user = userService.findEntityById(userId);
                request.setAttribute("userProfile", user);
                URL url = new URL(request.getHeader(PARAM_HEADER_REFERER));
                request.setAttribute("redirect", PARAM_NAME_SERVLET + url.getQuery());
                page = ConfigurationManager.getProperty("path.page.balance");
            } else {
                User user = userService.findEntityById(userId);
                request.setAttribute("userProfile", user);
                URL url = new URL(request.getHeader(PARAM_HEADER_REFERER));
                request.setAttribute("redirect", PARAM_NAME_SERVLET + url.getQuery());
                request.setAttribute("errorBalanceAddMessage",
                        TravelController.messageManager.getProperty("message.balanceadderror"));
                page = ConfigurationManager.getProperty("path.page.balance");
            }
        } catch (MalformedURLException | ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }

}
