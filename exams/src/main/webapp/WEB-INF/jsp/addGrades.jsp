<h5 class="title mt-5 mb-5 d-flex justify-content-center">examen id : {{idExam}}</h5>
<div class="card text-white bg-secondary text-secondary m-3 " v-for="(s,index) in students">
    <div class="card-header bg-primary text-white h3">{{s.firstName}} {{s.lastName}}</div>
    <div class="card-body">
        <div class="w-100 card-title d-flex justify-content-between">
            <label class="mt-auto mb-auto h5" for="grade">Note :</label>
            <input v-model="students[index].grade.value" type="number" class="form-control m-3 w-25" id="grade" name="grade">
            <small><button class="btn btn-info m-3" v-on:click="saveGrade(index)">OK</button></small>
        </div>
    </div>
</div>
