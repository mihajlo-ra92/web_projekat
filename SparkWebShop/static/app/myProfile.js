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
                }
		    }
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		My profile:
		<br>
		<label>username:</label>
		<input type="text" name="username" v-bind="currentUser.username" />
		<br>
	    <label>firstname:</label>
	    <input type="text" name="firstname" v-model="currentUser.firstname"/>
		<br>
		<label>lastname:</label>
		<input type="text" name="lastname" v-model="currentUser.lastname"/>
		<br>
		<label>birthdate:</label>
		<input type="text" name="birthdate" v-model="currentUser.birthdate"/>
		<br>
		<label>gender:</label>
		<select name="gender" v-bind="currentUser.gender">
			<option value="MALE">MALE</option>
			<optionvalue="FEMALE">FEMALE</option>
		</select>
		<br>
		<button type="button" v-on:click="edit()">Edit</button>
		<button type="button" v-on:click="cancal()">Cancal</button>
		<br>
		
	</div>	
	
	
</div>	  
`
	, 
	methods : {
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
          .then(response => (this.currentUser = response.data));
        console.log(this.currentUser);
    },
});