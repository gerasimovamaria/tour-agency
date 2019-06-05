package com.maria.travelagency.command.vacation;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.entity.City;
import com.maria.travelagency.entity.Vacation;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.CityServiceImpl;
import com.maria.travelagency.service.impl.VacationServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditVacationPageCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(EditVacationPageCommand.class);

    private static final String PARAM_NAME_ID = "id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Long id = Long.parseLong(request.getParameter(PARAM_NAME_ID));
        VacationServiceImpl vacationService = new VacationServiceImpl();
        CityServiceImpl cityService = new CityServiceImpl();
        Vacation vacation = null;
        List<City> cities = null;
        try {
            vacation = vacationService.findEntityById(id);
            cities = cityService.findAllCities();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (id == vacation.getId()) {
            request.setAttribute("vacation", vacation);
            request.setAttribute("cities", cities);
            page = ConfigurationManager.getProperty("path.page.admin.edit.info.vacation");
        } else {
            page = ConfigurationManager.getProperty("path.page.admin.panel");
        }
        return page;
    }

}
