package com.maria.travelagency.command.trip;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.entity.Trip;
import com.maria.travelagency.entity.User;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.TripServiceImpl;
import com.maria.travelagency.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TripFullCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(TripFullCommand.class);

    private static final String PARAM_NAME_ID = "id";
    
    private static final String PARAM_NAME_ID_USER = "iduser";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Long id = Long.parseLong(request.getParameter(PARAM_NAME_ID));
        Long iduser = (Long) request.getSession().getAttribute(PARAM_NAME_ID_USER);
        TripServiceImpl tripService = new TripServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        Trip trip = null;
        try {
            trip = tripService.findEntityById(id);
            if (id == trip.getId()) {
                request.setAttribute("trip", trip);
                request.setAttribute("lastCity", trip.getCities().size()-1);
                if (iduser != null) {
                    User user = userService.findEntityById(iduser);
                    request.setAttribute("userProfile", user);
                }
                page = ConfigurationManager.getProperty("path.page.trip.full");
            } else {
                page = ConfigurationManager.getProperty("path.page.main");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
