package com.maria.travelagency.command.tour;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.entity.Shopping;
import com.maria.travelagency.entity.Trip;
import com.maria.travelagency.entity.Vacation;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.ShoppingServiceImpl;
import com.maria.travelagency.service.impl.TripServiceImpl;
import com.maria.travelagency.service.impl.VacationServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class TourListCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(TourListCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Date nowDate = new Date();
        VacationServiceImpl vacationService = new VacationServiceImpl();
        TripServiceImpl tripService = new TripServiceImpl();
        ShoppingServiceImpl shoppingService = new ShoppingServiceImpl();

        try {
        List<Vacation> vacations = vacationService.selectLastVacations(new java.sql.Date(nowDate.getTime()));
        request.setAttribute("vacations", vacations);

        List<Trip> trips = tripService.selectLastTrips(new java.sql.Date(nowDate.getTime()));
        request.setAttribute("trips", trips);

        List<Shopping> shoppings = shoppingService.selectLastShoppings(new java.sql.Date(nowDate.getTime()));
        request.setAttribute("shoppings", shoppings);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
