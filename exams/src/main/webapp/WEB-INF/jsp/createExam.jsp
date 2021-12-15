<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Création d'un nouvelle examen</title>
<script src="https://unpkg.com/vue@next"></script>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="module" src="/createExam.js"></script>
</head>
<body>

	<div id="appExamCreate">
		<h1>Création d'un nouvel examen</h1>
		
		<form id="appNewExam" method = "post">
			<div class="form-group">
				<label>Le nom de l'UE :</label><br />
				<select id="selectGettingUE" name="UE"></select>
				
				<br />
				
				<label>Le nom de la période :</label><br />
				<select id="selectGettingPeriod" name="Period"></select>
			</div>
		</form>
	</div>

</body>
</html>