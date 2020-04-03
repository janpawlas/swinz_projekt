<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.02.2020
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="graphService" class="cz.osu.services.GraphService" scope="request"/>
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
                            }
                        }]
                    }
                }
            });
        </script>
    </div>
</main>
</body>
</html>
