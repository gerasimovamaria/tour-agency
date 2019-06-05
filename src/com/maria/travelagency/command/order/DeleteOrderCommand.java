package com.maria.travelagency.command.order;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.exception.CommandException;
import com.maria.travelagency.controller.TravelController;
import com.maria.travelagency.entity.OrderTourInfo;
import com.maria.travelagency.resource.ConfigurationManager;
import com.maria.travelagency.service.exception.ServiceException;
import com.maria.travelagency.service.impl.OrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public class DeleteOrderCommand implements ActionCommand {

    private final static Logger LOG = Logger.getLogger(DeleteOrderCommand.class);

    private static final String PARAM_NAME_ID = "id";
    
    private static final String PARAM_NAME_ID_USER = "iduser";

    private static final String ATTR_NAME_ORDERS = "orders";

    private static final String ATTR_NAME_TIME = "time";

    private static final String ATTR_NAME_UPCOMING = "upcoming";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = null;
        Date nowDate = new Date();
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(PARAM_NAME_ID_USER);
        Long orderId = Long.parseLong(request.getParameter(PARAM_NAME_ID));
        OrderServiceImpl orderService = new OrderServiceImpl();
        try {
            if (orderService.checkOrderDelete(orderId, userId)) {
                List<OrderTourInfo> orderTourInfos = orderService.findAllUserOrdersByUserIdAfterNow(userId, new java.sql.Date(nowDate.getTime()));
                request.setAttribute(ATTR_NAME_ORDERS, orderTourInfos);
                request.setAttribute(ATTR_NAME_TIME, ATTR_NAME_UPCOMING);
                page = ConfigurationManager.getProperty("path.page.orders");
            } else {
                List<OrderTourInfo> orderTourInfos = orderService.findAllUserOrdersByUserIdAfterNow(userId, new java.sql.Date(nowDate.getTime()));
                request.setAttribute(ATTR_NAME_ORDERS, orderTourInfos);
                request.setAttribute(ATTR_NAME_TIME, ATTR_NAME_UPCOMING);
                request.setAttribute("errorOrderDeleteMessage",
                        TravelController.messageManager.getProperty("message.ordercancelerror"));
                page = ConfigurationManager.getProperty("path.page.orders");
            }
        } catch (ServiceException e){
            throw new CommandException(e);
        }

        return page;
    }

}
