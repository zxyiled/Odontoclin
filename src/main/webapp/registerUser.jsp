<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyfirstpart.jsp"%>

<h1>Register User</h1>
<p>Here we can register users</p>

<form class="user">

  <div class="form-group col">

    <div class="col-sm-6 mb-3">
      <input type="text" class="form-control form-control-user" id="username"
             placeholder="Username">
    </div>

    <div class="col-sm-6 mb-3">
      <input type="password" class="form-control form-control-user" id="password"
             placeholder="Password">
    </div>

    <div class="col-sm-6 mb-3">
      <input type="text" class="form-control form-control-user" id="rol"
             placeholder="Role">
    </div>

  </div>
  <button class="btn btn-primary btn-user btn-block" type="submit">
    Register User
  </button>
</form>

<%@include file="components/bodyfinal.jsp"%>
