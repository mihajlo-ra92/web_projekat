Vue.component("home", {
	data: function () {
		    return {
				users: null,
				input: {
                    username: "",
                    password: ""
                },
                approved: false
		    }
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		Log in:
		<br>
		<input type="text" name="username" v-model="input.username" placeholder="Username" />
		<br>
	    <input type="password" name="password" v-model="input.password" placeholder="Password" />
		<br>
		<button type="button" v-on:click="login()">Login</button>
		<br>
		<br>
		<!-- Korisnici:
		<table border="1">
			<tr bgcolor="lightgrey">
				<th>Username</th>
				<th>Password</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>BirthDate</th>
				<th>Gender</th>
			</tr>
				
			<tr v-for="user in users">
				<td>{{user.username}}</td>
				<td>{{user.password }}</td>
				<td>{{user.firstName }}</td>
				<td>{{user.lastName }}</td>
				<td>{{user.birthDate }}</td>
				<td>{{user.gender }}</td>
			</tr>
		</table> -->
	</div>	
	
	
</div>	  
`
	, 
	methods : {
		login() {
                for (let i = 0; i < this.users.length; i++) {
					if (this.input.username === this.users[i].username &&
					this.input.password === this.users[i].password){
						this.approved = true;
						LogedInUser = this.users[i];
					}
            	}
            	
				if(this.approved === false){
					console.log('Failed log in!!!!');
					toast("Incorrect information!");
				}
				else{
            		axios
					.post('rest/proizvodi/log-in', this.input)
					.then(response => {
						if (response.data == '403'){
							toast("User is already loged in!");
						}
						else {
							localStorage.setItem('loggedUser', response.data);
							toast("Loged in user!");
							router.push('/sportObjects');
						}
					})
				}
        },
	},
	mounted () {
        console.log("Mounted home");
        axios
          .get('rest/proizvodi/getJustUsers')
          .then(response => (this.users = response.data))
    },
});