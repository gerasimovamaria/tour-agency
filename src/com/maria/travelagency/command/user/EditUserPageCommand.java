package com.maria.travelagency.command.user;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.entity.User;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserPageCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(EditUserPageCommand.class);

    private static final String PARAM_NAME_ID = "id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Long id = Long.parseLong(request.getParameter(PARAM_NAME_ID));
        UserServiceImpl userService = new UserServiceImpl();
        User user = null;
        try {
            user = userService.findEntityById(id);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (id == user.getId()) {
            request.setAttribute("userProfile", user);
            page = ConfigurationManager.getProperty("path.page.admin.edit.info.user");
        } else {
            page = ConfigurationManager.getProperty("path.page.admin.panel");
        }
        return page;
    }

}
