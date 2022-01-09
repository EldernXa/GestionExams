<%@ include file="header.jsp"%>
<script src="https://unpkg.com/vue@next"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<div id="myApp">

    <a class="navbar-brand text-light" >{{connected}} est connect&eacute</a>

    <table id = "myTable"class="table table-sm table-bordered">
        <tr class=" table-primary">
            <th>Year</th>
            <th>Nom UE</th>
            <th>Credits</th>
            <th>Duree d'exam</th>


        </tr>

        <tr v-for="element in inscriptions" >
            <td>{{element.year}}</td>
            <td>{{element.ue.name}}</td>
            <td>{{element.ue.credit}}</td>
            <td>{{element.ue.durationExam}}</td>
        </tr>
    </table>

</div>
<script src="/Inscription.js"></script>
<%@ include file="footer.jsp"%>