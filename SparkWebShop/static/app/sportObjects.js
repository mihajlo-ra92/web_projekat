//Main
Vue.component("sport-objects", {
	data: function () {
		    return {
		      sportObjects: null,
		      selectedObject: {},
		      selected: false,
		      search: ''
		    }
	},
	computed: {
		filteredSportObjects() {
			if(this.sportObjects !== null){
				const nameFilter = this.sportObjects.filter(so => so.name.toLowerCase().includes(this.search.toLowerCase()));
				const objectTypeFilter = this.sportObjects.filter(so => so.objectType.toLowerCase().includes(this.search.toLowerCase()));
				const addressFilter = this.sportObjects.filter(so => so.location.address.toLowerCase().includes(this.search.toLowerCase()));
				const gradeFilter = this.sportObjects.filter(so => so.avegareGrade == this.search);
				const allFilters = nameFilter.concat(objectTypeFilter, addressFilter, gradeFilter);
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
		<input type="text" v-model="search" placeholder="Search objects"/>
		<br>
		<table class="table table-bordered table--lg team-roster-table table-hover"">
			<tr bgcolor="lightgrey">
				<th scope="col">Name</th>
				<th scope="col">Object type</th>
				<th scope="col">Status</th>
				<th scope="col">Address</th>
				<th scope="col">Grade</th>
			</tr>
				
			<tr v-for="so in filteredSportObjects" v-on:click="selectObject(so)">
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
		<img src="#/resources/images/gym_background.jpg">
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
		<table v-if="selectedObject.content != null">
				<tr bgcolor="lightgrey">
					<th scope="col">Content name  </th>
					<th scope="col">Content type  </th>
					<th scope="col">Duration  </th>
					<th scope="col">Description  </th>
				</tr>
				
				<tr v-for="so in selectedObject.content">
					<td scope="row">{{so.name}}</td>
					<td scope="row">{{so.workoutType}}</td>
					<td scope="row">{{so.workoutDuration}}</td>
					<td scope="row">{{so.description}}</td>
				</tr>
			</table>
			<br>
		<button type="button" v-on:click="unselect()">Back</button>
	</div>		  
</div>
`	  
	, 
	methods : {
		selectObject : function(sportObject){
			console.log("Usli smo u select.");
			this.selectedObject = sportObject;
			this.selected = true;
			//router.push('/sport-object');
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
          .then(response => (this.sportObjects = response.data))
    },
});	  