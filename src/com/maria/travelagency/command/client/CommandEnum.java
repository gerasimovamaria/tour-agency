package com.maria.travelagency.command.client;

import com.maria.travelagency.command.ActionCommand;
import com.maria.travelagency.command.ForwardCommand;
import com.maria.travelagency.command.city.*;
import com.maria.travelagency.command.country.*;
import com.maria.travelagency.command.order.DeleteOrderCommand;
import com.maria.travelagency.command.order.OrderOldListCommand;
import com.maria.travelagency.command.order.OrderUpcomingListCommand;
import com.maria.travelagency.command.shopping.*;
import com.maria.travelagency.command.tour.SearchListCommand;
import com.maria.travelagency.command.tour.TourListCommand;
import com.maria.travelagency.command.trip.*;
import com.maria.travelagency.command.user.*;
import com.maria.travelagency.command.vacation.*;

public enum CommandEnum {

    LOGIN(new LoginCommand()),

    LOGOUT(new LogoutCommand()),

    REGISTER(new RegisterCommand()),

    ENGLISH_LANGUAGE(new EnglishLanguageCommand()),

    RUSSIAN_LANGUAGE(new RussianLanguageCommand()),

    VACATION_LIST(new VacationListCommand()),

    VACATION_FULL(new VacationFullCommand()),

    TRIP_LIST(new TripListCommand()),

    TRIP_FULL(new TripFullCommand()),

    SHOPPING_LIST(new ShoppingListCommand()),

    SHOPPING_FULL(new ShoppingFullCommand()),

    TOUR_LIST(new TourListCommand()),

    CREATE_VACATION(new CreateVacationCommand()),

    CREATE_VACATION_PAGE(new CreateVacationPageCommand()),

    CREATE_TRIP(new CreateTripCommand()),

    CREATE_TRIP_PAGE(new CreateTripPageCommand()),

    CREATE_SHOPPING(new CreateShoppingCommand()),

    CREATE_SHOPPING_PAGE(new CreateShoppingPageCommand()),

    CREATE_COUNTRY(new CreateCountryCommand()),

    CREATE_CITY(new CreateCityCommand()),

    CREATE_CITY_PAGE(new CreateCityPageCommand()),

    VACATION_ADMIN_LIST(new VacationAdminListCommand()),

    TRIP_ADMIN_LIST(new TripAdminListCommand()),

    SHOPPING_ADMIN_LIST(new ShoppingAdminListCommand()),

    COUNTRY_ADMIN_LIST(new CountryAdminListCommand()),

    CITY_ADMIN_LIST(new CityAdminListCommand()),

    EDIT_VACATION_PAGE(new EditVacationPageCommand()),

    EDIT_VACATION(new EditVacationCommand()),

    EDIT_TRIP_PAGE(new EditTripPageCommand()),

    EDIT_TRIP(new EditTripCommand()),

    EDIT_SHOPPING_PAGE(new EditShoppingPageCommand()),

    EDIT_SHOPPING(new EditShoppingCommand()),

    EDIT_COUNTRY(new EditCountryCommand()),

    EDIT_COUNTRY_PAGE(new EditCountryPageCommand()),

    EDIT_CITY(new EditCityCommand()),

    EDIT_CITY_PAGE(new EditCityPageCommand()),

    VACATION_DELETE_ADMIN_LIST(new VacationDeleteAdminListCommand()),

    TRIP_DELETE_ADMIN_LIST(new TripDeleteAdminListCommand()),

    SHOPPING_DELETE_ADMIN_LIST(new ShoppingDeleteAdminListCommand()),

    COUNTRY_DELETE_ADMIN_LIST(new CountryDeleteAdminListCommand()),

    CITY_DELETE_ADMIN_LIST(new CityDeleteAdminListCommand()),

    DELETE_VACATION(new DeleteVacationCommand()),

    DELETE_TRIP(new DeleteTripCommand()),

    DELETE_SHOPPING(new DeleteShoppingCommand()),

    DELETE_COUNTRY(new DeleteCountryCommand()),

    DELETE_CITY(new DeleteCityCommand()),

    ORDER_VACATION(new OrderVacationCommand()),

    ORDER_TRIP(new OrderTripCommand()),

    ORDER_SHOPPING(new OrderShoppingCommand()),

    ORDER_LIST_UPCOMING(new OrderUpcomingListCommand()),

    ORDER_LIST_OLD(new OrderOldListCommand()),

    DELETE_ORDER(new DeleteOrderCommand()),

    BALANCE(new BalancePageCommand()),

    BALANCE_ADD(new BalanceAddCommand()),

    CHANGE_PASSWORD(new ChangePasswordCommand()),

    CREATE_USER(new CreateUserCommand()),

    USER_ADMIN_LIST(new UserAdminListCommand()),

    EDIT_USER_PAGE(new EditUserPageCommand()),

    EDIT_USER(new EditUserCommand()),

    USER_DELETE_ADMIN_LIST(new UserDeleteAdminListCommand()),

    DELETE_USER(new DeleteUserCommand()),

    SEARCH(new SearchListCommand()),

    VACATION_SORT(new VacationSortCommand()),

    TRIP_SORT(new TripSortCommand()),

    SHOPPING_SORT(new ShoppingSortCommand()),

    FORWARD(new ForwardCommand());

    ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }


    public ActionCommand getCurrentCommand() {
        return command;
    }
}
