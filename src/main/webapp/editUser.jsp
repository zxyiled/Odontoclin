<%@ page import="org.odontoclin.logic.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyfirstpart.jsp"%>

<h1>Edit User</h1>
<p>Here we can edit the users</p>

<% User user = (User) request.getAttribute("username"); %>

<form class="user" action="SvUser" method="POST">

  <div class="form-group col">

    <div class="col-sm-6 mb-3">
      <input type="text" class="form-control form-control-user" id="username" name="username"
             placeholder="Username" value="<%= user.getUsername() %>">
    </div>

    <div class="col-sm-6 mb-3">
      <input type="password" class="form-control form-control-user" id="password" name="password"
             placeholder="Password" value="<%= user.getPassword() %>">
    </div>

    <div class="col-sm-6 mb-3">
      <input type="text" class="form-control form-control-user" id="role" name="role"
             placeholder="Role" value="<%= user.getRole() %>"><%= user.getId_user() %>
    </div>

  </div>
  <button class="btn btn-primary btn-user btn-block" type="submit">
    Save Edit
  </button>
</form>

<%@include file="components/bodyfinal.jsp"%>