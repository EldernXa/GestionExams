<%@ include file="header.jsp"%>
<script src="../../../resources/static/app.js" ></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<div id="myApp">

    <table id = "myTable2"class="table table-sm table-bordered">
        <tr class=" table-primary">
            <th>Nom UE</th>
            <th>crédit</th>
            <th>durée exam</th>
            <th>descipline</th>
        </tr>

        <tr v-for="ue in ueList">
            <td>{{ue.name}}</td>
            <td>{{ue.credit}}</td>
            <td>{{ue.durationExam}}</td>
            <td>{{ue.discipline}}</td>
        </tr>
    </table>

</div>
<script src="${app}"></script>