package com.maria.travelagency.command.country;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.entity.Country;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.CountryServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCountryPageCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(EditCountryPageCommand.class);

    private static final String PARAM_NAME_ID = "id";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Long id = Long.parseLong(request.getParameter(PARAM_NAME_ID));
        CountryServiceImpl countryService = new CountryServiceImpl();
        Country country = null;
        try {
            country = countryService.findEntityById(id);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (id == country.getId()) {
            request.setAttribute("country", country);
            page = ConfigurationManager.getProperty("path.page.admin.edit.info.country");
        } else {
            page = ConfigurationManager.getProperty("path.page.admin.panel");
        }
        return page;
    }

}
