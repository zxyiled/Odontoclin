<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyfirstpart.jsp"%>

<h1>Register Dentist</h1>
<p>Here we can register dentist</p>

<form class="user">

    <div class="form-group col">
        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="dni"
                   placeholder="DNI">
        </div>

        <div class="col-sm-6 mb-3">
            <input type="text" class="form-control form-control-user" id="name"
                   placeholder="Name">
        </div>

    <div class="col-sm-6 mb-3">
        <input type="text" class="form-control form-control-user" id="lastName"
               placeholder="Last Name">
    </div>

    <div class="col-sm-6 mb-3">
        <input type="text" class="form-control form-control-user" id="address"
               placeholder="Address">
    </div>

    <div class="col-sm-6 mb-3">
        <input type="text" class="form-control form-control-user" id="phone"
               placeholder="Phone">
    </div>

    <div class="col-sm-6 mb-3">
        <input type="text" class="form-control form-control-user" id="dateOfBirth"
               placeholder="Date of Birth">
    </div>

    <div class="col-sm-6 mb-3">
        <input type="text" class="form-control form-control-user" id="specialty"
               placeholder="Specialty">
    </div>
    </div>
    <button class="btn btn-primary btn-user btn-block" type="submit">
        Register Dentist
    </button>
</form>

<%@include file="components/bodyfinal.jsp"%>
