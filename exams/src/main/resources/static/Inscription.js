const myApp = {

    // Préparation des données
    data() {
        console.log("data");
        return {
            axios: null,
            inscriptions: [],
            connected: null,
            inscription:null
        }
    },
    // Mise en place de l'application
    mounted() {

        console.log("Mounted///////////////////////////////// ");
        this.axios = axios.create({
            baseURL: 'http://localhost:8080/inscription/',
            timeout: 1000,
            headers: {
                'Content-Type': 'application/json',
                
            },
            
               });

			this.axios.get('/all/'+'email0')
                .then(r => {
                    console.log(r)
                    this.inscriptions = r.data;
              
                    this.connected='email0';
                	console.log(this.connected);
                });


    },

    methods: {
        // Place pour les futures méthodes
        getAllInscriptionsOfStudent: function (email) {
            this.axios.get('/all/'+email)
                .then(r => {
                    console.log(r)
                    this.inscriptions = r.data;
                    this.connected=email;
                    window.location.href = 'inscription';
                });
        },

        registerStudentToUE : function (id,year,nameUE){
            this.axios.post('/register/'+id+'/'+year+'/'+nameUE)
                .then(r => {
                    console.log(r)
                    this.inscription = r.data;
                    window.location.href = 'inscription';
                });
        },

        unsubscribeStudentFromInscription : function (id , year , nameUE){
            this.axios.delete('/unsubscribe/'+id+'/'+year+'/'+nameUE)
                .then(r => {
                    console.log(r)
                    this.inscription = r.data;
                    window.location.href = 'inscription';
                });


        },







    },







}


Vue.createApp(myApp).mount('#myApp');