Vue.component("sport-objects", {
	data: function () {
		    return {
		      sportObjects: null,
		      selectedObject: {},
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
	<div>
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
		<
	</div>		  
</div>
`	  
	, 
	methods : {
		selectObject : function(sportObject){
			console.log("Usli smo u select.");
			selectedObject = sportObject;
			console.log(selectedObject);
			router.push('/sport-object');
			
		}
	},
	mounted () {
		console.log("Mounted sportObjects!");
        axios
          .get('rest/proizvodi/getJustSportObjects')
          .then(response => (this.sportObjects = response.data))
    },
});	  