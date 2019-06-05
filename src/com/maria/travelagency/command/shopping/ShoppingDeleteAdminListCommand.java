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

public class ShoppingDeleteAdminListCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(ShoppingDeleteAdminListCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Date nowDate = new Date();
        ShoppingServiceImpl shoppingService = new ShoppingServiceImpl();
        List<Shopping> shoppings = null;
        try {
            shoppings = shoppingService.findAllShoppingsAfterNow(new java.sql.Date(nowDate.getTime()));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute("shoppings", shoppings);
        page = ConfigurationManager.getProperty("path.page.admin.delete.list.shopping");
        return page;
    }
}
