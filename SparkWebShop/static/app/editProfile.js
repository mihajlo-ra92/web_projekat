Vue.component("edit-profile", {
	data: function () {
		    return {
				user: {
				id: '',
				username: '',
				password: '',
				firstName: '',
				lastName: '',
				birthDate: '',
				gender: ''
				}
		    }
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Edit profile:
		<br>
		<input type="text" name="username" v-model="user.username" placeholder="Username" />
		<br>
		<input type="text" name="password" v-model="user.password" placeholder="Password" />
		<br>
		<input type="text" name="firstName" v-model="user.firstName" placeholder="First name" />
		<br>
		<input type="text" name="lastName" v-model="user.lastName" placeholder="Last name" />
		<br>
		<input type="radio" name="gender" value="MALE" v-model="user.gender">Male
		<br>
		<input type="radio" name="gender" value="FEMALE" v-model="user.gender">Female
		<br>
		<button v-on:click="submit" >Register</button>
	
	</div>	
	
	
</div>	  
`
	,
	methods : {
		submit: function(){
			console.log("Submit!");
		}
	},
	mounted () {
        console.log("Mounted EditProfile");
        axios
			.get('rest/getCurrentUser')
      		.then(response => (this.user = response.data))
    }
});