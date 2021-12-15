<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Affichage des différents Examens</title>
<script src="https://unpkg.com/vue@next"></script>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="module" src="/examList.js"></script>
</head>
<body>
    <div id="appExamList">
        <h1>Liste des examens</h1>

        <table class="table">
            <tr>
                <th>Nom de l'ue</th>
                <th>Date de début</th>
                <th>Date de fin</th>
            </tr>
            <tr v-for="exam in listExam">

                <td>{{exam.name}}</td>
                <td>{{exam.beginDateExam}}</td>
                <td>{{exam.endDateExam}}</td>
            </tr>
        </table>
    </div>
</body>
</html>