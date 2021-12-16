const appExamCreate = {
	data(){
		return {
			axios: null,
			exam: {
				nameUE: "",
				namePeriod: "",
				session: 1,
				year: 2000,
			},
			listUE: null,
			listPeriod: null,
		}
	},
	
	mounted(){
		this.axios = axios.create({
			baseURL: 'http://localhost:8080/',
			timeout: 1000,
			headers: {'Content-Type' : 'application/json'}
		});
		this.changeSelectFormUE();
		this.changeSelectFormPeriod();
	},
	methods: {
		changeSelectFormUE: function(){
			var formSelect = document.getElementById('selectGettingUE');
			this.axios.get("/exam/listUE").then(r=>{
                this.listUE = r.data;
                for(let i = 0; i < this.listUE.length; i++){
                	var opt1 = document.createElement("option");
                	opt1.value = (i+1).toString();
					opt1.text = this.listUE.at(i);
					formSelect.add(opt1, null);
            	}
            });
		},
		changeSelectFormPeriod: function(){
			var formSelect = document.getElementById('selectGettingPeriod');
			this.axios.get("/periodListName").then(r=>{
				this.listPeriod = r.data;
				for(let i =0; i<r.data.length; i++){
					var opt1 = document.createElement("option");
					opt1.value = (i+1).toString();
					opt1.text = r.data.at(i).name;
					formSelect.add(opt1, null);
				}
			});
			
		},
		submitExam: function(){
			this.exam.nameUE = this.listUE.at(this.exam.nameUE-1);
			this.exam.namePeriod = this.listPeriod.at(this.exam.namePeriod-1).id;
			window.location.href = "http://localhost:8080/exam";
			console.log(this.exam);
			this.axios.post("/exam/add", this.exam);
		}
	}
}

Vue.createApp(appExamCreate).mount('#appExamCreate');















