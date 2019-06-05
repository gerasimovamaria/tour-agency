package com.maria.travelagency.command;

import com.maria.travelagency.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionCommand {

    String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
