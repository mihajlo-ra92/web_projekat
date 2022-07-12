Vue.component("sport-object", {
	data: function(){
		return{
			currentUser: null,
			trainers :null,
			contents: {},
			selected: false,
			selectedContent:{
				name:'',
				type:'',
				description:'',
				duration:''
			},
			SportObject : null,
     	    selectedObject: {},
			input: {
				name:"",
				workoutType:"",
				description:"",
				workoutDuration:"",
							
			}
		}
	},
	
	template: ` 
<div class="h-100 d-flex align-items-center justify-content-center">
	<div v-if="selected != true">
		This sport object:
		<br>
		<div v-if="SportObject  != null">
		<br>
			<p>Name:{{this.SportObject.name}}</p>
			<p>Object type:{{this.SportObject.objectType}}</p>
			<p>Is open:{{this.SportObject.isOpen}}</p>
			<p>Average grade:{{this.SportObject.avegareGrade}}</p>
		
			<table v-if="SportObject != null" >
				<tr bgcolor="lightgrey">
					<th scope="col">Content name     </th>
					<th scope="col">Type of content     </th>
					<th scope="col">Duration   </th>
					<th scope="col">Description  </th>
					<th scope="col">Trainer  </th>
				</tr>
				<tr v-for="so in contents" v-on:click="selectObject(so)">
					<td>{{so.name}}</td>
					<td>{{so.workoutType}}</td>
					<td>{{so.workoutDuration}} min</td>
					<td>{{so.description}}</td>
					<td>{{so.trainer}}</td>
				</tr>
			</table>	
			Add content:
			<br>		
			<input type="text" placeholder="Name" v-model="input.name">
			<br>
			<input type="radio" name="workoutType" value="Personal" v-model="input.workoutType">Personal
			<br>
			<input type="radio" name="workoutType" value="Group" v-model="input.workoutType">Group
			<br>
			<input type="radio" name="workoutType" value="Solo" v-model="input.workoutType">Solo
			<br>
			<input type="number" placeholder="Duration" v-model="input.workoutDuration">
			<br>
			<input type="text" placeholder="Description" v-model="input.description">
			<br>
			<button v-on:click="addContent">Add content</button>
		</div>
	</div>	
	
	
	<div v-if="selected != false">
		
		<input type="text" name="name" v-model="selectedContent.name"></input>
		<br>
		<input type="text" name="type" v-model="selectedContent.workoutType"></input>
		<br>
		<input type="text" name="duration" v-model="selectedContent.workoutDuration"></input>
		<br>
		<input type="text" name="description" v-model="selectedContent.description"></input>		
		<br>
		<label>Odaberite trenera:</label>
		<br>
		<table class="table table-bordered table--lg team-roster-table table-hover"">
		<tr bgcolor="lightgrey">
				<th scope="col">Name</th>
				<th scope="col">Surname</th>
		</tr>
		
		<tr v-for="tr in trainers" v-on:click="addTrainerToContent(tr)">
		<td scope="row">{{tr.firstName}}</td>
		<td>{{tr.lastName}}</td>
		</tr>
		</table>
		<br>
		<button type="button" v-on:click="edit()">Edit</button>
		<br>
		<button type="button" v-on:click="unselect()">Back</button>
		<button type="button" v-on:click="deleteContent()">Delete</button>
	</div>
	
</div>	  
`,

	methods:{
		edit: function(){
			console.log("edit!");
			axios
			.post('rest/editContent',this.selectedContent)
			.then(response =>(console.log(response.data)))
			.catch((error) => console.log(error));
			
			axios 
			.get('/rest/contnentsForMenagersObject')
			.then(response => {	this.contents = response.data;
			console.log(this.contents)});
			this.selected = false;
		},
		deleteContent: function(){
			axios
			.post('rest/deleteContent',this.selectedContent.name)
			.then(response =>(console.log(response.data)))
			.catch((error) => console.log(error));
			
			axios
			.get('/rest/contnentsForMenagersObject')
			.then(response => {
				this.contents  = response.data});
			this.selected = false;
		},
		addTrainerToContent: function(trainer){
			console.log("dodajemo trenera.");
			const request =  this.selectedContent.name + '+' + trainer.username
			
			axios
			.post('rest/AddTrainerToContent', request)
			.then(response =>(console.log(response.data)))
			.catch((error) => console.log(error));
				
			axios 
			.get('/rest/contnentsForMenagersObject')
			.then(response => {	this.contents = response.data;});
			console.log(this.contents);
				
			this.selected = false;
		},
		addContent : function(){
			isExist = false;
			console.log("Treba da se doda post za content");
			if(this.contents != null){
				for(let i = 0; i < this.contents.length; i++){
					if(this.input.name === this.contents[i].name){
						isExist = true;
					}
				}	
			}
			if(isExist === false){				
				axios
				.post('rest/proizvodi/CreateContent', this.input)
				.then(response => this.contents = response.data)
				.catch((error) => console.log(error));
				
				axios
				.get('/rest/contnentsForMenagersObject')
				.then(response => {
				this.contents  = response.data;});
				
			}else{
				console.log("Vec postoji!");
				toast("Content exist!");
			}
		},
		selectObject : function(content){
			console.log("Usli smo u select.");
			if(content.trainerId != null){				
				console.log("Vec ima trenera!");
			}							
			this.selectedContent = content;
			this.selected = true;
		},
		unselect : function() {
			console.log("vrati na tabelu!");
			this.selected = false;
		
		axios 
			.get('/rest/contnentsForMenagersObject')
			.then(response => {	this.contents = response.data;
			console.log(this.contents)});
			
		}
	}, 
	mounted () {
		console.log("Mounted sport object!");
			axios
			.get('/rest/MenagersSportObject')
			.then(response => {
				this.SportObject  = response.data;
				console.log(this.SportObject)});
			
			axios 
			.get('/rest/contnentsForMenagersObject')
			.then(response => {	this.contents = response.data;
			console.log(this.contents);
			
			});
			
			axios
			.get('rest/getCurrentUser')
      		.then(response => (this.currentUser = response.data));
      		
      		axios
			.get('rest/getAllTrainers')
			.then(response => (this.trainers = response.data));			
		},
});