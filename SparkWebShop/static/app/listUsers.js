Vue.component("list-users", {
	data: function () {
		    return {
				currentUser: null,
				users: null,
				searchName: '',
				searchSurname : '',
				searchUsername : '',
				allUsers : null,
				selectedType :''
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
		<br>
		<input type="text" v-model="searchUsername" placeholder="Search user by username"/>
		<input type="text" v-model="searchName" placeholder="Search user by name"/>
		<input type="text" v-model="searchSurname" placeholder="Search user by surname"/>
		<select name="UserType" @change="onChange($event)" v-model="selectedType">
			<option value="">All users</option>
			<option value="BUYER">Buyer</option>
			<option value="TRAINER">Trainer</option>
			<option value="MENAGER">Menager</option>
			<option value="ADMIN">Admin</option>
		</select>
		<br>
		<button v-on:click="search">Search</button>
		<br>
		List users:
		<br>
			<table border="1">
			<tr bgcolor="lightgrey">
				<th>Username</th>
				<th>Password</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>BirthDate</th>
				<th>Gender</th>
				<th>Role  </th>
			</tr>
				
			<tr v-for="user in users">
				<td>{{user.username}}</td>
				<td>{{user.password }}</td>
				<td>{{user.firstName }}</td>
				<td>{{user.lastName }}</td>
				<td>{{user.birthDate }}</td>
				<td>{{user.gender }}</td>
				<td>{{user.role }}</td>
				
				
			</tr>
		</table>
		
	</div>	
	
	
</div>	  
`
	,
	methods : {
			onChange(event) {
		//	console.log(event.target.value, this.selectedType);	
				
		},	
		search : function(){
			retVal = [];
			for(let i = 0 ; i < this.allUsers.length; i++){
				if(this.allUsers[i].firstName.
				toLowerCase().includes(this.searchName.toLowerCase()) &&
				this.allUsers[i].lastName.
				toLowerCase().includes(this.searchSurname.toLowerCase()) &&
				this.allUsers[i].username.
				toLowerCase().includes(this.searchUsername.toLowerCase()) &&
				this.allUsers[i].role.
				toLowerCase().includes(this.selectedType.toLowerCase()))
				{
					retVal.push(this.allUsers[i]);
				}
			}
			
			this.users = retVal;
		}
	},
	mounted () {
        console.log("Mounted list users");
        axios
			.get('rest/getCurrentUser')
      		.then(response => (this.currentUser = response.data))
      	
      	
      	axios	
      		.get('rest/proizvodi/getJustUsers')
          .then(response => {this.users = response.data;
          					this.allUsers = response.data;
          })
    }
});