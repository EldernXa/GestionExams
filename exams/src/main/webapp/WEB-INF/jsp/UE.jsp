<%@ include file="header.jsp"%>
<!--<script src="app.js" ></script>-->
<c:url var="app" value="/app.js" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<div id="myApp">

    <table id = "myTable2"class="table table-sm table-bordered">
        <tr class=" table-primary">
            <th>Nom UE</th>
            <th>crédit</th>
            <th>durée exam</th>
            <th>descipline</th>
        </tr>

        <tr v-for="element in ueList">
            <td>{{element.name}}</td>
            <td>{{element.credit}}</td>
            <td>{{element.durationExam}}</td>
            <td>{{element.discipline}}</td>
        </tr>
    </table>

</div>
<script src="${app}"></script>