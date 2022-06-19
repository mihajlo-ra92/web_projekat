Vue.component("register", {
	data: function () {
		return {
			user: {
				username: '',
				password: '',
				firstName: '',
				lastName: '',
				
			}
		}
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		<p>Register!!!!</p>
		<input type="text" name="username" v-model="user.username" placeholder="Username" />
		<br>
		<input type="text" name="password" v-model="user.password" placeholder="Password" />
		<br>
		<input type="text" name="firstName" v-model="user.firstName" placeholder="First name" />
		<br>
		<input type="text" name="lastName" v-model="user.lastName" placeholder="Last name" />
		<br>
		<button v-on:click="register" >Register test</button>
	</div>		  
</div>
`	  
	, 
	methods : {
		register : function (){
		console.log("Register!!!");
		
		axios
		    .post('/rest/proizvodi/register', this.user)
		    .then(response => (console.log(response)))
		    .catch((error) => console.log(error))
		}
	},
	mounted () {
		console.log("Mounted register!");
        axios
          .get('rest/proizvodi/getJustSportObjects')
          .then(response => (this.sportObjects = response.data))
    },
});	  