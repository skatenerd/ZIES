<%@ page import="SudokuBackend.SudokuState" %>
<%@ page import="SudokuBackend.SudokuServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: 8thlight
  Date: 2/17/13
  Time: 2:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <script src="jquery.js" type="text/javascript"></script>
    <script src="refresh.js" type="text/javascript"></script>
    <title>ZERO INDEXED EMERGENT SUDOKU</title>
  </head>
  <body>
  <h3>ZERO INDEXED EMERGENT SUDOKU</h3>
  <h4><%= SudokuServlet.formatError(request) %></h4>
  <div id="result">
  </div>
  <form action="/sudoku" method="post">
      <div>ROW:</div>
      <select name="row">
          <option value="0">0</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
      </select>
      <div>COLUMN:</div>
      <select name="col">
          <option value="0">0</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
      </select>
      <div>VALUE:</div>
      <select name="value">
          <option value="0">0</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
          <option value="-1">BLANK</select>
      <br/>
      <input type="submit"/>
  </form>

  </body>
</html>