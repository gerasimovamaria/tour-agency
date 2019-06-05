package com.maria.travelagency.command.trip;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.entity.City;
import com.maria.travelagency.entity.Trip;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.CityServiceImpl;
import com.maria.travelagency.service.impl.TripServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditTripPageCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(EditTripPageCommand.class);

    private static final String PARAM_NAME_ID = "id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Long id = Long.parseLong(request.getParameter(PARAM_NAME_ID));
        TripServiceImpl tripService = new TripServiceImpl();
        CityServiceImpl cityService = new CityServiceImpl();
        Trip trip = null;
        List<City> cities = null;
        try {
            trip = tripService.findEntityById(id);
            cities = cityService.findAllCities();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (id == trip.getId()) {
            request.setAttribute("trip", trip);
            request.setAttribute("cities", cities);
            request.setAttribute("cities_size", trip.getCities().size());
            page = ConfigurationManager.getProperty("path.page.admin.edit.info.trip");
        } else {
            page = ConfigurationManager.getProperty("path.page.admin.panel");
        }
        return page;
    }
}
