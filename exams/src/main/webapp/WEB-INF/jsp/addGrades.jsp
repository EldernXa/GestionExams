<div class="w-50 ml-auto mr-auto">
    <h5 class="title">examen id : {{grade.gradePK.exam.idExam}}</h5>
    <div class="card text-white bg-secondary text-secondary m-3 " v-for="grade in grades">
        <div class="card-header bg-primary text-white h3">{{grade.gradePK.student.firstName}} {{grade.gradePK.student.lastName}}</div>
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
</div>