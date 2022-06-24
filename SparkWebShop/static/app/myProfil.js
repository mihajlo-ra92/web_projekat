Vue.component("my-profile", {
	data: function () {
		    return {
				currentUser: null,
				input: {
                    username: "",
                    firstname:"",
                    lastname:"",
                    birthdate:"",
                    gender:""
                },
                editing: false
		    }
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		My profile:
		<br>
		<div v-if="currentUser != null" v-if="!editing" >
			<p>Username: {{this.currentUser.username}}</p>
			<p>Password: {{this.currentUser.password}}</p>
			<p>First name: {{this.currentUser.firstName}}</p>
			<p>Last name: {{this.currentUser.lastName}}</p>
			<p>Birthdate: {{this.currentUser.birthDate}}</p>
			<p>Gender: {{this.currentUser.gender}}</p>
			<button type="button" v-on:click="startEdit()">Edit</button>
		</div>
	</div>	
	
	
</div>	  
`
	,
	methods : {
		startEdit(){
			console.log("editing is currently: " + this.editing);
			this.editing = true;
			console.log("editing is after change: " + this.editing);
		},
        edit(){
		 	axios
			 	.post('rest/proizvodi/editUser')
			 	.then(response => (console.log(response)))
				.catch((error) => console.log(error));
			router.push('/sportObjects');
		},
		cancal(){
			axios
	          	.get('/rest/proizvodi/getUser')
	          	.then(response => (this.currentUser = response.data))
		}
	},
	mounted () {
        console.log("Mounted MyProfile");
        axios
			.get('rest/getCurrentUser')
      		.then(response => (this.currentUser = response.data))
    }
});