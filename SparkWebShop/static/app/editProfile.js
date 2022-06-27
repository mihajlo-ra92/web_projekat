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
		
		<input type="date" id="birthDate" name="birthDate"
       	value="2022-01-01" v-model=user.birthDate
       	min="1900-01-01" max="2122-01-01">
       	<br>
		
		<input type="radio" name="gender" value="MALE" v-model="user.gender">Male
		<br>
		<input type="radio" name="gender" value="FEMALE" v-model="user.gender">Female
		<br>
		<button v-on:click="submit" >Submit changes</button>
	
	</div>	
	
	
</div>	  
`
	,
	methods : {
		submit: function(){
			console.log("Submit!");
			axios
				.post('rest/edit-profile', this.user)
				.then(response => {
					if (response.data === false){
					toast("Failed, username is taken!");
					}
					else {
						toast("Succesfully edited user!");
					}
				})
				.catch((error) => console.log(error));
			router.push('/my-profile');
		}
	},
	mounted () {
        console.log("Mounted EditProfile");
        axios
			.get('rest/getCurrentUser')
      		.then(response => (this.user = response.data))
    }
});