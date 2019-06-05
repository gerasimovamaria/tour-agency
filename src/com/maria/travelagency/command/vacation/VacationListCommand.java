package com.maria.travelagency.command.vacation;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.entity.Vacation;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.VacationServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class VacationListCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(VacationListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Date nowDate = new Date();
        VacationServiceImpl vacationService = new VacationServiceImpl();
        List<Vacation> vacations = null;
        try {
            vacations = vacationService.findAllVacationsAfterNow(new java.sql.Date(nowDate.getTime()));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute("vacations", vacations);
        page = ConfigurationManager.getProperty("path.page.vacation.list");
        return page;
    }
}
