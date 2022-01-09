const examListApp = {
    data() {
        return{
            listExam : [],
            axios : null
        }
    },

    mounted(){
        this.axios = axios.create({
            baseURL: 'http://localhost:8080/',
            timeout: 1000,
            headers: {'Content-Type' : 'application/json'}
        });
        this.refresh();
    },

    methods: {
        refresh: function(){
            this.axios.get("/exam/list").then(r=>{
                this.listExam = r.data;
                this.refreshExam();
            });
        },

        refreshExam: function(){
            for(let i = 0; i < this.listExam.length; i++){
                let ind = this.listExam.at(i).idExam;
                this.axios.get("/exam/"+ind+"/nameUE").then(r=>{
    	            this.listExam.at(i).name = r.data;
				});
                this.axios.get("/exam/" + ind +"/beginDate").then(r=>{
                    this.listExam.at(i).beginDateExam = r.data;
                });
                this.axios.get("/exam/" + ind + "/endDate").then(r=>{
                    this.listExam.at(i).endDateExam = r.data;
                });
            }
        }
    },
}

Vue.createApp(examListApp).mount('#appExamList');