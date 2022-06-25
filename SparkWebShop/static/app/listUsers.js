Vue.component("list-users", {
	data: function () {
		    return {
				currentUser: null,
				users: null,
				search: ''
		    }
	},
	computed: {
		filteredUsers(){
			if(this.users !== null){				
			const nameFilter = this.users.filter(user => user.firstName.toLowerCase().includes(this.search.toLowerCase()));
			const surnameFilter = this.users.filter(user => user.lastName.toLowerCase().includes(this.search.toLowerCase()));
			const username = this.users.filter(user => user.username.toLowerCase().includes(this.search.toLowerCase()));
			const allFilters = nameFilter.concat(surnameFilter, username);
			const final = Array.from(allFilters.reduce((map, obj) => map.set(obj.id,
				obj),new Map()).values());
				return final;
			}
		}
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div>
		List users:
		<br>
		<input type="text" v-model="search" placeholder="Search objects"/>
		<br>
			<table border="1">
			<tr bgcolor="lightgrey">
				<th>Username</th>
				<th>Password</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>BirthDate</th>
				<th>Gender</th>
			</tr>
				
			<tr v-for="user in filteredUsers">
				<td>{{user.username}}</td>
				<td>{{user.password }}</td>
				<td>{{user.firstName }}</td>
				<td>{{user.lastName }}</td>
				<td>{{user.birthDate }}</td>
				<td>{{user.gender }}</td>
			</tr>
		</table>
		
	</div>	
	
	
</div>	  
`
	,
	methods : {
		startEdit(){
			console.log("Pushing router to edit profile!");
			router.push('/edit-profile')
		},
		
		startEdit(){
			console.log("Pushing router to create sport object!");
			router.push('/edit-profile')
		}
	},
	mounted () {
        console.log("Mounted list users");
        axios
			.get('rest/getCurrentUser')
      		.then(response => (this.currentUser = response.data))
      	
      	
      	axios	
      		.get('rest/proizvodi/getJustUsers')
          .then(response => (this.users = response.data))
    }
});