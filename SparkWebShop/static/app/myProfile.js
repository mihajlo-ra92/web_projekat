Vue.component("my-profile", {
	data: function () {
		    return {
				currentUser: null
		    }
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		My profile:
		<br>
		<div v-if="currentUser != null">
			<p>Username: {{this.currentUser.username}}</p>
			<p>Password: {{this.currentUser.password}}</p>
			<p>First name: {{this.currentUser.firstName}}</p>
			<p>Last name: {{this.currentUser.lastName}}</p>
			<p>Birthdate: {{this.currentUser.birthDate}}</p>
			<p>Gender: {{this.currentUser.gender}}</p>
			<button type="button" v-on:click="startEdit()">Edit</button>
			<br>
			<button type="button" v-on:click="logOut()">Log out</button>
			<br>
			
			<button type="button" v-if="currentUser.role.includes('ADMIN')" v-on:click="createSportObject()">Create sport object</button>
			<button type="button" v-if="currentUser.role.includes('ADMIN')" v-on:click="createMenager()">Create menager</button>
			<button type="button" v-if="currentUser.role.includes('ADMIN')" v-on:click="createTrainer()">Create trainer</button>
			<br>
			<button type="button" v-if="currentUser.role.includes('ADMIN')" v-on:click="listUsers()">List users</button>
			<button type="button" v-if="currentUser.role.includes('ADMIN')" v-on:click="listSportObjects()">List sport objects</button>
			
			
			<button type="button" v-if="currentUser.role.includes('MENAGER')" v-on:click="showSportObject()">Show my sport object</button>
			
		</div>
	</div>	
	
	
</div>	  
`
	,
	methods : {
		startEdit(){
			console.log("Pushing router to edit profile!");
			router.push('/edit-profile')
		},
		
		logOut(){
			console.log("Log out clicked");
			axios
		    .post('rest/log-out')
		    .then(response => {
				console.log(response.data);
			})
	    	.catch((error) => console.log(error));
			router.push('/');
		},
		
		createSportObject(){
			console.log("Pushing router to create sport object!");
			router.push('/create-sport-object')
		},
		
		createMenager(){
			console.log("Pushing router to create menager!");
			router.push('/create-menager')
		},
		
		createTrainer(){
			console.log("Pushing router to create trainer!");
			router.push('/create-trainer')
		},
		
		listUsers(){
			console.log("Pushing router to list users!");
			router.push('/list-users')
		},
		
		listSportObjects(){
			console.log("Pushing router to list sport objects!");
			router.push('/list-sport-objects')
		},
		showSportObject(){
			console.log("Show sport object selected!");
		}
	},
	mounted () {
        console.log("Mounted MyProfile");
        axios
			.get('rest/getCurrentUser')
      		.then(response => {
				if (response.data == '404'){
					console.log('No loged in user!');
				}
				else {
					this.currentUser = response.data
				}
			})
    }
});