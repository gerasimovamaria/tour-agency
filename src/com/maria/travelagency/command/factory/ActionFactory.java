package com.maria.travelagency.command.factory;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.EmptyCommand;
import com.maria.travelagency.command.client.CommandEnum;
import com.maria.travelagency.controller.TravelController;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionFactory {

    private final static Logger LOG = Logger.getLogger(ActionFactory.class);

    private static final String PARAM_NAME_COMMAND = "command";

    public ActionCommand defineCommand(HttpServletRequest request, HttpServletResponse response) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(PARAM_NAME_COMMAND);
        LOG.debug("Action = " + action);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action
                    + TravelController.messageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
