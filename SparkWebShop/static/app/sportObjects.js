//Main NE RADIMO VISE computed I  filteredSportObjects
Vue.component("sport-objects", {
	data: function () {
		    return {
			currentUser: null,
			allSportObjects : null,
		      sportObjects: null,
		      selectedObject: {},
		      selected: false,
		      searchName: '',
		      searchObjectType:'',
		      searchAddress:'',
		    	selectedType : "",
		 	  	selectedActivity : "",
		      workouts:null,
		      filteredWorkouts:{},
		      selectedObjectComments: null,
		      selectedObjectUnapprovedComments: null

		    }
	},
	computed: {
		filteredSportObjects() {
			if(this.sportObjects !== null){
				const nameFilter = this.sportObjects.filter(so => so.name.toLowerCase().includes(this.search.toLowerCase()));
				const objectTypeFilter = this.sportObjects.filter(so => so.objectType.toLowerCase().includes(this.search.toLowerCase()));
				const addressFilter = this.sportObjects.filter(so => so.location.address.toLowerCase().includes(this.search.toLowerCase()));
				const gradeFilter = this.sportObjects.filter(so => so.avegareGrade == this.search);
				const allFilters = typeFilter.concat(nameFilter,objectTypeFilter, addressFilter, gradeFilter);
				const final = Array.from(allFilters.reduce((map, obj) => map.set(obj.id,
				obj),new Map()).values());
				return final;
			}
		}
	},
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div v-if="selected != true">
		Existing sport objects:
		<input type="text" v-model="searchName" placeholder="Search objects name"/>
		<input type="text" v-model="searchObjectType" placeholder="Search objects type"/>
		<input type="text" v-model="searchAddress" placeholder="Search objects address"/>
		
		<select name="SOType" @change="onChange($event)" v-model="selectedType">
			<option value="">All objects</option>
			<option value="gym">Gyms</option>
			<option value="pool">Pools</option>
			<option value="dance">Dance studios</option>
		</select>
		
		<select name="SOActive" @change="onChangeActive($event)" v-model="selectedActivity">
			<option value="">All objects</option>
			<option value="true">Open</option>
			<option value="false">Close</option>
		</select>
		<br>
		
		<button v-on:click="search">Search</button>
		
		<table class="table table-bordered table--lg team-roster-table table-hover"">
			<tr bgcolor="lightgrey">
				<th scope="col">Name</th>
				<th scope="col">Object type</th>
				<th scope="col">Status</th>
				<th scope="col">Address</th>
				<th scope="col">Grade</th>
			</tr>
				
			<tr v-for="so in sportObjects" v-on:click="selectObject(so)">
				<td scope="row">{{so.name }}</td>
				<td>{{so.objectType }}</td>
				<td v-if="so.isOpen">Open</td>
				<td v-else>Closed</td>
				<td>{{so.location.address }}</td>
				<td>{{so.avegareGrade }}</td>
			</tr>
		</table>
		<br>
		<br>
		<br>
	</div>	
	
	<div v-if="selected != false">
		This sport object:
		<br>
		<img src="dumbbell.png" alt="Gym picture">
		<br>
		<label>Name:</label>
			<label>{{this.selectedObject.name}}</label>
			<br>
		<label>Object type:</label>
			<label>{{this.selectedObject.objectType}}</label>
			<br>
		<label>Is open:</label>
			<label>{{this.selectedObject.isOpen}}</label>
			<br>
		<label>Average grade:</label>
			<label>{{this.selectedObject.avegareGrade}}</label>
			<br>
		<label>Adress:</label>
			<label>{{this.selectedObject.location.address}}</label>
			<br>
		<table v-if="workouts.length != 0">
				<tr bgcolor="lightgrey">
					<th scope="col">Content name  </th>
					<th scope="col">Content type  </th>
					<th scope="col">Duration  </th>
					<th scope="col">Description  </th>
				</tr>
				
				<tr v-for="so in workouts" v-if="so.sportObject === selectedObject.name">
					<td scope="row">{{so.name}}</td>
					<td scope="row">{{so.workoutType}}</td>
					<td scope="row">{{so.workoutDuration}}</td>
					<td scope="row">{{so.description}}</td>
				</tr>
			</table>
		
		<div v-if="selectedObjectComments != null">
			<br>
			<div v-for="com in selectedObjectComments" class="p-3 mb-2 bg-primary text-white">
				<h5>{{com.buyer}}</h5>
				<p>{{com.content}}<p>
				<p class="text-dark">Grade: {{com.grade}}</p>
			</div>
		
		<div v-if="selectedObjectUnapprovedComments != null && (currentUser.role.includes('MENAGER') || currentUser.role.includes('TRAINER'))">
			<p>Unapproved comments</p>
			<div v-for="unCom in selectedObjectUnapprovedComments" class="p-3 mb-2 bg-primary text-white">
				<h5>{{unCom.buyer}}</h5>
				<p>{{unCom.content}}<p>
				<p class="text-dark">Grade: {{unCom.grade}}</p>
				<p class="text-dark">Status: {{unCom.status}}</p>
			</div>
		</div>
		<br>
		<button type="button" v-on:click="unselect()">Back</button>
		</div>
		
		<br>
	</div>		  
</div>
`	  
	, 
	methods : { 
		search : function(){
			if(this.selectedType < this.selectedActivity){
				console.log("Ovde 1");
			}else{
				console.log("Ovde 2");
			}
			console.log(this.selectedType);
			console.log(this.selectedActivity);
			retVal = [];
			for(let i = 0; i < this.allSportObjects.length; i++){
				if(this.allSportObjects[i].name.
				toLowerCase().includes(this.searchName.toLowerCase()) &&
				this.allSportObjects[i].objectType.
				toLowerCase().includes(this.searchObjectType.toLowerCase()) &&
				this.allSportObjects[i].location.address.
				toLowerCase().includes(this.searchAddress.toLowerCase()) && 
				this.allSportObjects[i].objectType.
				toLowerCase().includes(this.selectedType.toLowerCase())
				)
				{
					if(
					this.allSportObjects[i].isOpen == false &&
					this.selectedActivity === "false"
					){						
						retVal.push(this.allSportObjects[i]);
					}else if(
						this.allSportObjects[i].isOpen == true &&
						this.selectedActivity === "true"
					){
						retVal.push(this.allSportObjects[i]);
					}else if(this.selectedActivity === ""){
						retVal.push(this.allSportObjects[i]);
					}
				}
			}
			
			this.sportObjects = retVal;
			
		},
		onChangeActive(event){
				console.log(event.target.value, this.selectedActivity);		
		},
		
			onChange(event) {
			console.log(event.target.value, this.selectedType);			
		},		
		
		selectObject : function(sportObject){
			console.log("Usli smo u select.");
			this.selectedObject = sportObject;
			this.selected = true;
			axios
			    .post('/rest/comments-for-object', sportObject.name)
			    .then(response => {
					this.selectedObjectComments = response.data;
					console.log(this.selectedObjectComments);
				})
		    	.catch((error) => console.log(error));
		    axios
			    .post('/rest/unapproved-comments-for-object', sportObject.name)
			    .then(response => {
					this.selectedObjectUnapprovedComments = response.data;
					console.log(this.selectedObjectUnapprovedComments);
				})
		    	.catch((error) => console.log(error));
		},
		unselect : function() {
			console.log("vrati na tabelu!");
			this.selected = false;
			
		}
	},
	mounted () {
		console.log("Mounted sportObjects!");
        axios
          .get('rest/proizvodi/getJustSportObjects')
          .then(response => {this.sportObjects = response.data;
          					this.allSportObjects = response.data;
          		
          })
        axios
	        .get('rest/proizvodi/getAllcontents')
	        .then(response => this.workouts = response.data);
        axios
			.get('rest/getCurrentUser')
      		.then(response => {
				if (response.data == '404'){
					console.log('No loged in user!');
				}
				else {
					this.currentUser = response.data;
					console.log(this.currentUser);
				}
		})
    },
});	  