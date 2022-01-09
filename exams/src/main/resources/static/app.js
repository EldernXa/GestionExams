const myApp = {
    data(){
        console.log("data");
        return{
            axios : null,
            ueList : null,
            UeByName : null,
            updatedUE : null,
            UeToBeAdded : null,
            editclick : false,
            add : false,
            UeToBeUpdated:{
                name : "",
                credit : "",
                durationExam : "",
                discipline : "",
            },
            UeToBeAdded:{
                name : "",
                credit : "",
                durationExam : "",
                discipline : "",
            }
        }
    },

    mounted(){
        console.log("Mounted");
        this.axios = axios.create({
            baseURL: 'http://localhost:8080/ue/',
            timeout: 1000,
            headers: {
                'Content-Type': 'application/json',
            },
        });

        this.getAllUe();
    },

    methods : {

        initializeUeToAdd: function () {
            this.add = true;
        },
        addUe : function (){
            this.axios.post('/add/', this.UeToBeAdded).then(
                r =>{
                    this.UeToBeAdded = r.data
                })
        },

        getUeToForm : function (id) {
            this.axios.get(id)
                .then(r => {
                    this.UeToBeUpdated = r.data
                    console.log(this.UeToBeUpdated)
                });
            this.editclick = true
            console.log(this.editclick)
        },

        submitEditUE : function (name) {
            this.axios.put('/update/' + name, this.UeToBeUpdated)
            console.log(this.UeToBeUpdated)
        },

        getAllUe : function (){
            this.axios.get('/allUE').then(r => {
                console.log(r);
                this.ueList = r.data;
            });
        },

        getUeName : function(name){
            this.axios.get('/name'+name).then(r =>{
                this.UeByName = r.data;
                console.log(this.UeByName);
            })
        },

        deleteUe : function (name){
            this.axios.delete('/name/'+name)
        },

        createUe : function (){
            this.axios.post('/add', this.UeToBeAdded).then(r =>{
                this.UeToBeAdded = r.data;
            })
        },
    }
}
Vue.createApp(myApp).mount('#myApp');