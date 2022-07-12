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
					sportObject: ''
				},
				canCreate: true,
				selected: ''
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
		
		<input type="number" name="location_geoLenght" v-model="sportObject.location.geoLenght" placeholder="Location geoLenght" />
		<br>
		<input type="number" name="location_geoWidth" v-model="sportObject.location.geoWidth" placeholder="Location geoWidth" />
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
			<label for="choose">Choose a menager:</label>
			
		  	<select name="menagers" id="menagers" v-model="selected">
			    <option v-for="men in freeMenagers" v-bind:value="{id: men.username, text: men.username}">{{men.username}}</option>
		    </select>
	    </div>
	    <button v-on:click="toggleMake">{{buttonText}}</button>
		<br>
		<div v-if="makingNewMenager">
			
			Create menager:
			<br>
			<input type="text" name="username" v-model="newMenager.username" placeholder="Username" />
			<br>
			<input type="text" name="password" v-model="newMenager.password" placeholder="Password" />
			<br>
			<input type="text" name="firstName" v-model="newMenager.firstName" placeholder="First name" />
			<br>
			<input type="text" name="lastName" v-model="newMenager.lastName" placeholder="Last name" />
			<br>
			
			<input type="date" id="birthDate" name="birthDate"
	       	value="2022-01-01" v-model=newMenager.birthDate
	       	min="1900-01-01" max="2122-01-01">
	       	<br>
	       	
			<input type="radio" name="gender" value="MALE" v-model="newMenager.gender">Male
			<br>
			<input type="radio" name="gender" value="FEMALE" v-model="newMenager.gender">Female
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
				this.newMenager.sportObject = this.sportObject.name;
				if(this.sportObject.name === "" || this.sportObject.objectType === ""
				|| this.sportObject.location.geoLenght === "" || this.sportObject.location.geoWidth === ""
				|| this.sportObject.location.address === "" || this.timeInput.timeStart === ""
				|| this.timeInput.timeEnd === ""){
					this.canCreate = false;
				}
				if (this.makingNewMenager && this.canCreate){
			    	//creating menager
			    	axios
				    .post('/rest/register-menager', this.newMenager)
				    .then(response => {
						if (response.data === "No field can be empty!"){
							toast(response.data);
							this.canCreate = false;
						}
						else if (response.data === "Username is taken!"){
							toast(response.data);
							this.canCreate = false;
						}
						else {
							toast("Succesfully registered menager!");
							this.canCreate = true;
						}
					})
			    	.catch((error) => console.log(error));
				}
				
		    	if (this.canCreate){
					//creating new sportobject
					axios
					    .post('/rest/register-sport-object', this.sportObject)
					    .then(response => {
							if (response.data === false){
								toast("Failed, sport object name is taken!");
								this.canCreate = false;
							}
							else {
								toast("Succesfully registered sport object!");
								this.canCreate = true;
							}
						})
				    	.catch((error) => console.log(error));
				}
				
		    	
		    	if (this.makingNewMenager && this.canCreate){
					//setting sportobject to new menager
					const request = this.sportObject.name + '+' + this.newMenager.username;
					axios
						.post('/rest/set-object-to-menager', request)
						.then(response => {
							console.log('Set object to new menager post req')
						})
				    	.catch((error) => console.log(error));
		    		router.push('/my-profile')
			    	
				}
				
				if (!this.makingNewMenager && this.canCreate){
					//setting sportobject to selected menager
					const request = this.sportObject.name + '+' + this.selected.text;
					console.log('Requset concatenated:');
					console.log(request);
					axios
						.post('/rest/set-object-to-menager', request)
						.then(response => {
							console.log('Set object to new menager post req')
						})
				    	.catch((error) => console.log(error));
		    		router.push('/my-profile')
				}
		    	
			}
		},
		toggleMake : function(){
			this.makingNewMenager = !this.makingNewMenager;
			if (this.makingNewMenager){
				this.buttonText = 'Select existing menager';
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