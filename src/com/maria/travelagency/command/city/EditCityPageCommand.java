package com.maria.travelagency.command.city;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.entity.City;
import com.maria.travelagency.entity.Country;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.CityServiceImpl;
import com.maria.travelagency.service.impl.CountryServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditCityPageCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(EditCityPageCommand.class);

    private static final String PARAM_NAME_ID = "id";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Long id = Long.parseLong(request.getParameter(PARAM_NAME_ID));
        CityServiceImpl cityService = new CityServiceImpl();
        CountryServiceImpl countryService = new CountryServiceImpl();
        City city = null;
        List<Country> countries = null;
        try {
            city = cityService.findEntityById(id);
            countries = countryService.findAllCountries();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (id == city.getId()) {
            request.setAttribute("city", city);
            request.setAttribute("countries", countries);
            page = ConfigurationManager.getProperty("path.page.admin.edit.info.city");
        } else {
            page = ConfigurationManager.getProperty("path.page.admin.panel");
        }
        return page;
    }

}
