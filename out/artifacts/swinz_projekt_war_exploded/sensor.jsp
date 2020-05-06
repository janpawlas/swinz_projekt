<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.02.2020
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="roomService" class="cz.osu.services.RoomService" scope="request"/>
<jsp:useBean id="dataParser" class="cz.osu.ApiData.DataParser" scope="request"/>
<html>
<head>
    <title>Dashboard</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<main>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link" href="index.jsp">Home</a>
                <a class="nav-item nav-link active" href="sensor.jsp">Sensors</a>
                <a class="nav-item nav-link" href="statistics.jsp">Statistics</a>
            </div>
        </div>
    </nav>
    <div class="container mt-6">
        <div class="row">
            <div class="col-3">
                <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical" >
                    <c:forEach var="room" items="${roomService.list}">
                    <a class="nav-link <c:if test="${room.id == 1}">active</c:if>" id="${room.name}tab" data-toggle="pill" href="#room${room.id}" role="tab" <c:if test="${room.id == 1}"> aria-selected="true"</c:if> >${room.name}</a>
                    </c:forEach>
                </div>
            </div>
            <div class="col-9">
                <div class="tab-content" id="v-pills-tabContent">
                    <c:forEach var="room" items="${roomService.list}">
                        <div class="tab-pane fade show <c:if test="${room.id == 1}">active</c:if>" id="room${room.id}" role="tabpanel" >
                            <h2>${room.name}</h2>
                            <div class="card" style="width: 60%">
                                <ul class="list-group list-group-flush">
                                    <c:set var="currentTemp" value="${dataParser.getTempByRoom(room.id).data}"/>
                                    <li class="list-group-item">Temperature: ${currentTemp} °C</li>
                                    <li class="list-group-item">Heat: ${dataParser.getHeat(room.temp, currentTemp)}
                                    </li>
                                    <li class="list-group-item">Current consumption:
                                        <c:if test="${dataParser.getPowerByRoom(room.id).data == null}">
                                            0
                                        </c:if>${dataParser.getPowerByRoom(room.id).data} W</li>

                                </ul>
                                <form class="form-inline mt-4" action="setTemperature" method="post">
                                    <div class="form-group mb-2">
                                        <input type="hidden" readonly hidden name="roomId" id="roomId" value="${room.id}">
                                    </div>
                                    <div class="form-group mx-sm-3 mb-2">
                                        <label for="wantedTemp" class="sr">Temperature: </label>
                                        <input type="number" class="form-control" i min="0" max="50" step="0.1" name="wantedTemp" id="wantedTemp" value="${room.temp}">
                                    </div>
                                    <button type="submit" class="btn btn-primary mb-2">Save</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
    </div>


</main>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
