const gradeApp = {

    // Préparation des données
    data() {
        return {
            axios : null,
            hash : null,
            idExam : 0,
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
                baseURL: 'http://localhost:8080/',
                timeout: 1000,
                headers: {'Content-Type' : 'application/json'}
            });
        },
        initData() {
            this.hash = new Map();
            this.axios.get('/grade/exam'+this.idExam, this.idExam).then(
                result => {
                    this.hash = result.data;
                }
            );

        },
        addGrades() {
            this.axios.post('/grade/exam'+this.idExam, this.idExam, this.hash.values());
        }
    }
}

Vue.createApp(gradeApp).mount('#gradeApp');