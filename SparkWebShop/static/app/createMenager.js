Vue.component("create-menager", {
	data: function () {
		    return {
				currentUser: null,
				user: {
				username: '',
				password: '',
				firstName: '',
				lastName: '',
				birthDate: '',
				gender: '',
				role: 'MENAGER'
			}
		    }
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Create menager:
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
		<button v-on:click="register" >Register</button>
	</div>	
	
	
</div>	  
`
	,
	methods : {
		register : function (){
		console.log("Register!!!");
		axios
		    .post('/rest/register', this.user)
		    .then(response => {
				if (response.data === false){
					toast("Failed, username is taken!");
				}
				else {
					toast("Succesfully registered user!");
				}
			})
	    	.catch((error) => console.log(error));
		}
	},
	mounted () {
        console.log("Mounted create menager");
        axios
			.get('rest/getCurrentUser')
      		.then(response => (this.currentUser = response.data))
    }
});