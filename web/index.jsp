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
          <a class="nav-item nav-link active" href="index.jsp">Home</a>
          <a class="nav-item nav-link" href="sensor.jsp">Sensors</a>
          <a class="nav-item nav-link" href="statistics.jsp">Statistics</a>
        </div>
      </div>
    </nav>
    <div class="container mt-6">
      <table class="table table-striped">
        <thead>
        <tr>
          <th scope="col">Heat</th>
          <th scope="col">Room</th>
          <th scope="col">Current temp.</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td scope="row">ON</td>
          <td>Living room</td>
          <td>25</td>
        </tr>
        <tr>
          <td scope="row">OFF</td>
          <td>Kitchen</td>
          <td>30</td>
        </tr>
        </tbody>
      </table>
    </div>
  </main>
  </body>
</html>
