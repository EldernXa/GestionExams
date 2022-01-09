<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Création d'une période</title>
<script src="https://unpkg.com/vue@next"></script>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="module" src="/periodForm.js"></script>
</head>
<body>
    <div id="appPeriodForm">
        <h1>Ajout de période</h1>
        <form id="app" method="post" novalidate="true">

            <div class="form-group">
                <label>Nom de la période :</label>
                <input v-model="period.name" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Date de début de la période :</label>
                <input type="date" v-model="period.beginDatePeriod" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Date de fin de la période :</label>
                <input type="date" v-model="period.endDatePeriod" class="form-control"/>
            </div>
            <div class="form-group">
                <button v-on:click.prevent="submitPeriod()" class="btn btn-primary">
                    Enregistrer la période.</button>
                <!-- <button v-on:click="refresh()" class="btn btn-primary">
                    Abort</button> -->
            </div>
        </form>
    </div>
</body>
</html>