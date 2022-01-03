const myApp = {

    // Préparation des données
    data() {
        console.log("data");
        return {
            axios: null,
            inscriptions: [],
            connected: null,
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


    },







}


Vue.createApp(myApp).mount('#myApp');