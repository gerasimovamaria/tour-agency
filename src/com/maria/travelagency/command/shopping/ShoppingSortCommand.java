package com.maria.travelagency.command.shopping;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.entity.Shopping;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.ShoppingServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class ShoppingSortCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(ShoppingSortCommand.class);

    private static final String PARAM_NAME_CRITERION = "criterion";
    
    private static final String PARAM_NAME_ORDER = "order";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        String criterion = request.getParameter(PARAM_NAME_CRITERION);
        boolean order = Boolean.parseBoolean(request.getParameter(PARAM_NAME_ORDER));
        Date nowDate = new Date();
        ShoppingServiceImpl shoppingService = new ShoppingServiceImpl();
        List<Shopping> shoppings = null;
        try {
            shoppings = shoppingService.findAllSortShoppingsAfterNow(new java.sql.Date(nowDate.getTime()), criterion, order);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute("sortCriterion", criterion);
        request.setAttribute("sortOrder", order);
        request.setAttribute("shoppings", shoppings);
        page = ConfigurationManager.getProperty("path.page.shopping.list");
        return page;
    }
}
