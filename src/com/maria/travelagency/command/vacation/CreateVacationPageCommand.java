package com.maria.travelagency.command.vacation;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.entity.City;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.CityServiceImpl;
import com.maria.travelagency.service.impl.VacationServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateVacationPageCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(CreateVacationPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        VacationServiceImpl vacationService = new VacationServiceImpl();
        CityServiceImpl cityService = new CityServiceImpl();
        List<City> cities = null;
        try {
            cities = cityService.findAllCities();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
            request.setAttribute("cities", cities);
            page = ConfigurationManager.getProperty("path.page.admin.create.vacation");
        return page;
    }

}
