Vue.component("register", {
	data: function () {
		return {
			user: {
				username: '',
				password: '',
				firstName: '',
				lastName: '',
				birthDate: '',
				gender: '',
				role: 'BUYER',
				membership : null,
				visitedSportObject : null,
				points : null,
				buyerType : null
			},
			responseData : null
		}
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		<p>Register user</p>
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
		if (this.user.username === "" || this.user.password === "" 
		|| this.user.firstName === "" || this.user.lastName === ""
		|| this.user.birthDate === "" || this.user.gender === ""){
			toast("No fields can be left empty!");
		}
		else {			
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
				router.push('/');
		}
		}
	},
	mounted () {
		console.log("Mounted register!");
    },
});	  