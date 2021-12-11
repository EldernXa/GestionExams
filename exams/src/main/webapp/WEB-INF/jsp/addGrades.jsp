<h5 class="title">examen id : {{idExam}}</h5>
<div class="card text-white bg-secondary text-secondary m-3 " v-for="(student, grade) in hash" :key="grade">
    <div class="card-header bg-primary text-white h3">{{student.firstName}} {{student.lastName}}</div>
    <div class="card-body">
        <div class="d-flex justify-content-between">
            <div>
                <h5 class="card-title">note : {{grade.value}}</h5>
            </div>
            <!--
            <div>
                <h5 class="card-title">note :</h5><input type="text" class="form-control" id="grade" name="grade">
            </div>
            -->
        </div>
    </div>
</div>
