package com.maria.travelagency.command.trip;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.TripServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTripCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(DeleteTripCommand.class);

    private static final String PARAM_NAME_ID = "id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Long id = Long.parseLong(request.getParameter(PARAM_NAME_ID));
        TripServiceImpl tripService = new TripServiceImpl();
        try {
            tripService.delete(id);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        page = ConfigurationManager.getProperty("path.page.admin.panel");
        return page;
    }

}
