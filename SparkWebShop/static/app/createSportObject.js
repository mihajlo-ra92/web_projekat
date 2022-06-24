Vue.component("create-sport-object", {
	data: function () {
		    return {
				currentUser: null,
				sportObject: {
					name: '',
					objectType: '',
					location: {
						geoLenght: '',
						geoWidth: '',
						address: ''
					},
					workHours: ''
				},
				timeInput: {
					timeStart: '',
					timeEnd: ''
				}
		    }
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Create sport object:
		<br>
		<input type="text" name="name" v-model="sportObject.name" placeholder="Name" />
		<br>
		
		<input type="radio" name="objectType" value="gym" v-model="sportObject.objectType">Gym
		<br>
		<input type="radio" name="objectType" value="pool" v-model="sportObject.objectType">Pool
		<br>
		<input type="radio" name="objectType" value="dance studio" v-model="sportObject.objectType">Dance studio
		<br>
		
		<input type="text" name="location_geoLenght" v-model="sportObject.location.geoLenght" placeholder="Location geoLenght" />
		<br>
		<input type="text" name="location_geoWidth" v-model="sportObject.location.geoWidth" placeholder="Location geoWidth" />
		<br>
		<input type="text" name="location_address" v-model="sportObject.location.address" placeholder="Location address" />
		<br>
		
		Pocetno vreme:
		<input type="time" name="timeStart" v-model="timeInput.timeStart">
		<br>
		
		Vreme zatvaranja:
		<input type="time" name="timeEnd" v-model="timeInput.timeEnd">
		<br>
		
		<button v-on:click="register" >Register</button>
	</div>	
	
	
</div>	  
`
	,
	methods : {
		register(){
			console.log("Register clicked!");
			this.sportObject.workHours = this.timeInput.timeStart
			 + "-" + this.timeInput.timeEnd;
			console.log(this.sportObject.workHours);
			axios
		    .post('/rest/register-sport-object', this.sportObject)
		    .then(response => {
				if (response.data === false){
					toast("Failed, sport object name is taken!");
				}
				else {
					toast("Succesfully registered sport object!");
				}
			})
	    	.catch((error) => console.log(error));
		}
	},
	mounted () {
        console.log("Mounted create sport object");
        axios
			.get('rest/getCurrentUser')
      		.then(response => (this.currentUser = response.data))
    }
});