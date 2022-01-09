<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
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
            <td><button v-on:click.prevent="getUeToForm(element.name)" class="btn btn-primary">Modifier</button></td>
            <td><button v-on:v-on:click.prevent="(element.email)" class="btn btn-primary">Supprimer</button></td>
        </tr>
    </table>

    <br>
    <button v-on:click.prevent="initializeUeToAdd()" class="btn btn-primary">Ajouter UE</button>

    <div v-if="(editclick===true)" class="container d-flex h-100 mt-5 d-flex justify-content-center">

        <div class="container py-5 h-100">

            <div class="row d-flex justify-content-center align-items-center h-100">

                <div class="col-12 col-md-8 col-lg-6 col-xl-5">

                    <div class="card bg-info text-light" style="border-radius: 1rem;">

                        <div class="card-body p-5 text-center">

                            <div class="mb-md-5 mt-md-4 pb-5">

                                <h2 class="fw-bold mb-2 text-uppercase">Modifier UE</h2>

                                <form id="app" method="post" >

                                    <div class="form-group">
                                        <label>Credit:</label>
                                        <input v-model=" UeToBeUpdated.credit" class="form-control" />
                                    </div>

                                    <div class="form-group" >
                                        <label>Exam duration:</label>
                                        <input v-model=" UeToBeUpdated.durationExam" class="form-control"/>
                                    </div>

                                    <div class="form-group">
                                        <button v-on:click.prevent="submitEditUE(UeToBeUpdated.name)" class="btn btn-primary">Modifier</button>
                                    </div>

                                </form>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>


    <div v-if="(add===true)" class="container d-flex h-100 mt-5 d-flex justify-content-center">

        <div class="container py-5 h-100">

            <div class="row d-flex justify-content-center align-items-center h-100">

                <div class="col-12 col-md-8 col-lg-6 col-xl-5">

                    <div class="card bg-info text-light" style="border-radius: 1rem;">

                        <div class="card-body p-5 text-center">

                            <div class="mb-md-5 mt-md-4 pb-5">

                                <h2 class="fw-bold mb-2 text-uppercase">Ajout d'activit&eacute</h2>

                                <form id="app" method="post" >

                                    <div class="form-group">
                                        <label>Nom UE:</label>
                                        <input v-model="UeToBeAdded.name" class="form-control" />
                                    </div>

                                    <div class="form-group" >
                                        <label>Credit:</label>
                                        <input v-model="UeToBeAdded.credit" class="form-control"/>
                                    </div>

                                    <div class="form-group" >
                                        <label>duration Exam:</label>
                                        <input v-model="UeToBeAdded.durationExam" class="form-control"/>
                                    </div>

                                    <div class="form-group">
                                        <label>Discipline:</label>
                                        <textarea v-model="UeToBeAdded.discipline" rows="5" cols="50" class="form-control"></textarea>
                                    </div>

                                    <div class="form-group">
                                        <button v-on:click.prevent="addUe()"  class="btn btn-primary">Ajouter</button>

                                    </div>

                                </form>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>


</div>
<script src="${app}"></script>