package com.maria.travelagency.command.country;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.controller.TravelController;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.CountryServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCountryCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(EditCountryCommand.class);

    private static final String PARAM_NAME_ID = "id";
    
    private static final String PARAM_NAME_NAME = "name";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        String id = request.getParameter(PARAM_NAME_ID);
        String name = request.getParameter(PARAM_NAME_NAME);
        CountryServiceImpl countryService = new CountryServiceImpl();

        try {
            if (countryService.checkEditCountry(id,name)) {
                page = ConfigurationManager.getProperty("path.page.admin.panel");
            }
            else {
                request.setAttribute("errorEditCountryPassMessage",
                        TravelController.messageManager.getProperty("message.editcountryerror"));
                page = ConfigurationManager.getProperty("path.page.admin.edit.info.country");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }

}
