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
				<select v-model="exam.nameUE" id="selectGettingUE" name="UE"></select>
			</div>
			<br />
			
			<div class="form-group">
				<label>Le nom de la période :</label><br />
				<select v-model="exam.namePeriod" id="selectGettingPeriod" name="Period"></select>
			</div>			
			
			<br/>
			
			<div class="form-group">
				<label>La session :</label>
				<input v-model="exam.session" type="number" min="1" max="2" step="1"/>
			</div>
			
			<div class="form-group">
				<label>L'année :</label>
				<input v-model="exam.year" type="number" min="2000" step="1"/>
			</div>
			
			<div class="form-group">
				<button v-on:click.prevent="submitExam()" class="btn btn-primary">
                    Enregistrer l'examen.</button>
			</div>
		</form>
	</div>

</body>
</html>