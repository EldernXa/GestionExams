const myApp = {

    // Préparation des données
    data() {
        return {
            axios: null,
            students : null,
            idExam : null,
            grades : null,
        }
    },

    // Mise en place de l'application
    mounted() {
        this.initAxios();
        this.initData()
    },

    methods: {

        initAxios() {
            var headers;
            headers = {'Content-Type': 'application/json'}
            this.axios = axios.create({
                baseURL: 'http://localhost:8080/',
                timeout: 1000,
                headers: headers
            });
        },
        initData() {

            this.students = [];
            this.idExam = 1;
            this.grades = [];

            this.axios.get('/grade/exam'+this.idExam, {params: {id : id}}).then(
                result => {
                    this.grades = result.data;
                }
            );
            console.log("data initialized.");
        },
        addGrades() {
            this.axios.post('/grade/exam'+this.idExam, {params: {id : id, grades : grades}});
        }
    }
}

Vue.createApp(myApp).mount('#myApp');