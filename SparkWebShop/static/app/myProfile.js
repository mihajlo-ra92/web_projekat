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
			
			<button type="button" v-if="currentUser.role.includes('ADMIN')" v-on:click="createSportObject()">Create sport object</button>
			
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
		
		createSportObject(){
			console.log("Pushing router to create sport object!");
			router.push('/create-sport-object')
		}
	},
	mounted () {
        console.log("Mounted MyProfile");
        axios
			.get('rest/getCurrentUser')
      		.then(response => (this.currentUser = response.data))
    }
});