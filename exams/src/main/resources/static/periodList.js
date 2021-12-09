const periodListApp = {
    data() {
        console.log("data");
        return{
            listPeriod : [],
            axios : null
        }
    },

    mounted(){
        console.log("début du début");
        this.axios = axios.create({
            baseURL: 'http://localhost:8080/',
            timeout: 1000,
            headers: {'Content-Type' : 'application/json'}
        });
        this.refresh();
        console.log("fin du début");
    },

    methods: {
        refresh: function(){
            this.axios.get("/periodList").then(r=>{
                this.listPeriod = r.data;
            });
        },
    },
}

Vue.createApp(periodListApp).mount('#appPeriodList');