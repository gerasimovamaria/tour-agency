package com.maria.travelagency.command.user;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.entity.UserOrderNumber;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserAdminListCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(UserAdminListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        UserServiceImpl userService = new UserServiceImpl();
        List<UserOrderNumber> users = null;
        try {
            users = userService.findAllUsersWithOrderCount();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute("users", users);
        page = ConfigurationManager.getProperty("path.page.admin.edit.list.user");
        return page;
    }
}
