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

public class TripDeleteAdminListCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(TripDeleteAdminListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Date nowDate = new Date();
        TripServiceImpl tripService = new TripServiceImpl();
        List<Trip> trips = null;
        try {
            trips = tripService.findAllTripsAfterNow(new java.sql.Date(nowDate.getTime()));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute("trips", trips);
        page = ConfigurationManager.getProperty("path.page.admin.delete.list.trip");
        return page;
    }
}
