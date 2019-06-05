package com.maria.travelagency.command.city;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.entity.City;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.CityServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CityDeleteAdminListCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(CityDeleteAdminListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        CityServiceImpl cityService = new CityServiceImpl();
        List<City> cities = null;
        try {
            cities = cityService.findAllCities();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute("cities", cities);
        page = ConfigurationManager.getProperty("path.page.admin.delete.list.city");
        return page;
    }
}
