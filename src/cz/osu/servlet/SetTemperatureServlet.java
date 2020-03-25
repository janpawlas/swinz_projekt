package cz.osu.servlet;

import cz.osu.services.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SetTemperatureServlet", urlPatterns = "setTemperature")
public class SetTemperatureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomIdString = request.getParameter("roomId");
        String tempString = request.getParameter("wantedTemp");
        int roomId = Integer.parseInt(roomIdString);
        Double temp = Double.parseDouble(tempString);
        RoomService roomService = new RoomService();
        roomService.updateTemp(roomId, temp);
        response.sendRedirect("sensor.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
