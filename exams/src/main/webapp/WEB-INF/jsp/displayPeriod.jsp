<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Affichage des périodes</title>
<script src="https://unpkg.com/vue@next"></script>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="module" src="/periodList.js"></script>
</head>
<body>
    <div id="appPeriodList">
        <h1>Liste des périodes</h1>

        <table class="table">
            <tr>
                <th>Nom de la période</th>
                <th>Date de début</th>
                <th>Date de fin</th>
            </tr>
            <tr v-for="period in listPeriod">

                <td>{{period.name}}</td>
                <td>{{period.beginDatePeriod}}</td>
                <td>{{period.endDatePeriod}}</td>
            </tr>
        </table>
    </div>
</body>
</html>