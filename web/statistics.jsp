<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.02.2020
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="graphService" class="cz.osu.services.GraphService" scope="request"/>
<jsp:useBean id="roomService" class="cz.osu.services.RoomService" scope="request"/>
<jsp:useBean id="sensorService" class="cz.osu.services.SensorService" scope="request"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Dashboard</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>
<body>
<main>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link" href="index.jsp">Home</a>
                <a class="nav-item nav-link" href="sensor.jsp">Sensors</a>
                <a class="nav-item nav-link active" href="statistics.jsp">Statistics</a>
            </div>
        </div>
    </nav>

    <div class="container">
        <div>
            <h2>Počet dní se zaplým vytápěním: ${sensorService.daysWithHeatOnThisYear}</h2><br>
        </div>
        <div>
            <h2>Spotřeba energie za jednotlivé měsíce</h2>
            <canvas id="myChart" width="500" height="500"></canvas>
            <script>
                var ctx = document.getElementById('myChart').getContext('2d');
                var consumption = [];
                <c:forEach var="item" items="${graphService.powerConsumptionPerYear}">
                consumption.push(${item})
                </c:forEach>
                var myChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: ['January', 'February', 'March', 'April', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                        datasets: [{
                            label: 'Power Consumption',
                            data: consumption,
                            backgroundColor: [
                                'rgba(0, 0, 255, 0.2)',
                                'rgba(0, 127, 255, 0.2)',
                                'rgba(127, 255, 0, 0.2)',
                                'rgba(0, 255, 0, 0.2)',
                                'rgba(0, 255, 127, 0.2)',
                                'rgba(255, 127, 0, 0.2)',
                                'rgba(255, 0, 0, 0.2)',
                                'rgba(255, 255, 0, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(0, 255, 255, 0.2)'
                            ],
                            borderColor: [
                                'rgba(0, 0, 255, 1)',
                                'rgba(0, 127, 255, 1)',
                                'rgba(127, 255, 0, 1)',
                                'rgba(0, 255, 0, 1)',
                                'rgba(0, 255, 127, 1)',
                                'rgba(255, 127, 0, 1)',
                                'rgba(255, 0, 0, 1)',
                                'rgba(255, 255, 0, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(0, 255, 255, 1)'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: false,
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                },
                                scaleLabel: {
                                    display: true,
                                    labelString: 'Spotřeba energie [W]',
                                    fontSize: 20
                                }
                            }]
                        }
                    }
                });
            </script>
        </div>
        <div>
            <h2>Průměrná doba svícené za jednotlivé měsíce</h2>
            <canvas id="myChart1" width="800" height="500"></canvas>
            <script>
                var canvas = document.getElementById("myChart1");
                var ctx = canvas.getContext('2d');
                <c:forEach var="room" items="${roomService.list}">
                var room${room.id} = [];
                <c:forEach var="item" items="${graphService.getLightsOnPerYearPerSensor(room.id)}">
                room${room.id}.push(${item});
                </c:forEach>
                </c:forEach>
                Chart.defaults.global.defaultFontColor = 'black';
                Chart.defaults.global.defaultFontSize = 16;
                var data = {
                    labels: ['January', 'February', 'March', 'April', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                    datasets: [
                        <c:forEach var="room" items="${roomService.list}">
                        {
                            label: "${room.name}",
                            fill: false,
                            lineTension: 0.1,
                            //backgroundColor: "rgba(225,0,0,0.4)",
                            borderColor: "rgba(" + Math.floor(Math.random()*256) + "," + Math.floor(Math.random()*256) + "," + Math.floor(Math.random()*256) + ", 1)",                            borderCapStyle: 'square',
                            borderDash: [],
                            borderDashOffset: 0.0,
                            borderJoinStyle: 'miter',
                            pointBorderColor: "black",
                            pointBackgroundColor: "white",
                            pointBorderWidth: 1,
                            pointHoverRadius: 8,
                            pointHoverBackgroundColor: "yellow",
                            pointHoverBorderColor: "brown",
                            pointHoverBorderWidth: 2,
                            pointRadius: 4,
                            pointHitRadius: 10,
                            data: room${room.id},
                            spanGaps: true,
                        },
                        </c:forEach>
                    ]
                };
                var options = {
                    responsive: false,
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero:true
                            },
                            scaleLabel: {
                                display: true,
                                labelString: 'Lights on',
                                fontSize: 20
                            }
                        }]
                    }
                };
                var myBarChart = new Chart(ctx, {
                    type: 'line',
                    data: data,
                    options: options
                });
            </script>
        </div>
    </div>
</main>
</body>
</html>
