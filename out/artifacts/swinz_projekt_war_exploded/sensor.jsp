<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.02.2020
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
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
                <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                    <a class="nav-link active" id="someRoomTab" data-toggle="pill" href="#someRoom" role="tab" aria-selected="true">some room</a>
                    <a class="nav-link" id="someRoomTab2" data-toggle="pill" href="#someRoom2" role="tab">some room 2</a>

                </div>
            </div>
            <div class="col-9">
                <div class="tab-content" id="v-pills-tabContent">
                    <div class="tab-pane fade show active" id="someRoom" role="tabpanel" >
                        <h2>Living Room</h2>
                        <div class="card" style="width: 60%">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">Temperature: 25 °C</li>
                                <li class="list-group-item">Current consumption: 5 W</li>
                                <li class="list-group-item">AVG lights ON per day: 90 minutes</li>
                            </ul>
                            <form class="form-inline mt-4" action="setTemperature" method="post">
                                <div class="form-group mb-2">
                                    <input type="hidden" readonly hidden id="roomId" value="1">
                                </div>
                                <div class="form-group mx-sm-3 mb-2">
                                    <label for="wantedTemp" class="sr">Temperature: </label>
                                    <input type="number" class="form-control" i min="0" max="50" step="0.1" id="wantedTemp" value="35">
                                </div>
                                <button type="submit" class="btn btn-primary mb-2">Save</button>
                            </form>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="someRoom2" role="tabpanel" >
                        <h2>Kitchen</h2>
                        <div class="card" style="width: 60%">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">Temperature: 35 °C</li>
                                <li class="list-group-item">Current consumption: 50 W</li>
                                <li class="list-group-item">AVG lights ON per day: 360 minutes</li>
                            </ul>
                            <form class="form-inline mt-4" action="setTemperature" method="post">
                                <div class="form-group mb-2">
                                    <input type="hidden" readonly hidden id="roomId" value="1">
                                </div>
                                <div class="form-group mx-sm-3 mb-2">
                                    <label for="wantedTemp" class="sr">Temperature: </label>
                                    <input type="number" class="form-control" i min="0" max="50" step="0.1" id="wantedTemp" value="35">
                                </div>
                                <button type="submit" class="btn btn-primary mb-2">Save</button>
                            </form>
                        </div>

                    </div>
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
