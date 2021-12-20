const gradeApp = {

    // Préparation des données
    data() {
        return {
            axios : null,
            students : null,
            idExam : 13,
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
                }
            );
        },
        saveGrade(idStudent) {
            console.log("entring in saveGrade");
            this.axios.post('/exam'+this.idExam, this.students[idStudent].grade).then(
                result => {
                    this.students[idStudent].grade = result.data;
                }
            );

        },
        saveAllGrade(){
            this.students.forEach((student,index) => {
                this.axios.post('/exam'+this.idExam, student.grade).then(
                    result => {
                        this.students[index].grade = result.data;
                    }
                );

            })

        }
    }
}

Vue.createApp(gradeApp).mount('#gradeApp');