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
				},
				freeMenagers: null,
				makingNewMenager: false,
				buttonText: 'Create new menager',
				newMenager: {
					username: '',
					password: '',
					firstName: '',
					lastName: '',
					birthDate: '',
					gender: '',
					role: 'MENAGER',
					sportObject: null
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
		
		Open time:
		<input type="time" name="timeStart" v-model="timeInput.timeStart">
		<br>
		
		Close time:
		<input type="time" name="timeEnd" v-model="timeInput.timeEnd">
		<br>
		<div v-if="!makingNewMenager">
			<label for="cars">Choose a menager:</label>
			
		  	<select name="menagers" id="menagers">
			    <option v-for="men in freeMenagers" value="men.username">{{men.username}}</option>
		    </select>
	    </div>
	    <button v-on:click="toggleMake">{{buttonText}}</button>
		<br>
		<div v-if="makingNewMenager">
			MAKING NEW MENAGER
		</div>
		<br>
		<button v-on:click="register" >Register</button>
	</div>	
	
	
</div>	  
`
	,
	methods : {
		register : function(){
			console.log("Register clicked!");
			if (this.currentUser.role != 'ADMIN'){
				toast("You are not loged in as admin!")
			}
			else {
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
		toggleMake : function(){
			this.makingNewMenager = !this.makingNewMenager;
			if (this.makingNewMenager){
				this.buttonText = 'Select existin menager';
			}
			else {
				this.buttonText = 'Create new menager';
			}
		}
	},
	mounted () {
        console.log("Mounted create sport object");
        axios
			.get('rest/getCurrentUser')
      		.then(response => (this.currentUser = response.data));
      	console.log("Getting free menagers...")
      	axios
      		.get('rest/proizvodi/getFreeMenagers')
      		.then(response => {
				this.freeMenagers = response.data;
				console.log(this.freeMenagers);
			})
    }
});