const myApp = {
    data(){
        console.log("data");
        return{
            axois : null,
            ueList : [],
            UeByName : null,
            updatedUE : null,
            UeToBeAdded : null,
            UeToBeUpdated:{
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
                'Authorization': 'Bearer ' + localStorage.getItem('user-token')
            },
        });
        if (localStorage.getItem('user-token')) {
            this.token = localStorage.getItem('user-token');
            this.authenticated = true
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('user-token');
        }
    },

    methods : {
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

        updateUe : function (name){
            this.axios.put('/update/name'+name).then(r =>{
                this.updatedUE = r.data;
                console.log(this.updatedUE);
            })
        },

        createUe : function (){
            this.axios.post('/add', this.UeToBeAdded).then(r =>{
                this.UeToBeAdded = r.data;
            })
        },
    }
}