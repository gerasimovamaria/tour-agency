package com.maria.travelagency.command.city;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.CityServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCityCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(DeleteCityCommand.class);

    private static final String PARAM_NAME_ID = "id";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Long id = Long.parseLong(request.getParameter(PARAM_NAME_ID));
        CityServiceImpl cityService = new CityServiceImpl();
        try {
            cityService.delete(id);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        page = ConfigurationManager.getProperty("path.page.admin.panel");
        return page;
    }

}
