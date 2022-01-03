const appPeriodForm = {
    data() {
        return {
            axios: null,
            period: {name: "", beginDatePeriod: "", endDatePeriod: ""},
        }
    },

    mounted(){
        this.axios = axios.create({
            baseURL: 'http://localhost:8080/',
            timeout: 1000,
            headers: {'Content-Type' : 'application/json'}
        });
    },
    methods: {
        submitPeriod: function(){
            window.location.href = "http://localhost:8080/periodDisplay";
            this.axios.post("/period", this.period).then(()=>{
            });
        },
    }

}

Vue.createApp(appPeriodForm).mount('#appPeriodForm');