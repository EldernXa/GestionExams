<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="bootstrap_css" value="/webjars/bootstrap/4.6.0-1/css/bootstrap.min.css" />
<c:url var="bootstrap_style_css" value="https://bootswatch.com/4/lux/bootstrap.css" />
<c:url var="bootstrap_js" value="/webjars/bootstrap/4.6.0-1/js/bootstrap.min.js" />
<c:url var="jquery_js" value="/webjars/jquery/3.5.1/jquery.min.js" />
<c:url var="vue_js" value="/webjars/vue/3.2.19/dist/vue.global.js" />
<c:url var="axios_js" value="/webjars/axios/0.22.0/dist/axios.min.js" />

<script src="${vue_js}"></script>
<script src="${axios_js}"></script>

<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion Exams</title>
    <link rel="stylesheet" href="${bootstrap_css}">
    <link rel="stylesheet" href="${bootstrap_style_css}">
    <script src="${jquery_js}"></script>
    <script src="${bootstrap_js}"></script>
</head>
<body>