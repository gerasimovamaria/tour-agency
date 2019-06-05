package com.maria.travelagency.command.trip;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.entity.Trip;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.TripServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class TripSortCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(TripSortCommand.class);

    private static final String PARAM_NAME_CRITERION = "criterion";
    
    private static final String PARAM_NAME_ORDER = "order";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        String criterion = request.getParameter(PARAM_NAME_CRITERION);
        boolean order = Boolean.parseBoolean(request.getParameter(PARAM_NAME_ORDER));
        Date nowDate = new Date();
        TripServiceImpl tripService = new TripServiceImpl();
        List<Trip> trips = null;
        try {
            trips = tripService.findAllSortTripsAfterNow(new java.sql.Date(nowDate.getTime()), criterion, order);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute("sortCriterion", criterion);
        request.setAttribute("sortOrder", order);
        request.setAttribute("trips", trips);
        page = ConfigurationManager.getProperty("path.page.trip.list");
        return page;
    }
}
