<%@ page import="java.util.List" %>
<%@ page import="java.util.List" %>
<%@ page import="org.odontoclin.logic.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>
<%@include file="components/bodyfirstpart.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">View Users</h1>
    <p class="mb-4">Here you can see the information of the different registered users</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Users</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th style="width: 210px">Action</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>Id</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th style=" width: 210px">Action</th>
                    </tr>
                    </tfoot>

                    <% List<User> userList = (List) request.getSession().getAttribute("userList"); %>
                    <tbody>
                    <% for (User user : userList) { %>
                    <tr>
                        <td><%= user.getId_user() %></td>
                        <td><%= user.getUsername() %></td>
                        <td><%= user.getRole() %></td>
                        <td style = "display: flex ; width: 210px">
                            <form action="SvDeleteUser" method="POST">
                                <button type = "submit" class = "btn btn-primary btn-user btn-block" style = " background-color:red; margin-right:5px">
                                    <i class="fas fa-solid fa-trash"></i>
                                    Delete</button>
                                <input name = "id" type= "hidden" value = "<%= user.getId_user() %>"/>
                            </form>
                            <form action="SvEditUser" method="GET">
                                <button type = "submit" class = "btn btn-primary btn-user btn-block" style = "margin-left:5px">
                                    <i class="fas fa-solid fa-pen"></i>
                                    Edit</button>
                                <input name = "id" type= "hidden" value = "<%= user.getId_user() %>"/>
                            </form>
                        </td>
                    </tr>

                    <% }%>
                    </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

<%@include file="components/bodyfinal.jsp"%>