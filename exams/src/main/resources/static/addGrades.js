const gradeApp = {

    // Préparation des données
    data() {
        return {
            axios : null,
            students : null,
            idExam : 13,
            //newValue : this.students[0].grades[0].value,
        }
    },

    // Mise en place de l'application
    mounted() {
        this.initAxios();
        this.initData()
    },

    methods: {
        initAxios() {
            this.axios = axios.create({
                baseURL: 'http://localhost:8080/grade',
                timeout: 1000,
                headers: {'Content-Type' : 'application/json'}
            });
        },
        initData() {
            this.students = [];
            this.axios.get('/exam'+this.idExam, this.idExam).then(
                result => {
                    this.students = result.data;
                    console.log(this.students[0].grade);
                }
            );
        },
        saveGrade(idStudent) {
            console.log("entring in saveGrade");
            /*
            this.grade.idStudent = idStudent;
            console.log("id student affected : " + this.grade.idStudent);
            this.grade.idEx = this.idExam;
            console.log("id exam affected : " + this.grade.idEx);
            //this.grade.gradeValue = gradeValue;
            console.log("grade value affected : " + this.grade.gradeValue);
            //this.students[].grades[0].value = this.grade.gradeValue;
            */
            //this.students[idStudent].grade.value = this.newValue;
            this.axios.post('/exam'+this.idExam, this.students[idStudent].grade);
            //this.initData();

        }
    }
}

Vue.createApp(gradeApp).mount('#gradeApp');